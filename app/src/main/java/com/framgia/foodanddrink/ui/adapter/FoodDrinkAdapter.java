package com.framgia.foodanddrink.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.framgia.foodanddrink.R;
import com.framgia.foodanddrink.data.model.FoodDrinkItem;

import java.util.List;

/**
 * Created by framgia on 18/10/2016.
 */
public class FoodDrinkAdapter extends RecyclerView.Adapter<FoodDrinkAdapter.ItemHolder> {
    private List<FoodDrinkItem> foodDrinkItems;
    private OnItemClickListener itemClickListener;
    private ItemHolder holder;

    public FoodDrinkAdapter(List<FoodDrinkItem> foodDrinkItems) {
        this.foodDrinkItems = foodDrinkItems;
    }

    public void setItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflatedView = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.food_drink_item_row, parent, false);
        final ItemHolder holder = new ItemHolder(inflatedView);
        inflatedView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListener.onItemClick(v, holder.getAdapterPosition());
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ItemHolder holder, int position) {
        this.holder = holder;
        FoodDrinkItem foodDrinkItem = foodDrinkItems.get(position);
        holder.itemImage.setImageResource(foodDrinkItem.getResImage());
        holder.itemTitle.setText(foodDrinkItem.getTitle());
        holder.itemDescription.setText(foodDrinkItem.getDescription());
        holder.itemPrice.setText(foodDrinkItem.getPrice());
        final int pos = position;
        holder.btnQuickOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListener.onItemQuickOrder(pos);
            }
        });
    }

    @Override
    public int getItemCount() {
        return foodDrinkItems == null ? 0 : foodDrinkItems.size();
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
        void onItemQuickOrder(int position);
    }

    public static class ItemHolder extends RecyclerView.ViewHolder {
        private ImageView itemImage;
        private TextView itemTitle;
        private TextView itemDescription;
        private TextView itemPrice;
        private Button btnQuickOrder;

        public ItemHolder(View view) {
            super(view);
            itemImage = (ImageView) view.findViewById(R.id.image_item_detail);
            itemTitle = (TextView) view.findViewById(R.id.text_item_title);
            itemDescription = (TextView) view.findViewById(R.id.text_item_desc);
            itemPrice = (TextView) view.findViewById(R.id.text_item_price);
            btnQuickOrder = (Button) view.findViewById(R.id.btn_quick_order);
        }
    }
}