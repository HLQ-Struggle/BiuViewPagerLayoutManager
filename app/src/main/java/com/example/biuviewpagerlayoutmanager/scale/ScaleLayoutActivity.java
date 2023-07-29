package com.example.biuviewpagerlayoutmanager.scale;

import com.example.biuviewpagerlayoutmanager.BaseActivity;
import com.example.biuviewpagerlayoutmanager.Util;
import com.example.biuviewpagerlayoutmanager.layoutmanager.ScaleLayoutManager;

/**
 * Created by Dajavu on 27/10/2017.
 */

public class ScaleLayoutActivity extends BaseActivity<ScaleLayoutManager, ScalePopUpWindow> {

    @Override
    protected ScaleLayoutManager createLayoutManager() {
        return new ScaleLayoutManager(this, Util.Dp2px(this, 10));
    }

    @Override
    protected ScalePopUpWindow createSettingPopUpWindow() {
        return new ScalePopUpWindow(this, getViewPagerLayoutManager(), getRecyclerView());
    }
}
