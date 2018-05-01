package com.shanshui.smartcommunity.android.viewmodel;

import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;

import com.shanshui.smartcommunity.android.dao.PropertyIssueCommentDao;
import com.shanshui.smartcommunity.android.model.PropertyIssueComment;

/**
 * {@link PropertyIssueComment} {@link android.arch.lifecycle.ViewModel}
 */

public class PropertyIssueCommentViewModel extends ViewModelBase<PropertyIssueComment> {

    public void setup(final long id, PropertyIssueCommentDao dao) {
        PagedList.Config.Builder builder = new PagedList.Config.Builder();
        final PagedList.Config config = builder.setPageSize(20)
                .setPrefetchDistance(6)
                .setInitialLoadSizeHint(20)
                .build();
        data = new LivePagedListBuilder<>(dao.pagingAll(id), config).build();
    }

}
