package com.shanshui.smartcommunity.android.adaptor;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.ramotion.foldingcell.FoldingCell;
import com.shanshui.smartcommunity.android.R;
import com.shanshui.smartcommunity.android.SellingItem;

import java.util.HashSet;

/**
 * Created by I336253 on 3/4/2018.
 */

public class VegetableItemAdaptor extends RecyclerView.Adapter<VegetableItemAdaptor.ViewHolder> implements AdapterView.OnItemClickListener {
    private HashSet<Integer> unfoldedIndexes = new HashSet<>();
    private LayoutInflater inflater;
    private Context context;
    private int layoutRes;
    private SellingItem[] sellingItems;
    private AdapterView.OnItemClickListener listener;

    public VegetableItemAdaptor(Context context, int layoutRes, SellingItem[] sellingItems) {
        super();
        this.inflater = LayoutInflater.from(context);
        this.context = context;
        this.layoutRes = layoutRes;
        this.sellingItems = sellingItems;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(this.layoutRes, parent, false);
        FoldingCell fc = (FoldingCell) view;
        fc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FoldingCell fc = FoldingCell.class.cast(v);
                if (fc.isUnfolded()) {
                    fc.fold(false);
                } else {
                    fc.unfold(false);
                }
            }
        });
        ViewHolder vh = new ViewHolder(fc);

        return vh;
    }

    @Override
    public void onBindViewHolder(VegetableItemAdaptor.ViewHolder viewHolder, int position) {
        if (sellingItems != null && sellingItems.length > position) {
            SellingItem item = this.sellingItems[position];
            viewHolder.vegName.setText(item.getName());
            viewHolder.vegContentName.setText(item.getName());
            viewHolder.vegPic.setImageResource(item.getImage());
            viewHolder.vegContentPic.setImageResource(item.getImage());
            Double price = item.getPrice();
            Double decimal = price * 100 % 100;
            viewHolder.vegPriceInt.setText(String.valueOf(price.intValue()));
            viewHolder.vegContentPriceInt.setText(String.valueOf(price.intValue()));
            viewHolder.vegPriceDec.setText(String.valueOf(decimal.intValue()));
            viewHolder.vegContentPriceDec.setText(String.valueOf(decimal.intValue()));
        }
    }

    @Override
    public int getItemCount() {
        return sellingItems == null ? 0 : sellingItems.length;
    }

    // simple methods for register cell state changes
    public void registerToggle(int position) {
        if (unfoldedIndexes.contains(position))
            registerFold(position);
        else
            registerUnfold(position);
    }

    public void registerFold(int position) {
        unfoldedIndexes.remove(position);
    }

    public void registerUnfold(int position) {
        unfoldedIndexes.add(position);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        // toggle clicked cell state

        ((FoldingCell) view).toggle(false);
        // register in adapter that state for selected cell is toggled
        this.registerToggle(position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView vegName;
        TextView vegContentName;
        ImageView vegPic;
        ImageView vegContentPic;
        TextView vegPriceInt;
        TextView vegContentPriceInt;
        TextView vegPriceDec;
        TextView vegContentPriceDec;

        public ViewHolder(FoldingCell cell) {
            super(cell);
            vegName = cell.findViewById(R.id.veg_name);
            vegContentName = cell.findViewById(R.id.veg_content_name);
            vegPic = cell.findViewById(R.id.vg_pic);
            vegContentPic = cell.findViewById(R.id.vg_content_pic);
            vegPriceInt = cell.findViewById(R.id.veg_price_before_zero);
            vegContentPriceInt = cell.findViewById(R.id.veg_content_price_before_zero);
            vegPriceDec = cell.findViewById(R.id.veg_price_after_zero);
            vegContentPriceDec = cell.findViewById(R.id.veg_content_price_after_zero);
        }
    }
}
