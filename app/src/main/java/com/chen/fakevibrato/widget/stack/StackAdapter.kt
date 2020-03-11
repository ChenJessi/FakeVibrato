package com.chen.fakevibrato.widget.stack

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.chen.fakevibrato.R

class StackAdapter(var mContext : Context, var mList : List<String>) : BaseAdapter() {

    override fun getItem(position: Int): Any {
        return mList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return mList.size
    }

    override fun getView(position: Int, view: View?, parent: ViewGroup?): View? {
        var holder : ViewHolder? = null
        var convertView : View? = view
        if (convertView == null){
            holder = ViewHolder()
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_stack,null)
            convertView?.tag = holder
        }else {
            holder = convertView.tag as ViewHolder?
        }
        holder?.textView = convertView?.findViewById(R.id.textView)
        holder?.textView?.setBackgroundColor(Color.RED)
        holder?.textView?.text = mList[position]
        return convertView
    }




    class ViewHolder{
        var textView : TextView? = null
    }
}