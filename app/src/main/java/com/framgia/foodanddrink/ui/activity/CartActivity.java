package com.framgia.foodanddrink.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.framgia.foodanddrink.R;
import com.framgia.foodanddrink.data.model.FoodDrinkItem;
import com.framgia.foodanddrink.ui.adapter.FoodDrinkAdapter;
import com.framgia.foodanddrink.utils.AlertDialogUtils;
import com.framgia.foodanddrink.utils.DataTests;
import com.framgia.foodanddrink.utils.UserStorage;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {
    private FoodDrinkAdapter adapter;
    private RecyclerView contentFoodDrink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        initViews();
    }
    private void initViews(){
        contentFoodDrink = (RecyclerView)findViewById(R.id.content_food_drink);
        contentFoodDrink.setLayoutManager(new LinearLayoutManager(this));
        adapter = new FoodDrinkAdapter(UserStorage.getInstance().cart);
        adapter.setLayoutId(R.layout.cart_item_row);
        contentFoodDrink.setAdapter(adapter);
    }

    public void Ordered(View view) {
        UserStorage.getInstance().ordered.addAll(UserStorage.getInstance().cart);
        UserStorage.getInstance().cart.clear();
        adapter.notifyDataSetChanged();
        AlertDialogUtils.show(this, "Congratulation!", "Order Successfully");
    }
}
