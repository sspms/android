package com.shanshui.smartcommunity.android.adaptor;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ramotion.foldingcell.FoldingCell;
import com.shanshui.smartcommunity.android.R;
import com.shanshui.smartcommunity.android.model.Vote;
import com.shanshui.smartcommunity.android.util.LogHelper;

/**
 * Created by I336253 on 4/5/2018.
 */

public class VoteAdaptor extends PagedListAdapterBase<Vote, VoteAdaptor.ViewHolder> {
    private Context context;

    public VoteAdaptor(Context context) {
        super();
        this.context = context.getApplicationContext();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(LogHelper.TAG, "start creating vote view holder");
        FoldingCell cell = (FoldingCell) LayoutInflater.from(context).inflate(R.layout.cell_vote, parent, false);
        Log.d(LogHelper.TAG, "start creating vote view holder - inflate done");
        ViewHolder vh = new ViewHolder(cell);
        Log.d(LogHelper.TAG, "end creating vote view holder");
        return vh;
    }

    public static class ViewHolder extends FoldingCellViewHolder<Vote> {
        private FoldingCell foldingCell;

        public ViewHolder(View view) {
            super(view);
            if (view instanceof FoldingCell) {
                foldingCell = FoldingCell.class.cast(view);
            }
        }

        @Override
        public FoldingCell getFoldingCell() {
            return this.foldingCell;
        }

        @Override
        void bindTo(Vote instance, int position) {

        }

        @Override
        void clear() {

        }
    }
}
