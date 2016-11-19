package com.framgia.foodanddrink.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.provider.ContactsContract;

import com.framgia.foodanddrink.FoodDrinkApplication;

/**
 * Created by hoangcongtung on 11/18/16.
 */
public class UserStorage {
    private static UserStorage instance;
    private String name;
    private String phone;
    private String email;
    private String image;

    public static UserStorage getInstance() {
        if (instance == null) {
            instance = new UserStorage();
            instance.loadFromStorage(FoodDrinkApplication.getInstance());
        }
        return instance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void saveToStorage(Context context){
        DataStorage.saveValue(context, "email", email);
        DataStorage.saveValue(context, "phone", phone);
        DataStorage.saveValue(context, "name", name);
        DataStorage.saveValue(context, "profile", image);
    }

    public void loadFromStorage(Context context) {
        image = DataStorage.getValue(context, "profile", "null");
        email = DataStorage.getValue(context, "email", "congtung10t2@gmail.com");
        phone = DataStorage.getValue(context, "phone", "01693306521");
        name = DataStorage.getValue(context, "name", "cong tung");
    }
}
