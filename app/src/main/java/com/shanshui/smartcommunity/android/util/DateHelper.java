package com.shanshui.smartcommunity.android.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by I336253 on 4/5/2018.
 */

public class DateHelper {
    public static String toChineseTimeZone(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        return sdf.format(date);
    }

    public static String diff(Date one, Date two) {
        String unit = "天前";
        int dd = diffDay(one, two);

        if (0 >= dd) {
            dd = diffHour(one, two);
            unit = "今天";
            if (0 >= dd) {
                return "刚刚";
            } else {
                return unit;
            }
        } else {
            return dd + unit;
        }
    }

    public static int diffDay(Date one, Date two) {
        return (int) (two.getTime() - one.getTime()) / (1000 * 60 * 60 * 24);
    }

    public static int diffHour(Date one, Date two) {
        return (int) (two.getTime() - one.getTime()) / (1000 * 60 * 60);
    }
}
