package com.ucucite.handypro_app;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ucucite.handypro_app.CategoryServicesItem;

import java.util.List;

public class CategoryServicesAdapter extends RecyclerView.Adapter<CategoryServicesAdapter.ViewHolder> {

    private List<CategoryServicesItem> categoryServicesItems;
    private OnCategoryClickListener listener;

    public CategoryServicesAdapter(List<CategoryServicesItem> categoryServicesItems, OnCategoryClickListener listener) {
        this.categoryServicesItems = categoryServicesItems;
        this.listener = listener;
    }

    public interface OnCategoryClickListener {
        void onCategoryClick(String categoryLabel);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView icon;
        public ViewHolder(View itemView) {
            super(itemView);
            icon = itemView.findViewById(R.id.home_services_icon);
        }
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
        holder.icon.setImageResource(item.getImage());

        holder.icon.setOnClickListener(v -> {
            if (listener != null) {
                listener.onCategoryClick(item.getLabel());
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryServicesItems.size();
    }
}
