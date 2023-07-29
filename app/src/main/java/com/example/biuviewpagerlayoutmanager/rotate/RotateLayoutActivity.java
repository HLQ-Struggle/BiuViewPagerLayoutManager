package com.example.biuviewpagerlayoutmanager.rotate;

import com.example.biuviewpagerlayoutmanager.BaseActivity;
import com.example.biuviewpagerlayoutmanager.Util;
import com.example.biuviewpagerlayoutmanager.layoutmanager.RotateLayoutManager;

/**
 * Created by Dajavu on 27/10/2017.
 */

public class RotateLayoutActivity extends BaseActivity<RotateLayoutManager, RotatePopUpWindow> {

    @Override
    protected RotateLayoutManager createLayoutManager() {
        return new RotateLayoutManager(this, Util.Dp2px(this, 10));
    }

    @Override
    protected RotatePopUpWindow createSettingPopUpWindow() {
        return new RotatePopUpWindow(this, getViewPagerLayoutManager(), getRecyclerView());
    }
}
