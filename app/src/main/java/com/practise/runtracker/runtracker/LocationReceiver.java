package com.practise.runtracker.runtracker;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.util.Log;

/**
 * Created by home on 04-04-2015.
 */
public class LocationReceiver extends BroadcastReceiver {

    private static final String TAG="LocationReceiver" ;
    @Override
    public void onReceive(Context context, Intent intent) {

        Location loc=(Location)intent.getParcelableExtra(LocationManager.KEY_LOCATION_CHANGED);
        if(loc!=null)
        {
            onLocationReceived(context,loc);
            return;
        }

        Boolean enabled=(Boolean)intent.getBooleanExtra(LocationManager.KEY_PROVIDER_ENABLED,false);
        if(enabled)
        {
            onProviderEnabled(enabled);
        }

    }


    private void onLocationReceived(Context context,Location loc)
    {
        Log.d(TAG,loc.getProvider()+"  "+loc.getLatitude()+"  "+loc.getLongitude()+"  "+loc.getAltitude());

    }

    private void onProviderEnabled(Boolean enabled)
    {
        Log.d(TAG,enabled?"provider enabled":"provider not enabled");
    }
}
