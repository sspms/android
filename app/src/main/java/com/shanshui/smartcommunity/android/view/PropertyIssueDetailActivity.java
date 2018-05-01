package com.shanshui.smartcommunity.android.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.paging.PagedList;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.baoyachi.stepview.HorizontalStepView;
import com.shanshui.smartcommunity.android.R;
import com.shanshui.smartcommunity.android.StepPathBuilder;
import com.shanshui.smartcommunity.android.adaptor.PropertyIssueCommentAdaptor;
import com.shanshui.smartcommunity.android.databinding.ActivityPropertyIssueDetailBinding;
import com.shanshui.smartcommunity.android.model.PropertyIssue;
import com.shanshui.smartcommunity.android.model.PropertyIssueComment;
import com.shanshui.smartcommunity.android.repository.DatabaseRepo;
import com.shanshui.smartcommunity.android.util.LogHelper;
import com.shanshui.smartcommunity.android.viewmodel.PropertyIssueCommentViewModel;
import com.shanshui.smartcommunity.android.viewmodel.PropertyIssueDetailViewModel;

public class PropertyIssueDetailActivity extends AppCompatActivity {
    public static final String EXTRA_ISSUE_ID =
            "com.shanshui.smartcommunity.android.view.PropertyIssueDetailActivity.issue_id";
    public static final String EXTRA_ISSUE_STATUS =
            "com.shanshui.smartcommunity.android.view.PropertyIssueDetailActivity.issue_status";

    private long issueId;
    private String issueStatus;
    private PropertyIssue issue;
    private PropertyIssueDetailViewModel viewModel;
    private PropertyIssueCommentViewModel commentViewModel;
    private RecyclerView recyclerView;
    HorizontalStepView stepView;
    private TextView title;
    private TextView location;
    private TextView category;
    private TextView description;

