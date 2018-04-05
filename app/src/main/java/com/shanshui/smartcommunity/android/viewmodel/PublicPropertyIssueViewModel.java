package com.shanshui.smartcommunity.android.viewmodel;

import android.arch.paging.LivePagedListBuilder;

import com.shanshui.smartcommunity.android.dao.PropertyIssueDao;
import com.shanshui.smartcommunity.android.model.PropertyIssue;

/**
 * view model, used by {@link com.shanshui.smartcommunity.android.view.MyPropertyIssueFragment}.
 */

public class PublicPropertyIssueViewModel extends ViewModelBase<PropertyIssue> {
    public void setup(PropertyIssueDao dao) {
        data = new LivePagedListBuilder<>(dao.pagingAllByType("PUBLIC"), 15).build();
    }
}
