package com.example.biuviewpagerlayoutmanager.circlescale;


import com.example.biuviewpagerlayoutmanager.BaseActivity;
import com.example.biuviewpagerlayoutmanager.layoutmanager.CircleScaleLayoutManager;

/**
 * Created by Dajavu on 27/10/2017.
 */

public class CircleScaleLayoutActivity extends BaseActivity<CircleScaleLayoutManager, CircleScalePopUpWindow> {

    @Override
    protected CircleScaleLayoutManager createLayoutManager() {
        return new CircleScaleLayoutManager(this);
    }

    @Override
    protected CircleScalePopUpWindow createSettingPopUpWindow() {
        return new CircleScalePopUpWindow(this, getViewPagerLayoutManager(), getRecyclerView());
    }
}
