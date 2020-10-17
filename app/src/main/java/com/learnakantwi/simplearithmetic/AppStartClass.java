package com.learnakantwi.simplearithmetic;

import android.app.AlarmManager;
import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import static com.learnakantwi.simplearithmetic.MultiplicationTableActivity.Numbers;

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

        if (5>2) {
            Numbers = new HashMap<>();
            Numbers.put(1, "one");
            Numbers.put(2, "two");
            Numbers.put(3, "three");
            Numbers.put(4, "four");
            Numbers.put(5, "five");
            Numbers.put(6, "six");
            Numbers.put(7, "seven");
            Numbers.put(8, "eight");
            Numbers.put(9, "nine");
            Numbers.put(10, "ten");
            Numbers.put(11, "eleven");
            Numbers.put(12, "twelve");
            Numbers.put(13, "thirteen");
            Numbers.put(14, "fourteen");
            Numbers.put(15, "fifteen");
            Numbers.put(16, "sixteen");
            Numbers.put(17, "seventeen");
            Numbers.put(18, "eighteen");
            Numbers.put(19, "nineteen");
            Numbers.put(20, "twenty");

            Numbers.put(21, "twentyone");
            Numbers.put(22, "twentytwo");
            Numbers.put(23, "twentythree");
            Numbers.put(24, "twentyfour");
            Numbers.put(25, "twentyfive");
            Numbers.put(26, "twentysix");
            Numbers.put(27, "twentyseven");
            Numbers.put(28, "twentyeight");
            Numbers.put(29, "twentynine");

            Numbers.put(30, "thirty");
            Numbers.put(31, "thirtyone");
            Numbers.put(32, "thirtytwo");
            Numbers.put(33, "thirtythree");
            Numbers.put(34, "thirtyfour");
            Numbers.put(35, "thirtyfive");
            Numbers.put(36, "thirtysix");
            Numbers.put(37, "thirtyseven");
            Numbers.put(38, "thirtyeight");
            Numbers.put(39, "thirtynine");

            Numbers.put(40, "forty");
            Numbers.put(41, "fortyone");
            Numbers.put(42, "fortytwo");
            Numbers.put(43, "fortythree");
            Numbers.put(44, "fortyfour");
            Numbers.put(45, "fortyfive");
            Numbers.put(46, "fortysix");
            Numbers.put(47, "fortyseven");
            Numbers.put(48, "fortyeight");
            Numbers.put(49, "fortynine");


            Numbers.put(50, "fifty");
            Numbers.put(51, "fiftyone");
            Numbers.put(52, "fiftytwo");
            Numbers.put(53, "fiftythree");
            Numbers.put(54, "fiftyfour");
            Numbers.put(55, "fiftyfive");
            Numbers.put(56, "fiftysix");
            Numbers.put(57, "fiftyseven");
            Numbers.put(58, "fiftyeight");
            Numbers.put(59, "fiftynine");

            Numbers.put(60, "sixty");
            Numbers.put(61, "sixtyone");
            Numbers.put(62, "sixtytwo");
            Numbers.put(63, "sixtythree");
            Numbers.put(64, "sixtyfour");
            Numbers.put(65, "sixtyfive");
            Numbers.put(66, "sixtysix");
            Numbers.put(67, "sixtyseven");
            Numbers.put(68, "sixtyeight");
            Numbers.put(69, "sixtynine");
            Numbers.put(70, "seventy");


            Numbers.put(71, "seventyone");
            Numbers.put(72, "seventytwo");
            Numbers.put(73, "seventythree");
            Numbers.put(74, "seventyfour");
            Numbers.put(75, "seventyfive");
            Numbers.put(76, "seventysix");
            Numbers.put(77, "seventyseven");
            Numbers.put(78, "seventyeight");
            Numbers.put(79, "seventynine");
            Numbers.put(80, "eighty");

            Numbers.put(81, "eightyone");
            Numbers.put(82, "eightytwo");
            Numbers.put(83, "eightythree");
            Numbers.put(84, "eightyfour");
            Numbers.put(85, "eightyfive");
            Numbers.put(86, "eightysix");
            Numbers.put(87, "eightyseven");
            Numbers.put(88, "eightyeight");
            Numbers.put(89, "eightynine");

            Numbers.put(90, "ninety");
            Numbers.put(91, "ninetyone");
            Numbers.put(92, "ninetytwo");
            Numbers.put(93, "ninetythree");
            Numbers.put(94, "ninetyfour");
            Numbers.put(95, "ninetyfive");
            Numbers.put(96, "ninetysix");
            Numbers.put(97, "ninetyseven");
            Numbers.put(98, "ninetyeight");
            Numbers.put(99, "ninetynine");


            Numbers.put(100, "onehundred");
            Numbers.put(200, "twohundred");
            Numbers.put(300, "threehundred");
            Numbers.put(400, "fourhundred");
            Numbers.put(500, "fivehundred");
            Numbers.put(600, "sixhundred");
            Numbers.put(700, "sevenhundred");
            Numbers.put(800, "eighthundred");
            Numbers.put(900, "ninehundred");
            Numbers.put(1000, "onethousand");


            Numbers.put(-100, "onehundredand");
            Numbers.put(-200, "twohundredand");
            Numbers.put(-300, "threehundredand");
            Numbers.put(-400, "fourhundredand");
            Numbers.put(-500, "fivehundredand");
            Numbers.put(-600, "sixhundredand");
            Numbers.put(-700, "sevenhundredand");
            Numbers.put(-800, "eighthundredand");
            Numbers.put(-900, "ninehundredand");

            //  Numbers.put(-1000, "onethousands");
            Numbers.put(-2000, "twothousands");
            Numbers.put(-3000, "threethousands");
            Numbers.put(-4000, "fourthousands");
            Numbers.put(-5000, "fivethousands");
            Numbers.put(-6000, "sixthousands");
            Numbers.put(-7000, "seventhousands");
            Numbers.put(-8000, "eightthousands");
            Numbers.put(-9000, "ninethousands");


            Numbers.put(2000, "twothousand");
            Numbers.put(3000, "threethousand");
            Numbers.put(4000, "fourthousand");
            Numbers.put(5000, "fivethousand");
            Numbers.put(6000, "sixthousand");
            Numbers.put(7000, "seventhousand");
            Numbers.put(8000, "eightthousand");
            Numbers.put(9000, "ninethousand");
            Numbers.put(10000, "tenthousand");

        }


    }
}

