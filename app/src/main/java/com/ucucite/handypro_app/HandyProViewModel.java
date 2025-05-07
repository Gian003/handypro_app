package com.ucucite.handypro_app;

import static com.ucucite.handypro_app.AppDatabase.getDummyCategories;
import static com.ucucite.handypro_app.AppDatabase.getDummyServices;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import java.util.List;

public class HandyProViewModel extends AndroidViewModel {
    private final ServiceRepository serviceRepository;

    private final CategoryRepository categoryRepository;
    private final MutableLiveData<String> selectedCategory = new MutableLiveData<>();

    public HandyProViewModel(@NonNull Application application) {
        super(application);
        serviceRepository = new ServiceRepository(application);
        categoryRepository = new CategoryRepository(application);

        checkAndInsertServicesDummyData();
        checkAndInsertCategoryDummyData();
    }

    public void checkAndInsertServicesDummyData() {
        LiveData<List<ServiceEntity>> servicesLiveData = serviceRepository.getAllServices();
        servicesLiveData.observeForever(new Observer<>() {
            @Override
            public void onChanged(List<ServiceEntity> services) {
                if (services == null || services.isEmpty()) {
                    serviceRepository.insertDummyData(getDummyServices());
                }
                servicesLiveData.removeObserver(this);
            }
        });
    }
    public void checkAndInsertCategoryDummyData() {
        LiveData<List<CategoryEntity>> categoriesLiveData = categoryRepository.getAllCategories();
        categoriesLiveData.observeForever(new Observer<>() {
            @Override
            public void onChanged(List<CategoryEntity> categories) {
                if (categories == null || categories.isEmpty()) {
                    categoryRepository.insertCategory(getDummyCategories());
                }
                categoriesLiveData.removeObserver(this);
            }
        });
    }

    public void insertService(List<ServiceEntity> services) {
        serviceRepository.insertService(services);
    }

    public void insertCategory(List<CategoryEntity> categories) {
        categoryRepository.insertCategory(categories);
    }

    public LiveData<List<CategoryEntity>> getAllCategories() {
        return categoryRepository.getAllCategories();
    }

    public void setSelectedCategory(String categoryLabel) {
        selectedCategory.setValue(categoryLabel);
    }

    public String getLastSelectedCategory() {
        return selectedCategory.getValue();
    }

    public LiveData<List<ServiceEntity>> getAllServices() {
        return serviceRepository.getAllServices();
    }


    public LiveData<List<ServiceEntity>> getSortedCategory(Object categoryLabel) {
        return serviceRepository.getSortedCategory((String) categoryLabel);
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
