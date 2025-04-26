package com.ucucite.handypro_app;

import android.content.Context;

import androidx.lifecycle.LiveData;
import java.util.List;
import java.util.concurrent.Executors;

public class ServiceRepository {
    private final ServiceDAO serviceDAO;

    public ServiceRepository(Context context) {
        AppDatabase db = AppDatabase.getInstance(context);
        this.serviceDAO = db.serviceDAO();
    }

    public void insertDummyData(List<ServiceEntity> services) {
        HandyProDatabaseExecutor.databaseWriteExecutor.execute(() -> {
            serviceDAO.insertAll(services);
        });
    }

    public void insertService(List<ServiceEntity> services) {
        HandyProDatabaseExecutor.databaseWriteExecutor.execute(() -> {
            serviceDAO.insertAll(services);
        });
    }

    public LiveData<List<ServiceEntity>> getAllServices() {
        return serviceDAO.getAllServices();
    }

    public LiveData<List<ServiceEntity>> getSortedCategory(String categoryLabel) {
        return serviceDAO.getSortedCategory(categoryLabel);
    }

    public LiveData<List<ServiceEntity>> getSortedByRating() {
        return serviceDAO.getSortedByRating();
    }

    public LiveData<List<ServiceEntity>> getSortedByRatingDesc() {
        return serviceDAO.getSortedByRatingDesc();
    }

    public LiveData<List<ServiceEntity>> getSortedByReviews() {
        return serviceDAO.getSortedByReviews();
    }

    public LiveData<List<ServiceEntity>> getSortedByReviewsDesc() {
        return serviceDAO.getSortedByReviewsDesc();
    }

    public LiveData<List<ServiceEntity>> getSortedByPrice() {
        return serviceDAO.getSortedByPrice();
    }

    public LiveData<List<ServiceEntity>> getSortedByPriceDesc() {
        return serviceDAO.getSortedByPriceDesc();
    }
}
