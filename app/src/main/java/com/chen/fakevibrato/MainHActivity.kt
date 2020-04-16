package com.chen.fakevibrato


import android.os.Environment
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.chen.baselibrary.base.BaseActivity
import com.chen.baselibrary.fix.FixDexManager

import com.chen.fakevibrato.ui.home.adapter.MyPagerAdapter
import com.chen.fakevibrato.ui.home.contract.MainContract
import com.chen.fakevibrato.ui.home.presenter.DMainPresenter
import com.chen.fakevibrato.ui.home.presenter.MainPresenter
import com.chen.functionmanager.FunctionHasParamNoResult
import com.chen.functionmanager.FunctionManager
import kotlinx.android.synthetic.main.activity_main_h.*
import java.util.ArrayList
import java.io.File


class MainHActivity : BaseActivity<MainPresenter>(), MainContract.View{

    private var adapter: MyPagerAdapter? = null
    private val mFragments = ArrayList<Fragment>()

    override fun getLayoutId(): Int {
        return R.layout.activity_main_h
    }
    override fun initPresenter(): MainPresenter {
        return MainPresenter()
    }

    override fun initView() {
//        fixDexBug()
        FunctionManager.instance.addFunction(object : FunctionHasParamNoResult<Boolean>("mainSwipeLayout") {
            override fun function(p: Boolean) {
                viewPager.setSwipeable(p)
            }
        })

//        mFragments.add(SwipeFragment())
//        mFragments.add(SwipeFragment())
        mFragments.add(MainFragment())
//        mFragments.add(SwipeFragment())

        adapter = MyPagerAdapter(supportFragmentManager, mFragments)
        viewPager.adapter = adapter
        viewPager.setSwipeable(true)
        viewPager.currentItem = 1

    }


    override fun initListener() {
    }

    override fun initData() {

    }

    /**
     * 自己的修复方式
     */
    private fun fixDexBug() {
        val fixFile = File(Environment.getExternalStorageDirectory().absolutePath , "fix.dex")
        
        if (fixFile.exists()) {
            val fixDexManager = FixDexManager(this)
            try {
                fixDexManager.fixDex(fixFile.absolutePath)
                Toast.makeText(this, "修复成功", Toast.LENGTH_LONG).show()
            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(this, "修复失败", Toast.LENGTH_LONG).show()
            }
        }
    }
}
