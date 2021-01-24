package com.example.oneday.Utils;

import android.content.Context;
import android.os.AsyncTask;

import androidx.room.Room;


import com.example.oneday.Models.NotificationModel;

import java.util.ArrayList;

public class NotificationDatabaseHelper {

    private Context mCtx;
    private static NotificationDatabaseHelper mInstance;

    //our app database object
    private NotificationDatabase appDatabase;

    private NotificationDatabaseHelper(Context mCtx) {
        this.mCtx = mCtx;
        appDatabase = Room.databaseBuilder(mCtx,NotificationDatabase.class, "MyNotificationDB").build();
    }

    public static synchronized NotificationDatabaseHelper getInstance(Context mCtx) {
        if (mInstance == null) {
            mInstance = new NotificationDatabaseHelper(mCtx);
        }
        return mInstance;
    }

    public NotificationDatabase getNotificationDatabase() {
        return appDatabase;
    }
    public void Save(final NotificationModel model)
    {
        new AsyncTask<Void, Void, Void>(){
            @Override
            protected Void doInBackground(Void... voids) {
                getNotificationDatabase().taskDao().insert(model);
                return null;
            }
        }.execute();

    }
    public void Delete(final NotificationModel model)
    {
        new AsyncTask<Void, Void, Void>(){
        @Override
        protected Void doInBackground(Void... voids) {
            getNotificationDatabase().taskDao().delete(model);
            return null;
        }
    }.execute();

    }
    public void Delete(final NotificationModel model, final ProcessCompletedListener listener)
    {
        new AsyncTask<Void, Void, Void>(){
            @Override
            protected Void doInBackground(Void... voids) {
                try {
                    getNotificationDatabase().taskDao().delete(model);
                }catch (Exception e)
                {
                    if(listener!=null)
                    listener.OnFailure(e.getMessage());
                }

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                if(listener!=null)
                    listener.OnSucess();
                super.onPostExecute(aVoid);
            }

            @Override
            protected void onCancelled() {
                if(listener!=null)
                    listener.OnFailure("delete process cancelled");
                super.onCancelled();
            }
        }.execute();

    }
    public void Update(final NotificationModel model)
    {
        new AsyncTask<Void, Void, Void>(){
            @Override
            protected Void doInBackground(Void... voids) {
                getNotificationDatabase().taskDao().update(model);
                return null;
            }
        }.execute();

    }
    public void getAllNotifications(final NotificationsListener listener)
    {
        new AsyncTask<Void, Void, Void>(){
            ArrayList<NotificationModel> models=new ArrayList<>();
            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                if(listener!=null)
                    listener.onNotificationsFetched(models);
            }

            @Override
            protected Void doInBackground(Void... voids) {
                models.clear();
                models.addAll(getNotificationDatabase().taskDao().getNotifications());
                return null;
            }
        }.execute();
    }
    public void clearAll()
    {
        new AsyncTask<Void, Void, Void>(){
            @Override
            protected Void doInBackground(Void... voids) {
                getNotificationDatabase().taskDao().clearAll();
                return null;
            }
        }.execute();

    }
    public interface NotificationsListener{
        void onNotificationsFetched(ArrayList<NotificationModel> models);
    }
    public interface ProcessCompletedListener{
        void OnSucess();
        void OnFailure(String s);
    }
}
