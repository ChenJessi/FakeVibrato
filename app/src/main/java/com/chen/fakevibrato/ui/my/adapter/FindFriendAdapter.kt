package com.chen.fakevibrato.ui.my.adapter

import android.content.Context
import android.graphics.Color
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.chen.fakevibrato.R
import com.chen.fakevibrato.bean.TitleBean
import com.chen.fakevibrato.widget.emojiview.EmojiconTextView

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
        var title = mList[position]
        Glide.with(mContext)
                .load(R.mipmap.logo)
                .circleCrop()
                .into(holder.ivHead)
        if (TextUtils.equals(title.initial,"new")){
            holder.constraintLayout.setBackgroundColor(Color.parseColor("#24272C"))
        }else {
            holder.constraintLayout.setBackgroundColor(Color.parseColor("#1C1F28"))
        }
    }

    class ViewHolder (view : View) : RecyclerView.ViewHolder(view){
        var ivHead : ImageView = view.findViewById(R.id.ivHead)
        var ivClose : ImageView = view.findViewById(R.id.ivClose)
        var tvAttention : TextView = view.findViewById(R.id.tvAttention)
        var tvName : EmojiconTextView = view.findViewById(R.id.tvName)
        var tvGender : TextView = view.findViewById(R.id.tvGender)
        var tvCity : TextView = view.findViewById(R.id.tvCity)
        var tvSource : TextView = view.findViewById(R.id.tvSource)
        var constraintLayout : ConstraintLayout = view.findViewById(R.id.constraintLayout)


    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

}