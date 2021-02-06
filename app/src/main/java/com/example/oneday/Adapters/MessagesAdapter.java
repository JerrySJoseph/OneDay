package com.example.oneday.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.oneday.Models.MessageDisplayModel;
import com.example.oneday.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;

public class MessagesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    ArrayList<MessageDisplayModel>models;
    private static final int VIEWTYPE_AD=1;
    private static final int VIEWTYPE_PROFILE=2;
    private int interval=5;
    private boolean enableAds=true;
    public MessagesAdapter(ArrayList<MessageDisplayModel> models) {
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

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType==VIEWTYPE_AD)
            return new ADHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_messages_ad,parent,false));
        return new MessageHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_messages_message,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType())
        {
            case VIEWTYPE_PROFILE:((MessageHolder)holder).Bind(models.get(position));break;
            case VIEWTYPE_AD:((ADHolder)holder).Bind(models.get(position));break;

        }

    }
    @Override
    public int getItemViewType(int position) {
        return (position%interval==0 && enableAds && position!=0 && interval!=0)?VIEWTYPE_AD:VIEWTYPE_PROFILE;
    }
    @Override
    public int getItemCount() {
        return models.size();
    }


    protected class MessageHolder extends RecyclerView.ViewHolder {
        CircleImageView icon;
        Context context;
        TextView username,lastmsg,timestamp,msgcount;
        public MessageHolder(@NonNull View itemView) {
            super(itemView);
            context=itemView.getContext();
            icon=itemView.findViewById(R.id.icon);
            username=itemView.findViewById(R.id.username);
            lastmsg=itemView.findViewById(R.id.lastmsg);
            timestamp=itemView.findViewById(R.id.timestamp);
            msgcount=itemView.findViewById(R.id.msgcount);
        }
        void Bind(MessageDisplayModel model)
        {
            Glide.with(context).load(model.getImgpath()).into(icon);
            username.setText(model.getFromName());
            lastmsg.setText(model.getLastMessage());
            timestamp.setText(new SimpleDateFormat("HH:mm").format(new Date(model.getTimestamp())));
            msgcount.setText(String.valueOf(model.getMsgCount()));
            if(!model.isRead())
            {
                lastmsg.setTextColor(context.getResources().getColor(R.color.colorPrimary));
            }
        }
    }
    protected class ADHolder extends RecyclerView.ViewHolder {
        public ADHolder(@NonNull View itemView) {
            super(itemView);
        }
        void Bind(MessageDisplayModel model)
        {

        }
    }
}
