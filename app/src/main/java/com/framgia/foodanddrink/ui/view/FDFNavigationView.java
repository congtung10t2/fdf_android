package com.framgia.foodanddrink.ui.view;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.util.AttributeSet;
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
    implements CompoundButton.OnCheckedChangeListener, IActivityState, View.OnClickListener {
    private static final int INVERSE_DEGREES = 180;
    private View headerNavigationView;
    private static final String REMINDER = "reminder";
    private CheckBox pushEnable;
    private Menu navigationMenu;
    private Context currentContext;
    private boolean profileView;

    @Override
    public void onCreate() {
    }

    public void onProfileShow(View view) {
        navigationMenu.setGroupVisible(R.id.gr_main_function, profileView);
        navigationMenu.setGroupVisible(R.id.gr_profile, !profileView);
        profileView = !profileView;
        view.setRotation(view.getRotation() + INVERSE_DEGREES);
        view.setVisibility(View.VISIBLE);
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
        headerNavigationView.findViewById(R.id.btn_profile_view).setOnClickListener(this);
        pushEnable.setOnCheckedChangeListener(this);

    }

    public FDFNavigationView(Context context) {
        this(context, null);
    }


    public FDFNavigationView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);;
    }

    public FDFNavigationView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        currentContext = context;
        initInsideViews();
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

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_profile_view:
                onProfileShow(view);
                break;
        }
    }
}
