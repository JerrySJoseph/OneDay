package com.example.oneday.Models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class NotificationModel implements Serializable {

    public static enum NotificationType{
        NORMAL,
        LINKED,
        LINKED_WITH_BUTTON
    }


    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "body")
    String body;
    @ColumnInfo(name = "timestamp")
    long timestamp;

    public int getId() {
        return id;
    }

    public NotificationModel() {
    }
    public NotificationModel(String body, long timestamp) {

        this.body = body;
        this.timestamp = timestamp;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
