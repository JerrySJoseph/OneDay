package com.example.oneday.Views;

import android.animation.TimeAnimator;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.example.oneday.Adapters.ProfileDisplayAdapter;
import com.example.oneday.R;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class ImageSliderView extends RelativeLayout {

    ViewPager2 viewPager2;
    LinearLayout pageIndicatorLayout;
    int selectedIndex=0;
    ImageAdapter adapter;
    ArrayList<String>imageURLs;
    private int mInterval = 2000; // 2 seconds by default, can be changed later
    private Handler mHandler;
    boolean enableSlideShow=false;

    public ImageSliderView(Context context) {
        super(context);
    }

    public ImageSliderView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ImageSliderView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ImageSliderView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void enableSlideShow(boolean enableSlideShow) {
        this.enableSlideShow = enableSlideShow;
    }

    public void setSelectedIndex(int selectedIndex) {
        this.selectedIndex = selectedIndex;
        updateIndicator();
    }

    public void Init(ArrayList<String>imageURLs)
    {
        inflate(this.getContext(), R.layout.view_image_slider,this);
        mHandler = new Handler();
        viewPager2=findViewById(R.id.viewpager);
        pageIndicatorLayout=findViewById(R.id.page_indicator);
        adapter= new ImageAdapter(imageURLs);
        viewPager2.setAdapter(adapter);
        this.imageURLs=imageURLs;
        setSelectedIndex(3);
        if(enableSlideShow)
            startSlideShow();

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                setSelectedIndex(position);
                super.onPageSelected(position);
            }
        });


    }
    Runnable slideChanger= new Runnable() {
        @Override
        public void run() {
            try {
                if(selectedIndex==imageURLs.size()-1)
                    selectedIndex=0;
                viewPager2.setCurrentItem(selectedIndex++,true);
            } finally {
                // 100% guarantee that this always happens, even if
                // your update method throws an exception
                mHandler.postDelayed(slideChanger, mInterval);
            }

        }
    };
    void startSlideShow()
    {
        slideChanger.run();
    }
    void pauseSlideShow()
    {
        mHandler.removeCallbacks(slideChanger);
    }

    void updateIndicator()
    {
        if(imageURLs==null || imageURLs.size()<1)
            return;
        pageIndicatorLayout.removeAllViews();
        //Create Page Indicator
        for(int i=0;i<imageURLs.size();i++)
        {
            View pageindicator=new LinearLayout(this.getContext());
            LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(20,20);
            params.setMargins(20,20,20,20);
            pageindicator.setLayoutParams(params);
            Drawable bg=selectedIndex!=i?this.getContext().getResources().getDrawable(R.drawable.bg_page_indicator_normal):
                    this.getContext().getResources().getDrawable(R.drawable.bg_page_indicator_selected);
            pageindicator.setBackground(bg);
            pageIndicatorLayout.addView(pageindicator);
        }

    }


    //Adapter
    protected class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageHolder> {

        ArrayList<String> models;

        public ImageAdapter(ArrayList<String> models) {
            this.models = models;
        }
        @NonNull
        @Override
        public ImageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ImageHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_imageslider,parent,false));
        }

        @Override
        public void onBindViewHolder(@NonNull ImageHolder holder, int position) {
            holder.Bind(models.get(position));
        }

        @Override
        public int getItemCount() {
            return models.size();
        }

        protected class ImageHolder extends RecyclerView.ViewHolder{

            ImageView imageView;
            public ImageHolder(@NonNull View itemView) {
                super(itemView);
                imageView=itemView.findViewById(R.id.image);
                imageView.setOnTouchListener(new OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if(event.getAction() == MotionEvent.ACTION_DOWN){
                            pauseSlideShow();
                            return true;
                        }
                        else if(event.getAction() == MotionEvent.ACTION_UP){
                            startSlideShow();
                            return true;
                        }
                        return false;
                    }
                });
            }
            public void Bind(String Url)
            {
                Glide.with(imageView.getContext()).load(Url).into(imageView);
            }
        }
    }
}
