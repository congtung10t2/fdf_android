package com.framgia.foodanddrink.ui.view;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by framgia on 15/11/2016.
 */
public class ActivityStateManager {
    private static ActivityStateManager instance = new ActivityStateManager();

    public static ActivityStateManager getInstance() {
        return instance;
    }

    private ActivityStateManager() {
    }
    List<IActivityState> activityStates = new ArrayList();

    public void init(){
        activityStates.clear();
    }

    public void add(IActivityState activityState){
        if(activityStates.contains(activityState)) return;
        activityStates.add(activityState);
    }

    public void remove(IActivityState activityState){
        if(!activityStates.contains(activityState)) return;
        activityStates.remove(activityState);
    }

    public void onCreate(){
    }
}
