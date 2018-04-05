package com.shanshui.smartcommunity.android.adaptor;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.baoyachi.stepview.HorizontalStepView;
import com.ramotion.foldingcell.FoldingCell;
import com.shanshui.smartcommunity.android.R;
import com.shanshui.smartcommunity.android.StepPathBuilder;
import com.shanshui.smartcommunity.android.model.PropertyIssue;
import com.shanshui.smartcommunity.android.util.DateHelper;

import java.util.Date;

/**
 * Created by I336253 on 3/26/2018.
 */

public class PropertyIssueAdaptor extends PagedListAdapterBase<PropertyIssue, PropertyIssueAdaptor.ViewHolder> {
    private Context context;

    public PropertyIssueAdaptor(Context context) {
        super();
        this.context = context.getApplicationContext();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        FoldingCell cell = (FoldingCell) LayoutInflater.from(context).inflate(R.layout.cell, parent, false);

        return new ViewHolder(this.context, cell);
    }

    public static class ViewHolder extends FoldingCellViewHolder<PropertyIssue> {
        private FoldingCell foldingCell;
        private HorizontalStepView stepView;
        private TextView orderId;
        private TextView issueLocation;
        private TextView issueReportedDate;
        private TextView issueDescription;
        private TextView titleIssueCategory;
        private TextView titleIssueDaysPassed;
        private TextView titleIssueCurrentStatus;
        private TextView titleIssueLocation;

        public ViewHolder(Context context, View itemView) {
            super(itemView);
            if (itemView instanceof FoldingCell) {

                foldingCell = FoldingCell.class.cast(itemView);
                stepView = foldingCell.findViewById(R.id.property_issue_step_view);

                stepView.setTextSize(10)//set textSize
                        .setStepsViewIndicatorCompletedLineColor(ContextCompat.getColor(context, android.R.color.white))//设置StepsViewIndicator完成线的颜色
                        .setStepsViewIndicatorUnCompletedLineColor(ContextCompat.getColor(context, R.color.uncompleted_text_color))//设置StepsViewIndicator未完成线的颜色
                        .setStepViewComplectedTextColor(ContextCompat.getColor(context, android.R.color.white))//设置StepsView text完成线的颜色
                        .setStepViewUnComplectedTextColor(ContextCompat.getColor(context, R.color.uncompleted_text_color))//设置StepsView text未完成线的颜色
                        .setStepsViewIndicatorCompleteIcon(ContextCompat.getDrawable(context, R.drawable.complted))//设置StepsViewIndicator CompleteIcon
                        .setStepsViewIndicatorDefaultIcon(ContextCompat.getDrawable(context, R.drawable.default_icon))//设置StepsViewIndicator DefaultIcon
                        .setStepsViewIndicatorAttentionIcon(ContextCompat.getDrawable(context, R.drawable.attention));//设置StepsViewIndicator AttentionIcon

                orderId = foldingCell.findViewById(R.id.property_issue_id);
                issueLocation = foldingCell.findViewById(R.id.title_property_issue_loc);
                issueReportedDate = foldingCell.findViewById(R.id.title_property_issue_days_passed);
                issueDescription = foldingCell.findViewById(R.id.property_issue_description);
                titleIssueCategory = foldingCell.findViewById(R.id.title_property_issue_category);
                titleIssueDaysPassed = foldingCell.findViewById(R.id.title_property_issue_days_passed);
                titleIssueCurrentStatus = foldingCell.findViewById(R.id.title_property_issue_status);
                titleIssueLocation = foldingCell.findViewById(R.id.title_property_issue_loc);
            }
        }

        @Override
        public FoldingCell getFoldingCell() {
            return foldingCell;
        }

        @Override
        void bindTo(PropertyIssue instance, int position) {
            StepPathBuilder builder = new StepPathBuilder().steps(new String[]{"报修", "确认", "维修", "完成", "评价"});

            switch (instance.getStatus()) {
                case "已评价":
                    builder.status(new int[]{1, 1, 1, 1, 1});
                    break;
                case "待评价":
                    builder.status(new int[]{1, 1, 1, 1, 0});
                    break;
                case "维修中":
                    builder.status(new int[]{1, 1, 1, 0, -1});
                    break;
                case "待维修":
                    builder.status(new int[]{1, 1, 0, -1, -1});
                    break;
                case "待确认":
                    builder.status(new int[]{1, 0, -1, -1, -1});
                    break;
            }
            stepView.setStepViewTexts(builder.build());
            orderId.setText(String.valueOf(instance.getId()));
            issueLocation.setText(instance.getLocation());
            //viewHolder.issueCurrentStatus.setText(item.getIssueCurrentStatus());
            if (instance.getOpenDate() != null) {
                issueReportedDate.setText(DateHelper.diff(instance.getOpenDate(), new Date()));
            }
            issueDescription.setText(instance.getDescription());
            //viewHolder.issueDaysPassed.setText(item.getIssueDaysPassed());
            //viewHolder.issueCategory.setText(item.getIssueCategory());
            titleIssueLocation.setText(instance.getLocation());
            titleIssueCategory.setText(instance.getCategory());
            if (instance.getOpenDate() != null) {
                titleIssueDaysPassed.setText(DateHelper.diff(instance.getOpenDate(), new Date()));
            }
            titleIssueCurrentStatus.setText(instance.getStatus());
        }

        @Override
        void clear() {

        }
    }
}
