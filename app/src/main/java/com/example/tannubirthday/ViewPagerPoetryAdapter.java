package com.example.tannubirthday;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class ViewPagerPoetryAdapter extends FragmentPagerAdapter {

    public ViewPagerPoetryAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if(position == 0){
            return new FragmentPoetry1();
        }
        else if(position == 1){
            return new FragmentPoetry2();
        }
        else{
            return new FragmentPoetry3();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if(position == 0){
            return "Poetry_1";
        }
        else if(position == 1){
            return "Poetry_2";
        }
        else{
            return "Poetry_3";
        }
    }
}
