package com.modxroidlabs.notificationexample;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;

public class MainActivity extends AppCompatActivity
{

    NotificationManager mNotificationManager;
    int totalMessages=0, notificationID = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //showNotification();
        showNotificationInboxStyle();

    }

    private void showNotification()
    {
        NotificationCompat.Builder nBuilder = new NotificationCompat.Builder(this);
        nBuilder.setContentTitle("Notification");
        nBuilder.setContentText("You have received a new Notification");
        nBuilder.setTicker("New Message");
        nBuilder.setAutoCancel(true);
        nBuilder.setSmallIcon(R.drawable.ic_action_notification);
        nBuilder.setNumber(++totalMessages);

        Intent intent = new Intent(this, MainActivity.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(MainActivity.class);
        stackBuilder.addNextIntent(intent);

        PendingIntent pendingIntent = stackBuilder.getPendingIntent(0,
                PendingIntent.FLAG_UPDATE_CURRENT);
        nBuilder.setContentIntent(pendingIntent);

        mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(notificationID, nBuilder.build());
    }

    private void showNotificationInboxStyle()
    {
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);

        mBuilder.setContentTitle("New Message");
        mBuilder.setContentText("You've received new message.");
        mBuilder.setTicker("New Message Alert!");
        mBuilder.setAutoCancel(true);
        mBuilder.setSmallIcon(R.drawable.ic_action_notification);
        mBuilder.setNumber(++totalMessages);

        NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();

        String[] notificationArray = new String[6];
        notificationArray[0] = "First Line";
        notificationArray[1] = "Second Line";
        notificationArray[2] = "Third Line";
        notificationArray[3] = "Fourth Line";
        notificationArray[4] = "Fifth Line";
        notificationArray[5] = "Sixth Line";

        inboxStyle.setBigContentTitle("Notification Details.");

        for (int i = 0; i < notificationArray.length; i++) {
            inboxStyle.addLine(notificationArray[i]);
        }
        mBuilder.setStyle(inboxStyle);

        Intent resultIntent = new Intent(this, MainActivity.class);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(MainActivity.class);

        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0,
                PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(resultPendingIntent);
        mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(notificationID, mBuilder.build());

    }
}
