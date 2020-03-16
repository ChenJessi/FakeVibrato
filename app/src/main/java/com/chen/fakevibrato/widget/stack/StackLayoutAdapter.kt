package com.chen.fakevibrato.widget.stack

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.chen.fakevibrato.R
import kotlin.random.Random

class StackLayoutAdapter(var mContext : Context, var mList : List<String>) : StackLayout.StackAdapter() {
    override fun getLayoutId(): Int {
        return R.layout.item_stack
    }

    override fun getCount(): Int {
        return mList.size
    }

    override fun bindView(view: View, index: Int) {
        var random = java.util.Random()
        val color = Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256))
        var textView = view.findViewById<TextView>(R.id.textView)
        textView.setBackgroundColor(color)
    }

    override fun getItem(index: Int): Any {
        return mList[index]
    }

}