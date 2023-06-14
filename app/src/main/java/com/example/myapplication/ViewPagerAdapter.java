package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    HomeFragment homeFragment = new HomeFragment();
    AccountFragment accountFragment = new AccountFragment();
    GiftFragment giftFragment = new GiftFragment();
    MapsFragment mapsFragment = new MapsFragment();

    public ViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return   new HomeFragment();
            case 1:
                return  new AccountFragment();
            case 2:
                return new GiftFragment();
            case 3:
                return new MapsFragment();
            default:
                return new HomeFragment();
        }

    }

    @Override
    public int getCount() {
        return 4;
    }
}
