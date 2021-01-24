package com.example.oneday.UI.Profile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.oneday.Models.ContactProfile;
import com.example.oneday.Models.DeviceProfile;
import com.example.oneday.Models.DisplayProfile;
import com.example.oneday.Models.WalletProfile;
import com.example.oneday.R;
import com.example.oneday.UI.DashBoard.DashBoard;
import com.example.oneday.Utils.AuthMethod;
import com.example.oneday.Utils.Functions;
import com.example.oneday.Utils.PrefManager;
import com.example.oneday.Views.EditTextDatePicker;
import com.example.oneday.Views.MultiSelectGender;
import com.example.oneday.interfaces.FirebaseSaveResponse;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.messaging.FirebaseMessaging;

import java.io.IOException;
import java.util.List;

public class ProfileSetup extends AppCompatActivity {

    EditTextDatePicker datePicker;
    EditText fullname,email,bio,phone,interests,jobTitle,company,school,location,nickname;
    MultiSelectGender gender,interestedIn;
    String Country,District,State,CountryCode,PostalCode;
    double latitude,longitude;
    private static String auth_method="NONE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_setup);

        //Date picker
        datePicker= new EditTextDatePicker(this,R.id.date_picker);

        //Auth method from intent
        auth_method= PrefManager.getAuthMethodSelected(this);


