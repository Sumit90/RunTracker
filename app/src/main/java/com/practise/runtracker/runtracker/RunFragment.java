package com.practise.runtracker.runtracker;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by home on 03-04-2015.
 */
public class RunFragment extends Fragment {

    private BroadcastReceiver mLocationReceiver=new LocationReceiver()
    {
        @Override
        protected void onLocationReceived(Context context, Location loc) {
            mLastLocation = loc;
            if (isVisible())
                updateUI();
        }

        @Override
        protected void onProviderEnabledChanged(boolean enabled) {
            int toastText = enabled ? R.string.gps_enabled : R.string.gps_disabled;
            Toast.makeText(getActivity(), toastText, Toast.LENGTH_LONG).show();
        }
    };

    private Button mStartButton,mStopButton;
    private TextView mStarted,mLatitude,mLongitude,mAltitude,mElapsedTime;
    private RunManager mRunManager;
    private Run mRun;
    private Location mLastLocation;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        mRun = new Run();
        mRunManager=RunManager.getInstance(getActivity());
    }

    @Override
    public void onStart() {
        super.onStart();
        getActivity().registerReceiver(mLocationReceiver,new IntentFilter(RunManager.ACTION_LOCATION));
    }

    @Override
    public void onStop() {

        getActivity().unregisterReceiver(mLocationReceiver);
        super.onStop();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v=inflater.inflate(R.layout.fragment_run,container,false);

        mStartButton=(Button)v.findViewById(R.id.start_btn);
        mStopButton=(Button)v.findViewById(R.id.stop_btn);
        mStarted=(TextView)v.findViewById(R.id.started);
        mLatitude=(TextView)v.findViewById(R.id.latitude);
        mLongitude=(TextView)v.findViewById(R.id.longitude);
        mAltitude=(TextView)v.findViewById(R.id.altitude);
        mElapsedTime=(TextView)v.findViewById(R.id.elapsed_time);

        mStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mRunManager.startLocationUpdates();
                updateUI();
            }
        });

        mStopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mRunManager.stopLocationUpdates();
                updateUI();
            }
        });

        updateUI();

        return v;
    }

    private void updateUI()
    {
        boolean isStarted=mRunManager.isTrackingRun();

        if (mRun != null)
            mStarted.setText(mRun.getStartDate().toString());
        int durationSeconds = 0;
        if (mRun != null && mLastLocation != null) {
            durationSeconds = mRun.getDurationSeconds(mLastLocation.getTime());
            mLatitude.setText(Double.toString(mLastLocation.getLatitude()));
            mLongitude.setText(Double.toString(mLastLocation.getLongitude()));
            mAltitude.setText(Double.toString(mLastLocation.getAltitude()));
        }
        mElapsedTime.setText(Run.formatDuration(durationSeconds));

        mStartButton.setEnabled(!isStarted);
        mStopButton.setEnabled(isStarted);
    }

}
