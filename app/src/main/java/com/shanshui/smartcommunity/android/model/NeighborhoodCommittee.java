package com.shanshui.smartcommunity.android.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Neighborhood committee class
 */

@Entity
public class NeighborhoodCommittee implements Roomable {

    public NeighborhoodCommittee(long id, String location) {
        this.id = id;
        this.location = location;
    }

    @PrimaryKey
    @NonNull
    private long id;
    private String location;

    @Override
    public long getId() {
        return id;
    }

    public String getLocation() {
        return location;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
