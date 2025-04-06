package com.ucucite.handypro_app;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class OnBoardingAdapter extends RecyclerView.Adapter<OnBoardingAdapter.OnBoardingViewHolder> {
    private List<OnBoardingItem> items;

    public OnBoardingAdapter(List<OnBoardingItem> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public OnBoardingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.onboarding_item, parent, false);
        return new OnBoardingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OnBoardingViewHolder holder, int position) {
        OnBoardingItem item = items.get(position);
        holder.imageView.setImageResource(item.getImage());
        holder.textView.setText(item.getText());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class OnBoardingViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;

        public OnBoardingViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView_OnBoarding);
            textView = itemView.findViewById(R.id.textView_OnBoarding);
        }
    }
}
