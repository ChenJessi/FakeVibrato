package com.chen.fakevibrato.widget

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import android.view.animation.DecelerateInterpolator
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.IntDef
import androidx.annotation.LayoutRes
import androidx.constraintlayout.widget.ConstraintLayout
import com.chen.fakevibrato.R
import com.chen.fakevibrato.utils.DisplayUtils
import com.chen.fakevibrato.utils.ToastUtils
import com.chen.fakevibrato.widget.anim.AnimtorUtils
import org.greenrobot.eventbus.EventBus

import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy

/**
 * 长按弹窗
 */
class VideoLongDialog  constructor(context: Context, themeResId: Int = R.style.QMUI_TipDialog) : Dialog(context, themeResId) {
     var contentWrap : ViewGroup? = null

    init {
        setCanceledOnTouchOutside(false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDialogWidth()
        contentWrap = findViewById<View>(R.id.contentWrap) as ViewGroup
    }

    private fun initDialogWidth() {
        val window = window
        if (window != null) {
            val wmLp = window.attributes
            wmLp.width = ViewGroup.LayoutParams.MATCH_PARENT
            wmLp.height = ViewGroup.LayoutParams.MATCH_PARENT
            window.attributes = wmLp
            window.setWindowAnimations(R.style.dialog_style)
        }
    }

    /**
     * 生成默认的 [VideoLongDialog]
     *
     * @see CustomBuilder
     */
    class Builder(private val mContext: Context) {
        private lateinit var ev : MotionEvent ;
        /**
         * 创建 Dialog, 但没有弹出来, 如果要弹出来, 请调用返回值的 [Dialog.show] 方法
         *
         * @param cancelable 按系统返回键是否可以取消
         * @return 创建的 Dialog
         */
        @JvmOverloads
        fun create(cancelable: Boolean = true): VideoLongDialog {
            val dialog = VideoLongDialog(mContext)
            dialog.setCancelable(cancelable)
            dialog.setContentView(R.layout.dialog_video_long)
            val contentWrap = dialog.findViewById<View>(R.id.contentWrap) as ViewGroup
            val ivSave = dialog.findViewById<View>(R.id.ivSave) as ImageView
            val ivCollect = dialog.findViewById<View>(R.id.ivCollect) as ImageView
            val ivDislike = dialog.findViewById<View>(R.id.ivDislike) as ImageView
            val  constraintLayout = dialog.findViewById<View>(R.id.constraintLayout) as ConstraintLayout

            val  sHeight = DisplayUtils.getScreenHeight(mContext);
            var start  = 0f
            var stop  = 0f
            if (ev.y < sHeight / 4){
                stop = (sHeight / 4).toFloat()
                start = stop + 300
            }else if (ev.y < sHeight / 2){
                stop = (sHeight / 2).toFloat()
                start = stop - 300
            }else if (ev.y < sHeight / 4 * 3){
                stop = (sHeight / 2).toFloat()
                start = stop + 300
            }else{
                stop = (sHeight / 4 * 3).toFloat()
                start = stop - 300
            }
//            start -= 100f
//            stop -= 300f
            contentWrap.setOnClickListener {
//                dialog.dismiss()
                var anim  = AnimtorUtils.translationY(constraintLayout, stop, start, 300, 0, AccelerateInterpolator())
                anim.addListener(object : AnimatorListenerAdapter(){
                    override fun onAnimationEnd(animation: Animator?) {
                        dialog.dismiss()
                    }
                })
                anim.start()
            }

            dialog.setOnShowListener{
                AnimtorUtils.translationY(constraintLayout, start, stop, 300, 0, DecelerateInterpolator()).start()
            }

            ivSave.setOnClickListener {
                Toast.makeText(mContext, "保存到本地", Toast.LENGTH_SHORT).show()
            }
            ivCollect.setOnClickListener {
                Toast.makeText(mContext, "收藏", Toast.LENGTH_SHORT).show()
            }
            ivDislike.setOnClickListener {
                Toast.makeText(mContext, "不感兴趣", Toast.LENGTH_SHORT).show()
            }

            return dialog
        }

        public  fun setEvent(ev : MotionEvent): Builder {
            this.ev = ev
            return this
        }
        companion object {

        }
    }

    override fun onBackPressed() {
        contentWrap?.performClick()

    }

}
