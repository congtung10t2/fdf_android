package com.framgia.fdf_android.utils;

import android.support.design.widget.NavigationView;
import android.view.MenuItem;

import com.framgia.fdf_android.R;

/**
 * Created by framgia on 26/10/2016.
 */
public class FoodAndDrinkHelper {
    public static boolean onChangeNavigationItemState(NavigationView navigationView, MenuItem item){
        int groupId = getGroupIdFromTopicId(item.getItemId());
        if(groupId == 0) return false;
        if(item.isChecked() == false){
            item.setChecked(true);
            navigationView.getMenu().setGroupVisible(groupId, true);
            return true;
        }
        item.setChecked(false);
        navigationView.getMenu().setGroupVisible(groupId, false);
        return true;
    }

    public static int getGroupIdFromTopicId(int topicId){
        switch(topicId){
            case (R.id.nav_category):
                return R.id.gr_category;
            case (R.id.nav_feature):
                return R.id.gr_feature;
            default:
                return 0;
        }
    }
}
