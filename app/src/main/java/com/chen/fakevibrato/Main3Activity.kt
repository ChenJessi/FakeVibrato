package com.chen.fakevibrato

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.chen.fakevibrato.utils.MyLog
import com.chen.fakevibrato.widget.CommentSpan
import kotlinx.android.synthetic.main.activity_main3.*

class Main3Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
        val s = "#测试@朋友 #话题@a#s #这是一个话题 @艾特"
        val sp = CommentSpan()
        val a = s.indexOf("#这是一个话题 ")
        MyLog.d(a.toString() + " ");
        textView.setText(sp.setSpan(s))
        textView.setOnClickListener {
            MyLog.d(a.toString() + " 点击了");
        }
    }
}

