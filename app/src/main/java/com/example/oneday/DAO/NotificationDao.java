package com.example.oneday.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


import com.example.oneday.Models.NotificationModel;

import java.util.List;

@Dao
public interface NotificationDao {

    @Query("SELECT * from notificationmodel")
    List<NotificationModel> getNotifications();

    @Insert
    void insert(NotificationModel model);

    @Delete
    void delete(NotificationModel model);

    @Update
    void update(NotificationModel model);

    @Query("DELETE from notificationmodel")
    void clearAll();
}
