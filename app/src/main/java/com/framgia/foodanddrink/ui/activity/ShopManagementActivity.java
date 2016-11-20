package com.framgia.foodanddrink.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.framgia.foodanddrink.R;
import com.framgia.foodanddrink.data.model.ShopItem;
import com.framgia.foodanddrink.ui.adapter.ShopAdapter;
import com.framgia.foodanddrink.utils.DataTests;
import com.framgia.foodanddrink.utils.UserStorage;

import java.util.ArrayList;
import java.util.List;

public class ShopManagementActivity extends AppCompatActivity implements View.OnClickListener{
    private ShopAdapter adapter;
    private List<ShopItem> shopItems = new ArrayList<>();
    private RecyclerView shopItemView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_management);
        initViews();
    }

    private void initViews() {
        shopItemView = (RecyclerView) findViewById(R.id.content_shops);
        shopItemView.setLayoutManager(new LinearLayoutManager(this));
        DataTests.fakeListShop(shopItems);
        shopItems.addAll(UserStorage.getInstance().itemShops);
        adapter = new ShopAdapter(shopItems);
        shopItemView.setAdapter(adapter);
        findViewById(R.id.btn_request_shop).setOnClickListener(this);
    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.btn_request_shop:
                //add new shop
        }
    }
}
