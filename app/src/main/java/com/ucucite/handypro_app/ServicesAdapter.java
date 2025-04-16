package com.ucucite.handypro_app;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ServicesAdapter extends RecyclerView.Adapter<ServicesAdapter.ViewHolder>{

    private List<ServicesItem> servicesItems;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView service;
        TextView worker;
        TextView rating;
        TextView reviews;
        TextView price;
        TextView discount;

        public ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.worker_image);
            service = itemView.findViewById(R.id.worker_service);
            worker = itemView.findViewById(R.id.worker_name);
            rating = itemView.findViewById(R.id.worker_rating);
            reviews = itemView.findViewById(R.id.worker_review);
            price = itemView.findViewById(R.id.worker_price);
            discount = itemView.findViewById(R.id.worker_discount);
        }
    }

    public ServicesAdapter(List<ServicesItem> servicesItems) {
        this.servicesItems = servicesItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.services_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ServicesItem item = servicesItems.get(position);
        holder.image.setImageResource(item.getImage());
        holder.service.setText(item.getService());
        holder.worker.setText(item.getWorker());

        String ratingText = holder.itemView.getContext().getString(R.string.Home_Recc_Icon_Rating, item.getRating());
        holder.rating.setText(ratingText);

        String reviewText = holder.itemView.getContext().getString(R.string.Home_Recc_text_Review, item.getReviews());
        holder.reviews.setText(reviewText);

        String priceText = holder.itemView.getContext().getString(R.string.Home_Recc_text_Price, item.getPrice());
        holder.price.setText(priceText);

        if (item.hasDiscount()) {
            holder.discount.setVisibility(View.VISIBLE);
        } else {
            holder.discount.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return servicesItems.size();
    }
}
