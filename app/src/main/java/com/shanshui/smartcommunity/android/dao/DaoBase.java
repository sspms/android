package com.shanshui.smartcommunity.android.dao;

import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Update;

import com.shanshui.smartcommunity.android.model.Roomable;

/**
 * Created by I336253 on 3/25/2018.
 */

public interface DaoBase<R extends Roomable> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(R... instances);

    @Update
    int update(R... instances);

    @Delete
    int delete(R... instances);
}
