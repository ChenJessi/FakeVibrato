package com.chen.fakevibrato.widget;

import android.text.NoCopySpan;
import android.text.SpannableString;

import com.chen.fakevibrato.ui.home.CommentBean;
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
    String patt = "GraphType\\\\s*=\\\\s*\\\".+\\\"\\\\s*";
//    Pattern Pattern.matches

    public CommentSpan(CharSequence source) {
        super(source);
//        Pattern.compile()
    }

    private void get(){
        String s = "https://wenku.baidu.com/view/e109601f52d380eb62946d75.html?rec_flag=default&mark_pay_doc=2&mark_rec_page=1&mark_rec_position=4&mark_rec=view_r_1&clear_uda_param=1\n" ;
        List<String> strs = new ArrayList<String>();
        Pattern p = Pattern.compile("GraphType\\s*=\\s*\".+\"\\s*");
        Matcher m = p.matcher(s);
//        new QMUIBottomSheet.BottomListSheetBuilder().
        while(m.find()) {
            strs.add(m.group());
        }
    }
}
