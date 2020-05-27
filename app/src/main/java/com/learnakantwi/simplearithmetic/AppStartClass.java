package com.learnakantwi.simplearithmetic;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.Application;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AppStartClass extends Application {

    public static final String CHANNEL_1_ID = "channel1";
    public static final String CHANNEL_2_ID = "channel2";

    private void createNotificationChannels() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel1 = new NotificationChannel(
                    CHANNEL_1_ID,
                    "Channel 1",
                    NotificationManager.IMPORTANCE_HIGH

            );
            channel1.setDescription("This is Channel 1");

            NotificationChannel channel2 = new NotificationChannel(
                    CHANNEL_2_ID,
                    "Channel 2",
                    NotificationManager.IMPORTANCE_LOW
            );
            channel2.setDescription("This is Channel 2");

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel1);
            manager.createNotificationChannel(channel2);
        }
    }


    public void showDaily() {
        Calendar calendar = Calendar.getInstance();
       // calendar.add(Calendar.SECOND, 5);

        ///////
       // calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 30);
        ////////


        //calendar.set(Calendar.SECOND,5);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        Intent intent = new Intent(getApplicationContext(), Notification_receiver.class);


        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(),0,intent, 0);

    //correct
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),AlarmManager.INTERVAL_DAY,pendingIntent);

    }

    @Override
    public void onCreate() {
        super.onCreate();

        createNotificationChannels();
        //showDaily();

        SharedPreferences sharedPreferences = this.getSharedPreferences("com.learnakantwi.simplearithmetic", Context.MODE_PRIVATE);
        String dailyPreference = sharedPreferences.getString("DailyTrainingPreference", "Yes");

        assert dailyPreference != null;
        if (dailyPreference.equals("Yes")) {
            showDaily();
        }

    }
}

