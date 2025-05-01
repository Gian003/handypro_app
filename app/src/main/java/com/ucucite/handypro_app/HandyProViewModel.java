package com.ucucite.handypro_app;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import java.util.Arrays;
import java.util.List;

public class HandyProViewModel extends AndroidViewModel {
    private ServiceRepository serviceRepository;
    private final LiveData<List<ServiceEntity>> allServices;

    private CategoryRepository categoryRepository;

    private final LiveData<List<CategoryEntity>> allCategories;

    public HandyProViewModel(@NonNull Application application) {
        super(application);
        serviceRepository = new ServiceRepository(application);
        allServices = serviceRepository.getAllServices();

        categoryRepository = new CategoryRepository(application);
        allCategories = categoryRepository.getAllCategories();

        checkAndInsertDummyData();
        checkAndInsertCategoryData();
    }

    private final Observer<List<ServiceEntity>> serviceObserver = services -> {
        if (services == null || services.isEmpty()) {
            List<ServiceEntity> dummyList = Arrays.asList(
                    new ServiceEntity(R.drawable.worker_housekeeping, "Housekeeping", "Jack Hinshelwood", 4.5, 120, 50.0, true),
                    new ServiceEntity(R.drawable.worker_electrician, "Electrician", "Carlos Baleba", 4.8, 200, 75.0, false),
                    new ServiceEntity(R.drawable.worker_moving__and_packing, "Moving & Packing", "Kylian Mbappe", 4.7, 150, 60.0, true),
                    new ServiceEntity(R.drawable.worker_plumbing, "Plumbing", "Matt O'Riley", 4.9, 300, 80.0, false)
            );
            serviceRepository.insertDummyData(dummyList);
        }
    };

    private final Observer<List<CategoryEntity>> categoryObserver = categories -> {
        if (categories == null || categories.isEmpty()) {
            List<CategoryEntity> dummyList = Arrays.asList(
                    new CategoryEntity(R.drawable.worker_housekeeping, "Housekeeping"),
                    new CategoryEntity(R.drawable.worker_electrician, "Electrician"),
                    new CategoryEntity(R.drawable.worker_moving__and_packing, "Moving & Packing"),
                    new CategoryEntity(R.drawable.worker_plumbing, "Plumbing")
            );
            categoryRepository.insertCategory(dummyList);
        }
    };

    private void checkAndInsertDummyData() {
        serviceRepository.getAllServices().observeForever(serviceObserver);
    }

    private void checkAndInsertCategoryData() {
        categoryRepository.getAllCategories().observeForever(categoryObserver);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        serviceRepository.getAllServices().removeObserver(serviceObserver);
        categoryRepository.getAllCategories().removeObserver(categoryObserver);
    }

    public void insertService(List<ServiceEntity> services) {
        serviceRepository.insertService(services);
    }

    public void insertCategory(List<CategoryEntity> categories) {
        categoryRepository.insertCategory(categories);
    }

    public LiveData<List<ServiceEntity>> getAllServices() {
        return serviceRepository.getAllServices();
    }

    public LiveData<List<CategoryEntity>> getAllCategories() {
        return categoryRepository.getAllCategories();
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