    public static Intent newIntent(Context context, long issueId, String status) {
        Intent intent = new Intent(context, PropertyIssueDetailActivity.class);
        intent.putExtra(EXTRA_ISSUE_ID, issueId);
        intent.putExtra(EXTRA_ISSUE_STATUS, status);

        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActivityPropertyIssueDetailBinding binding = DataBindingUtil.setContentView(this,
                R.layout.activity_property_issue_detail);
        issueId = getIntent().getLongExtra(EXTRA_ISSUE_ID, 0L);
        issueStatus = getIntent().getStringExtra(EXTRA_ISSUE_STATUS);
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                issue = DatabaseRepo.newInstance(PropertyIssueDetailActivity.this).propertyIssueDao().find(issueId);
                binding.setViewModel(issue);
                return null;
            }
        }.execute();

        this.title = findViewById(R.id.property_issue_title);
        this.description = findViewById(R.id.property_issue_description);
        this.category = findViewById(R.id.title_property_issue_category);
        this.location = findViewById(R.id.property_issue_loc);

        this.viewModel = ViewModelProviders.of(this).get(PropertyIssueDetailViewModel.class);
        this.viewModel.setup(issueId, DatabaseRepo.newInstance(this).propertyIssueDao());
        this.viewModel.getData().observe(this, new Observer<PropertyIssue>() {
            @Override
            public void onChanged(@Nullable PropertyIssue propertyIssue) {
                title.setText(propertyIssue.getTitle());
                location.setText(propertyIssue.getLocation());
                category.setText(propertyIssue.getCategory());
                description.setText(propertyIssue.getDescription());
                String issueStatus = propertyIssue.getStatus();
                StepPathBuilder builder = new StepPathBuilder().steps(PropertyIssue.STEPS);
                switch (issueStatus) {
                    case "评价":
                        builder = builder.status(new int[]{1, 1, 1, 1, 1});
                        break;
                    case "验收":
                        builder = builder.status(new int[]{1, 1, 1, 1, 0});
                        break;
                    case "维修":
                        builder = builder.status(new int[]{1, 1, 1, 0, -1});
                        break;
                    case "确认":
                        builder = builder.status(new int[]{1, 1, 0, -1, -1});
                        break;
                    case "报修":
                        builder = builder.status(new int[]{1, 0, -1, -1, -1});
                        break;
                }
                stepView.setStepViewTexts(builder.build());
                //stepView.ondrawIndicator();
                // update UI
            }
        });
        //binding.setViewModel(this.viewModel);
        stepView = findViewById(R.id.property_issue_step_view);

        stepView.setTextSize(10)//set textSize
                .setStepsViewIndicatorCompletedLineColor(ContextCompat.getColor(this, android.R.color.white))//设置StepsViewIndicator完成线的颜色
                .setStepsViewIndicatorUnCompletedLineColor(ContextCompat.getColor(this, R.color.uncompleted_text_color))//设置StepsViewIndicator未完成线的颜色
                .setStepViewComplectedTextColor(ContextCompat.getColor(this, android.R.color.white))//设置StepsView text完成线的颜色
                .setStepViewUnComplectedTextColor(ContextCompat.getColor(this, R.color.uncompleted_text_color))//设置StepsView text未完成线的颜色
                .setStepsViewIndicatorCompleteIcon(ContextCompat.getDrawable(this, R.drawable.complted))//设置StepsViewIndicator CompleteIcon
                .setStepsViewIndicatorDefaultIcon(ContextCompat.getDrawable(this, R.drawable.default_icon))//设置StepsViewIndicator DefaultIcon
                .setStepsViewIndicatorAttentionIcon(ContextCompat.getDrawable(this, R.drawable.attention));

        StepPathBuilder builder = new StepPathBuilder().steps(PropertyIssue.STEPS);

        switch (issueStatus) {
            case "评价":
                builder = builder.status(new int[]{1, 1, 1, 1, 1});
                break;
            case "验收":
                builder = builder.status(new int[]{1, 1, 1, 1, 0});
                break;
            case "维修":
                builder = builder.status(new int[]{1, 1, 1, 0, -1});
                break;
            case "确认":
                builder = builder.status(new int[]{1, 1, 0, -1, -1});
                break;
            case "报修":
                builder = builder.status(new int[]{1, 0, -1, -1, -1});
                break;
        }

        stepView.setStepViewTexts(builder.build());
        final PropertyIssueCommentAdaptor adaptor = new PropertyIssueCommentAdaptor(this);
        this.recyclerView = findViewById(R.id.property_issue_comments);
        this.commentViewModel = ViewModelProviders.of(this).get(PropertyIssueCommentViewModel.class);
        this.commentViewModel.setup(issueId, DatabaseRepo.newInstance(this).propertyIssueCommentDao());
        this.commentViewModel.getData().observe(this, new Observer<PagedList<PropertyIssueComment>>() {
            @Override
            public void onChanged(@Nullable PagedList<PropertyIssueComment> propertyIssueComments) {
                adaptor.submitList(propertyIssueComments);
                adaptor.notifyDataSetChanged();
            }
        });
        LinearLayoutManager llm = new LinearLayoutManager(this);
        //llm.setSmoothScrollbarEnabled(true);
        llm.setItemPrefetchEnabled(true);// allow prefetch for better ux
        recyclerView.setLayoutManager(llm);
        this.recyclerView.setAdapter(adaptor);
        ImageView back = findViewById(R.id.back_entry);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PropertyIssueDetailActivity.this.finish();
            }
        });
        final ImageView menu = findViewById(R.id.menu_entry);
        menu.setOnClickListener(new View.OnClickListener() {

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
                                        int count = DatabaseRepo.newInstance(PropertyIssueDetailActivity.this).propertyIssueDao().update(propertyIssue);
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
                                                    int count = DatabaseRepo.newInstance(PropertyIssueDetailActivity.this).propertyIssueDao().update(propertyIssue);
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
                                                    int count = DatabaseRepo.newInstance(PropertyIssueDetailActivity.this).propertyIssueDao().update(propertyIssue);
                                                    Log.d(LogHelper.TAG, "update db done, updated row " + count);
                                                    return null;
                                                }
                                            }.execute();
                                        }
                                    }
                                })};
                PropertyIssuePopupMenu piMenu = new PropertyIssuePopupMenu(PropertyIssueDetailActivity.this, menu, items);

                piMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        int i = item.getItemId();

                        items[i - 1].getOnClick().OnClick(issue);
                        Log.d(LogHelper.TAG, "menu clicked on " + i);
                        Toast.makeText(PropertyIssueDetailActivity.this, "menu click on" + item.getTitle(), Toast.LENGTH_SHORT).show();
                        //startMenu(menuList.get(i - 1));
                        return true;
                    }
                });
                piMenu.show();
            }
        });
    }

    private void bind(PropertyIssue issue) {

    }
}
