package com.shanshui.smartcommunity.android.viewmodel;

import android.arch.paging.LivePagedListBuilder;

import com.shanshui.smartcommunity.android.dao.TopicDao;
import com.shanshui.smartcommunity.android.model.Topic;

/**
 * Created by I336253 on 4/5/2018.
 */

public class TopicViewModel extends ViewModelBase<Topic> {
    public void setup(TopicDao dao) {
        data = new LivePagedListBuilder<>(dao.pagingAll(), 15).build();
    }
}
