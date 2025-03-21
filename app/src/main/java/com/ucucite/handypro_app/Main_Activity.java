package com.ucucite.handypro_app;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;


public class Main_Activity extends AppCompatActivity {

    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager2 viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);

        // Set up onboarding adapter
        List<OnBoardingItem> items = new ArrayList<>();
        items.add(new OnBoardingItem(R.drawable.onboarding1,"We provide professional service at a friendly price."));
        items.add(new OnBoardingItem(R.drawable.onboarding2,"Onboarding 2"));
        items.add(new OnBoardingItem(R.drawable.onboarding3, "Onboarding 3"));

        OnBoardingAdapter adapter = new OnBoardingAdapter(items);
        viewPager.setAdapter(adapter);

        tabLayout.setSelectedTabIndicator(null);
        tabLayout.setTabRippleColor(null);

        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            tab.setIcon(R.drawable.navigation_indicator_inactive);
        }).attach();

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                for (int i = 0; i < tabLayout.getTabCount(); i++) {
                    TabLayout.Tab tab = tabLayout.getTabAt(i);
                    if (tab != null) {
                        if (i == position) {
                            tab.setIcon(R.drawable.navigation_indicator_active);
                        } else {
                            tab.setIcon(R.drawable.navigation_indicator_inactive);
                        }
                    }
                }
            }
        });
    }
}
