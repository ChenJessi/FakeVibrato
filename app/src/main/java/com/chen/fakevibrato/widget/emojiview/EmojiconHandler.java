package com.chen.fakevibrato.widget.emojiview;

import android.content.Context;
import android.content.res.Resources;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.util.ArrayMap;
import android.util.Log;
import android.util.SparseIntArray;

import com.chen.fakevibrato.R;
import com.chen.fakevibrato.utils.DisplayUtils;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * emoji
 * @author Created by CHEN on 2019/7/5
 * @email 188669@163.com
 */
public final class EmojiconHandler {
    private static final HashMap<String, Integer> sQQFaceMap = new HashMap<>();
    private static final List<QQFace> mQQFaceList = new ArrayList<>();
    private static final SparseIntArray sEmojisMap = new SparseIntArray(846);
    private static final SparseIntArray sSoftbanksMap = new SparseIntArray(471);
    private static final ArrayMap<String, String> mQQFaceFileNameList = new ArrayMap<>();//存储QQ表情对应的文件名,方便混淆后可以获取到原文件名
    /**
     * 表情的放大倍数
     */
    private static final float EMOJIICON_SCALE = 1.2f;
    /**
     * 表情的偏移值
     */
    private static final int EMOJIICON_TRANSLATE_Y = 0;

    private static final int QQFACE_TRANSLATE_Y = 1;  //单位dp

