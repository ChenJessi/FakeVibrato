package com.chen.fakevibrato.tangram

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.Nullable
import com.chen.fakevibrato.R
import com.chen.fakevibrato.utils.DisplayUtils
import io.rong.imkit.utilities.RongUtils.dip2px
import com.tmall.wireless.tangram.structure.CellRender
import com.tmall.wireless.tangram.support.ExposureSupport
import java.util.*
import com.tmall.wireless.tangram.structure.BaseCell as BaseCell1


/**
 * 使用注解方式的自定义View
 */
class CustomAnnotationView : LinearLayout {
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
        orientation = LinearLayout.VERTICAL
        gravity = Gravity.CENTER
        val padding = DisplayUtils.dp2px(context, 10f)
        setPadding(padding, padding, padding, padding)
        mImageView = ImageView(context)
        addView(mImageView, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        mTextView = TextView(context)
        mTextView?.setPadding(0, padding, 0, 0)
        addView(mTextView, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
    }


    @CellRender
    fun cellInited(cell: BaseCell1<*>?) {
        //        setOnClickListener(cell);
        if (cell != null) {
            if (cell.serviceManager != null) {
                val exposureSupport = cell?.serviceManager?.getService(ExposureSupport::class.java)
                if (exposureSupport != null) {
                    exposureSupport!!.onTrace(this, cell, cell.type)
                }
            }
        }
    }

    @CellRender
    fun postBindView(cell: BaseCell1<*>?) {
        if (cell != null) {
            if (cell.pos % 2 === 0) {
                setBackgroundColor(-0xffff01)
                mImageView?.setImageResource(R.mipmap.ic_launcher)
            } else {
                setBackgroundColor(-0xff0001)
                mImageView?.setImageResource(R.mipmap.ic_launcher_round)
            }
        }
        mTextView?.text = String.format(Locale.CHINA, "%s%d: %s", javaClass.simpleName,
                cell?.pos, cell?.optParam("text"))
        setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                Toast.makeText(context,
                        "您点击了组件，type=" + (cell?.stringType ?: "  NULL") + ", pos=" + (cell?.pos ?: -1),
                        Toast.LENGTH_SHORT).show()
            }
        })
    }

    @CellRender
    fun postUnBindView(cell: BaseCell1<*>?) {

    }
}