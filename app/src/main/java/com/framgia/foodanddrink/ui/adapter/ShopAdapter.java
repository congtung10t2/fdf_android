package com.framgia.foodanddrink.ui.adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.framgia.foodanddrink.R;
import com.framgia.foodanddrink.data.model.ShopItem;

import java.io.File;
import java.util.List;

/**
 * Created by framgia on 18/10/2016.
 */
public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.ItemHolder> {
    private List<ShopItem> shopItems;
    private OnItemClickListener itemClickListener;
    private ItemHolder holder;
    private int layoutId;

    public int getLayoutId() {
        return layoutId;
    }

    public void setLayoutId(int layoutId) {
        this.layoutId = layoutId;
    }


    public ShopAdapter(List<ShopItem> shopItems) {
        this.shopItems = shopItems;
    }

    public void setItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflatedView = LayoutInflater.from(parent.getContext())
            .inflate(layoutId, parent, false);
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
        File imgFile = new  File(shopItem.getAvatar());
        if(imgFile.exists()){
            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            holder.itemImage.setImageBitmap(myBitmap);
            return;
        }
        holder.itemImage.setImageResource(R.drawable.ic_shop_avatar);
        if (holder.coverImage != null)
        {
            File imageFile = new  File(shopItem.getCoverImage());
            if(imageFile.exists()){
                Bitmap myBitmap = BitmapFactory.decodeFile(imageFile.getAbsolutePath());
                holder.coverImage.setImageBitmap(myBitmap);
                return;
            }
            holder.coverImage.setImageResource(R.drawable.ic_shop_avatar);
        }

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
        private ImageView coverImage;

        public ItemHolder(View view) {
            super(view);
            itemImage = (ImageView) view.findViewById(R.id.image_shop_avatar_detail);
            itemTitle = (TextView) view.findViewById(R.id.text_shop_item_title);
            itemDescription = (TextView) view.findViewById(R.id.text_shop_item_desc);
            coverImage = (ImageView) view.findViewById(R.id.image_item_shop_cover);
        }
    }
}