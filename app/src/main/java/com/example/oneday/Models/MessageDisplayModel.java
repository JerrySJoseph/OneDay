package com.example.oneday.Models;

public class MessageDisplayModel {
    String fromName,imgpath,lastMessage;
    int msgCount;
    long timestamp;
    boolean isRead=false;

    public MessageDisplayModel() {
    }

    public MessageDisplayModel(String fromName, String imgpath, String lastMessage, int msgCount, long timestamp, boolean isRead) {
        this.fromName = fromName;
        this.imgpath = imgpath;
        this.lastMessage = lastMessage;
        this.msgCount = msgCount;
        this.timestamp = timestamp;
        this.isRead = isRead;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public String getImgpath() {
        return imgpath;
    }

    public void setImgpath(String imgpath) {
        this.imgpath = imgpath;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    public int getMsgCount() {
        return msgCount;
    }

    public void setMsgCount(int msgCount) {
        this.msgCount = msgCount;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
