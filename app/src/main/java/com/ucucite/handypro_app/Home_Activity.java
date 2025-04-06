package com.ucucite.handypro_app;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;
import java.util.List;

public class Home_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);

        ViewPager2 viewPager = findViewById(R.id.Home_ViewPager);

        List<Carouselitem> carouselItems = new ArrayList<>();
        carouselItems.add(new Carouselitem(R.drawable.offers_plumbing, "20% off", "Plumbing Service"));
        carouselItems.add(new Carouselitem(R.drawable.offers_house_cleaning, "15% off", "House Cleaning Service"));
        carouselItems.add(new Carouselitem(R.drawable.offers_appliance_repair, "30% off", "Appliance Repair Service"));

        CarouselAdapter carouselAdapter = new CarouselAdapter(carouselItems);
        viewPager.setAdapter(carouselAdapter);

        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            int currentItem = 0;

            @Override
            public void run() {
                if (currentItem == carouselItems.size()) {
                    currentItem = 0;
                }
                viewPager.setCurrentItem(currentItem++, true);
                handler.postDelayed(this, 3000);
            }
        };
        handler.post(runnable);

        viewPager.setClipChildren(false);
        viewPager.setClipToPadding(false);
        viewPager.setOffscreenPageLimit(3);

        viewPager.setPageTransformer((page, position) -> {
            float scale = 1 - Math.abs(position) * 0.1f;
            page.setScaleX(scale);
            page.setScaleY(scale);
        });
    }
}