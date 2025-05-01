package com.ucucite.handypro_app;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CategoryDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(CategoryEntity category);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<CategoryEntity> categories);

    @Query("SELECT * FROM categories ORDER BY id ASC")
    LiveData<List<CategoryEntity>> getAllCategories();

    @Query("DELETE FROM categories")
    void deleteAllCategories();
}