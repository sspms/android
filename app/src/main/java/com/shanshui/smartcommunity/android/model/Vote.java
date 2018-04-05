package com.shanshui.smartcommunity.android.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.Date;

/**
 * vote class, basically all the information at server side is available at client side.
 */
@Entity(indices = {@Index("community")},
        foreignKeys = {@ForeignKey(entity = Community.class, parentColumns = "id", childColumns = "community")})
public class Vote implements Roomable {

    @PrimaryKey
    @NonNull
    private long id;
    private long community;
    private long initiator;
    private String title;
    private String description;
    @ColumnInfo(name = "start_date")
    private Date startDate;
    @ColumnInfo(name = "due_date")
    private Date dueDate;
    @ColumnInfo(name = "total_votes")
    private int totalVotes;
    private boolean valid;
    @ColumnInfo(name = "invalid_reason")
    private String invalidReason;
    @ColumnInfo(name = "aye_count")
    private int ayeCount;
    @ColumnInfo(name = "nay_count")
    private int nayCount;
    @ColumnInfo(name = "abstain_count")
    private int abstainCount;

    public Vote(long id, long community, long initiator, String title, String description) {
        this.id = id;
        this.community = community;
        this.initiator = initiator;
        this.title = title;
        this.description = description;
    }

    @Override
    public long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public int getAyeCount() {
        return ayeCount;
    }

    public int getNayCount() {
        return nayCount;
    }

    public int getAbstainCount() {
        return abstainCount;
    }

    public long getCommunity() {
        return community;
    }

    public long getInitiator() {
        return initiator;
    }

    public String getTitle() {
        return title;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public int getTotalVotes() {
        return totalVotes;
    }

    public boolean isValid() {
        return valid;
    }

    public String getInvalidReason() {
        return invalidReason;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public void setTotalVotes(int totalVotes) {
        this.totalVotes = totalVotes;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public void setInvalidReason(String invalidReason) {
        this.invalidReason = invalidReason;
    }

    public void setAyeCount(int ayeCount) {
        this.ayeCount = ayeCount;
    }

    public void setNayCount(int nayCount) {
        this.nayCount = nayCount;
    }

    public void setAbstainCount(int abstainCount) {
        this.abstainCount = abstainCount;
    }

    public static Vote[] mock(int size) {
        if (size > 0) {
            Vote[] votes = new Vote[size];
            for (int i = 0; i < size; i++) {
                Vote vote = new Vote(1L * (i + 1), 1L, 1L, "业委会选举", "换届选举");
                vote.dueDate = new Date();
                vote.startDate = new Date();
                vote.ayeCount = 100;
                vote.nayCount = 3;
                vote.abstainCount = 40;
                vote.totalVotes = 200;
                vote.valid = true;
                vote.invalidReason = "";
                votes[i] = vote;
            }
            return votes;
        }
        return null;
    }
}
