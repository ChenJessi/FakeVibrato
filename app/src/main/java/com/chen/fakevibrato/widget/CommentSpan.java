package com.chen.fakevibrato.widget;

import android.graphics.Color;
import android.graphics.Typeface;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.chen.fakevibrato.utils.MyLog;
import com.qmuiteam.qmui.span.QMUITouchableSpan;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Created by CHEN on 2019/6/18
 * @email 188669@163.com
 */
public class CommentSpan {

    private static final String regex_http = "http(s)?://([a-zA-Z|\\d]+\\.)+[a-zA-Z|\\d]+(/[a-zA-Z|\\d|\\-|\\+|_./?%=]*)?";
    private static final String regex_at = "@[\\[\\]0-9a-zA-Z\\u4e00-\\u9fa5_-]{1,30}\\s";
    private static final String regex_topic="#[\\[\\]0-9a-zA-Z\\u4e00-\\u9fa5]{2,15}\\s";

    private List<String> topicList = new ArrayList<String>();
    private List<String> atList = new ArrayList<String>();
    private List<String> httpList = new ArrayList<String>();
    private String source;
    private OnSpanClick onSpanClick;
    public CommentSpan() { }

    public void setOnSpanClick(OnSpanClick onSpanClick) {
        this.onSpanClick = onSpanClick;
    }

    private void getTopic(String s){
        Pattern p = Pattern.compile(regex_topic);
        Matcher m = p.matcher(s);
        while(m.find()) {
            topicList.add(m.group());
            Log.e("tag","group getTopic ： "+topicList.toString());
        }

    }
    private void getAt(String s){
        Pattern p = Pattern.compile(regex_at);
        Matcher m = p.matcher(s);
        while(m.find()) {
            atList.add(m.group());
            Log.e("tag","group getAt ： "+atList.toString());
        }
    }


    public SpannableString setSpan(CharSequence s){
        this.source = s.toString();
        getAt(source.toString());
        getTopic(source.toString());
        int fromIndex = 0;
        SpannableString sp = new SpannableString(source);

        fromIndex = 0;

        for (int i = 0; i < atList.size(); i++) {
            String atStr = atList.get(i);
            sp.setSpan(new TouchableSpan(Color.WHITE, Color.WHITE, Color.TRANSPARENT, Color.TRANSPARENT) {
                @Override
                public void onSpanClick(View widget) {
                    if (onSpanClick != null){
                        onSpanClick.atClick(atStr);
                    }
                }
            }, source.indexOf(atStr,fromIndex), source.indexOf(atStr,fromIndex) + atStr.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            sp.setSpan(new StyleSpan(Typeface.BOLD),source.indexOf(atStr,fromIndex), source.indexOf(atStr,fromIndex) + atStr.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            fromIndex = source.indexOf(atStr,fromIndex) + atStr.length();
        }
        fromIndex = 0;

        for (int i = 0; i < topicList.size(); i++) {
            MyLog.d("topicList : "+i);
            String topicStr = topicList.get(i);
            sp.setSpan(new TouchableSpan(Color.WHITE, Color.WHITE, Color.TRANSPARENT, Color.TRANSPARENT) {
                @Override
                public void onSpanClick(View widget) {
                    if (onSpanClick != null){
                        onSpanClick.topicClick(topicStr);
                    }
                }
            }, source.indexOf(topicStr,fromIndex), source.indexOf(topicStr,fromIndex) + topicStr.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            sp.setSpan(new StyleSpan(Typeface.BOLD),source.indexOf(topicStr,fromIndex), source.indexOf(topicStr,fromIndex) + topicStr.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            fromIndex = source.indexOf(topicStr,fromIndex) + topicStr.length();
        }
        fromIndex = 0;
        for (int i = 0; i < httpList.size(); i++) {
            String httpStr = httpList.get(i);
            sp.setSpan(new TouchableSpan(Color.WHITE, Color.WHITE, Color.TRANSPARENT, Color.TRANSPARENT) {
                @Override
                public void onSpanClick(View widget) {
                    Toast.makeText(widget.getContext(), "点击了url", Toast.LENGTH_SHORT).show();
                }
            }, source.indexOf(httpStr,fromIndex), source.indexOf(httpStr,fromIndex) + httpStr.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            sp.setSpan(new StyleSpan(Typeface.BOLD),source.indexOf(httpStr,fromIndex), source.indexOf(httpStr,fromIndex) + httpStr.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            fromIndex = source.indexOf(httpStr,fromIndex) + httpStr.length();
        }

        return sp;
    }

    public  interface OnSpanClick{
        void topicClick(String topic);
        void atClick(String at);
        void urlClick(String url);
    }
}
