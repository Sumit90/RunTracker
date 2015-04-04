package com.practise.runtracker.runtracker;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by home on 03-04-2015.
 */
public class RunFragment extends Fragment {

    private Button mStartButton,mStopButton;
    private TextView mStarted,mLatitude,mLongitude,mAltitude,mElapsedTime;
    private RunManager mRunManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        mRunManager=RunManager.getInstance(getActivity());
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

        mStartButton.setEnabled(!isStarted);
        mStopButton.setEnabled(isStarted);
    }

}
