package com.example.oneday.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.oneday.Models.Profile;
import com.example.oneday.R;

import java.util.ArrayList;

public class ProfileDisplayAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private static final int VIEWTYPE_AD=1;
    private static final int VIEWTYPE_PROFILE=2;
    private int interval=5;
    private boolean enableAds=true;
    ArrayList<Profile> models;
    onProfileClickListener onProfileClickListener;
    public ProfileDisplayAdapter(ArrayList<Profile> models) {
        this.models = models;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public int getInterval() {
        return interval;
    }

    public void enableAds(boolean enableAds) {
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
        switch (holder.getItemViewType())
        {
            case VIEWTYPE_PROFILE:((ProfileDisplayHolder)holder).Bind(models.get(position));break;
            case VIEWTYPE_AD:((ADHolder)holder).Bind(models.get(position));break;

        }

    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    @Override
    public int getItemViewType(int position) {
        return (position%interval==0 && enableAds && position!=0 && interval!=0)?VIEWTYPE_AD:VIEWTYPE_PROFILE;
    }

    //Profile Display Holder
    protected class ProfileDisplayHolder extends RecyclerView.ViewHolder  {
        CardView layout;
        ImageView like,dislike,superlike;
        public ProfileDisplayHolder(@NonNull View itemView) {
            super(itemView);
            layout=itemView.findViewById(R.id.layout);
            like=itemView.findViewById(R.id.like);
            dislike=itemView.findViewById(R.id.dislike);
            superlike=itemView.findViewById(R.id.superlike);

        }
        public void Bind(final Profile profile)
        {
            like.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(onProfileClickListener!=null)
                        onProfileClickListener.onLikeClick(profile);
                }
            });
            dislike.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(onProfileClickListener!=null)
                        onProfileClickListener.onDislikeClick(profile);
                }
            });
            superlike.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(onProfileClickListener!=null)
                        onProfileClickListener.onSuperLikeClick(profile);
                }
            });
            layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(onProfileClickListener!=null)
                    {
                        onProfileClickListener.onProfileClick(profile);
                    }
                }
            });
        }
    }

    //Holder for ADs
    protected class ADHolder extends RecyclerView.ViewHolder {
        CardView layout;

        public ADHolder(@NonNull View itemView) {
            super(itemView);
            layout=itemView.findViewById(R.id.layout);
        }
        public void Bind(final Profile profile)
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
    public interface onProfileClickListener{
        void onProfileClick(Profile profile);
        void onLikeClick(Profile profile);
        void onSuperLikeClick(Profile profile);
        void onDislikeClick(Profile profile);
    }
}
