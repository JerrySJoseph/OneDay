package com.example.oneday.Models;

import org.json.JSONObject;

import java.util.ArrayList;

public class DisplayProfile {
    //Properties
    String _id,name, bio, jobTitle,company,school,nickName,displayPicture,district, state,country;
    Gender gender,interestedIn;
    boolean verified;
    long dob;
    ArrayList<String> portfolio,interests;

    public DisplayProfile() {
    }

    public DisplayProfile(String _id, String name, String bio, String jobTitle, String company, String school, String nickName, String displayPicture, String district, String state, String country, Gender gender, Gender interestedIn, boolean verified, long dob, ArrayList<String> portfolio, ArrayList<String> interests) {
        this._id = _id;
        this.name = name;
        this.bio = bio;
        this.jobTitle = jobTitle;
        this.company = company;
        this.school = school;
        this.nickName = nickName;
        this.displayPicture = displayPicture;
        this.district = district;
        this.state = state;
        this.country = country;
        this.gender = gender;
        this.interestedIn = interestedIn;
        this.verified = verified;
        this.dob = dob;
        this.portfolio = portfolio;
        this.interests = interests;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
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

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getDisplayPicture() {
        return displayPicture;
    }

    public void setDisplayPicture(String displayPicture) {
        this.displayPicture = displayPicture;
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

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Gender getInterestedIn() {
        return interestedIn;
    }

    public void setInterestedIn(Gender interestedIn) {
        this.interestedIn = interestedIn;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public long getDob() {
        return dob;
    }

    public void setDob(long dob) {
        this.dob = dob;
    }

    public ArrayList<String> getPortfolio() {
        return portfolio;
    }

    public void setPortfolio(ArrayList<String> portfolio) {
        this.portfolio = portfolio;
    }

    public ArrayList<String> getInterests() {
        return interests;
    }

    public void setInterests(ArrayList<String> interests) {
        this.interests = interests;
    }
    public static DisplayProfile fromJSON(JSONObject object)
    {
        return new DisplayProfile();
    }

}
