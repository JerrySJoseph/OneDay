package com.example.oneday.Views;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.example.oneday.R;
import com.example.oneday.Utils.Animations;

public class BottomNavigation extends LinearLayout {

    public static enum Button{
        NOTIFICATION,
        LIKES,
        HOME,
        REQUESTS,
        PROFILE
    }


    int[] menu_ids={R.id.notif,R.id.likes,R.id.home,R.id.requests,R.id.profile};
    int[] icon_ids={R.id.ic_notif,R.id.ic_likes,R.id.ic_home,R.id.ic_requests,R.id.ic_profile};
    int[] tv_ids={R.id.tv_notif,R.id.tv_likes,R.id.tv_requests,R.id.tv_profile};

    onBottomNavigationClickListener onBottomNavigationClickListener;

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
        for(int id:menu_ids)
        {
            findViewById(id).setOnClickListener(onMenuButtonClicklistener);
        }
    }
    public void setBadge(Button button,boolean pulsating)
    {
        int id=R.id.tv_notif;
        switch (button)
        {
            case NOTIFICATION:id=R.id.tv_notif;break;
            case LIKES:id=R.id.tv_likes;break;
            case REQUESTS:id=R.id.tv_requests;break;
            case PROFILE:id=R.id.tv_profile;break;
        }
        clearAllBadge();
        TextView badge=findViewById(id);
        badge.setVisibility(VISIBLE);
        if(pulsating)
            Animations.pulse(badge);
    }
    public void clearAllBadge()
    {
        for(int id:tv_ids)
        {
            findViewById(id).setVisibility(INVISIBLE);
        }
    }
    public void setOnBottomNavigationClickListener(BottomNavigation.onBottomNavigationClickListener onBottomNavigationClickListener) {
        this.onBottomNavigationClickListener = onBottomNavigationClickListener;
    }

    private int getIndex(View view)
    {
        switch (view.getId())
        {
            case R.id.notif:return 0;
            case R.id.likes:return 1;
            case R.id.home:return 2;
            case R.id.requests:return 3;
            case R.id.profile:return 4;
            default:return 2;
        }
    }
    private void changeMenuSelection(int index)
    {
        LinearLayout menu_item= findViewById(menu_ids[index]);
        menu_item.setBackground(this.getResources().getDrawable(R.drawable.bg_button_primary));
        ImageView icon=findViewById(icon_ids[index]);
        icon.setColorFilter(this.getResources().getColor(R.color.white));
    }
    private void clearAllSelection()
    {
        for(int i=0;i<menu_ids.length;i++)
        {
            LinearLayout menu_item= findViewById(menu_ids[i]);
            menu_item.setBackground(null);
            ImageView icon=findViewById(icon_ids[i]);
            icon.setColorFilter(this.getResources().getColor(R.color.black_overlay));
        }
    }

    private View.OnClickListener onMenuButtonClicklistener= new View.OnClickListener(){

        @Override
        public void onClick(View view) {
            int index=getIndex(view);
          //  clearAllSelection();
          //  changeMenuSelection(index);
            if(onBottomNavigationClickListener!=null)
                switch (index)
                {
                    case 0:onBottomNavigationClickListener.onNotificationClick();break;
                    case 1:onBottomNavigationClickListener.onLikesClick();break;
                    case 2:onBottomNavigationClickListener.onHomeClick();break;
                    case 3:onBottomNavigationClickListener.onRequestsClick();break;
                    case 4:onBottomNavigationClickListener.onProfileClick();break;
                }

        }
    };

    public interface onBottomNavigationClickListener{
        void onNotificationClick();
        void onLikesClick();
        void onHomeClick();
        void onRequestsClick();
        void onProfileClick();
    }
}
