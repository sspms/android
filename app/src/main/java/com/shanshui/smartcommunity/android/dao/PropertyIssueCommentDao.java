package com.shanshui.smartcommunity.android.dao;

import android.arch.lifecycle.LiveData;
import android.arch.paging.DataSource;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.shanshui.smartcommunity.android.model.PropertyIssue;
import com.shanshui.smartcommunity.android.model.PropertyIssueComment;

import java.util.List;

/**
 * {@link android.arch.persistence.room.Dao} for {@link PropertyIssueComment}
 */
@Dao
public interface PropertyIssueCommentDao extends DaoBase<PropertyIssueComment> {
    @Query("SELECT * FROM PropertyIssueComment WHERE issue = :issue")
    PropertyIssueComment[] findAll(long issue);

    @Query("SELECT * FROM PropertyIssueComment WHERE issue = :issue")
    DataSource.Factory<Integer, PropertyIssueComment> pagingAll(long issue);

    @Query("SELECT * FROM PropertyIssueComment WHERE issue = :issue")
    LiveData<List<PropertyIssueComment>> findAllLiveData(long issue);
}
