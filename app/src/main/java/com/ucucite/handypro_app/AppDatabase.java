package com.ucucite.handypro_app;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

public abstract class AppDatabase extends RoomDatabase {
    public abstract ServiceDAO serviceDAO();

    public static volatile AppDatabase INSTANCE;
    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        AppDatabase.class, "service_database")
                        .fallbackToDestructiveMigrationOnDowngrade(false)
                        .build();
            }
        }
        return INSTANCE;
    }
}