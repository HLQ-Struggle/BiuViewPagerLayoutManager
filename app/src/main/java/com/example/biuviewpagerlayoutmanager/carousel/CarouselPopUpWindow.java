package com.example.biuviewpagerlayoutmanager.carousel;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.widget.SwitchCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.biuviewpagerlayoutmanager.R;
import com.example.biuviewpagerlayoutmanager.SettingPopUpWindow;
import com.example.biuviewpagerlayoutmanager.Util;
import com.example.biuviewpagerlayoutmanager.layoutmanager.CarouselLayoutManager;
import com.example.biuviewpagerlayoutmanager.layoutmanager.CenterSnapHelper;
import com.example.biuviewpagerlayoutmanager.layoutmanager.ViewPagerLayoutManager;

/**
 * Created by Dajavu on 27/10/2017.
 */

@SuppressLint("InflateParams")
@SuppressWarnings("FieldCanBeLocal")
public class CarouselPopUpWindow extends SettingPopUpWindow
        implements SeekBar.OnSeekBarChangeListener, CompoundButton.OnCheckedChangeListener {

    private CarouselLayoutManager carouselLayoutManager;
    private RecyclerView recyclerView;
    private TextView itemSpaceValue;
    private TextView speedValue;
    private TextView minScaleValue;
    private SwitchCompat changeOrientation;
    private SwitchCompat autoCenter;
    private SwitchCompat infinite;
    private SwitchCompat reverse;
    private CenterSnapHelper centerSnapHelper;

    CarouselPopUpWindow(Context context, CarouselLayoutManager carouselLayoutManager, RecyclerView recyclerView) {
        super(context);
        this.carouselLayoutManager = carouselLayoutManager;
        this.recyclerView = recyclerView;
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_carousel_setting, null);
        setContentView(view);

        centerSnapHelper = new CenterSnapHelper();

        SeekBar itemSpace = view.findViewById(R.id.sb_item_space);
        SeekBar speed = view.findViewById(R.id.sb_speed);
        SeekBar minScale = view.findViewById(R.id.sb_min_scale);

        itemSpaceValue = view.findViewById(R.id.item_space);
        speedValue = view.findViewById(R.id.speed_value);
        minScaleValue = view.findViewById(R.id.min_scale_value);

        changeOrientation = view.findViewById(R.id.s_change_orientation);
        autoCenter = view.findViewById(R.id.s_auto_center);
        infinite = view.findViewById(R.id.s_infinite);
        reverse = view.findViewById(R.id.s_reverse);

        itemSpace.setOnSeekBarChangeListener(this);
        speed.setOnSeekBarChangeListener(this);
        minScale.setOnSeekBarChangeListener(this);

        itemSpace.setProgress(carouselLayoutManager.getItemSpace() / 5);
        speed.setProgress(Math.round(carouselLayoutManager.getMoveSpeed() / 0.05f));
        minScale.setProgress(Math.round(carouselLayoutManager.getMinScale() * 100));

        itemSpaceValue.setText(String.valueOf(carouselLayoutManager.getItemSpace()));
        speedValue.setText(Util.formatFloat(carouselLayoutManager.getMoveSpeed()));
        minScaleValue.setText(Util.formatFloat(carouselLayoutManager.getMinScale()));

        changeOrientation.setChecked(carouselLayoutManager.getOrientation() == ViewPagerLayoutManager.VERTICAL);
        reverse.setChecked(carouselLayoutManager.getReverseLayout());
        infinite.setChecked(carouselLayoutManager.getInfinite());

        changeOrientation.setOnCheckedChangeListener(this);
        autoCenter.setOnCheckedChangeListener(this);
        reverse.setOnCheckedChangeListener(this);
        infinite.setOnCheckedChangeListener(this);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        int id = seekBar.getId();
        if (id == R.id.sb_item_space) {
            int itemSpace = progress * 5;
            carouselLayoutManager.setItemSpace(itemSpace);
            itemSpaceValue.setText(String.valueOf(itemSpace));
        } else if (id == R.id.sb_min_scale) {
            final float scale = progress / 100f;
            carouselLayoutManager.setMinScale(scale);
            minScaleValue.setText(Util.formatFloat(scale));
        } else if (id == R.id.sb_speed) {
            final float speed = progress * 0.05f;
            carouselLayoutManager.setMoveSpeed(speed);
            speedValue.setText(Util.formatFloat(speed));
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        int id = buttonView.getId();
        if (id == R.id.s_infinite) {
            recyclerView.scrollToPosition(0);
            carouselLayoutManager.setInfinite(isChecked);
        } else if (id == R.id.s_change_orientation) {
            carouselLayoutManager.scrollToPosition(0);
            carouselLayoutManager.setOrientation(isChecked ?
                    RecyclerView.VERTICAL : RecyclerView.HORIZONTAL);
        } else if (id == R.id.s_auto_center) {
            if (isChecked) {
                centerSnapHelper.attachToRecyclerView(recyclerView);
            } else {
                centerSnapHelper.attachToRecyclerView(null);
            }
        } else if (id == R.id.s_reverse) {
            carouselLayoutManager.scrollToPosition(0);
            carouselLayoutManager.setReverseLayout(isChecked);
        }
    }
}
