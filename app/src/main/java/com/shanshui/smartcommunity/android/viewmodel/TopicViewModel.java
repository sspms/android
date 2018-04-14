package com.shanshui.smartcommunity.android.viewmodel;

import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;

import com.shanshui.smartcommunity.android.dao.TopicDao;
import com.shanshui.smartcommunity.android.model.Topic;

/**
 * Created by I336253 on 4/5/2018.
 */

public class TopicViewModel extends ViewModelBase<Topic> {
    public void setup(TopicDao dao) {
        PagedList.Config.Builder builder = new PagedList.Config.Builder();
        PagedList.Config config = builder.setPageSize(20)
                .setPrefetchDistance(7)
                .setInitialLoadSizeHint(20)
                .build();
        data = new LivePagedListBuilder<>(dao.pagingAll(), config).build();
    }
}
