package com.chen.fakevibrato.widget.emojipanel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.chen.fakevibrato.R;
import com.chen.fakevibrato.widget.emojiview.EmojiconTextView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Created by CHEN on 2019/7/16
 * @email 188669@163.com
 */
public class EmojiAdapter extends RecyclerView.Adapter<EmojiAdapter.ViewHolder> {
    private Context mContext;
    private List<String> mList = new ArrayList<>();
    private OnItemClickListener onItemClickListener;
    public EmojiAdapter(Context mContext) {
        this.mContext = mContext;
        setEmoji();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public EmojiAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_emoji, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EmojiAdapter.ViewHolder holder, int position) {
        holder.tvEmoji.setText(mList.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null){
                    onItemClickListener.onItemClick(mList.get(position));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        EmojiconTextView tvEmoji;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvEmoji = itemView.findViewById(R.id.tvEmoji);
        }
    }

    public interface OnItemClickListener{
        void onItemClick(String emoji);
    }


    private void setEmoji(){
        mList.add("[微笑]");
        mList.add("[撇嘴]");
        mList.add("[色]");
        mList.add("[发呆]");
        mList.add("[得意]");
        mList.add("[流泪]");
        mList.add("[害羞]");
        mList.add("[闭嘴]");
        mList.add("[睡]");
        mList.add("[大哭]");
        mList.add("[尴尬]");
        mList.add("[发怒]");
        mList.add("[调皮]");
        mList.add("[呲牙]");
        mList.add("[惊讶]");
        mList.add("[难过]");
        mList.add("[酷]");
        mList.add("[冷汗]");
        mList.add("[抓狂]");
        mList.add("[吐]");
        mList.add("[偷笑]");
        mList.add("[可爱]");
        mList.add("[白眼]");
        mList.add("[傲慢]");
        mList.add("[饥饿]");
        mList.add("[困]");
        mList.add("[惊恐]");
        mList.add("[流汗]");
        mList.add("[憨笑]");
        mList.add("[大兵]");
        mList.add("[奋斗]");
        mList.add("[咒骂]");
        mList.add("[疑问]");
        mList.add("[嘘]");
        mList.add("[晕]");
        mList.add("[折磨]");
        mList.add("[衰]");
        mList.add("[骷髅]");
        mList.add("[敲打]");
        mList.add("[再见]");
        mList.add("[擦汗]");
        mList.add("[抠鼻]");
        mList.add("[鼓掌]");
        mList.add("[糗大了]");
        mList.add("[坏笑]");
        mList.add("[左哼哼]");
        mList.add("[右哼哼]");
        mList.add("[哈欠]");
        mList.add("[鄙视]");
        mList.add("[委屈]");
        mList.add("[快哭了]");
        mList.add("[阴险]");
        mList.add("[亲亲]");
        mList.add("[吓]");
        mList.add("[可怜]");
        mList.add("[菜刀]");
        mList.add("[西瓜]");
        mList.add("[啤酒]");
        mList.add("[篮球]");
        mList.add("[乒乓]");
        mList.add("[咖啡]");
        mList.add("[饭]");
        mList.add("[猪头]");
        mList.add("[玫瑰]");
        mList.add("[凋谢]");
        mList.add("[示爱]");
        mList.add("[爱心]");
        mList.add("[心碎]");
        mList.add("[蛋糕]");
        mList.add("[闪电]");
        mList.add("[炸弹]");
        mList.add("[刀]");
        mList.add("[足球]");
        mList.add("[瓢虫]");
        mList.add("[便便]");
        mList.add("[月亮]");
        mList.add("[太阳]");
        mList.add("[礼物]");
        mList.add("[拥抱]");
        mList.add("[强]");
        mList.add("[弱]");
        mList.add("[握手]");
        mList.add("[胜利]");
        mList.add("[抱拳]");
        mList.add("[勾引]");
        mList.add("[拳头]");
        mList.add("[差劲]");
        mList.add("[爱你]");
        mList.add("[NO]");
        mList.add("[OK]");
        mList.add("[爱情]");
        mList.add("[飞吻]");
        mList.add("[跳跳]");
        mList.add("[发抖]");
        mList.add("[怄火]");
        mList.add("[转圈]");
        mList.add("[磕头]");
        mList.add("[回头]");
        mList.add("[跳绳]");
        mList.add("[挥手]");
        mList.add("[激动]");
        mList.add("[街舞]");
        mList.add("[献吻]");
        mList.add("[左太极]");
        mList.add("[右太极]");
    }
}
