package com.shanshui.smartcommunity.android;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class ViewPagerAdapter<V extends ViewGroup> extends PagerAdapter {
    private List<V> views;

    public ViewPagerAdapter(List<V> views) {
        this.views = views;
    }

    @Override
    public int getCount() {
        return views == null ? 0 : views.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        if (views != null && views.size() > position) {
            View theView = views.get(position);
            if (theView.getParent() == null) {
                container.addView(theView);
            }
            return theView;
        }
        return null;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((ViewGroup) object);
    }
}
