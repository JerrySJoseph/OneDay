package com.example.oneday.Utils;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.oneday.DAO.NotificationDao;
import com.example.oneday.Models.NotificationModel;


@Database(entities = {NotificationModel.class}, version = 1)
public abstract class NotificationDatabase extends RoomDatabase {
    public abstract NotificationDao taskDao();
}

