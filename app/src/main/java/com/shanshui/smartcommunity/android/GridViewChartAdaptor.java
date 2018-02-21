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

public class GridViewChartAdaptor extends BaseAdapter {
    private Chart[] charts;
    private LayoutInflater inflater;
    private int currentPos = Integer.MIN_VALUE;

    public GridViewChartAdaptor(LayoutInflater inflator, Context context, Chart[] charts) {
        super();
        this.charts = charts;
        this.inflater = inflator;
    }

    @Override
    public int getCount() {
        return this.charts.length;
    }

    @Override
    public Object getItem(int position) {
        return this.charts[position];
    }

    @Override
    public long getItemId(int position) {
        return this.charts[position].getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (this.currentPos != position) {
            this.currentPos = position;
            ViewHolder holder;
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.grid_view_issue,
                        parent, false);
                holder = new ViewHolder();
                if (this.charts != null && this.charts.length > position) {
                    if (this.charts[position].getParent() == null) {
                        ((LinearLayout) convertView).addView(this.charts[position]);
                    }
                    //holder.text = (TextView) convertView.findViewById(R.id.item_text);
                    holder.chart = this.charts[position];
                    convertView.setTag(holder);
                }
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.chart.invalidate();
        }
        return convertView;
    }

    static class ViewHolder {
        Chart chart;
    }
}
