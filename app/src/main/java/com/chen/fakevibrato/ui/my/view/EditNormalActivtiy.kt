package com.chen.fakevibrato.ui.my.view

import android.text.Editable
import android.text.InputFilter
import android.text.TextUtils
import android.text.TextWatcher
import android.text.method.DigitsKeyListener
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.core.content.ContextCompat
import com.chen.fakevibrato.R
import com.chen.fakevibrato.base.BaseSupportActivity
import com.chen.fakevibrato.bean.UserInfo
import com.chen.fakevibrato.ui.my.presenter.EditNormalPresenter
import com.chen.fakevibrato.utils.Constants
import com.chen.fakevibrato.utils.Constants.*
import com.chen.fakevibrato.utils.MyLog
import kotlinx.android.synthetic.main.activity_edit_normal.*
import org.greenrobot.eventbus.EventBus


/**
 * 编辑页面
 */
class EditNormalActivtiy : BaseSupportActivity<EditNormalPresenter>(), View.OnClickListener {


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
                MyLog.e("beforeTextChanged  s : " )
            }
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                MyLog.e("onTextChanged  s : " )

            }
            override fun afterTextChanged(s: Editable) {
                if (type == EDIT_INTRODUCTION)return
                if (type == EDIT_NAME && TextUtils.equals(s.trim(), userInfo.name)){
                    isSave(false)
                }else if (type == EDIT_NUM && TextUtils.equals(s.trim(), userInfo.number)){
                    isSave(false)
                }else if (TextUtils.isEmpty(s.trim())){
                    isSave(false)
                }else{
                    isSave(true)
                }
                var count = s.length
                tvNote.text = "$count/20"
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

        tvSave.setOnClickListener(this)
        ivBack.setOnClickListener(this)
    }

    override fun initData() {

    }

    /**
     * 是否可以保存
     */
    private fun isSave(isSava: Boolean) {
        tvSave.isEnabled = isSava
        if (isSava) {
            tvSave.setTextColor(ContextCompat.getColor(this@EditNormalActivtiy, R.color.red1))
        } else {
            tvSave.setTextColor(ContextCompat.getColor(this@EditNormalActivtiy, R.color.red1_50a))
        }
    }

    override fun onClick(v: View?) {
        when(v){
            tvSave->{
                when(type){
                    EDIT_NAME -> {
                       Constants.userInfo.name = editText.text.toString()
                    }
                    EDIT_NUM -> {
                        Constants.userInfo.number = editText.text.toString()
                    }
                    EDIT_INTRODUCTION -> {
                        Constants.userInfo.introduction = editLines.text.toString()
                    }
                }
                EventBus.getDefault().post(UserInfo())
                finish()
            }
            ivBack->{
                finish()
            }
        }
    }
}
