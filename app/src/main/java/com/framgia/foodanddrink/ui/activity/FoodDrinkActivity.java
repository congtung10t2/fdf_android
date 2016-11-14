package com.framgia.foodanddrink.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.framgia.foodanddrink.R;
import com.framgia.foodanddrink.data.Constants;
import com.framgia.foodanddrink.data.model.FoodDrinkItem;
import com.framgia.foodanddrink.ui.adapter.FoodDrinkAdapter;
import com.framgia.foodanddrink.utils.DataTests;

import java.util.ArrayList;
import java.util.List;

public class FoodDrinkActivity extends AppCompatActivity implements FoodDrinkAdapter
    .OnItemClickListener, NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    private static final int INVERSE_DEGREES = 180;
    private FoodDrinkAdapter adapter;
    private List<FoodDrinkItem> foodAndDrinks = new ArrayList<>();
    private NavigationView navigationView;
    private DrawerLayout drawer;
    private RecyclerView contentFoodDrink;
    private boolean profileView;
    private View headerNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_food_drink);
        initViews();
    }

    private void initViews() {
        contentFoodDrink = (RecyclerView) findViewById(R.id.content_food_drink);
        contentFoodDrink.setLayoutManager(new LinearLayoutManager(this));
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
            this, drawer, toolbar, R.string.navigation_drawer_open,
            R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        DataTests.fakeListFoodDrink(foodAndDrinks);
        adapter = new FoodDrinkAdapter(foodAndDrinks);
        adapter.setItemClickListener(this);
        contentFoodDrink.setAdapter(adapter);
        headerNavigationView = navigationView.getHeaderView(0);
        headerNavigationView.findViewById(R.id.btn_profile_view).setOnClickListener(this);
}

    private void onProfileShow(View view) {
        onHideGroupItems();
        Menu navigationMenu = navigationView.getMenu();
        navigationMenu.setGroupVisible(R.id.gr_category_menu, profileView);
        navigationMenu.setGroupVisible(R.id.gr_feature_menu, profileView);
        navigationMenu.setGroupVisible(R.id.gr_profile, !profileView);
        profileView = !profileView;
        view.setRotation(view.getRotation() + INVERSE_DEGREES);
        view.setVisibility(View.VISIBLE);
    }

    private void onHideGroupItems() {
        Menu navigationMenu = navigationView.getMenu();
        navigationMenu.setGroupVisible(R.id.gr_category, false);
        navigationMenu.setGroupVisible(R.id.gr_feature, false);
        navigationMenu.findItem(R.id.nav_feature).setChecked(false);
        navigationMenu.findItem(R.id.nav_shop).setChecked(false);
        navigationMenu.findItem(R.id.nav_shop).setVisible(profileView);
        navigationMenu.findItem(R.id.nav_shop_manager).setVisible(profileView);
        navigationMenu.findItem(R.id.nav_shop_manager).setChecked(false);
        navigationMenu.findItem(R.id.nav_category).setChecked(false);
    }

    @Override
    public void onItemClick(View view, int position) {
        Intent intent = new Intent(FoodDrinkActivity.this, FoodDrinkDetailActivity.class);
        intent.putExtra(Constants.ITEM_INDEX_KEY, foodAndDrinks.get(position));
        startActivity(intent);
    }

    @Override
    public void onItemQuickOrder(int position) {
        //TODO: Send request quick order
    }

    private void onDetailItemClick(int itemId) {
        switch (itemId) {
            case R.id.nav_profile:
                startActivity(new Intent(FoodDrinkActivity.this, ProfileViewActivity.class));
                break;
            case R.id.nav_change_password:
                startActivity(new Intent(FoodDrinkActivity.this, ChangePasswordActivity.class));
                break;
            case R.id.nav_shop_manager:
                startActivity(new Intent(FoodDrinkActivity.this, NewProductActivity.class));
                break;
            default:
                break;
        }
    }

    public boolean onChangeNavigationItemState(NavigationView navigationView, MenuItem item) {
        boolean isChecked = item.isChecked();
        int groupId = getGroupIdFromTitleId(item.getItemId());
        if (groupId != Constants.INVALID_GROUP_ID) {
            item.setChecked(!isChecked);
            navigationView.getMenu().setGroupVisible(groupId, !isChecked);
        } else {
            item.setChecked(true);
            onDetailItemClick(item.getItemId());
            drawer.closeDrawer(GravityCompat.START);
        }
        return true;
    }

    private int getGroupIdFromTitleId(int titleId) {
        switch (titleId) {
            case R.id.nav_category:
                return R.id.gr_category;
            case R.id.nav_feature:
                return R.id.gr_feature;
            default:
                return Constants.INVALID_GROUP_ID;
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return onChangeNavigationItemState(navigationView, item);
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
