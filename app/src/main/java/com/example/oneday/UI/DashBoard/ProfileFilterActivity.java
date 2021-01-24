package com.example.oneday.UI.DashBoard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.oneday.R;

public class ProfileFilterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_filter);
        getSupportActionBar().setTitle("Filter Profiles");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.setResult(RESULT_OK);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
}