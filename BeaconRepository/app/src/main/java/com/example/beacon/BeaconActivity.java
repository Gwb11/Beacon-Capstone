package com.example.beacon;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.fragment.app.Fragment;

import java.util.UUID;

public class BeaconActivity extends SingleFragmentActivity {
    private static final String TAG = "BeaconActivity";
    private static final String EXTRA_BEACON_ID = "beacon_id";


    @Override
    public Fragment createFragment() {
        Log.d(TAG, "createFragment");
        UUID beaconId = (UUID) getIntent().getSerializableExtra(EXTRA_BEACON_ID);
        return BeaconFragment.newInstance(beaconId);
    }

    public static Intent newIntent(Context packageContext, UUID beaconId)  {
        Log.d(TAG, "newIntent");
        Intent intent = new Intent(packageContext, BeaconActivity.class);
        intent.putExtra(EXTRA_BEACON_ID, beaconId);
        return intent;
    }

    @Override
    public void finish() {
        Log.d(TAG, "finish");
        super.finish();
    }
}
