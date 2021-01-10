package com.example.oneday.UI.PhoneAuthentication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.oneday.R;
import com.example.oneday.UI.Profile.ProfileSetup;
import com.example.oneday.Utils.Utils;
import com.example.oneday.Views.PhoneAuthView;

import java.io.IOException;
import java.util.List;

public class PhoneAuthActivity extends AppCompatActivity {

    PhoneAuthView phoneAuthView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_auth);
        phoneAuthView=findViewById(R.id.phone_auth_view);
        phoneAuthView.setPhoneAuthListener(new PhoneAuthView.PhoneAuthListeners() {
            @Override
            public void onSendCodeClick() {
                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        phoneAuthView.moveToVerify();
                        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                phoneAuthView.setVerificationCode("235689");
                            }
                        },2000);
                    }
                },2000);
            }

            @Override
            public void onVerifyCodeClick() {

                Toast.makeText(getApplicationContext(),"code:"+ phoneAuthView.getVerificationCode(),Toast.LENGTH_LONG).show();
                startActivity(new Intent(getApplicationContext(), ProfileSetup.class));
            }

            @Override
            public void onVerificationSuccess() {

            }

            @Override
            public void onTimeout() {

            }

            @Override
            public void onVerificationFailed() {

            }
        });
    }

}