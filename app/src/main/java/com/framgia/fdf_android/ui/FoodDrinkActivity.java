package com.framgia.fdf_android.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.framgia.fdf_android.R;
import com.framgia.fdf_android.data.FoodDrinkItem;

import java.util.ArrayList;
import java.util.List;

public class FoodDrinkActivity extends AppCompatActivity {
    private FoodDrinkAdapter adapter;
    private List<FoodDrinkItem> foodAndDrinks = new ArrayList<>();
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViews();
    }
    public void initViews(){
        setContentView(R.layout.activity_food_drink);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new FoodDrinkAdapter(foodAndDrinks);
        recyclerView.setAdapter(adapter);
    }
}
