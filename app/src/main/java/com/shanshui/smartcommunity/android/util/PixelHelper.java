package com.shanshui.smartcommunity.android.util;

import android.content.Context;

/**
 * Created by I336253 on 2/19/2018.
 */

public class PixelHelper {
    public static float dpToPixels(int dp, Context context) {
        return dp * (context.getResources().getDisplayMetrics().density);
    }
}
