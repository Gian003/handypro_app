package com.ucucite.handypro_app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.button.MaterialButton;

import java.util.List;

public class FragmentExplore extends Fragment {

    private ServicesAdapter servicesAdapter;
    private RecyclerView servicesRecyclerView;
    private HandyProViewModel handyProViewModel;
    private void showFilterDialog() {
        FilterBottomSheet bottomSheet = new FilterBottomSheet();
        bottomSheet.setListener(filterType -> {
            servicesRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

            switch (filterType) {
                case "category":
                    String selectedCategory = handyProViewModel.getLastSelectedCategory();
                    if (selectedCategory != null && !selectedCategory.isEmpty()) {
                        servicesRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
                        handyProViewModel.getSortedCategory(selectedCategory)
                                .observe(getViewLifecycleOwner(), servicesAdapter::submitList);
                    }
                    break;
                case "ratingLtH":
                    handyProViewModel.getSortedByRating().observe(getViewLifecycleOwner(), servicesAdapter::submitList);
                    break;
                case "ratingHtL":
                    handyProViewModel.getSortedByRatingDesc().observe(getViewLifecycleOwner(), servicesAdapter::submitList);
                    break;
                case "reviews":
                    handyProViewModel.getSortedByReviews().observe(getViewLifecycleOwner(), servicesAdapter::submitList);
                    break;
                case "priceLtH":
                    handyProViewModel.getSortedByPrice().observe(getViewLifecycleOwner(), servicesAdapter::submitList);
                    break;
                case "priceHtL":
                    handyProViewModel.getSortedByPriceDesc().observe(getViewLifecycleOwner(), servicesAdapter::submitList);
                    break;
                default:
                    handyProViewModel.getAllServices().observe(getViewLifecycleOwner(), servicesAdapter::submitList);
                    break;
            }

            servicesRecyclerView.scrollToPosition(0);
        });

        bottomSheet.show(getChildFragmentManager(), bottomSheet.getTag());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_explore, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        handyProViewModel = new ViewModelProvider(this).get(HandyProViewModel.class);

        // Button for filters
        MaterialButton filterButton = view.findViewById(R.id.Explore_FilterButton);
        filterButton.setOnClickListener(v -> showFilterDialog());

        // Services RecyclerView
        servicesRecyclerView = view.findViewById(R.id.Explore_RecyclerView);
        servicesAdapter = new ServicesAdapter();
        servicesRecyclerView.setAdapter(servicesAdapter);
        servicesRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        handyProViewModel.getAllServices().observe(getViewLifecycleOwner(), servicesAdapter::submitList);

        CategoryServicesAdapter categoryServicesAdapter = new CategoryServicesAdapter(categoryLabel -> {
            handyProViewModel.setSelectedCategory(categoryLabel);
            handyProViewModel.getSortedCategory(categoryLabel).observe(getViewLifecycleOwner(), servicesAdapter::submitList);
        });

        handyProViewModel.getAllCategories().observe(getViewLifecycleOwner(), categoryServicesAdapter::submitList);

    }
}