    static {
        long start = System.currentTimeMillis();

        mQQFaceList.add(new QQFace("[微笑]", R.drawable.smiley_0));
        mQQFaceList.add(new QQFace("[撇嘴]", R.drawable.smiley_1));
        mQQFaceList.add(new QQFace("[色]", R.drawable.smiley_2));
        mQQFaceList.add(new QQFace("[发呆]", R.drawable.smiley_3));
        mQQFaceList.add(new QQFace("[得意]", R.drawable.smiley_4));
        mQQFaceList.add(new QQFace("[流泪]", R.drawable.smiley_5));
        mQQFaceList.add(new QQFace("[害羞]", R.drawable.smiley_6));
        mQQFaceList.add(new QQFace("[闭嘴]", R.drawable.smiley_7));
        mQQFaceList.add(new QQFace("[睡]", R.drawable.smiley_8));
        mQQFaceList.add(new QQFace("[大哭]", R.drawable.smiley_9));
        mQQFaceList.add(new QQFace("[尴尬]", R.drawable.smiley_10));
        mQQFaceList.add(new QQFace("[发怒]", R.drawable.smiley_11));
        mQQFaceList.add(new QQFace("[调皮]", R.drawable.smiley_12));
        mQQFaceList.add(new QQFace("[呲牙]", R.drawable.smiley_13));
        mQQFaceList.add(new QQFace("[惊讶]", R.drawable.smiley_14));
        mQQFaceList.add(new QQFace("[难过]", R.drawable.smiley_15));
        mQQFaceList.add(new QQFace("[酷]", R.drawable.smiley_16));
        mQQFaceList.add(new QQFace("[冷汗]", R.drawable.smiley_17));
        mQQFaceList.add(new QQFace("[抓狂]", R.drawable.smiley_18));
        mQQFaceList.add(new QQFace("[吐]", R.drawable.smiley_19));
        mQQFaceList.add(new QQFace("[偷笑]", R.drawable.smiley_20));
        mQQFaceList.add(new QQFace("[可爱]", R.drawable.smiley_21));
        mQQFaceList.add(new QQFace("[白眼]", R.drawable.smiley_22));
        mQQFaceList.add(new QQFace("[傲慢]", R.drawable.smiley_23));
        mQQFaceList.add(new QQFace("[饥饿]", R.drawable.smiley_24));
        mQQFaceList.add(new QQFace("[困]", R.drawable.smiley_25));
        mQQFaceList.add(new QQFace("[惊恐]", R.drawable.smiley_26));
        mQQFaceList.add(new QQFace("[流汗]", R.drawable.smiley_27));
        mQQFaceList.add(new QQFace("[憨笑]", R.drawable.smiley_28));
        mQQFaceList.add(new QQFace("[大兵]", R.drawable.smiley_29));
        mQQFaceList.add(new QQFace("[奋斗]", R.drawable.smiley_30));
        mQQFaceList.add(new QQFace("[咒骂]", R.drawable.smiley_31));
        mQQFaceList.add(new QQFace("[疑问]", R.drawable.smiley_32));
        mQQFaceList.add(new QQFace("[嘘]", R.drawable.smiley_33));
        mQQFaceList.add(new QQFace("[晕]", R.drawable.smiley_34));
        mQQFaceList.add(new QQFace("[折磨]", R.drawable.smiley_35));
        mQQFaceList.add(new QQFace("[衰]", R.drawable.smiley_36));
        mQQFaceList.add(new QQFace("[骷髅]", R.drawable.smiley_37));
        mQQFaceList.add(new QQFace("[敲打]", R.drawable.smiley_38));
        mQQFaceList.add(new QQFace("[再见]", R.drawable.smiley_39));
        mQQFaceList.add(new QQFace("[擦汗]", R.drawable.smiley_40));
        mQQFaceList.add(new QQFace("[抠鼻]", R.drawable.smiley_41));
        mQQFaceList.add(new QQFace("[鼓掌]", R.drawable.smiley_42));
        mQQFaceList.add(new QQFace("[糗大了]", R.drawable.smiley_43));
        mQQFaceList.add(new QQFace("[坏笑]", R.drawable.smiley_44));
        mQQFaceList.add(new QQFace("[左哼哼]", R.drawable.smiley_45));
        mQQFaceList.add(new QQFace("[右哼哼]", R.drawable.smiley_46));
        mQQFaceList.add(new QQFace("[哈欠]", R.drawable.smiley_47));
        mQQFaceList.add(new QQFace("[鄙视]", R.drawable.smiley_48));
        mQQFaceList.add(new QQFace("[委屈]", R.drawable.smiley_49));
        mQQFaceList.add(new QQFace("[快哭了]", R.drawable.smiley_50));
        mQQFaceList.add(new QQFace("[阴险]", R.drawable.smiley_51));
        mQQFaceList.add(new QQFace("[亲亲]", R.drawable.smiley_52));
        mQQFaceList.add(new QQFace("[吓]", R.drawable.smiley_53));
        mQQFaceList.add(new QQFace("[可怜]", R.drawable.smiley_54));
        mQQFaceList.add(new QQFace("[菜刀]", R.drawable.smiley_55));
        mQQFaceList.add(new QQFace("[西瓜]", R.drawable.smiley_56));
        mQQFaceList.add(new QQFace("[啤酒]", R.drawable.smiley_57));
        mQQFaceList.add(new QQFace("[篮球]", R.drawable.smiley_58));
        mQQFaceList.add(new QQFace("[乒乓]", R.drawable.smiley_59));
        mQQFaceList.add(new QQFace("[咖啡]", R.drawable.smiley_60));
        mQQFaceList.add(new QQFace("[饭]", R.drawable.smiley_61));
        mQQFaceList.add(new QQFace("[猪头]", R.drawable.smiley_62));
        mQQFaceList.add(new QQFace("[玫瑰]", R.drawable.smiley_63));
        mQQFaceList.add(new QQFace("[凋谢]", R.drawable.smiley_64));
        mQQFaceList.add(new QQFace("[示爱]", R.drawable.smiley_65));
        mQQFaceList.add(new QQFace("[爱心]", R.drawable.smiley_66));
        mQQFaceList.add(new QQFace("[心碎]", R.drawable.smiley_67));
        mQQFaceList.add(new QQFace("[蛋糕]", R.drawable.smiley_68));
        mQQFaceList.add(new QQFace("[闪电]", R.drawable.smiley_69));
        mQQFaceList.add(new QQFace("[炸弹]", R.drawable.smiley_70));
        mQQFaceList.add(new QQFace("[刀]", R.drawable.smiley_71));
        mQQFaceList.add(new QQFace("[足球]", R.drawable.smiley_72));
        mQQFaceList.add(new QQFace("[瓢虫]", R.drawable.smiley_73));
        mQQFaceList.add(new QQFace("[便便]", R.drawable.smiley_74));
        mQQFaceList.add(new QQFace("[月亮]", R.drawable.smiley_75));
        mQQFaceList.add(new QQFace("[太阳]", R.drawable.smiley_76));
        mQQFaceList.add(new QQFace("[礼物]", R.drawable.smiley_77));
        mQQFaceList.add(new QQFace("[拥抱]", R.drawable.smiley_78));
        mQQFaceList.add(new QQFace("[强]", R.drawable.smiley_79));
        mQQFaceList.add(new QQFace("[弱]", R.drawable.smiley_80));
        mQQFaceList.add(new QQFace("[握手]", R.drawable.smiley_81));
        mQQFaceList.add(new QQFace("[胜利]", R.drawable.smiley_82));
        mQQFaceList.add(new QQFace("[抱拳]", R.drawable.smiley_83));
        mQQFaceList.add(new QQFace("[勾引]", R.drawable.smiley_84));
        mQQFaceList.add(new QQFace("[拳头]", R.drawable.smiley_85));
        mQQFaceList.add(new QQFace("[差劲]", R.drawable.smiley_86));
        mQQFaceList.add(new QQFace("[爱你]", R.drawable.smiley_87));
        mQQFaceList.add(new QQFace("[NO]", R.drawable.smiley_88));
        mQQFaceList.add(new QQFace("[OK]", R.drawable.smiley_89));
        mQQFaceList.add(new QQFace("[爱情]", R.drawable.smiley_90));
        mQQFaceList.add(new QQFace("[飞吻]", R.drawable.smiley_91));
        mQQFaceList.add(new QQFace("[跳跳]", R.drawable.smiley_92));
        mQQFaceList.add(new QQFace("[发抖]", R.drawable.smiley_93));
        mQQFaceList.add(new QQFace("[怄火]", R.drawable.smiley_94));
        mQQFaceList.add(new QQFace("[转圈]", R.drawable.smiley_95));
        mQQFaceList.add(new QQFace("[磕头]", R.drawable.smiley_96));
        mQQFaceList.add(new QQFace("[回头]", R.drawable.smiley_97));
        mQQFaceList.add(new QQFace("[跳绳]", R.drawable.smiley_98));
        mQQFaceList.add(new QQFace("[挥手]", R.drawable.smiley_99));
        mQQFaceList.add(new QQFace("[激动]", R.drawable.smiley_100));
        mQQFaceList.add(new QQFace("[街舞]", R.drawable.smiley_101));
        mQQFaceList.add(new QQFace("[献吻]", R.drawable.smiley_102));
        mQQFaceList.add(new QQFace("[左太极]", R.drawable.smiley_103));
        mQQFaceList.add(new QQFace("[右太极]", R.drawable.smiley_104));

        for (QQFace face : mQQFaceList) {
            sQQFaceMap.put(face.name, face.res);
        }

        mQQFaceFileNameList.put("[微笑]", "smiley_0");
        mQQFaceFileNameList.put("[撇嘴]", "smiley_1");
        mQQFaceFileNameList.put("[色]", "smiley_2");
        mQQFaceFileNameList.put("[发呆]", "smiley_3");
        mQQFaceFileNameList.put("[得意]", "smiley_4");
        mQQFaceFileNameList.put("[流泪]", "smiley_5");
        mQQFaceFileNameList.put("[害羞]", "smiley_6");
        mQQFaceFileNameList.put("[闭嘴]", "smiley_7");
        mQQFaceFileNameList.put("[睡]", "smiley_8");
        mQQFaceFileNameList.put("[大哭]", "smiley_9");
        mQQFaceFileNameList.put("[尴尬]", "smiley_10");
        mQQFaceFileNameList.put("[发怒]", "smiley_11");
        mQQFaceFileNameList.put("[调皮]", "smiley_12");
        mQQFaceFileNameList.put("[呲牙]", "smiley_13");
        mQQFaceFileNameList.put("[惊讶]", "smiley_14");
        mQQFaceFileNameList.put("[难过]", "smiley_15");
        mQQFaceFileNameList.put("[酷]", "smiley_16");
        mQQFaceFileNameList.put("[冷汗]", "smiley_17");
        mQQFaceFileNameList.put("[抓狂]", "smiley_18");
        mQQFaceFileNameList.put("[吐]", "smiley_19");
        mQQFaceFileNameList.put("[偷笑]", "smiley_20");
        mQQFaceFileNameList.put("[可爱]", "smiley_21");
        mQQFaceFileNameList.put("[白眼]", "smiley_22");
        mQQFaceFileNameList.put("[傲慢]", "smiley_23");
        mQQFaceFileNameList.put("[饥饿]", "smiley_24");
        mQQFaceFileNameList.put("[困]", "smiley_25");
        mQQFaceFileNameList.put("[惊恐]", "smiley_26");
        mQQFaceFileNameList.put("[流汗]", "smiley_27");
        mQQFaceFileNameList.put("[憨笑]", "smiley_28");
        mQQFaceFileNameList.put("[大兵]", "smiley_29");
        mQQFaceFileNameList.put("[奋斗]", "smiley_30");
        mQQFaceFileNameList.put("[咒骂]", "smiley_31");
        mQQFaceFileNameList.put("[疑问]", "smiley_32");
        mQQFaceFileNameList.put("[嘘]", "smiley_33");
        mQQFaceFileNameList.put("[晕]", "smiley_34");
        mQQFaceFileNameList.put("[折磨]", "smiley_35");
        mQQFaceFileNameList.put("[衰]", "smiley_36");
        mQQFaceFileNameList.put("[骷髅]", "smiley_37");
        mQQFaceFileNameList.put("[敲打]", "smiley_38");
        mQQFaceFileNameList.put("[再见]", "smiley_39");
        mQQFaceFileNameList.put("[擦汗]", "smiley_40");
        mQQFaceFileNameList.put("[抠鼻]", "smiley_41");
        mQQFaceFileNameList.put("[鼓掌]", "smiley_42");
        mQQFaceFileNameList.put("[糗大了]", "smiley_43");
        mQQFaceFileNameList.put("[坏笑]", "smiley_44");
        mQQFaceFileNameList.put("[左哼哼]", "smiley_45");
        mQQFaceFileNameList.put("[右哼哼]", "smiley_46");
        mQQFaceFileNameList.put("[哈欠]", "smiley_47");
        mQQFaceFileNameList.put("[鄙视]", "smiley_48");
        mQQFaceFileNameList.put("[委屈]", "smiley_49");
        mQQFaceFileNameList.put("[快哭了]", "smiley_50");
        mQQFaceFileNameList.put("[阴险]", "smiley_51");
        mQQFaceFileNameList.put("[亲亲]", "smiley_52");
        mQQFaceFileNameList.put("[吓]", "smiley_53");
        mQQFaceFileNameList.put("[可怜]", "smiley_54");
        mQQFaceFileNameList.put("[菜刀]", "smiley_55");
        mQQFaceFileNameList.put("[西瓜]", "smiley_56");
        mQQFaceFileNameList.put("[啤酒]", "smiley_57");
        mQQFaceFileNameList.put("[篮球]", "smiley_58");
        mQQFaceFileNameList.put("[乒乓]", "smiley_59");
        mQQFaceFileNameList.put("[咖啡]", "smiley_60");
        mQQFaceFileNameList.put("[饭]", "smiley_61");
        mQQFaceFileNameList.put("[猪头]", "smiley_62");
        mQQFaceFileNameList.put("[玫瑰]", "smiley_63");
        mQQFaceFileNameList.put("[凋谢]", "smiley_64");
        mQQFaceFileNameList.put("[示爱]", "smiley_65");
        mQQFaceFileNameList.put("[爱心]", "smiley_66");
        mQQFaceFileNameList.put("[心碎]", "smiley_67");
        mQQFaceFileNameList.put("[蛋糕]", "smiley_68");
        mQQFaceFileNameList.put("[闪电]", "smiley_69");
        mQQFaceFileNameList.put("[炸弹]", "smiley_70");
        mQQFaceFileNameList.put("[刀]", "smiley_71");
        mQQFaceFileNameList.put("[足球]", "smiley_72");
        mQQFaceFileNameList.put("[瓢虫]", "smiley_73");
        mQQFaceFileNameList.put("[便便]", "smiley_74");
        mQQFaceFileNameList.put("[月亮]", "smiley_75");
        mQQFaceFileNameList.put("[太阳]", "smiley_76");
        mQQFaceFileNameList.put("[礼物]", "smiley_77");
        mQQFaceFileNameList.put("[拥抱]", "smiley_78");
        mQQFaceFileNameList.put("[强]", "smiley_79");
        mQQFaceFileNameList.put("[弱]", "smiley_80");
        mQQFaceFileNameList.put("[握手]", "smiley_81");
        mQQFaceFileNameList.put("[胜利]", "smiley_82");
        mQQFaceFileNameList.put("[抱拳]", "smiley_83");
        mQQFaceFileNameList.put("[勾引]", "smiley_84");
        mQQFaceFileNameList.put("[拳头]", "smiley_85");
        mQQFaceFileNameList.put("[差劲]", "smiley_86");
        mQQFaceFileNameList.put("[爱你]", "smiley_87");
        mQQFaceFileNameList.put("[NO]", "smiley_88");
        mQQFaceFileNameList.put("[OK]", "smiley_89");
        mQQFaceFileNameList.put("[爱情]", "smiley_90");
        mQQFaceFileNameList.put("[飞吻]", "smiley_91");
        mQQFaceFileNameList.put("[跳跳]", "smiley_92");
        mQQFaceFileNameList.put("[发抖]", "smiley_93");
        mQQFaceFileNameList.put("[怄火]", "smiley_94");
        mQQFaceFileNameList.put("[转圈]", "smiley_95");
        mQQFaceFileNameList.put("[磕头]", "smiley_96");
        mQQFaceFileNameList.put("[回头]", "smiley_97");
        mQQFaceFileNameList.put("[跳绳]", "smiley_98");
        mQQFaceFileNameList.put("[挥手]", "smiley_99");
        mQQFaceFileNameList.put("[激动]", "smiley_100");
        mQQFaceFileNameList.put("[街舞]", "smiley_101");
        mQQFaceFileNameList.put("[献吻]", "smiley_102");
        mQQFaceFileNameList.put("[左太极]", "smiley_103");
        mQQFaceFileNameList.put("[右太极]", "smiley_104");

        sEmojisMap.append(0x00a9, R.drawable.emoji_00a9);
        sEmojisMap.append(0x00ae, R.drawable.emoji_00ae);
        sEmojisMap.append(0x203c, R.drawable.emoji_203c);
        sEmojisMap.append(0x2049, R.drawable.emoji_2049);
        sEmojisMap.append(0x2122, R.drawable.emoji_2122);
        sEmojisMap.append(0x2139, R.drawable.emoji_2139);
        sEmojisMap.append(0x2194, R.drawable.emoji_2194);
        sEmojisMap.append(0x2195, R.drawable.emoji_2195);
        sEmojisMap.append(0x2196, R.drawable.emoji_2196);
        sEmojisMap.append(0x2197, R.drawable.emoji_2197);
        sEmojisMap.append(0x2198, R.drawable.emoji_2198);
        sEmojisMap.append(0x2199, R.drawable.emoji_2199);
        sEmojisMap.append(0x21a9, R.drawable.emoji_21a9);
        sEmojisMap.append(0x21aa, R.drawable.emoji_21aa);
        sEmojisMap.append(0x231a, R.drawable.emoji_231a);
        sEmojisMap.append(0x231b, R.drawable.emoji_231b);
        sEmojisMap.append(0x23e9, R.drawable.emoji_23e9);
        sEmojisMap.append(0x23ea, R.drawable.emoji_23ea);
        sEmojisMap.append(0x23eb, R.drawable.emoji_23eb);
        sEmojisMap.append(0x23ec, R.drawable.emoji_23ec);
        sEmojisMap.append(0x23f0, R.drawable.emoji_23f0);
        sEmojisMap.append(0x23f3, R.drawable.emoji_23f3);
        sEmojisMap.append(0x24c2, R.drawable.emoji_24c2);
        sEmojisMap.append(0x25aa, R.drawable.emoji_25aa);
        sEmojisMap.append(0x25ab, R.drawable.emoji_25ab);
        sEmojisMap.append(0x25b6, R.drawable.emoji_25b6);
        sEmojisMap.append(0x25c0, R.drawable.emoji_25c0);
        sEmojisMap.append(0x25fb, R.drawable.emoji_25fb);
        sEmojisMap.append(0x25fc, R.drawable.emoji_25fc);
        sEmojisMap.append(0x25fd, R.drawable.emoji_25fd);
        sEmojisMap.append(0x25fe, R.drawable.emoji_25fe);
        sEmojisMap.append(0x2600, R.drawable.emoji_2600);
        sEmojisMap.append(0x2601, R.drawable.emoji_2601);
        sEmojisMap.append(0x260e, R.drawable.emoji_260e);
        sEmojisMap.append(0x2611, R.drawable.emoji_2611);
        sEmojisMap.append(0x2614, R.drawable.emoji_2614);
        sEmojisMap.append(0x2615, R.drawable.emoji_2615);
        sEmojisMap.append(0x261d, R.drawable.emoji_261d);
        sEmojisMap.append(0x263a, R.drawable.emoji_263a);
        sEmojisMap.append(0x2648, R.drawable.emoji_2648);
        sEmojisMap.append(0x2649, R.drawable.emoji_2649);
        sEmojisMap.append(0x264a, R.drawable.emoji_264a);
        sEmojisMap.append(0x264b, R.drawable.emoji_264b);
        sEmojisMap.append(0x264c, R.drawable.emoji_264c);
        sEmojisMap.append(0x264d, R.drawable.emoji_264d);
        sEmojisMap.append(0x264e, R.drawable.emoji_264e);
        sEmojisMap.append(0x264f, R.drawable.emoji_264f);
        sEmojisMap.append(0x2650, R.drawable.emoji_2650);
        sEmojisMap.append(0x2651, R.drawable.emoji_2651);
        sEmojisMap.append(0x2652, R.drawable.emoji_2652);
        sEmojisMap.append(0x2653, R.drawable.emoji_2653);
        sEmojisMap.append(0x2660, R.drawable.emoji_2660);
        sEmojisMap.append(0x2663, R.drawable.emoji_2663);
        sEmojisMap.append(0x2665, R.drawable.emoji_2665);
        sEmojisMap.append(0x2666, R.drawable.emoji_2666);
        sEmojisMap.append(0x2668, R.drawable.emoji_2668);
        sEmojisMap.append(0x267b, R.drawable.emoji_267b);
        sEmojisMap.append(0x267f, R.drawable.emoji_267f);
        sEmojisMap.append(0x2693, R.drawable.emoji_2693);
        sEmojisMap.append(0x26a0, R.drawable.emoji_26a0);
        sEmojisMap.append(0x26a1, R.drawable.emoji_26a1);
        sEmojisMap.append(0x26aa, R.drawable.emoji_26aa);
        sEmojisMap.append(0x26ab, R.drawable.emoji_26ab);
        sEmojisMap.append(0x26bd, R.drawable.emoji_26bd);
        sEmojisMap.append(0x26be, R.drawable.emoji_26be);
        sEmojisMap.append(0x26c4, R.drawable.emoji_26c4);
        sEmojisMap.append(0x26c5, R.drawable.emoji_26c5);
        sEmojisMap.append(0x26ce, R.drawable.emoji_26ce);
        sEmojisMap.append(0x26d4, R.drawable.emoji_26d4);
        sEmojisMap.append(0x26ea, R.drawable.emoji_26ea);
        sEmojisMap.append(0x26f2, R.drawable.emoji_26f2);
        sEmojisMap.append(0x26f3, R.drawable.emoji_26f3);
        sEmojisMap.append(0x26f5, R.drawable.emoji_26f5);
        sEmojisMap.append(0x26fa, R.drawable.emoji_26fa);
        sEmojisMap.append(0x26fd, R.drawable.emoji_26fd);
        sEmojisMap.append(0x2702, R.drawable.emoji_2702);
        sEmojisMap.append(0x2705, R.drawable.emoji_2705);
        sEmojisMap.append(0x2708, R.drawable.emoji_2708);
        sEmojisMap.append(0x2709, R.drawable.emoji_2709);
        sEmojisMap.append(0x270a, R.drawable.emoji_270a);
        sEmojisMap.append(0x270b, R.drawable.emoji_270b);
        sEmojisMap.append(0x270c, R.drawable.emoji_270c);
        sEmojisMap.append(0x270f, R.drawable.emoji_270f);
        sEmojisMap.append(0x2712, R.drawable.emoji_2712);
        sEmojisMap.append(0x2714, R.drawable.emoji_2714);
        sEmojisMap.append(0x2716, R.drawable.emoji_2716);
        sEmojisMap.append(0x2728, R.drawable.emoji_2728);
        sEmojisMap.append(0x2733, R.drawable.emoji_2733);
        sEmojisMap.append(0x2734, R.drawable.emoji_2734);
        sEmojisMap.append(0x2744, R.drawable.emoji_2744);
        sEmojisMap.append(0x2747, R.drawable.emoji_2747);
        sEmojisMap.append(0x274c, R.drawable.emoji_274c);
        sEmojisMap.append(0x274e, R.drawable.emoji_274e);
        sEmojisMap.append(0x2753, R.drawable.emoji_2753);
        sEmojisMap.append(0x2754, R.drawable.emoji_2754);
        sEmojisMap.append(0x2755, R.drawable.emoji_2755);
        sEmojisMap.append(0x2757, R.drawable.emoji_2757);
        sEmojisMap.append(0x2764, R.drawable.emoji_2764);
        sEmojisMap.append(0x2795, R.drawable.emoji_2795);
        sEmojisMap.append(0x2796, R.drawable.emoji_2796);
        sEmojisMap.append(0x2797, R.drawable.emoji_2797);
        sEmojisMap.append(0x27a1, R.drawable.emoji_27a1);
        sEmojisMap.append(0x27b0, R.drawable.emoji_27b0);
        sEmojisMap.append(0x27bf, R.drawable.emoji_27bf);
        sEmojisMap.append(0x2934, R.drawable.emoji_2934);
        sEmojisMap.append(0x2935, R.drawable.emoji_2935);
        sEmojisMap.append(0x2b05, R.drawable.emoji_2b05);
        sEmojisMap.append(0x2b06, R.drawable.emoji_2b06);
        sEmojisMap.append(0x2b07, R.drawable.emoji_2b07);
        sEmojisMap.append(0x2b1b, R.drawable.emoji_2b1b);
        sEmojisMap.append(0x2b1c, R.drawable.emoji_2b1c);
        sEmojisMap.append(0x2b50, R.drawable.emoji_2b50);
        sEmojisMap.append(0x2b55, R.drawable.emoji_2b55);
        sEmojisMap.append(0x3030, R.drawable.emoji_3030);
        sEmojisMap.append(0x303d, R.drawable.emoji_303d);
        sEmojisMap.append(0x3297, R.drawable.emoji_3297);
        sEmojisMap.append(0x3299, R.drawable.emoji_3299);
        sEmojisMap.append(0x1f004, R.drawable.emoji_1f004);
        sEmojisMap.append(0x1f0cf, R.drawable.emoji_1f0cf);
        sEmojisMap.append(0x1f170, R.drawable.emoji_1f170);
        sEmojisMap.append(0x1f171, R.drawable.emoji_1f171);
        sEmojisMap.append(0x1f17e, R.drawable.emoji_1f17e);
        sEmojisMap.append(0x1f17f, R.drawable.emoji_1f17f);
        sEmojisMap.append(0x1f18e, R.drawable.emoji_1f18e);
        sEmojisMap.append(0x1f191, R.drawable.emoji_1f191);
        sEmojisMap.append(0x1f192, R.drawable.emoji_1f192);
        sEmojisMap.append(0x1f193, R.drawable.emoji_1f193);
        sEmojisMap.append(0x1f194, R.drawable.emoji_1f194);
        sEmojisMap.append(0x1f195, R.drawable.emoji_1f195);
        sEmojisMap.append(0x1f196, R.drawable.emoji_1f196);
        sEmojisMap.append(0x1f197, R.drawable.emoji_1f197);
        sEmojisMap.append(0x1f198, R.drawable.emoji_1f198);
        sEmojisMap.append(0x1f199, R.drawable.emoji_1f199);
        sEmojisMap.append(0x1f19a, R.drawable.emoji_1f19a);
        sEmojisMap.append(0x1f201, R.drawable.emoji_1f201);
        sEmojisMap.append(0x1f202, R.drawable.emoji_1f202);
        sEmojisMap.append(0x1f21a, R.drawable.emoji_1f21a);
        sEmojisMap.append(0x1f22f, R.drawable.emoji_1f22f);
        sEmojisMap.append(0x1f232, R.drawable.emoji_1f232);
        sEmojisMap.append(0x1f233, R.drawable.emoji_1f233);
        sEmojisMap.append(0x1f234, R.drawable.emoji_1f234);
        sEmojisMap.append(0x1f235, R.drawable.emoji_1f235);
        sEmojisMap.append(0x1f236, R.drawable.emoji_1f236);
        sEmojisMap.append(0x1f237, R.drawable.emoji_1f237);
        sEmojisMap.append(0x1f238, R.drawable.emoji_1f238);
        sEmojisMap.append(0x1f239, R.drawable.emoji_1f239);
        sEmojisMap.append(0x1f23a, R.drawable.emoji_1f23a);
        sEmojisMap.append(0x1f250, R.drawable.emoji_1f250);
        sEmojisMap.append(0x1f251, R.drawable.emoji_1f251);
        sEmojisMap.append(0x1f300, R.drawable.emoji_1f300);
        sEmojisMap.append(0x1f301, R.drawable.emoji_1f301);
        sEmojisMap.append(0x1f302, R.drawable.emoji_1f302);
        sEmojisMap.append(0x1f303, R.drawable.emoji_1f303);
        sEmojisMap.append(0x1f304, R.drawable.emoji_1f304);
        sEmojisMap.append(0x1f305, R.drawable.emoji_1f305);
        sEmojisMap.append(0x1f306, R.drawable.emoji_1f306);
        sEmojisMap.append(0x1f307, R.drawable.emoji_1f307);
        sEmojisMap.append(0x1f308, R.drawable.emoji_1f308);
        sEmojisMap.append(0x1f309, R.drawable.emoji_1f309);
        sEmojisMap.append(0x1f30a, R.drawable.emoji_1f30a);
        sEmojisMap.append(0x1f30b, R.drawable.emoji_1f30b);
        sEmojisMap.append(0x1f30c, R.drawable.emoji_1f30c);
        sEmojisMap.append(0x1f30d, R.drawable.emoji_1f30d);
        sEmojisMap.append(0x1f30e, R.drawable.emoji_1f30e);
        sEmojisMap.append(0x1f30f, R.drawable.emoji_1f30f);
        sEmojisMap.append(0x1f310, R.drawable.emoji_1f310);
        sEmojisMap.append(0x1f311, R.drawable.emoji_1f311);
        sEmojisMap.append(0x1f312, R.drawable.emoji_1f312);
        sEmojisMap.append(0x1f313, R.drawable.emoji_1f313);
        sEmojisMap.append(0x1f314, R.drawable.emoji_1f314);
        sEmojisMap.append(0x1f315, R.drawable.emoji_1f315);
        sEmojisMap.append(0x1f316, R.drawable.emoji_1f316);
        sEmojisMap.append(0x1f317, R.drawable.emoji_1f317);
        sEmojisMap.append(0x1f318, R.drawable.emoji_1f318);
        sEmojisMap.append(0x1f319, R.drawable.emoji_1f319);
        sEmojisMap.append(0x1f31a, R.drawable.emoji_1f31a);
        sEmojisMap.append(0x1f31b, R.drawable.emoji_1f31b);
        sEmojisMap.append(0x1f31c, R.drawable.emoji_1f31c);
        sEmojisMap.append(0x1f31d, R.drawable.emoji_1f31d);
        sEmojisMap.append(0x1f31e, R.drawable.emoji_1f31e);
        sEmojisMap.append(0x1f31f, R.drawable.emoji_1f31f);
        sEmojisMap.append(0x1f320, R.drawable.emoji_1f303);
        sEmojisMap.append(0x1f330, R.drawable.emoji_1f330);
        sEmojisMap.append(0x1f331, R.drawable.emoji_1f331);
        sEmojisMap.append(0x1f332, R.drawable.emoji_1f332);
        sEmojisMap.append(0x1f333, R.drawable.emoji_1f333);
        sEmojisMap.append(0x1f334, R.drawable.emoji_1f334);
        sEmojisMap.append(0x1f335, R.drawable.emoji_1f335);
        sEmojisMap.append(0x1f337, R.drawable.emoji_1f337);
        sEmojisMap.append(0x1f338, R.drawable.emoji_1f338);
        sEmojisMap.append(0x1f339, R.drawable.emoji_1f339);
        sEmojisMap.append(0x1f33a, R.drawable.emoji_1f33a);
        sEmojisMap.append(0x1f33b, R.drawable.emoji_1f33b);
        sEmojisMap.append(0x1f33c, R.drawable.emoji_1f33c);
        sEmojisMap.append(0x1f33d, R.drawable.emoji_1f33d);
        sEmojisMap.append(0x1f33e, R.drawable.emoji_1f33e);
        sEmojisMap.append(0x1f33f, R.drawable.emoji_1f33f);
        sEmojisMap.append(0x1f340, R.drawable.emoji_1f340);
        sEmojisMap.append(0x1f341, R.drawable.emoji_1f341);
        sEmojisMap.append(0x1f342, R.drawable.emoji_1f342);
        sEmojisMap.append(0x1f343, R.drawable.emoji_1f343);
        sEmojisMap.append(0x1f344, R.drawable.emoji_1f344);
        sEmojisMap.append(0x1f345, R.drawable.emoji_1f345);
        sEmojisMap.append(0x1f346, R.drawable.emoji_1f346);
        sEmojisMap.append(0x1f347, R.drawable.emoji_1f347);
        sEmojisMap.append(0x1f348, R.drawable.emoji_1f348);
        sEmojisMap.append(0x1f349, R.drawable.emoji_1f349);
        sEmojisMap.append(0x1f34a, R.drawable.emoji_1f34a);
        sEmojisMap.append(0x1f34b, R.drawable.emoji_1f34b);
        sEmojisMap.append(0x1f34c, R.drawable.emoji_1f34c);
        sEmojisMap.append(0x1f34d, R.drawable.emoji_1f34d);
        sEmojisMap.append(0x1f34e, R.drawable.emoji_1f34e);
        sEmojisMap.append(0x1f34f, R.drawable.emoji_1f34f);
        sEmojisMap.append(0x1f350, R.drawable.emoji_1f350);
        sEmojisMap.append(0x1f351, R.drawable.emoji_1f351);
        sEmojisMap.append(0x1f352, R.drawable.emoji_1f352);
        sEmojisMap.append(0x1f353, R.drawable.emoji_1f353);
        sEmojisMap.append(0x1f354, R.drawable.emoji_1f354);
        sEmojisMap.append(0x1f355, R.drawable.emoji_1f355);
        sEmojisMap.append(0x1f356, R.drawable.emoji_1f356);
        sEmojisMap.append(0x1f357, R.drawable.emoji_1f357);
        sEmojisMap.append(0x1f358, R.drawable.emoji_1f358);
        sEmojisMap.append(0x1f359, R.drawable.emoji_1f359);
        sEmojisMap.append(0x1f35a, R.drawable.emoji_1f35a);
        sEmojisMap.append(0x1f35b, R.drawable.emoji_1f35b);
        sEmojisMap.append(0x1f35c, R.drawable.emoji_1f35c);
        sEmojisMap.append(0x1f35d, R.drawable.emoji_1f35d);
        sEmojisMap.append(0x1f35e, R.drawable.emoji_1f35e);
        sEmojisMap.append(0x1f35f, R.drawable.emoji_1f35f);
        sEmojisMap.append(0x1f360, R.drawable.emoji_1f360);
        sEmojisMap.append(0x1f361, R.drawable.emoji_1f361);
        sEmojisMap.append(0x1f362, R.drawable.emoji_1f362);
        sEmojisMap.append(0x1f363, R.drawable.emoji_1f363);
        sEmojisMap.append(0x1f364, R.drawable.emoji_1f364);
        sEmojisMap.append(0x1f365, R.drawable.emoji_1f365);
        sEmojisMap.append(0x1f366, R.drawable.emoji_1f366);
        sEmojisMap.append(0x1f367, R.drawable.emoji_1f367);
        sEmojisMap.append(0x1f368, R.drawable.emoji_1f368);
        sEmojisMap.append(0x1f369, R.drawable.emoji_1f369);
        sEmojisMap.append(0x1f36a, R.drawable.emoji_1f36a);
        sEmojisMap.append(0x1f36b, R.drawable.emoji_1f36b);
        sEmojisMap.append(0x1f36c, R.drawable.emoji_1f36c);
        sEmojisMap.append(0x1f36d, R.drawable.emoji_1f36d);
        sEmojisMap.append(0x1f36e, R.drawable.emoji_1f36e);
        sEmojisMap.append(0x1f36f, R.drawable.emoji_1f36f);
        sEmojisMap.append(0x1f370, R.drawable.emoji_1f370);
        sEmojisMap.append(0x1f371, R.drawable.emoji_1f371);
        sEmojisMap.append(0x1f372, R.drawable.emoji_1f372);
        sEmojisMap.append(0x1f373, R.drawable.emoji_1f373);
        sEmojisMap.append(0x1f374, R.drawable.emoji_1f374);
        sEmojisMap.append(0x1f375, R.drawable.emoji_1f375);
        sEmojisMap.append(0x1f376, R.drawable.emoji_1f376);
        sEmojisMap.append(0x1f377, R.drawable.emoji_1f377);
        sEmojisMap.append(0x1f378, R.drawable.emoji_1f378);
        sEmojisMap.append(0x1f379, R.drawable.emoji_1f379);
        sEmojisMap.append(0x1f37a, R.drawable.emoji_1f37a);
        sEmojisMap.append(0x1f37b, R.drawable.emoji_1f37b);
        sEmojisMap.append(0x1f37c, R.drawable.emoji_1f37c);
        sEmojisMap.append(0x1f380, R.drawable.emoji_1f380);
        sEmojisMap.append(0x1f381, R.drawable.emoji_1f381);
        sEmojisMap.append(0x1f382, R.drawable.emoji_1f382);
        sEmojisMap.append(0x1f383, R.drawable.emoji_1f383);
        sEmojisMap.append(0x1f384, R.drawable.emoji_1f384);
        sEmojisMap.append(0x1f385, R.drawable.emoji_1f385);
        sEmojisMap.append(0x1f386, R.drawable.emoji_1f386);
        sEmojisMap.append(0x1f387, R.drawable.emoji_1f387);
        sEmojisMap.append(0x1f388, R.drawable.emoji_1f388);
        sEmojisMap.append(0x1f389, R.drawable.emoji_1f389);
        sEmojisMap.append(0x1f38a, R.drawable.emoji_1f38a);
        sEmojisMap.append(0x1f38b, R.drawable.emoji_1f38b);
        sEmojisMap.append(0x1f38c, R.drawable.emoji_1f38c);
        sEmojisMap.append(0x1f38d, R.drawable.emoji_1f38d);
        sEmojisMap.append(0x1f38e, R.drawable.emoji_1f38e);
        sEmojisMap.append(0x1f38f, R.drawable.emoji_1f38f);
        sEmojisMap.append(0x1f390, R.drawable.emoji_1f390);
        sEmojisMap.append(0x1f391, R.drawable.emoji_1f391);
        sEmojisMap.append(0x1f392, R.drawable.emoji_1f392);
        sEmojisMap.append(0x1f393, R.drawable.emoji_1f393);
        sEmojisMap.append(0x1f3a0, R.drawable.emoji_1f3a0);
        sEmojisMap.append(0x1f3a1, R.drawable.emoji_1f3a1);
        sEmojisMap.append(0x1f3a2, R.drawable.emoji_1f3a2);
        sEmojisMap.append(0x1f3a3, R.drawable.emoji_1f3a3);
        sEmojisMap.append(0x1f3a4, R.drawable.emoji_1f3a4);
        sEmojisMap.append(0x1f3a5, R.drawable.emoji_1f3a5);
        sEmojisMap.append(0x1f3a6, R.drawable.emoji_1f3a6);
        sEmojisMap.append(0x1f3a7, R.drawable.emoji_1f3a7);
        sEmojisMap.append(0x1f3a8, R.drawable.emoji_1f3a8);
        sEmojisMap.append(0x1f3a9, R.drawable.emoji_1f3a9);
        sEmojisMap.append(0x1f3aa, R.drawable.emoji_1f3aa);
        sEmojisMap.append(0x1f3ab, R.drawable.emoji_1f3ab);
        sEmojisMap.append(0x1f3ac, R.drawable.emoji_1f3ac);
        sEmojisMap.append(0x1f3ad, R.drawable.emoji_1f3ad);
        sEmojisMap.append(0x1f3ae, R.drawable.emoji_1f3ae);
        sEmojisMap.append(0x1f3af, R.drawable.emoji_1f3af);
        sEmojisMap.append(0x1f3b0, R.drawable.emoji_1f3b0);
        sEmojisMap.append(0x1f3b1, R.drawable.emoji_1f3b1);
        sEmojisMap.append(0x1f3b2, R.drawable.emoji_1f3b2);
        sEmojisMap.append(0x1f3b3, R.drawable.emoji_1f3b3);
        sEmojisMap.append(0x1f3b4, R.drawable.emoji_1f3b4);
        sEmojisMap.append(0x1f3b5, R.drawable.emoji_1f3b5);
        sEmojisMap.append(0x1f3b6, R.drawable.emoji_1f3b6);
        sEmojisMap.append(0x1f3b7, R.drawable.emoji_1f3b7);
        sEmojisMap.append(0x1f3b8, R.drawable.emoji_1f3b8);
        sEmojisMap.append(0x1f3b9, R.drawable.emoji_1f3b9);
        sEmojisMap.append(0x1f3ba, R.drawable.emoji_1f3ba);
        sEmojisMap.append(0x1f3bb, R.drawable.emoji_1f3bb);
        sEmojisMap.append(0x1f3bc, R.drawable.emoji_1f3bc);
        sEmojisMap.append(0x1f3bd, R.drawable.emoji_1f3bd);
        sEmojisMap.append(0x1f3be, R.drawable.emoji_1f3be);
        sEmojisMap.append(0x1f3bf, R.drawable.emoji_1f3bf);
        sEmojisMap.append(0x1f3c0, R.drawable.emoji_1f3c0);
        sEmojisMap.append(0x1f3c1, R.drawable.emoji_1f3c1);
        sEmojisMap.append(0x1f3c2, R.drawable.emoji_1f3c2);
        sEmojisMap.append(0x1f3c3, R.drawable.emoji_1f3c3);
        sEmojisMap.append(0x1f3c4, R.drawable.emoji_1f3c4);
        sEmojisMap.append(0x1f3c6, R.drawable.emoji_1f3c6);
        sEmojisMap.append(0x1f3c7, R.drawable.emoji_1f3c7);
        sEmojisMap.append(0x1f3c8, R.drawable.emoji_1f3c8);
        sEmojisMap.append(0x1f3c9, R.drawable.emoji_1f3c9);
        sEmojisMap.append(0x1f3ca, R.drawable.emoji_1f3ca);
        sEmojisMap.append(0x1f3e0, R.drawable.emoji_1f3e0);
        sEmojisMap.append(0x1f3e1, R.drawable.emoji_1f3e1);
        sEmojisMap.append(0x1f3e2, R.drawable.emoji_1f3e2);
        sEmojisMap.append(0x1f3e3, R.drawable.emoji_1f3e3);
        sEmojisMap.append(0x1f3e4, R.drawable.emoji_1f3e4);
        sEmojisMap.append(0x1f3e5, R.drawable.emoji_1f3e5);
        sEmojisMap.append(0x1f3e6, R.drawable.emoji_1f3e6);
        sEmojisMap.append(0x1f3e7, R.drawable.emoji_1f3e7);
        sEmojisMap.append(0x1f3e8, R.drawable.emoji_1f3e8);
        sEmojisMap.append(0x1f3e9, R.drawable.emoji_1f3e9);
        sEmojisMap.append(0x1f3ea, R.drawable.emoji_1f3ea);
        sEmojisMap.append(0x1f3eb, R.drawable.emoji_1f3eb);
        sEmojisMap.append(0x1f3ec, R.drawable.emoji_1f3ec);
        sEmojisMap.append(0x1f3ed, R.drawable.emoji_1f3ed);
        sEmojisMap.append(0x1f3ee, R.drawable.emoji_1f3ee);
        sEmojisMap.append(0x1f3ef, R.drawable.emoji_1f3ef);
        sEmojisMap.append(0x1f3f0, R.drawable.emoji_1f3f0);
        sEmojisMap.append(0x1f400, R.drawable.emoji_1f400);
        sEmojisMap.append(0x1f401, R.drawable.emoji_1f401);
        sEmojisMap.append(0x1f402, R.drawable.emoji_1f402);
        sEmojisMap.append(0x1f403, R.drawable.emoji_1f403);
        sEmojisMap.append(0x1f404, R.drawable.emoji_1f404);
        sEmojisMap.append(0x1f405, R.drawable.emoji_1f405);
        sEmojisMap.append(0x1f406, R.drawable.emoji_1f406);
        sEmojisMap.append(0x1f407, R.drawable.emoji_1f407);
        sEmojisMap.append(0x1f408, R.drawable.emoji_1f408);
        sEmojisMap.append(0x1f409, R.drawable.emoji_1f409);
        sEmojisMap.append(0x1f40a, R.drawable.emoji_1f40a);
        sEmojisMap.append(0x1f40b, R.drawable.emoji_1f40b);
        sEmojisMap.append(0x1f40c, R.drawable.emoji_1f40c);
        sEmojisMap.append(0x1f40d, R.drawable.emoji_1f40d);
        sEmojisMap.append(0x1f40e, R.drawable.emoji_1f40e);
        sEmojisMap.append(0x1f40f, R.drawable.emoji_1f40f);
        sEmojisMap.append(0x1f410, R.drawable.emoji_1f410);
        sEmojisMap.append(0x1f411, R.drawable.emoji_1f411);
        sEmojisMap.append(0x1f412, R.drawable.emoji_1f412);
        sEmojisMap.append(0x1f413, R.drawable.emoji_1f413);
        sEmojisMap.append(0x1f414, R.drawable.emoji_1f414);
        sEmojisMap.append(0x1f415, R.drawable.emoji_1f415);
        sEmojisMap.append(0x1f416, R.drawable.emoji_1f416);
        sEmojisMap.append(0x1f417, R.drawable.emoji_1f417);
        sEmojisMap.append(0x1f418, R.drawable.emoji_1f418);
        sEmojisMap.append(0x1f419, R.drawable.emoji_1f419);
        sEmojisMap.append(0x1f41a, R.drawable.emoji_1f41a);
        sEmojisMap.append(0x1f41b, R.drawable.emoji_1f41b);
        sEmojisMap.append(0x1f41c, R.drawable.emoji_1f41c);
        sEmojisMap.append(0x1f41d, R.drawable.emoji_1f41d);
        sEmojisMap.append(0x1f41e, R.drawable.emoji_1f41e);
        sEmojisMap.append(0x1f41f, R.drawable.emoji_1f41f);
        sEmojisMap.append(0x1f420, R.drawable.emoji_1f420);
        sEmojisMap.append(0x1f421, R.drawable.emoji_1f421);
        sEmojisMap.append(0x1f422, R.drawable.emoji_1f422);
        sEmojisMap.append(0x1f423, R.drawable.emoji_1f423);
        sEmojisMap.append(0x1f424, R.drawable.emoji_1f424);
        sEmojisMap.append(0x1f425, R.drawable.emoji_1f425);
        sEmojisMap.append(0x1f426, R.drawable.emoji_1f426);
        sEmojisMap.append(0x1f427, R.drawable.emoji_1f427);
        sEmojisMap.append(0x1f428, R.drawable.emoji_1f428);
        sEmojisMap.append(0x1f429, R.drawable.emoji_1f429);
        sEmojisMap.append(0x1f42a, R.drawable.emoji_1f42a);
        sEmojisMap.append(0x1f42b, R.drawable.emoji_1f42b);
        sEmojisMap.append(0x1f42c, R.drawable.emoji_1f42c);
        sEmojisMap.append(0x1f42d, R.drawable.emoji_1f42d);
        sEmojisMap.append(0x1f42e, R.drawable.emoji_1f42e);
        sEmojisMap.append(0x1f42f, R.drawable.emoji_1f42f);
        sEmojisMap.append(0x1f430, R.drawable.emoji_1f430);
        sEmojisMap.append(0x1f431, R.drawable.emoji_1f431);
        sEmojisMap.append(0x1f432, R.drawable.emoji_1f432);
        sEmojisMap.append(0x1f433, R.drawable.emoji_1f433);
        sEmojisMap.append(0x1f434, R.drawable.emoji_1f434);
        sEmojisMap.append(0x1f435, R.drawable.emoji_1f435);
        sEmojisMap.append(0x1f436, R.drawable.emoji_1f436);
        sEmojisMap.append(0x1f437, R.drawable.emoji_1f437);
        sEmojisMap.append(0x1f438, R.drawable.emoji_1f438);
        sEmojisMap.append(0x1f439, R.drawable.emoji_1f439);
        sEmojisMap.append(0x1f43a, R.drawable.emoji_1f43a);
        sEmojisMap.append(0x1f43b, R.drawable.emoji_1f43b);
        sEmojisMap.append(0x1f43c, R.drawable.emoji_1f43c);
        sEmojisMap.append(0x1f43d, R.drawable.emoji_1f43d);
        sEmojisMap.append(0x1f43e, R.drawable.emoji_1f43e);
        sEmojisMap.append(0x1f440, R.drawable.emoji_1f440);
        sEmojisMap.append(0x1f442, R.drawable.emoji_1f442);
        sEmojisMap.append(0x1f443, R.drawable.emoji_1f443);
        sEmojisMap.append(0x1f444, R.drawable.emoji_1f444);
        sEmojisMap.append(0x1f445, R.drawable.emoji_1f445);
        sEmojisMap.append(0x1f446, R.drawable.emoji_1f446);
        sEmojisMap.append(0x1f447, R.drawable.emoji_1f447);
        sEmojisMap.append(0x1f448, R.drawable.emoji_1f448);
        sEmojisMap.append(0x1f449, R.drawable.emoji_1f449);
        sEmojisMap.append(0x1f44a, R.drawable.emoji_1f44a);
        sEmojisMap.append(0x1f44b, R.drawable.emoji_1f44b);
        sEmojisMap.append(0x1f44c, R.drawable.emoji_1f44c);
        sEmojisMap.append(0x1f44d, R.drawable.emoji_1f44d);
        sEmojisMap.append(0x1f44e, R.drawable.emoji_1f44e);
        sEmojisMap.append(0x1f44f, R.drawable.emoji_1f44f);
        sEmojisMap.append(0x1f450, R.drawable.emoji_1f450);
        sEmojisMap.append(0x1f451, R.drawable.emoji_1f451);
        sEmojisMap.append(0x1f452, R.drawable.emoji_1f452);
        sEmojisMap.append(0x1f453, R.drawable.emoji_1f453);
        sEmojisMap.append(0x1f454, R.drawable.emoji_1f454);
        sEmojisMap.append(0x1f455, R.drawable.emoji_1f455);
        sEmojisMap.append(0x1f456, R.drawable.emoji_1f456);
        sEmojisMap.append(0x1f457, R.drawable.emoji_1f457);
        sEmojisMap.append(0x1f458, R.drawable.emoji_1f458);
        sEmojisMap.append(0x1f459, R.drawable.emoji_1f459);
        sEmojisMap.append(0x1f45a, R.drawable.emoji_1f45a);
        sEmojisMap.append(0x1f45b, R.drawable.emoji_1f45b);
        sEmojisMap.append(0x1f45c, R.drawable.emoji_1f45c);
        sEmojisMap.append(0x1f45d, R.drawable.emoji_1f45d);
        sEmojisMap.append(0x1f45e, R.drawable.emoji_1f45e);
        sEmojisMap.append(0x1f45f, R.drawable.emoji_1f45f);
        sEmojisMap.append(0x1f460, R.drawable.emoji_1f460);
        sEmojisMap.append(0x1f461, R.drawable.emoji_1f461);
        sEmojisMap.append(0x1f462, R.drawable.emoji_1f462);
        sEmojisMap.append(0x1f463, R.drawable.emoji_1f463);
        sEmojisMap.append(0x1f464, R.drawable.emoji_1f464);
        sEmojisMap.append(0x1f465, R.drawable.emoji_1f465);
        sEmojisMap.append(0x1f466, R.drawable.emoji_1f466);
        sEmojisMap.append(0x1f467, R.drawable.emoji_1f467);
        sEmojisMap.append(0x1f468, R.drawable.emoji_1f468);
        sEmojisMap.append(0x1f469, R.drawable.emoji_1f469);
        sEmojisMap.append(0x1f46a, R.drawable.emoji_1f46a);
        sEmojisMap.append(0x1f46b, R.drawable.emoji_1f46b);
        sEmojisMap.append(0x1f46c, R.drawable.emoji_1f46c);
        sEmojisMap.append(0x1f46d, R.drawable.emoji_1f46d);
        sEmojisMap.append(0x1f46e, R.drawable.emoji_1f46e);
        sEmojisMap.append(0x1f46f, R.drawable.emoji_1f46f);
        sEmojisMap.append(0x1f470, R.drawable.emoji_1f470);
        sEmojisMap.append(0x1f471, R.drawable.emoji_1f471);
        sEmojisMap.append(0x1f472, R.drawable.emoji_1f472);
        sEmojisMap.append(0x1f473, R.drawable.emoji_1f473);
        sEmojisMap.append(0x1f474, R.drawable.emoji_1f474);
        sEmojisMap.append(0x1f475, R.drawable.emoji_1f475);
        sEmojisMap.append(0x1f476, R.drawable.emoji_1f476);
        sEmojisMap.append(0x1f477, R.drawable.emoji_1f477);
        sEmojisMap.append(0x1f478, R.drawable.emoji_1f478);
        sEmojisMap.append(0x1f479, R.drawable.emoji_1f479);
        sEmojisMap.append(0x1f47a, R.drawable.emoji_1f47a);
        sEmojisMap.append(0x1f47b, R.drawable.emoji_1f47b);
        sEmojisMap.append(0x1f47c, R.drawable.emoji_1f47c);
        sEmojisMap.append(0x1f47d, R.drawable.emoji_1f47d);
        sEmojisMap.append(0x1f47e, R.drawable.emoji_1f47e);
        sEmojisMap.append(0x1f47f, R.drawable.emoji_1f47f);
        sEmojisMap.append(0x1f480, R.drawable.emoji_1f480);
        sEmojisMap.append(0x1f481, R.drawable.emoji_1f481);
        sEmojisMap.append(0x1f482, R.drawable.emoji_1f482);
        sEmojisMap.append(0x1f483, R.drawable.emoji_1f483);
        sEmojisMap.append(0x1f484, R.drawable.emoji_1f484);
        sEmojisMap.append(0x1f485, R.drawable.emoji_1f485);
        sEmojisMap.append(0x1f486, R.drawable.emoji_1f486);
        sEmojisMap.append(0x1f487, R.drawable.emoji_1f487);
        sEmojisMap.append(0x1f488, R.drawable.emoji_1f488);
        sEmojisMap.append(0x1f489, R.drawable.emoji_1f489);
        sEmojisMap.append(0x1f48a, R.drawable.emoji_1f48a);
        sEmojisMap.append(0x1f48b, R.drawable.emoji_1f48b);
        sEmojisMap.append(0x1f48c, R.drawable.emoji_1f48c);
        sEmojisMap.append(0x1f48d, R.drawable.emoji_1f48d);
        sEmojisMap.append(0x1f48e, R.drawable.emoji_1f48e);
        sEmojisMap.append(0x1f48f, R.drawable.emoji_1f48f);
        sEmojisMap.append(0x1f490, R.drawable.emoji_1f490);
        sEmojisMap.append(0x1f491, R.drawable.emoji_1f491);
        sEmojisMap.append(0x1f492, R.drawable.emoji_1f492);
        sEmojisMap.append(0x1f493, R.drawable.emoji_1f493);
        sEmojisMap.append(0x1f494, R.drawable.emoji_1f494);
        sEmojisMap.append(0x1f495, R.drawable.emoji_1f495);
        sEmojisMap.append(0x1f496, R.drawable.emoji_1f496);
        sEmojisMap.append(0x1f497, R.drawable.emoji_1f497);
        sEmojisMap.append(0x1f498, R.drawable.emoji_1f498);
        sEmojisMap.append(0x1f499, R.drawable.emoji_1f499);
        sEmojisMap.append(0x1f49a, R.drawable.emoji_1f49a);
        sEmojisMap.append(0x1f49b, R.drawable.emoji_1f49b);
        sEmojisMap.append(0x1f49c, R.drawable.emoji_1f49c);
        sEmojisMap.append(0x1f49d, R.drawable.emoji_1f49d);
        sEmojisMap.append(0x1f49e, R.drawable.emoji_1f49e);
        sEmojisMap.append(0x1f49f, R.drawable.emoji_1f49f);
        sEmojisMap.append(0x1f4a0, R.drawable.emoji_1f4a0);
        sEmojisMap.append(0x1f4a1, R.drawable.emoji_1f4a1);
        sEmojisMap.append(0x1f4a2, R.drawable.emoji_1f4a2);
        sEmojisMap.append(0x1f4a3, R.drawable.emoji_1f4a3);
        sEmojisMap.append(0x1f4a4, R.drawable.emoji_1f4a4);
        sEmojisMap.append(0x1f4a5, R.drawable.emoji_1f4a5);
        sEmojisMap.append(0x1f4a6, R.drawable.emoji_1f4a6);
        sEmojisMap.append(0x1f4a7, R.drawable.emoji_1f4a7);
        sEmojisMap.append(0x1f4a8, R.drawable.emoji_1f4a8);
        sEmojisMap.append(0x1f4a9, R.drawable.emoji_1f4a9);
        sEmojisMap.append(0x1f4aa, R.drawable.emoji_1f4aa);
        sEmojisMap.append(0x1f4ab, R.drawable.emoji_1f4ab);
        sEmojisMap.append(0x1f4ac, R.drawable.emoji_1f4ac);
        sEmojisMap.append(0x1f4ad, R.drawable.emoji_1f4ad);
        sEmojisMap.append(0x1f4ae, R.drawable.emoji_1f4ae);
        sEmojisMap.append(0x1f4af, R.drawable.emoji_1f4af);
        sEmojisMap.append(0x1f4b0, R.drawable.emoji_1f4b0);
        sEmojisMap.append(0x1f4b1, R.drawable.emoji_1f4b1);
        sEmojisMap.append(0x1f4b2, R.drawable.emoji_1f4b2);
        sEmojisMap.append(0x1f4b3, R.drawable.emoji_1f4b3);
        sEmojisMap.append(0x1f4b4, R.drawable.emoji_1f4b4);
        sEmojisMap.append(0x1f4b5, R.drawable.emoji_1f4b5);
        sEmojisMap.append(0x1f4b6, R.drawable.emoji_1f4b6);
        sEmojisMap.append(0x1f4b7, R.drawable.emoji_1f4b7);
        sEmojisMap.append(0x1f4b8, R.drawable.emoji_1f4b8);
        sEmojisMap.append(0x1f4b9, R.drawable.emoji_1f4b9);
        sEmojisMap.append(0x1f4ba, R.drawable.emoji_1f4ba);
        sEmojisMap.append(0x1f4bb, R.drawable.emoji_1f4bb);
        sEmojisMap.append(0x1f4bc, R.drawable.emoji_1f4bc);
        sEmojisMap.append(0x1f4bd, R.drawable.emoji_1f4bd);
        sEmojisMap.append(0x1f4be, R.drawable.emoji_1f4be);
        sEmojisMap.append(0x1f4bf, R.drawable.emoji_1f4bf);
        sEmojisMap.append(0x1f4c0, R.drawable.emoji_1f4c0);
        sEmojisMap.append(0x1f4c1, R.drawable.emoji_1f4c1);
        sEmojisMap.append(0x1f4c2, R.drawable.emoji_1f4c2);
        sEmojisMap.append(0x1f4c3, R.drawable.emoji_1f4c3);
        sEmojisMap.append(0x1f4c4, R.drawable.emoji_1f4c4);
        sEmojisMap.append(0x1f4c5, R.drawable.emoji_1f4c5);
        sEmojisMap.append(0x1f4c6, R.drawable.emoji_1f4c6);
        sEmojisMap.append(0x1f4c7, R.drawable.emoji_1f4c7);
        sEmojisMap.append(0x1f4c8, R.drawable.emoji_1f4c8);
        sEmojisMap.append(0x1f4c9, R.drawable.emoji_1f4c9);
        sEmojisMap.append(0x1f4ca, R.drawable.emoji_1f4ca);
        sEmojisMap.append(0x1f4cb, R.drawable.emoji_1f4cb);
        sEmojisMap.append(0x1f4cc, R.drawable.emoji_1f4cc);
        sEmojisMap.append(0x1f4cd, R.drawable.emoji_1f4cd);
        sEmojisMap.append(0x1f4ce, R.drawable.emoji_1f4ce);
        sEmojisMap.append(0x1f4cf, R.drawable.emoji_1f4cf);
        sEmojisMap.append(0x1f4d0, R.drawable.emoji_1f4d0);
        sEmojisMap.append(0x1f4d1, R.drawable.emoji_1f4d1);
        sEmojisMap.append(0x1f4d2, R.drawable.emoji_1f4d2);
        sEmojisMap.append(0x1f4d3, R.drawable.emoji_1f4d3);
        sEmojisMap.append(0x1f4d4, R.drawable.emoji_1f4d4);
        sEmojisMap.append(0x1f4d5, R.drawable.emoji_1f4d5);
        sEmojisMap.append(0x1f4d6, R.drawable.emoji_1f4d6);
        sEmojisMap.append(0x1f4d7, R.drawable.emoji_1f4d7);
        sEmojisMap.append(0x1f4d8, R.drawable.emoji_1f4d8);
        sEmojisMap.append(0x1f4d9, R.drawable.emoji_1f4d9);
        sEmojisMap.append(0x1f4da, R.drawable.emoji_1f4da);
        sEmojisMap.append(0x1f4db, R.drawable.emoji_1f4db);
        sEmojisMap.append(0x1f4dc, R.drawable.emoji_1f4dc);
        sEmojisMap.append(0x1f4dd, R.drawable.emoji_1f4dd);
        sEmojisMap.append(0x1f4de, R.drawable.emoji_1f4de);
        sEmojisMap.append(0x1f4df, R.drawable.emoji_1f4df);
        sEmojisMap.append(0x1f4e0, R.drawable.emoji_1f4e0);
        sEmojisMap.append(0x1f4e1, R.drawable.emoji_1f4e1);
        sEmojisMap.append(0x1f4e2, R.drawable.emoji_1f4e2);
        sEmojisMap.append(0x1f4e3, R.drawable.emoji_1f4e3);
        sEmojisMap.append(0x1f4e4, R.drawable.emoji_1f4e4);
        sEmojisMap.append(0x1f4e5, R.drawable.emoji_1f4e5);
        sEmojisMap.append(0x1f4e6, R.drawable.emoji_1f4e6);
        sEmojisMap.append(0x1f4e7, R.drawable.emoji_1f4e7);
        sEmojisMap.append(0x1f4e8, R.drawable.emoji_1f4e8);
        sEmojisMap.append(0x1f4e9, R.drawable.emoji_1f4e9);
        sEmojisMap.append(0x1f4ea, R.drawable.emoji_1f4ea);
        sEmojisMap.append(0x1f4eb, R.drawable.emoji_1f4eb);
        sEmojisMap.append(0x1f4ec, R.drawable.emoji_1f4ec);
        sEmojisMap.append(0x1f4ed, R.drawable.emoji_1f4ed);
        sEmojisMap.append(0x1f4ee, R.drawable.emoji_1f4ee);
        sEmojisMap.append(0x1f4ef, R.drawable.emoji_1f4ef);
        sEmojisMap.append(0x1f4f0, R.drawable.emoji_1f4f0);
        sEmojisMap.append(0x1f4f1, R.drawable.emoji_1f4f1);
        sEmojisMap.append(0x1f4f2, R.drawable.emoji_1f4f2);
        sEmojisMap.append(0x1f4f3, R.drawable.emoji_1f4f3);
        sEmojisMap.append(0x1f4f4, R.drawable.emoji_1f4f4);
        sEmojisMap.append(0x1f4f5, R.drawable.emoji_1f4f5);
        sEmojisMap.append(0x1f4f6, R.drawable.emoji_1f4f6);
        sEmojisMap.append(0x1f4f7, R.drawable.emoji_1f4f7);
        sEmojisMap.append(0x1f4f9, R.drawable.emoji_1f4f9);
        sEmojisMap.append(0x1f4fa, R.drawable.emoji_1f4fa);
        sEmojisMap.append(0x1f4fb, R.drawable.emoji_1f4fb);
        sEmojisMap.append(0x1f4fc, R.drawable.emoji_1f4fc);
        sEmojisMap.append(0x1f500, R.drawable.emoji_1f500);
        sEmojisMap.append(0x1f501, R.drawable.emoji_1f501);
        sEmojisMap.append(0x1f502, R.drawable.emoji_1f502);
        sEmojisMap.append(0x1f503, R.drawable.emoji_1f503);
        sEmojisMap.append(0x1f504, R.drawable.emoji_1f504);
        sEmojisMap.append(0x1f505, R.drawable.emoji_1f505);
        sEmojisMap.append(0x1f506, R.drawable.emoji_1f506);
        sEmojisMap.append(0x1f507, R.drawable.emoji_1f507);
        sEmojisMap.append(0x1f508, R.drawable.emoji_1f508);
        sEmojisMap.append(0x1f509, R.drawable.emoji_1f509);
        sEmojisMap.append(0x1f50a, R.drawable.emoji_1f50a);
        sEmojisMap.append(0x1f50b, R.drawable.emoji_1f50b);
        sEmojisMap.append(0x1f50c, R.drawable.emoji_1f50c);
        sEmojisMap.append(0x1f50d, R.drawable.emoji_1f50d);
        sEmojisMap.append(0x1f50e, R.drawable.emoji_1f50e);
        sEmojisMap.append(0x1f50f, R.drawable.emoji_1f50f);
        sEmojisMap.append(0x1f510, R.drawable.emoji_1f510);
        sEmojisMap.append(0x1f511, R.drawable.emoji_1f511);
        sEmojisMap.append(0x1f512, R.drawable.emoji_1f512);
        sEmojisMap.append(0x1f513, R.drawable.emoji_1f513);
        sEmojisMap.append(0x1f514, R.drawable.emoji_1f514);
        sEmojisMap.append(0x1f515, R.drawable.emoji_1f515);
        sEmojisMap.append(0x1f516, R.drawable.emoji_1f516);
        sEmojisMap.append(0x1f517, R.drawable.emoji_1f517);
        sEmojisMap.append(0x1f518, R.drawable.emoji_1f518);
        sEmojisMap.append(0x1f519, R.drawable.emoji_1f519);
        sEmojisMap.append(0x1f51a, R.drawable.emoji_1f51a);
        sEmojisMap.append(0x1f51b, R.drawable.emoji_1f51b);
        sEmojisMap.append(0x1f51c, R.drawable.emoji_1f51c);
        sEmojisMap.append(0x1f51d, R.drawable.emoji_1f51d);
        sEmojisMap.append(0x1f51e, R.drawable.emoji_1f51e);
        sEmojisMap.append(0x1f51f, R.drawable.emoji_1f51f);
        sEmojisMap.append(0x1f520, R.drawable.emoji_1f520);
        sEmojisMap.append(0x1f521, R.drawable.emoji_1f521);
        sEmojisMap.append(0x1f522, R.drawable.emoji_1f522);
        sEmojisMap.append(0x1f523, R.drawable.emoji_1f523);
        sEmojisMap.append(0x1f524, R.drawable.emoji_1f524);
        sEmojisMap.append(0x1f525, R.drawable.emoji_1f525);
        sEmojisMap.append(0x1f526, R.drawable.emoji_1f526);
        sEmojisMap.append(0x1f527, R.drawable.emoji_1f527);
        sEmojisMap.append(0x1f528, R.drawable.emoji_1f528);
        sEmojisMap.append(0x1f529, R.drawable.emoji_1f529);
        sEmojisMap.append(0x1f52a, R.drawable.emoji_1f52a);
        sEmojisMap.append(0x1f52b, R.drawable.emoji_1f52b);
        sEmojisMap.append(0x1f52c, R.drawable.emoji_1f52c);
        sEmojisMap.append(0x1f52d, R.drawable.emoji_1f52d);
        sEmojisMap.append(0x1f52e, R.drawable.emoji_1f52e);
        sEmojisMap.append(0x1f52f, R.drawable.emoji_1f52f);
        sEmojisMap.append(0x1f530, R.drawable.emoji_1f530);
        sEmojisMap.append(0x1f531, R.drawable.emoji_1f531);
        sEmojisMap.append(0x1f532, R.drawable.emoji_1f532);
        sEmojisMap.append(0x1f533, R.drawable.emoji_1f533);
        sEmojisMap.append(0x1f534, R.drawable.emoji_1f534);
        sEmojisMap.append(0x1f535, R.drawable.emoji_1f535);
        sEmojisMap.append(0x1f536, R.drawable.emoji_1f536);
        sEmojisMap.append(0x1f537, R.drawable.emoji_1f537);
        sEmojisMap.append(0x1f538, R.drawable.emoji_1f538);
        sEmojisMap.append(0x1f539, R.drawable.emoji_1f539);
        sEmojisMap.append(0x1f53a, R.drawable.emoji_1f53a);
        sEmojisMap.append(0x1f53b, R.drawable.emoji_1f53b);
        sEmojisMap.append(0x1f53c, R.drawable.emoji_1f53c);
        sEmojisMap.append(0x1f53d, R.drawable.emoji_1f53d);
        sEmojisMap.append(0x1f550, R.drawable.emoji_1f550);
        sEmojisMap.append(0x1f551, R.drawable.emoji_1f551);
        sEmojisMap.append(0x1f552, R.drawable.emoji_1f552);
        sEmojisMap.append(0x1f553, R.drawable.emoji_1f553);
        sEmojisMap.append(0x1f554, R.drawable.emoji_1f554);
        sEmojisMap.append(0x1f555, R.drawable.emoji_1f555);
        sEmojisMap.append(0x1f556, R.drawable.emoji_1f556);
        sEmojisMap.append(0x1f557, R.drawable.emoji_1f557);
        sEmojisMap.append(0x1f558, R.drawable.emoji_1f558);
        sEmojisMap.append(0x1f559, R.drawable.emoji_1f559);
        sEmojisMap.append(0x1f55a, R.drawable.emoji_1f55a);
        sEmojisMap.append(0x1f55b, R.drawable.emoji_1f55b);
        sEmojisMap.append(0x1f55c, R.drawable.emoji_1f55c);
        sEmojisMap.append(0x1f55d, R.drawable.emoji_1f55d);
        sEmojisMap.append(0x1f55e, R.drawable.emoji_1f55e);
        sEmojisMap.append(0x1f55f, R.drawable.emoji_1f55f);
        sEmojisMap.append(0x1f560, R.drawable.emoji_1f560);
        sEmojisMap.append(0x1f561, R.drawable.emoji_1f561);
        sEmojisMap.append(0x1f562, R.drawable.emoji_1f562);
        sEmojisMap.append(0x1f563, R.drawable.emoji_1f563);
        sEmojisMap.append(0x1f564, R.drawable.emoji_1f564);
        sEmojisMap.append(0x1f565, R.drawable.emoji_1f565);
        sEmojisMap.append(0x1f566, R.drawable.emoji_1f566);
        sEmojisMap.append(0x1f567, R.drawable.emoji_1f567);
        sEmojisMap.append(0x1f5fb, R.drawable.emoji_1f5fb);
        sEmojisMap.append(0x1f5fc, R.drawable.emoji_1f5fc);
        sEmojisMap.append(0x1f5fd, R.drawable.emoji_1f5fd);
        sEmojisMap.append(0x1f5fe, R.drawable.emoji_1f5fe);
        sEmojisMap.append(0x1f5ff, R.drawable.emoji_1f5ff);
        sEmojisMap.append(0x1f600, R.drawable.emoji_1f600);
        sEmojisMap.append(0x1f601, R.drawable.emoji_1f601);
        sEmojisMap.append(0x1f602, R.drawable.emoji_1f602);
        sEmojisMap.append(0x1f603, R.drawable.emoji_1f603);
        sEmojisMap.append(0x1f604, R.drawable.emoji_1f604);
        sEmojisMap.append(0x1f605, R.drawable.emoji_1f605);
        sEmojisMap.append(0x1f606, R.drawable.emoji_1f606);
        sEmojisMap.append(0x1f607, R.drawable.emoji_1f607);
        sEmojisMap.append(0x1f608, R.drawable.emoji_1f608);
        sEmojisMap.append(0x1f609, R.drawable.emoji_1f609);
        sEmojisMap.append(0x1f60a, R.drawable.emoji_1f60a);
        sEmojisMap.append(0x1f60b, R.drawable.emoji_1f60b);
        sEmojisMap.append(0x1f60c, R.drawable.emoji_1f60c);
        sEmojisMap.append(0x1f60d, R.drawable.emoji_1f60d);
        sEmojisMap.append(0x1f60e, R.drawable.emoji_1f60e);
        sEmojisMap.append(0x1f60f, R.drawable.emoji_1f60f);
        sEmojisMap.append(0x1f610, R.drawable.emoji_1f610);
        sEmojisMap.append(0x1f611, R.drawable.emoji_1f611);
        sEmojisMap.append(0x1f612, R.drawable.emoji_1f612);
        sEmojisMap.append(0x1f613, R.drawable.emoji_1f613);
        sEmojisMap.append(0x1f614, R.drawable.emoji_1f614);
        sEmojisMap.append(0x1f615, R.drawable.emoji_1f615);
        sEmojisMap.append(0x1f616, R.drawable.emoji_1f616);
        sEmojisMap.append(0x1f617, R.drawable.emoji_1f617);
        sEmojisMap.append(0x1f618, R.drawable.emoji_1f618);
        sEmojisMap.append(0x1f619, R.drawable.emoji_1f619);
        sEmojisMap.append(0x1f61a, R.drawable.emoji_1f61a);
        sEmojisMap.append(0x1f61b, R.drawable.emoji_1f61b);
        sEmojisMap.append(0x1f61c, R.drawable.emoji_1f61c);
        sEmojisMap.append(0x1f61d, R.drawable.emoji_1f61d);
        sEmojisMap.append(0x1f61e, R.drawable.emoji_1f61e);
        sEmojisMap.append(0x1f61f, R.drawable.emoji_1f61f);
        sEmojisMap.append(0x1f620, R.drawable.emoji_1f620);
        sEmojisMap.append(0x1f621, R.drawable.emoji_1f621);
        sEmojisMap.append(0x1f622, R.drawable.emoji_1f622);
        sEmojisMap.append(0x1f623, R.drawable.emoji_1f623);
        sEmojisMap.append(0x1f624, R.drawable.emoji_1f624);
        sEmojisMap.append(0x1f625, R.drawable.emoji_1f625);
        sEmojisMap.append(0x1f626, R.drawable.emoji_1f626);
        sEmojisMap.append(0x1f627, R.drawable.emoji_1f627);
        sEmojisMap.append(0x1f628, R.drawable.emoji_1f628);
        sEmojisMap.append(0x1f629, R.drawable.emoji_1f629);
        sEmojisMap.append(0x1f62a, R.drawable.emoji_1f62a);
        sEmojisMap.append(0x1f62b, R.drawable.emoji_1f62b);
        sEmojisMap.append(0x1f62c, R.drawable.emoji_1f62c);
        sEmojisMap.append(0x1f62d, R.drawable.emoji_1f62d);
        sEmojisMap.append(0x1f62e, R.drawable.emoji_1f62e);
        sEmojisMap.append(0x1f62f, R.drawable.emoji_1f62f);
        sEmojisMap.append(0x1f630, R.drawable.emoji_1f630);
        sEmojisMap.append(0x1f631, R.drawable.emoji_1f631);
        sEmojisMap.append(0x1f632, R.drawable.emoji_1f632);
        sEmojisMap.append(0x1f633, R.drawable.emoji_1f633);
        sEmojisMap.append(0x1f634, R.drawable.emoji_1f634);
        sEmojisMap.append(0x1f635, R.drawable.emoji_1f635);
        sEmojisMap.append(0x1f636, R.drawable.emoji_1f636);
        sEmojisMap.append(0x1f637, R.drawable.emoji_1f637);
        sEmojisMap.append(0x1f638, R.drawable.emoji_1f638);
        sEmojisMap.append(0x1f639, R.drawable.emoji_1f639);
        sEmojisMap.append(0x1f63a, R.drawable.emoji_1f63a);
        sEmojisMap.append(0x1f63b, R.drawable.emoji_1f63b);
        sEmojisMap.append(0x1f63c, R.drawable.emoji_1f63c);
        sEmojisMap.append(0x1f63d, R.drawable.emoji_1f63d);
        sEmojisMap.append(0x1f63e, R.drawable.emoji_1f63e);
        sEmojisMap.append(0x1f63f, R.drawable.emoji_1f63f);
        sEmojisMap.append(0x1f640, R.drawable.emoji_1f640);
        sEmojisMap.append(0x1f645, R.drawable.emoji_1f645);
        sEmojisMap.append(0x1f646, R.drawable.emoji_1f646);
        sEmojisMap.append(0x1f647, R.drawable.emoji_1f647);
        sEmojisMap.append(0x1f648, R.drawable.emoji_1f648);
        sEmojisMap.append(0x1f649, R.drawable.emoji_1f649);
        sEmojisMap.append(0x1f64a, R.drawable.emoji_1f64a);
        sEmojisMap.append(0x1f64b, R.drawable.emoji_1f64b);
        sEmojisMap.append(0x1f64c, R.drawable.emoji_1f64c);
        sEmojisMap.append(0x1f64d, R.drawable.emoji_1f64d);
        sEmojisMap.append(0x1f64e, R.drawable.emoji_1f64e);
        sEmojisMap.append(0x1f64f, R.drawable.emoji_1f64f);
        sEmojisMap.append(0x1f680, R.drawable.emoji_1f680);
        sEmojisMap.append(0x1f681, R.drawable.emoji_1f681);
        sEmojisMap.append(0x1f682, R.drawable.emoji_1f682);
        sEmojisMap.append(0x1f683, R.drawable.emoji_1f683);
        sEmojisMap.append(0x1f684, R.drawable.emoji_1f684);
        sEmojisMap.append(0x1f685, R.drawable.emoji_1f685);
        sEmojisMap.append(0x1f686, R.drawable.emoji_1f686);
        sEmojisMap.append(0x1f687, R.drawable.emoji_1f687);
        sEmojisMap.append(0x1f688, R.drawable.emoji_1f688);
        sEmojisMap.append(0x1f689, R.drawable.emoji_1f689);
        sEmojisMap.append(0x1f68a, R.drawable.emoji_1f68a);
        sEmojisMap.append(0x1f68b, R.drawable.emoji_1f68b);
        sEmojisMap.append(0x1f68c, R.drawable.emoji_1f68c);
        sEmojisMap.append(0x1f68d, R.drawable.emoji_1f68d);
        sEmojisMap.append(0x1f68e, R.drawable.emoji_1f68e);
        sEmojisMap.append(0x1f68f, R.drawable.emoji_1f68f);
        sEmojisMap.append(0x1f690, R.drawable.emoji_1f690);
        sEmojisMap.append(0x1f691, R.drawable.emoji_1f691);
        sEmojisMap.append(0x1f692, R.drawable.emoji_1f692);
        sEmojisMap.append(0x1f693, R.drawable.emoji_1f693);
        sEmojisMap.append(0x1f694, R.drawable.emoji_1f694);
        sEmojisMap.append(0x1f695, R.drawable.emoji_1f695);
        sEmojisMap.append(0x1f696, R.drawable.emoji_1f696);
        sEmojisMap.append(0x1f697, R.drawable.emoji_1f697);
        sEmojisMap.append(0x1f698, R.drawable.emoji_1f698);
        sEmojisMap.append(0x1f699, R.drawable.emoji_1f699);
        sEmojisMap.append(0x1f69a, R.drawable.emoji_1f69a);
        sEmojisMap.append(0x1f69b, R.drawable.emoji_1f69b);
        sEmojisMap.append(0x1f69c, R.drawable.emoji_1f69c);
        sEmojisMap.append(0x1f69d, R.drawable.emoji_1f69d);
        sEmojisMap.append(0x1f69e, R.drawable.emoji_1f69e);
        sEmojisMap.append(0x1f69f, R.drawable.emoji_1f69f);
        sEmojisMap.append(0x1f6a0, R.drawable.emoji_1f6a0);
        sEmojisMap.append(0x1f6a1, R.drawable.emoji_1f6a1);
        sEmojisMap.append(0x1f6a2, R.drawable.emoji_1f6a2);
        sEmojisMap.append(0x1f6a3, R.drawable.emoji_1f6a3);
        sEmojisMap.append(0x1f6a4, R.drawable.emoji_1f6a4);
        sEmojisMap.append(0x1f6a5, R.drawable.emoji_1f6a5);
        sEmojisMap.append(0x1f6a6, R.drawable.emoji_1f6a6);
        sEmojisMap.append(0x1f6a7, R.drawable.emoji_1f6a7);
        sEmojisMap.append(0x1f6a8, R.drawable.emoji_1f6a8);
        sEmojisMap.append(0x1f6a9, R.drawable.emoji_1f6a9);
        sEmojisMap.append(0x1f6aa, R.drawable.emoji_1f6aa);
        sEmojisMap.append(0x1f6ab, R.drawable.emoji_1f6ab);
        sEmojisMap.append(0x1f6ac, R.drawable.emoji_1f6ac);
        sEmojisMap.append(0x1f6ad, R.drawable.emoji_1f6ad);
        sEmojisMap.append(0x1f6ae, R.drawable.emoji_1f6ae);
        sEmojisMap.append(0x1f6af, R.drawable.emoji_1f6af);
        sEmojisMap.append(0x1f6b0, R.drawable.emoji_1f6b0);
        sEmojisMap.append(0x1f6b1, R.drawable.emoji_1f6b1);
        sEmojisMap.append(0x1f6b2, R.drawable.emoji_1f6b2);
        sEmojisMap.append(0x1f6b3, R.drawable.emoji_1f6b3);
        sEmojisMap.append(0x1f6b4, R.drawable.emoji_1f6b4);
        sEmojisMap.append(0x1f6b5, R.drawable.emoji_1f6b5);
        sEmojisMap.append(0x1f6b6, R.drawable.emoji_1f6b6);
        sEmojisMap.append(0x1f6b7, R.drawable.emoji_1f6b7);
        sEmojisMap.append(0x1f6b8, R.drawable.emoji_1f6b8);
        sEmojisMap.append(0x1f6b9, R.drawable.emoji_1f6b9);
        sEmojisMap.append(0x1f6ba, R.drawable.emoji_1f6ba);
        sEmojisMap.append(0x1f6bb, R.drawable.emoji_1f6bb);
        sEmojisMap.append(0x1f6bc, R.drawable.emoji_1f6bc);
        sEmojisMap.append(0x1f6bd, R.drawable.emoji_1f6bd);
        sEmojisMap.append(0x1f6be, R.drawable.emoji_1f6be);
        sEmojisMap.append(0x1f6bf, R.drawable.emoji_1f6bf);
        sEmojisMap.append(0x1f6c0, R.drawable.emoji_1f6c0);
        sEmojisMap.append(0x1f6c1, R.drawable.emoji_1f6c1);
        sEmojisMap.append(0x1f6c2, R.drawable.emoji_1f6c2);
        sEmojisMap.append(0x1f6c3, R.drawable.emoji_1f6c3);
        sEmojisMap.append(0x1f6c4, R.drawable.emoji_1f6c4);
        sEmojisMap.append(0x1f6c5, R.drawable.emoji_1f6c5);


        sSoftbanksMap.append(0xe001, R.drawable.emoji_1f466);
        sSoftbanksMap.append(0xe002, R.drawable.emoji_1f467);
        sSoftbanksMap.append(0xe003, R.drawable.emoji_1f48b);
        sSoftbanksMap.append(0xe004, R.drawable.emoji_1f468);
        sSoftbanksMap.append(0xe005, R.drawable.emoji_1f469);
        sSoftbanksMap.append(0xe006, R.drawable.emoji_1f455);
        sSoftbanksMap.append(0xe007, R.drawable.emoji_1f45e);
        sSoftbanksMap.append(0xe008, R.drawable.emoji_1f4f7);
        sSoftbanksMap.append(0xe009, R.drawable.emoji_1f4de);
        sSoftbanksMap.append(0xe00a, R.drawable.emoji_1f4f1);
        sSoftbanksMap.append(0xe00b, R.drawable.emoji_1f4e0);
        sSoftbanksMap.append(0xe00c, R.drawable.emoji_1f4bb);
        sSoftbanksMap.append(0xe00d, R.drawable.emoji_1f44a);
        sSoftbanksMap.append(0xe00e, R.drawable.emoji_1f44d);
        sSoftbanksMap.append(0xe00f, R.drawable.emoji_261d);
        sSoftbanksMap.append(0xe010, R.drawable.emoji_270a);
        sSoftbanksMap.append(0xe011, R.drawable.emoji_270c);
        sSoftbanksMap.append(0xe012, R.drawable.emoji_1f64b);
        sSoftbanksMap.append(0xe013, R.drawable.emoji_1f3bf);
        sSoftbanksMap.append(0xe014, R.drawable.emoji_26f3);
        sSoftbanksMap.append(0xe015, R.drawable.emoji_1f3be);
        sSoftbanksMap.append(0xe016, R.drawable.emoji_26be);
        sSoftbanksMap.append(0xe017, R.drawable.emoji_1f3c4);
        sSoftbanksMap.append(0xe018, R.drawable.emoji_26bd);
        sSoftbanksMap.append(0xe019, R.drawable.emoji_1f3a3);
        sSoftbanksMap.append(0xe01a, R.drawable.emoji_1f434);
        sSoftbanksMap.append(0xe01b, R.drawable.emoji_1f697);
        sSoftbanksMap.append(0xe01c, R.drawable.emoji_26f5);
        sSoftbanksMap.append(0xe01d, R.drawable.emoji_2708);
        sSoftbanksMap.append(0xe01e, R.drawable.emoji_1f683);
        sSoftbanksMap.append(0xe01f, R.drawable.emoji_1f685);
        sSoftbanksMap.append(0xe020, R.drawable.emoji_2753);
        sSoftbanksMap.append(0xe021, R.drawable.emoji_2757);
        sSoftbanksMap.append(0xe022, R.drawable.emoji_2764);
        sSoftbanksMap.append(0xe023, R.drawable.emoji_1f494);
        sSoftbanksMap.append(0xe024, R.drawable.emoji_1f550);
        sSoftbanksMap.append(0xe025, R.drawable.emoji_1f551);
        sSoftbanksMap.append(0xe026, R.drawable.emoji_1f552);
        sSoftbanksMap.append(0xe027, R.drawable.emoji_1f553);
        sSoftbanksMap.append(0xe028, R.drawable.emoji_1f554);
        sSoftbanksMap.append(0xe029, R.drawable.emoji_1f555);
        sSoftbanksMap.append(0xe02a, R.drawable.emoji_1f556);
        sSoftbanksMap.append(0xe02b, R.drawable.emoji_1f557);
        sSoftbanksMap.append(0xe02c, R.drawable.emoji_1f558);
        sSoftbanksMap.append(0xe02d, R.drawable.emoji_1f559);
        sSoftbanksMap.append(0xe02e, R.drawable.emoji_1f55a);
        sSoftbanksMap.append(0xe02f, R.drawable.emoji_1f55b);
        sSoftbanksMap.append(0xe030, R.drawable.emoji_1f338);
        sSoftbanksMap.append(0xe031, R.drawable.emoji_1f531);
        sSoftbanksMap.append(0xe032, R.drawable.emoji_1f339);
        sSoftbanksMap.append(0xe033, R.drawable.emoji_1f384);
        sSoftbanksMap.append(0xe034, R.drawable.emoji_1f48d);
        sSoftbanksMap.append(0xe035, R.drawable.emoji_1f48e);
        sSoftbanksMap.append(0xe036, R.drawable.emoji_1f3e0);
        sSoftbanksMap.append(0xe037, R.drawable.emoji_26ea);
        sSoftbanksMap.append(0xe038, R.drawable.emoji_1f3e2);
        sSoftbanksMap.append(0xe039, R.drawable.emoji_1f689);
        sSoftbanksMap.append(0xe03a, R.drawable.emoji_26fd);
        sSoftbanksMap.append(0xe03b, R.drawable.emoji_1f5fb);
        sSoftbanksMap.append(0xe03c, R.drawable.emoji_1f3a4);
        sSoftbanksMap.append(0xe03d, R.drawable.emoji_1f3a5);
        sSoftbanksMap.append(0xe03e, R.drawable.emoji_1f3b5);
        sSoftbanksMap.append(0xe03f, R.drawable.emoji_1f511);
        sSoftbanksMap.append(0xe040, R.drawable.emoji_1f3b7);
        sSoftbanksMap.append(0xe041, R.drawable.emoji_1f3b8);
        sSoftbanksMap.append(0xe042, R.drawable.emoji_1f3ba);
        sSoftbanksMap.append(0xe043, R.drawable.emoji_1f374);
        sSoftbanksMap.append(0xe044, R.drawable.emoji_1f377);
        sSoftbanksMap.append(0xe045, R.drawable.emoji_2615);
        sSoftbanksMap.append(0xe046, R.drawable.emoji_1f370);
        sSoftbanksMap.append(0xe047, R.drawable.emoji_1f37a);
        sSoftbanksMap.append(0xe048, R.drawable.emoji_26c4);
        sSoftbanksMap.append(0xe049, R.drawable.emoji_2601);
        sSoftbanksMap.append(0xe04a, R.drawable.emoji_2600);
        sSoftbanksMap.append(0xe04b, R.drawable.emoji_2614);
        sSoftbanksMap.append(0xe04c, R.drawable.emoji_1f313);
        sSoftbanksMap.append(0xe04d, R.drawable.emoji_1f304);
        sSoftbanksMap.append(0xe04e, R.drawable.emoji_1f47c);
        sSoftbanksMap.append(0xe04f, R.drawable.emoji_1f431);
        sSoftbanksMap.append(0xe050, R.drawable.emoji_1f42f);
        sSoftbanksMap.append(0xe051, R.drawable.emoji_1f43b);
        sSoftbanksMap.append(0xe052, R.drawable.emoji_1f429);
        sSoftbanksMap.append(0xe053, R.drawable.emoji_1f42d);
        sSoftbanksMap.append(0xe054, R.drawable.emoji_1f433);
        sSoftbanksMap.append(0xe055, R.drawable.emoji_1f427);
        sSoftbanksMap.append(0xe056, R.drawable.emoji_1f60a);
        sSoftbanksMap.append(0xe057, R.drawable.emoji_1f603);
        sSoftbanksMap.append(0xe058, R.drawable.emoji_1f61e);
        sSoftbanksMap.append(0xe059, R.drawable.emoji_1f620);
        sSoftbanksMap.append(0xe05a, R.drawable.emoji_1f4a9);
        sSoftbanksMap.append(0xe101, R.drawable.emoji_1f4ea);
        sSoftbanksMap.append(0xe102, R.drawable.emoji_1f4ee);
        sSoftbanksMap.append(0xe103, R.drawable.emoji_1f4e7);
        sSoftbanksMap.append(0xe104, R.drawable.emoji_1f4f2);
        sSoftbanksMap.append(0xe105, R.drawable.emoji_1f61c);
        sSoftbanksMap.append(0xe106, R.drawable.emoji_1f60d);
        sSoftbanksMap.append(0xe107, R.drawable.emoji_1f631);
        sSoftbanksMap.append(0xe108, R.drawable.emoji_1f613);
        sSoftbanksMap.append(0xe109, R.drawable.emoji_1f435);
        sSoftbanksMap.append(0xe10a, R.drawable.emoji_1f419);
        sSoftbanksMap.append(0xe10b, R.drawable.emoji_1f437);
        sSoftbanksMap.append(0xe10c, R.drawable.emoji_1f47d);
        sSoftbanksMap.append(0xe10d, R.drawable.emoji_1f680);
        sSoftbanksMap.append(0xe10e, R.drawable.emoji_1f451);
        sSoftbanksMap.append(0xe10f, R.drawable.emoji_1f4a1);
        sSoftbanksMap.append(0xe110, R.drawable.emoji_1f331);
        sSoftbanksMap.append(0xe111, R.drawable.emoji_1f48f);
        sSoftbanksMap.append(0xe112, R.drawable.emoji_1f381);
        sSoftbanksMap.append(0xe113, R.drawable.emoji_1f52b);
        sSoftbanksMap.append(0xe114, R.drawable.emoji_1f50d);
        sSoftbanksMap.append(0xe115, R.drawable.emoji_1f3c3);
        sSoftbanksMap.append(0xe116, R.drawable.emoji_1f528);
        sSoftbanksMap.append(0xe117, R.drawable.emoji_1f386);
        sSoftbanksMap.append(0xe118, R.drawable.emoji_1f341);
        sSoftbanksMap.append(0xe119, R.drawable.emoji_1f342);
        sSoftbanksMap.append(0xe11a, R.drawable.emoji_1f47f);
        sSoftbanksMap.append(0xe11b, R.drawable.emoji_1f47b);
        sSoftbanksMap.append(0xe11c, R.drawable.emoji_1f480);
        sSoftbanksMap.append(0xe11d, R.drawable.emoji_1f525);
        sSoftbanksMap.append(0xe11e, R.drawable.emoji_1f4bc);
        sSoftbanksMap.append(0xe11f, R.drawable.emoji_1f4ba);
        sSoftbanksMap.append(0xe120, R.drawable.emoji_1f354);
        sSoftbanksMap.append(0xe121, R.drawable.emoji_26f2);
        sSoftbanksMap.append(0xe122, R.drawable.emoji_26fa);
        sSoftbanksMap.append(0xe123, R.drawable.emoji_2668);
        sSoftbanksMap.append(0xe124, R.drawable.emoji_1f3a1);
        sSoftbanksMap.append(0xe125, R.drawable.emoji_1f3ab);
        sSoftbanksMap.append(0xe126, R.drawable.emoji_1f4bf);
        sSoftbanksMap.append(0xe127, R.drawable.emoji_1f4c0);
        sSoftbanksMap.append(0xe128, R.drawable.emoji_1f4fb);
        sSoftbanksMap.append(0xe129, R.drawable.emoji_1f4fc);
        sSoftbanksMap.append(0xe12a, R.drawable.emoji_1f4fa);
        sSoftbanksMap.append(0xe12b, R.drawable.emoji_1f47e);
        sSoftbanksMap.append(0xe12c, R.drawable.emoji_303d);
        sSoftbanksMap.append(0xe12d, R.drawable.emoji_1f004);
        sSoftbanksMap.append(0xe12e, R.drawable.emoji_1f19a);
        sSoftbanksMap.append(0xe12f, R.drawable.emoji_1f4b0);
        sSoftbanksMap.append(0xe130, R.drawable.emoji_1f3af);
        sSoftbanksMap.append(0xe131, R.drawable.emoji_1f3c6);
        sSoftbanksMap.append(0xe132, R.drawable.emoji_1f3c1);
        sSoftbanksMap.append(0xe133, R.drawable.emoji_1f3b0);
        sSoftbanksMap.append(0xe134, R.drawable.emoji_1f40e);
        sSoftbanksMap.append(0xe135, R.drawable.emoji_1f6a4);
        sSoftbanksMap.append(0xe136, R.drawable.emoji_1f6b2);
        sSoftbanksMap.append(0xe137, R.drawable.emoji_1f6a7);
        sSoftbanksMap.append(0xe138, R.drawable.emoji_1f6b9);
        sSoftbanksMap.append(0xe139, R.drawable.emoji_1f6ba);
        sSoftbanksMap.append(0xe13a, R.drawable.emoji_1f6bc);
        sSoftbanksMap.append(0xe13b, R.drawable.emoji_1f489);
        sSoftbanksMap.append(0xe13c, R.drawable.emoji_1f4a4);
        sSoftbanksMap.append(0xe13d, R.drawable.emoji_26a1);
        sSoftbanksMap.append(0xe13e, R.drawable.emoji_1f460);
        sSoftbanksMap.append(0xe13f, R.drawable.emoji_1f6c0);
        sSoftbanksMap.append(0xe140, R.drawable.emoji_1f6bd);
        sSoftbanksMap.append(0xe141, R.drawable.emoji_1f50a);
        sSoftbanksMap.append(0xe142, R.drawable.emoji_1f4e2);
        sSoftbanksMap.append(0xe143, R.drawable.emoji_1f38c);
        sSoftbanksMap.append(0xe144, R.drawable.emoji_1f50f);
        sSoftbanksMap.append(0xe145, R.drawable.emoji_1f513);
        sSoftbanksMap.append(0xe146, R.drawable.emoji_1f306);
        sSoftbanksMap.append(0xe147, R.drawable.emoji_1f373);
        sSoftbanksMap.append(0xe148, R.drawable.emoji_1f4c7);
        sSoftbanksMap.append(0xe149, R.drawable.emoji_1f4b1);
        sSoftbanksMap.append(0xe14a, R.drawable.emoji_1f4b9);
        sSoftbanksMap.append(0xe14b, R.drawable.emoji_1f4e1);
        sSoftbanksMap.append(0xe14c, R.drawable.emoji_1f4aa);
        sSoftbanksMap.append(0xe14d, R.drawable.emoji_1f3e6);
        sSoftbanksMap.append(0xe14e, R.drawable.emoji_1f6a5);
        sSoftbanksMap.append(0xe14f, R.drawable.emoji_1f17f);
        sSoftbanksMap.append(0xe150, R.drawable.emoji_1f68f);
        sSoftbanksMap.append(0xe151, R.drawable.emoji_1f6bb);
        sSoftbanksMap.append(0xe152, R.drawable.emoji_1f46e);
        sSoftbanksMap.append(0xe153, R.drawable.emoji_1f3e3);
        sSoftbanksMap.append(0xe154, R.drawable.emoji_1f3e7);
        sSoftbanksMap.append(0xe155, R.drawable.emoji_1f3e5);
        sSoftbanksMap.append(0xe156, R.drawable.emoji_1f3ea);
        sSoftbanksMap.append(0xe157, R.drawable.emoji_1f3eb);
        sSoftbanksMap.append(0xe158, R.drawable.emoji_1f3e8);
        sSoftbanksMap.append(0xe159, R.drawable.emoji_1f68c);
        sSoftbanksMap.append(0xe15a, R.drawable.emoji_1f695);
        sSoftbanksMap.append(0xe201, R.drawable.emoji_1f6b6);
        sSoftbanksMap.append(0xe202, R.drawable.emoji_1f6a2);
        sSoftbanksMap.append(0xe203, R.drawable.emoji_1f201);
        sSoftbanksMap.append(0xe204, R.drawable.emoji_1f49f);
        sSoftbanksMap.append(0xe205, R.drawable.emoji_2734);
        sSoftbanksMap.append(0xe206, R.drawable.emoji_2733);
        sSoftbanksMap.append(0xe207, R.drawable.emoji_1f51e);
        sSoftbanksMap.append(0xe208, R.drawable.emoji_1f6ad);
        sSoftbanksMap.append(0xe209, R.drawable.emoji_1f530);
        sSoftbanksMap.append(0xe20a, R.drawable.emoji_267f);
        sSoftbanksMap.append(0xe20b, R.drawable.emoji_1f4f6);
        sSoftbanksMap.append(0xe20c, R.drawable.emoji_2665);
        sSoftbanksMap.append(0xe20d, R.drawable.emoji_2666);
        sSoftbanksMap.append(0xe20e, R.drawable.emoji_2660);
        sSoftbanksMap.append(0xe20f, R.drawable.emoji_2663);
        sSoftbanksMap.append(0xe210, R.drawable.emoji_0023);
        sSoftbanksMap.append(0xe211, R.drawable.emoji_27bf);
        sSoftbanksMap.append(0xe212, R.drawable.emoji_1f195);
        sSoftbanksMap.append(0xe213, R.drawable.emoji_1f199);
        sSoftbanksMap.append(0xe214, R.drawable.emoji_1f192);
        sSoftbanksMap.append(0xe215, R.drawable.emoji_1f236);
        sSoftbanksMap.append(0xe216, R.drawable.emoji_1f21a);
        sSoftbanksMap.append(0xe217, R.drawable.emoji_1f237);
        sSoftbanksMap.append(0xe218, R.drawable.emoji_1f238);
        sSoftbanksMap.append(0xe219, R.drawable.emoji_1f534);
        sSoftbanksMap.append(0xe21a, R.drawable.emoji_1f532);
        sSoftbanksMap.append(0xe21b, R.drawable.emoji_1f533);
        sSoftbanksMap.append(0xe21c, R.drawable.emoji_0031);
        sSoftbanksMap.append(0xe21d, R.drawable.emoji_0032);
        sSoftbanksMap.append(0xe21e, R.drawable.emoji_0033);
        sSoftbanksMap.append(0xe21f, R.drawable.emoji_0034);
        sSoftbanksMap.append(0xe220, R.drawable.emoji_0035);
        sSoftbanksMap.append(0xe221, R.drawable.emoji_0036);
        sSoftbanksMap.append(0xe222, R.drawable.emoji_0037);
        sSoftbanksMap.append(0xe223, R.drawable.emoji_0038);
        sSoftbanksMap.append(0xe224, R.drawable.emoji_0039);
        sSoftbanksMap.append(0xe225, R.drawable.emoji_0030);
        sSoftbanksMap.append(0xe226, R.drawable.emoji_1f250);
        sSoftbanksMap.append(0xe227, R.drawable.emoji_1f239);
        sSoftbanksMap.append(0xe228, R.drawable.emoji_1f202);
        sSoftbanksMap.append(0xe229, R.drawable.emoji_1f194);
        sSoftbanksMap.append(0xe22a, R.drawable.emoji_1f235);
        sSoftbanksMap.append(0xe22b, R.drawable.emoji_1f233);
        sSoftbanksMap.append(0xe22c, R.drawable.emoji_1f22f);
        sSoftbanksMap.append(0xe22d, R.drawable.emoji_1f23a);
        sSoftbanksMap.append(0xe22e, R.drawable.emoji_1f446);
        sSoftbanksMap.append(0xe22f, R.drawable.emoji_1f447);
        sSoftbanksMap.append(0xe230, R.drawable.emoji_1f448);
        sSoftbanksMap.append(0xe231, R.drawable.emoji_1f449);
        sSoftbanksMap.append(0xe232, R.drawable.emoji_2b06);
        sSoftbanksMap.append(0xe233, R.drawable.emoji_2b07);
        sSoftbanksMap.append(0xe234, R.drawable.emoji_27a1);
        sSoftbanksMap.append(0xe235, R.drawable.emoji_1f519);
        sSoftbanksMap.append(0xe236, R.drawable.emoji_2197);
        sSoftbanksMap.append(0xe237, R.drawable.emoji_2196);
        sSoftbanksMap.append(0xe238, R.drawable.emoji_2198);
        sSoftbanksMap.append(0xe239, R.drawable.emoji_2199);
        sSoftbanksMap.append(0xe23a, R.drawable.emoji_25b6);
        sSoftbanksMap.append(0xe23b, R.drawable.emoji_25c0);
        sSoftbanksMap.append(0xe23c, R.drawable.emoji_23e9);
        sSoftbanksMap.append(0xe23d, R.drawable.emoji_23ea);
        sSoftbanksMap.append(0xe23e, R.drawable.emoji_1f52e);
        sSoftbanksMap.append(0xe23f, R.drawable.emoji_2648);
        sSoftbanksMap.append(0xe240, R.drawable.emoji_2649);
        sSoftbanksMap.append(0xe241, R.drawable.emoji_264a);
        sSoftbanksMap.append(0xe242, R.drawable.emoji_264b);
        sSoftbanksMap.append(0xe243, R.drawable.emoji_264c);
        sSoftbanksMap.append(0xe244, R.drawable.emoji_264d);
        sSoftbanksMap.append(0xe245, R.drawable.emoji_264e);
        sSoftbanksMap.append(0xe246, R.drawable.emoji_264f);
        sSoftbanksMap.append(0xe247, R.drawable.emoji_2650);
        sSoftbanksMap.append(0xe248, R.drawable.emoji_2651);
        sSoftbanksMap.append(0xe249, R.drawable.emoji_2652);
        sSoftbanksMap.append(0xe24a, R.drawable.emoji_2653);
        sSoftbanksMap.append(0xe24b, R.drawable.emoji_26ce);
        sSoftbanksMap.append(0xe24c, R.drawable.emoji_1f51d);
        sSoftbanksMap.append(0xe24d, R.drawable.emoji_1f197);
        sSoftbanksMap.append(0xe24e, R.drawable.emoji_00a9);
        sSoftbanksMap.append(0xe24f, R.drawable.emoji_00ae);
        sSoftbanksMap.append(0xe250, R.drawable.emoji_1f4f3);
        sSoftbanksMap.append(0xe251, R.drawable.emoji_1f4f4);
        sSoftbanksMap.append(0xe252, R.drawable.emoji_26a0);
        sSoftbanksMap.append(0xe253, R.drawable.emoji_1f481);
        sSoftbanksMap.append(0xe301, R.drawable.emoji_1f4c3);
        sSoftbanksMap.append(0xe302, R.drawable.emoji_1f454);
        sSoftbanksMap.append(0xe303, R.drawable.emoji_1f33a);
        sSoftbanksMap.append(0xe304, R.drawable.emoji_1f337);
        sSoftbanksMap.append(0xe305, R.drawable.emoji_1f33b);
        sSoftbanksMap.append(0xe306, R.drawable.emoji_1f490);
        sSoftbanksMap.append(0xe307, R.drawable.emoji_1f334);
        sSoftbanksMap.append(0xe308, R.drawable.emoji_1f335);
        sSoftbanksMap.append(0xe309, R.drawable.emoji_1f6be);
        sSoftbanksMap.append(0xe30a, R.drawable.emoji_1f3a7);
        sSoftbanksMap.append(0xe30b, R.drawable.emoji_1f376);
        sSoftbanksMap.append(0xe30c, R.drawable.emoji_1f37b);
        sSoftbanksMap.append(0xe30d, R.drawable.emoji_3297);
        sSoftbanksMap.append(0xe30e, R.drawable.emoji_1f6ac);
        sSoftbanksMap.append(0xe30f, R.drawable.emoji_1f48a);
        sSoftbanksMap.append(0xe310, R.drawable.emoji_1f388);
        sSoftbanksMap.append(0xe311, R.drawable.emoji_1f4a3);
        sSoftbanksMap.append(0xe312, R.drawable.emoji_1f389);
        sSoftbanksMap.append(0xe313, R.drawable.emoji_2702);
        sSoftbanksMap.append(0xe314, R.drawable.emoji_1f380);
        sSoftbanksMap.append(0xe315, R.drawable.emoji_3299);
        sSoftbanksMap.append(0xe316, R.drawable.emoji_1f4bd);
        sSoftbanksMap.append(0xe317, R.drawable.emoji_1f4e3);
        sSoftbanksMap.append(0xe318, R.drawable.emoji_1f452);
        sSoftbanksMap.append(0xe319, R.drawable.emoji_1f457);
        sSoftbanksMap.append(0xe31a, R.drawable.emoji_1f461);
        sSoftbanksMap.append(0xe31b, R.drawable.emoji_1f462);
        sSoftbanksMap.append(0xe31c, R.drawable.emoji_1f484);
        sSoftbanksMap.append(0xe31d, R.drawable.emoji_1f485);
        sSoftbanksMap.append(0xe31e, R.drawable.emoji_1f486);
        sSoftbanksMap.append(0xe31f, R.drawable.emoji_1f487);
        sSoftbanksMap.append(0xe320, R.drawable.emoji_1f488);
        sSoftbanksMap.append(0xe321, R.drawable.emoji_1f458);
        sSoftbanksMap.append(0xe322, R.drawable.emoji_1f459);
        sSoftbanksMap.append(0xe323, R.drawable.emoji_1f45c);
        sSoftbanksMap.append(0xe324, R.drawable.emoji_1f3ac);
        sSoftbanksMap.append(0xe325, R.drawable.emoji_1f514);
        sSoftbanksMap.append(0xe326, R.drawable.emoji_1f3b6);
        sSoftbanksMap.append(0xe327, R.drawable.emoji_1f493);
        sSoftbanksMap.append(0xe328, R.drawable.emoji_1f48c);
        sSoftbanksMap.append(0xe329, R.drawable.emoji_1f498);
        sSoftbanksMap.append(0xe32a, R.drawable.emoji_1f499);
        sSoftbanksMap.append(0xe32b, R.drawable.emoji_1f49a);
        sSoftbanksMap.append(0xe32c, R.drawable.emoji_1f49b);
        sSoftbanksMap.append(0xe32d, R.drawable.emoji_1f49c);
        sSoftbanksMap.append(0xe32e, R.drawable.emoji_2728);
        sSoftbanksMap.append(0xe32f, R.drawable.emoji_2b50);
        sSoftbanksMap.append(0xe330, R.drawable.emoji_1f4a8);
        sSoftbanksMap.append(0xe331, R.drawable.emoji_1f4a6);
        sSoftbanksMap.append(0xe332, R.drawable.emoji_2b55);
        sSoftbanksMap.append(0xe333, R.drawable.emoji_2716);
        sSoftbanksMap.append(0xe334, R.drawable.emoji_1f4a2);
        sSoftbanksMap.append(0xe335, R.drawable.emoji_1f31f);
        sSoftbanksMap.append(0xe336, R.drawable.emoji_2754);
        sSoftbanksMap.append(0xe337, R.drawable.emoji_2755);
        sSoftbanksMap.append(0xe338, R.drawable.emoji_1f375);
        sSoftbanksMap.append(0xe339, R.drawable.emoji_1f35e);
        sSoftbanksMap.append(0xe33a, R.drawable.emoji_1f366);
        sSoftbanksMap.append(0xe33b, R.drawable.emoji_1f35f);
        sSoftbanksMap.append(0xe33c, R.drawable.emoji_1f361);
        sSoftbanksMap.append(0xe33d, R.drawable.emoji_1f358);
        sSoftbanksMap.append(0xe33e, R.drawable.emoji_1f35a);
        sSoftbanksMap.append(0xe33f, R.drawable.emoji_1f35d);
        sSoftbanksMap.append(0xe340, R.drawable.emoji_1f35c);
        sSoftbanksMap.append(0xe341, R.drawable.emoji_1f35b);
        sSoftbanksMap.append(0xe342, R.drawable.emoji_1f359);
        sSoftbanksMap.append(0xe343, R.drawable.emoji_1f362);
        sSoftbanksMap.append(0xe344, R.drawable.emoji_1f363);
        sSoftbanksMap.append(0xe345, R.drawable.emoji_1f34e);
        sSoftbanksMap.append(0xe346, R.drawable.emoji_1f34a);
        sSoftbanksMap.append(0xe347, R.drawable.emoji_1f353);
        sSoftbanksMap.append(0xe348, R.drawable.emoji_1f349);
        sSoftbanksMap.append(0xe349, R.drawable.emoji_1f345);
        sSoftbanksMap.append(0xe34a, R.drawable.emoji_1f346);
        sSoftbanksMap.append(0xe34b, R.drawable.emoji_1f382);
        sSoftbanksMap.append(0xe34c, R.drawable.emoji_1f371);
        sSoftbanksMap.append(0xe34d, R.drawable.emoji_1f372);
        sSoftbanksMap.append(0xe401, R.drawable.emoji_1f625);
        sSoftbanksMap.append(0xe402, R.drawable.emoji_1f60f);
        sSoftbanksMap.append(0xe403, R.drawable.emoji_1f614);
        sSoftbanksMap.append(0xe404, R.drawable.emoji_1f601);
        sSoftbanksMap.append(0xe405, R.drawable.emoji_1f609);
        sSoftbanksMap.append(0xe406, R.drawable.emoji_1f623);
        sSoftbanksMap.append(0xe407, R.drawable.emoji_1f616);
        sSoftbanksMap.append(0xe408, R.drawable.emoji_1f62a);
        sSoftbanksMap.append(0xe409, R.drawable.emoji_1f445);
        sSoftbanksMap.append(0xe40a, R.drawable.emoji_1f606);
        sSoftbanksMap.append(0xe40b, R.drawable.emoji_1f628);
        sSoftbanksMap.append(0xe40c, R.drawable.emoji_1f637);
        sSoftbanksMap.append(0xe40d, R.drawable.emoji_1f633);
        sSoftbanksMap.append(0xe40e, R.drawable.emoji_1f612);
        sSoftbanksMap.append(0xe40f, R.drawable.emoji_1f630);
        sSoftbanksMap.append(0xe410, R.drawable.emoji_1f632);
        sSoftbanksMap.append(0xe411, R.drawable.emoji_1f62d);
        sSoftbanksMap.append(0xe412, R.drawable.emoji_1f602);
        sSoftbanksMap.append(0xe413, R.drawable.emoji_1f622);
        sSoftbanksMap.append(0xe414, R.drawable.emoji_263a);
        sSoftbanksMap.append(0xe415, R.drawable.emoji_1f605);
        sSoftbanksMap.append(0xe416, R.drawable.emoji_1f621);
        sSoftbanksMap.append(0xe417, R.drawable.emoji_1f61a);
        sSoftbanksMap.append(0xe418, R.drawable.emoji_1f618);
        sSoftbanksMap.append(0xe419, R.drawable.emoji_1f440);
        sSoftbanksMap.append(0xe41a, R.drawable.emoji_1f443);
        sSoftbanksMap.append(0xe41b, R.drawable.emoji_1f442);
        sSoftbanksMap.append(0xe41c, R.drawable.emoji_1f444);
        sSoftbanksMap.append(0xe41d, R.drawable.emoji_1f64f);
        sSoftbanksMap.append(0xe41e, R.drawable.emoji_1f44b);
        sSoftbanksMap.append(0xe41f, R.drawable.emoji_1f44f);
        sSoftbanksMap.append(0xe420, R.drawable.emoji_1f44c);
        sSoftbanksMap.append(0xe421, R.drawable.emoji_1f44e);
        sSoftbanksMap.append(0xe422, R.drawable.emoji_1f450);
        sSoftbanksMap.append(0xe423, R.drawable.emoji_1f645);
        sSoftbanksMap.append(0xe424, R.drawable.emoji_1f646);
        sSoftbanksMap.append(0xe425, R.drawable.emoji_1f491);
        sSoftbanksMap.append(0xe426, R.drawable.emoji_1f647);
        sSoftbanksMap.append(0xe427, R.drawable.emoji_1f64c);
        sSoftbanksMap.append(0xe428, R.drawable.emoji_1f46b);
        sSoftbanksMap.append(0xe429, R.drawable.emoji_1f46f);
        sSoftbanksMap.append(0xe42a, R.drawable.emoji_1f3c0);
        sSoftbanksMap.append(0xe42b, R.drawable.emoji_1f3c8);
        sSoftbanksMap.append(0xe42c, R.drawable.emoji_1f3b1);
        sSoftbanksMap.append(0xe42d, R.drawable.emoji_1f3ca);
        sSoftbanksMap.append(0xe42e, R.drawable.emoji_1f699);
        sSoftbanksMap.append(0xe42f, R.drawable.emoji_1f69a);
        sSoftbanksMap.append(0xe430, R.drawable.emoji_1f692);
        sSoftbanksMap.append(0xe431, R.drawable.emoji_1f691);
        sSoftbanksMap.append(0xe432, R.drawable.emoji_1f693);
        sSoftbanksMap.append(0xe433, R.drawable.emoji_1f3a2);
        sSoftbanksMap.append(0xe434, R.drawable.emoji_1f687);
        sSoftbanksMap.append(0xe435, R.drawable.emoji_1f684);
        sSoftbanksMap.append(0xe436, R.drawable.emoji_1f38d);
        sSoftbanksMap.append(0xe437, R.drawable.emoji_1f49d);
        sSoftbanksMap.append(0xe438, R.drawable.emoji_1f38e);
        sSoftbanksMap.append(0xe439, R.drawable.emoji_1f393);
        sSoftbanksMap.append(0xe43a, R.drawable.emoji_1f392);
        sSoftbanksMap.append(0xe43b, R.drawable.emoji_1f38f);
        sSoftbanksMap.append(0xe43c, R.drawable.emoji_1f302);
        sSoftbanksMap.append(0xe43d, R.drawable.emoji_1f492);
        sSoftbanksMap.append(0xe43e, R.drawable.emoji_1f30a);
        sSoftbanksMap.append(0xe43f, R.drawable.emoji_1f367);
        sSoftbanksMap.append(0xe440, R.drawable.emoji_1f387);
        sSoftbanksMap.append(0xe441, R.drawable.emoji_1f41a);
        sSoftbanksMap.append(0xe442, R.drawable.emoji_1f390);
        sSoftbanksMap.append(0xe443, R.drawable.emoji_1f300);
        sSoftbanksMap.append(0xe444, R.drawable.emoji_1f33e);
        sSoftbanksMap.append(0xe445, R.drawable.emoji_1f383);
        sSoftbanksMap.append(0xe446, R.drawable.emoji_1f391);
        sSoftbanksMap.append(0xe447, R.drawable.emoji_1f343);
        sSoftbanksMap.append(0xe448, R.drawable.emoji_1f385);
        sSoftbanksMap.append(0xe449, R.drawable.emoji_1f305);
        sSoftbanksMap.append(0xe44a, R.drawable.emoji_1f307);
        sSoftbanksMap.append(0xe44b, R.drawable.emoji_1f303);
        sSoftbanksMap.append(0xe44b, R.drawable.emoji_1f30c);
        sSoftbanksMap.append(0xe44c, R.drawable.emoji_1f308);
        sSoftbanksMap.append(0xe501, R.drawable.emoji_1f3e9);
        sSoftbanksMap.append(0xe502, R.drawable.emoji_1f3a8);
        sSoftbanksMap.append(0xe503, R.drawable.emoji_1f3a9);
        sSoftbanksMap.append(0xe504, R.drawable.emoji_1f3ec);
        sSoftbanksMap.append(0xe505, R.drawable.emoji_1f3ef);
        sSoftbanksMap.append(0xe506, R.drawable.emoji_1f3f0);
        sSoftbanksMap.append(0xe507, R.drawable.emoji_1f3a6);
        sSoftbanksMap.append(0xe508, R.drawable.emoji_1f3ed);
        sSoftbanksMap.append(0xe509, R.drawable.emoji_1f5fc);
        sSoftbanksMap.append(0xe50b, R.drawable.emoji_1f1ef_1f1f5);
        sSoftbanksMap.append(0xe50c, R.drawable.emoji_1f1fa_1f1f8);
        sSoftbanksMap.append(0xe50d, R.drawable.emoji_1f1eb_1f1f7);
        sSoftbanksMap.append(0xe50e, R.drawable.emoji_1f1e9_1f1ea);
        sSoftbanksMap.append(0xe50f, R.drawable.emoji_1f1ee_1f1f9);
        sSoftbanksMap.append(0xe510, R.drawable.emoji_1f1ec_1f1e7);
        sSoftbanksMap.append(0xe511, R.drawable.emoji_1f1ea_1f1f8);
        sSoftbanksMap.append(0xe512, R.drawable.emoji_1f1f7_1f1fa);
        sSoftbanksMap.append(0xe513, R.drawable.emoji_1f1e8_1f1f3);
        sSoftbanksMap.append(0xe514, R.drawable.emoji_1f1f0_1f1f7);
        sSoftbanksMap.append(0xe515, R.drawable.emoji_1f471);
        sSoftbanksMap.append(0xe516, R.drawable.emoji_1f472);
        sSoftbanksMap.append(0xe517, R.drawable.emoji_1f473);
        sSoftbanksMap.append(0xe518, R.drawable.emoji_1f474);
        sSoftbanksMap.append(0xe519, R.drawable.emoji_1f475);
        sSoftbanksMap.append(0xe51a, R.drawable.emoji_1f476);
        sSoftbanksMap.append(0xe51b, R.drawable.emoji_1f477);
        sSoftbanksMap.append(0xe51c, R.drawable.emoji_1f478);
        sSoftbanksMap.append(0xe51d, R.drawable.emoji_1f5fd);
        sSoftbanksMap.append(0xe51e, R.drawable.emoji_1f482);
        sSoftbanksMap.append(0xe51f, R.drawable.emoji_1f483);
        sSoftbanksMap.append(0xe520, R.drawable.emoji_1f42c);
        sSoftbanksMap.append(0xe521, R.drawable.emoji_1f426);
        sSoftbanksMap.append(0xe522, R.drawable.emoji_1f420);
        sSoftbanksMap.append(0xe523, R.drawable.emoji_1f423);
        sSoftbanksMap.append(0xe524, R.drawable.emoji_1f439);
        sSoftbanksMap.append(0xe525, R.drawable.emoji_1f41b);
        sSoftbanksMap.append(0xe526, R.drawable.emoji_1f418);
        sSoftbanksMap.append(0xe527, R.drawable.emoji_1f428);
        sSoftbanksMap.append(0xe528, R.drawable.emoji_1f412);
        sSoftbanksMap.append(0xe529, R.drawable.emoji_1f411);
        sSoftbanksMap.append(0xe52a, R.drawable.emoji_1f43a);
        sSoftbanksMap.append(0xe52b, R.drawable.emoji_1f42e);
        sSoftbanksMap.append(0xe52c, R.drawable.emoji_1f430);
        sSoftbanksMap.append(0xe52d, R.drawable.emoji_1f40d);
        sSoftbanksMap.append(0xe52e, R.drawable.emoji_1f414);
        sSoftbanksMap.append(0xe52f, R.drawable.emoji_1f417);
        sSoftbanksMap.append(0xe530, R.drawable.emoji_1f42b);
        sSoftbanksMap.append(0xe531, R.drawable.emoji_1f438);
        sSoftbanksMap.append(0xe532, R.drawable.emoji_1f170);
        sSoftbanksMap.append(0xe533, R.drawable.emoji_1f171);
        sSoftbanksMap.append(0xe534, R.drawable.emoji_1f18e);
        sSoftbanksMap.append(0xe535, R.drawable.emoji_1f17e);
        sSoftbanksMap.append(0xe536, R.drawable.emoji_1f43e);
        sSoftbanksMap.append(0xe537, R.drawable.emoji_2122);

        Log.d("emoji", String.format("init emoji cost: %dms", (System.currentTimeMillis() - start)));
    }

