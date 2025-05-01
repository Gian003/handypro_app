package com.ucucite.handypro_app;

import android.content.Context;

import androidx.lifecycle.LiveData;

import java.util.List;

public class CategoryRepository {

    private final CategoryDAO categoryDAO;

    public CategoryRepository(Context context) {
        AppDatabase db = AppDatabase.getInstance(context);
        this.categoryDAO = db.categoryDAO();
    }

    public void insertCategory(List<CategoryEntity> categories) {
        HandyProDatabaseExecutor.databaseWriteExecutor.execute(() -> {
            categoryDAO.insertAll(categories);
        });
    }

    public LiveData<List<CategoryEntity>> getAllCategories() {
        return categoryDAO.getAllCategories();
    }
}
