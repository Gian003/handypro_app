package com.ucucite.handypro_app;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.ucucite.handypro_app.CategoryEntity;

import java.util.List;

public class CategoryServicesAdapter extends ListAdapter<CategoryEntity, CategoryServicesAdapter.VH> {

    private final OnCategoryClickListener listener;

    public interface OnCategoryClickListener {
        void onCategoryClick(String categoryLabel);
    }

    public CategoryServicesAdapter(OnCategoryClickListener listener) {
        super(new DiffUtil.ItemCallback<>() {
            @Override
            public boolean areItemsTheSame(@NonNull CategoryEntity oldItem, @NonNull CategoryEntity newItem) {
                return oldItem.getId() == newItem.getId();
            }

            @Override
            public boolean areContentsTheSame(@NonNull CategoryEntity oldItem, @NonNull CategoryEntity newItem) {
                return oldItem.equals(newItem);
            }
        });
        this.listener = listener;
    }

    public static class VH extends RecyclerView.ViewHolder {
        ImageView categoryIcon;
        TextView categoryLabel;

        public VH(@NonNull View itemView) {
            super(itemView);
            categoryIcon = itemView.findViewById(R.id.category_icon);
            categoryLabel = itemView.findViewById(R.id.category_label);
        }
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_services_item, parent, false);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        CategoryEntity item = getItem(position);
        holder.categoryIcon.setImageResource(item.image);
        holder.categoryLabel.setText(item.label);

        holder.itemView.setOnClickListener(v -> {
            listener.onCategoryClick(item.label);
        });
    }
}
