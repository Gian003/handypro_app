package com.ucucite.handypro_app;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ServiceDataList {

    public static List<ServicesItem> getServicesItems() {
        List<ServicesItem> servicesItems = new ArrayList<>();
        servicesItems.add(new ServicesItem(R.drawable.worker_housekeeping, "Housekeeping", "Jack Hinshelwood", 4.5, 120, 50.0, true));
        servicesItems.add(new ServicesItem(R.drawable.worker_electrician, "Electrician", "Carlos Baleba", 4.8, 200, 75.0, false));
        servicesItems.add(new ServicesItem(R.drawable.worker_moving__and_packing, "Moving & Packing", "Kylian Mbappe", 4.7, 150, 60.0, true));
        servicesItems.add(new ServicesItem(R.drawable.worker_plumbing, "Plumbing", "Matt O'Riley", 4.9, 300, 80.0, false));
        return servicesItems;
    }

    public static List<CategoryServicesItem> getCategoryServicesItems() {
        List<CategoryServicesItem> categoryServicesItems = new ArrayList<>();
        categoryServicesItems.add(new CategoryServicesItem(R.drawable.home_services_ic_housekeeping, "Housekeeping"));
        categoryServicesItems.add(new CategoryServicesItem(R.drawable.home_services_ic_plumbing, "Plumbing"));
        categoryServicesItems.add(new CategoryServicesItem(R.drawable.home_services_ic_electrician, "Electrician"));
        return categoryServicesItems;
    }
}