    private EmojiconHandler() {
    }

    private static boolean isSoftBankEmoji(char c) {
        return ((c >> 12) == 0xe);
    }

    private static int getEmojiResource(int codePoint) {
        return sEmojisMap.get(codePoint);
    }

    private static int getSoftbankEmojiResource(char c) {
        return sSoftbanksMap.get(c);
    }

    /**
     * @param context   Convert emoji characters of the given Spannable to the according emojicon.
     * @param text
     * @param emojiSize
     */
    public static void addEmojis(Context context, SpannableStringBuilder text, int emojiSize, int textSize) {
        addEmojis(context, text, emojiSize, textSize, 0, -1, false);
    }

    /**
     * Convert emoji characters of the given Spannable to the according emojicon.
     *
     * @param context
     * @param text
     * @param emojiSize
     * @param index
     * @param length
     */
    public static void addEmojis(Context context, SpannableStringBuilder text, int emojiSize, int textSize, int index, int length) {
        addEmojis(context, text, emojiSize, textSize, index, length, false);
    }

    /**
     * Convert emoji characters of the given Spannable to the according emojicon.
     *
     * @param context
     * @param text
     * @param emojiSize
     * @param useSystemDefault
     */
    public static void addEmojis(Context context, SpannableStringBuilder text, int emojiSize, int textSize, boolean useSystemDefault) {
        addEmojis(context, text, emojiSize, textSize, 0, -1, useSystemDefault);
    }

