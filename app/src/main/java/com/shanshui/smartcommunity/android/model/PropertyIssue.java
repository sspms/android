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
    @ColumnInfo(name = "order_status")
    private String orderStatus;// open, canceled, re-opened, closed, pending-on-vote
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

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
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

        if (Double.compare(that.getEstimatedCost(), getEstimatedCost()) != 0) return false;
        if (getCommunity() != that.getCommunity()) return false;
        if (getCreator() != that.getCreator()) return false;
        if (getAssignee() != that.getAssignee()) return false;
        if (getOpenDate() != null ? !getOpenDate().equals(that.getOpenDate()) : that.getOpenDate() != null)
            return false;
        if (getLastUpdateDate() != null ? !getLastUpdateDate().equals(that.getLastUpdateDate()) : that.getLastUpdateDate() != null)
            return false;
        if (getCompleteDate() != null ? !getCompleteDate().equals(that.getCompleteDate()) : that.getCompleteDate() != null)
            return false;
        if (!getDescription().equals(that.getDescription())) return false;
        if (getImageUrl() != null ? !getImageUrl().equals(that.getImageUrl()) : that.getImageUrl() != null)
            return false;
        if (getType() != null ? !getType().equals(that.getType()) : that.getType() != null)
            return false;
        if (!getLocation().equals(that.getLocation())) return false;
        if (!getCategory().equals(that.getCategory())) return false;
        return getStatus().equals(that.getStatus());
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = getOpenDate() != null ? getOpenDate().hashCode() : 0;
        result = 31 * result + (getLastUpdateDate() != null ? getLastUpdateDate().hashCode() : 0);
        result = 31 * result + (getCompleteDate() != null ? getCompleteDate().hashCode() : 0);
        result = 31 * result + getDescription().hashCode();
        result = 31 * result + (getImageUrl() != null ? getImageUrl().hashCode() : 0);
        result = 31 * result + getLocation().hashCode();
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
