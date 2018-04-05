package com.shanshui.smartcommunity.android.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.PagedList;

import com.shanshui.smartcommunity.android.model.Roomable;

/**
 * Property issue {@link ViewModel}.
 */

public abstract class ViewModelBase<RM extends Roomable> extends ViewModel {
    protected LiveData<PagedList<RM>> data;

    public LiveData<PagedList<RM>> getData() {
        return data;
    }
}
