package com.shanshui.smartcommunity.android.repository;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.util.Log;

import com.shanshui.smartcommunity.android.util.LogHelper;

/**
 * Created by I336253 on 4/1/2018.
 */

public class DatabaseRepo {
    private static AppDatabase instance;

    public static AppDatabase newInstance(Context context) {
        if (instance == null) {
            synchronized (DatabaseRepo.class) {
                if (instance == null) {
                    Log.d(LogHelper.TAG, "start creating database instance");
                    instance = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "shanshui.db").build();
                    Log.d(LogHelper.TAG, "end creating database instance");
                }
            }
        }
        return instance;
    }


}
