package com.ucucite.handypro_app;

import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HomeServicesAdapter extends RecyclerView.Adapter<HomeServicesAdapter.ViewHolder>{
    private List<HomeServicesItem> homeServicesItems;

    public HomeServicesAdapter(List<HomeServicesItem> homeServicesItems) {
        this.homeServicesItems = homeServicesItems;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView icon;
        public ViewHolder(View itemView) {
            super(itemView);
            icon = itemView.findViewById(R.id.home_services_icon);
        }
    }

    @Override
    public HomeServicesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.home_services_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HomeServicesAdapter.ViewHolder holder, int position) {
        HomeServicesItem item = homeServicesItems.get(position);
        holder.icon.setImageResource(item.getImage());
    }

    @Override
    public int getItemCount() {
        return homeServicesItems.size();
    }
}
