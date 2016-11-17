package com.framgia.foodanddrink.service;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;

import com.framgia.foodanddrink.R;
import com.framgia.foodanddrink.data.Constants;
import com.framgia.foodanddrink.ui.activity.MainActivity;
import com.framgia.foodanddrink.ui.activity.LoginActivity;

/**
 * Created by congtung10t2 on 10-Nov-16.
 */
public class DailyNotifyService extends IntentService {
    public DailyNotifyService() {
        super("DailyNotifyService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        pushNotification();
    }

    public void pushNotification() {
        NotificationCompat.Builder builder =
            new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_demo_food)
                .setContentTitle(getString(R.string.notify_message))
                .setContentText(getString(R.string.notify_order_now));
        Intent resultIntent = new Intent(this, MainActivity.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(LoginActivity.class);
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent =
            stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(resultPendingIntent);
        NotificationManager notificationManager =
            (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(Constants.EVERYDAY_NOTIFY_ID, builder.build());
    }
}
