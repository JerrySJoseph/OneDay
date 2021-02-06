package com.example.oneday.Models;

public class MessageModel {
    public static enum MessageStatus{
        SENDING,
        SENT,
        DELIVERED,
        SEEN,
        ERROR
    }
    String content;
    long timestamp;
    MessageStatus status;

    public MessageModel() {
    }

    public MessageModel(String content, long timestamp, MessageStatus status) {
        this.content = content;
        this.timestamp = timestamp;
        this.status = status;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public MessageStatus getStatus() {
        return status;
    }

    public void setStatus(MessageStatus status) {
        this.status = status;
    }
}
