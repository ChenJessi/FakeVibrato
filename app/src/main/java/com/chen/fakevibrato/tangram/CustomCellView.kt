package com.chen.fakevibrato.tangram

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.Nullable
import com.bumptech.glide.Glide
import com.chen.fakevibrato.utils.DisplayUtils
import io.rong.imkit.utilities.RongUtils.dip2px




/**
 * 使用自定义model方式自定义view
 */
class CustomCellView : LinearLayout {
    private var mImageView: ImageView? = null
    private var mTextView: TextView? = null
    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, @Nullable attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, @Nullable attrs: AttributeSet,
                defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    private fun init() {
        orientation = VERTICAL
        gravity = Gravity.CENTER
        val padding = DisplayUtils.dp2px(context, 10f)
        setPadding(padding, padding, padding, padding)
        mImageView = ImageView(context)
        addView(mImageView, DisplayUtils.dp2px(context, 110f), DisplayUtils.dp2px(context, 72f))
        mTextView = TextView(context)
        mTextView?.setPadding(0, padding, 0, 0)
        addView(mTextView, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
    }

    fun setImageUrl(url: String) {
        mImageView?.let { Glide.with(this).load(url).into(it) }
    }

    fun setText(text: String) {
        mTextView?.text = text
    }
}