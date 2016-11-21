package com.framgia.foodanddrink.ui.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.framgia.foodanddrink.R;
import com.framgia.foodanddrink.data.Constants;
import com.framgia.foodanddrink.data.model.FoodDrinkItem;
import com.framgia.foodanddrink.data.model.ShopItem;
import com.framgia.foodanddrink.databinding.ActivityFoodDrinkDetailBinding;
import com.framgia.foodanddrink.databinding.ActivityShopDetailBinding;
import com.framgia.foodanddrink.databinding.ActivityShopOtherDetailBinding;
import com.framgia.foodanddrink.ui.adapter.FoodDrinkAdapter;
import com.framgia.foodanddrink.utils.DataTests;
import com.framgia.foodanddrink.utils.UserStorage;

import java.util.ArrayList;
import java.util.List;

public class ShopOtherDetailActivity extends AppCompatActivity
    implements FoodDrinkAdapter.OnItemClickListener {
    private ShopItem item;
    private ActivityShopOtherDetailBinding binding;
    private FoodDrinkAdapter adapter;
    private RecyclerView contentFoodDrink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    @Override
    protected void onResume(){
        super.onResume();
        adapter.notifyDataSetChanged();
    }

    public void newProducts(View view){
        startActivity(new Intent(this, NewProductActivity.class));
    }

    private void initView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_shop_other_detail);
        Intent intent = getIntent();
        item = ShopManagementActivity.currentShop;
        binding.setItem(ShopManagementActivity.currentShop);

        contentFoodDrink = (RecyclerView) findViewById(R.id.content_shop_detail);
        contentFoodDrink.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        adapter = new FoodDrinkAdapter(ShopManagementActivity.currentShop.list);
        adapter.setLayoutId(R.layout.item_show_row);
        adapter.setItemClickListener(this);
        contentFoodDrink.setAdapter(adapter);
        //binding.getRoot().findViewById(R.id.btn_add_products)
    }

    @Override
    public void onItemClick(View view, int position) {
        Intent intent = new Intent(this, FoodDrinkDetailActivity.class);
        intent.putExtra(Constants.ITEM_INDEX_KEY, item.list.get(position));
        startActivity(intent);
    }

    @Override
    public void onItemAddToCart(int position) {
    }
}
