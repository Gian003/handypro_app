package com.ucucite.handypro_app;

import android.os.Bundle;
import android.os.Handler;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Home_Activity extends AppCompatActivity {

    private Handler handler = new Handler();
    private Runnable runnable;
    private int currentItem = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);

        ViewPager2 viewPager = findViewById(R.id.Home_ViewPager);
        TabLayout tabLayout = findViewById(R.id.Home_TabLayout);

        List<Carouselitem> carouselItems = new ArrayList<>();
        carouselItems.add(new Carouselitem(R.drawable.offers_plumbing, "20% off", "Plumbing Service"));
        carouselItems.add(new Carouselitem(R.drawable.offers_house_cleaning, "15% off", "House Cleaning Service"));
        carouselItems.add(new Carouselitem(R.drawable.offers_appliance_repair, "30% off", "Appliance Repair Service"));

        CarouselAdapter carouselAdapter = new CarouselAdapter(carouselItems);
        viewPager.setAdapter(carouselAdapter);

        runnable = new Runnable() {
            @Override
            public void run() {
                if (currentItem == carouselItems.size()) {
                    currentItem = 0;
                }
                viewPager.setCurrentItem(currentItem, true);
                TabLayout.Tab tab = tabLayout.getTabAt(currentItem);
                if (tab != null) {
                    tab.select();
                }
                currentItem++;
                handler.postDelayed(this, 3000);
            }
        };
        handler.post(runnable);

        viewPager.setClipChildren(false);
        viewPager.setClipToPadding(false);
        viewPager.setOffscreenPageLimit(1);

        viewPager.setPageTransformer((page, position) -> {
            float absPos = Math.abs(position);
            page.setScaleX(1 - absPos * 0.1f);
            page.setScaleY(1 - absPos * 0.1f);
            page.setAlpha(1 - absPos * 0.1f);
        });

        tabLayout.setSelectedTabIndicator(null);
        tabLayout.setTabRippleColor(null);

        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            tab.setIcon(R.drawable.promo_indicator_default);
        }).attach();

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                for (int i = 0; i < tabLayout.getTabCount(); i++) {
                    TabLayout.Tab tab = tabLayout.getTabAt(i);
                    if (tab != null) {
                        if (i == position) {
                            tab.setIcon(R.drawable.promo_indicator_selected);
                            Objects.requireNonNull(tab.getIcon()).setTint(getResources().getColor(R.color.indicator_color));
                        } else {
                            tab.setIcon(R.drawable.promo_indicator_default);
                        }
                    }
                }
            }
        });
    }
}