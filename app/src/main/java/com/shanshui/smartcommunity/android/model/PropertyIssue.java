package com.shanshui.smartcommunity.android.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.Date;

/**
 * property issue class, property owner files a ticket for this kind of issue for property service
 * provider.
 */

@Entity(indices = {@Index("community"), @Index("creator"), @Index("type"), @Index("assignee")},
        foreignKeys = {@ForeignKey(entity = Community.class, parentColumns = "id", childColumns = "community")})
public class PropertyIssue implements Roomable {

    public PropertyIssue(long id, long community, long creator) {
        this.id = id;
        this.community = community;
        this.creator = creator;
    }

    @PrimaryKey
    @NonNull
    private long id;
    @ColumnInfo(name = "open_date")
    private Date openDate;
    @ColumnInfo(name = "last_update_date")
    private Date lastUpdateDate;
    @ColumnInfo(name = "complete_date")
    private Date completeDate;
    private String description;
    @ColumnInfo(name = "image_url")
    private String imageUrl;
    private String location;
    @ColumnInfo(name = "estimated_cost")
    private double estimatedCost;
    private String category;
    private String status;
    private String type;
    private long community;
    private long creator;
    private long assignee;

    public long getId() {
        return id;
    }

    public Date getOpenDate() {
        return openDate;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public Date getCompleteDate() {
        return completeDate;
    }

    public String getLocation() {
        return location;
    }

    public String getDescription() {
        return description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public double getEstimatedCost() {
        return estimatedCost;
    }

    public String getCategory() {
        return category;
    }

    public String getStatus() {
        return status;
    }

    public String getType() {
        return type;
    }

    public long getCommunity() {
        return community;
    }

    public long getCreator() {
        return creator;
    }

    public long getAssignee() {
        return assignee;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setOpenDate(Date openDate) {
        this.openDate = openDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public void setCompleteDate(Date completeDate) {
        this.completeDate = completeDate;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setEstimatedCost(double estimatedCost) {
        this.estimatedCost = estimatedCost;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setCommunity(long community) {
        this.community = community;
    }

    public void setCreator(long creator) {
        this.creator = creator;
    }

    public void setAssignee(long assignee) {
        this.assignee = assignee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PropertyIssue)) return false;

        PropertyIssue that = (PropertyIssue) o;

        if (getCompleteDate() == null && getCompleteDate() != that.getCompleteDate()) {
            return false;
        }
        if (getOpenDate() == null && getOpenDate() != that.getOpenDate()) {
            return false;
        }
        if (getId() != that.getId()) return false;
        if (Double.compare(that.getEstimatedCost(), getEstimatedCost()) != 0) return false;
        if (getCommunity() != that.getCommunity()) return false;
        if (getCreator() != that.getCreator()) return false;
        if (getAssignee() != that.getAssignee()) return false;
        if (!getOpenDate().equals(that.getOpenDate())) return false;
        if (!getLastUpdateDate().equals(that.getLastUpdateDate())) return false;
        if (!getCompleteDate().equals(that.getCompleteDate())) return false;
        if (!getDescription().equals(that.getDescription())) return false;
        if (!getImageUrl().equals(that.getImageUrl())) return false;
        if (!getCategory().equals(that.getCategory())) return false;
        if (!getStatus().equals(that.getStatus())) return false;
        return getType().equals(that.getType());
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (getId() ^ (getId() >>> 32));
        result = 31 * result + getOpenDate().hashCode();
        result = 31 * result + getLastUpdateDate().hashCode();
        result = 31 * result + getCompleteDate().hashCode();
        result = 31 * result + getDescription().hashCode();
        result = 31 * result + getImageUrl().hashCode();
        temp = Double.doubleToLongBits(getEstimatedCost());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + getCategory().hashCode();
        result = 31 * result + getStatus().hashCode();
        result = 31 * result + getType().hashCode();
        result = 31 * result + (int) (getCommunity() ^ (getCommunity() >>> 32));
        result = 31 * result + (int) (getCreator() ^ (getCreator() >>> 32));
        result = 31 * result + (int) (getAssignee() ^ (getAssignee() >>> 32));
        return result;
    }
}
