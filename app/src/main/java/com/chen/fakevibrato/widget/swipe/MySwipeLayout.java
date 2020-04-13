package com.chen.fakevibrato.widget.swipe;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Scroller;

import androidx.annotation.ColorInt;
import androidx.annotation.FloatRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.Size;
import androidx.core.view.GestureDetectorCompat;
import androidx.core.view.ViewCompat;
import androidx.customview.widget.ViewDragHelper;
import androidx.viewpager.widget.ViewPager;

import com.chen.fakevibrato.utils.ColorUtils;
import com.chen.fakevibrato.utils.DisplayUtils;
import com.chen.fakevibrato.utils.EvaluateUtils;
import com.chen.fakevibrato.utils.MyLog;
import com.chen.fakevibrato.widget.SwipeLayout;
import com.chen.fakevibrato.widget.anim.AnimtorUtils;

import java.lang.annotation.RetentionPolicy;


/**
 * 滑动布局
 * 多个滑动布局嵌套时的手势处理未完全优化
 */
public class MySwipeLayout extends FrameLayout {
    private ViewDragHelper mDragHelper;
    //侧面板
    private ViewGroup mLeftContent;
    //主面板
    private ViewGroup mMainContent;
    //右侧面板
    private ViewGroup mRightContent;
    private OnDragStatusChangeListener mListener;
    private Status mStatus = Status.Close;
    //打开抽屉开关的难易程度，越小 越难打开，1.0f为normal
    private float mSensitivity = 0.8f;
    private int mHeight;
    private int mWidth;
    private int leftWidth;
    private int leftHeight;
    private int rightWidth;
    private int rightHeight;
    private int mainLeft ;
    private boolean isSwipe = true;

    private boolean first = true;
    private  int mRange ;
    private float scale = 0.8f;

    private DragEdge mDragEdge = DragEdge.Right;

    private float sX = -1, sY = -1;
    private int mTouchSlop;

    public MySwipeLayout(Context context) {
        this(context, null);
    }

