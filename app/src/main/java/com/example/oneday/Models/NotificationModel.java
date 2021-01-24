package com.example.oneday.Models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class NotificationModel implements Serializable {
    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "title")
    String title;
    @ColumnInfo(name = "body")
    String body;
    @ColumnInfo(name = "timestamp")
    long timestamp;

    public int getId() {
        return id;
    }

    public NotificationModel() {
    }
    public NotificationModel(String title, String body, long timestamp) {
        this.title = title;
        this.body = body;
        this.timestamp = timestamp;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
