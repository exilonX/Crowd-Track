package com.track.crowd.crowdtrack.daemon;

import android.app.ProgressDialog;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.track.crowd.crowdtrack.MainActivity;

/**
 * Created by root on 19.08.2015.
 */
public class LocationTask extends AsyncTask<Void, Void, Void> {

    ProgressDialog progDialog = null;

    public double latitude = 0.0;
    public double longitude = 0.0;

    public LocationManager mLocationManager;
    public MyLocationListener locationListener;
    public Context context;

    public LocationTask(Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        locationListener = new MyLocationListener();
        mLocationManager = (LocationManager) this.context.getSystemService(Context.LOCATION_SERVICE);
        mLocationManager.requestLocationUpdates(
                LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);

    }

    @Override
    protected void onCancelled() {
        mLocationManager.removeUpdates(locationListener);
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        // send the location to the server

    }

    @Override
    protected Void doInBackground(Void... voids) {



        return null;
    }

    private final class MyLocationListener implements LocationListener {

        @Override
        public void onLocationChanged(Location location) {
            latitude = location.getLatitude();
            longitude = location.getLongitude();
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {
            Log.i("LocationListener", "The status has changed " + String.valueOf(i));
        }

        @Override
        public void onProviderEnabled(String s) {
            Log.i("LocationListener", "The provider is enabled");
        }

        @Override
        public void onProviderDisabled(String s) {
            Log.i("LocationListener", "The provider is disabled");
        }
    }

}
