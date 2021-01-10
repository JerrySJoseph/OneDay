package com.example.oneday.Views;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.example.oneday.R;

public class BottomNavigation extends LinearLayout {
    ImageView iml;
    public BottomNavigation(Context context) {
        super(context);
        Init(context,null);
    }

    public BottomNavigation(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        Init(context,attrs);
    }

    public BottomNavigation(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Init(context,attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public BottomNavigation(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        Init(context,attrs);
    }
    private void Init(Context context,AttributeSet attrs)
    {
        inflate(context, R.layout.view_bottom_navigation,this);
    }
}
