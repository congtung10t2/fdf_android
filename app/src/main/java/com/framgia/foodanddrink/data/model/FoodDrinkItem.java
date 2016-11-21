package com.framgia.foodanddrink.data.model;

import android.databinding.BindingAdapter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ImageView;

import com.framgia.foodanddrink.FoodDrinkApplication;
import com.framgia.foodanddrink.R;

import java.io.File;

/**
 * Created by framgia on 19/10/2016.
 */
public class FoodDrinkItem implements Parcelable {
    public static final Creator<FoodDrinkItem> CREATOR = new Creator<FoodDrinkItem>() {
        @Override
        public FoodDrinkItem createFromParcel(Parcel in) {
            return new FoodDrinkItem(in);
        }

        @Override
        public FoodDrinkItem[] newArray(int size) {
            return new FoodDrinkItem[size];
        }
    };
    private String title;
    private String description;
    private String price;
    private String resImage;
    public String shopName;

    public FoodDrinkItem(String title, String description, String price, String resImage) {
        this.title = title;
        this.description = description;
        this.resImage = resImage;
        this.price = price;
    }

    protected FoodDrinkItem(Parcel in) {
        title = in.readString();
        description = in.readString();
        price = in.readString();
        resImage = in.readString();
        shopName = in.readString();
    }

    @BindingAdapter("imageResource")
    public static void setImageResource(ImageView imageView, String resource) {
        File imgFile = new  File(resource);

        if(imgFile.exists()){
            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            imageView.setImageBitmap(myBitmap);
            return;
        }
        imageView.setImageBitmap(BitmapFactory.decodeResource(FoodDrinkApplication.getInstance().getResources(), R.drawable.ic_demo_food));
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

    public String getResImage() {
        return resImage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(description);
        dest.writeString(price);
        dest.writeString(resImage);
        dest.writeString(shopName);
    }
}
