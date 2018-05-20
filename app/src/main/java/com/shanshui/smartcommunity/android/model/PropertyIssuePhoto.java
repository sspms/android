package com.shanshui.smartcommunity.android.model;

import android.graphics.Bitmap;

/**
 * Created by I336253 on 5/20/2018.
 */

public class PropertyIssuePhoto {
    String filePath;
    int defaultResourseId;

    public PropertyIssuePhoto(String filePath, int defaultResourseId) {
        this.filePath = filePath;
        this.defaultResourseId = defaultResourseId;
    }

    public String getFilePath() {
        return filePath;
    }

    public int getDefaultResourseId() {
        return defaultResourseId;
    }

    Bitmap getCompressedBitMap() {
        return null;
    }
}
