package com.chen.fakevibrato.ui.my.adapter

import android.content.Context
import android.transition.Visibility
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import com.bumptech.glide.Glide
import com.chen.fakevibrato.R
import com.chen.fakevibrato.utils.MyLog


/**
 * @author Created by CHEN on 2019/8/29
 * @email 188669@163.com
 */
class FriendAdapter(var mContext : Context, private var mList : List<String>) : RecyclerView.Adapter<FriendAdapter.ViewHolder>() {
    private var onItemClickListener: OnItemClickListener? = null

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_friend, parent, false))
    }

    override fun getItemCount(): Int {
        return 20
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(mContext)
                .load(R.mipmap.logo)
                .circleCrop()
                .into(holder.ivHead)
        holder.ivEdit.setOnClickListener {
            onItemClickListener?.onRemark(position)
        }
        holder.tvMessage.setOnClickListener {
            onItemClickListener?.onMessage(position)
        }
        holder.itemView.setOnClickListener {
            onItemClickListener?.onItemClick(position)
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int, payloads: MutableList<Any>) {
        if (payloads.isEmpty()){
            onBindViewHolder(holder, position)
        }else{
            if (payloads[0] as Boolean){
                holder.ivEdit.visibility = VISIBLE
                holder.tvMessage.visibility = GONE
            }else{
                holder.tvMessage.visibility = VISIBLE
                holder.ivEdit.visibility = GONE
            }
        }

    }
    class ViewHolder(view : View) : RecyclerView.ViewHolder(view){
        var ivHead : ImageView = view.findViewById(R.id.ivHead)
        var tvName : TextView = view.findViewById(R.id.tvName)
        var ivEdit : ImageView = view.findViewById(R.id.ivEdit)
        var tvMessage : ImageView = view.findViewById(R.id.tvMessage)
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
        //编辑备注
        fun onRemark(position: Int)
        //发消息
        fun onMessage(position: Int)
    }
}