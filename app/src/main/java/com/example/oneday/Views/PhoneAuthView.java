package com.example.oneday.Views;

import android.content.Context;
import android.content.res.TypedArray;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.example.oneday.BuildConfig;
import com.example.oneday.R;
import com.example.oneday.Utils.Utils;

import java.io.IOException;
import java.util.List;

public class PhoneAuthView extends RelativeLayout {


    //Components in layout
    ImageView flag;
    EditText code,phone,verify_code;
    LinearLayout auth_btn,verify_btn;
    RelativeLayout layout;


    // Style Variables
     int auth_hero_src,verify_hero_src,themeColor,auth_btn_icon;
     String phone_auth_title,phone_verify_title;
     String phone_auth_desc,phone_verify_desc,auth_btn_text,verify_btn_text;
     int verify_btn_icon,phone_code_array;
     boolean autoPhoneCode,enable_flag,showPhoneCode;



    //Custom interface for event listeners
    PhoneAuthListeners phoneAuthListener;
    Context ctx;

    public PhoneAuthView(Context context) {
        super(context);
        ctx=context;
        Init( context,null);
    }

    public PhoneAuthView(Context context, AttributeSet attrs) {
        super(context, attrs);
        ctx=context;
        Init( context,attrs);
    }

    public PhoneAuthView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        ctx=context;
        Init( context,attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public PhoneAuthView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        ctx=context;
        Init( context,attrs);
    }

    private void Init(Context context,AttributeSet attrs)
    {
        //Inflating the layout
        inflate(context, R.layout.view_phone_auth,this);

        //declaring the components in layout
        flag=findViewById(R.id.flag);
        code=findViewById(R.id.code);
        phone=findViewById(R.id.phone);
        auth_btn=findViewById(R.id.auth_btn);
        layout=findViewById(R.id.layout);


        //Autoset Phone Code and Flag
        SetPhoneCodeandFlag(context);

        //assign styled attributes if not null
        if(attrs!=null)
            processStyledAttributes( context, attrs);

        //Click Listeners for buttons in both auth and verify Screens
        auth_btn.setOnClickListener(v -> {
           phoneAuthListener.onSendCodeClick();
        });

    }
    public void moveToVerify()
    {
        layout.removeAllViews();
        inflate(layout.getContext(),R.layout.view_phone_verify,this);
        verify_btn=findViewById(R.id.verify_btn);
        verify_code=findViewById(R.id.verify_code);

        verify_btn.setOnClickListener(v -> {
            phoneAuthListener.onVerifyCodeClick();
        });

    }
    public void setVerificationCode(String code)
    {
        verify_code.setText(code);
    }
    public String getVerificationCode()
    {
       return verify_code.getText().toString().trim();
    }
    public void setPhoneAuthListener(PhoneAuthListeners mListener) {
        this.phoneAuthListener = mListener;
    }
    private void SetPhoneCodeandFlag(Context context)
    {
        String country_code = null;
        String country_name=null;
        LocationManager lm = (LocationManager)context.getSystemService(Context.LOCATION_SERVICE);
        Geocoder geocoder = new Geocoder(context);

        for(String provider: lm.getAllProviders()) {
            @SuppressWarnings("ResourceType") Location location = lm.getLastKnownLocation(provider);
            if(location!=null) {
                try {
                    List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                    if(addresses != null && addresses.size() > 0) {
                        country_code = addresses.get(0).getCountryCode();
                        country_name=addresses.get(0).getCountryName();
                        break;
                    }
                } catch (IOException e) {
                    Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        }
        code.setText(getPhoneCode(context,country_code));
        flag.setImageResource(getCountryFlagResource(context,country_name));

    }
    private void processStyledAttributes(Context context,AttributeSet attrs)
    {
        //Reading style attributes
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs,R.styleable.PhoneAuthView,0, 0);
        try{
            phone_auth_title=a.getString(R.styleable.PhoneAuthView_phone_auth_title)!=null?
                    a.getString(R.styleable.PhoneAuthView_phone_auth_title):
                    "Continue with Phone";
            phone_auth_desc=a.getString(R.styleable.PhoneAuthView_phone_auth_desc)!=null?
                    a.getString(R.styleable.PhoneAuthView_phone_auth_desc):
                    "Please Enter your Phone number to verify";
            phone_verify_title=a.getString(R.styleable.PhoneAuthView_phone_verify_title)!=null?
                    a.getString(R.styleable.PhoneAuthView_phone_verify_title):
                    "Enter the OTP";
            phone_verify_desc=a.getString(R.styleable.PhoneAuthView_phone_verify_desc)!=null?
                    a.getString(R.styleable.PhoneAuthView_phone_verify_desc):
                    "We have sent you an OTP. Please enter the OTP below to continue";
            auth_btn_text=a.getString(R.styleable.PhoneAuthView_auth_btn_text)!=null?
                    a.getString(R.styleable.PhoneAuthView_auth_btn_text):
                    "Send Code";
            verify_btn_text=a.getString(R.styleable.PhoneAuthView_verify_btn_text)!=null?
                    a.getString(R.styleable.PhoneAuthView_verify_btn_text):
                    "Verify";
            enable_flag=a.getBoolean(R.styleable.PhoneAuthView_enable_flag,false);
            autoPhoneCode=a.getBoolean(R.styleable.PhoneAuthView_autoPhoneCode,true);
            showPhoneCode=a.getBoolean(R.styleable.PhoneAuthView_showPhoneCode,true);


        }catch (Exception e){
            if(BuildConfig.DEBUG)
                Toast.makeText(context,e.getMessage(),Toast.LENGTH_LONG).show();
            Log.e("ERR",e.getMessage());
        }
        finally {
            a.recycle();
        }

    }
    private String getPhoneCode(Context ctx, String countryCode)
    {
        String[] codes=ctx.getResources().getStringArray(R.array.CountryCodes);
        for(String item:codes)
        {
            String[] array_items=item.split(",");
            if(countryCode.equals(array_items[1]))
            {
                return "+"+array_items[0];
            }
        }
        return null;
    }
    private int getCountryFlagResource(Context context,String countryName)
    {
        countryName=countryName.toLowerCase().replace(" ","");
        return  context.getResources().getIdentifier("drawable/"+countryName, null, context.getPackageName());
    }

    public interface PhoneAuthListeners{
        void onSendCodeClick();
        void onVerifyCodeClick();
        void onVerificationSuccess();
        void onTimeout();
        void onVerificationFailed();

    }

}
