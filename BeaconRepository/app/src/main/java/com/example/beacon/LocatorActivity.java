package com.example.beacon;

import android.app.Dialog;
import android.content.DialogInterface;

import androidx.fragment.app.Fragment;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;


public class LocatorActivity extends SingleFragmentActivity {
    private static final int REQUEST_ERROR = 0;
    @Override
    protected Fragment createFragment() {
            return LocatorFragment.newInstance();
    }

    @Override
    protected void onResume() {
        super.onResume();
        int errorCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (errorCode != ConnectionResult.SUCCESS) {
            Dialog errorDialog = GooglePlayServicesUtil
                    .getErrorDialog(errorCode, this, REQUEST_ERROR,
                            new DialogInterface.OnCancelListener() {
                                @Override
                                public void onCancel(DialogInterface dialog) {
                                    // Leave if services are unavailable.
                                    finish();
                                }
                            });
            errorDialog.show();
        }
    }

}
