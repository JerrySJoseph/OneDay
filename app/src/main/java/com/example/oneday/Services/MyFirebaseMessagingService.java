package com.example.oneday.Services;

import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.oneday.Models.NotificationModel;
import com.example.oneday.R;
import com.example.oneday.UI.DashBoard.DashBoard;
import com.example.oneday.Utils.NotificationDatabaseHelper;
import com.example.oneday.Utils.PrefManager;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.jstechnologies.notificationprovidermodule.NotificationProvider;

import java.util.Map;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    public MyFirebaseMessagingService(){
        Log.e("FCM:","Messaging Service STarted");
    }

    @Override
    public void onCreate() {
        Log.e("FCM:","Messaging Service Created");
        super.onCreate();
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Map<String, String> data = remoteMessage.getData();
        String body = data.get("body");
        String title = data.get("title");
        String Channel=data.get("Channel");
        Log.e("FCM", "body: " + data.get("body"));
        Log.e("FCM", "title: " + data.get("title"));
        Log.e("FCM", "Channel: " + data.get("Channel"));
        Log.e("FCM", "Number: " + data.get("number"));
        new NotificationProvider(this)
                .setTitle(title!=null?title:"Title") //Required
                .setBody(body!=null?body:"body")     //Required
                .setAutocancel(true)
                .setIntent(new Intent(getApplicationContext(), DashBoard.class))                  //Routes to intent when clicked on Notification
                .setNotificationIcon(R.drawable.ic_launcher_foreground)
                .setChannelID("notif_channel")
                .setChannelName("notif_channel")
                .show();
        NotificationDatabaseHelper.getInstance(this).Save(new NotificationModel(title,body, System.currentTimeMillis()));
        int ncount=PrefManager.getNotificationCount(this);
        PrefManager.setNotificationCount(this,ncount+1);
    }

    @Override
    public void onNewToken(String s) {
        Log.e("FCM","New Token: "+s);
        sendTokentoServer(s);
    }

    public void sendTokentoServer(String token)
    {
        if(FirebaseAuth.getInstance().getCurrentUser()!=null)
        {
            String uid=FirebaseAuth.getInstance().getCurrentUser().getUid();
            DatabaseReference reference= FirebaseDatabase.getInstance().getReference("device_profiles/"+uid).child("notifToken");
            reference.setValue(token)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    Log.d("FCM","New Token write to DB Success");
                }
            })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.d("FCM","New Token write to DB Failed : "+e.getMessage());
                        }
                    });


        }

    }

    @Override
    public void onDestroy() {
        Log.e("FCM","Service Destroyed");
        super.onDestroy();
    }
}
