package com.shanshui.smartcommunity.android;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by I336253 on 2/24/2018.
 */

public class VoteItem {
    public VoteItem() {
    }

    public static List<VoteItem> mock() {
        List<VoteItem> topics = new ArrayList();
        topics.add(new VoteItem());
        topics.add(new VoteItem());
        topics.add(new VoteItem());
        return topics;
    }
}
