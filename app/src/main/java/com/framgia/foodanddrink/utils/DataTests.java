package com.framgia.foodanddrink.utils;

import android.content.Context;

import com.framgia.foodanddrink.FoodDrinkApplication;
import com.framgia.foodanddrink.R;
import com.framgia.foodanddrink.data.Constants;
import com.framgia.foodanddrink.data.model.FoodDrinkItem;
import com.framgia.foodanddrink.data.model.ShopItem;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by framgia on 26/10/2016.
 */
public class DataTests {
    public static ArrayList<ShopItem> shopItems;
    public static void init(){
        shopItems = new ArrayList<ShopItem>();
        Context context = FoodDrinkApplication.getInstance();
        for (int i = 0; i < Constants.NUMBER_OF_TEST; i++) {

            String title = context.getString(R.string.shop_fake) + "" + i;
            String desc = context.getString(R.string.desc_test);
            ShopItem shopItem = new ShopItem(title, desc, "null", "null");
            shopItems.add(shopItem);
            for (int j = 0; j < Constants.NUMBER_OF_TEST; j++) {
                String titleProduct = context.getString(R.string.product_title_test) + "" + j;
                String descProduct = context.getString(R.string.desc_test);
                String priceProduct = context.getString(R.string.price_test);
                FoodDrinkItem item = new FoodDrinkItem(titleProduct, descProduct, priceProduct,
                    "null");
                item.shopName = title;
                shopItem.list.add(item);
            }
        }
    }

    public static Calendar getTimeTest() {
        Calendar calendar = Calendar.getInstance();
        return calendar;
    }
}
