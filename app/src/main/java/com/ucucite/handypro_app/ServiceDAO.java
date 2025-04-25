package com.ucucite.handypro_app;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ServiceDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(ServiceEntity e);

    @Query("SELECT * FROM services ORDER BY id DESC")
    LiveData<List<ServiceEntity>> getAllServices();

    //Sorting
    @Query("SELECT * FROM services WHERE service = :categoryLabel") LiveData<List<ServiceEntity>> getSortedCategory(String categoryLabel);
    @Query("SELECT * FROM services ORDER BY rating ASC") LiveData<List<ServiceEntity>> getSortedByRating();
    @Query("SELECT * FROM services ORDER BY rating DESC") LiveData<List<ServiceEntity>> getSortedByRatingDesc();
    @Query("SELECT * FROM services ORDER BY reviews ASC") LiveData<List<ServiceEntity>> getSortedByReviews();
    @Query("SELECT * FROM services ORDER BY reviews DESC") LiveData<List<ServiceEntity>> getSortedByReviewsDesc();
    @Query(("SELECT * FROM services ORDER BY price ASC")) LiveData<List<ServiceEntity>> getSortedByPrice();
    @Query("SELECT * FROM services ORDER BY price DESC") LiveData<List<ServiceEntity>> getSortedByPriceDesc();
}
