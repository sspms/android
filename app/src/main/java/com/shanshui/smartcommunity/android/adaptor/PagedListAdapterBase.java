package com.shanshui.smartcommunity.android.adaptor;

import android.arch.paging.PagedListAdapter;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.shanshui.smartcommunity.android.adaptor.PagedListAdapterBase.ViewHolderBase;
import com.shanshui.smartcommunity.android.model.Roomable;
import com.shanshui.smartcommunity.android.util.CallbackFactory;
import com.shanshui.smartcommunity.android.util.LogHelper;

/**
 * PageListAdapter base class for convenient encapsulation
 */

public abstract class PagedListAdapterBase<T extends Roomable, V extends ViewHolderBase<T>> extends PagedListAdapter<T, V> {

    protected PagedListAdapterBase() {
        super(CallbackFactory.getItemDiffCallback());
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).getId();
    }

    @Override
    public void onBindViewHolder(V holder, int position) {
        Log.d(LogHelper.TAG, "start binding position" + position);
        T instance = getItem(position);
        if (instance != null) {
            // pass position here for handling recycler view header and footer, usually it's not useful
            holder.bindTo(instance, position);
            postBinding(holder, instance, position);
        } else {
            // Null defines a placeholder item - PagedListAdapter will automatically invalidate
            // this row when the actual object is loaded from the database
            holder.clear();
        }
        Log.d(LogHelper.TAG, "end binding position" + position);
    }

    void postBinding(V holder, T instance, int position) {
        // NOOP
    }

    public abstract static class ViewHolderBase<T extends Roomable> extends RecyclerView.ViewHolder {
        private T instance;
        private int poistion;

        public ViewHolderBase(View itemView) {
            super(itemView);
        }

        /**
         * make sure invoke this implementation in descent's implementation to assign the value.
         *
         * @param instance
         * @param position
         */
        @CallSuper
        void bindTo(T instance, int position) {
            this.instance = instance;
            this.poistion = position;
        }

        /**
         * this will return null before you bind data to the view holder. Assign value to it while binding
         * data to UI
         *
         * @return
         */
        @Nullable
        T getItem() {
            return this.instance;
        }

        int getPoistion() {
            return this.poistion;
        }

        abstract void clear();
    }
}
