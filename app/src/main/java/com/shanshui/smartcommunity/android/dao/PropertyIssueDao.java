package com.shanshui.smartcommunity.android.dao;

import android.arch.lifecycle.LiveData;
import android.arch.paging.DataSource;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.shanshui.smartcommunity.android.model.PropertyIssue;

import java.util.List;


/**
 * DAO for {@link com.shanshui.smartcommunity.android.model.PropertyIssue}
 */

@Dao
public interface PropertyIssueDao extends DaoBase<PropertyIssue> {

    @Query("SELECT * FROM PropertyIssue")
    PropertyIssue[] findAll();

    @Query("SELECT * FROM PropertyIssue")
    DataSource.Factory<Integer, PropertyIssue> pagingAll();

    @Query("SELECT * FROM PropertyIssue")
    LiveData<List<PropertyIssue>> findAllLiveData();

    @Query("SELECT * FROM PropertyIssue where id = :id")
    PropertyIssue find(long id);

    @Query("SELECT * FROM PropertyIssue where id = :id")
    PropertyIssue[] find(long... id);

    @Query("select * from PropertyIssue where creator = :id")
    PropertyIssue[] findMine(long id);

    @Query("select * from PropertyIssue where creator = :id")
    LiveData<List<PropertyIssue>> findMineLiveData(long id);

    @Query("select * from PropertyIssue where creator = :id")
    DataSource.Factory<Integer, PropertyIssue> pagingMine(long id);

    @Query("select * from PropertyIssue where type = :type")
    PropertyIssue[] findAllByType(String type);

    @Query("select * from PropertyIssue where type = :type")
    LiveData<List<PropertyIssue>> findAllLiveDataByType(String type);

    @Query("select * from PropertyIssue where type = :type")
    DataSource.Factory<Integer, PropertyIssue> pagingAllByType(String type);
}
