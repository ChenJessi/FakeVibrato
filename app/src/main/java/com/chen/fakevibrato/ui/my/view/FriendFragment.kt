package com.chen.fakevibrato.ui.my.view

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.text.InputType
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.chen.fakevibrato.R
import com.chen.fakevibrato.base.BaseFragment
import com.chen.fakevibrato.ui.home.presenter.MainPresenter
import com.chen.fakevibrato.ui.my.adapter.FriendAdapter
import kotlinx.android.synthetic.main.fragment_friend.*
import com.qmuiteam.qmui.widget.dialog.QMUIDialog
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction
import android.text.InputType.TYPE_CLASS_TEXT



/**
 * @author Created by CHEN on 2019/8/29
 * @email 188669@163.com
 * 好友
 */
class FriendFragment : BaseFragment<MainPresenter>() {
    var adapter: FriendAdapter? = null

    var mList = ArrayList<String>()
    override fun setView(): Int {
        return R.layout.fragment_friend
    }

    override fun initPresenter(): MainPresenter {
        return MainPresenter()
    }

    override fun initView(view: View?) {
        mList.add("哦哦哦")
        mList.add("哦哦哦")
        adapter = activity?.let { FriendAdapter(it, mList) }
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = adapter

        initListener()
    }

    override fun initData(savedInstanceState: Bundle?) {

    }

    override fun Load() {

    }

    private fun initListener() {
        tvRemarks.setOnClickListener {
            tvRemarks.visibility = GONE
            tvComplete.visibility = VISIBLE
            adapter?.notifyItemRangeChanged(0, mList.size, true)
        }
        tvComplete.setOnClickListener {
            tvComplete.visibility = GONE
            tvRemarks.visibility = VISIBLE
            adapter?.notifyItemRangeChanged(0, mList.size, false)
        }

        adapter?.setOnItemClickListener(object : FriendAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {

            }

            override fun onRemark(position: Int) {
                showEditTextDialog()
            }

            override fun onMessage(position: Int) {

            }
        })
    }

    private fun showEditTextDialog() {
        val builder = QMUIDialog.EditTextDialogBuilder(activity)
        builder.setTitle("修改备注名")
                .setPlaceholder("在此输入备注名")
                .setInputType(InputType.TYPE_CLASS_TEXT)
                .addAction("取消") { dialog, index -> dialog.dismiss() }
                .addAction("确定") { dialog, index ->
                    val text = builder.editText.text
                    if (text != null && text.length > 0) {

                        dialog.dismiss()
                    } else {
                    }
                }
                .create(R.style.dialogTheme).show()
    }
}