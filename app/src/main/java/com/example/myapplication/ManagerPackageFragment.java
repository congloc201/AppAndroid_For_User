package com.example.myapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;


public class ManagerPackageFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    TabLayout tabLayout;
    ViewPager viewPager;
    ViewPagerUsingPackageAdaper viewPagerUsingPackageAdaper;
    public ManagerPackageFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_manager_package, container, false);
        tabLayout = view.findViewById(R.id.tab_layout);
        viewPager = view.findViewById(R.id.view_pager);
        viewPagerUsingPackageAdaper = new ViewPagerUsingPackageAdaper(getChildFragmentManager());
        viewPager.setAdapter(viewPagerUsingPackageAdaper);
        tabLayout.setupWithViewPager(viewPager);
        return view;
    }
}