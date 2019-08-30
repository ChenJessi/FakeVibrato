package com.chen.fakevibrato.ui.my.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
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
        Glide.with(mContext)
                .load(R.mipmap.logo)
                .circleCrop()
                .into(holder.ivHead)
    }

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view){
        var ivHead : ImageView = view.findViewById(R.id.ivHead)
        var tvName : TextView = view.findViewById(R.id.tvName)
        var ivEdit : ImageView = view.findViewById(R.id.ivEdit)
        var tvMessage : ImageView = view.findViewById(R.id.tvMessage)
    }
}