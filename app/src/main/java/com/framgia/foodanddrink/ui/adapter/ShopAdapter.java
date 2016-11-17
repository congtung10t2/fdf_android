package com.framgia.foodanddrink.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.framgia.foodanddrink.R;
import com.framgia.foodanddrink.data.model.ShopItem;

import java.util.List;

/**
 * Created by framgia on 18/10/2016.
 */
public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.ItemHolder> {
    private List<ShopItem> shopItems;
    private OnItemClickListener itemClickListener;
    private ItemHolder holder;

    public ShopAdapter(List<ShopItem> shopItems) {
        this.shopItems = shopItems;
    }

    public void setItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflatedView = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.shop_item_row, parent, false);
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
        ShopItem shopItem = shopItems.get(position);
        holder.itemImage.setImageResource(shopItem.getResImage());
        holder.itemTitle.setText(shopItem.getTitle());
        holder.itemDescription.setText(shopItem.getDescription());
    }

    @Override
    public int getItemCount() {
        return shopItems == null ? 0 : shopItems.size();
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public static class ItemHolder extends RecyclerView.ViewHolder {
        private ImageView itemImage;
        private TextView itemTitle;
        private TextView itemDescription;

        public ItemHolder(View view) {
            super(view);
            itemImage = (ImageView) view.findViewById(R.id.image_item_detail);
            itemTitle = (TextView) view.findViewById(R.id.text_shop_item_title);
            itemDescription = (TextView) view.findViewById(R.id.text_shop_item_desc);
        }
    }
}