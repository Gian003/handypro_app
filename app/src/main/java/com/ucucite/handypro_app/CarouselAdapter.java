package com.ucucite.handypro_app;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CarouselAdapter extends RecyclerView.Adapter<CarouselAdapter.CarouselViewHolder> {
    private List<Carouselitem> carouselItems;

    public CarouselAdapter(List<Carouselitem> carouselItems) {
        this.carouselItems = carouselItems;
    }

    @NonNull
    @Override
    public CarouselViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.carousel_item, parent, false);
        return new CarouselViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CarouselViewHolder holder, int position) {
        Carouselitem carousel = carouselItems.get(position);
        holder.imageViewService.setImageResource(carousel.getImage());
        holder.textViewPromo.setText(carousel.getPromo());
        holder.textViewService.setText(carousel.getService());
    }

    @Override
    public int getItemCount() {
        return carouselItems.size();
    }

    static class CarouselViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewService;
        TextView textViewPromo;
        TextView textViewService;
        Button buttonDetails;

        public CarouselViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewService = itemView.findViewById(R.id.offer_ad_image);
            textViewPromo = itemView.findViewById(R.id.offer_ad_promo);
            textViewService = itemView.findViewById(R.id.offer_ad_service);
            buttonDetails = itemView.findViewById(R.id.offer_ad_button);
        }
    }
}
