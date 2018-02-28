package com.shanshui.smartcommunity.android;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.baoyachi.stepview.HorizontalStepView;
import com.ramotion.foldingcell.FoldingCell;

import java.util.HashSet;
import java.util.List;

/**
 * Simple example of ListAdapter for using with Folding Cell
 * Adapter holds indexes of unfolded elements for correct work with default reusable views behavior
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public class VoteCardListAdapter extends ArrayAdapter<VoteItem> {

    private HashSet<Integer> unfoldedIndexes = new HashSet<>();
    private View.OnClickListener defaultRequestBtnClickListener;

    public VoteCardListAdapter(Context context, List<VoteItem> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        // get item for selected view
        VoteItem item = getItem(position);
        // if cell is exists - reuse it, if not - create the new one from resource
        FoldingCell cell = (FoldingCell) convertView;
        ViewHolder viewHolder;
        if (cell == null) {
            viewHolder = new ViewHolder();
            LayoutInflater vi = LayoutInflater.from(getContext());
            cell = (FoldingCell) vi.inflate(R.layout.cell_vote, parent, false);
            cell.setTag(viewHolder);
        } else {
            // for existing cell set valid valid state(without animation)
            if (unfoldedIndexes.contains(position)) {
                cell.unfold(true);
            } else {
                cell.fold(true);
            }
            viewHolder = (ViewHolder) cell.getTag();
        }

        if (null == item)
            return cell;
        bind(cell, viewHolder, item);
        return cell;
    }

    private void bind(FoldingCell cell, ViewHolder viewHolder, VoteItem item) {
//        HorizontalStepView stepView = cell.findViewById(R.id.property_issue_step_view);
//
//        StepPathBuilder builder = new StepPathBuilder().steps(new String[]{"报修", "确认", "维修", "完成", "评价"});
//        switch (item.getIssueCurrentStatus()) {
//            case "已评价":
//                builder.status(new int[]{1, 1, 1, 1, 1});
//                break;
//            case "待评价":
//                builder.status(new int[]{1, 1, 1, 1, 0});
//                break;
//            case "维修中":
//                builder.status(new int[]{1, 1, 1, 0, -1});
//                break;
//            case "待维修":
//                builder.status(new int[]{1, 1, 0, -1, -1});
//                break;
//            case "待确认":
//                builder.status(new int[]{1, 0, -1, -1, -1});
//                break;
//        }
//
//        stepView.setStepViewTexts(builder.build())//总步骤
//                .setTextSize(10)//set textSize
//                .setStepsViewIndicatorCompletedLineColor(ContextCompat.getColor(getContext(), android.R.color.white))//设置StepsViewIndicator完成线的颜色
//                .setStepsViewIndicatorUnCompletedLineColor(ContextCompat.getColor(getContext(), R.color.uncompleted_text_color))//设置StepsViewIndicator未完成线的颜色
//                .setStepViewComplectedTextColor(ContextCompat.getColor(getContext(), android.R.color.white))//设置StepsView text完成线的颜色
//                .setStepViewUnComplectedTextColor(ContextCompat.getColor(getContext(), R.color.uncompleted_text_color))//设置StepsView text未完成线的颜色
//                .setStepsViewIndicatorCompleteIcon(ContextCompat.getDrawable(getContext(), R.drawable.complted))//设置StepsViewIndicator CompleteIcon
//                .setStepsViewIndicatorDefaultIcon(ContextCompat.getDrawable(getContext(), R.drawable.default_icon))//设置StepsViewIndicator DefaultIcon
//                .setStepsViewIndicatorAttentionIcon(ContextCompat.getDrawable(getContext(), R.drawable.attention));//设置StepsViewIndicator AttentionIcon

        // bind data from selected element to view through view holder
//        viewHolder.orderId.setText(item.getOrderId());
//        viewHolder.issueLocation.setText(item.getIssuePosition());
//        //viewHolder.issueCurrentStatus.setText(item.getIssueCurrentStatus());
//        viewHolder.issueReportedDate.setText(item.getIssueReportDate());
//        viewHolder.issueDescription.setText(item.getIssueDescription());
//        //viewHolder.issueDaysPassed.setText(item.getIssueDaysPassed());
//        //viewHolder.issueCategory.setText(item.getIssueCategory());
//        viewHolder.titleIssueLocation.setText(item.getIssuePosition());
//        viewHolder.titleIssueCategory.setText(item.getIssueCategory());
//        viewHolder.titleIssueDaysPassed.setText(item.getIssueDaysPassed());
//        viewHolder.titleIssueCurrentStatus.setText(item.getIssueCurrentStatus());
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

    public View.OnClickListener getDefaultRequestBtnClickListener() {
        return defaultRequestBtnClickListener;
    }

    public void setDefaultRequestBtnClickListener(View.OnClickListener defaultRequestBtnClickListener) {
        this.defaultRequestBtnClickListener = defaultRequestBtnClickListener;
    }

    // View lookup cache
    private static class ViewHolder {
    }
}
