package com.example.biuviewpagerlayoutmanager.carousel;

import com.example.biuviewpagerlayoutmanager.BaseActivity;
import com.example.biuviewpagerlayoutmanager.Util;
import com.example.biuviewpagerlayoutmanager.layoutmanager.CarouselLayoutManager;

/**
 * Created by Dajavu on 27/10/2017.
 */

public class CarouselLayoutActivity extends BaseActivity<CarouselLayoutManager, CarouselPopUpWindow> {

    @Override
    protected CarouselLayoutManager createLayoutManager() {
        return new CarouselLayoutManager(this, Util.Dp2px(this, 100));
    }

    @Override
    protected CarouselPopUpWindow createSettingPopUpWindow() {
        return new CarouselPopUpWindow(this, getViewPagerLayoutManager(), getRecyclerView());
    }
}
