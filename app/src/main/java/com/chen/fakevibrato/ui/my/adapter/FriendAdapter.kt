package com.chen.fakevibrato.ui.my.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chen.fakevibrato.R

/**
 * @author Created by CHEN on 2019/8/29
 * @email 188669@163.com
 */
class FriendAdapter(var mContext : Context, private var mList : List<String>) : RecyclerView.Adapter<FriendAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_friend, parent, false))
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {

    }
}