package com.chen.fakevibrato.ui.my.view

import android.text.TextUtils
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import com.bigkoo.pickerview.builder.TimePickerBuilder
import com.bigkoo.pickerview.listener.OnTimeSelectListener
import com.bigkoo.pickerview.view.TimePickerView
import com.chen.fakevibrato.R
import com.chen.fakevibrato.base.DBaseSupportActivity
import com.chen.fakevibrato.bean.UserInfo
import com.chen.fakevibrato.ui.home.presenter.DMainPresenter
import com.chen.fakevibrato.utils.Constants
import com.chen.fakevibrato.utils.ToastUtils
import com.contrarywind.view.WheelView
import com.qmuiteam.qmui.widget.dialog.QMUIDialog
import kotlinx.android.synthetic.main.activity_add_school.*
import org.greenrobot.eventbus.EventBus
import java.util.*

/**
 * @author Created by CHEN on 2019/8/24
 * @email 188669@163.com
 */
class AddSchoolActivity : DBaseSupportActivity<DMainPresenter>(), View.OnClickListener {
    private var pvTime: TimePickerView? = null
    override fun getLayoutId(): Int {
        return R.layout.activity_add_school
    }

    override fun initPresenter(): DMainPresenter {
        return DMainPresenter()
    }

    override fun initView() {
        initToolbar(toolbar)
        initTime();
    }

    override fun initListener() {
        tvSave.setOnClickListener(this)
        tvDepartment.setOnClickListener(this)
        tvSchool.setOnClickListener(this)
        tvTime.setOnClickListener(this)
        tvEducation.setOnClickListener(this)
        tvRange.setOnClickListener(this)
        pvTime?.setOnDismissListener {
            pvTime?.returnData()
        }
    }

    override fun initData() {

    }

    override fun onClick(v: View?) {
        when (v) {
            tvSchool -> {
                tvSchool.text = "ta"
                tvSave.isEnabled =  true
                tvSave.setTextColor(ContextCompat.getColor(this@AddSchoolActivity, R.color.red1))
            }
            tvDepartment -> {
                if (TextUtils.equals(tvSchool.text, "点击设置")) {
                    ToastUtils.showMessage(this@AddSchoolActivity, "请先选择学校")
                } else {

                }
            }
            tvTime -> {
                if (TextUtils.equals(tvSchool.text, "点击设置")) {
                    ToastUtils.showMessage(this@AddSchoolActivity, "请先选择学校")
                } else {
                    pvTime?.show()
                }
            }
            tvEducation -> {
                if (TextUtils.equals(tvSchool.text, "点击设置")) {
                    ToastUtils.showMessage(this@AddSchoolActivity, "请先选择学校")
                } else {
                    showEducationDialog();
                }
            }
            tvRange -> {
            }
            tvSave->{
                Constants.userInfo.school = tvSchool.text.toString()
                EventBus.getDefault().post(UserInfo())
                finish()
            }
        }
    }


    private fun showEducationDialog() {
        val items = arrayOf("专科", "本科", "硕士", "博士")
        QMUIDialog.MenuDialogBuilder(this@AddSchoolActivity)
                .addItems(items) { dialog, which ->
                    Constants.userInfo.gender = items[which]
                    tvEducation.setText(items[which])
                    dialog.dismiss()
                }
                .create().show()
    }

    /**
     * 选择入学时间
     */
    private fun initTime() {
        val selectedDate = Calendar.getInstance()//系统当前时间
        val startDate = Calendar.getInstance()
        startDate.set(1949, 1, 23)
        val endDate = Calendar.getInstance()
        endDate.set(2027, 2, 28)
        //时间选择器
        pvTime = TimePickerBuilder(this, OnTimeSelectListener { date, v ->
            //选中事件回调
            var calendar = Calendar.getInstance()
            calendar.time = date
            tvTime.text = calendar.get(Calendar.YEAR).toString()
        })
                .setDate(selectedDate)
                .setRangDate(startDate, Calendar.getInstance())
                .setLayoutRes(R.layout.dialog_school_time) { v ->
                    val linearLayout : LinearLayout = v.findViewById(R.id.linearLayout)
                    linearLayout.setOnClickListener {}
                }
                .setContentTextSize(18)
                .setType(booleanArrayOf(true, false, false, false, false, false))
                .setLabel("", "", "", "", "", "")
                .setLineSpacingMultiplier(2.2f)
                .setGravity(Gravity.CENTER)
                .isCenterLabel(true) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setDividerColor(-0xb2b2b3)
                .setDividerType(WheelView.DividerType.WRAP)
                .build()


    }
}