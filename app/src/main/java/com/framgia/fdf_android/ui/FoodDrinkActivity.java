package com.framgia.fdf_android.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.framgia.fdf_android.R;
import com.framgia.fdf_android.data.Constants;
import com.framgia.fdf_android.data.FoodDrinkItem;

import java.util.ArrayList;
import java.util.List;

public class FoodDrinkActivity extends AppCompatActivity implements FoodDrinkAdapter
    .OnItemClickListener {
    private FoodDrinkAdapter adapter;
    private List<FoodDrinkItem> foodAndDrinks = new ArrayList<>();
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViews();
    }

    public void initViews() {
        setContentView(R.layout.activity_food_drink);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_news);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new FoodDrinkAdapter(foodAndDrinks);
        adapter.setItemClickListener(this);
        recyclerView.setAdapter(adapter);
    }
    @Override
    public void onItemClick(View view, int position) {
        Intent intent = new Intent(FoodDrinkActivity.this, FoodDrinkDetailActivity.class);
        intent.putExtra(Constants.ITEM_INDEX_KEY, foodAndDrinks.get(position));
        startActivity(intent);
    }
}