        //Registering IDs
        fullname=findViewById(R.id.fullname);
        email=findViewById(R.id.email);
        bio=findViewById(R.id.bio);
        phone=findViewById(R.id.phone);
        interests=findViewById(R.id.interests);
        jobTitle=findViewById(R.id.jobtitle);
        company=findViewById(R.id.company);
        school=findViewById(R.id.school);
        location=findViewById(R.id.location);
        nickname=findViewById(R.id.nickname);
        gender=findViewById(R.id.gender);
        interestedIn=findViewById(R.id.interestedIn);
        // Implement click listener over button
        findViewById(R.id.image_picker).setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View arg0) {

                       startActivity(new Intent(getApplicationContext(),ImageUploadSelectionActivity.class));

                    }
                });

        switch (auth_method)
        {
            case AuthMethod.FB_AUTH:FacebookProfileSetupSequence();break;
            case AuthMethod.GOOGLE_AUTH:GoogleProfileSetupSequence();break;
            case AuthMethod.PHONE_AUTH:PhoneProfileSetupSequence();break;
            default: return;
        }
    }

    private void PhoneProfileSetupSequence() {
        //Loading current user
        FirebaseUser firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
        setData(phone,firebaseUser.getPhoneNumber());
    }

    private void GoogleProfileSetupSequence() {
        //Loading current user
        FirebaseUser firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
        setData(email,firebaseUser.getEmail());
        setData(fullname,firebaseUser.getDisplayName());
    }

    private void FacebookProfileSetupSequence() {
        //Loading current user
        FirebaseUser firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
        setData(fullname,firebaseUser.getDisplayName());

    }

    private void setData(EditText editText,String Data)
    {
        editText.setText(Data);
        editText.setEnabled(false);
    }
    private boolean validateFields()
    {
        if(!checkEmpty(fullname,"Full name should not be blank"))
            return false;
        if(!checkEmpty(email,"Email should not be blank"))
            return false;
        if(!checkEmpty(bio,"Bio should not be blank"))
            return false;
        if(!checkEmpty(phone,"Phone  should not be blank"))
            return false;
        if(!checkEmpty(interests,"Interests should not be blank"))
            return false;
        if(!checkEmpty(location,"location should not be blank"))
            return false;
        return true;
    }
    private boolean checkEmpty(EditText editText,String errorMessage)
    {
        if(editText.getText().toString()==null || editText.getText().toString().isEmpty())
        {
            editText.setError(errorMessage);
            return false;
        }
        return true;
    }
    private void prepareAndSaveData()
    {
        DisplayProfile displayProfile= new DisplayProfile();
        ContactProfile contactProfile= new ContactProfile();
        DeviceProfile deviceProfile= new DeviceProfile();
        WalletProfile walletProfile= new WalletProfile();

        //Preparing display Profile
        displayProfile.setId(FirebaseAuth.getInstance().getCurrentUser().getUid());
        displayProfile.setName(readValue(fullname,FirebaseAuth.getInstance().getCurrentUser().getUid()));
        displayProfile.setBio(readValue(bio,"N/A"));
        displayProfile.setInterests(readValue(interests,"N/A"));
        displayProfile.setJobTitle(readValue(jobTitle,"N/A"));
        displayProfile.setCompany(readValue(company,"N/A"));
        displayProfile.setSchool(readValue(school,"N/A"));
        displayProfile.setNickname(readValue(nickname,displayProfile.getName()));
        displayProfile.setGender(gender.getSelectedGender());
        displayProfile.setInterestedIn(interestedIn.getSelectedGender());
        displayProfile.setDOB(datePicker.getSelectedDate());
        DisplayProfile.Save(displayProfile, new FirebaseSaveResponse() {
            @Override
            public void onSaveSuccess() {

            }

            @Override
            public void onSaveFailure(String reason) {

            }

            @Override
            public void onSaveError(String reason) {

            }
        });
        getLocationData();
        contactProfile.setId(FirebaseAuth.getInstance().getCurrentUser().getUid());
        contactProfile.setEmail(readValue(email,"N/A"));
        contactProfile.setPhone(readValue(phone,"N/A"));
        contactProfile.setCountry(Country==null?"N/A":Country);
        contactProfile.setCountryCode(CountryCode==null?"N/A":CountryCode);
        contactProfile.setDistrict(District==null?"N/A":District);
        contactProfile.setState(State==null?"N/A":State);
        contactProfile.setPostalCode(PostalCode==null?"N/A":PostalCode);
        contactProfile.setLatitude(latitude);
        contactProfile.setLongitude(longitude);
        ContactProfile.Save(contactProfile, new FirebaseSaveResponse() {
            @Override
            public void onSaveSuccess() {
                startActivity(new Intent(getApplicationContext(), DashBoard.class));
            }

            @Override
            public void onSaveFailure(String reason) {

            }

            @Override
            public void onSaveError(String reason) {

            }
        });
        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            Log.e("FCM","Fetching firebase token failed");
                            return;
                        }

                        // Get new FCM registration token
                        String token = task.getResult();
                        deviceProfile.setNotifToken(token);
                        deviceProfile.setId(FirebaseAuth.getInstance().getCurrentUser().getUid());
                        String deviceID= Functions.getDeviceID(getApplicationContext());
                        deviceProfile.setDeviceID(deviceID);
                        DeviceProfile.Save(deviceProfile,null);
                    }
                });

    }
    private void getLocationData()
    {
        LocationManager lm = (LocationManager)this.getSystemService(Context.LOCATION_SERVICE);
        Geocoder geocoder = new Geocoder(this);

        for(String provider: lm.getAllProviders()) {
            @SuppressWarnings("ResourceType") Location location = lm.getLastKnownLocation(provider);
            if(location!=null) {
                try {
                    List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                    if(addresses != null && addresses.size() > 0) {
                        CountryCode=addresses.get(0).getCountryCode();
                        Country=addresses.get(0).getCountryName();
                        District=addresses.get(0).getSubLocality();
                        State=addresses.get(0).getLocality();
                        PostalCode=addresses.get(0).getPostalCode();
                        latitude=(addresses.get(0).getLatitude());
                        longitude=(addresses.get(0).getLongitude());
                        break;
                    }
                } catch (IOException e) {
                    Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        }
    }
    private String readValue(EditText editText, String defaultValue)
    {
        String value=editText.getText().toString().trim();
        return value==null || value.isEmpty()?defaultValue:value;
    }
    public void onClickPortfolio(View view) {
        startActivity(new Intent(this,PortfolioActivity.class));
    }

    public void onNextClick(View view) {
        if(validateFields())
            prepareAndSaveData();

    }
}