package com.shanshui.smartcommunity.android.repository;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverter;
import android.arch.persistence.room.TypeConverters;

import com.shanshui.smartcommunity.android.dao.CommunityDao;
import com.shanshui.smartcommunity.android.dao.PropertyIssueCommentDao;
import com.shanshui.smartcommunity.android.dao.PropertyIssueDao;
import com.shanshui.smartcommunity.android.dao.TopicDao;
import com.shanshui.smartcommunity.android.dao.UserDao;
import com.shanshui.smartcommunity.android.dao.VoteDao;
import com.shanshui.smartcommunity.android.model.Community;
import com.shanshui.smartcommunity.android.model.PropertyIssue;
import com.shanshui.smartcommunity.android.model.PropertyIssueComment;
import com.shanshui.smartcommunity.android.model.Topic;
import com.shanshui.smartcommunity.android.model.User;
import com.shanshui.smartcommunity.android.model.Vote;

import java.util.Date;

/**
 * Room database.
 */
@Database(version = 1, entities = {PropertyIssue.class, Community.class, User.class, Vote.class,
        Topic.class, PropertyIssueComment.class})
@TypeConverters(AppDatabase.DateConverter.class)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();

    public abstract CommunityDao communityDao();

    public abstract PropertyIssueDao propertyIssueDao();

    public abstract PropertyIssueCommentDao propertyIssueCommentDao();

    public abstract VoteDao voteDao();

    public abstract TopicDao topicDao();


    public static class DateConverter {
        @TypeConverter
        public static Date stringToDate(String dateString) {
            return dateString != null && !dateString.isEmpty() ? new Date(dateString) : null;
        }

        @TypeConverter
        public static String dateToString(Date date) {
            return date == null ? "" : date.toString();
        }
    }
}
