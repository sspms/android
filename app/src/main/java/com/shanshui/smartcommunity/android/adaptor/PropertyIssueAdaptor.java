package com.shanshui.smartcommunity.android.adaptor;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.baoyachi.stepview.HorizontalStepView;
import com.ramotion.foldingcell.FoldingCell;
import com.shanshui.smartcommunity.android.R;
import com.shanshui.smartcommunity.android.StepPathBuilder;
import com.shanshui.smartcommunity.android.model.PropertyIssue;
import com.shanshui.smartcommunity.android.repository.DatabaseRepo;
import com.shanshui.smartcommunity.android.util.DateHelper;
import com.shanshui.smartcommunity.android.util.LogHelper;
import com.shanshui.smartcommunity.android.view.PropertyIssueDetailActivity;
import com.shanshui.smartcommunity.android.view.PropertyIssuePopupMenu;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

/**
 * Created by I336253 on 3/26/2018.
 */

public class PropertyIssueAdaptor extends PagedListAdapterBase<PropertyIssue, PropertyIssueAdaptor.ViewHolder> {

    private Context context;
    private HashSet unfolded = new HashSet();

    public PropertyIssueAdaptor(Context context) {
        super();
        this.context = context.getApplicationContext();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(LogHelper.TAG, "start creating property view holder");
        FoldingCell cell = (FoldingCell) LayoutInflater.from(context).inflate(R.layout.cell, parent, false);
        Log.d(LogHelper.TAG, "start creating property view holder - inflate done");
        ViewHolder vh = new ViewHolder(this.context, cell);
        Log.d(LogHelper.TAG, "end creating property view holder");
        return vh;
    }

    @Override
    void postBinding(final ViewHolder holder, final PropertyIssue instance, final int position) {
        final FoldingCell fc = holder.getFoldingCell();

        fc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fc != null) {
                    if (unfolded.contains(position)) {
                        unfolded.remove(position);
                    }
                    fc.toggle(false);
                    if (fc.isUnfolded()) {
                        unfolded.add(position);
                    }
                }
            }
        });
//        fc.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//            @Override
//            public void onGlobalLayout() {
//                if (unfolded.contains(position)) {
//                    if (!fc.isUnfolded()) {
//                        fc.unfold(true);
//                    }
//                }
//            }
//        });
        holder.detail.setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {

                        Intent intent = PropertyIssueDetailActivity.newIntent(context, instance.getId(), instance.getStatus());
                        context.startActivity(intent);
                        Log.d(LogHelper.TAG, "property issue detail button clicked on " + instance.getId());
                    }
                }
        );
        holder.menu.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final PropertyIssuePopupMenu.MenuItem[] items = {new PropertyIssuePopupMenu.MenuItem("取消",
                        new PropertyIssuePopupMenu.OnMenuClick() {
                            @Override
                            public void OnClick(final PropertyIssue propertyIssue) {
                                propertyIssue.setOrderStatus("取消");
                                new AsyncTask<Void, Void, Void>() {

                                    @Override
                                    protected Void doInBackground(Void... voids) {
                                        int count = DatabaseRepo.newInstance(context).propertyIssueDao().update(propertyIssue);
                                        Log.d(LogHelper.TAG, "update db done, updated row " + count);
                                        return null;
                                    }
                                }.execute();
                            }
                        }),
                        new PropertyIssuePopupMenu.MenuItem("完成", // required role: PM,WORKER,REPORTER
                                new PropertyIssuePopupMenu.OnMenuClick() {
                                    @Override
                                    public void OnClick(final PropertyIssue propertyIssue) {
                                        final String next = PropertyIssue.nextStep(propertyIssue.getStatus());
                                        if (!next.equals(propertyIssue.getStatus())) {
                                            new AsyncTask<Void, Void, Void>() {

                                                @Override
                                                protected Void doInBackground(Void... voids) {
                                                    propertyIssue.setStatus(next);
                                                    int count = DatabaseRepo.newInstance(context).propertyIssueDao().update(propertyIssue);
                                                    Log.d(LogHelper.TAG, "update db done, updated row " + count);
                                                    return null;
                                                }
                                            }.execute();
                                        }
                                    }
                                }),
                        new PropertyIssuePopupMenu.MenuItem("分派", // required role: PM(property manager)
                                new PropertyIssuePopupMenu.OnMenuClick() {
                                    @Override
                                    public void OnClick(PropertyIssue propertyIssue) {
                                    }
                                }),
                        new PropertyIssuePopupMenu.MenuItem("重修", // required role: PM(property manager)
                                new PropertyIssuePopupMenu.OnMenuClick() {
                                    @Override
                                    public void OnClick(final PropertyIssue propertyIssue) {
                                        if (PropertyIssue.STEPS[2].equals(propertyIssue.getStatus())) {
                                            new AsyncTask<Void, Void, Void>() {

                                                @Override
                                                protected Void doInBackground(Void... voids) {
                                                    propertyIssue.setStatus(PropertyIssue.STEPS[0]);
                                                    int count = DatabaseRepo.newInstance(context).propertyIssueDao().update(propertyIssue);
                                                    Log.d(LogHelper.TAG, "update db done, updated row " + count);
                                                    return null;
                                                }
                                            }.execute();
                                        }
                                    }
                                })};
                PropertyIssuePopupMenu piMenu = new PropertyIssuePopupMenu(context, holder.menu, items);

                piMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        int i = item.getItemId();
                        items[i - 1].getOnClick().OnClick(instance);
                        Log.d(LogHelper.TAG, "menu clicked on " + i);
                        Toast.makeText(context, "menu click on" + item.getTitle(), Toast.LENGTH_SHORT).show();
                        //startMenu(menuList.get(i - 1));
                        return true;
                    }
                });
                piMenu.show();
            }
        });

    }

    public static class ViewHolder extends FoldingCellViewHolder<PropertyIssue> {
        private FoldingCell foldingCell;
        private HorizontalStepView stepView;
        private int stepIndex;
        private TextView orderId;
        private TextView issueLocation;
        private TextView issueReportedDate;
        private TextView issueDescription;
        private TextView titleIssueCategory;
        private TextView titleIssueDaysPassed;
        private TextView titleIssueCurrentStatus;
        private TextView titleIssueLocation;
        private ImageView menu;
        private TextView detail;
        private boolean folded = true;

        public ViewHolder(final Context context, View itemView) {
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
                        .setStepsViewIndicatorAttentionIcon(ContextCompat.getDrawable(context, R.drawable.attention));
                menu = foldingCell.findViewById(R.id.property_issue_menu);
                detail = foldingCell.findViewById(R.id.property_detail);
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
        void bindTo(final PropertyIssue instance, final int position) {
            super.bindTo(instance, position);

            StepPathBuilder builder = new StepPathBuilder().steps(PropertyIssue.STEPS);

            switch (instance.getStatus()) {
                case "评价":
                    stepIndex = 4;
                    builder = builder.status(new int[]{1, 1, 1, 1, 1});
                    break;
                case "验收":
                    stepIndex = 3;
                    builder = builder.status(new int[]{1, 1, 1, 1, 0});
                    break;
                case "维修":
                    stepIndex = 2;
                    builder = builder.status(new int[]{1, 1, 1, 0, -1});
                    break;
                case "确认":
                    stepIndex = 1;
                    builder = builder.status(new int[]{1, 1, 0, -1, -1});
                    break;
                case "报修":
                    stepIndex = 0;
                    builder = builder.status(new int[]{1, 0, -1, -1, -1});
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
