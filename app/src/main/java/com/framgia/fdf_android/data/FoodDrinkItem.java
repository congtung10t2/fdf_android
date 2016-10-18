package com.framgia.fdf_android.data;

import android.graphics.Bitmap;

/**
 * Created by framgia on 19/10/2016.
 */
public class FoodDrinkItem {
    private String title;
    private String description;
    private String price;
    private Bitmap image;

    public FoodDrinkItem(String title, String description, String price, Bitmap image) {
        this.title = title;
        this.description = description;
        this.image = image;
        this.price = price;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }

    public Bitmap getImage() {
        return image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
