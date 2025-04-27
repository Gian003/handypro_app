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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.button.MaterialButton;

import java.util.List;

public class FragmentExplore extends Fragment {

    private ServicesAdapter servicesAdapter;
    private ViewModel viewModel;
    private void showFilterDialog (ViewModel viewModel) {
        BottomSheetDialog dialog = new BottomSheetDialog(requireContext());
        View view = LayoutInflater.from(getContext()).inflate(R.layout.bottom_sheet_filter, null);
        dialog.setContentView(view);

        RadioGroup radioGroup = view.findViewById(R.id.radioGroup);
        CardView applyButton = view.findViewById(R.id.card_button_apply);

        applyButton.setOnClickListener(v -> {
            int selectedId = radioGroup.getCheckedRadioButtonId();

            if (selectedId == R.id.radioButton_category) {
                this.viewModel.getSortedCategory("categoryLabel").observe(getViewLifecycleOwner(), servicesAdapter::submitList);
            } else if (selectedId == R.id.radioButton_ratingLtH) {
                this.viewModel.getSortedByRating().observe(getViewLifecycleOwner(), servicesAdapter::submitList);
            } else if (selectedId == R.id.radioButton_ratingHtL) {
                this.viewModel.getSortedByRatingDesc().observe(getViewLifecycleOwner(), servicesAdapter::submitList);
            } else if (selectedId == R.id.radioButton_reviews) {
                this.viewModel.getSortedByReviews().observe(getViewLifecycleOwner(), servicesAdapter::submitList);
            } else if (selectedId == R.id.radioButton_priceLtH) {
                this.viewModel.getSortedByPrice().observe(getViewLifecycleOwner(), servicesAdapter::submitList);
            } else if (selectedId == R.id.radioButton_priceHtL) {
                this.viewModel.getSortedByPriceDesc().observe(getViewLifecycleOwner(), servicesAdapter::submitList);
            } else {
                this.viewModel.getAllServices().observe(getViewLifecycleOwner(), servicesAdapter::submitList);
            }

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
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Button for filters
        MaterialButton filterButton = view.findViewById(R.id.Explore_FilterButton);
        filterButton.setOnClickListener(v -> showFilterDialog(viewModel));

        // Main services RecyclerView
        RecyclerView servicesRecyclerView = view.findViewById(R.id.Explore_RecyclerView);
        servicesAdapter = new ServicesAdapter();
        servicesRecyclerView.setAdapter(servicesAdapter);
        servicesRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        // ViewModel
        viewModel = new ViewModelProvider(this).get(ViewModel.class);
        viewModel.getAllServices().observe(getViewLifecycleOwner(), servicesAdapter::submitList);

        // Now for the category icons RecyclerView
        List<CategoryServicesItem> categoryServicesItems = ServiceDataList.getCategoryServicesItems();
        RecyclerView categoryRecyclerView = view.findViewById(R.id.Home_Category_RecyclerView);
        CategoryServicesAdapter categoryServicesAdapter = new CategoryServicesAdapter(categoryServicesItems, categoryLabel -> {
            viewModel.getSortedCategory(categoryLabel).observe(getViewLifecycleOwner(), servicesAdapter::submitList);
        });
        categoryRecyclerView.setAdapter(categoryServicesAdapter);
        categoryRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));
    }
}
