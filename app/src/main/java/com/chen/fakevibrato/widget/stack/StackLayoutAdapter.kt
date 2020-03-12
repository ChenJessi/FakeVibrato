package com.chen.fakevibrato.widget.stack

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.chen.fakevibrato.R

class StackLayoutAdapter(var mContext : Context, var mList : List<String>) : StackLayout.StackAdapter() {
    override fun getLayoutId(): Int {
        return R.layout.item_stack
    }

    override fun getCount(): Int {
        return mList.size
    }

    override fun bindView(view: View, index: Int) {

    }

    override fun getItem(index: Int): Any {
        return mList[index]
    }

}