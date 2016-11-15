package com.framgia.foodanddrink.utils;

import android.content.Context;

import com.framgia.foodanddrink.FoodDrinkApplication;
import com.framgia.foodanddrink.R;
import com.framgia.foodanddrink.data.Constants;
import com.framgia.foodanddrink.data.model.FoodDrinkItem;
import com.framgia.foodanddrink.data.model.ShopItem;

import java.util.Calendar;
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

    public static void fakeListShop(List<ShopItem> listItem) {
        Context context = FoodDrinkApplication.getInstance();
        for (int i = 0; i < Constants.NUMBER_OF_TEST; i++) {
            String title = context.getString(R.string.title_test);
            String desc = context.getString(R.string.desc_test);
            listItem.add(new ShopItem(title, desc, R.drawable.ic_shop_avatar));
        }
    }


    public static Calendar getTimeTest() {
        Calendar calendar = Calendar.getInstance();
        /*calendar.set(Calendar.HOUR_OF_DAY, 11);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);*/
        return calendar;
    }
}
