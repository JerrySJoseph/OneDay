package com.example.oneday.UI.Profile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.oneday.Models.Profile;
import com.example.oneday.Models.WalletProfile;
import com.example.oneday.R;
import com.example.oneday.UI.DashBoard.DashBoard;
import com.example.oneday.Models.AuthMethod;
import com.example.oneday.Utils.Functions;
import com.example.oneday.Utils.PrefManager;
import com.example.oneday.Utils.ServerRequestHandler;
import com.example.oneday.Views.EditTextDatePicker;
import com.example.oneday.Views.MultiSelectGender;
import com.example.oneday.interfaces.FirebaseSaveResponse;
import com.example.oneday.interfaces.ServerRequestResponse;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.messaging.FirebaseMessaging;

import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

public class ProfileSetup extends AppCompatActivity {

    EditTextDatePicker datePicker;
    EditText fullname,email,bio,phone,interests,jobTitle,company,school,location,nickname;
    MultiSelectGender gender,interestedIn;
    String Country,District,State;
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
        getLocationData();
        ServerRequestHandler.SaveProfile(this,Profile.getTestObject(), new ServerRequestResponse() {
            @Override
            public void onResponse(String response) {
                Log.d("S-Response",response);
                startActivity(new Intent(ProfileSetup.this,DashBoard.class));
                PrefManager.setProfileSetupComplete(ProfileSetup.this,true);
            }

            @Override
            public void onError(String error) {
                Toast.makeText(getApplicationContext(),error,Toast.LENGTH_SHORT);
                Log.e("S-Response",error);
                //TODO: REMOVE this statement in production
                PrefManager.setProfileSetupComplete(ProfileSetup.this,true);
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
                        Country=addresses.get(0).getCountryName();
                        District=addresses.get(0).getSubLocality();
                        State=addresses.get(0).getLocality();
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
        if(true)
            prepareAndSaveData();

    }
}