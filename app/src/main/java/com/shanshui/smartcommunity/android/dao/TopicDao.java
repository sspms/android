package com.shanshui.smartcommunity.android.dao;

import android.arch.lifecycle.LiveData;
import android.arch.paging.DataSource;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.shanshui.smartcommunity.android.model.Topic;

import java.util.List;

/**
 * Created by I336253 on 4/5/2018.
 */
@Dao
public interface TopicDao extends DaoBase<Topic> {
    @Query("SELECT * FROM Topic")
    Topic[] findAll();

    @Query("SELECT * FROM Topic")
    DataSource.Factory<Integer, Topic> pagingAll();

    @Query("SELECT * FROM Topic")
    LiveData<List<Topic>> findAllLiveData();
}
