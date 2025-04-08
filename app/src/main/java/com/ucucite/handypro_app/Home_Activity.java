package com.ucucite.handypro_app;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;

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

    private int dpToPx(int dp) {
        return (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics());
    }

    private void animateTabWidth (View tabView, int fromWidth, int toWidth){
        ValueAnimator animator = ValueAnimator.ofInt(fromWidth, toWidth);
        animator.setDuration(250);
        animator.addUpdateListener(animation -> {
            int value = (int) animation.getAnimatedValue();
            ViewGroup.LayoutParams params = tabView.getLayoutParams();
            params.width = value;
            tabView.setLayoutParams(params);
        });
        animator.start();
    }

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
            View indicator = new View(this);
            indicator.setBackgroundResource(R.drawable.promo_indicator_selector);
            int size = dpToPx(8);
            ViewGroup.LayoutParams  params = new ViewGroup.LayoutParams(size, dpToPx(8));
            indicator.setLayoutParams(params);
            tab.setCustomView(indicator);
        }).attach();

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                View view = tab.getCustomView();
                if (view != null) {
                    view.setSelected(true);
                    animateTabWidth(view, dpToPx(8), dpToPx(40));
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                View view = tab.getCustomView();
                if (view != null) {
                    view.setSelected(false);
                    animateTabWidth(view, dpToPx(40), dpToPx(8));
                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}