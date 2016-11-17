package com.framgia.foodanddrink.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.framgia.foodanddrink.R;
import com.framgia.foodanddrink.data.Constants;
import com.framgia.foodanddrink.data.model.FoodDrinkItem;
import com.framgia.foodanddrink.ui.adapter.FoodDrinkAdapter;
import com.framgia.foodanddrink.utils.DataTests;

import java.util.ArrayList;
import java.util.List;

public class FoodDrinkFragment extends Fragment implements FoodDrinkAdapter
    .OnItemClickListener {
    private FoodDrinkAdapter adapter;
    private List<FoodDrinkItem> foodAndDrinks = new ArrayList<>();
    private RecyclerView contentFoodDrink;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.content_food_drink, container, false);
        initViews();
        return view;
    }

    private void initViews() {
        contentFoodDrink = (RecyclerView) getView().findViewById(R.id.content_food_drink);
        contentFoodDrink.setLayoutManager(new LinearLayoutManager(getContext()));
        DataTests.fakeListFoodDrink(foodAndDrinks);
        adapter = new FoodDrinkAdapter(foodAndDrinks);
        adapter.setItemClickListener(this);
        contentFoodDrink.setAdapter(adapter);
    }

    @Override
    public void onItemClick(View view, int position) {
        Intent intent = new Intent(getContext(), FoodDrinkDetailActivity.class);
        intent.putExtra(Constants.ITEM_INDEX_KEY, foodAndDrinks.get(position));
        startActivity(intent);
    }

    @Override
    public void onItemQuickOrder(int position) {
        //TODO: Send request quick order
    }
}
