package com.framgia.fdf_android;

import android.app.Application;
/**
 * Created by framgia on 10/10/2016.
 */
public class FoodDrinkApplication extends Application {
    private static FoodDrinkApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static FoodDrinkApplication getInstance(){
        return instance;
    }
}