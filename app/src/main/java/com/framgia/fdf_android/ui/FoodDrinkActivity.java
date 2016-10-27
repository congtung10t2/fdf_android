package com.framgia.fdf_android.ui;

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
import android.view.MenuItem;
import android.view.View;

import com.framgia.fdf_android.R;
import com.framgia.fdf_android.data.Constants;
import com.framgia.fdf_android.data.FoodDrinkItem;
import com.framgia.fdf_android.utils.DataTests;

import java.util.ArrayList;
import java.util.List;

public class FoodDrinkActivity extends AppCompatActivity implements FoodDrinkAdapter
    .OnItemClickListener, NavigationView.OnNavigationItemSelectedListener {
    private FoodDrinkAdapter adapter;
    private List<FoodDrinkItem> foodAndDrinks = new ArrayList<>();
    private NavigationView navigationView;
    private DrawerLayout drawer;
    private RecyclerView contentFoodDrink;
    private boolean profileView = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_food_drink);
        initViews();
    }

    public void initViews() {
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
    }

    public void onProfileShow(View view) {
        navigationView.getMenu().setGroupVisible(R.id.gr_category_menu, profileView);
        navigationView.getMenu().setGroupVisible(R.id.gr_feature_menu, profileView);
        navigationView.getMenu().setGroupVisible(R.id.gr_profile, !profileView);
        profileView = !profileView;
        view.setRotation(view.getRotation() + Constants.INVERSE_DEGREES);
    }

    @Override
    public void onItemClick(View view, int position) {
        Intent intent = new Intent(FoodDrinkActivity.this, FoodDrinkDetailActivity.class);
        intent.putExtra(Constants.ITEM_INDEX_KEY, foodAndDrinks.get(position));
        startActivity(intent);
    }

    public void onDetailItemClick(int itemId){
        switch(itemId){
            case R.id.nav_profile:
                startActivity(new Intent(FoodDrinkActivity.this, ProfileViewActivity.class));
                break;
            default:
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

    public int getGroupIdFromTitleId(int titleId) {
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
}
