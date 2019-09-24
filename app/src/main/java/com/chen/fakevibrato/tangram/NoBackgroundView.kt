package com.chen.fakevibrato.tangram

import android.content.Context
import android.util.AttributeSet
import android.view.Display
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.Nullable
import com.alibaba.fastjson.util.TypeUtils.getClass
import com.chen.fakevibrato.utils.DisplayUtils
import com.tmall.wireless.tangram.structure.BaseCell
import com.tmall.wireless.tangram.structure.view.ITangramViewLifeCycle
import com.tmall.wireless.tangram.support.ExposureSupport
import io.rong.imkit.utilities.RongUtils.dip2px
import java.util.*


/**
 * 没有设置背景的自定义view
 */
class NoBackgroundView : LinearLayout, ITangramViewLifeCycle {
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
        orientation = LinearLayout.VERTICAL
        gravity = Gravity.CENTER
        val padding = DisplayUtils.dp2px(context, 10f)
        setPadding(padding, padding, padding, padding)
        mTextView = TextView(context)
        mTextView?.textSize = 12f
        addView(mTextView, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
    }


    override fun postBindView(cell: BaseCell<*>?) {
        mTextView?.text = String.format(Locale.CHINA, "%s%d: %s", javaClass.simpleName,
                cell?.pos, cell?.optParam("text"));
    }

    override fun postUnBindView(cell: BaseCell<*>?) {

    }

    override fun cellInited(cell: BaseCell<*>?) {
        setOnClickListener(cell)
        if (cell != null) {
            if (cell.serviceManager != null) {
                val exposureSupport = cell?.serviceManager?.getService(ExposureSupport::class.java)
                if (exposureSupport != null) {
                    exposureSupport!!.onTrace(this, cell, cell.type)
                }
            }
        }
    }
}