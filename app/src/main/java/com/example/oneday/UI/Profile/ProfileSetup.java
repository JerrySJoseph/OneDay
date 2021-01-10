package com.example.oneday.UI.Profile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.oneday.R;
import com.example.oneday.UI.DashBoard.DashBoard;
import com.example.oneday.Views.EditTextDatePicker;

public class ProfileSetup extends AppCompatActivity {

    EditTextDatePicker datePicker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_setup);
        datePicker= new EditTextDatePicker(this,R.id.date_picker);
    }

    public void onClickPortfolio(View view) {
        startActivity(new Intent(this,PortfolioActivity.class));
    }

    public void onNextClick(View view) {
        startActivity(new Intent(this, DashBoard.class));
    }
}