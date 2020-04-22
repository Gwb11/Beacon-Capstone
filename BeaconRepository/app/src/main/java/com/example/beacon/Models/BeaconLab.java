package com.example.beacon.Models;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BeaconLab {
    private static final String TAG = "BeaconLab";
    private static BeaconLab sBeaconLab;

    private Context mContext;
    private List<Beacon> mBeacons;

    public static BeaconLab get(Context context) {
        if (sBeaconLab == null) {
            sBeaconLab = new BeaconLab(context);
        }
        return sBeaconLab;
    }

    private BeaconLab(Context context)
    {
        mContext = context.getApplicationContext();
        mBeacons = new ArrayList<>();
    }

    public void addBeacon(Beacon b)
    {
        Log.d(TAG, "addBeacon");
        mBeacons.add(b);
    }

    public void deleteBeacon(Beacon b)
    {
        Log.d(TAG, "deleteBeacon");
        mBeacons.remove(b);
    }

    public List<Beacon> getBeacons() {
        Log.d(TAG, "getBeacons");

        return mBeacons;
    }

    public Beacon getBeacon(UUID id) {
        for (Beacon beacon : mBeacons) {
            if (beacon.getId().equals(id)) {
                Log.d(TAG, "beacon found");
                return beacon;
            }
        }
        Log.d(TAG, "beacon not found");
        return null;
    }
}
