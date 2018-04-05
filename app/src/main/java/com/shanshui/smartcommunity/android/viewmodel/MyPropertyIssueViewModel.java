package com.shanshui.smartcommunity.android.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.arch.paging.LivePagedListBuilder;

import com.shanshui.smartcommunity.android.dao.PropertyIssueDao;
import com.shanshui.smartcommunity.android.model.PropertyIssue;

/**
 * Property issue {@link ViewModel}, used by {@link com.shanshui.smartcommunity.android.view.MyPropertyIssueFragment}.
 */

public class MyPropertyIssueViewModel extends ViewModelBase<PropertyIssue> {
    public void setup(final long id, PropertyIssueDao dao) {
        data = new LivePagedListBuilder<>(dao.pagingMine(id), 15).build();
    }
}
