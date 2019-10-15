package com.chen.fakevibrato.ui.home.adapter;

import android.content.Context;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.chen.fakevibrato.R;
import com.chen.fakevibrato.bean.CommentBean;
import com.chen.fakevibrato.bean.CommentChildBean;
import com.chen.fakevibrato.utils.MyLog;
import com.chen.fakevibrato.widget.CommentSpan;
import com.chen.fakevibrato.widget.TouchableSpan;
import com.chen.fakevibrato.widget.emojiview.EmojiconTextView;
import com.chen.fakevibrato.widget.expandable.BaseExpandableRecyclerViewAdapter;
import com.like.LikeButton;
import com.qmuiteam.qmui.widget.QMUIRadiusImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Created by CHEN on 2019/7/11
 * @email 188669@163.com
 */
public class CommentAdapter extends BaseExpandableRecyclerViewAdapter<CommentBean, CommentChildBean,
        CommentAdapter.GroupViewHolder, CommentAdapter.ChildViewHolder> {
    private Context mContext;
    private List<CommentBean> mList = new ArrayList<>();
    private OnItemClickListener onItemClickListener;
    public CommentAdapter(Context mContext) {
        this.mContext = mContext;
        for (int i = 0; i < 20; i++) {
            CommentBean commentBean = new CommentBean();
            List<CommentChildBean> list = new ArrayList<CommentChildBean>();
            for (int j = 0; j < 5; j++) {
                CommentChildBean childBean = new CommentChildBean();
                childBean.setContent("child : " + j);
                list.add(childBean);
            }
            commentBean.setContent("group : " + i);
            commentBean.setList(list);
            mList.add(commentBean);
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
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
        return new GroupViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_group_comment, parent, false));
    }

    @Override
    public void onBindGroupViewHolder(GroupViewHolder holder, CommentBean groupBean, boolean isExpand) {
        String content = "groupItem content @[蛋糕][呲牙] @chen[爱心] @chen188669  https://www.baidu.com #这是也是话题 #话题@测试  ";
        CommentSpan sp = new CommentSpan();
        contentClick(sp);
        SpannableString timeStr = new SpannableString("12:00");
        RelativeSizeSpan sizeSpan = new RelativeSizeSpan(1f);
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.parseColor("#ABABAB"));
        timeStr.setSpan(sizeSpan, 0, timeStr.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        timeStr.setSpan(colorSpan, 0, timeStr.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        holder.tvContent.setMovementMethod(LinkMovementMethod.getInstance());
        holder.tvContent.setText(sp.setSpan(content,  Color.parseColor("#FFA500")).append(timeStr));

        if (!isExpand){
            holder.tvMore.setVisibility(View.VISIBLE);
        }else {
            holder.tvMore.setVisibility(View.GONE);
        }
        holder.tvName.setText("[胜利]chen");
        holder.tvMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.tvMore.setVisibility(View.GONE);
                expandGroup(groupBean);
            }
        });
    }

    @Override
    public ChildViewHolder onCreateChildViewHolder(ViewGroup parent, int childViewType) {
        return new ChildViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_child_comment, parent, false));
    }

    @Override
    public void onBindChildViewHolder(ChildViewHolder holder, CommentBean groupBean, CommentChildBean commentChildBean) {

        SpannableString replyStr = new SpannableString("chencc[蛋糕]");
        TouchableSpan touchSpan = new TouchableSpan(Color.parseColor("#ABABAB"), Color.parseColor("#ABABAB"), Color.TRANSPARENT, Color.TRANSPARENT) {
            @Override
            public void onSpanClick(View widget) {
                Toast.makeText(mContext, "回复了 :"+replyStr, Toast.LENGTH_SHORT).show();
            }
        };
        replyStr.setSpan(touchSpan,0, replyStr.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);

        String content = "childItem content @[蛋糕][呲牙] @chen[爱心] @chen188669  https://www.baidu.com #这是也是话题 #话题@测试  ";
        CommentSpan sp = new CommentSpan();
        contentClick(sp);
        SpannableString timeStr = new SpannableString("12:00");
        RelativeSizeSpan sizeSpan = new RelativeSizeSpan(0.9f);
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.parseColor("#ABABAB"));
        timeStr.setSpan(sizeSpan, 0, timeStr.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        timeStr.setSpan(colorSpan, 0, timeStr.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);

        holder.tvContent.setMovementMethod(LinkMovementMethod.getInstance());
        holder.tvContent.setText("回复 ");
        holder.tvContent.append(replyStr);
        holder.tvContent.append("：");
        holder.tvContent.append(sp.setSpan(content,  Color.parseColor("#FFA500")).append(timeStr));

        if (commentChildBean == groupBean.getList().get(groupBean.getChildCount() - 1)){
            holder.tvMore.setVisibility(View.VISIBLE);
        }else {
            holder.tvMore.setVisibility(View.GONE);
        }
        holder.tvName.setText("[蛋糕]chen");
        holder.tvMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.tvMore.setVisibility(View.GONE);
                MyLog.e("测试Expand 关闭 =======  "+foldGroup(groupBean));;
                if (onItemClickListener != null){
                    onItemClickListener.onMoreClick(groupBean);
                }
            }
        });

    }

    public class GroupViewHolder extends BaseExpandableRecyclerViewAdapter.BaseGroupViewHolder {
        @BindView(R.id.ivHead)
        QMUIRadiusImageView ivHead;
        @BindView(R.id.tvName)
        EmojiconTextView tvName;
        @BindView(R.id.tvContent)
        EmojiconTextView tvContent;
        @BindView(R.id.tvLikes)
        TextView tvLikes;
        @BindView(R.id.btLikes)
        LikeButton btLikes;
        @BindView(R.id.tvMore)
        TextView tvMore;
        public GroupViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        protected void onExpandStatusChanged(RecyclerView.Adapter relatedAdapter, boolean isExpanding) {
            MyLog.d("isExpanding : " + isExpanding);
        }
    }


    public class ChildViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.ivHead)
        QMUIRadiusImageView ivHead;
        @BindView(R.id.tvName)
        EmojiconTextView tvName;
        @BindView(R.id.tvContent)
        EmojiconTextView tvContent;
        @BindView(R.id.tvLikes)
        TextView tvLikes;
        @BindView(R.id.btLikes)
        LikeButton btLikes;
        @BindView(R.id.tvMore)
        TextView tvMore;
        public ChildViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnItemClickListener extends BaseExpandableRecyclerViewAdapter.OnItemClickListener<CommentBean, CommentChildBean>{
        void onMoreClick(CommentBean groupBean);
    }

    /**
     * span点击事件
     * 可以添加跳转等逻辑
     */
    private void contentClick(CommentSpan sp){
        sp.setOnSpanClick(new CommentSpan.OnSpanClick() {
            @Override
            public void topicClick(String topic) {
                Toast.makeText(mContext, "topic :"+topic, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void atClick(String at) {
                Toast.makeText(mContext, "at :"+at, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void urlClick(String url) {
                Toast.makeText(mContext, "url :"+url, Toast.LENGTH_SHORT).show();
            }
        });
    }

}
