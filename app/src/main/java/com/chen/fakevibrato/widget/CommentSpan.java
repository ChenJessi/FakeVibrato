package com.chen.fakevibrato.widget;

import android.text.NoCopySpan;
import android.text.SpannableString;
import android.text.Spanned;
import android.util.Log;
import android.view.View;

import com.chen.fakevibrato.ui.home.CommentBean;
import com.qmuiteam.qmui.span.QMUITouchableSpan;
import com.qmuiteam.qmui.widget.dialog.QMUIBottomSheet;
import com.qmuiteam.qmui.widget.textview.QMUILinkTextView;

import org.w3c.dom.Comment;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Created by CHEN on 2019/6/18
 * @email 188669@163.com
 */
public class CommentSpan extends SpannableString   {

    private static final String regex_http = "http(s)?://([a-zA-Z|\\d]+\\.)+[a-zA-Z|\\d]+(/[a-zA-Z|\\d|\\-|\\+|_./?%=]*)?";
    private static final String regex_at = "^@[0-9a-zA-Z\\u4e00-\\u9fa5_-]{4,30}\\s";
    private static final String regex_topic="#[^#@]{1,15}\\s";

    private List<String> topicList = new ArrayList<String>();
    private List<String> atList = new ArrayList<String>();
    private List<String> httpList = new ArrayList<String>();
    private String source;
    public CommentSpan(CharSequence source) {
        super(source);
        this.source = source.toString();
        getAt(source.toString());
        getTopic(source.toString());
        setSpan();
    }

    private void getTopic(String s){
        Pattern p = Pattern.compile(regex_topic);
        Matcher m = p.matcher(s);
        while(m.find()) {
            topicList.add(m.group());
            Log.e("tag","group ： "+topicList.toString());
        }

    }
    private void getAt(String s){
        Pattern p = Pattern.compile(regex_at);
        Matcher m = p.matcher(s);
        while(m.find()) {
            atList.add(m.group());
            Log.e("tag","group ： "+atList.toString());
        }

    }

    private void setSpan(){
        for (int i = 0; i < atList.size(); i++) {
            String atStr = atList.get(i);
            setSpan(atStr, source.indexOf(atStr), source.indexOf(atStr) + atStr.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        }
        for (int i = 0; i < topicList.size(); i++) {
            String topicStr = topicList.get(i);
            setSpan(topicList, source.indexOf(topicStr), topicList.indexOf(topicStr) + topicStr.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        }
        for (int i = 0; i < httpList.size(); i++) {
            String httpStr = httpList.get(i);
            setSpan(httpList, source.indexOf(httpStr), httpList.indexOf(httpStr) + httpStr.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        }
    }
    @Override
    public void setSpan(Object what, int start, int end, int flags) {
        super.setSpan(what, start, end, flags);
    }


    public  interface onClick{
        void topic();
        void at();
        void url();
    }
}
