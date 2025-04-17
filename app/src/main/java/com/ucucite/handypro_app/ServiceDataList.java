package com.ucucite.handypro_app;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ServiceDataList {
    private static final List<ServicesItem> servicesItems = new ArrayList<>();
    private static final List<CategoryServicesItem> categoryServicesItems = new ArrayList<CategoryServicesItem>();

    static {
        servicesItems.add(new ServicesItem(R.drawable.worker_housekeeping, "Housekeeping", "Jack Hinshelwood", 4.5, 120, 50.0, true));
        servicesItems.add(new ServicesItem(R.drawable.worker_electrician, "Electrician", "Carlos Baleba", 4.8, 200, 75.0, false));
        servicesItems.add(new ServicesItem(R.drawable.worker_moving__and_packing, "Moving & Packing", "Kylian Mbappe", 4.7, 150, 60.0, true));
        servicesItems.add(new ServicesItem(R.drawable.worker_plumbing, "Plumbing", "Matt O'Riley", 4.9, 300, 80.0, false));
    }

    static {
        categoryServicesItems.add(new CategoryServicesItem(R.drawable.home_services_ic_housekeeping, "Housekeeping"));
        categoryServicesItems.add(new CategoryServicesItem(R.drawable.home_services_ic_plumbing, "Plumbing"));
        categoryServicesItems.add(new CategoryServicesItem(R.drawable.home_services_ic_electrician, "Electrician"));
    }

    public static List<ServicesItem> getServicesItems() {
        return new ArrayList<>(servicesItems);
    }

    public static List<CategoryServicesItem> getCategoryServicesItems() {
        return new ArrayList<>(categoryServicesItems);
    }

    public static List<ServicesItem> getSortedRatingAscending() {
        List<ServicesItem> getSortedRatingAscend = new ArrayList<>(servicesItems);
        getSortedRatingAscend.sort(Comparator.comparingDouble(ServicesItem::getRating));
        return getSortedRatingAscend;
    }

    public static List<ServicesItem> getSortedRatingDescending() {
        List<ServicesItem> getSortedRatingDescend = new ArrayList<>(servicesItems);
        getSortedRatingDescend.sort(Comparator.comparingDouble(ServicesItem::getRating).reversed());
        return getSortedRatingDescend;
    }

    public static List<ServicesItem> getSortedReviewsAscending() {
        List<ServicesItem> getSortedReviewsAscend = new ArrayList<>(servicesItems);
        getSortedReviewsAscend.sort(Comparator.comparingInt(ServicesItem::getReviews));
        return getSortedReviewsAscend;
    }

    public static List<ServicesItem> getSortedReviewsDescending() {
        List<ServicesItem> getSortedReviewsDescend = new ArrayList<>(servicesItems);
        getSortedReviewsDescend.sort(Comparator.comparingInt(ServicesItem::getReviews).reversed());
        return getSortedReviewsDescend;
    }

    public static List<ServicesItem> getSortedPriceAscending() {
        List<ServicesItem> getSortedAscend = new ArrayList<>(servicesItems);
        getSortedAscend.sort(Comparator.comparingDouble(ServicesItem::getPrice));
        return getSortedAscend;
    }
    public static List<ServicesItem> getSortedPriceDescending() {
        List<ServicesItem> getSortedDescend = new ArrayList<>(servicesItems);
        getSortedDescend.sort(Comparator.comparingDouble(ServicesItem::getPrice).reversed());
        return getSortedDescend;
    }
}
