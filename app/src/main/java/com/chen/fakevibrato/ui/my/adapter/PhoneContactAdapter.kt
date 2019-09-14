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
 * 手机通讯录
 */
class PhoneContactAdapter(var mContext : Context, private var mList : List<String>) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    /**
     * item类型
     */
    private val ITEM_TYPE_HEADER = 0
    private val ITEM_TYPE_CONTENT = 1
    private val ITEM_TYPE_FOOTER = 2
    var onItemClickListener : OnItemClickListener? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ITEM_TYPE_HEADER -> HeaderViewHolder(LayoutInflater.from(mContext).inflate(R.layout.header_phone, parent, false))
            ITEM_TYPE_FOOTER -> FooterViewHolder(LayoutInflater.from(mContext).inflate(R.layout.footer_phone, parent, false))
            else -> ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_phone_contact, parent, false))
        }
    }

    override fun getItemCount(): Int {
       return mList.size + 2
    }

    override fun getItemViewType(position: Int): Int {
        if (position == 0){
            return ITEM_TYPE_HEADER
        }else if (position == mList.size + 1){
            return ITEM_TYPE_FOOTER
        }else{
            return ITEM_TYPE_CONTENT
        }
    }
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if (holder is  ViewHolder){
            var data = mList[position - 1]
            Glide.with(mContext)
                    .load(R.mipmap.logo)
                    .circleCrop()
                    .into(holder.ivHead)
        }

        holder.itemView.setOnClickListener {
            onItemClickListener?.onItemClick(position)
        }
    }

    class ViewHolder (view : View) : RecyclerView.ViewHolder(view){
        var ivHead : ImageView = view.findViewById(R.id.ivHead)
    }
    class HeaderViewHolder (view : View) : RecyclerView.ViewHolder(view)
    class FooterViewHolder (view : View) : RecyclerView.ViewHolder(view)
    interface OnItemClickListener {
        fun onItemClick(position: Int)
        fun onAttention(position: Int)
    }

}