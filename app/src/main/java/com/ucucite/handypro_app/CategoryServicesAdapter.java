package com.ucucite.handypro_app;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CategoryServicesAdapter extends RecyclerView.Adapter<CategoryServicesAdapter.ViewHolder> {

    private final List<CategoryEntity> categoryList;
    private final OnCategoryClickListener listener;

    public interface OnCategoryClickListener {
        void onCategoryClick(String categoryLabel);
    }

    public CategoryServicesAdapter(List<CategoryEntity> categoryList, OnCategoryClickListener listener) {
        this.categoryList = categoryList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_services_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CategoryEntity categoryItem = categoryList.get(position);
        holder.categoryIcon.setImageResource(categoryItem.image);
        holder.categoryLabel.setText(categoryItem.label);

        holder.itemView.setOnClickListener(v -> {
                listener.onCategoryClick(categoryItem.label);
        });
    }

    @Override
    public int getItemCount() {
        return categoryList == null ? 0 : categoryList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView categoryIcon;
        TextView categoryLabel;

        public ViewHolder(View itemView) {
            super(itemView);
            categoryIcon = itemView.findViewById(R.id.category_icon);
            categoryLabel = itemView.findViewById(R.id.category_label);
        }
    }
}
