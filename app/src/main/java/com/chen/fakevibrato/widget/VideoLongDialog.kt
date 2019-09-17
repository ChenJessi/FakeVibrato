package com.chen.fakevibrato.widget

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.text.TextUtils
import android.util.TypedValue
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.IntDef
import androidx.annotation.LayoutRes
import androidx.core.content.ContextCompat
import com.qmuiteam.qmui.R
import com.qmuiteam.qmui.util.QMUIDisplayHelper
import com.qmuiteam.qmui.widget.QMUILoadingView
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy

/**
 * 长按弹窗
 */
class VideoLongDialog @JvmOverloads constructor(context: Context, themeResId: Int = R.style.QMUI_TipDialog) : Dialog(context, themeResId) {

    init {
        setCanceledOnTouchOutside(false)
    }

    override fun onCreate(savedInstanceState: Bundle) {
        super.onCreate(savedInstanceState)
        initDialogWidth()
    }

    private fun initDialogWidth() {
        val window = window
        if (window != null) {
            val wmLp = window.attributes
            wmLp.width = ViewGroup.LayoutParams.MATCH_PARENT
            window.attributes = wmLp
        }
    }

    /**
     * 生成默认的 [QMUITipDialog]
     *
     *
     * 提供了一个图标和一行文字的样式, 其中图标有几种类型可选。见 [IconType]
     *
     *
     * @see CustomBuilder
     */
    class Builder(private val mContext: Context) {

        @IconType
        private var mCurrentIconType = ICON_TYPE_NOTHING

        private var mTipWord: CharSequence? = null

        @IntDef(ICON_TYPE_NOTHING, ICON_TYPE_LOADING, ICON_TYPE_SUCCESS, ICON_TYPE_FAIL, ICON_TYPE_INFO)
        @Retention(RetentionPolicy.SOURCE)
        annotation class IconType

        /**
         * 设置 icon 显示的内容
         *
         * @see IconType
         */
        fun setIconType(@IconType iconType: Int): Builder {
            mCurrentIconType = iconType
            return this
        }

        /**
         * 设置显示的文案
         */
        fun setTipWord(tipWord: CharSequence): Builder {
            mTipWord = tipWord
            return this
        }

        /**
         * 创建 Dialog, 但没有弹出来, 如果要弹出来, 请调用返回值的 [Dialog.show] 方法
         *
         * @param cancelable 按系统返回键是否可以取消
         * @return 创建的 Dialog
         */
        @JvmOverloads
        fun create(cancelable: Boolean = true): QMUITipDialog {
            val dialog = QMUITipDialog(mContext)
            dialog.setCancelable(cancelable)
            dialog.setContentView(R.layout.qmui_tip_dialog_layout)
            val contentWrap = dialog.findViewById<View>(R.id.contentWrap) as ViewGroup

            if (mCurrentIconType == ICON_TYPE_LOADING) {
                val loadingView = QMUILoadingView(mContext)
                loadingView.setColor(Color.WHITE)
                loadingView.setSize(QMUIDisplayHelper.dp2px(mContext, 32))
                val loadingViewLP = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                loadingView.layoutParams = loadingViewLP
                contentWrap.addView(loadingView)

            } else if (mCurrentIconType == ICON_TYPE_SUCCESS || mCurrentIconType == ICON_TYPE_FAIL || mCurrentIconType == ICON_TYPE_INFO) {
                val imageView = ImageView(mContext)
                val imageViewLP = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                imageView.layoutParams = imageViewLP

                if (mCurrentIconType == ICON_TYPE_SUCCESS) {
                    imageView.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.qmui_icon_notify_done))
                } else if (mCurrentIconType == ICON_TYPE_FAIL) {
                    imageView.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.qmui_icon_notify_error))
                } else {
                    imageView.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.qmui_icon_notify_info))
                }

                contentWrap.addView(imageView)

            }

            if (mTipWord != null && mTipWord!!.length > 0) {
                val tipView = TextView(mContext)
                val tipViewLP = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)

                if (mCurrentIconType != ICON_TYPE_NOTHING) {
                    tipViewLP.topMargin = QMUIDisplayHelper.dp2px(mContext, 12)
                }
                tipView.layoutParams = tipViewLP

                tipView.ellipsize = TextUtils.TruncateAt.END
                tipView.gravity = Gravity.CENTER
                tipView.maxLines = 2
                tipView.setTextColor(ContextCompat.getColor(mContext, R.color.qmui_config_color_white))
                tipView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14f)
                tipView.text = mTipWord

                contentWrap.addView(tipView)
            }
            return dialog
        }

        companion object {
            /**
             * 不显示任何icon
             */
            const val ICON_TYPE_NOTHING = 0
            /**
             * 显示 Loading 图标
             */
            const val ICON_TYPE_LOADING = 1
            /**
             * 显示成功图标
             */
            const val ICON_TYPE_SUCCESS = 2
            /**
             * 显示失败图标
             */
            const val ICON_TYPE_FAIL = 3
            /**
             * 显示信息图标
             */
            const val ICON_TYPE_INFO = 4
        }

    }

    /**
     * 传入自定义的布局并使用这个布局生成 TipDialog
     */
    class CustomBuilder(private val mContext: Context) {
        private var mContentLayoutId: Int = 0

        fun setContent(@LayoutRes layoutId: Int): CustomBuilder {
            mContentLayoutId = layoutId
            return this
        }

        /**
         * 创建 Dialog, 但没有弹出来, 如果要弹出来, 请调用返回值的 [Dialog.show] 方法
         *
         * @return 创建的 Dialog
         */
        fun create(): QMUITipDialog {
            val dialog = QMUITipDialog(mContext)
            dialog.setContentView(R.layout.qmui_tip_dialog_layout)
            val contentWrap = dialog.findViewById<View>(R.id.contentWrap) as ViewGroup
            LayoutInflater.from(mContext).inflate(mContentLayoutId, contentWrap, true)
            return dialog
        }
    }
}
