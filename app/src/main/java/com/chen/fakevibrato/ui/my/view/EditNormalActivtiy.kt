package com.chen.fakevibrato.ui.my.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.chen.fakevibrato.R
import com.chen.fakevibrato.base.BaseActivity
import com.chen.fakevibrato.ui.my.presenter.EditNormalPresenter
import com.chen.fakevibrato.ui.my.presenter.UserVideoPresenter
import kotlinx.android.synthetic.main.activity_edit_normal.*

/**
 * 编辑页面
 */
class EditNormalActivtiy : BaseActivity<EditNormalPresenter>() {

    override fun getLayoutId(): Int {
        return R.layout.activity_edit_normal
    }

    override fun initPresenter(): EditNormalPresenter {
        return EditNormalPresenter();
    }

    override fun initView() {
        initToolbar(toolbar)
    }

    override fun initListener() {

    }

    override fun initData() {

    }

}
