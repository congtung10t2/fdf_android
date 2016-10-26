package com.framgia.fdf_android.utils;

import com.framgia.fdf_android.FoodDrinkApplication;
import com.framgia.fdf_android.R;
import com.framgia.fdf_android.data.Constants;
import com.framgia.fdf_android.data.FoodDrinkItem;

import java.util.List;

/**
 * Created by framgia on 26/10/2016.
 */
public class DataTests {
    public static void fakeListFoodDrink(List<FoodDrinkItem> listItem){
        for(int i = 0; i < Constants.NUMBER_OF_TEST; i++)
            listItem.add(new FoodDrinkItem(FoodDrinkApplication.getInstance().getString(R.string.title_test), FoodDrinkApplication.getInstance().getString(
                            R.string.desc_test), FoodDrinkApplication.getInstance().getString(R
                .string.price_test), R.drawable.ic_demo_food));
    }
}
