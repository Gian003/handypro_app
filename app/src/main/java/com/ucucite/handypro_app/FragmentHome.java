package com.ucucite.handypro_app;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FragmentHome extends Fragment {

    private ViewModel viewModel;
    private RecyclerView recyclerView;
    private ServicesAdapter servicesAdapter;
    private final Handler handler = new Handler(Looper.getMainLooper());
    private Runnable runnable;
    private int currentItem = 0;

    // Convert dp to pixels
    private int dpToPx(int dp) {
        return (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics());
    }

    // Animate the width change for your tab indicators
    private void animateTabWidth(View tabView, int fromWidth, int toWidth) {
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
    public void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable);
        currentItem = 0;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (runnable != null) {
            handler.post(runnable);
        }
    }

    // Inflate the fragment layout
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    // Set up UI components once the view is created
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        // Set up your ViewPager2 and TabLayout
        ViewPager2 viewPager = view.findViewById(R.id.Home_ViewPager);
        TabLayout tabLayout = view.findViewById(R.id.Home_TabLayout);

        List<Carouselitem> carouselItems = new ArrayList<>();
        carouselItems.add(new Carouselitem(R.drawable.offers_plumbing, "20% off", "Plumbing Service"));
        carouselItems.add(new Carouselitem(R.drawable.offers_house_cleaning, "15% off", "House Cleaning Service"));
        carouselItems.add(new Carouselitem(R.drawable.offers_appliance_repair, "30% off", "Appliance Repair Service"));

        CarouselAdapter carouselAdapter = new CarouselAdapter(carouselItems);
        viewPager.setAdapter(carouselAdapter);

        // Runnable for auto-scrolling the carousel
        runnable = new Runnable() {
            @Override
            public void run() {
                if (carouselItems.isEmpty()) {
                    return;
                }
                if (currentItem >= carouselItems.size()) {
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
            View indicator = new View(getContext());
            indicator.setBackgroundResource(R.drawable.promo_indicator_selector);
            int size = dpToPx(8);
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(size, dpToPx(8));
            indicator.setLayoutParams(params);
            tab.setCustomView(indicator);
        }).attach();

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                View customView = tab.getCustomView();
                if (customView != null) {
                    customView.setSelected(true);
                    animateTabWidth(customView, dpToPx(8), dpToPx(40));
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                View customView = tab.getCustomView();
                if (customView != null) {
                    customView.setSelected(false);
                    animateTabWidth(customView, dpToPx(40), dpToPx(8));
                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                // No additional action required on reselection.
            }
        });

        // Set up the RecyclerView
        viewModel = new ViewModelProvider(this).get(ViewModel.class);
        recyclerView = view.findViewById(R.id.Home_Services_RecyclerView);
        servicesAdapter = new ServicesAdapter();
        recyclerView.setAdapter(servicesAdapter);

        viewModel.getAllServices().observe(getViewLifecycleOwner(), servicesAdapter::submitList);
    }

    // Clean up the handler callbacks when the view is destroyed to prevent memory leaks
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (runnable != null) {
            handler.removeCallbacks(runnable);
            runnable = null;
        }
        currentItem = 0;
    }
}
