package com.framgia.foodanddrink.ui.adapter;

/**
 * Created by framgia on 17/11/2016.
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.framgia.foodanddrink.ui.activity.FoodDrinkFragment;
import com.framgia.foodanddrink.ui.activity.ShopFragment;

public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new ShopFragment();
            case 1:
                return new ShopFragment();
            case 2:
                return new FoodDrinkFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}