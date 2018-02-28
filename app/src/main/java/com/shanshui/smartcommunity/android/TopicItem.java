package com.shanshui.smartcommunity.android;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by I336253 on 2/24/2018.
 */

public class TopicItem {
    private String title;
    private String author;
    private String date;
    private String viewed;
    private String comments;
    private String watch;

    public TopicItem(String title, String author, String date, String viewed, String comments, String watch) {
        this.title = title;
        this.author = author;
        this.date = date;
        this.viewed = viewed;
        this.comments = comments;
        this.watch = watch;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getDate() {
        return date;
    }

    public String getViewed() {
        return viewed;
    }

    public String getComments() {
        return comments;
    }

    public String getWatch() {
        return watch;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TopicItem topicItem = (TopicItem) o;

        if (!title.equals(topicItem.title)) return false;
        if (!author.equals(topicItem.author)) return false;
        if (!date.equals(topicItem.date)) return false;
        if (viewed != null ? !viewed.equals(topicItem.viewed) : topicItem.viewed != null)
            return false;
        if (comments != null ? !comments.equals(topicItem.comments) : topicItem.comments != null)
            return false;
        return watch != null ? watch.equals(topicItem.watch) : topicItem.watch == null;
    }

    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + author.hashCode();
        result = 31 * result + date.hashCode();
        result = 31 * result + (viewed != null ? viewed.hashCode() : 0);
        result = 31 * result + (comments != null ? comments.hashCode() : 0);
        result = 31 * result + (watch != null ? watch.hashCode() : 0);
        return result;
    }

    public static List<TopicItem> mock() {
        List<TopicItem> topics = new ArrayList();
        topics.add(new TopicItem("一张图，让我对哈登的季后赛表现隐隐担忧", "麦没伤 ", "2018-02-24", "14031", "68", "32"));
        topics.add(new TopicItem("从错失小卡，谈谈独行侠眼里的卡佩拉", "永远的火迷", "2018-02-24", "45332", "122", "143"));
        topics.add(new TopicItem("火箭十一连胜，哈登31分9助攻，保罗14分10板8助攻，卡皇25分11板，JJ10分，完美！", "我是祁同伟  ", "2018-02-24", "2345", "198", "6"));
        topics.add(new TopicItem("最近的比赛火箭似乎增加了卡佩拉弧顶持球两翼无球掩护的套路", "noman ", "2018-02-24", "209881", "3568", "1232"));
        topics.add(new TopicItem("今天比赛的一个细节彰显保罗的人品", " 听雨1983 ", "2018-02-24", "932", "6", "0"));
        return topics;
    }
}
