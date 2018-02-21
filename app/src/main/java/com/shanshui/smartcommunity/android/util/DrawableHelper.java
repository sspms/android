package com.shanshui.smartcommunity.android.util;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.widget.TextView;

/**
 * Created by I336253 on 2/20/2018.
 */

public class DrawableHelper {
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
}
