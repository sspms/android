package com.shanshui.smartcommunity.android.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;

import com.shanshui.smartcommunity.android.dao.PropertyIssueDao;
import com.shanshui.smartcommunity.android.model.PropertyIssue;

/**
 * Property issue {@link ViewModel}, used by {@link com.shanshui.smartcommunity.android.view.MyPropertyIssueFragment}.
 */

public class MyPropertyIssueViewModel extends ViewModelBase<PropertyIssue> {
    public void setup(final long id, PropertyIssueDao dao) {

        PagedList.Config.Builder builder = new PagedList.Config.Builder();
        final PagedList.Config config = builder.setPageSize(20)
                .setPrefetchDistance(6)
                .setInitialLoadSizeHint(20)
                .build();
        data = new LivePagedListBuilder<>(dao.pagingMine(id), config).build();
    }
}
