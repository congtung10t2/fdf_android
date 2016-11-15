package com.framgia.foodanddrink.data.model;

import android.databinding.BindingAdapter;
import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ImageView;

/**
 * Created by framgia on 19/10/2016.
 */
public class ShopItem implements Parcelable {
    public static final Creator<ShopItem> CREATOR = new Creator<ShopItem>() {
        @Override
        public ShopItem createFromParcel(Parcel in) {
            return new ShopItem(in);
        }

        @Override
        public ShopItem[] newArray(int size) {
            return new ShopItem[size];
        }
    };
    private String title;
    private String description;
    private int resImage;

    public ShopItem(String title, String description, int resImage) {
        this.title = title;
        this.description = description;
        this.resImage = resImage;
    }

    protected ShopItem(Parcel in) {
        title = in.readString();
        description = in.readString();
        resImage = in.readInt();
    }

    @BindingAdapter("imageResource")
    public static void setImageResource(ImageView imageView, int resource) {
        imageView.setImageResource(resource);
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
        dest.writeInt(resImage);
    }
}
