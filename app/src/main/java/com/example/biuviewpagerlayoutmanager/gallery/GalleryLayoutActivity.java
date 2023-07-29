package com.example.biuviewpagerlayoutmanager.gallery;


import com.example.biuviewpagerlayoutmanager.BaseActivity;
import com.example.biuviewpagerlayoutmanager.Util;
import com.example.biuviewpagerlayoutmanager.layoutmanager.GalleryLayoutManager;

/**
 * Created by Dajavu on 27/10/2017.
 */

public class GalleryLayoutActivity extends BaseActivity<GalleryLayoutManager, GalleryPopUpWindow> {

    @Override
    protected GalleryLayoutManager createLayoutManager() {
        return new GalleryLayoutManager(this, Util.Dp2px(this, 10));
    }

    @Override
    protected GalleryPopUpWindow createSettingPopUpWindow() {
        return new GalleryPopUpWindow(this, getViewPagerLayoutManager(), getRecyclerView());
    }
}
