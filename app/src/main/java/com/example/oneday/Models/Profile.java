package com.example.oneday.Models;

import com.google.firebase.auth.FirebaseAuth;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

public class Profile {
    //Properties
    String _id,name, bio, jobTitle,company,school,nickName,displayPicture,notifToken,deviceId, phone, email, district, state,country,authMethod;
    Gender gender,interestedIn;
    boolean verified;
    long dob;
    double latitude, longitude;
    ArrayList<String> portfolio,interests;

    public Profile() {
    }

    public Profile(String _id, String name, String bio, String jobTitle, String company, String school, String nickName, String displayPicture, String notifToken, String deviceId, String phone, String email, String district, String state, String country, String authMethod, Gender gender, Gender interestedIn, boolean verified, long dob, double latitude, double longitude, ArrayList<String> portfolio, ArrayList<String> interests) {
        this._id = _id;
        this.name = name;
        this.bio = bio;
        this.jobTitle = jobTitle;
        this.company = company;
        this.school = school;
        this.nickName = nickName;
        this.displayPicture = displayPicture;
        this.notifToken = notifToken;
        this.deviceId = deviceId;
        this.phone = phone;
        this.email = email;
        this.district = district;
        this.state = state;
        this.country = country;
        this.authMethod = authMethod;
        this.gender = gender;
        this.interestedIn = interestedIn;
        this.verified = verified;
        this.dob = dob;
        this.latitude = latitude;
        this.longitude = longitude;
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

    public String getNotifToken() {
        return notifToken;
    }

    public void setNotifToken(String notifToken) {
        this.notifToken = notifToken;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
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

    public String getAuthMethod() {
        return authMethod;
    }

    public void setAuthMethod(String authMethod) {
        this.authMethod = authMethod;
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

    public JSONObject toJSON() throws Exception
    {
        JSONArray array= new JSONArray();
        array.put(portfolio);

        JSONObject object= new JSONObject();
        object.put("_id",_id);
        object.put("name",name);
        object.put("bio",bio);
        object.put("jobTitle",jobTitle);
        object.put("company",company);
        object.put("school",school);
        object.put("nickName",nickName);
        object.put("displayPicture",displayPicture);
        object.put("notifToken",notifToken);
        object.put("deviceId",deviceId);
        object.put("phone",phone);
        object.put("email",email);
        object.put("district",district);
        object.put("state",state);
        object.put("country",country);
        object.put("authMethod",authMethod);
        object.put("gender",gender);
        object.put("interestedIn",interestedIn);
        object.put("verified",verified);
        object.put("dob",dob);
        object.put("latitude",latitude);
        object.put("longitude",longitude);
        object.put("portfolio",new JSONArray().put(portfolio));
        object.put("interests",new JSONArray().put(interests));

        return object;

    }
    public static Profile fromJSON(JSONObject object) throws Exception
    {
       return new Profile();

    }
    public static Profile getTestObject()
    {
        ArrayList<String>arrayList= new ArrayList<>();
        arrayList.add("String1");
        arrayList.add("Strign2");
        arrayList.add("String1");
        arrayList.add("Strign2");
        arrayList.add("String1");
        arrayList.add("Strign2");
        arrayList.add("String1");
        arrayList.add("Strign2");
        arrayList.add("String1");
        arrayList.add("Strign2");
        arrayList.add("String1");
        arrayList.add("Strign2");
        arrayList.add("String1");
        arrayList.add("Strign2");
        arrayList.add("String1");
        arrayList.add("Strign2");
        arrayList.add("String1");
        arrayList.add("Strign2");
        arrayList.add("String1");
        arrayList.add("Strign2");
        arrayList.add("String1");
        arrayList.add("Strign2");
        arrayList.add("String1");
        arrayList.add("Strign2");

        return new Profile(FirebaseAuth.getInstance().getUid(),
                "TestUser",
                "Some excting bio that can mae anyone fall for me...",
                "Sr. Software Developer",
                "JS Industries",
                "school5",
                "Testy",
                "https://m.media-amazon.com/images/M/MV5BZDk1ZmU0NGYtMzQ2Yi00N2NjLTkyNWEtZWE2NTU4NTJiZGUzXkEyXkFqcGdeQXVyMTExNDQ2MTI@._V1_UY1200_CR107,0,630,1200_AL_.jpg",
                "notifToken",
                "deviceID",
                "+911111111111",
                "Some email",
                "some didtrict",
                "some state",
                "some country",
                "some Auth",
                Gender.MALE,
                Gender.FEMALE,
                true,
                697007372,
                28.77777,
                77.33389,
                arrayList,
                arrayList);
    }
}
