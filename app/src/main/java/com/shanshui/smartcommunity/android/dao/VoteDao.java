package com.shanshui.smartcommunity.android.dao;

import android.arch.lifecycle.LiveData;
import android.arch.paging.DataSource;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.shanshui.smartcommunity.android.model.PropertyIssue;
import com.shanshui.smartcommunity.android.model.Vote;

import java.util.List;

/**
 * {@link Dao} interface for class {@link Vote}.
 */
@Dao
public interface VoteDao extends DaoBase<Vote> {
    @Query("SELECT * FROM Vote")
    Vote[] findAll();

    @Query("SELECT * FROM Vote")
    DataSource.Factory<Integer, Vote> pagingAll();

    @Query("SELECT * FROM Vote")
    LiveData<List<Vote>> findAllLiveData();
}
