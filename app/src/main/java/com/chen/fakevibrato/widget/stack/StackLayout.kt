package com.chen.fakevibrato.widget.stack

import android.content.Context
import android.database.DataSetObservable
import android.database.DataSetObserver
import android.graphics.Point
import android.graphics.Rect
import android.util.AttributeSet
import android.widget.TextView
import androidx.customview.widget.ViewDragHelper
import com.chen.fakevibrato.utils.MyLog
import java.lang.ref.WeakReference
import android.R.attr.top
import android.graphics.Color
import android.view.*
import androidx.core.view.GestureDetectorCompat


/**
 * 卡片堆叠
 */
class StackLayout : ViewGroup {

    var defStyleAttr: Int = 0
    var attrs: AttributeSet? = null

    // 存放的是每一层的view，从顶到底
    private val viewList = ArrayList<CardItemView>()
    //手指松开后的view存放
    private val releasedViewList = ArrayList<View>()
    private var adapter: StackAdapter? = null

    private val SCALE_STEP = 0.08f // view叠加缩放的步长
    // view叠加垂直偏移量的步长
    private var yOffsetStep = 40
    //叠加的view个数
    private val VIEW_COUNT = 4
    //拖拽类
    private lateinit var mDragHelper: ViewDragHelper
    private lateinit var moveDetector: GestureDetectorCompat
    //可拖动区域
    private var draggableArea: Rect? = null
    //手指按下的坐标
    private var downPoint = Point()
    //宽度和高度
    private var allWidth = 0
    private var allHeight = 0
    // 判定为滑动的阈值，单位是像素
    private var mTouchSlop = 5
    // 卡片距离顶部的偏移量
    private val itemMarginTop = 10
    // 底部按钮与卡片的margin值
    private val bottomMarginTop = 40
    private var savedFirstItemData: WeakReference<Any>? = null
    //当前显示
    private var isShowing = 0
    private var btnLock = false

    private var mDragHelperCallback = object : ViewDragHelper.Callback() {
        override fun tryCaptureView(child: View, pointerId: Int): Boolean {
            // 如果数据List为空，或者子View不可见，则不予处理
            if (adapter == null || adapter?.getCount() == 0
                    || child.visibility != View.VISIBLE || child.scaleX <= 1.0f - SCALE_STEP) {
                // 一般来讲，如果拖动的是第三层、或者第四层的View，则直接禁止
                // 此处用getScale的用法来巧妙回避
                return false
            }
            if (btnLock) {
                return false
            }
            // 1. 只有顶部的View才允许滑动
            var childIndex = viewList.indexOf(child)
            if (childIndex > 0) {
                return false
            }
            //2.获取可滑动区域
            (child as CardItemView).onStartDragging()
            if (draggableArea == null) {
                draggableArea = adapter?.obtainDraggableArea(child)
            }

            //判断是否可以滑动
            var shouldCapture = true
            if (draggableArea != null) {
                shouldCapture = draggableArea?.contains(downPoint.x, downPoint.y) ?: false
            }

            //4. 如果确定要滑动，就让touch事件交给自己消费
            if (shouldCapture) {
                parent.requestDisallowInterceptTouchEvent(shouldCapture)
            }

            return shouldCapture
        }

        override fun onViewReleased(releasedChild: View, xvel: Float, yvel: Float) {
            super.onViewReleased(releasedChild, xvel, yvel)
        }
        override fun onViewPositionChanged(changedView: View, left: Int, top: Int, dx: Int, dy: Int) {
            onViewPosChanged(changedView as CardItemView)
        }

        override fun getViewHorizontalDragRange(child: View): Int {
            // 这个用来控制拖拽过程中松手后，自动滑行的速度
            return 256
        }

        override fun clampViewPositionHorizontal(child: View, left: Int, dx: Int): Int {
            return left
        }

        override fun clampViewPositionVertical(child: View, top: Int, dy: Int): Int {
            return top
        }
    }

    private var mDetector = object :  GestureDetector.SimpleOnGestureListener(){
        override fun onScroll(e1: MotionEvent?, e2: MotionEvent?, distanceX: Float, distanceY: Float): Boolean {
            return Math.abs(distanceX) + Math.abs(distanceY) > mTouchSlop
        }
    }
    init {
        mDragHelper = ViewDragHelper.create(this, 10f,  mDragHelperCallback)
        moveDetector = GestureDetectorCompat(context, mDetector)
//        init()
    }
    constructor(mContext: Context?) : this(mContext, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        this.attrs = attrs
        this.defStyleAttr = defStyleAttr
    }


    fun init() {
        val configuration = ViewConfiguration.get(context)
        mTouchSlop = configuration.scaledTouchSlop
        moveDetector.setIsLongpressEnabled(false)

        viewTreeObserver.addOnGlobalLayoutListener {
            if (childCount != VIEW_COUNT) {
//                doBindAdapter()
            }
        }
    }

    fun doBindAdapter() {
//        || allWidth <= 0 || allHeight <= 0
        if (adapter == null ) {
            return
        }
        MyLog.e(" viewList ${viewList.size}")
        //添加view 到ViewGroup
        for (i in 0 until VIEW_COUNT) {
            var itemView = CardItemView(context)
            itemView.bindLayoutResId(adapter?.getLayoutId() ?: 0)
            itemView.setParentView(this)
            addView(itemView, LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT))
            if (i == 0) {
                itemView.alpha = 0f
            }
        }

        //viewList 初始化
        viewList.clear()
        for (i in 0 until VIEW_COUNT) {
            viewList.add(getChildAt(VIEW_COUNT - 1 - i) as CardItemView)
        }

