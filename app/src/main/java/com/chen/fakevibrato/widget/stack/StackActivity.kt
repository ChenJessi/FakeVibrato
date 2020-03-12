package com.chen.fakevibrato.widget.stack

import androidx.dynamicanimation.animation.DynamicAnimation
import androidx.dynamicanimation.animation.SpringAnimation
import androidx.dynamicanimation.animation.SpringForce
import com.chen.baselibrary.base.BasePresenter
import com.chen.fakevibrato.R
import com.chen.fakevibrato.base.BaseSupportActivity
import com.chen.fakevibrato.utils.MyLog
import kotlinx.android.synthetic.main.activity_stack.*

/**
 * stackView 测试
 */
class StackActivity : BaseSupportActivity<BasePresenter<*>>() {
    var mList = ArrayList<String>()
    var adapter : StackLayoutAdapter? = null
    override fun getLayoutId(): Int {
        return R.layout.activity_stack
    }

    override fun initPresenter(): BasePresenter<*>? {
        return null
    }

    override fun initView() {
        mList.add("1")
        mList.add("2")
        mList.add("3")
        mList.add("5")
        mList.add("6")
        mList.add("7")
        mList.add("9")
        mList.add("10")
        mList.add("11")
        mList.add("12")
        mList.add("13")
        mList.add("14")

        adapter = StackLayoutAdapter(mContext, mList)
        adapter?.let { stackLayout.setAdapter(it) }
//        stackView.adapter = StackAdapter(this@StackActivity, mList)
//        var anima : SpringAnimation =
    }

    override fun initListener() {
        button.setOnClickListener {
//            stackView.showNext()
            SpringAnimation(constraintLayout, DynamicAnimation.TRANSLATION_X).apply {
                this.setStartVelocity(4000f)
                val spring = SpringForce(0f)
                spring.stiffness = SpringForce.STIFFNESS_LOW
                spring.dampingRatio = SpringForce.DAMPING_RATIO_HIGH_BOUNCY
                this.spring = spring
                this.minimumVisibleChange = DynamicAnimation.MIN_VISIBLE_CHANGE_PIXELS
            }.start()
        }
        button1.setOnClickListener {
//            stackView.showPrevious()
        }

    }

    override fun initData() {

    }
}