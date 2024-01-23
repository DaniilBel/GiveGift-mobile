package com.example.givegiftdesign;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import java.util.Random;

public class Reminder extends BroadcastReceiver {

    private static final String CHANNEL_ID = "1";

    @Override
    public void onReceive(Context context, Intent intent) {
        Random random = new Random();

        NotificationCompat.Builder builder = null;
        
        switch (random.nextInt(3)) {
            case 0:
                builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                        .setSmallIcon(R.mipmap.logout)
                        .setContentTitle("Напоминалка")
                        .setContentText("Давно тебя не было в уличных гонках")
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                        .setAutoCancel(true);
                break;
            case 1:
                builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                        .setSmallIcon(R.mipmap.logout)
                        .setContentTitle("Сова")
                        .setContentText("Знаешь сову из duolingo? Всеки ей")
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                        .setAutoCancel(true);
                break;
            case 2:
                builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                        .setSmallIcon(R.mipmap.logout)
                        .setContentTitle("Напоминалка")
                        .setContentText("Скоро новый год. Ты ведь не забыл подготовить подарки?")
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                        .setAutoCancel(true);
                break;
        }
        
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        if (ActivityCompat.checkSelfPermission(context, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        notificationManager.notify(1, builder.build());
    }
}
