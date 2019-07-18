package com.chen.fakevibrato.ui.home.adapter;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.content.Context;
import android.graphics.Color;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.chen.fakevibrato.R;
import com.chen.fakevibrato.utils.MyLog;
import com.chen.fakevibrato.widget.CommentSpan;
import com.chen.fakevibrato.widget.HeartLayout;
import com.chen.fakevibrato.widget.emojiview.EmojiconTextView;
import com.like.LikeButton;
import com.qmuiteam.qmui.widget.QMUIRadiusImageView;

import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

/**
 * @author Created by CHEN on 2019/7/18
 * @email 188669@163.com
 */
public class HomeListAdapter extends RecyclerView.Adapter<HomeListAdapter.ViewHolder> {
    private Context mContext;
    private List<String> mDatas;
    private OnItemClickListener onItemClickListener;

    public HomeListAdapter(Context mContext, List<String> mDatas) {
        this.mContext = mContext;
        this.mDatas = mDatas;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_home, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText("position : " + mDatas.get(position) + "  :  " + position);
        holder.ivAttention.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attentionAnim(holder.ivAttention, holder.ivAttentionBg);
            }
        });
        recordAnim(holder.ivRecord);
        iconAnim(holder.ivIcom, holder.ivIcom2);
        holder.tvName.setText("@chen188669[爱心]");
        String content = "百无一用是深情，最不屑一顾是相思 https://www.baidu.com @[蛋糕][呲牙] @chen[爱心] @chen188669 #这是[微笑]话题 #这是也是话题 #话题@测试  @艾特#测试";
        CommentSpan sp = new CommentSpan();
        contentClick(sp);

        holder.tvContent.setMovementMethod(LinkMovementMethod.getInstance());
        holder.tvContent.setText(sp.setSpan(content, Color.WHITE));


        holder.tvRecordName.setSelected(true);
        String text = "测试跑马灯[爱心][微笑][微笑][得意]测试[爱心][蛋糕][爱心][爱心][爱心]";
        holder.tvRecordName.setText(text);
        //        holder.tvRecordName.scrollBy();

        holder.heartLayout.setOnLikeListener(new HeartLayout.OnLikeListener() {
            @Override
            public void onClick() {

            }
        });
        holder.tvComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onComment(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.textView)
        TextView textView;
        @BindView(R.id.ivHead)
        QMUIRadiusImageView ivHead;
        @BindView(R.id.ivAttention)
        QMUIRadiusImageView ivAttention;
        @BindView(R.id.ivAttentionBg)
        QMUIRadiusImageView ivAttentionBg;
        @BindView(R.id.tvLikes)
        TextView tvLikes;
        @BindView(R.id.btLikes)
        LikeButton btLikes;
        @BindView(R.id.tvComment)
        TextView tvComment;
        @BindView(R.id.tvReprinted)
        TextView tvReprinted;
        @BindView(R.id.ivRecord)
        ImageView ivRecord;
        @BindView(R.id.tvRecordName)
        EmojiconTextView tvRecordName;
        @BindView(R.id.tvContent)
        EmojiconTextView tvContent;
        @BindView(R.id.tvName)
        EmojiconTextView tvName;
        @BindView(R.id.heartLayout)
        HeartLayout heartLayout;
        @BindView(R.id.ivIcom)
        ImageView ivIcom;
        @BindView(R.id.ivIcom2)
        ImageView ivIcom2;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


    private void iconAnim(ImageView ivIcom, ImageView ivIcom2) {
        //右下角音符动画
        AnimatorSet animator = (AnimatorSet) AnimatorInflater.loadAnimator(mContext, R.animator.icon_anim);
        animator.setTarget(ivIcom);
        animator.start();
        AnimatorSet animator2 = (AnimatorSet) AnimatorInflater.loadAnimator(mContext, R.animator.icon_anim);
        animator2.setTarget(ivIcom2);
        Observable.timer(1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long num) throws Exception {
                        animator2.start();
                    }
                });

    }

    private void recordAnim(ImageView ivRecord) {
        //唱片旋转动画
        AnimatorSet animator = (AnimatorSet) AnimatorInflater.loadAnimator(mContext, R.animator.record);
        animator.setInterpolator(new LinearInterpolator());
        animator.setTarget(ivRecord);
        animator.start();
    }

    /**
     * 关注动画
     * @param ivAttention
     * @param ivAttentionBg
     */
    private void attentionAnim(QMUIRadiusImageView ivAttention, QMUIRadiusImageView ivAttentionBg) {
        AnimatorSet animator = (AnimatorSet) AnimatorInflater.loadAnimator(mContext, R.animator.attention);
        AnimatorSet animator2 = (AnimatorSet) AnimatorInflater.loadAnimator(mContext, R.animator.attention2);
        AnimatorSet animator3 = (AnimatorSet) AnimatorInflater.loadAnimator(mContext, R.animator.attention3);
        AnimatorSet animatorbg = (AnimatorSet) AnimatorInflater.loadAnimator(mContext, R.animator.attention3);
        animator.setTarget(ivAttention);
        animator.start();
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                ivAttention.setImageResource(R.mipmap.hook_red);
                animator2.setTarget(ivAttention);
                animator2.start();
            }
        });

        animator2.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                animator3.setTarget(ivAttention);
                animatorbg.setTarget(ivAttentionBg);
                animator3.start();
                animatorbg.start();
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                super.onAnimationCancel(animation);
                MyLog.d("结束");
            }
        });
    }

    /**
     * 内容span点击监听
     * @param sp
     */
    private void contentClick(CommentSpan sp) {
        sp.setOnSpanClick(new CommentSpan.OnSpanClick() {
            @Override
            public void topicClick(String topic) {
                Toast.makeText(mContext, "topic :" + topic, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void atClick(String at) {
                Toast.makeText(mContext, "at :" + at, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void urlClick(String url) {
                Toast.makeText(mContext, "url :" + url, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public interface OnItemClickListener {
        void onItemClick(int position);

        //点赞
        void onLikes(int position);

        //评论
        void onComment(int position);

        //分享
        void onReprinted(int position);
    }
}
