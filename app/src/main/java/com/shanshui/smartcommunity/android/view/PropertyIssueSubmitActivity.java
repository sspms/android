package com.shanshui.smartcommunity.android.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.shanshui.smartcommunity.android.R;
import com.shanshui.smartcommunity.android.adaptor.PropertyIssuePhotoAdapter;
import com.shanshui.smartcommunity.android.util.CallbackFactory;
import com.shanshui.smartcommunity.android.viewmodel.PropertyIssuePhotoListViewModel;

public class PropertyIssueSubmitActivity extends AppCompatActivity {
    private PropertyIssuePhotoListViewModel photoListViewModel;

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, PropertyIssueSubmitActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_issue_submit);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RecyclerView recyclerView = findViewById(R.id.picture_list);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        photoListViewModel = new PropertyIssuePhotoListViewModel();
        final PropertyIssuePhotoAdapter adapter = new PropertyIssuePhotoAdapter(this, photoListViewModel.getData());
        recyclerView.setAdapter(adapter);
        this.photoListViewModel.getData().addOnListChangedCallback(CallbackFactory.getListChangedCallback(adapter));
        //adapter.getItems().add(null);
    }

}
