package com.shanshui.smartcommunity.android.adaptor;

import android.arch.paging.PagedListAdapter;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.shanshui.smartcommunity.android.adaptor.PagedListAdapterBase.ViewHolderBase;
import com.shanshui.smartcommunity.android.model.Roomable;

/**
 * PageListAdapter base class for convenient encapsulation
 */

public abstract class PagedListAdapterBase<T extends Roomable, V extends ViewHolderBase<T>> extends PagedListAdapter<T, V> {

    protected PagedListAdapterBase() {
        super(new DiffUtil.ItemCallback<T>() {
            @Override
            public boolean areItemsTheSame(@NonNull T oldItem, @NonNull T newItem) {
                return oldItem.getId() == newItem.getId();
            }

            @Override
            public boolean areContentsTheSame(@NonNull T oldItem, @NonNull T newItem) {
                return oldItem.equals(newItem);
            }
        });
    }

    @Override
    public void onBindViewHolder(V holder, int position) {
        T instance = getItem(position);
        if (instance != null) {
            // pass position here for handling recycler view header and footer, usually it's not useful
            holder.bindTo(instance, position);
        } else {
            // Null defines a placeholder item - PagedListAdapter will automatically invalidate
            // this row when the actual object is loaded from the database
            holder.clear();
        }
    }

    public abstract static class ViewHolderBase<T extends Roomable> extends RecyclerView.ViewHolder {

        public ViewHolderBase(View itemView) {
            super(itemView);
        }

        abstract void bindTo(T instance, int position);

        abstract void clear();
    }
}
