package com.shanshui.smartcommunity.android;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class VegetableRecyclerViewAdapter extends RecyclerView.Adapter<VegetableRecyclerViewAdapter.ViewHolder> {

    static final int TYPE_HEADER = 0;
    static final int TYPE_CELL = 1;
    private LayoutInflater inflater;
    private SellingItem[] sellingItems;

    public VegetableRecyclerViewAdapter(Context context, SellingItem[] sellingItems) {
        super();
        this.inflater = LayoutInflater.from(context);
        this.sellingItems = sellingItems;
    }

    @Override
    public void onBindViewHolder(VegetableRecyclerViewAdapter.ViewHolder viewHolder, int position) {
        if (position != 0 && sellingItems != null && sellingItems.length > position) {
            SellingItem item = this.sellingItems[position];
            //viewHolder.vegName.setText(item.getName());
            viewHolder.vegContentName.setText(item.getName());
            //viewHolder.vegPic.setImageResource(item.getImage());
            viewHolder.vegContentPic.setImageResource(item.getImage());
            Double price = item.getPrice();
            //Double decimal = price * 100 % 100;
            //viewHolder.vegPriceInt.setText(String.valueOf(price.intValue()));
            viewHolder.vegContentPriceInt.setText(String.valueOf(price));
            //viewHolder.vegPriceDec.setText(String.valueOf(decimal.intValue()));
            //viewHolder.vegContentPriceDec.setText(String.valueOf(decimal.intValue()));
        }
    }

    @Override
    public int getItemCount() {
        return sellingItems == null ? 0 : sellingItems.length;
    }

    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case 0:
                return TYPE_HEADER;
            default:
                return TYPE_CELL;
        }
    }

    @Override
    public VegetableRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;

        switch (viewType) {
            case TYPE_HEADER: {
                view = inflater.inflate(R.layout.list_item_card_big, parent, false);
                return new VegetableRecyclerViewAdapter.ViewHolder(view);
            }
            case TYPE_CELL: {
                view = inflater.inflate(R.layout.list_item_card_small, parent, false);
//                FoldingCell cell = view.findViewById(R.id.veg_item_id);
//                cell.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        FoldingCell fc = FoldingCell.class.cast(v);
//                        if (fc.isUnfolded()) {
//                            fc.fold(false);
//                        } else {
//                            fc.unfold(false);
//                        }
//                    }
//                });
                return new VegetableRecyclerViewAdapter.ViewHolder(view);
            }
        }
        return null;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        //TextView vegName;
        TextView vegContentName;
        //ImageView vegPic;
        ImageView vegContentPic;
        //TextView vegPriceInt;
        TextView vegContentPriceInt;
        //TextView vegPriceDec;
        //TextView vegContentPriceDec;

        public ViewHolder(View view) {
            super(view);
            vegContentName = view.findViewById(R.id.veg_content_name);
            //vegPic = cell.findViewById(R.id.vg_pic);
            vegContentPic = view.findViewById(R.id.vg_content_pic);
            //vegPriceInt = cell.findViewById(R.id.veg_price_before_zero);
            vegContentPriceInt = view.findViewById(R.id.veg_content_price_before_zero);
            //vegPriceDec = cell.findViewById(R.id.veg_price_after_zero);
            //vegContentPriceDec = view.findViewById(R.id.veg_content_price_after_zero);
            //View cview = view.findViewById(R.id.veg_item_id);
//            if (cview != null && cview instanceof FoldingCell) {
//                FoldingCell cell = FoldingCell.class.cast(cview);
//                //vegName = cell.findViewById(R.id.veg_name);
//
//            }
        }
    }
}