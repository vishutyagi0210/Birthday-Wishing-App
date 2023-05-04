package com.example.tannubirthday;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class ViewPagerMessageAdapter extends FragmentPagerAdapter {

    public ViewPagerMessageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
            return new FragmentPersonalMessage();
    }

    @Override
    public int getCount() {
        return 1;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return "My Personal Message for you";
    }
}
