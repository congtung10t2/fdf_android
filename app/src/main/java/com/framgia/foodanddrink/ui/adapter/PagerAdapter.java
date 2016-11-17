package com.framgia.foodanddrink.ui.adapter;

/**
 * Created by framgia on 17/11/2016.
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.framgia.foodanddrink.ui.activity.FoodDrinkFragment;

public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        FoodDrinkFragment tab1 = new FoodDrinkFragment();
        return tab1;
        /*switch (position) {
            case 0:
                FoodDrinkFragment tab1 = new FoodDrinkFragment();
                return tab1;
            case 1:
                FoodDrinkFragment tab1 = new FoodDrinkFragment();
                return tab1;
            case 2:
                FoodDrinkFragment tab1 = new FoodDrinkFragment();
                return tab1;
            default:
                return null;
        }*/
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}