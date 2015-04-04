package com.practise.runtracker.runtracker;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;

/**
 * Created by home on 03-04-2015.
 */
public class RunManager {

    private String TAG="RunManager";
    private static RunManager sRunManager;
    private LocationManager mLocationManager;
    private Context mContext;

    public static final String ACTION_LOCATION =
            "com.practise.runtracker.ACTION_LOCATION";

    private RunManager(Context c)
    {
        mContext=c;
        mLocationManager=(LocationManager)c.getSystemService(Context.LOCATION_SERVICE);
    }

    public static RunManager getInstance(Context c)
    {
        if(sRunManager==null)
        {
            sRunManager=new RunManager(c.getApplicationContext());
        }

        return sRunManager;
    }

    private PendingIntent getLocationPendingIntent(boolean flag)
    {
        Intent broadcast=new Intent(ACTION_LOCATION);
        int shouldCreate=flag?0:PendingIntent.FLAG_NO_CREATE;

        return PendingIntent.getBroadcast(mContext,0,broadcast,shouldCreate);
    }

    public void  startLocationUpdates()
    {
        String provider=LocationManager.GPS_PROVIDER;
        PendingIntent pi=getLocationPendingIntent(true);

        mLocationManager.requestLocationUpdates(provider,0,0,pi);
    }

    public boolean isTrackingRun()
    {
        return getLocationPendingIntent(false)!=null;

    }

    public void stopLocationUpdates()
    {
        PendingIntent pi=getLocationPendingIntent(false);
        if(pi!=null)
        {
            mLocationManager.removeUpdates(pi);
            pi.cancel();
        }
    }
}
