package com.chen.fakevibrato.ui.my.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chen.fakevibrato.R
import com.chen.fakevibrato.bean.TitleBean

/**
 * 寻找好友
 */
class FindFriendAdapter(var mContext : Context, private var mList : List<TitleBean>) : RecyclerView.Adapter<FindFriendAdapter.ViewHolder>(){

    var onItemClickListener : OnItemClickListener? = null



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_find_friend, parent, false))
    }

    override fun getItemCount(): Int {
       return mList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }

    class ViewHolder (view : View) : RecyclerView.ViewHolder(view){

    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

}