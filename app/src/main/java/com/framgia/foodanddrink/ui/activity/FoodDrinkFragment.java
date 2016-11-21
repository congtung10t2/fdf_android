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
import com.framgia.foodanddrink.data.model.ShopItem;
import com.framgia.foodanddrink.ui.adapter.FoodDrinkAdapter;
import com.framgia.foodanddrink.utils.AlertDialogUtils;
import com.framgia.foodanddrink.utils.DataTests;
import com.framgia.foodanddrink.utils.UserStorage;

import java.util.ArrayList;
import java.util.List;

public class FoodDrinkFragment extends Fragment implements FoodDrinkAdapter
    .OnItemClickListener {
    private FoodDrinkAdapter adapter;
    private List<FoodDrinkItem> foodAndDrinks = new ArrayList<>();
    private RecyclerView contentFoodDrink;
    ArrayList<ShopItem> shopItemArrayList;
    private View view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.content_food_drink, container, false);
        initViews();
        return view;
    }

    private void initViews() {
        contentFoodDrink = (RecyclerView) view.findViewById(R.id.content_food_drink);
        contentFoodDrink.setLayoutManager(new LinearLayoutManager(getActivity()));
        shopItemArrayList = new ArrayList();
        shopItemArrayList.addAll(UserStorage.getInstance().itemShops);
        shopItemArrayList.addAll(DataTests.shopItems);
        for(ShopItem item : shopItemArrayList)
            foodAndDrinks.addAll(item.list);
        adapter = new FoodDrinkAdapter(foodAndDrinks);
        adapter.setLayoutId(R.layout.food_drink_item_row);
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
    public void onItemAddToCart(int position) {
        UserStorage.getInstance().cart.add(foodAndDrinks.get(position));
        AlertDialogUtils.show(getContext(), "Congratulation!", "Add to cart successfully");
    }
}
