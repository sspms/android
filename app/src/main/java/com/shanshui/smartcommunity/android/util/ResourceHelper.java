package com.shanshui.smartcommunity.android.util;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.shanshui.smartcommunity.android.R;

/**
 * Created by I336253 on 2/20/2018.
 */

public class ResourceHelper {

    public static void setImageViewTint(Context context, ImageView imageView, int colorRes) {
        imageView.setImageTintList(ColorStateList.valueOf(ContextCompat.getColor(context, colorRes)));
    }

    public static void setTextViewDrawableColor(TextView textView, int color) {
        for (Drawable drawable : textView.getCompoundDrawables()) {
            if (drawable != null) {
                drawable.setTint(color);
                //drawable.setColorFilter(new PorterDuffColorFilter(getColor(color), PorterDuff.Mode.SRC_IN));
            }
        }
    }

    public static void setTextViewColor(TextView view, int color) {
        setTextViewDrawableColor(view, color);
        view.setTextColor(color);
    }

    public static void setBackgroundColor(View view, int color) {
        if (view.getBackground() != null) {
            Drawable shape = view.getBackground();
            if (shape instanceof GradientDrawable) {
                GradientDrawable.class.cast(shape).setColor(color);
            }
        } else {
            view.setBackgroundColor(color);
        }
    }

    public static int getInt(Context context, int resId) {
        return context.getResources().getInteger(resId);
    }
}
