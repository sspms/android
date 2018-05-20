package com.shanshui.smartcommunity.android.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableArrayList;

import com.shanshui.smartcommunity.android.R;
import com.shanshui.smartcommunity.android.model.PropertyIssuePhoto;

/**
 * Created by I336253 on 5/20/2018.
 */

public class PropertyIssuePhotoListViewModel extends ViewModel {
    private final ObservableArrayList<PropertyIssuePhoto> items;

    public PropertyIssuePhotoListViewModel() {
        items = new ObservableArrayList();
        items.add(new PropertyIssuePhoto(null, R.drawable.add));
    }

    public void addItem(PropertyIssuePhoto photo) {
        items.add(photo);
    }

    public ObservableArrayList<PropertyIssuePhoto> getData() {
        return items;
    }
}
