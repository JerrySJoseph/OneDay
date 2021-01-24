package com.example.oneday.Models;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.oneday.interfaces.FirebaseSaveResponse;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DeviceProfile {
    String id,notifToken,deviceID;

    public DeviceProfile(String id, String notifToken, String deviceID) {
        this.id = id;
        this.notifToken = notifToken;
        this.deviceID = deviceID;
    }

    public DeviceProfile() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNotifToken() {
        return notifToken;
    }

    public void setNotifToken(String notifToken) {
        this.notifToken = notifToken;
    }

    public String getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(String deviceID) {
        this.deviceID = deviceID;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if(!(obj instanceof DeviceProfile) || obj.getClass()!=this.getClass())
            return false;
        DeviceProfile deviceProfile=(DeviceProfile)obj;
        return (deviceProfile.id.equals(this.id));
    }

    @NonNull
    @Override
    public String toString() {
        return "ID:["+this.id+"], Ntoken:{"+this.notifToken+"} deviceID: "+this.deviceID;
    }

    public static void Save(DeviceProfile profile, FirebaseSaveResponse responseCallback)
    {
        try{
            String uid= FirebaseAuth.getInstance().getCurrentUser().getUid();
            DatabaseReference reference= FirebaseDatabase.getInstance().getReference("device_profiles/"+uid);
            reference.setValue(profile).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    if(responseCallback!=null)
                    responseCallback.onSaveSuccess();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    if(responseCallback!=null)
                    responseCallback.onSaveFailure(e.getMessage());
                }
            });
        }catch (Exception e)
        {
            responseCallback.onSaveError(e.getMessage());
        }

    }
}
