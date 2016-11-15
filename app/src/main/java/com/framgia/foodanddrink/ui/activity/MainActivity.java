package com.framgia.foodanddrink.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.framgia.foodanddrink.R;
import com.framgia.foodanddrink.data.enums.BottomBarDef;
import com.framgia.foodanddrink.ui.adapter.PagerAdapter;
import com.framgia.foodanddrink.ui.view.FDFNavigationView;

public class MainActivity extends AppCompatActivity
    implements BottomNavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;
    private FDFNavigationView navigationView;
    private BottomNavigationView tabbarNavigation;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_food_drink);
        initViews();
    }

    @Override
    protected void onResume() {
        super.onResume();
        navigationView.updateNavigationState();
    }

    private void initViews() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
            this, drawer, toolbar, R.string.navigation_drawer_open,
            R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        navigationView = (FDFNavigationView) findViewById(R.id.nav_view);
        navigationView.setDrawer(drawer);
        tabbarNavigation = (BottomNavigationView) findViewById(R.id.bottom_bar);
        tabbarNavigation.setOnNavigationItemSelectedListener(this);

        viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(new PagerAdapter
            (getSupportFragmentManager(), 3));

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.action_top_shops:
                viewPager.setCurrentItem(BottomBarDef.TOP_SHOPS.ordinal());
                break;
            case R.id.action_top_products:
                viewPager.setCurrentItem(BottomBarDef.TOP_PRODUCTS.ordinal());
                break;
            case R.id.action_cart_order:
                viewPager.setCurrentItem(BottomBarDef.CART_ORDERS.ordinal());
                break;
        }
        return true;
    }
}
