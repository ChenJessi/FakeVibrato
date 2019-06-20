package com.chen.fakevibrato.widget;

import android.text.NoCopySpan;
import android.text.SpannableString;
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
public class CommentSpan extends SpannableString {

    private static final String regex_http = "http(s)?://([a-zA-Z|\\d]+\\.)+[a-zA-Z|\\d]+(/[a-zA-Z|\\d|\\-|\\+|_./?%=]*)?";
//    private static final String regex_at = "@[\\u4e00-\\u9fa5\\w\\-]+";
    private static final String regex_at = "^[0-9a-zA-Z\\u4e00-\\u9fa5_-]\\s${4,10}";
//    private static final String regex_at = "@[^.]{4,30} ";
    private static final String regex_topic="#[^#@]{1,15}\\s";

    private List<String> topicList = new ArrayList<String>();
    private List<String> atList = new ArrayList<String>();
    private List<String> httpList = new ArrayList<String>();

    public CommentSpan(CharSequence source) {
        super(source);
    }

    private List<String> getTopic(String s){
        List<String> strs = new ArrayList<String>();
        Pattern p = Pattern.compile(regex_topic);
        Matcher m = p.matcher(s);
        while(m.find()) {
            strs.add(m.group());
        }

        return strs;
    }
    private List<String> getAt(String s){
        List<String> strs = new ArrayList<String>();
        Pattern p = Pattern.compile(regex_at);
        Matcher m = p.matcher(s);
        while(m.find()) {
            strs.add(m.group());
        }

        return strs;
    }

    @Override
    public void setSpan(Object what, int start, int end, int flags) {
        super.setSpan(what, start, end, flags);
    }

    public interface onClick{
        void topic();
        void at();
        void url();
    }
}
