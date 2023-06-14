package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

public class PackageActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_package);
        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.view_pager);
        ViewPagerPackageAdapter viewPagerPackageAdapter = new ViewPagerPackageAdapter(getSupportFragmentManager());
        viewPager.setAdapter(viewPagerPackageAdapter);
        tabLayout.setSelectedTabIndicator(getResources().getDrawable(R.drawable.custom_tab_indicator));
        tabLayout.setupWithViewPager(viewPager);
    }
}