package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class ViewPagerUsingPackageAdaper extends FragmentPagerAdapter {
    UsedPackageFragment usedPackageFragment = new UsedPackageFragment();
    UsingPackgeFragment usingPackgeFragment = new UsingPackgeFragment();
    public ViewPagerUsingPackageAdaper(@NonNull FragmentManager fm) {
        super(fm);
    }

    public ViewPagerUsingPackageAdaper(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return usedPackageFragment;
            case 1:
                return usingPackgeFragment;
            default:
                return usingPackgeFragment;
        }

    };

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title="";
        switch (position){
            case 0:
                title = "Gói đang dùng";
                break;
            case 1:
                title = "Tất cả";
                break;
            default:
                title = "Gói đang dùng";
        }
        return title;
    }
}
