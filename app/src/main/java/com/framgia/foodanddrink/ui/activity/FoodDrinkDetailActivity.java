package com.framgia.foodanddrink.ui.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.framgia.foodanddrink.R;
import com.framgia.foodanddrink.data.Constants;
import com.framgia.foodanddrink.data.model.FoodDrinkItem;
import com.framgia.foodanddrink.databinding.ActivityFoodDrinkDetailBinding;

public class FoodDrinkDetailActivity extends AppCompatActivity {
    private FoodDrinkItem item;
    private ActivityFoodDrinkDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    public void initView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_food_drink_detail);
        Intent intent = getIntent();
        item = intent.getParcelableExtra(Constants.ITEM_INDEX_KEY);
        binding.setItem(item);
    }
}
