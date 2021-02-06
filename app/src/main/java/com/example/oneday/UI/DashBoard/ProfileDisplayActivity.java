package com.example.oneday.UI.DashBoard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.example.oneday.R;
import com.example.oneday.Utils.Animations;
import com.example.oneday.Utils.AppBarStateChangeListener;
import com.example.oneday.Views.ImageSliderView;
import com.google.android.material.appbar.AppBarLayout;

import java.util.ArrayList;

public class ProfileDisplayActivity extends AppCompatActivity {
    Toolbar toolbar;
    ImageSliderView imageSliderView;
    AppBarLayout appBarLayout;
    LinearLayout controlsLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_display);
        toolbar = findViewById(R.id.toolbar);
        imageSliderView=findViewById(R.id.imageslider);
        imageSliderView.enableSlideShow(true);
        appBarLayout=findViewById(R.id.appbar);
        appBarLayout.addOnOffsetChangedListener(onAppbarStateChanged);
        toolbar.setTitle("Tatiana sara");
        controlsLayout=findViewById(R.id.controls_layout);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        ArrayList<String> imageURLs= new ArrayList<>();
        imageURLs.add("https://upload.wikimedia.org/wikipedia/commons/thumb/a/ae/Alia_Bhatt_graces_GQ_Style_Awards_%2804%29_%28cropped%29.jpg/170px-Alia_Bhatt_graces_GQ_Style_Awards_%2804%29_%28cropped%29.jpg");
        imageURLs.add("https://upload.wikimedia.org/wikipedia/commons/thumb/3/3a/Alia_Bhatt_promoting_Kalank.jpg/220px-Alia_Bhatt_promoting_Kalank.jpg");
        imageURLs.add("https://i.pinimg.com/originals/cb/76/03/cb7603a9215b5be6f59fd4523bd66e1f.jpg");
        imageURLs.add("https://fsb.zobj.net/crop.php?r=kgE8DK4avg177fvGLG3VXBfRaKnO1dj8Kjqxyh9fhzl0nVSYCuA7cNPPyyhG3paQBL6KY2ZTtZDf5iNRYESLvEwihClk4O43VwNbCm27a_hfdwxfA13-FGG-bWQkaSxHJeEyIvI5keuJRFSb");
        imageURLs.add("https://www.tellychakkar.com/sites/www.tellychakkar.com/files/styles/display_665x429/public/images/movie_image/2020/07/15/Bhat.jpg?itok=ftjJuXFD");
        imageURLs.add("https://upload.wikimedia.org/wikipedia/commons/thumb/a/ae/Alia_Bhatt_graces_GQ_Style_Awards_%2804%29_%28cropped%29.jpg/170px-Alia_Bhatt_graces_GQ_Style_Awards_%2804%29_%28cropped%29.jpg");
        imageURLs.add("https://upload.wikimedia.org/wikipedia/commons/thumb/3/3a/Alia_Bhatt_promoting_Kalank.jpg/220px-Alia_Bhatt_promoting_Kalank.jpg");
        imageURLs.add("https://i.pinimg.com/originals/cb/76/03/cb7603a9215b5be6f59fd4523bd66e1f.jpg");
        imageURLs.add("https://fsb.zobj.net/crop.php?r=kgE8DK4avg177fvGLG3VXBfRaKnO1dj8Kjqxyh9fhzl0nVSYCuA7cNPPyyhG3paQBL6KY2ZTtZDf5iNRYESLvEwihClk4O43VwNbCm27a_hfdwxfA13-FGG-bWQkaSxHJeEyIvI5keuJRFSb");
        imageURLs.add("https://www.tellychakkar.com/sites/www.tellychakkar.com/files/styles/display_665x429/public/images/movie_image/2020/07/15/Bhat.jpg?itok=ftjJuXFD");
        imageSliderView.Init(imageURLs);

    }
    private AppBarStateChangeListener onAppbarStateChanged= new AppBarStateChangeListener() {
        @Override
        public void onStateChanged(AppBarLayout appBarLayout, State state) {
            Log.e("STATE",state.name());

            if(state==State.COLLAPSED)
            {
                Animations.fadeIn(findViewById(R.id.controls_layout));
            }
            if(state==State.EXPANDED)
            {
                Animations.fadeOut(findViewById(R.id.controls_layout));
            }
        }
    };


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
}