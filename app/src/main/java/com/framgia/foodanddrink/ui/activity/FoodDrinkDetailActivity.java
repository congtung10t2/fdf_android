package com.framgia.foodanddrink.ui.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.framgia.foodanddrink.R;
import com.framgia.foodanddrink.data.Constants;
import com.framgia.foodanddrink.data.model.FoodDrinkItem;
import com.framgia.foodanddrink.databinding.ActivityFoodDrinkDetailBinding;
import com.framgia.foodanddrink.utils.AlertDialogUtils;
import com.framgia.foodanddrink.utils.UserStorage;

public class FoodDrinkDetailActivity extends AppCompatActivity {
    private FoodDrinkItem item;
    private ActivityFoodDrinkDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_food_drink_detail);
        Intent intent = getIntent();
        item = intent.getParcelableExtra(Constants.ITEM_INDEX_KEY);
        binding.setItem(item);
    }

    public void addToCart(View view){
        UserStorage.getInstance().cart.add(item);
        AlertDialogUtils.show(this, "Congratulation!", "Add to cart successfully");
    }
}
