package com.example.oneday.Views;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;

import com.example.oneday.Models.Gender;
import com.example.oneday.R;

public class MultiSelectGender extends LinearLayout implements View.OnClickListener {

    LinearLayout male,female,other;
    TextView tv_male,tv_female,tv_other;
    Gender selectedGender=Gender.MALE;
    public MultiSelectGender(Context context) {
        super(context);
        Init(null);
    }

    public MultiSelectGender(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        Init(attrs);
    }

    public MultiSelectGender(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Init(attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public MultiSelectGender(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        Init(attrs);
    }
    private void Init(AttributeSet attrs)
    {
        inflate(this.getContext(), R.layout.view_multiselectbutton,this);
        male=findViewById(R.id.male);
        female=findViewById(R.id.female);
        other=findViewById(R.id.others);
        tv_female=findViewById(R.id.tv_female);
        tv_male=findViewById(R.id.tv_male);
        tv_other=findViewById(R.id.tv_others);
        male.setOnClickListener(this::onClick);
        female.setOnClickListener(this::onClick);
        other.setOnClickListener(this::onClick);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.male:onMaleClick();break;
            case R.id.female:onFemaleClick();break;
            case R.id.others:onOthersClick();break;
        }
    }

    private void onOthersClick() {
        clearSelection();
        selectedGender=Gender.OTHER;
        other.setBackground(this.getContext().getResources().getDrawable(R.drawable.bg_button_primary));
        tv_other.setTextColor(this.getContext().getResources().getColor(android.R.color.white));
        setTextViewDrawableColor(tv_other,android.R.color.white);

    }
    private void setTextViewDrawableColor(TextView textView, int color) {
        for (Drawable drawable : textView.getCompoundDrawables()) {
            if (drawable != null) {
                drawable.setColorFilter(new PorterDuffColorFilter(ContextCompat.getColor(textView.getContext(), color), PorterDuff.Mode.SRC_IN));
            }
        }
    }
    private void onFemaleClick() {
        clearSelection();
        selectedGender=Gender.FEMALE;
        female.setBackground(this.getContext().getResources().getDrawable(R.drawable.bg_button_primary));
        tv_female.setTextColor(this.getContext().getResources().getColor(android.R.color.white));
        setTextViewDrawableColor(tv_female,android.R.color.white);
    }

    private void onMaleClick() {
        clearSelection();
        selectedGender=Gender.MALE;
        male.setBackground(this.getContext().getResources().getDrawable(R.drawable.bg_button_primary));
        tv_male.setTextColor(this.getContext().getResources().getColor(android.R.color.white));
        setTextViewDrawableColor(tv_male,android.R.color.white);
    }
    private void clearSelection()
    {
        male.setBackground(this.getContext().getResources().getDrawable(R.drawable.bg_rounded_stroke_primary));
        tv_male.setTextColor(this.getContext().getResources().getColor(R.color.colorPrimary));
        setTextViewDrawableColor(tv_male,R.color.colorPrimary);
        female.setBackground(this.getContext().getResources().getDrawable(R.drawable.bg_rounded_stroke_primary));
        tv_female.setTextColor(this.getContext().getResources().getColor(R.color.colorPrimary));
        setTextViewDrawableColor(tv_female,R.color.colorPrimary);
        other.setBackground(this.getContext().getResources().getDrawable(R.drawable.bg_rounded_stroke_primary));
        tv_other.setTextColor(this.getContext().getResources().getColor(R.color.colorPrimary));
        setTextViewDrawableColor(tv_other,R.color.colorPrimary);
    }
    public Gender getSelectedGender()
    {
        return selectedGender;
    }

}
