package com.example.oneday.Utils;

import android.content.Context;

import com.example.oneday.R;

public class Utils {

    public static String getPhoneCode(Context ctx, String countryCode)
    {
        String[] codes=ctx.getResources().getStringArray(R.array.CountryCodes);
        for(String item:codes)
        {
            String[] array_items=item.split(",");
            if(countryCode.equals(array_items[1]))
            {
                return "+"+array_items[0];
            }
        }
        return null;
    }
    public static int getCountryFlagResource(Context context,String countryName)
    {
        countryName=countryName.toLowerCase().replace(" ","");
        return  context.getResources().getIdentifier("drawable/"+countryName, null, context.getPackageName());
    }
}
