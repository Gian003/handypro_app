package com.ucucite.handypro_app;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CategoryServicesAdapter extends RecyclerView.Adapter<CategoryServicesAdapter.ViewHolder>{
    private List<CategoryServicesItem> categoryServicesItems;

    public CategoryServicesAdapter(List<CategoryServicesItem> categoryServicesItems) {
        this.categoryServicesItems = categoryServicesItems;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView icon;
        public ViewHolder(View itemView) {
            super(itemView);
            icon = itemView.findViewById(R.id.home_services_icon);
        }
    }

    @Override
    public CategoryServicesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.home_services_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CategoryServicesAdapter.ViewHolder holder, int position) {
        CategoryServicesItem item = categoryServicesItems.get(position);
        holder.icon.setImageResource(item.getImage());
    }

    @Override
    public int getItemCount() {
        return categoryServicesItems.size();
    }
}
