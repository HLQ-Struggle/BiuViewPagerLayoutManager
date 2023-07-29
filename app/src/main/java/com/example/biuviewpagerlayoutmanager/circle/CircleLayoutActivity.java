package com.example.biuviewpagerlayoutmanager.circle;

import com.example.biuviewpagerlayoutmanager.BaseActivity;
import com.example.biuviewpagerlayoutmanager.layoutmanager.CircleLayoutManager;

/**
 * Created by Dajavu on 25/10/2017.
 */

public class CircleLayoutActivity extends BaseActivity<CircleLayoutManager, CirclePopUpWindow> {

    @Override
    protected CircleLayoutManager createLayoutManager() {
        return new CircleLayoutManager(this);
    }

    @Override
    protected CirclePopUpWindow createSettingPopUpWindow() {
        return new CirclePopUpWindow(this, getViewPagerLayoutManager(), getRecyclerView());
    }
}
