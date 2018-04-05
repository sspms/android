package com.shanshui.smartcommunity.android.repository;

import android.arch.persistence.room.Room;
import android.arch.persistence.room.TypeConverter;
import android.content.Context;

import java.util.Date;

/**
 * Created by I336253 on 4/1/2018.
 */

public class DatabaseRepo {
    private static AppDatabase instance;

    public static AppDatabase newInstance(Context context) {
        if (instance == null) {
            synchronized (DatabaseRepo.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context,
                            AppDatabase.class, "shanshui.db").build();
                }
            }
        }
        return instance;
    }


}
