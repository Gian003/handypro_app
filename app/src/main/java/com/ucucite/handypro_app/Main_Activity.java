package com.ucucite.handypro_app;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
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
        setContentView(R.layout.onboarding_activity);

        ViewPager2 viewPager = findViewById(R.id.viewPager_OnBoarding);
        tabLayout = findViewById(R.id.tabLayout_Onboarding);

        // Set up onboarding adapter
        List<OnBoardingItem> items = new ArrayList<>();
        items.add(new OnBoardingItem(R.drawable.onboarding1,"We provide professional service at a friendly price."));
        items.add(new OnBoardingItem(R.drawable.onboarding2,"Deliver expert service with reliability and care to every home"));
        items.add(new OnBoardingItem(R.drawable.onboarding3, "Book your service, relax, and enjoy a spotless home! "));

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

        ImageView BtnPrev = findViewById(R.id.Prev_Button_OnBoarding);
        ImageView BtnNext = findViewById(R.id.Next_Button_OnBoarding);

        Button button = findViewById(R.id.OnBoarding_Button);

        BtnPrev.setAlpha(.5f);
        BtnPrev.setEnabled(false);

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);

                if (position == 0) {
                    BtnPrev.setAlpha(.5f);
                    BtnPrev.setEnabled(false);
                    button.setVisibility(View.INVISIBLE);
                } else {
                    BtnPrev.setAlpha(1f);
                    BtnPrev.setEnabled(true);
                    button.setVisibility(View.INVISIBLE);
                }

                if (position == 2) {
                    BtnNext.setAlpha(.5f);
                    BtnNext.setEnabled(false);
                    button.setVisibility(View.VISIBLE);
                } else {
                    BtnNext.setAlpha(1f);
                    BtnNext.setEnabled(true);
                    button.setVisibility(View.INVISIBLE);
                }
            }
        });

        BtnPrev.setOnClickListener(v -> {
            int prevItem = viewPager.getCurrentItem() - 1;
            if (prevItem < adapter.getItemCount()) {
                viewPager.setCurrentItem(prevItem, true);
            }
        });

        BtnNext.setOnClickListener(v -> {
            int nextItem = viewPager.getCurrentItem() + 1;
            if (nextItem >= 0) {
                viewPager.setCurrentItem(nextItem,  true);
            }
        });
    }
}
