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

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class FilterBottomSheet extends BottomSheetDialogFragment {

    private OnFilterSelected listener;

    public interface OnFilterSelected {
        void onFilterSelected(String filterType);
    }

    public void setListener(OnFilterSelected listener) {
        this.listener = listener;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.bottom_sheet_filter, container, false);

        RadioGroup radioGroup = view.findViewById(R.id.radioGroup);
        CardView btnApply = view.findViewById(R.id.card_button_apply);

        btnApply.setOnClickListener(v -> {
            int selectedId = radioGroup.getCheckedRadioButtonId();

            String filterType = "";

            if (selectedId == R.id.radioButton_category) {
                filterType = "category";
            } else if (selectedId == R.id.radioButton_ratingLtH) {
                filterType = "ratingLtH";
            } else if (selectedId == R.id.radioButton_ratingHtL) {
                filterType = "ratingHtL";
            } else if (selectedId == R.id.radioButton_reviews) {
                filterType = "reviews";
            } else if (selectedId == R.id.radioButton_priceLtH) {
                filterType = "priceLtH";
            } else if (selectedId == R.id.radioButton_priceHtL) {
                filterType = "priceHtL";
            }

            if (listener != null && !filterType.isEmpty()) {
                listener.onFilterSelected(filterType);
            }

            dismiss();
        });

        return view;
    }
}
