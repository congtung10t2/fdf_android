package com.framgia.fdf_android.utils;

import android.content.Context;

import com.framgia.fdf_android.FoodDrinkApplication;
import com.framgia.fdf_android.R;
import com.framgia.fdf_android.data.Constants;
import com.framgia.fdf_android.data.FoodDrinkItem;

import java.util.List;

/**
 * Created by framgia on 26/10/2016.
 */
public class DataTests {
    public static void fakeListFoodDrink(List<FoodDrinkItem> listItem) {
        Context context = FoodDrinkApplication.getInstance();
        for (int i = 0; i < Constants.NUMBER_OF_TEST; i++) {
            String title = context.getString(R.string.title_test);
            String desc = context.getString(R.string.desc_test);
            String price = context.getString(R.string.price_test);
            listItem.add(new FoodDrinkItem(title, desc, price, R.drawable.ic_demo_food));
        }
    }
}
