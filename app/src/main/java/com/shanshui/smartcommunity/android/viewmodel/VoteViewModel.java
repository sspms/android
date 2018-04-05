package com.shanshui.smartcommunity.android.viewmodel;

import android.arch.paging.LivePagedListBuilder;

import com.shanshui.smartcommunity.android.dao.VoteDao;
import com.shanshui.smartcommunity.android.model.Vote;

/**
 * Created by I336253 on 4/5/2018.
 */

public class VoteViewModel extends ViewModelBase<Vote> {
    public void setup(VoteDao dao) {
        data = new LivePagedListBuilder<>(dao.pagingAll(), 15).build();
    }
}
