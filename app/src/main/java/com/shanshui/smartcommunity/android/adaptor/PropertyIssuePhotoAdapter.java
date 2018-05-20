package com.shanshui.smartcommunity.android.adaptor;

import android.content.Context;
import android.databinding.ObservableArrayList;
import android.databinding.ViewDataBinding;
import android.view.View;
import android.widget.ImageView;

import com.shanshui.smartcommunity.android.GlideApp;
import com.shanshui.smartcommunity.android.R;
import com.shanshui.smartcommunity.android.databinding.ItemPhotoAddBinding;
import com.shanshui.smartcommunity.android.databinding.ItemPhotoBinding;
import com.shanshui.smartcommunity.android.model.PropertyIssue;
import com.shanshui.smartcommunity.android.model.PropertyIssuePhoto;

/**
 * Created by I336253 on 5/20/2018.
 */

public class PropertyIssuePhotoAdapter extends RecyclerViewBindingAdapter<PropertyIssuePhoto, ItemPhotoBinding> {

    public PropertyIssuePhotoAdapter(Context context, ObservableArrayList<PropertyIssuePhoto> items) {
        super(context, items);
    }

    @Override
    protected int getLayoutResId(int viewType) {
        if (0 == viewType) {
            return R.layout.item_photo;
        } else if (1 == viewType) {
            return R.layout.item_photo_add;
        }
        return R.layout.item_photo;
    }

    public int getItemViewType(int position) {
        if (position == getItemCount() - 1) {
            return 1;
        }
        return 0;
    }

    @Override
    protected void onBindItem(ViewDataBinding binding, PropertyIssuePhoto item) {
        View view = binding.getRoot();
        ImageView imageView = view.findViewById(R.id.property_issue_phote);
        GlideApp.with(this.context)
                .load(item.getFilePath())
                .centerCrop()
                .fallback(item.getDefaultResourseId())
                .into(imageView);
        if (binding instanceof ItemPhotoBinding) {
            ((ItemPhotoBinding) binding).setPhoto(item);
        } else if (binding instanceof ItemPhotoAddBinding) {
            ((ItemPhotoAddBinding) binding).setPhoto(item);
        }
        binding.executePendingBindings();
    }
}
