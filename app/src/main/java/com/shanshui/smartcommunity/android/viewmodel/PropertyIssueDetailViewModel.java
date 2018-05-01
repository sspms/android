package com.shanshui.smartcommunity.android.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.shanshui.smartcommunity.android.dao.PropertyIssueDao;
import com.shanshui.smartcommunity.android.model.PropertyIssue;

/**
 * Created by I336253 on 4/15/2018.
 */

public class PropertyIssueDetailViewModel extends ViewModel {
    private LiveData<PropertyIssue> data;

    public void setup(final long id, PropertyIssueDao dao) {
        this.data = dao.findLiveData(id);
    }

    public LiveData<PropertyIssue> getData() {
        return this.data;
    }
}
