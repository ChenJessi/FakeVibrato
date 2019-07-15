package com.chen.fakevibrato

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableString
import android.text.method.LinkMovementMethod
import android.view.View
import android.widget.Toast
import androidx.core.view.ViewCompat
import com.chen.fakevibrato.utils.MyLog
import com.chen.fakevibrato.widget.CommentSpan
import com.chen.fakevibrato.widget.FaceManager
import com.qmuiteam.qmui.qqface.QMUIQQFaceCompiler
import com.qmuiteam.qmui.qqface.QMUIQQFaceView
import com.qmuiteam.qmui.util.QMUIResHelper
import kotlinx.android.synthetic.main.activity_main3.*
import kotlinx.android.synthetic.main.activity_main3.view.*
import java.security.AccessController.getContext

class Main3Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
//        val s = "#测试@朋友[微笑]hai #话题@a#s[微笑][微笑][微笑][撇嘴][撇嘴] #这是一个话题[难过] @艾特#测试@朋友 #话题@a#s #这是一个话题[难过] @艾特#测试@朋[微笑] #话题@a#s #这是一个话题 @艾特#测试@朋友 #话题@a#s #这是[微笑]话题 @艾特#测试@朋友 #话题@a#s #这是一个话题 @艾特#测试@朋友 #话题@a#s #这是一个话题 @艾特"
        val s = "#测试@朋友hai @朋友[呲牙] @朋友 @朋友 @朋友 @朋友 @朋友 @朋友 #话题@a#s #这是一个话题 @艾特#测试@朋友 #话题@a#s #这是一个话题 @艾特#测试@朋 #话题@a#s #这是一个话题 @艾特#测试@朋友 #话题@a#s #这是[微笑]话题 @艾特#测试@朋友 #话题@a#s #这是一个话题 @艾特#测试@朋友 #话题@a#s #这是一个话题 @艾特"
        val sp = CommentSpan()
        sp.setOnSpanClick(object : CommentSpan.OnSpanClick {
            override fun topicClick(topic: String) {
                MyLog.d("topic : "+topic)
                Toast.makeText(this@Main3Activity, "topic : "+topic, Toast.LENGTH_SHORT).show()
            }

            override fun atClick(at: String) {
                MyLog.d("at : "+at)
                Toast.makeText(this@Main3Activity, "at :$at", Toast.LENGTH_SHORT).show()
            }

            override fun urlClick(url: String) {
                MyLog.d("url : "+url)
            }
        })

        var span = sp.setSpan(s)

//        textView.setText(span);

//        textView1.setText(span);
//        textView1.setMovementMethod(LinkMovementMethod.getInstance())
//        ViewCompat.setBackground(textView2, QMUIResHelper.getAttrDrawable(
//                this@Main3Activity, R.attr.qmui_s_list_item_bg_with_border_bottom));
//        textView2.setPadding(padding, padding, padding, padding);
//        textView2.setMovementMethodDefault()
//        textView2.setText(span)
//        textView.setOnClickListener {
//            MyLog.d(a.toString() + " 点击了");
//        }
    }
}

