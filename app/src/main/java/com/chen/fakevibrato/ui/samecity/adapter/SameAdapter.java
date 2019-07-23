package com.chen.fakevibrato.ui.samecity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.chen.fakevibrato.R;
import com.chen.fakevibrato.utils.MyLog;
import com.chen.fakevibrato.widget.emojiview.EmojiconTextView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Created by CHEN on 2019/7/23
 * @email 188669@163.com
 * 同城video adapter
 */
public class SameAdapter extends RecyclerView.Adapter<SameAdapter.ViewHolder> {
    private Context mContext;


    public SameAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_same, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        lp.height = (int) (Math.random() * 400 + 400);
        holder.textView.setLayoutParams(lp);
    }

    @Override
    public int getItemCount() {
        return 20;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.textView)
        TextView textView;
        @BindView(R.id.tvContent)
        EmojiconTextView tvContent;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
