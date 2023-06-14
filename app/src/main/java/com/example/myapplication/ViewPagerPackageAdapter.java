package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class ViewPagerPackageAdapter extends FragmentPagerAdapter {
    public ViewPagerPackageAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    public ViewPagerPackageAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new BuyPackageFragment();
            case 1:
                return new ManagerPackageFragment();
            default:
                return new BuyPackageFragment();
        }

    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title;
        switch (position){
            case 0:
                title = "Mua vé";
                break;
            case 1:
                title = "Quản lí vé";
                break;
            default:
                title = "Mua vé";

        }
        return title;
    }
}
