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

@Database(entities = {ServiceEntity.class, CategoryEntity.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract ServiceDAO serviceDAO();
    public abstract CategoryDAO categoryDAO();

    private static volatile AppDatabase INSTANCE;

    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                                    context.getApplicationContext(),
                                    AppDatabase.class,
                                    "handypro_database"
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
                if (INSTANCE != null) { // Defensive check
                    ServiceDAO serviceDAO = INSTANCE.serviceDAO();
                    CategoryDAO categoryDAO = INSTANCE.categoryDAO();

                    categoryDAO.insertAll(getDummyCategories());
                    serviceDAO.insertAll(getDummyServices());
                }
            });
        }
    };

    static List<ServiceEntity> getDummyServices() {
        return Arrays.asList(
                new ServiceEntity(R.drawable.worker_housekeeping, "Housekeeping", "Jack Hinshelwood", 4.5, 120, 50.0, true),
                new ServiceEntity(R.drawable.worker_electrician, "Electrician", "Carlos Baleba", 4.8, 200, 75.0, false),
                new ServiceEntity(R.drawable.worker_moving__and_packing, "Moving & Packing", "Kaoru Mitoma", 4.7, 150, 60.0, true),
                new ServiceEntity(R.drawable.worker_plumbing, "Plumbing", "Matt O'Riley", 4.9, 300, 80.0, false)
        );
    }

    static List<CategoryEntity> getDummyCategories() {
        return Arrays.asList(
                new CategoryEntity(R.drawable.home_services_ic_housekeeping, "Housekeeping"),
                new CategoryEntity(R.drawable.home_services_ic_plumbing, "Plumbing"),
                new CategoryEntity(R.drawable.home_services_ic_electrician, "Electrician")
        );
    }
}

