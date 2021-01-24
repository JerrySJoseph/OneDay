package com.example.oneday.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;


public class PrefManager {

    static SharedPreferences preferences;
    static SharedPreferences.Editor editor;

    private static String PREF_NAME="oneday-shared-prefs";
    private static String PROFILE_SETUP_COMPLETE="profile_setup_complete";
    private static String AUTH_METHOD_SELECTED="auth-method-selected";

    public static void setProfileSetupComplete(Context context,boolean isComplete)
    {
        preferences=context.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
        editor=preferences.edit();
        editor.putBoolean(PROFILE_SETUP_COMPLETE,isComplete);
        editor.commit();
    }
    public static boolean isProfileSetupComplete(Context context)
    {
        preferences=context.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
        return preferences.getBoolean(PROFILE_SETUP_COMPLETE,false);
    }
    public static void setAuthMethodSelected(Context context,String authMethod)
    {
        preferences=context.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
        editor=preferences.edit();
        editor.putString(AUTH_METHOD_SELECTED,authMethod);
        editor.commit();
    }
    public static String getAuthMethodSelected(Context context)
    {
        preferences=context.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
        return preferences.getString(AUTH_METHOD_SELECTED,"NONE");
    }
    public static  int getNotificationCount(Context context)
    {
        preferences=context.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
        return preferences.getInt("notification-count",0);
    }
    public static void setNotificationCount(Context context,int count)
    {
        preferences=context.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
        editor=preferences.edit();
        editor.putInt("notification-count",count);
        editor.commit();
    }

}
