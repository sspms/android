package com.shanshui.smartcommunity.android.model;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.Date;

/**
 * property issue comment
 */
@Entity(indices = {@Index("issue")},
        foreignKeys = {@ForeignKey(entity = PropertyIssue.class, parentColumns = "id", childColumns = "issue")})
public class PropertyIssueComment implements Roomable {

    @PrimaryKey
    @NonNull
    private long id;
    @Embedded
    private User user;
    private long issue;
    private String content;
    private Date date;

    public PropertyIssueComment(long issue, long id, User user, String content, Date date) {
        this.issue = issue;
        this.id = id;
        this.user = user;
        this.content = content;
        this.date = date;
    }

    @Override
    public long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public long getIssue() {
        return issue;
    }

    public String getContent() {
        return content;
    }

    public Date getDate() {
        return date;
    }
}
