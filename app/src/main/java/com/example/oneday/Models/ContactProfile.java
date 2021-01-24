package com.example.oneday.Models;

import android.location.Location;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.oneday.interfaces.FirebaseSaveResponse;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ContactProfile {

    String id,phone,email,district,state,country,countryCode,postalCode;
    double latitude,longitude;
    boolean isVerified=false;

    public ContactProfile() {
    }

    public ContactProfile(String id, String phone, String email, String district, String state, String country, String countryCode, String postalCode, double latitude, double longitude, boolean isVerified) {
        this.id = id;
        this.phone = phone;
        this.email = email;
        this.district = district;
        this.state = state;
        this.country = country;
        this.countryCode = countryCode;
        this.postalCode = postalCode;
        this.latitude = latitude;
        this.longitude = longitude;
        this.isVerified = isVerified;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public boolean isVerified() {
        return isVerified;
    }

    public void setVerified(boolean verified) {
        isVerified = verified;
    }
    @Override
    public boolean equals(@Nullable Object obj) {
        if(!(obj instanceof ContactProfile) || obj.getClass()!=this.getClass())
            return false;
        ContactProfile contactProfile=(ContactProfile)obj;
        return (contactProfile.id.equals(this.id));
    }
    public static void Save(ContactProfile profile, FirebaseSaveResponse responseCallback)
    {
        try{
            String uid= FirebaseAuth.getInstance().getCurrentUser().getUid();
            DatabaseReference reference= FirebaseDatabase.getInstance().getReference("contact_profiles/"+uid);
            reference.setValue(profile).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    if(responseCallback!=null)responseCallback.onSaveSuccess();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    if(responseCallback!=null) responseCallback.onSaveFailure(e.getMessage());
                }
            });
        }catch (Exception e)
        {
            responseCallback.onSaveError(e.getMessage());
        }

    }

}
