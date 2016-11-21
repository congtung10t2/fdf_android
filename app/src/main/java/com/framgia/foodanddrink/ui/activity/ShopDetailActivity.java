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
import com.framgia.foodanddrink.ui.adapter.FoodDrinkAdapter;
import com.framgia.foodanddrink.utils.DataTests;
import com.framgia.foodanddrink.utils.UserStorage;

import java.util.ArrayList;
import java.util.List;

public class ShopDetailActivity extends AppCompatActivity {
    private ShopItem item;
    private ActivityShopDetailBinding binding;
    private FoodDrinkAdapter adapter;
    private RecyclerView contentFoodDrink;
    Intent intentNew;

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
        startActivity(intentNew);
    }

    private void initView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_shop_detail);
        Intent intent = getIntent();
        item = intent.getParcelableExtra(Constants.ITEM_INDEX_KEY);
        intentNew = new Intent(this, NewProductActivity.class);
        intentNew.putExtra(Constants.SHOP_INDEX_KEY, item);
        binding.setItem(item);

        contentFoodDrink = (RecyclerView) findViewById(R.id.content_shop_detail);
        contentFoodDrink.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        adapter = new FoodDrinkAdapter(item.list);
        adapter.setLayoutId(R.layout.food_drink_item_row);
        contentFoodDrink.setAdapter(adapter);
    }
}
