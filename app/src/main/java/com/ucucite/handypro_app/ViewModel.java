package com.ucucite.handypro_app;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.Arrays;
import java.util.List;

public class ViewModel extends AndroidViewModel {
    private final ServiceRepository serviceRepository;
    private final LiveData<List<ServiceEntity>> allServices;

    public ViewModel(@NonNull Application application) {
        super(application);
        serviceRepository = new ServiceRepository(application);
        allServices = serviceRepository.getAllServices();
        checkAndInsertDummyData();
    }

    private void checkAndInsertDummyData() {
        new Thread(() -> {
            List<ServiceEntity> currentServices = allServices.getValue();
            if (currentServices == null || currentServices.isEmpty()) {
                List<ServiceEntity> dummyList = Arrays.asList(
                        new ServiceEntity(R.drawable.worker_housekeeping, "Housekeeping", "Jack Hinshelwood", 4.5, 120, 50.0, true),
                        new ServiceEntity(R.drawable.worker_electrician, "Electrician", "Carlos Baleba", 4.8, 200, 75.0, false),
                        new ServiceEntity(R.drawable.worker_moving__and_packing, "Moving & Packing", "Kylian Mbappe", 4.7, 150, 60.0, true),
                        new ServiceEntity(R.drawable.worker_plumbing, "Plumbing", "Matt O'Riley", 4.9, 300, 80.0, false)
                );
                serviceRepository.insertDummyData(dummyList);
            }
        }).start();
    }


    public void insertService(List<ServiceEntity> services) {
        serviceRepository.insertService(services);
    }

    public LiveData<List<ServiceEntity>> getAllServices() {
        return serviceRepository.getAllServices();
    }

    public LiveData<List<ServiceEntity>> getSortedCategory(String categoryLabel) {
        return serviceRepository.getSortedCategory(categoryLabel);
    }

    public LiveData<List<ServiceEntity>> getSortedByRating() {
        return serviceRepository.getSortedByRating();
    }

    public LiveData<List<ServiceEntity>> getSortedByRatingDesc() {
        return serviceRepository.getSortedByRatingDesc();
    }

    public LiveData<List<ServiceEntity>> getSortedByReviews() {
        return serviceRepository.getSortedByReviews();
    }

//    public LiveData<List<ServiceEntity>> getSortedByReviewsDesc() {
//        return serviceRepository.getSortedByReviewsDesc();
//    }

    public LiveData<List<ServiceEntity>> getSortedByPrice() {
        return serviceRepository.getSortedByPrice();
    }

    public LiveData<List<ServiceEntity>> getSortedByPriceDesc() {
        return serviceRepository.getSortedByPriceDesc();
    }
}
