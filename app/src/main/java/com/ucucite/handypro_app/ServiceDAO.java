package com.ucucite.handypro_app;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ServiceDAO {

    // Insert a single ServiceEntity
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(ServiceEntity service);

    // Insert multiple ServiceEntities at once
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<ServiceEntity> services);

    // Get all services ordered by latest added
    @Query("SELECT * FROM services ORDER BY id DESC")
    LiveData<List<ServiceEntity>> getAllServices();

    // Filtering by category label
    @Query("SELECT * FROM services WHERE service = :categoryLabel")
    LiveData<List<ServiceEntity>> getSortedCategory(String categoryLabel);

    // Sorting by rating
    @Query("SELECT * FROM services ORDER BY rating ASC")
    LiveData<List<ServiceEntity>> getSortedByRating();

    @Query("SELECT * FROM services ORDER BY rating DESC")
    LiveData<List<ServiceEntity>> getSortedByRatingDesc();

    // Sorting by reviews
    @Query("SELECT * FROM services ORDER BY reviews ASC")
    LiveData<List<ServiceEntity>> getSortedByReviews();

    @Query("SELECT * FROM services ORDER BY reviews DESC")
    LiveData<List<ServiceEntity>> getSortedByReviewsDesc();

    // Sorting by price
    @Query("SELECT * FROM services ORDER BY price ASC")
    LiveData<List<ServiceEntity>> getSortedByPrice();

    @Query("SELECT * FROM services ORDER BY price DESC")
    LiveData<List<ServiceEntity>> getSortedByPriceDesc();

    // Delete a single service
    @Delete
    void deleteService(ServiceEntity service);

    // Delete all services
    @Query("DELETE FROM services")
    void deleteAllServices();
}
