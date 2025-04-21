package com.ucucite.handypro_app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

public class FragmentExplore extends Fragment {

    private ServicesAdapter servicesAdapter;
    private CategoryServicesAdapter categoryServicesAdapter;
    private void showFilterDialog () {
        BottomSheetDialog dialog = new BottomSheetDialog(requireContext());
        View view = LayoutInflater.from(getContext()).inflate(R.layout.bottom_sheet_filter, null);
        dialog.setContentView(view);

        RadioGroup radioGroup = view.findViewById(R.id.radioGroup);
        CardView applyButton = view.findViewById(R.id.card_button_apply);

        applyButton.setOnClickListener(v -> {
            int selectedId = radioGroup.getCheckedRadioButtonId();

            List<ServicesItem> filteredList = ServiceDataList.getServicesItems();
            List<CategoryServicesItem> categoryFilteredList = ServiceDataList.getCategoryServicesItems();

            if (selectedId == R.id.radioButton_general) {
                filteredList = ServiceDataList.getServicesItems();
            } else if (selectedId == R.id.radioButton_category) {
                categoryFilteredList = ServiceDataList.getCategoryServicesItems();
            } else if (selectedId == R.id.radioButton_ratingLtH) {
                filteredList = ServiceDataList.getSortedRatingLtH();
            } else if (selectedId == R.id.radioButton_ratingHtL) {
                filteredList = ServiceDataList.getSortedRatingHtL();
            } else if (selectedId == R.id.radioButton_reviews) {
                filteredList = ServiceDataList.getSortedReviews();
            } else if (selectedId == R.id.radioButton_priceLtH) {
                filteredList = ServiceDataList.getSortedPriceLtH();
            } else if (selectedId == R.id.radioButton_priceHtL) {
                filteredList = ServiceDataList.getSortedPriceHtL();
            }

            servicesAdapter.filterList(filteredList);
            dialog.dismiss();
        });
        dialog.show();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_explore, container, false);
    }

    @Override
    public void onViewCreated (@NonNull View view, @Nullable Bundle savedInstanceState) {
        MaterialButton filterButton = view.findViewById(R.id.Explore_FilterButton);
        filterButton.setOnClickListener(v -> showFilterDialog());

        RecyclerView servicesRecyclerView = view.findViewById(R.id.Explore_RecyclerView);

        // Initialize Services Adapter
        servicesAdapter = new ServicesAdapter(ServiceDataList.getServicesItems());
        servicesRecyclerView.setAdapter(servicesAdapter);

        // Category filter logic
        categoryServicesAdapter = new CategoryServicesAdapter(
                ServiceDataList.getCategoryServicesItems(),
                categoryLabel -> {
                    List<ServicesItem> filteredCategoryItems = ServiceDataList.getSortedCategory(categoryLabel);
                    servicesAdapter.filterList(filteredCategoryItems);
                }
        );
    }
}
