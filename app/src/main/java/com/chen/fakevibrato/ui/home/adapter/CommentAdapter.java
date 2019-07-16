package com.chen.fakevibrato.ui.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.chen.fakevibrato.R;
import com.chen.fakevibrato.bean.CommentBean;
import com.chen.fakevibrato.bean.CommentChildBean;
import com.chen.fakevibrato.utils.MyLog;
import com.chen.fakevibrato.widget.expandable.BaseExpandableRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Created by CHEN on 2019/7/11
 * @email 188669@163.com
 */
public class CommentAdapter extends BaseExpandableRecyclerViewAdapter<CommentBean, CommentChildBean,
        CommentAdapter.GroupViewHolder, CommentAdapter.ChildViewHolder> {
    private Context mContext;
    private List<CommentBean> mList = new ArrayList<>();

    public CommentAdapter(Context mContext) {
        this.mContext = mContext;
        for (int i = 0; i < 20; i++) {
            CommentBean commentBean = new CommentBean();
            List<CommentChildBean> list = new ArrayList<CommentChildBean>();
            for (int j = 0; j < 5; j++){
                CommentChildBean childBean = new CommentChildBean();
                childBean.setContent("child : "+ j);
                list.add(childBean);
            }
            commentBean.setContent("group : "+i);
            commentBean.setList(list);
            mList.add(commentBean);
        }
    }

    @Override
    public int getGroupCount() {
        return mList.size();
    }

    @Override
    public CommentBean getGroupItem(int groupIndex) {
        return mList.get(groupIndex);
    }

    @Override
    public GroupViewHolder onCreateGroupViewHolder(ViewGroup parent, int groupViewType) {
        return new GroupViewHolder(LayoutInflater.from(mContext).inflate(R.layout.group_test, parent, false));
    }

    @Override
    public void onBindGroupViewHolder(GroupViewHolder holder, CommentBean groupBean, boolean isExpand) {
        holder.tvGroup.setText(groupBean.getContent() + groupBean.isExpandable());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public ChildViewHolder onCreateChildViewHolder(ViewGroup parent, int childViewType) {
        return new ChildViewHolder(LayoutInflater.from(mContext).inflate(R.layout.child_test, parent, false));
    }

    @Override
    public void onBindChildViewHolder(ChildViewHolder holder, CommentBean groupBean, CommentChildBean commentChildBean) {
        holder.tvChild.setText(commentChildBean.getContent());
    }

    public class GroupViewHolder extends BaseExpandableRecyclerViewAdapter.BaseGroupViewHolder {
        private TextView tvGroup;
        public GroupViewHolder(View itemView) {
            super(itemView);
            tvGroup =  itemView.findViewById(R.id.tvGroup);
        }

        @Override
        protected void onExpandStatusChanged(RecyclerView.Adapter relatedAdapter, boolean isExpanding) {
            MyLog.d("isExpanding : "+isExpanding);
        }
    }


    public class ChildViewHolder extends RecyclerView.ViewHolder {
        private TextView tvChild;
        public ChildViewHolder(@NonNull View itemView) {
            super(itemView);
            tvChild =  itemView.findViewById(R.id.tvChild);
        }
    }
}
