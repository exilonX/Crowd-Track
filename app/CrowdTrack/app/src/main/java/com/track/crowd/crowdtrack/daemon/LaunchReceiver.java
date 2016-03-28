package com.track.crowd.crowdtrack.daemon;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;


public class LaunchReceiver extends BroadcastReceiver {

    public static final String ACTION_PULSE_SERVER_ALARM =
            "com.track.crowd.crowdtrack.ACTION_PULSE_SERVER_ALARM";

    @Override
    public void onReceive(Context context, Intent intent) {
        // start the service

    }
}

