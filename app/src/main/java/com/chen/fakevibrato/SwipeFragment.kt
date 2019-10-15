package com.chen.fakevibrato

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.chen.fakevibrato.base.BaseFragment
import com.chen.fakevibrato.ui.home.presenter.MainPresenter

class SwipeFragment : BaseFragment<MainPresenter>(){
    override fun setView(): Int {
        return R.layout.layout_random_shoot
    }

    override fun initPresenter(): MainPresenter {
        return MainPresenter()
    }

    override fun initView(view: View?) {

    }

    override fun initData(savedInstanceState: Bundle?) {

    }

    override fun Load() {

    }

}