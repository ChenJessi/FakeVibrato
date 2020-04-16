package com.chen.fakevibrato.base

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.LayoutInflaterCompat
import androidx.core.view.LayoutInflaterFactory
import androidx.core.view.ViewCompat
import com.chen.baselibrary.base.BaseActivity
import com.chen.baselibrary.base.BasePresenter
import com.chen.baselibrary.base.DBasePresenter
import com.chen.baselibrary.base.BaseView
import com.chen.fakevibrato.skin.SkinManager
import com.chen.fakevibrato.skin.attr.SkinView
import com.chen.fakevibrato.skin.support.SkinAppCompatViewInflater
import com.chen.fakevibrato.skin.support.SkinAttrSupport
import com.chen.fakevibrato.utils.Constants
import com.qmuiteam.qmui.util.QMUIDisplayHelper
import com.qmuiteam.qmui.util.QMUIStatusBarHelper
import java.util.*

abstract class BaseSupportActivity<P : BasePresenter<BaseView>>  : BaseActivity<P>(), BaseView, LayoutInflaterFactory {
    private var mAppCompatViewInflater: SkinAppCompatViewInflater? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        val layoutInflater = LayoutInflater.from(this)
        LayoutInflaterCompat.setFactory(layoutInflater, this)
        super.onCreate(savedInstanceState)
        QMUIStatusBarHelper.translucent(this)

        /**
         * APP灰度
         * 两种方案
         */
        //        Paint mPaint = new Paint();
        //        ColorMatrix cm = new ColorMatrix();
        //        cm.setSaturation(0);
        //        mPaint.setColorFilter(new ColorMatrixColorFilter(cm));
        //        getWindow().getDecorView().setLayerType(View.LAYER_TYPE_HARDWARE, mPaint);

    }

    protected fun initToolbar(toolbar: Toolbar) {
        val lp = ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        lp.height = QMUIDisplayHelper.getActionBarHeight(this) + QMUIDisplayHelper.getStatusBarHeight(this)
        toolbar.layoutParams = lp
        toolbar.setPadding(0, QMUIDisplayHelper.getStatusBarHeight(this), 0, 0)
    }

    override fun onCreateView(parent: View?, name: String, context: Context, attrs: AttributeSet): View? {
        if (Constants.isGray) {
            if ("FrameLayout" == name) {
                val cont = attrs.attributeCount
                for (i in 0 until cont) {
                    val attributeName = attrs.getAttributeName(i)
                    val attributeValue = attrs.getAttributeValue(i)
                    if ("id" == attributeName) {
                        val id = Integer.parseInt(attributeValue.substring(1))
                        val idVal = resources.getResourceName(id)
                        if ("android:id/content" == idVal) {
                            return GrayFrameLayout(context, attrs)
                        }
                    }
                }
            }
        }

        //拦截到view的创建 获取view之后解析
        //1. 创建view
        val view = createView(parent, name, context, attrs)
        // 2. 解析属性 src textcolor background

        //2.1 一个activity布局有多个 skinView
        if (view != null) {
            val skinAttrs = SkinAttrSupport.getSkinAttrs(context, attrs)
            val skinView = SkinView(view, skinAttrs)
            // 3.统一交给SkinManager管理
            managerSkinView(skinView)
        }
        //        return super.onCreateView(parent, name, context, attrs);
        return view
    }

    //    @Override
    //    public void onPointerCaptureChanged(boolean hasCapture) {
    //
    //    }

    /**
     * 统一管理skinView
     *
     * @param skinView
     */
    private fun managerSkinView(skinView: SkinView) {
        var skinViews: MutableList<SkinView>? = SkinManager.instance.getSkinViews(this)
        if (skinViews == null) {
            skinViews = ArrayList()
            SkinManager.instance.register(this, skinViews)
        }
        skinViews.add(skinView)
    }

    fun createView(parent: View?, name: String, context: Context,
                   attrs: AttributeSet): View? {
        val isPre21 = Build.VERSION.SDK_INT < 21

        if (mAppCompatViewInflater == null) {
            mAppCompatViewInflater = SkinAppCompatViewInflater()
            //            mAppCompatViewInflater = new com.chen.fakevibrato.skin.SkinAppCompatViewInflater();
        }

        // We only want the View to inherit it's context if we're running pre-v21
        val inheritContext = (isPre21 && shouldInheritContext(parent as ViewParent?))

        return mAppCompatViewInflater!!.createView(parent, name, context, attrs, inheritContext,
                isPre21, /* Only read android:theme pre-L (L+ handles this anyway) */
                true /* Read read app:theme as a fallback at all times for legacy reasons */
        )
    }

    private fun shouldInheritContext(parent: ViewParent?): Boolean {
        var parent: ViewParent? = parent
                ?: // The initial parent is null so just return false
                return false
        while (true) {
            if (parent == null) {
                // Bingo. We've hit a view which has a null parent before being terminated from
                // the loop. This is (most probably) because it's the root view in an inflation
                // call, therefore we should inherit. This works as the inflated layout is only
                // added to the hierarchy at the end of the inflate() call.
                return true
            } else if (parent === window.decorView || parent !is View
                    || ViewCompat.isAttachedToWindow((parent as View?)!!)) {
                // We have either hit the window's decor view, a parent which isn't a View
                // (i.e. ViewRootImpl), or an attached view, so we know that the original parent
                // is currently added to the view hierarchy. This means that it has not be
                // inflated in the current inflate() call and we should not inherit the context.
                return false
            }
            parent = parent.getParent()
        }
    }
}