package com.framgia.foodanddrink.ui.view;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.view.Menu;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.framgia.foodanddrink.R;
import com.framgia.foodanddrink.service.DailyNotifyService;
import com.framgia.foodanddrink.utils.DataStorage;
import com.framgia.foodanddrink.utils.DataTests;

/**
 * Created by framgia on 15/11/2016.
 */
public class FDFNavigationView extends NavigationView
    implements CompoundButton.OnCheckedChangeListener, IActivityState {
    private View headerNavigationView;
    private static final String REMINDER = "reminder";
    private CheckBox pushEnable;
    private Menu navigationMenu;
    private Context currentContext;

    public void setCurrentContext(Context context) {
        this.currentContext = context;
    }

    @Override
    public void onCreate() {
        initInsideViews();
    }

    @Override
    public void onResume() {
        pushEnable.setChecked(DataStorage.getValue(currentContext, REMINDER, false));
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onDestroy() {
    }

    private void initInsideViews(){
        headerNavigationView = getHeaderView(0);
        pushEnable = (CheckBox) headerNavigationView.findViewById(R.id
            .cb_push_notify);
        navigationMenu = getMenu();
        pushEnable.setOnCheckedChangeListener(this);

    }

    public FDFNavigationView(Context context) {
        super(context);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.cb_push_notify:
                AlarmManager alarm = (AlarmManager) currentContext.getSystemService(Context
                    .ALARM_SERVICE);
                DataStorage.saveValue(currentContext, REMINDER, isChecked);
                if (isChecked) {
                    alarm.setRepeating(AlarmManager.RTC_WAKEUP,
                        DataTests.getTimeTest().getTimeInMillis(),
                        AlarmManager.INTERVAL_DAY, PendingIntent
                            .getBroadcast(currentContext, 0, new Intent(currentContext, DailyNotifyService.class), 0));
                    return;
                }
                alarm.cancel(PendingIntent
                    .getBroadcast(currentContext, 0, new Intent(currentContext, DailyNotifyService.class), 0));
                break;
        }
    }
}