        //填充数据
        var count = adapter?.getCount() ?: 0
        for (i in 0 until VIEW_COUNT) {
            if (i < count) {
                adapter?.bindView(viewList[i], i)
                if (i == 0) {
                    savedFirstItemData = WeakReference<Any>(adapter?.getItem(i))
                }
            } else {
                viewList[i].visibility = INVISIBLE
            }
        }
    }


    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        var childCount = childCount

        for (i in 0 until childCount){
            var viewItem = viewList[i]
            // layout
            var childHeight = viewItem.measuredHeight
            var viewLeft = (width - viewItem.measuredWidth) / 2
            viewItem.layout(viewLeft, itemMarginTop, viewLeft + viewItem.measuredWidth, itemMarginTop + childHeight)

            //调整位置
            var offset = yOffsetStep * i
            var scale = 1 - SCALE_STEP * i

            if (i > 2) {
                // 备用的view
                offset = yOffsetStep * 2
                scale = 1 - SCALE_STEP * 2
            }

            viewItem.offsetTopAndBottom(offset)

            //view 中心，缩放
            viewItem.pivotY = viewItem.measuredHeight.toFloat()
            viewItem.pivotX = (viewItem.measuredWidth / 2).toFloat()
            viewItem.scaleX = scale
            viewItem.scaleY = scale
        }

    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        measureChildren(widthMeasureSpec, heightMeasureSpec)
        val maxWidth = MeasureSpec.getSize(widthMeasureSpec)
        val maxHeight = MeasureSpec.getSize(heightMeasureSpec)
        setMeasuredDimension(
                resolveSizeAndState(maxWidth, widthMeasureSpec, 0),
                resolveSizeAndState(maxHeight, heightMeasureSpec, 0))
        allWidth = measuredWidth
        allHeight = measuredHeight
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        var shouldIntercept = ev?.let { mDragHelper.shouldInterceptTouchEvent(it) } ?: false
        var moveFlag = moveDetector.onTouchEvent(ev)
        var action = ev?.actionMasked
        if (action == MotionEvent.ACTION_DOWN){
            //ACTION_DOWN 对view 重新排列
            if (mDragHelper.viewDragState == ViewDragHelper.STATE_SETTLING){
                mDragHelper.abort()
            }
            orderViewStack()

            // 保存初次按下时arrowFlagView的Y坐标
            // action_down时就让mDragHelper开始工作，否则有时候导致异常
            ev?.let { mDragHelper.processTouchEvent(it) }
        }
        return shouldIntercept && moveFlag
    }
    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        // 按下时保存坐标信息
        var action = ev?.actionMasked
        if (action == MotionEvent.ACTION_DOWN){
            downPoint.x = ev?.x?.toInt() ?: 0
            downPoint.y = ev?.y?.toInt() ?: 0
        }
        return super.dispatchTouchEvent(ev)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        //统一交给dragHelper处理滑动手势
        try {
            event?.let { mDragHelper.processTouchEvent(it) }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return true
    }



    fun onViewPosChanged(cardItemView: CardItemView) {
        var index = viewList.indexOf(cardItemView)
        if (index + 2 > viewList.size) {
            return
        }
        processLinkageView(cardItemView)
    }

    /**
     * 顶层view改变，底层需要调整
     */
    fun processLinkageView(cardItemView: CardItemView) {

    }

    /**
     * 对view进行重新排序
     */
    fun orderViewStack() {
        if (releasedViewList.size == 0) {
            return
        }
    }

    fun setAdapter(adapter: StackAdapter) {
        this.adapter = adapter
        doBindAdapter()
//        adapter.registerDataSetObserver(object : DataSetObserver() {
//            override fun onChanged() {
//                orderViewStack()
//
//                var reset = false
//                if (adapter.getCount() > 0) {
//                    var firstObj = adapter.getItem(0)
//                    if (savedFirstItemData == null) {
//                        savedFirstItemData = WeakReference(adapter.getItem(0))
//                        isShowing = 0
//                    } else {
//                        var saveObj = savedFirstItemData?.get()
//                        if (firstObj != saveObj) {
//                            //第一条数据不等。重置数据
//                            isShowing = 0
//                            reset = true
//                            savedFirstItemData = WeakReference<Any>(saveObj)
//                        }
//                    }
//                }
//
//                var delay = 0
//                for (i in 0 until VIEW_COUNT) {
//                    var itemView = viewList[i]
//                    if (isShowing + 1 < adapter.getCount()) {
//                        if (itemView.visibility == View.VISIBLE) {
//                            if (!reset) continue
//                        } else if (i == 0) {
//                            if (isShowing > 0) {
//                                isShowing++
//                            }
//                        }
//
//                        if (i == VIEW_COUNT - 1) {
//                            itemView.alpha = 0f
//                            itemView.visibility = View.VISIBLE
//                        }else{
//                            itemView.setVisibilityWithAnimation(View.VISIBLE, delay++)
//                        }
//                        adapter.bindView(itemView, isShowing + i)
//                    }else{
//                        itemView.visibility = INVISIBLE
//                    }
//                }
//            }
//        })
    }


    abstract class StackAdapter {
        private val mDataSetObservable = DataSetObservable()

        abstract fun getLayoutId(): Int
        /**
         * item 数量
         */
        abstract fun getCount(): Int

        abstract fun bindView(view: View, index: Int)

        abstract fun getItem(index: Int): Any
        /**
         * 可拖动区域
         */
        fun obtainDraggableArea(view: View): Rect? {
            return null
        }

        fun registerDataSetObserver(observer: DataSetObserver) {
            mDataSetObservable.registerObserver(observer)
        }

        fun unregisterDataSetObserver(observer: DataSetObserver) {
            mDataSetObservable.unregisterObserver(observer)
        }

        fun notifyDataSetChanged() {
            mDataSetObservable.notifyChanged()
        }
    }
}