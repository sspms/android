//package com.shanshui.smartcommunity.android;
//
//import android.content.Context;
//import android.support.annotation.NonNull;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ArrayAdapter;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.ramotion.foldingcell.FoldingCell;
//
//import java.util.HashSet;
//import java.util.List;
//
///**
// * Simple example of ListAdapter for using with Folding Cell
// * Adapter holds indexes of unfolded elements for correct work with default reusable views behavior
// */
//@SuppressWarnings({"WeakerAccess", "unused"})
//public class VegetableListAdapter extends ArrayAdapter<SellingItem> {
//
//    private HashSet<Integer> unfoldedIndexes = new HashSet<>();
//    private View.OnClickListener defaultRequestBtnClickListener;
//
//    public VegetableListAdapter(Context context, List<SellingItem> objects) {
//        super(context, 0, objects);
//    }
//
//    @NonNull
//    @Override
//    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
//        // get item for selected view
//        SellingItem item = getItem(position);
//        // if cell is exists - reuse it, if not - create the new one from resource
//        FoldingCell cell = (FoldingCell) convertView;
//        ViewHolder viewHolder;
//        if (cell == null) {
//            LayoutInflater vi = LayoutInflater.from(getContext());
//            cell = (FoldingCell) vi.inflate(R.layout.cell_veg, parent, false);
//            viewHolder = new ViewHolder();
//            viewHolder.vegName = cell.findViewById(R.id.veg_name);
//            viewHolder.vegContentName = cell.findViewById(R.id.veg_content_name);
//            viewHolder.vegPic = cell.findViewById(R.id.vg_pic);
//            viewHolder.vegContentPic = cell.findViewById(R.id.vg_content_pic);
//            viewHolder.vegPriceInt = cell.findViewById(R.id.veg_price_before_zero);
//            viewHolder.vegContentPriceInt = cell.findViewById(R.id.veg_content_price_before_zero);
//            viewHolder.vegPriceDec = cell.findViewById(R.id.veg_price_after_zero);
//            viewHolder.vegContentPriceDec = cell.findViewById(R.id.veg_content_price_after_zero);
//            cell.setTag(viewHolder);
//        } else {
//            // for existing cell set valid valid state(without animation)
//            if (unfoldedIndexes.contains(position)) {
//                cell.unfold(true);
//            } else {
//                cell.fold(true);
//            }
//            viewHolder = (ViewHolder) cell.getTag();
//        }
//
//        if (null == item)
//            return cell;
//        bind(cell, viewHolder, item);
//        return cell;
//    }
//
//    private void bind(FoldingCell cell, ViewHolder viewHolder, SellingItem item) {
//        // bind data from selected element to view through view holder
//        viewHolder.vegName.setText(item.getName());
//        viewHolder.vegContentName.setText(item.getName());
//        viewHolder.vegPic.setImageResource(item.getImage());
//        viewHolder.vegContentPic.setImageResource(item.getImage());
//        Double price = item.getPrice();
//        viewHolder.vegPriceInt.setText(String.valueOf(price.intValue()));
//        viewHolder.vegContentPriceInt.setText(String.valueOf(price.intValue()));
//        viewHolder.vegPriceDec.setText(String.valueOf(price.floatValue()));
//        viewHolder.vegContentPriceDec.setText(String.valueOf(price.floatValue()));
//    }
//
//    // simple methods for register cell state changes
//    public void registerToggle(int position) {
//        if (unfoldedIndexes.contains(position))
//            registerFold(position);
//        else
//            registerUnfold(position);
//    }
//
//    public void registerFold(int position) {
//        unfoldedIndexes.remove(position);
//    }
//
//    public void registerUnfold(int position) {
//        unfoldedIndexes.add(position);
//    }
//
//    public View.OnClickListener getDefaultRequestBtnClickListener() {
//        return defaultRequestBtnClickListener;
//    }
//
//    public void setDefaultRequestBtnClickListener(View.OnClickListener defaultRequestBtnClickListener) {
//        this.defaultRequestBtnClickListener = defaultRequestBtnClickListener;
//    }
//
//    // View lookup cache
//    private static class ViewHolder {
//        TextView vegName;
//        TextView vegContentName;
//        ImageView vegPic;
//        ImageView vegContentPic;
//        TextView vegPriceInt;
//        TextView vegContentPriceInt;
//        TextView vegPriceDec;
//        TextView vegContentPriceDec;
//    }
//}
