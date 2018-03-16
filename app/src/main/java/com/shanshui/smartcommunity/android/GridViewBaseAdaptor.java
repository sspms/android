package com.shanshui.smartcommunity.android;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;

import com.github.mikephil.charting.charts.Chart;

/**
 * Created by I336253 on 2/18/2018.
 */

public class GridViewBaseAdaptor extends BaseAdapter {
    //private View[] charts;
    private SellingItem[] items;
    private LayoutInflater inflater;
    private int currentPos = Integer.MIN_VALUE;

    public GridViewBaseAdaptor(LayoutInflater inflator, Context context, SellingItem[] items) {
        super();
        this.items = items;
        this.inflater = inflator;
    }

    @Override
    public int getCount() {
        return this.items.length;
    }

    @Override
    public Object getItem(int position) {
        return this.items[position];
    }

    @Override
    public long getItemId(int position) {
        return this.items[position].getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (this.currentPos != position) {
            this.currentPos = position;
            ViewHolder holder;
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.dragview,
                        parent, false);
                holder = new ViewHolder();
                //holder.chart = this.charts[position];
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            //holder.chart.invalidate();
        }
        return convertView;
    }

    static class ViewHolder {
        View chart;
    }
}
