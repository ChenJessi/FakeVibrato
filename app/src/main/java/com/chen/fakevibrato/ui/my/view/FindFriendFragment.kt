package com.chen.fakevibrato.ui.my.view

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.recyclerview.widget.LinearLayoutManager
import com.chen.fakevibrato.R
import com.chen.fakevibrato.base.BaseSupportFragment
import com.chen.fakevibrato.bean.ButtonBean
import com.chen.fakevibrato.bean.TitleBean
import com.chen.fakevibrato.ui.home.presenter.MainPresenter
import com.chen.fakevibrato.ui.my.adapter.FindFriendAdapter
import com.chen.fakevibrato.utils.MyLog
import com.chen.fakevibrato.widget.decoration.TitleItemDecoration
import kotlinx.android.synthetic.main.fragment_find_friend.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/**
 * @author Created by CHEN on 2019/8/29
 * @email 188669@163.com
 * 发现好友
 */
class FindFriendFragment : BaseSupportFragment<MainPresenter>() {
    var adapter : FindFriendAdapter? = null
    var mList = ArrayList<TitleBean>()
    lateinit var titleItemDecoration : TitleItemDecoration

//     lateinit var loadingView: LoadingView
    override fun setView(): Int {
        return R.layout.fragment_find_friend
    }

    override fun initPresenter(): MainPresenter {
        return MainPresenter()
    }

    override fun initView(view: View?) {
        var bean = TitleBean()
        bean.initial = "new";
        var bean1 = TitleBean()
        bean1.initial = "all";
        mList.add(bean)
        mList.add(bean)
        mList.add(bean)
        mList.add(bean1)
        mList.add(bean1)
        mList.add(bean1)
        mList.add(bean1)
        mList.add(bean1)
        mList.add(bean1)
        mList.add(bean1)
        adapter = activity?.let { FindFriendAdapter(it, mList) }
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = adapter
        titleItemDecoration = TitleItemDecoration(activity, mList)
        recyclerView.addItemDecoration(titleItemDecoration)

        initListener()
    }

    override fun initData(savedInstanceState: Bundle?) {

    }

    override fun Load() {

    }

    fun initListener(){
        adapter?.onItemClickListener = object : FindFriendAdapter.OnItemClickListener{
            override fun onAttention(position: Int) {

            }

            override fun onClose(position: Int) {
                mList.removeAt(position)
                adapter?.notifyDataSetChanged()

            }

            override fun onItemClick(position: Int) {
                edittext.clearFocus()
            }

        }

        edittext.onFocusChangeListener = View.OnFocusChangeListener {
            _, hasFocus ->
            if(hasFocus){
                EventBus.getDefault().post(ButtonBean("hasFocus", "ContactActivity"))
                tvSearch.visibility = VISIBLE
                recyclerView.visibility = GONE
                llSearch.visibility = VISIBLE
            }else{
                tvSearch.visibility = GONE
                recyclerView.visibility = VISIBLE
                llSearch.visibility = GONE
            }
        }

        edittext.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                MyLog.d("TextChange : $s")
                if (TextUtils.isEmpty(s)){
                    tvHint.visibility = GONE
                }else{
                    tvHint.visibility = VISIBLE
                }
                tvContent.text = s
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }
        })

        tvHint.setOnClickListener {
            tvSearch.performClick()
        }
        tvContent.setOnClickListener {
            tvSearch.performClick()
        }
        tvSearch.setOnClickListener {
            var str = edittext.text.toString().trim();
            if (!TextUtils.isEmpty(str)){
                MyLog.e("search  : $str")
                tvHint.visibility = GONE
                tvContent.visibility = GONE
                loadingView.visibility = VISIBLE
                loadingView.start()
            }
        }
        ivContact.setOnClickListener {
            startActivity(Intent(activity, PhoneContactActivity::class.java))
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public fun buttonBean(buttonBean: ButtonBean){
        MyLog.d("ButtonBean : $buttonBean")
        if (TextUtils.equals(buttonBean.type, "onBackPressed")){
            if (TextUtils.equals(buttonBean.position, TAG))
                edittext.clearFocus()
        }
    }

    override fun onPause() {
        super.onPause()
        loadingView.stop()
        loadingView.visibility = GONE
    }
}