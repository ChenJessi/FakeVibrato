package com.chen.fakevibrato.ui.my.view

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.InputFilter
import android.text.TextUtils
import android.text.TextWatcher
import android.text.method.DigitsKeyListener
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.chen.fakevibrato.R
import com.chen.fakevibrato.base.BaseActivity
import com.chen.fakevibrato.ui.my.presenter.EditNormalPresenter
import com.chen.fakevibrato.ui.my.presenter.UserVideoPresenter
import com.chen.fakevibrato.utils.Constants
import com.chen.fakevibrato.utils.Constants.*
import com.chen.fakevibrato.utils.MyLog
import kotlinx.android.synthetic.main.activity_edit_normal.*

/**
 * 编辑页面
 */
class EditNormalActivtiy : BaseActivity<EditNormalPresenter>() {
    companion object {
        val EDIT_TYPE = "EDIT_TYPE"
        val EDIT_NAME: Int = 10   //昵称
        val EDIT_NUM: Int = 11     //号码
        val EDIT_INTRODUCTION: Int = 12     //号码
    }

    private var type: Int = EDIT_NAME;
    override fun getLayoutId(): Int {
        return R.layout.activity_edit_normal
    }

    override fun initPresenter(): EditNormalPresenter {
        return EditNormalPresenter();
    }

    override fun initView() {
        initToolbar(toolbar)

        type = intent.getIntExtra(EDIT_TYPE, EDIT_NAME)
        when (type) {
            EDIT_NAME -> {
                editText.visibility = VISIBLE
                tvNote.visibility = VISIBLE
                editLines.visibility = GONE
                editText.text = Editable.Factory.getInstance().newEditable(userInfo.name)
                tvTitle.text = "修改昵称"
                tvHint.text = "我的昵称"
                tvNote.text = "3/20"
            }
            EDIT_NUM -> {
                editText.filters = arrayOf<InputFilter>(InputFilter.LengthFilter(16));
                editText.keyListener = DigitsKeyListener.getInstance("" +
                        "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ_.");
                editText.visibility = VISIBLE
                tvNote.visibility = VISIBLE
                editLines.visibility = GONE
                editText.text = Editable.Factory.getInstance().newEditable(userInfo.number)
                tvTitle.text = "修改抖音号"
                tvHint.text = "我的抖音号"
                tvNote.text = "最多16个字，只允许包含字母、数字、下划线和点，30天内仅能修改一次"
            }
            EDIT_INTRODUCTION -> {
                editText.visibility = GONE
                tvNote.visibility = GONE
                editLines.visibility = VISIBLE
                tvTitle.text = "修改简介"
                tvHint.text = "个人简介"
            }
        }

        editText.setSelection(editText.text.length)
        editLines.setSelection(editLines.text.length)

    }

    override fun initListener() {
        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable) {
                if (type == EDIT_INTRODUCTION)return
                if (TextUtils.equals(s.trim(), userInfo.name)){
                    isSave(false)
                }else if (TextUtils.isEmpty(s.trim())){
                    isSave(false)
                }else{
                    isSave(true)
                }
            }
        })
        editLines.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable) {
                if (type != EDIT_INTRODUCTION)return
                if (TextUtils.isEmpty(s.trim())){
                    isSave(false)
                }else{
                    isSave(true)
                }
            }
        })


    }

    override fun initData() {

    }

    /**
     * 是否可以保存
     */
    private fun isSave(isSava: Boolean) {
        tvSave.isEnabled = !isSava
        if (isSava) {
            tvSave.setTextColor(ContextCompat.getColor(this@EditNormalActivtiy, R.color.red1))
        } else {
            tvSave.setTextColor(ContextCompat.getColor(this@EditNormalActivtiy, R.color.red1_50a))
        }
    }

}
