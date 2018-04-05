package com.shanshui.smartcommunity.android.adaptor;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by I336253 on 4/3/2018.
 */

public class FragmentListAdaptor extends FragmentPagerAdapter {
    private List<Fragment> mFragments;
    private String[] titles;

    public FragmentListAdaptor(FragmentManager fm, @NonNull List<Fragment> fragments, @NonNull String[] titles) {
        super(fm);
        this.mFragments = fragments;
        this.titles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        if (this.mFragments != null && position < this.mFragments.size()) {
            return this.mFragments.get(position);
        }
        return null;
    }

    @Override
    public int getCount() {
        return this.mFragments != null ? this.mFragments.size() : 0;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return position < this.titles.length ? this.titles[position] : "";
    }
}
