package com.ucucite.handypro_app;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

public class ViewModel extends AndroidViewModel {
    private ServiceRepository serviceRepository;

    public ViewModel(@NonNull Application application) {
        super(application);
        serviceRepository = new ServiceRepository(application);
    }

    public void insertDummyData(List<ServiceEntity> services) {
        serviceRepository.insertDummyData(services);
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

    public LiveData<List<ServiceEntity>> getSortedByReviewsDesc() {
        return serviceRepository.getSortedByReviewsDesc();
    }

    public LiveData<List<ServiceEntity>> getSortedByPrice() {
        return serviceRepository.getSortedByPrice();
    }

    public LiveData<List<ServiceEntity>> getSortedByPriceDesc() {
        return serviceRepository.getSortedByPriceDesc();
    }
}
