package com.shanshui.smartcommunity.android.adaptor;

import android.view.View;

import com.ramotion.foldingcell.FoldingCell;
import com.shanshui.smartcommunity.android.model.Roomable;

/**
 * Created by I336253 on 3/27/2018.
 */

public abstract class FoldingCellViewHolder<T extends Roomable> extends PagedListAdapterBase.ViewHolderBase<T> {

    public FoldingCellViewHolder(View view) {
        super(view);
    }

    public abstract FoldingCell getFoldingCell();
}
