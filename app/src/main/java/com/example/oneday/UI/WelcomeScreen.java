package com.example.oneday.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.oneday.R;
import com.example.oneday.UI.PhoneAuthentication.PhoneAuthActivity;

import java.io.IOException;
import java.util.List;

public class WelcomeScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);

    }

    public void PhoneAuth(View view) {
        startActivity(new Intent(this, PhoneAuthActivity.class));
    }
}