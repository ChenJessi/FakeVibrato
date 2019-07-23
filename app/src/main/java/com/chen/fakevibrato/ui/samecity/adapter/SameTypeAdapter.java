package com.chen.fakevibrato.ui.samecity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.chen.fakevibrato.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Created by CHEN on 2019/7/23
 * @email 188669@163.com
 * 同城类型adapter
 */
public class SameTypeAdapter extends RecyclerView.Adapter<SameTypeAdapter.ViewHolder> {


    private Context mContext;

    private String[] datas = new String[]{"美食", "景点", "文化", "玩乐", "酒店", "购物", "运动"};
    private int[] images = new int[]{R.mipmap.food, R.mipmap.attractions, R.mipmap.build, R.mipmap.play, R.mipmap.hotel, R.mipmap.shopping, R.mipmap.motion};

    public SameTypeAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_same_type, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvName.setText(datas[position]);
        holder.image.setImageResource(images[position]);
    }

    @Override
    public int getItemCount() {
        return datas.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.image)
        ImageView image;
        @BindView(R.id.tvName)
        TextView tvName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
