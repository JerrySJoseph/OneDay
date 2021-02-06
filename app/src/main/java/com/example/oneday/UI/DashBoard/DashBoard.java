package com.example.oneday.UI.DashBoard;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.oneday.Adapters.ProfileDisplayAdapter;
import com.example.oneday.Models.Profile;
import com.example.oneday.R;
import com.example.oneday.UI.Chat.ChatActivity;
import com.example.oneday.UI.Likes.LikesActivity;
import com.example.oneday.UI.Notifications.NotificationsActivity;
import com.example.oneday.UI.Requests.RequestsActivity;
import com.example.oneday.Utils.Animations;
import com.example.oneday.Views.BottomNavigation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GetTokenResult;

import java.util.ArrayList;

public class DashBoard extends AppCompatActivity {

    ViewPager2 viewPager2;
    ArrayList<Profile> models= new ArrayList<>();
    private static int FILTER_REQUEST_CODE=101;
    BottomNavigation bottomNavigation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);


        //Fetching firebase Notification Token
        /*
        FirebaseUser mUser = FirebaseAuth.getInstance().getCurrentUser();
         mUser.getIdToken(true)
                .addOnCompleteListener(new OnCompleteListener<GetTokenResult>() {
                    public void onComplete(@NonNull Task<GetTokenResult> task) {
                        if (task.isSuccessful()) {
                            String idToken = task.getResult().getToken();
                            Log.e("IDTOKEN",idToken);
                            // Send token to your backend via HTTPS
                            // ...
                        } else {
                            // Handle error -> task.getException();
                        }
                    }
                });*/
        bottomNavigation=findViewById(R.id.bottom_nav);
        viewPager2=findViewById(R.id.viewpager);

        //Creating dummy profiles
        for(int i=0;i<50;i++)
            models.add(new Profile());

        ProfileDisplayAdapter adapter= new ProfileDisplayAdapter(models);
        adapter.setOnProfileClickListener(profileClickListener);
        adapter.enableAds(true);
        adapter.setInterval(10);
        viewPager2.setAdapter(adapter);
        viewPager2.setOffscreenPageLimit(3);
        viewPager2.getChildAt(0).setOverScrollMode(View.OVER_SCROLL_ALWAYS);
        bottomNavigation.setOnBottomNavigationClickListener(bottomNavigationClickListener);
        bottomNavigation.setBadge(BottomNavigation.Button.NOTIFICATION,true);
        //Pulse animating badge for chat
        Animations.pulse(findViewById(R.id.badge_chat));
    }

    //Profile Display Click listeners
    ProfileDisplayAdapter.onProfileClickListener profileClickListener=new ProfileDisplayAdapter.onProfileClickListener() {
        @Override
        public void onProfileClick(Profile profile) {
            startActivity(new Intent(getApplicationContext(),ProfileDisplayActivity.class));
        }

        @Override
        public void onLikeClick(Profile profile) {
            Toast.makeText(getApplicationContext(),"onLikeClicked",Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onSuperLikeClick(Profile profile) {
            Toast.makeText(getApplicationContext(),"onSuperLikeClicked",Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onDislikeClick(Profile profile) {
            Toast.makeText(getApplicationContext(),"onDislikeClicked",Toast.LENGTH_SHORT).show();
        }
    };

    //Bottomnavigation click listeners
    BottomNavigation.onBottomNavigationClickListener bottomNavigationClickListener= new BottomNavigation.onBottomNavigationClickListener() {
        @Override
        public void onNotificationClick() {
            startActivity(new Intent(getApplicationContext(), NotificationsActivity.class));
        }

        @Override
        public void onLikesClick() {
            startActivity(new Intent(getApplicationContext(), LikesActivity.class));
        }

        @Override
        public void onHomeClick() {
            Toast.makeText(getApplicationContext(),"onHomeClick",Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onRequestsClick() {
            startActivity(new Intent(getApplicationContext(), RequestsActivity.class));
        }


        @Override
        public void onProfileClick() {
            Toast.makeText(getApplicationContext(),"onProfileClick",Toast.LENGTH_SHORT).show();
        }
    };

    //Filter button Click Listener
    public void onFilterClick(View view) {
        startActivityForResult(new Intent(this, ProfileFilterActivity.class),FILTER_REQUEST_CODE);
    }
    //Chat Button Click Listener
    public void onChatClick(View view) {
        startActivity(new Intent(getApplicationContext(), ChatActivity.class));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==FILTER_REQUEST_CODE && resultCode==RESULT_OK)
            Toast.makeText(this,"Filter success",Toast.LENGTH_SHORT).show();
        super.onActivityResult(requestCode, resultCode, data);
    }


}