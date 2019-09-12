package com.chen.fakevibrato.ui.my.view

import android.graphics.Color
import android.text.TextUtils
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.chen.fakevibrato.R
import com.chen.fakevibrato.base.BaseActivity
import com.chen.fakevibrato.bean.ButtonBean
import com.chen.fakevibrato.ui.home.adapter.MyPagerAdapter
import com.chen.fakevibrato.ui.home.presenter.MainPresenter
import com.chen.fakevibrato.ui.home.view.HomeListFragment
import com.chen.fakevibrato.utils.DisplayUtils
import com.chen.fakevibrato.utils.MyLog
import com.qmuiteam.qmui.util.QMUIDisplayHelper
import com.qmuiteam.qmui.widget.QMUITabSegment
import kotlinx.android.synthetic.main.activity_contact.*
import kotlinx.android.synthetic.main.fragment_find_friend.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.util.ArrayList

/**
 * @author Created by CHEN on 2019/8/28
 * @email 188669@163.com
 * 联系人
 * 好友列表 and 发现好友
 */
class ContactActivity : BaseActivity<MainPresenter>() {
    private var adapter: MyPagerAdapter? = null
    override fun getLayoutId(): Int {
        return R.layout.activity_contact
    }

    override fun initPresenter(): MainPresenter {
        return MainPresenter()
    }

    override fun initView() {
        val lp = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        lp.height = DisplayUtils.dp2px(this@ContactActivity, 48f) + QMUIDisplayHelper.getStatusBarHeight(this)
        toolbar.setLayoutParams(lp)
        toolbar.setPadding(0, QMUIDisplayHelper.getStatusBarHeight(this), 0, 0)

        val mFragments = ArrayList<Fragment>()
        mFragments.add(FriendFragment())
        mFragments.add(FindFriendFragment())
        adapter = MyPagerAdapter(supportFragmentManager, mFragments)
        viewPager.adapter = adapter

        var tab = QMUITabSegment.Tab(" 好友列表 ")
        tab.setTextColor(Color.parseColor("#A9A9A9"), Color.WHITE)
        tabLayout.addTab(tab)
        var tab1 = QMUITabSegment.Tab(" 发现好友 ")
        tab1.setTextColor(Color.parseColor("#A9A9A9"), Color.WHITE)
        tabLayout.addTab(tab1)

        val drawable = ContextCompat.getDrawable(this@ContactActivity, R.drawable.indicator_yellow)

        tabLayout.setHasIndicator(true)  //是否需要显示indicator
//        tablayout.setIndicatorDrawable();
        tabLayout.setIndicatorPosition(false)//true 时表示 indicator 位置在 Tab 的上方, false 时表示在下方
        tabLayout.setIndicatorWidthAdjustContent(true)//设置 indicator的宽度是否随内容宽度变化
        tabLayout.setIndicatorDrawable(drawable)
        tabLayout.setupWithViewPager(viewPager, false)
        tabLayout.notifyDataChanged()
        tabLayout.selectTab(1, true, true)
    }

    override fun initListener() {

    }

    override fun initData() {

    }

    override fun onBackPressed() {
        if (hasFocus){
            hasFocus = false
            EventBus.getDefault().post(ButtonBean("onBackPressed", FindFriendFragment::class.simpleName.toString()))
        }else{
            super.onBackPressed()
        }
    }
    var hasFocus = false
    @Subscribe(threadMode = ThreadMode.MAIN)
    public fun buttonBean(buttonBean: ButtonBean){
        MyLog.d("ButtonBean : $buttonBean")
        if (TextUtils.equals(buttonBean.type, "hasFocus")){
            if (TextUtils.equals(buttonBean.position, TAG)){
                hasFocus = true
            }

        }
    }
}