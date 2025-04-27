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

    private List<CategoryServicesItem> categoryServicesItems;
    private final OnCategoryClickListener listener;

    public interface OnCategoryClickListener {
        void onCategoryClick(String categoryLabel);
    }

    public CategoryServicesAdapter(List<CategoryServicesItem> categoryServicesItems, OnCategoryClickListener listener) {
        this.categoryServicesItems = categoryServicesItems;
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
        CategoryServicesItem item = categoryServicesItems.get(position);
        holder.categoryIcon.setImageResource(item.getImage());

        holder.categoryIcon.setOnClickListener(v -> {
            if (listener != null) {
                listener.onCategoryClick(item.getLabel());
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryServicesItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView categoryIcon;
        TextView categoryLabel;

        ViewHolder (View itemView) {
            super(itemView);
            categoryIcon = itemView.findViewById(R.id.category_icon);
            categoryLabel = itemView.findViewById(R.id.category_label);

            itemView.setOnClickListener(v -> {
                int position = getBindingAdapterPosition();
                if (position != RecyclerView.NO_POSITION && listener != null) {
                    listener.onCategoryClick(categoryServicesItems.get(position).getLabel());
                }
            });
        }

        void bind(CategoryServicesItem item) {
            categoryIcon.setImageResource(item.getImage());
            categoryLabel.setText(item.getLabel());
        }
    }
}
