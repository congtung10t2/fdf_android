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
import java.util.ArrayList;

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
    private String avatar;
    private String coverImage;
    public ArrayList<FoodDrinkItem> list = new ArrayList();

    public ShopItem(String title, String description, String avatar, String coverImage) {
        this.title = title;
        this.description = description;
        this.avatar = avatar;
        this.coverImage = coverImage;
    }

    protected ShopItem(Parcel in) {
        title = in.readString();
        description = in.readString();
        avatar = in.readString();
        coverImage = in.readString();
    }

    @BindingAdapter("imageResource")
    public static void setImageResource(ImageView imageView, String resource) {
        if(resource!= null)
        {
            File imgFile = new  File(resource);

            if(imgFile.exists()){
                Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                imageView.setImageBitmap(myBitmap);
                return;
            }
        }
        imageView.setImageBitmap(BitmapFactory.decodeResource(FoodDrinkApplication.getInstance().getResources(), R.drawable.ic_demo_food));
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage){
        this.coverImage = coverImage;
    }

    public void setAvatar(String avatar){
        this.avatar = avatar;
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
        dest.writeString(avatar);
        dest.writeString(coverImage);
    }
}
