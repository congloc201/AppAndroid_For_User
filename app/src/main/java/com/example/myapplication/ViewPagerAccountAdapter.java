package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class ViewPagerAccountAdapter extends FragmentStatePagerAdapter {
    public ViewPagerAccountAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new MoneyInOutFragment();
            case 1:
                return new MoneyInFragment();
            case 2:
                return new MoneyOutFragment();
            default:
                return new MoneyInOutFragment();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title="";
        switch (position){
            case 0:
                title="Tất cả";
                break;
            case 1:
                title="Tiền vào";
                break;
            case 2:
                title="Tiền ra";
                break;
        }
        return title;
    }
}