    public MySwipeLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MySwipeLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mDragHelper = ViewDragHelper.create(this, mSensitivity, mDragHelperCallback);
        mTouchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
        MyLog.e("mDragEdge   :  "+mDragEdge);
    }

    public enum DragEdge {
        Left,
        Right
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (!isSwipe()){
            return false;
        }
        if (event.getAction() == MotionEvent.ACTION_DOWN && event.getEdgeFlags() != 0) {
            return false;
        }
        final int action = event.getAction();
        switch (action & MotionEvent.ACTION_MASK){
            case MotionEvent.ACTION_DOWN:
                sX = event.getRawX();
                sY = event.getRawY();
                mDragHelper.processTouchEvent(event);
                break;
            case MotionEvent.ACTION_MOVE:
                checkCanDrag(event);
                MyLog.e("onTouchEvent   mIsBeingDragged  :  "+mIsBeingDragged);
                if (mIsBeingDragged){
                    getParent().requestDisallowInterceptTouchEvent(true);
                    mDragHelper.processTouchEvent(event);
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                mIsBeingDragged = false;
                mDragHelper.processTouchEvent(event);
                break;
            default://handle other action, such as ACTION_POINTER_DOWN/UP
                mDragHelper.processTouchEvent(event);
        }
        return super.onTouchEvent(event) || mIsBeingDragged || action == MotionEvent.ACTION_DOWN;
    }
    private boolean mIsBeingDragged;

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        MyLog.e("onInterceptTouchEvent  Status.Open  test   === : "+ mIsBeingDragged);
        if (!isSwipe()){
            return false;
        }
        if (getStatus() == Status.Open){
//            mIsBeingDragged = true;
            checkCanDrag(ev);
            MyLog.e("onInterceptTouchEvent  Status.Open  : "+ mIsBeingDragged);
            return mIsBeingDragged;
        }
        int action =  ev.getAction() & MotionEvent.ACTION_MASK;
        if (action == MotionEvent.ACTION_CANCEL || action == MotionEvent.ACTION_UP) {
            mIsBeingDragged = false;
            return false;
        }

        switch (action){
            case MotionEvent.ACTION_DOWN:
                mDragHelper.processTouchEvent(ev);
                mIsBeingDragged = false;
                sX = ev.getRawX();
                sY = ev.getRawY();
                if (getStatus() == Status.Draging){
                    mIsBeingDragged = true;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                boolean beforeCheck = mIsBeingDragged;
                checkCanDrag(ev);
                if (mIsBeingDragged) {
                    ViewParent parent = getParent();
                    if (parent != null) {
                        parent.requestDisallowInterceptTouchEvent(true);
                    }
                }
                if (!beforeCheck && mIsBeingDragged) {
                    //let children has one chance to catch the touch, and request the swipe not intercept
                    //useful when swipeLayout wrap a swipeLayout or other gestural layout
                    return false;
                }
                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                mIsBeingDragged = false;
                mDragHelper.processTouchEvent(ev);
                break;
            default://handle other action, such as ACTION_POINTER_DOWN/UP
                mDragHelper.processTouchEvent(ev);
        }
        MyLog.e("mIsBeingDragged  :  "+mIsBeingDragged);
//        if (mIsBeingDragged){
//            mDragHelper.shouldInterceptTouchEvent(ev);
//        }
        return mIsBeingDragged;
    }

    private void checkCanDrag(MotionEvent event){
        if (mIsBeingDragged){
            return;
        }
        if (getStatus() == Status.Draging){
            mIsBeingDragged = true;
            return;
        }

        float distanceX = event.getRawX() - sX;
        float distanceY = event.getRawY() - sY;
        float angle = Math.abs(distanceY / distanceX);

        boolean doNothing = false;
        if (mDragEdge == DragEdge.Right) {
            // suitable false 不拦截
            boolean suitable = (mStatus == Status.Open && distanceX > mTouchSlop  )
                    || (mStatus == Status.Close && distanceX < -mTouchSlop);
            suitable = suitable || (mStatus == Status.Draging);

            if (angle > 30 || !suitable) {
                //不拦截
                doNothing = true;
            }
        }
        if (mDragEdge == DragEdge.Left) {
            boolean suitable = (mStatus == Status.Open && distanceX < -mTouchSlop)
                    || (mStatus == Status.Close && distanceX > mTouchSlop);
            suitable = suitable || mStatus == Status.Draging;

            if (angle > 30 || !suitable) {
                doNothing = true;
            }
        }
        MyLog.e("checkCanDrag   mIsBeingDragged  :  "+!doNothing + "  "+distanceX +"   "+mTouchSlop + "    "+getStatus());
        mIsBeingDragged = !doNothing;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        // 容错性检查  子view不能少于2个
        if (getChildCount() != 2 && getChildCount() != 3) {
            throw new IllegalStateException("Your ViewGroup must have 2 or 3 children.");
        }

        if (!(getChildAt(0) instanceof ViewGroup && getChildAt(1) instanceof ViewGroup)) {
            throw new IllegalArgumentException("Your children must be an instance of ViewGroup");
        }
        if (getChildCount() == 3){
            if (!(getChildAt(2) instanceof ViewGroup)){
                throw new IllegalArgumentException("Your children must be an instance of ViewGroup");
            }
        }
        if (getChildCount() == 3){
            mLeftContent = (ViewGroup) getChildAt(0);
            mMainContent = (ViewGroup) getChildAt(1);
            mRightContent = (ViewGroup) getChildAt(2);
        }else if (getChildCount() == 2){
            if (mDragEdge == DragEdge.Left){
                mLeftContent = (ViewGroup) getChildAt(0);
                MyLog.e( " mLeftContent  : "+getChildCount() + "    =     " + mLeftContent );
                mMainContent = (ViewGroup) getChildAt(1);
            }else {
                mRightContent = (ViewGroup) getChildAt(0);
                mMainContent = (ViewGroup) getChildAt(1);
            }
        }

    }

    @Override
    public boolean getChildVisibleRect(View child, Rect r, Point offset) {
        return super.getChildVisibleRect(child, r, offset);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (mMainContent != null){
            mMainContent.layout(mainLeft, 0, mainLeft + mWidth, mHeight);
        }
    }



    /**
     *
     * @param w
     * @param h
     * @param oldw
     * @param oldh
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        //尺寸有变化的时候调用
        mHeight = getMeasuredHeight();
        mWidth = getMeasuredWidth();
        if (mDragEdge == DragEdge.Left){
            leftWidth = mLeftContent.getMeasuredWidth();
            leftHeight = mLeftContent.getMeasuredHeight();
        }else if (mDragEdge == DragEdge.Right){
            rightWidth = mRightContent.getMeasuredWidth();
            rightHeight = mRightContent.getMeasuredHeight();
        }

        if (first) {
            if (mRange == 0) {
                if (mDragEdge == DragEdge.Left){
                    mRange = (int) (mLeftContent.getMeasuredWidth() - (mMainContent.getMeasuredWidth() * (1 - scale) / 2));
                }else {
                    mRange = (int) (mRightContent.getMeasuredWidth() - (mMainContent.getMeasuredWidth() * (1 - scale) / 2));
                }
            }
            first = false;
        }
    }

    private ViewDragHelper.Callback mDragHelperCallback = new ViewDragHelper.Callback() {
        // 1. 根据返回结果决定当前child是否可以拖拽
        // child 当前被拖拽的View
        // pointerId 区分多点触摸的id
        @Override
        public boolean tryCaptureView(@NonNull View child, int pointerId) {
            return isSwipe;
        }

        @Override
        public void onViewCaptured(@NonNull View capturedChild, int activePointerId) {
            // 当capturedChild被捕获时,调用.
            super.onViewCaptured(capturedChild, activePointerId);
        }

        @Override
        public int getViewHorizontalDragRange(@NonNull View child) {
            // 返回拖拽的范围, 不对拖拽进行真正的限制. 仅仅决定了动画执行速度
            return mRange;
        }

        // 2. 根据建议值 修正将要移动到的(横向)位置
        // 此时没有发生真正的移动
        @Override
        public int clampViewPositionHorizontal(@NonNull View child, int left, int dx) {

            // child: 当前拖拽的View
            // left 新的位置的建议值, dx 位置变化量
            // left = oldLeft + dx;
            if (child == mMainContent) {
                if (mDragEdge == DragEdge.Left){
                    left = fixLeft(left);
                }else if (mDragEdge == DragEdge.Right){
                    left = fixRight(left);
                }
            }
            return left;
        }

        // 3. 当View位置改变的时候, 处理要做的事情 (更新状态, 伴随动画, 重绘界面)
        // 此时,View已经发生了位置的改变
        @Override
        public void onViewPositionChanged(@NonNull View changedView, int left, int top, int dx, int dy) {
            Log.e("tag ", "dispatchDragEvent:  updateStatus Before " + mStatus +"   left  "+left + "   "+dx);
            // changedView 改变位置的View
            // left 新的左边值
            // dx 水平方向变化量
            super.onViewPositionChanged(changedView, left, top, dx, dy);
            int newLeft = left;
            if (changedView == mLeftContent || changedView == mRightContent) {
                // 把当前变化量传递给mMainContent
                newLeft = mMainContent.getLeft() + dx;
            }

            // 进行修正
            if (mDragEdge == DragEdge.Left){
                newLeft = fixLeft(newLeft);
            }else if (mDragEdge == DragEdge.Right){
                newLeft = fixRight(newLeft);
            }

            mainLeft = newLeft;
            if (changedView == mLeftContent) {
                // 当左面板移动之后, 再强制放回去.
                mLeftContent.layout(0, 0, leftWidth,  leftHeight);
            }else if (changedView == mRightContent){
                mRightContent.layout(mWidth - rightWidth, 0, mWidth,  mHeight);
            }
            mMainContent.layout(newLeft, 0, newLeft + mWidth, mHeight);
            // 更新状态,执行动画
            dispatchDragEvent(newLeft);
            // 为了兼容低版本, 每次修改值之后, 进行重绘
            invalidate();
        }

        // 4. 当View被释放的时候, 处理的事情(执行动画)

        @Override
        public void onViewReleased(@NonNull View releasedChild, float xvel, float yvel) {
            // View releasedChild 被释放的子View
            // float xvel 水平方向的速度, 向右为+
            // float yvel 竖直方向的速度, 向下为+
            super.onViewReleased(releasedChild, xvel, yvel);

            // 判断执行 关闭/开启
            // 先考虑所有开启的情况,剩下的就都是关闭的情况
            if (mDragEdge == DragEdge.Left){
                if (xvel == 0 && mMainContent.getLeft() > mRange / 2.0f) {
                    open();
                } else if (xvel > 0) {
                    open();
                } else {
                    close();
                }
            }else if (mDragEdge == DragEdge.Right){
                if (xvel == 0 && mMainContent.getRight() > mRange / 2.0f) {
                    close();
                } else if (xvel > 0) {
                    close();
                } else {
                    open();
                }
            }

        }
        @Override
        public void onViewDragStateChanged(int state) {
            // TODO Auto-generated method stub
            super.onViewDragStateChanged(state);
        }
    };

    @Override
    public void computeScroll() {
        super.computeScroll();
        // 2. 持续平滑动画 (高频率调用)
        if (mDragHelper.continueSettling(true)) {
            //  如果返回true, 动画还需要继续执行
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    /**
     * 状态枚举
     */
    public enum Status {
        Close, Open, Draging;
    }

    /**
     * 抽屉开关的监听器
     */
    public interface OnDragStatusChangeListener {
        void onClose();

        void onOpen();

        void onDraging(float percent);
    }

    /**
     * 打开
     */
    public void open() {
        open(true);
    }

    public void open(boolean isSmooth) {
        int finalLeft = mRange;
        if (mDragEdge == DragEdge.Right){
            finalLeft = -mRange;
        }
        if (isSmooth) {
            if (mDragHelper.smoothSlideViewTo(mMainContent, finalLeft, 0)) {
                ViewCompat.postInvalidateOnAnimation(this);
            }
        } else {
            mMainContent.layout(finalLeft, 0, finalLeft + mWidth, mHeight);
        }
    }

    /**
     * 关闭
     */
    public void close(){
        close(true);
    }
    public void close(boolean isSmooth){
        int finalLeft = 0;
        if (isSmooth){
            if (mDragHelper.smoothSlideViewTo(mMainContent, finalLeft, 0)){
                ViewCompat.postInvalidateOnAnimation(this);
            }
        } else {
            mMainContent.layout(finalLeft, 0, finalLeft + mWidth, mHeight);
        }
    }


    /**
     * 根据范围修正左边值
     *
     * @param left
     * @return
     */
    private int fixLeft(int left) {
        if (left < 0) {
            return 0;
        } else if (left > mRange) {
            return mRange;
        }

        return left;
    }

    private int fixRight(int left) {
        if (left > 0) {
            return 0;
        } else if (left < -mRange) {
            return -mRange;
        }
        return left;
    }

    private void dispatchDragEvent(int newLeft) {
        float percent = Math.abs(newLeft * 1.0f / mRange);
        //0.0f -> 1.0f
        if (mListener != null) {
            mListener.onDraging(percent);
        }

        // 更新状态, 执行回调
        Status preStatus = mStatus;
        Log.e("tag ", "dispatchDragEvent:  updateStatus Before " + mStatus +"   percent  "+percent);
        mStatus = updateStatus(percent);
        Log.e("tag ", "dispatchDragEvent:  updateStatus after" + mStatus +"   percent   "+percent);

        if (mStatus != preStatus) {
            //状态发生变化
            if (mStatus == Status.Close) {
                // 当前变为关闭状态
                if (mListener != null) {
                    mListener.onClose();
                }
            } else if (mStatus == Status.Open) {
                if (mListener != null) {
                    mListener.onOpen();
                }
            }
        }

        //  动画
        animViews(percent);
    }

    private Status updateStatus(float percent) {
        if (percent == 0f) {
            return Status.Close;
        } else if (percent == 1.0f) {
            return Status.Open;
        }
        return Status.Draging;
    }


    private void animViews(float percent) {
        //		> 1. 左面板: 缩放动画, 平移动画, 透明度动画
        if (mDragEdge == DragEdge.Left){
            mLeftContent.setTranslationX(EvaluateUtils.INSTANCE.evaluate(percent, -mWidth / 2.0f, 0) );
            mLeftContent.setAlpha( EvaluateUtils.INSTANCE.evaluate(percent, 0.5f, 1.0f));
            mLeftContent.setScaleX( EvaluateUtils.INSTANCE.evaluate(percent, scale, 1.0f));
            mLeftContent.setScaleY( EvaluateUtils.INSTANCE.evaluate(percent, scale, 1.0f));
        }else if (mDragEdge == DragEdge.Right){
            mRightContent.setTranslationX(EvaluateUtils.INSTANCE.evaluate(percent, mWidth / 2.0f, 0) );
            mRightContent.setAlpha( EvaluateUtils.INSTANCE.evaluate(percent, 0.5f, 1.0f));
            mRightContent.setScaleX( EvaluateUtils.INSTANCE.evaluate(percent, scale, 1.0f));
            mRightContent.setScaleY( EvaluateUtils.INSTANCE.evaluate(percent, scale, 1.0f));
        }

//        //		> 2. 主面板: 缩放动画
        mMainContent.setScaleX(EvaluateUtils.INSTANCE.evaluate(percent, 1.0f, scale));
        mMainContent.setScaleY(EvaluateUtils.INSTANCE.evaluate(percent, 1.0f, scale));

        //		> 3. 背景动画: 亮度变化 (颜色变化)
        if (getBackground() == null){
            setBackgroundColor(Color.WHITE);
        }
        getBackground().setColorFilter(
                (Integer) ColorUtils.INSTANCE.evaluateColor(percent,
                        Color.BLACK, Color.TRANSPARENT), PorterDuff.Mode.SRC_OVER);
    }

    public void setScale(@FloatRange(from = 0.5f, to = 1) float scale) {
        this.scale = scale;
    }

    public boolean isSwipe() {
        return isSwipe;
    }

    public void setSwipe(boolean swipe) {
        isSwipe = swipe;
    }

    public Status getStatus() {
        return mStatus;
    }
    //    public int getRange() {
//        return mRange;
//    }
//
//    public void setRange(int range) {
//        this.mRange = range;
//    }
}
