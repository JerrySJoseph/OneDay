package com.example.oneday.Utils;

import android.app.Application;
import android.content.Context;
import android.provider.Settings;

public class Functions {
    public static String getDeviceID(Context context)
    {
       return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }
}
