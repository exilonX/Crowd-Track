package com.track.crowd.crowdtrack.daemon;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Binder;
import android.os.IBinder;

import java.util.Calendar;

/**
 * Created by root on 18.08.2015.
 */
public class MonitorService extends Service {

    private LocationTask mTask;
    private String mPulseUrl;
//    private HomeBoySettings settings;
//    private DataFile dataFile;
    private AlarmManager alarms;
    private PendingIntent alarmIntent;
    private ConnectivityManager cnnxManager;

    public class MonitorBinder extends Binder {
        MonitorService getService() {
            return MonitorService.this;
        }
    }



    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        cnnxManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        alarms = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intentOnAlarm = new Intent(LaunchReceiver.ACTION_PULSE_SERVER_ALARM);
        alarmIntent = PendingIntent.getBroadcast(this, 0, intentOnAlarm, 0);

        Calendar calendar = Calendar.getInstance();

        alarms.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), 1000 * 60, alarmIntent);
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);

        // execute some shit here
        executeTask();

        return START_STICKY;
    }

    public void executeTask() {
        if (mTask != null && mTask.getStatus() != LocationTask.Status.FINISHED)
            return;
        mTask = (LocationTask) new LocationTask(getApplicationContext()).execute();
    }
}
