package com.ucucite.handypro_app;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class Main_Activity extends AppCompatActivity {

    private ViewPager2 viewPager;
    private TabLayout tabLayout;
    private OnBoardingAdapter onBoardingAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);

        int[] layouts = new int[]{
                R.layout.activity_onboarding1,
                R.layout.activity_onboarding2
        };

        OnBoardingAdapter adapter = new OnBoardingAdapter(layouts);
        viewPager.setAdapter(adapter);

        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            switch (position) {
                case 0:
                    tab.setText("First");
                    break;
                case 1:
                    tab.setText("Second");
                    break;
            }
        }).attach();
    }
}