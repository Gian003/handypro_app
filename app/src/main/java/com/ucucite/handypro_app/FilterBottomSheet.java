package com.ucucite.handypro_app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class FilterBottomSheet extends BottomSheetDialogFragment {
    private OnFilterSelected listener;

    public interface OnFilterSelected {
        void onFilterSelected(String filterType);
    }

    public void setListener(OnFilterSelected listener) {
        this.listener = listener;
    }

//    @Nullable
//    @Override
//    public View onCreateView (@NonNull LayoutInflater inflater, @NonNull ViewGroup container, @NonNull Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.filter_bottom_sheet, container, false);
//
//    };
}
