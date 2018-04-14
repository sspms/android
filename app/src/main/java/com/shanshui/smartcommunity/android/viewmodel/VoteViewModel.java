package com.shanshui.smartcommunity.android.viewmodel;

import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;

import com.shanshui.smartcommunity.android.dao.VoteDao;
import com.shanshui.smartcommunity.android.model.Vote;

/**
 * Created by I336253 on 4/5/2018.
 */

public class VoteViewModel extends ViewModelBase<Vote> {
    public void setup(VoteDao dao) {

        PagedList.Config.Builder builder = new PagedList.Config.Builder();
        PagedList.Config config = builder.setPageSize(20)
                .setPrefetchDistance(6)
                .setInitialLoadSizeHint(20)
                .build();
        data = new LivePagedListBuilder<>(dao.pagingAll(), config).build();
    }
}
