package com.shanshui.smartcommunity.android;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

/**
 * Created by I336253 on 2/18/2018.
 */

public class GridViewColorAdaptor extends SimpleAdapter {
    private int[] backgroundColor;
    private List<? extends Map<String, ?>> data;
    private LayoutInflater inflater;
    private int currentPos = Integer.MIN_VALUE;
    private int row = 0;
    private int column = 0;

    public GridViewColorAdaptor(LayoutInflater inflator, Context context, List<? extends Map<String, ?>> data,
                                @LayoutRes int resource, String[] from, @IdRes int[] to, int[] backgroundColor) {
        super(context, data, resource, from, to);
        this.backgroundColor = backgroundColor;
        this.data = data;
        this.inflater = inflator;
        this.row = context.getResources().getInteger(R.integer.grid_view_row_num);
        this.column = context.getResources().getInteger(R.integer.grid_view_column_num);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (this.currentPos != position) {
            ViewHolder holder;
            convertView = inflater.inflate(R.layout.grid_view_item,
                    parent, false);
            holder = new ViewHolder();
            holder.text = (TextView) convertView.findViewById(R.id.item_text);
            holder.icon = (ImageView) convertView.findViewById(R.id.item_icon);
            convertView.setTag(holder);
            holder.text.setText(this.data.get(position).get("text").toString());
            holder.icon.setImageResource((Integer) this.data.get(position).get("img"));

            GradientDrawable drawable = new GradientDrawable();
            drawable.setShape(GradientDrawable.OVAL);
            drawable.setSize(16, 16);
            drawable.setStroke(1, Color.WHITE);

            drawable.setColor(ContextCompat.getColor(this.inflater.getContext(),
                    backgroundColor[position % (row * column)]));

            holder.icon.setBackground(drawable);
            this.currentPos = position;
            return convertView;
        }
        return convertView;
    }

    protected int[] getBackgroundColor() {
        return backgroundColor;
    }

    protected List<? extends Map<String, ?>> getData() {
        return data;
    }

    protected LayoutInflater getInflater() {
        return inflater;
    }

    protected int getCurrentPos() {
        return currentPos;
    }

    static class ViewHolder {
        TextView text;
        ImageView icon;
    }
}
