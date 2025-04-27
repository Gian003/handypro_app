package com.ucucite.handypro_app;

import static com.ucucite.handypro_app.HandyProDatabaseExecutor.databaseWriteExecutor;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.Arrays;
import java.util.List;

@Database(entities = {ServiceEntity.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract ServiceDAO serviceDAO();

    private static volatile AppDatabase INSTANCE;

    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                                    context.getApplicationContext(),
                                    AppDatabase.class,
                                    "service_database"
                            )
                            .addCallback(roomCallback)
                            .fallbackToDestructiveMigrationOnDowngrade(false)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static final RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            databaseWriteExecutor.execute(() -> {
                ServiceDAO serviceDAO = INSTANCE.serviceDAO();

                List<ServiceEntity> dummyList = Arrays.asList(
                        new ServiceEntity(R.drawable.worker_housekeeping, "Housekeeping", "Jack Hinshelwood", 4.5, 120, 50.0, true),
                        new ServiceEntity(R.drawable.worker_electrician, "Electrician", "Carlos Baleba", 4.8, 200, 75.0, false),
                        new ServiceEntity(R.drawable.worker_moving__and_packing, "Moving & Packing", "Kylian Mbappe", 4.7, 150, 60.0, true),
                        new ServiceEntity(R.drawable.worker_plumbing, "Plumbing", "Matt O'Riley", 4.9, 300, 80.0, false)
                );

                serviceDAO.insertAll(dummyList); // Insert all at once instead of multiple insert() calls
            });
        }
    };
}