    /**
     * Convert emoji characters of the given Spannable to the according emojicon.
     *
     * @param context
     * @param text
     * @param emojiSize
     * @param index
     * @param length
     * @param useSystemDefault
     */
    public static SpannableStringBuilder addEmojis(Context context, SpannableStringBuilder text, int emojiSize, int textSize, int index, int length, boolean useSystemDefault) {
        if (useSystemDefault) {
            return text;
        }

        int textLengthToProcess = calculateLegalTextLength(text, index, length);

        // remove spans throughout all text
        EmojiconSpan[] oldSpans = text.getSpans(0, text.length(), EmojiconSpan.class);
        for (EmojiconSpan oldSpan : oldSpans) {
            text.removeSpan(oldSpan);
        }

        int[] results = new int[3];
        String textStr = text.toString();

        int processIdx = index;
        while (processIdx < textLengthToProcess) {

            boolean isEmoji = findEmoji(textStr, processIdx, textLengthToProcess, results);
            int skip = results[1];
            if (isEmoji) {
                int icon = results[0];
                boolean isQQFace = results[2] > 0;
                EmojiconSpan span = new EmojiconSpan(context, icon, (int) (emojiSize * EMOJIICON_SCALE),
                        (int) (emojiSize * EMOJIICON_SCALE));
                span.setTranslateY(isQQFace ? DisplayUtils.dp2px(context,QQFACE_TRANSLATE_Y) : EMOJIICON_TRANSLATE_Y);
                if (span.getCachedDrawable() == null) {
                    text.replace(processIdx, processIdx + skip, "..");
                    //重新计算字符串的合法长度
                    textLengthToProcess = calculateLegalTextLength(text, index, length);
                } else {
                    text.setSpan(span, processIdx, processIdx + skip, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                }
            }

            processIdx += skip;
        }
        return (SpannableStringBuilder) text.subSequence(index, processIdx);
    }

    /**
     * 判断文本位于start的字节是否为emoji。
     *
     * @param text
     * @param start
     * @param end
     * @param result 长度为3的数据。当第一位表示emoji的资源id，
     *               第二位表示emoji在原文本占位长度，
     *               第三位表示emoji类型是否位qq表情。
     * @return 如果是emoji，返回True。
     */
    public static boolean findEmoji(String text, int start, int end, int[] result) {
        int skip = 0;
        int icon = 0;
        char c = text.charAt(start);
        if (isSoftBankEmoji(c)) {
            icon = getSoftbankEmojiResource(c);
            skip = icon == 0 ? 0 : 1;
        }

        if (icon == 0) {
            int unicode = Character.codePointAt(text, start);
            skip = Character.charCount(unicode);

            if (unicode > 0xff) {
                icon = getEmojiResource(unicode);
            }

            if (icon == 0 && start + skip < end) {
                int followUnicode = Character.codePointAt(text, start + skip);
                if (followUnicode == 0x20e3) {
                    int followSkip = Character.charCount(followUnicode);
                    switch (unicode) {
                        case 0x0031:
                            icon = R.drawable.emoji_0031;
                            break;
                        case 0x0032:
                            icon = R.drawable.emoji_0032;
                            break;
                        case 0x0033:
                            icon = R.drawable.emoji_0033;
                            break;
                        case 0x0034:
                            icon = R.drawable.emoji_0034;
                            break;
                        case 0x0035:
                            icon = R.drawable.emoji_0035;
                            break;
                        case 0x0036:
                            icon = R.drawable.emoji_0036;
                            break;
                        case 0x0037:
                            icon = R.drawable.emoji_0037;
                            break;
                        case 0x0038:
                            icon = R.drawable.emoji_0038;
                            break;
                        case 0x0039:
                            icon = R.drawable.emoji_0039;
                            break;
                        case 0x0030:
                            icon = R.drawable.emoji_0030;
                            break;
                        case 0x0023:
                            icon = R.drawable.emoji_0023;
                            break;
                        default:
                            followSkip = 0;
                            break;
                    }
                    skip += followSkip;
                } else {
                    int followSkip = Character.charCount(followUnicode);
                    switch (unicode) {
                        case 0x1f1ef:
                            icon = (followUnicode == 0x1f1f5) ? R.drawable.emoji_1f1ef_1f1f5 : 0;
                            break;
                        case 0x1f1fa:
                            icon = (followUnicode == 0x1f1f8) ? R.drawable.emoji_1f1fa_1f1f8 : 0;
                            break;
                        case 0x1f1eb:
                            icon = (followUnicode == 0x1f1f7) ? R.drawable.emoji_1f1eb_1f1f7 : 0;
                            break;
                        case 0x1f1e9:
                            icon = (followUnicode == 0x1f1ea) ? R.drawable.emoji_1f1e9_1f1ea : 0;
                            break;
                        case 0x1f1ee:
                            icon = (followUnicode == 0x1f1f9) ? R.drawable.emoji_1f1ee_1f1f9 : 0;
                            break;
                        case 0x1f1ec:
                            icon = (followUnicode == 0x1f1e7) ? R.drawable.emoji_1f1ec_1f1e7 : 0;
                            break;
                        case 0x1f1ea:
                            icon = (followUnicode == 0x1f1f8) ? R.drawable.emoji_1f1ea_1f1f8 : 0;
                            break;
                        case 0x1f1f7:
                            icon = (followUnicode == 0x1f1fa) ? R.drawable.emoji_1f1f7_1f1fa : 0;
                            break;
                        case 0x1f1e8:
                            icon = (followUnicode == 0x1f1f3) ? R.drawable.emoji_1f1e8_1f1f3 : 0;
                            break;
                        case 0x1f1f0:
                            icon = (followUnicode == 0x1f1f7) ? R.drawable.emoji_1f1f0_1f1f7 : 0;
                            break;
                        default:
                            followSkip = 0;
                            break;
                    }
                    skip += followSkip;
                }
            }
        }

        boolean isQQFace = false;
        if (icon == 0) {
            if (c == '[') {
                int emojiCloseIndex = text.indexOf(']', start);
                if (emojiCloseIndex > 0 && emojiCloseIndex - start <= 4) {
                    CharSequence charSequence = text.subSequence(start, emojiCloseIndex + 1);
                    Integer value = sQQFaceMap.get(charSequence.toString());

                    if (value != null) {
                        icon = value;
                        skip = emojiCloseIndex + 1 - start;
                        isQQFace = true;
                    }
                }
            }
        }

        result[0] = icon;
        result[1] = skip;
        result[2] = isQQFace ? 1 : 0;

        return icon > 0;
    }

    public static String findQQFaceFileName(String key) {
        return mQQFaceFileNameList.get(key);
    }

    private static int calculateLegalTextLength(SpannableStringBuilder text, int index, int length) {
        int textLength = text.length();
        int textLengthToProcessMax = textLength - index;
        return (length < 0 || length >= textLengthToProcessMax ? textLength : (length + index));
    }

    public static List<QQFace> getQQFaceKeyList() {
        return mQQFaceList;
    }

    public static boolean isQQFaceCodeExist(String qqFaceCode) {
        return sQQFaceMap.get(qqFaceCode) != null;
    }

    public static class QQFace {
        private String name;
        private int res;

        public QQFace(String name, int res) {
            this.name = name;
            this.res = res;
        }

        public String getName() {
            return name;
        }

        public int getRes() {
            return res;
        }
    }
}
