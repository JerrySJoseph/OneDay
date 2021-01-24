package com.example.oneday.UI.Profile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.oneday.R;

public class ImageUploadSelectionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_upload_selection);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Add Image");
    }
}