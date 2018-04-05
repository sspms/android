package com.shanshui.smartcommunity.android.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.Date;

/**
 * community information, all public.
 */
@Entity(indices = {@Index("neighborhoodCommittee"), @Index(value = "location", unique = true)})
public class Community implements Roomable {
    public Community(long id, String name) {
        this.id = id;
        this.name = name;
    }

    @PrimaryKey
    @NonNull
    private long id;
    private String name;
    private String city;
    private String province;
    private String district;
    private String location;
    private String developer;
    private String propertyCompany;
    private Date comleteDate;
    private double greenRate;
    private double capacyRate;
    private int parkingLotTotal;
    private int buildingTotal;
    private int householdTotal;
    private String layoutUrl;
    private String description;
    private long neighborhoodCommittee;

    @Override
    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public String getProvince() {
        return province;
    }

    public String getDistrict() {
        return district;
    }

    public String getLocation() {
        return location;
    }

    public String getDeveloper() {
        return developer;
    }

    public String getPropertyCompany() {
        return propertyCompany;
    }

    public Date getComleteDate() {
        return comleteDate;
    }

    public double getGreenRate() {
        return greenRate;
    }

    public double getCapacyRate() {
        return capacyRate;
    }

    public int getParkingLotTotal() {
        return parkingLotTotal;
    }

    public int getBuildingTotal() {
        return buildingTotal;
    }

    public int getHouseholdTotal() {
        return householdTotal;
    }

    public String getLayoutUrl() {
        return layoutUrl;
    }

    public String getDescription() {
        return description;
    }

    public long getNeighborhoodCommittee() {
        return neighborhoodCommittee;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    public void setPropertyCompany(String propertyCompany) {
        this.propertyCompany = propertyCompany;
    }

    public void setComleteDate(Date comleteDate) {
        this.comleteDate = comleteDate;
    }

    public void setGreenRate(double greenRate) {
        this.greenRate = greenRate;
    }

    public void setCapacyRate(double capacyRate) {
        this.capacyRate = capacyRate;
    }

    public void setParkingLotTotal(int parkingLotTotal) {
        this.parkingLotTotal = parkingLotTotal;
    }

    public void setBuildingTotal(int buildingTotal) {
        this.buildingTotal = buildingTotal;
    }

    public void setHouseholdTotal(int householdTotal) {
        this.householdTotal = householdTotal;
    }

    public void setLayoutUrl(String layoutUrl) {
        this.layoutUrl = layoutUrl;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setNeighborhoodCommittee(long neighborhoodCommittee) {
        this.neighborhoodCommittee = neighborhoodCommittee;
    }
}
