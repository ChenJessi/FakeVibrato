package com.chen.fakevibrato.widget.swipe

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.chen.fakevibrato.R
import kotlinx.android.synthetic.main.activity_swipe_test.*

class SwipeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_swipe_test)
        swipeLayout.setScale(1f)
        swipeLayout1.setScale(1f)
    }
}