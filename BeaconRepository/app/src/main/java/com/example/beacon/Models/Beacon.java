package com.example.beacon.Models;

import android.location.Location;

import java.util.Date;
import java.util.UUID;

public class Beacon {
    private String mTitle;
    private UUID mId;
    private Location mLoc;
    private Date mDate;
    private boolean mVisited;

    public Beacon() {
        mId = UUID.randomUUID();
    }

    public UUID getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public boolean isVisited() {
        return mVisited;
    }

    public void setVisited(boolean visited) {
        mVisited = visited;
    }

    public double getLat() {
        return mLoc.getLatitude();
    }

    public void setLat(double lat) {
        mLoc.setLatitude(lat);
    }

    public double getLon() {
        return mLoc.getLatitude();
    }

    public void setLon(double lon) {
        mLoc.setLongitude(lon);
    }

    @Override
    public String toString() {
        return mTitle;
    }
}
