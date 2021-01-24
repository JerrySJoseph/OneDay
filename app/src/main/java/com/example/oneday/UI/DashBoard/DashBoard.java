package com.example.oneday.UI.DashBoard;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.oneday.Adapters.ProfileDisplayAdapter;
import com.example.oneday.Models.DisplayProfile;
import com.example.oneday.R;
import com.example.oneday.Views.BottomNavigation;

import java.util.ArrayList;

public class DashBoard extends AppCompatActivity {

    ViewPager2 viewPager2;
    ArrayList<DisplayProfile> models= new ArrayList<>();
    private static int FILTER_REQUEST_CODE=101;
    BottomNavigation bottomNavigation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        bottomNavigation=findViewById(R.id.bottom_nav);
        viewPager2=findViewById(R.id.viewpager);
        for(int i=0;i<50;i++)
            models.add(new DisplayProfile());
        ProfileDisplayAdapter adapter= new ProfileDisplayAdapter(models);
        adapter.setOnProfileClickListener(new ProfileDisplayAdapter.onProfileClickListener() {
            @Override
            public void onProfileClick(DisplayProfile profile) {
                startActivity(new Intent(getApplicationContext(),ProfileDisplayActivity.class));
            }
        });
        adapter.setEnableAds(true);
        adapter.setInterval(10);
        viewPager2.setAdapter(adapter);
        viewPager2.setOffscreenPageLimit(3);
        viewPager2.getChildAt(0).setOverScrollMode(View.OVER_SCROLL_ALWAYS);
        bottomNavigation.setOnBottomNavigationClickListener(bottomNavigationClickListener);
    }

    BottomNavigation.onBottomNavigationClickListener bottomNavigationClickListener= new BottomNavigation.onBottomNavigationClickListener() {
        @Override
        public void onNotificationClick() {
            Toast.makeText(getApplicationContext(),"onNotificationClick",Toast.LENGTH_LONG).show();
        }

        @Override
        public void onLikesClick() {
            Toast.makeText(getApplicationContext(),"onLikesClick",Toast.LENGTH_LONG).show();
        }

        @Override
        public void onHomeClick() {
            Toast.makeText(getApplicationContext(),"onHomeClick",Toast.LENGTH_LONG).show();
        }

        @Override
        public void onChatClick() {
            Toast.makeText(getApplicationContext(),"onChatClick",Toast.LENGTH_LONG).show();
        }

        @Override
        public void onProfileClick() {

        }
    };
    public void onFilterClick(View view) {
        startActivityForResult(new Intent(this, ProfileFilterActivity.class),FILTER_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==FILTER_REQUEST_CODE && resultCode==RESULT_OK)
            Toast.makeText(this,"Filter success",Toast.LENGTH_LONG).show();
        super.onActivityResult(requestCode, resultCode, data);
    }
}