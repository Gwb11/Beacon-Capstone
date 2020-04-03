package com.example.beacon;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BeaconLab {

    private static BeaconLab sBeaconLab;
    private List<Beacon> mBeacons;

    public static BeaconLab get(Context context) {
        if (sBeaconLab == null) {
            sBeaconLab = new BeaconLab(context);
        }
        return sBeaconLab;
    }

    private BeaconLab(Context context) {
        mBeacons = new ArrayList<>();
        Beacon b1 = new Beacon();
        b1.setTitle("First Beacon");
        b1.setLat(30.5491);
        b1.setLon(87.2186);
        mBeacons.add(b1);
        Beacon b2 = new Beacon();
        b2.setTitle("Second Beacon");
        b2.setLat(30.5491);
        b2.setLon(87.2186);
        //mBeacons.add(b2);
    }

    public List<Beacon> getBeacons() {
        return mBeacons;
    }

    public Beacon getBeacon(UUID id) {
        for (Beacon beacon : mBeacons) {
            if (beacon.getId().equals(id)) {
                return beacon;
            }
        }
        return null;
    }
}
