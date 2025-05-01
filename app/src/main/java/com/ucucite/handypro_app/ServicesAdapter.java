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

import java.util.ArrayList;
import java.util.List;

public class ServicesAdapter extends ListAdapter<ServiceEntity, ServicesAdapter.VH> {

    public ServicesAdapter() {
        super(new DiffUtil.ItemCallback<ServiceEntity>() {
            @Override
            public boolean areItemsTheSame(@NonNull ServiceEntity oldItem, @NonNull ServiceEntity newItem) {
                return oldItem.id==newItem.id;
            }

            @Override
            public boolean areContentsTheSame(@NonNull ServiceEntity oldItem, @NonNull ServiceEntity newItem) {
                return oldItem.equals(newItem);
            }
        });
    }

    public static class VH extends RecyclerView.ViewHolder {
        ImageView image;
        TextView service;
        TextView worker;
        TextView rating;
        TextView reviews;
        TextView price;

        public VH(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.worker_image);
            service = itemView.findViewById(R.id.worker_service);
            worker = itemView.findViewById(R.id.worker_name);
            rating = itemView.findViewById(R.id.worker_rating);
            reviews = itemView.findViewById(R.id.worker_review);
            price = itemView.findViewById(R.id.worker_price);
        }
    }

    @NonNull
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.services_item, parent, false);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        ServiceEntity service = getItem(position);
        holder.image.setImageResource(service.image);
        holder.service.setText(service.service);
        holder.worker.setText(service.worker);
        holder.rating.setText(String.valueOf(service.rating));
        holder.reviews.setText(String.valueOf(service.reviews));
        holder.price.setText(String.valueOf(service.price));
    }
}
