package com.learnakantwi.simplearithmetic;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;

public class Notification_receiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        Intent intent1 = new Intent(context, OneMinuteMain.class);
        intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(context,100, intent1, PendingIntent.FLAG_UPDATE_CURRENT);


        Notification notification = new NotificationCompat.Builder(context, AppStartClass.CHANNEL_1_ID)
                .setSmallIcon(R.drawable.simplemathicon)
                //.setSmallIcon(R.drawable.proverbsimage)
                .setContentIntent(pendingIntent)
                .setContentTitle("My Math")
                .setContentText("It's time for ONE minute Arithmetic Training. Just a Minute")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setAutoCancel(true)
                .build();

        notificationManager.notify(1, notification);

        /*Notification notification = new NotificationCompat.Builder(context,"Channel_1").
                setContentIntent(pendingIntent).setContentTitle("Hi")
                .setContentText("Hi toast a notification")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();

        notificationManager.notify(1, notification);*/


    }
}
