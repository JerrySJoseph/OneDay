package com.example.oneday.Models;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.oneday.interfaces.FirebaseSaveResponse;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Date;

public class DisplayProfile {

    //Properties
    String id,name,bio,jobTitle,company,school,nickname,displayPicture,interests;
    ArrayList<String> portfolio;
    long DOB;
    Gender gender,interestedIn;
    boolean isVerified=false;

    public DisplayProfile() {
    }



    public DisplayProfile(String id, String name, String bio, String jobTitle, String company, String school, String instagram, String nickname, long DOB, Gender gender, Gender interest, boolean isVerified) {
        this.id = id;
        this.name = name;
        this.bio = bio;
        this.jobTitle = jobTitle;
        this.company = company;
        this.school = school;
        this.nickname = nickname;
        this.DOB = DOB;
        this.gender = gender;
        this.interestedIn = interest;
        this.isVerified = isVerified;
    }

    public DisplayProfile(String id, String name, String bio, String jobTitle, String company, String school, String nickname, long DOB, Gender gender, Gender interest) {
        this.id = id;
        this.name = name;
        this.bio = bio;
        this.jobTitle = jobTitle;
        this.company = company;
        this.school = school;
        this.nickname = nickname;
        this.DOB = DOB;
        this.gender = gender;
        this.interestedIn = interest;
    }

    public DisplayProfile(String id, String name, String bio, String jobTitle, String company, String school, String nickname, String displayPicture, ArrayList<String> portfolio, long DOB, Gender gender, Gender interest, boolean isVerified) {
        this.id = id;
        this.name = name;
        this.bio = bio;
        this.jobTitle = jobTitle;
        this.company = company;
        this.school = school;
        this.nickname = nickname;
        this.displayPicture = displayPicture;
        this.portfolio = portfolio;
        this.DOB = DOB;
        this.gender = gender;
        this.interestedIn = interest;
        this.isVerified = isVerified;
    }
    public String getInterests() {
        return interests;
    }

    public void setInterests(String interests) {
        this.interests = interests;
    }

    public Gender getInterestedIn() {
        return interestedIn;
    }

    public void setInterestedIn(Gender interestedIn) {
        this.interestedIn = interestedIn;
    }
    public String getDisplayPicture() {
        return displayPicture;
    }

    public void setDisplayPicture(String displayPicture) {
        this.displayPicture = displayPicture;
    }

    public ArrayList<String> getPortfolio() {
        return portfolio;
    }

    public void setPortfolio(ArrayList<String> portfolio) {
        this.portfolio = portfolio;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public long getDOB() {
        return DOB;
    }

    public void setDOB(long DOB) {
        this.DOB = DOB;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public boolean isVerified() {
        return isVerified;
    }

    public void setVerified(boolean verified) {
        isVerified = verified;
    }
    @Override
    public boolean equals(@Nullable Object obj) {
        if(!(obj instanceof DisplayProfile) || obj.getClass()!=this.getClass())
            return false;
        DisplayProfile displayProfile=(DisplayProfile)obj;
        return (displayProfile.id.equals(this.id));
    }
    public static void Save(DisplayProfile profile, FirebaseSaveResponse responseCallback)
    {
        try{
            String uid= FirebaseAuth.getInstance().getCurrentUser().getUid();
            DatabaseReference reference= FirebaseDatabase.getInstance().getReference("display_profiles/"+uid);
            reference.setValue(profile).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    if(responseCallback!=null) responseCallback.onSaveSuccess();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    if(responseCallback!=null)responseCallback.onSaveFailure(e.getMessage());
                }
            });
        }catch (Exception e)
        {
            responseCallback.onSaveError(e.getMessage());
        }

    }
}
