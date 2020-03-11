package com.chen.fakevibrato.widget.stack

import android.content.Context
import android.database.DataSetObservable
import android.database.DataSetObserver
import android.graphics.Point
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import androidx.customview.widget.ViewDragHelper
import com.chen.baselibrary.BaseApplication.Companion.context

/**
 * 卡片堆叠
 */
class StackLayout (var mContext: Context?) : ViewGroup(mContext){

    var defStyleAttr: Int = 0
    var attrs: AttributeSet? = null

    private val viewList = ArrayList<CardItemView>() // 存放的是每一层的view，从顶到底

    private var adapter : StackAdapter? = null

    private val SCALE_STEP = 0.08f // view叠加缩放的步长
    //拖拽类
    private lateinit var mDragHelper : ViewDragHelper
    //可拖动区域
    private var draggableArea : Rect? = null
    //手指按下的坐标
    private var downPoint = Point()

    private var btnLock = false

    private var mDragHelperCallback = object : ViewDragHelper.Callback(){
        override fun tryCaptureView(child: View, pointerId: Int): Boolean {
            // 如果数据List为空，或者子View不可见，则不予处理
            if (adapter == null || adapter?.getCount() == 0
                    || child.visibility != View.VISIBLE || child.scaleX <= 1.0f - SCALE_STEP){
                // 一般来讲，如果拖动的是第三层、或者第四层的View，则直接禁止
                // 此处用getScale的用法来巧妙回避
                return false
            }
            if (btnLock){
                return false
            }
            // 1. 只有顶部的View才允许滑动
            var childIndex = viewList.indexOf(child)
            if (childIndex > 0){
                return false
            }
            //2.获取可滑动区域
            (child as CardItemView).onStartDragging()
            if (draggableArea == null){
                draggableArea = adapter?.obtainDraggableArea(child)
            }

            //判断是否可以滑动
            var shouldCapture = true
            if (draggableArea != null){
                shouldCapture = draggableArea?.contains(downPoint.x, downPoint.y) ?: false
            }

            //4. 如果确定要滑动，就让touch事件交给自己消费
            if (shouldCapture){
                parent.requestDisallowInterceptTouchEvent(shouldCapture)
            }

            return shouldCapture
        }

        override fun onViewPositionChanged(changedView: View, left: Int, top: Int, dx: Int, dy: Int) {
            onViewPosChanged(changedView as CardItemView)
        }
    }


    init {

        mDragHelper = ViewDragHelper.create(this, 10f,  mDragHelperCallback)
    }
    constructor(context: Context?, attrs: AttributeSet?) : this(context){
        this.attrs = attrs
    }
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : this(context, attrs){
        this.defStyleAttr = defStyleAttr
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {

    }


    fun onViewPosChanged(cardItemView: CardItemView){
        var index = viewList.indexOf(cardItemView)
        if (index + 2 > viewList.size){
            return
        }
        processLinkageView(cardItemView)
    }

    /**
     * 顶层view改变，底层需要调整
     */
    fun processLinkageView(cardItemView: CardItemView){

    }


    abstract class  StackAdapter {
        private val mDataSetObservable = DataSetObservable()

        abstract fun getLayoutId() : Int
        /**
         * item 数量
         */
        abstract fun getCount() : Int

        abstract fun bindView(view : View, index : Int)

        abstract fun getItem(index : Int) : Any
        /**
         * 可拖动区域
         */
        fun obtainDraggableArea(view : View) : Rect?{
            return  null
        }

        fun registerDataSetObserver(observer : DataSetObserver){
            mDataSetObservable.registerObserver(observer)
        }

        fun unregisterDataSetObserver(observer : DataSetObserver){
            mDataSetObservable.unregisterObserver(observer)
        }

        fun notifyDataSetChanged(){
            mDataSetObservable.notifyChanged()
        }
    }
}