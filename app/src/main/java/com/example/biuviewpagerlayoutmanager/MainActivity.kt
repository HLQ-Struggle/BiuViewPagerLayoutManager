package com.example.biuviewpagerlayoutmanager

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.example.biuviewpagerlayoutmanager.carousel.CarouselLayoutActivity
import com.example.biuviewpagerlayoutmanager.circle.CircleLayoutActivity
import com.example.biuviewpagerlayoutmanager.circlescale.CircleScaleLayoutActivity
import com.example.biuviewpagerlayoutmanager.gallery.GalleryLayoutActivity
import com.example.biuviewpagerlayoutmanager.rotate.RotateLayoutActivity
import com.example.biuviewpagerlayoutmanager.scale.ScaleLayoutActivity

class MainActivity : AppCompatActivity(), OnClickListener {
    companion object {
        const val INTENT_TITLE = "title"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<AppCompatButton>(R.id.bt_circle).setOnClickListener(this)
        findViewById<AppCompatButton>(R.id.bt_circle_scale).setOnClickListener(this)
        findViewById<AppCompatButton>(R.id.bt_elevate_scale).setOnClickListener(this)
        findViewById<AppCompatButton>(R.id.bt_gallery).setOnClickListener(this)
        findViewById<AppCompatButton>(R.id.bt_rotate).setOnClickListener(this)
        findViewById<AppCompatButton>(R.id.bt_scale).setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.bt_circle -> startActivity(CircleLayoutActivity::class.java, v)
            R.id.bt_circle_scale -> startActivity(CircleScaleLayoutActivity::class.java, v)
            R.id.bt_elevate_scale -> startActivity(CarouselLayoutActivity::class.java, v)
            R.id.bt_gallery -> startActivity(GalleryLayoutActivity::class.java, v)
            R.id.bt_rotate -> startActivity(RotateLayoutActivity::class.java, v)
            R.id.bt_scale -> startActivity(ScaleLayoutActivity::class.java, v)
        }
    }

    private fun startActivity(clz: Class<*>, view: View) {
        val intent = Intent(this, clz)
        if (view is AppCompatButton) {
            intent.putExtra(INTENT_TITLE, (view as AppCompatButton).getText())
        }
        startActivity(intent)
    }
}