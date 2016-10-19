package com.framgia.fdf_android.ui;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.framgia.fdf_android.R;
import com.framgia.fdf_android.data.Constants;
import com.framgia.fdf_android.data.FoodDrinkItem;
import com.framgia.fdf_android.databinding.ActivityFoodDrinkDetailBinding;

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
