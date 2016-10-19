package com.framgia.fdf_android.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.framgia.fdf_android.R;
import com.framgia.fdf_android.data.FoodDrinkItem;

import java.util.List;

/**
 * Created by framgia on 18/10/2016.
 */
public class FoodDrinkAdapter extends RecyclerView.Adapter<FoodDrinkAdapter.ItemHolder> {
    private List<FoodDrinkItem> moviesList;

    public FoodDrinkAdapter(List<FoodDrinkItem> moviesList) {
        this.moviesList = moviesList;
    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflatedView = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.food_drink_item_row, parent, false);
        return new ItemHolder(inflatedView);
    }

    @Override
    public void onBindViewHolder(ItemHolder holder, int position) {
        FoodDrinkItem foodDrinkItem = moviesList.get(position);
        holder.itemImage.setImageBitmap(foodDrinkItem.getImage());
        holder.itemTitle.setText(foodDrinkItem.getTitle());
        holder.itemDescription.setText(foodDrinkItem.getDescription());
        holder.itemPrice.setText(foodDrinkItem.getPrice());
    }

    @Override
    public int getItemCount() {
        return moviesList == null ? 0 : moviesList.size();
    }

    public static class ItemHolder extends RecyclerView.ViewHolder {
        private ImageView itemImage;
        private TextView itemTitle;
        private TextView itemDescription;
        private TextView itemPrice;

        public ItemHolder(View view) {
            super(view);
            itemImage = (ImageView) view.findViewById(R.id.item_image);
            itemTitle = (TextView) view.findViewById(R.id.item_title);
            itemDescription = (TextView) view.findViewById(R.id.item_description);
            itemPrice = (TextView) view.findViewById(R.id.item_price);
        }
    }
}