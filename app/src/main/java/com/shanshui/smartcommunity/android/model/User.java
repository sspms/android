package com.shanshui.smartcommunity.android.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.List;

/**
 * User class holding user information needed for displaying, not all information as server side.
 * Basically information here are all the public information which could be viewed by users.
 */

@Entity
public class User implements Roomable {
    public User(long id, String nickName) {
        this.id = id;
        this.nickName = nickName;
        this.role = role;
    }

    @PrimaryKey
    @NonNull
    private long id;
    @ColumnInfo(name = "profile_image_url")
    private String profileImageUrl;
    @ColumnInfo(name = "nick_name")
    private String nickName;
    @ColumnInfo(name = "bonus_points")
    private int bonusPoints;

    @Ignore
    private List<Role> role;

    @Override
    public long getId() {
        return id;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public String getNickName() {
        return nickName;
    }

    public int getBonusPoints() {
        return bonusPoints;
    }

    public List<Role> getRole() {
        return role;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public void setBonusPoints(int bonusPoints) {
        this.bonusPoints = bonusPoints;
    }

    public void setRole(List<Role> role) {
        this.role = role;
    }
}
