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
import com.framgia.foodanddrink.ui.adapter.ShopAdapter;
import com.framgia.foodanddrink.utils.DataTests;
import com.framgia.foodanddrink.utils.UserStorage;

import java.util.ArrayList;
import java.util.List;

public class ShopFragment extends Fragment implements ShopAdapter
    .OnItemClickListener {
    private ShopAdapter adapter;
    private List<ShopItem> shopItems = new ArrayList<>();
    private RecyclerView shopItemView;
    private View view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.content_shops, container, false);
        initViews();
        return view;
    }

    private void initViews() {
        shopItemView = (RecyclerView) view.findViewById(R.id.content_shops);
        shopItemView.setLayoutManager(new LinearLayoutManager(getActivity()));
        shopItems.clear();
        shopItems.addAll(UserStorage.getInstance().itemShops);
        shopItems.addAll(DataTests.shopItems);
        adapter = new ShopAdapter(shopItems);
        adapter.setLayoutId(R.layout.shop_item_row);
        adapter.setItemClickListener(this);
        shopItemView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(View view, int position) {
       // Intent intent = new Intent(getContext(), FoodDrinkDetailActivity.class);
     //   intent.putExtra(Constants.ITEM_INDEX_KEY, shopItems.get(position));
      //  startActivity(intent);
    }
}
