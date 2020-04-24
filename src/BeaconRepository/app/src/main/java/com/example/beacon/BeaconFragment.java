package com.example.beacon;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.beacon.Models.Beacon;
import com.example.beacon.Models.BeaconLab;

import java.util.Date;
import java.util.UUID;

public class BeaconFragment extends Fragment {
    private static final String TAG = "BeaconFragment";

    private static final String ARG_BEACON_ID = "beacon_id";
    private static final String DIALOG_DATE = "dialog_date";
    private static final int REQUEST_DATE = 0;

    private Beacon mBeacon;
    private EditText mTitleField;
    private Button mDateButton;
    private CheckBox mVisitedCheckBox;

    public static BeaconFragment newInstance(UUID beaconID)  {
        Log.d(TAG, "newInstance");
        Bundle args = new Bundle();
        args.putSerializable(ARG_BEACON_ID, beaconID);

        BeaconFragment fragment = new BeaconFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");

        UUID beaconId = (UUID) getArguments().getSerializable(ARG_BEACON_ID);
        mBeacon = BeaconLab.get(getActivity()).getBeacon(beaconId);

        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_beacon, container, false);
        Log.d(TAG, "onCreateView");

        mTitleField = v.findViewById(R.id.beacon_title);
        mTitleField.setText(mBeacon.getTitle());
        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //this is useless
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mBeacon.setTitle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                //this is useless
            }
        });

        mDateButton = v.findViewById(R.id.beacon_date);
        updateDate();
        mDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                DatePickerFragment dialog = DatePickerFragment.newInstance(mBeacon.getDate());
                dialog.setTargetFragment(BeaconFragment.this, REQUEST_DATE);
                dialog.show(manager, DIALOG_DATE);
            }
        });

        mVisitedCheckBox = v.findViewById(R.id.beacon_visited);
        mVisitedCheckBox.setChecked(mBeacon.isVisited());
        mVisitedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mBeacon.setVisited(isChecked);
            }
        });

        return v;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        Log.d(TAG, "onCreateOptionsMenu");

        inflater.inflate(R.menu.fragment_beacon, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d(TAG, "onOptionsItemSelected");

        switch(item.getItemId())
        {
            case R.id.delete_beacon:
                BeaconLab.get(getActivity()).deleteBeacon(mBeacon);
                Log.d(TAG, "Beacon Deleted");
                getActivity().finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d(TAG, "onActivityResult");
        if(resultCode != Activity.RESULT_OK)
        {
            return;
        }
        if(requestCode == REQUEST_DATE)
        {
            Date date = (Date) data.getSerializableExtra(DatePickerFragment.EXTRA_DATE);
            mBeacon.setDate(date);
            updateDate();
        }

    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
        //BeaconLab.get(getActivity()).updateBeacon(mBeacon);
    }

    private void updateDate() {
        Log.d(TAG, "updateDate");
        mDateButton.setText(mBeacon.getDate().toString());
    }


}
