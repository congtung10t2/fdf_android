package com.framgia.foodanddrink.ui.view;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;

import com.framgia.foodanddrink.R;
import com.framgia.foodanddrink.data.Constants;
import com.framgia.foodanddrink.service.DailyNotifyService;
import com.framgia.foodanddrink.ui.activity.CartActivity;
import com.framgia.foodanddrink.ui.activity.LoginActivity;
import com.framgia.foodanddrink.ui.activity.OrdersActivity;
import com.framgia.foodanddrink.ui.activity.ProfileViewActivity;
import com.framgia.foodanddrink.ui.activity.ShopManagementActivity;
import com.framgia.foodanddrink.utils.DataStorage;
import com.framgia.foodanddrink.utils.DataTests;
import com.framgia.foodanddrink.utils.UserStorage;

import java.io.File;

/**
 * Created by framgia on 15/11/2016.
 */
public class FDFNavigationView extends NavigationView
    implements CompoundButton.OnCheckedChangeListener, View.OnClickListener,
    NavigationView.OnNavigationItemSelectedListener {
    private static final int INVERSE_DEGREES = 180;
    private View headerNavigationView;
    private static final String REMINDER = "reminder";
    private CheckBox pushEnable;
    private Menu navigationMenu;
    private Context currentContext;
    private boolean profileView;
    private DrawerLayout drawer;

    public void setDrawer(DrawerLayout drawer) {
        this.drawer = drawer;
    }

    public void onProfileShow(View view) {
        navigationMenu.setGroupVisible(R.id.gr_main_function, profileView);
        navigationMenu.setGroupVisible(R.id.gr_profile, !profileView);
        profileView = !profileView;
        view.setRotation(view.getRotation() + INVERSE_DEGREES);
        view.setVisibility(View.VISIBLE);
    }

    public void updateNavigationState() {
        pushEnable.setChecked(DataStorage.getValue(currentContext, REMINDER, false));
    }

    private void init(){
        setNavigationItemSelectedListener(this);
        headerNavigationView = getHeaderView(0);
        pushEnable = (CheckBox) headerNavigationView.findViewById(R.id
            .cb_push_notify);
        navigationMenu = getMenu();
        headerNavigationView.findViewById(R.id.btn_profile_view).setOnClickListener(this);
        String imageDirectory = UserStorage.getInstance().getImage();
        File imgFile = new  File(imageDirectory);
        ImageView avatar = ((ImageView)headerNavigationView.findViewById(R.id.image_avatar));
        if(imgFile.exists()){
            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            avatar.setImageBitmap(myBitmap);
            return;
        }
        avatar.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.ic_demo_food));
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
        init();
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
                        2000, PendingIntent
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

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return onChangeNavigationItemState(item);
    }

    public boolean onChangeNavigationItemState(MenuItem item) {
        boolean isChecked = item.isChecked();
        int groupId = getGroupIdFromTitleId(item.getItemId());
        if (groupId != Constants.INVALID_GROUP_ID) {
            item.setChecked(!isChecked);
            navigationMenu.setGroupVisible(groupId, !isChecked);
        } else {
            item.setChecked(true);
            onDetailItemClick(item.getItemId());
            drawer.closeDrawer(GravityCompat.START);
        }
        return true;
    }

    private int getGroupIdFromTitleId(int titleId) {
        switch (titleId) {
            default:
                return Constants.INVALID_GROUP_ID;
        }
    }


    private void onDetailItemClick(int itemId) {
        switch (itemId) {
            case R.id.nav_account:
                currentContext.startActivity(new Intent(currentContext, ProfileViewActivity.class));
                break;
            case R.id.nav_shop_management:
                currentContext.startActivity(new Intent(currentContext, ShopManagementActivity.class));
                break;
            case R.id.nav_cart:
                currentContext.startActivity(new Intent(currentContext, CartActivity.class));
                break;
            case R.id.nav_order:
                currentContext.startActivity(new Intent(currentContext, OrdersActivity.class));
                break;
            case R.id.nav_logout:
                currentContext.startActivity(new Intent(currentContext, LoginActivity.class));
                break;
            default:
                break;
        }
    }
}
