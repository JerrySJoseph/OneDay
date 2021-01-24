package com.example.oneday.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.oneday.Models.DisplayProfile;
import com.example.oneday.R;

import java.util.ArrayList;

public class ProfileDisplayAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private static int VIEWTYPE_AD=1;
    private static int VIEWTYPE_PROFILE=2;
    private int interval=5;
    private boolean enableAds=true;
    ArrayList<DisplayProfile> models;
    onProfileClickListener onProfileClickListener;
    public ProfileDisplayAdapter(ArrayList<DisplayProfile> models) {
        this.models = models;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public int getInterval() {
        return interval;
    }

    public void setEnableAds(boolean enableAds) {
        this.enableAds = enableAds;
    }

    public void setOnProfileClickListener(ProfileDisplayAdapter.onProfileClickListener onProfileClickListener) {
        this.onProfileClickListener = onProfileClickListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType==VIEWTYPE_AD)
            return new ADHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_display_ad,parent,false));
        return new ProfileDisplayHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_display_profile,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ProfileDisplayHolder)holder).Bind(models.get(position));
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    @Override
    public int getItemViewType(int position) {
        return (position%interval==0 && enableAds && position!=0 && interval!=0)?VIEWTYPE_AD:VIEWTYPE_PROFILE;
    }

    protected class ProfileDisplayHolder extends RecyclerView.ViewHolder {
        CardView layout;
        public ProfileDisplayHolder(@NonNull View itemView) {
            super(itemView);
            layout=itemView.findViewById(R.id.layout);
        }
        public void Bind(final DisplayProfile profile)
        {
            layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(onProfileClickListener!=null)
                        onProfileClickListener.onProfileClick(profile);
                }
            });
        }
    }
    protected class ADHolder extends RecyclerView.ViewHolder {
        CardView layout;
        public ADHolder(@NonNull View itemView) {
            super(itemView);
            layout=itemView.findViewById(R.id.layout);
        }
    }
    public interface onProfileClickListener{
        void onProfileClick(DisplayProfile profile);
    }
}
