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
    private String source;

    public ShopItem(String title, String description, String source) {
        this.title = title;
        this.description = description;
        this.source = source;
    }

    protected ShopItem(Parcel in) {
        title = in.readString();
        description = in.readString();
        source = in.readString();
    }

    @BindingAdapter("imageResource")
    public static void setImageResource(ImageView imageView, String resource) {
        //TODO: set resource name again
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }

    public String getResImage() {
        return source;
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
        dest.writeString(source);
    }
}
