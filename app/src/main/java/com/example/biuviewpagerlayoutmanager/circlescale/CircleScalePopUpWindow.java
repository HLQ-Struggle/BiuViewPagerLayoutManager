package com.example.biuviewpagerlayoutmanager.circlescale;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.widget.SwitchCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.biuviewpagerlayoutmanager.R;
import com.example.biuviewpagerlayoutmanager.SettingPopUpWindow;
import com.example.biuviewpagerlayoutmanager.Util;
import com.example.biuviewpagerlayoutmanager.layoutmanager.CenterSnapHelper;
import com.example.biuviewpagerlayoutmanager.layoutmanager.CircleScaleLayoutManager;

/**
 * Created by Dajavu on 27/10/2017.
 */

@SuppressLint("InflateParams")
@SuppressWarnings("FieldCanBeLocal")
public class CircleScalePopUpWindow extends SettingPopUpWindow
        implements SeekBar.OnSeekBarChangeListener, CompoundButton.OnCheckedChangeListener,
        RadioGroup.OnCheckedChangeListener {

    private CircleScaleLayoutManager circleScaleLayoutManager;
    private RecyclerView recyclerView;
    private TextView radiusValue;
    private TextView intervalValue;
    private TextView speedValue;
    private TextView centerScaleValue;
    private SwitchCompat infinite;
    private SwitchCompat autoCenter;
    private SwitchCompat reverse;
    private SwitchCompat flipRotate;
    private CenterSnapHelper centerSnapHelper;
    private RadioGroup gravity;
    private RadioGroup zAlignment;

    CircleScalePopUpWindow(Context context, CircleScaleLayoutManager circleScaleLayoutManager, RecyclerView recyclerView) {
        super(context);
        this.circleScaleLayoutManager = circleScaleLayoutManager;
        this.recyclerView = recyclerView;
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_circle_scale_setting, null);
        setContentView(view);

        centerSnapHelper = new CenterSnapHelper();

        SeekBar radius = view.findViewById(R.id.sb_radius);
        SeekBar interval = view.findViewById(R.id.sb_interval);
        SeekBar speed = view.findViewById(R.id.sb_speed);
        SeekBar centerScale = view.findViewById(R.id.sb_center_scale);

        radiusValue = view.findViewById(R.id.radius_value);
        intervalValue = view.findViewById(R.id.interval_value);
        speedValue = view.findViewById(R.id.speed_value);
        centerScaleValue = view.findViewById(R.id.center_scale_value);

        infinite = view.findViewById(R.id.s_infinite);
        autoCenter = view.findViewById(R.id.s_auto_center);
        reverse = view.findViewById(R.id.s_reverse);
        flipRotate = view.findViewById(R.id.s_flip);

        gravity = view.findViewById(R.id.rg_gravity);
        zAlignment = view.findViewById(R.id.rg_z_alignment);

        radius.setOnSeekBarChangeListener(this);
        interval.setOnSeekBarChangeListener(this);
        speed.setOnSeekBarChangeListener(this);
        centerScale.setOnSeekBarChangeListener(this);

        final int maxRadius = Util.Dp2px(radius.getContext(), 400);
        radius.setProgress(Math.round(circleScaleLayoutManager.getRadius() * 1f / maxRadius * 100));
        interval.setProgress(Math.round(circleScaleLayoutManager.getAngleInterval() / 0.9f));
        speed.setProgress(Math.round(circleScaleLayoutManager.getMoveSpeed() / 0.005f));
        centerScale.setProgress(Math.round(circleScaleLayoutManager.getCenterScale() * 200f / 3 - 100f / 3));

        radiusValue.setText(String.valueOf(circleScaleLayoutManager.getRadius()));
        intervalValue.setText(String.valueOf(circleScaleLayoutManager.getAngleInterval()));
        speedValue.setText(Util.formatFloat(circleScaleLayoutManager.getMoveSpeed()));
        centerScaleValue.setText(Util.formatFloat(circleScaleLayoutManager.getCenterScale()));

        infinite.setChecked(circleScaleLayoutManager.getInfinite());
        reverse.setChecked(circleScaleLayoutManager.getReverseLayout());
        flipRotate.setChecked(circleScaleLayoutManager.getFlipRotate());

        infinite.setOnCheckedChangeListener(this);
        autoCenter.setOnCheckedChangeListener(this);
        reverse.setOnCheckedChangeListener(this);
        flipRotate.setOnCheckedChangeListener(this);

        switch (circleScaleLayoutManager.getGravity()) {
            case CircleScaleLayoutManager.LEFT:
                gravity.check(R.id.rb_left);
                break;
            case CircleScaleLayoutManager.RIGHT:
                gravity.check(R.id.rb_right);
                break;
            case CircleScaleLayoutManager.TOP:
                gravity.check(R.id.rb_top);
                break;
            case CircleScaleLayoutManager.BOTTOM:
                gravity.check(R.id.rb_bottom);
                break;
        }

        switch (circleScaleLayoutManager.getZAlignment()) {
            case CircleScaleLayoutManager.LEFT_ON_TOP:
                zAlignment.check(R.id.rb_left_on_top);
                break;
            case CircleScaleLayoutManager.RIGHT_ON_TOP:
                zAlignment.check(R.id.rb_right_on_top);
                break;
            case CircleScaleLayoutManager.CENTER_ON_TOP:
                zAlignment.check(R.id.rb_center_on_top);
                break;
        }

        gravity.setOnCheckedChangeListener(this);
        zAlignment.setOnCheckedChangeListener(this);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        int id = seekBar.getId();
        if (id == R.id.sb_radius) {
            final int maxRadius = Util.Dp2px(seekBar.getContext(), 400);
            final int radius = Math.round(progress / 100f * maxRadius);
            circleScaleLayoutManager.setRadius(radius);
            radiusValue.setText(String.valueOf(radius));
        } else if (id == R.id.sb_interval) {
            final int interval = Math.round(progress * 0.9f);
            circleScaleLayoutManager.setAngleInterval(interval);
            intervalValue.setText(String.valueOf(interval));
        } else if (id == R.id.sb_center_scale) {
            final float scale = (progress + 100f / 3) * 3 / 200;
            circleScaleLayoutManager.setCenterScale(scale);
            centerScaleValue.setText(Util.formatFloat(scale));
        } else if (id == R.id.sb_speed) {
            final float speed = progress * 0.005f;
            circleScaleLayoutManager.setMoveSpeed(speed);
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
            circleScaleLayoutManager.setInfinite(isChecked);
        } else if (id == R.id.s_auto_center) {
            if (isChecked) {
                centerSnapHelper.attachToRecyclerView(recyclerView);
            } else {
                centerSnapHelper.attachToRecyclerView(null);
            }
        } else if (id == R.id.s_reverse) {
            circleScaleLayoutManager.scrollToPosition(0);
            circleScaleLayoutManager.setReverseLayout(isChecked);
        } else if (id == R.id.s_flip) {
            circleScaleLayoutManager.setFlipRotate(isChecked);
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        if (checkedId == R.id.rb_left) {
            circleScaleLayoutManager.setGravity(CircleScaleLayoutManager.LEFT);
        } else if (checkedId == R.id.rb_right) {
            circleScaleLayoutManager.setGravity(CircleScaleLayoutManager.RIGHT);
        } else if (checkedId == R.id.rb_top) {
            circleScaleLayoutManager.setGravity(CircleScaleLayoutManager.TOP);
        } else if (checkedId == R.id.rb_bottom) {
            circleScaleLayoutManager.setGravity(CircleScaleLayoutManager.BOTTOM);
        } else if (checkedId == R.id.rb_left_on_top) {
            circleScaleLayoutManager.setZAlignment(CircleScaleLayoutManager.LEFT_ON_TOP);
        } else if (checkedId == R.id.rb_right_on_top) {
            circleScaleLayoutManager.setZAlignment(CircleScaleLayoutManager.RIGHT_ON_TOP);
        } else if (checkedId == R.id.rb_center_on_top) {
            circleScaleLayoutManager.setZAlignment(CircleScaleLayoutManager.CENTER_ON_TOP);
        }
    }
}
