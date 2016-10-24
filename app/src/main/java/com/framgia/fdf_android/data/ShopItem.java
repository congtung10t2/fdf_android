package com.framgia.fdf_android.data;

import android.databinding.BindingAdapter;
import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ImageView;

/**
 * Created by framgia on 19/10/2016.
 */
public class ShopItem implements Parcelable {
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
    private int resImage;

    public ShopItem(String title, String description, String price, int resImage) {
        this.title = title;
        this.description = description;
        this.resImage = resImage;
        this.price = price;
    }

    protected ShopItem(Parcel in) {
        title = in.readString();
        description = in.readString();
        price = in.readString();
        resImage = in.readInt();
    }

    @BindingAdapter("imageResource")
    public static void setImageResource(ImageView imageView, int resource) {
        imageView.setImageResource(resource);
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

    public int getResImage() {
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
        dest.writeInt(resImage);
    }
}
