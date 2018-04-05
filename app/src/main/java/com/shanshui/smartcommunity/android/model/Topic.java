package com.shanshui.smartcommunity.android.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by I336253 on 2/24/2018.
 */
@Entity(indices = {@Index("community"), @Index("author")},
        foreignKeys = {@ForeignKey(entity = Community.class, parentColumns = "id", childColumns = "community")})
public class Topic implements Roomable {
    @PrimaryKey
    @NonNull
    private long id;
    private long community;
    private long author;
    private String title;
    private String date;
    private String viewed;
    private String comments;
    private String watch;

    public Topic(long id, long community, long author, String title, String date, String viewed, String comments, String watch) {
        this.id = id;
        this.community = community;
        this.title = title;
        this.author = author;
        this.date = date;
        this.viewed = viewed;
        this.comments = comments;
        this.watch = watch;
    }

    @Override
    public long getId() {
        return id;
    }

    public long getCommunity() {
        return community;
    }

    public String getTitle() {
        return title;
    }

    public long getAuthor() {
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
        if (!(o instanceof Topic)) return false;

        Topic topic = (Topic) o;

        if (getId() != topic.getId()) return false;
        if (community != topic.community) return false;
        if (getAuthor() != topic.getAuthor()) return false;
        if (!getTitle().equals(topic.getTitle())) return false;
        if (!getDate().equals(topic.getDate())) return false;
        if (!getViewed().equals(topic.getViewed())) return false;
        if (!getComments().equals(topic.getComments())) return false;
        return getWatch().equals(topic.getWatch());
    }

    @Override
    public int hashCode() {
        int result = (int) (getId() ^ (getId() >>> 32));
        result = 31 * result + (int) (community ^ (community >>> 32));
        result = 31 * result + getTitle().hashCode();
        result = 31 * result + (int) (getAuthor() ^ (getAuthor() >>> 32));
        result = 31 * result + getDate().hashCode();
        result = 31 * result + getViewed().hashCode();
        result = 31 * result + getComments().hashCode();
        result = 31 * result + getWatch().hashCode();
        return result;
    }

    public static List<Topic> mock() {
        List<Topic> topics = new ArrayList();
        topics.add(new Topic(1L, 1L, 2L, "一张图，让我对哈登的季后赛表现隐隐担忧", "2018-02-24", "14031", "68", "32"));
        topics.add(new Topic(2L, 1L, 2L, "从错失小卡，谈谈独行侠眼里的卡佩拉", "2018-02-24", "45332", "122", "143"));
        topics.add(new Topic(3L, 1L, 2L, "火箭十一连胜，哈登31分9助攻，保罗14分10板8助攻，卡皇25分11板，JJ10分，完美！", "2018-02-24", "2345", "198", "6"));
        topics.add(new Topic(4L, 1L, 2L, "最近的比赛火箭似乎增加了卡佩拉弧顶持球两翼无球掩护的套路", "2018-02-24", "209881", "3568", "1232"));
        topics.add(new Topic(5L, 1L, 2L, "今天比赛的一个细节彰显保罗的人品", "2018-02-24", "932", "6", "0"));
        topics.add(new Topic(6L, 1L, 2L, "一张图，让我对哈登的季后赛表现隐隐担忧", "2018-02-24", "14031", "68", "32"));
        topics.add(new Topic(7L, 1L, 2L, "从错失小卡，谈谈独行侠眼里的卡佩拉", "2018-02-24", "45332", "122", "143"));
        topics.add(new Topic(8L, 1L, 2L, "火箭十一连胜，哈登31分9助攻，保罗14分10板8助攻，卡皇25分11板，JJ10分，完美！", "2018-02-24", "2345", "198", "6"));
        topics.add(new Topic(9L, 1L, 2L, "最近的比赛火箭似乎增加了卡佩拉弧顶持球两翼无球掩护的套路", "2018-02-24", "209881", "3568", "1232"));
        topics.add(new Topic(10L, 1L, 2L, "今天比赛的一个细节彰显保罗的人品", "2018-02-24", "932", "6", "0"));
        topics.add(new Topic(11L, 1L, 2L, "一张图，让我对哈登的季后赛表现隐隐担忧", "2018-02-24", "14031", "68", "32"));
        topics.add(new Topic(12L, 1L, 2L, "从错失小卡，谈谈独行侠眼里的卡佩拉", "2018-02-24", "45332", "122", "143"));
        topics.add(new Topic(13L, 1L, 2L, "火箭十一连胜，哈登31分9助攻，保罗14分10板8助攻，卡皇25分11板，JJ10分，完美！", "2018-02-24", "2345", "198", "6"));
        topics.add(new Topic(14L, 1L, 2L, "最近的比赛火箭似乎增加了卡佩拉弧顶持球两翼无球掩护的套路", "2018-02-24", "209881", "3568", "1232"));
        topics.add(new Topic(15L, 1L, 2L, "今天比赛的一个细节彰显保罗的人品", "2018-02-24", "932", "6", "0"));
        topics.add(new Topic(16L, 1L, 2L, "火箭十一连胜，哈登31分9助攻，保罗14分10板8助攻，卡皇25分11板，JJ10分，完美！", "2018-02-24", "2345", "198", "6"));
        topics.add(new Topic(17L, 1L, 2L, "最近的比赛火箭似乎增加了卡佩拉弧顶持球两翼无球掩护的套路", "2018-02-24", "209881", "3568", "1232"));
        topics.add(new Topic(18L, 1L, 2L, "今天比赛的一个细节彰显保罗的人品", "2018-02-24", "932", "6", "0"));
        return topics;
    }
}
