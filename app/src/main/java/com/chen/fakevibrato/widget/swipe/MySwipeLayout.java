package com.chen.fakevibrato.widget.swipe;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Scroller;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.GestureDetectorCompat;
import androidx.core.view.ViewCompat;
import androidx.customview.widget.ViewDragHelper;

import com.chen.fakevibrato.utils.ColorUtils;
import com.chen.fakevibrato.utils.EvaluateUtils;
import com.chen.fakevibrato.utils.MyLog;
import com.chen.fakevibrato.widget.anim.AnimtorUtils;

public class MySwipeLayout extends FrameLayout {
    private ViewDragHelper mDragHelper;

    //侧面板
    private ViewGroup mLeftContent;
    //主面板
    private ViewGroup mMainContent;
    private OnDragStatusChangeListener mListener;
    //当前抽屉开关的 状态
    private Status mStatus = Status.Close;
    //打开抽屉开关的难易程度，越小 越难打开，1.0f为normal
    private float mSensitivity = 0.8f;
    private int mHeight;
    private int mWidth;

    private boolean mToogle = true;
    private boolean first = true;
    private  int mRange ;
    private int mXMLRange;

    private GestureDetectorCompat mGestureDetectorCompat;
    private Scroller mScroller;

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

//        mGestureDetectorCompat = new GestureDetectorCompat(getContext(), new XScrollDetector());
//        mScroller = new Scroller(getContext(), sInterpolator);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mDragHelper.processTouchEvent(event);
        return true;
    }

    // b.传递触摸事件
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        // 传递给mDragHelper
        return mDragHelper.shouldInterceptTouchEvent(ev);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        // 容错性检查  子view不能少于2个
        if (getChildCount() < 2) {
            throw new IllegalStateException("Your ViewGroup must have 2 children at least.");
        }

        if (!(getChildAt(0) instanceof ViewGroup && getChildAt(1) instanceof ViewGroup)) {
            throw new IllegalArgumentException("Your children must be an instance of ViewGroup");
        }

        mLeftContent = (ViewGroup) getChildAt(0);
        mMainContent = (ViewGroup) getChildAt(1);

    }

    /**
     * 注意这个方法在onResume生命周期之后调用
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

        if (first) {
            int defaultRange = (int) (mWidth * 0.6f);
            if (mXMLRange != 0) {
                defaultRange = mXMLRange;
            }
            if (mRange == 0) {
                mRange = defaultRange;
//                mRange = mWidth;
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
            MyLog.d("SwipeLayout  tryCaptureView : " + child);
            return mToogle;
        }

        @Override
        public void onViewCaptured(@NonNull View capturedChild, int activePointerId) {
            MyLog.d("SwipeLayout  onViewCaptured : " + capturedChild);
            // 当capturedChild被捕获时,调用.
            super.onViewCaptured(capturedChild, activePointerId);
        }

        @Override
        public int getViewHorizontalDragRange(@NonNull View child) {
            // 返回拖拽的范围, 不对拖拽进行真正的限制. 仅仅决定了动画执行速度
            MyLog.d("SwipeLayout  getViewHorizontalDragRange : " + child);
            return mRange;
        }

        // 2. 根据建议值 修正将要移动到的(横向)位置
        // 此时没有发生真正的移动
        @Override
        public int clampViewPositionHorizontal(@NonNull View child, int left, int dx) {
            // child: 当前拖拽的View
            // left 新的位置的建议值, dx 位置变化量
            // left = oldLeft + dx;
            MyLog.d("SwipeLayout    clampViewPositionHorizontal: "
                    + "oldLeft: " + child.getLeft() + " dx: " + dx + " left: " + left);
            if (child == mMainContent) {
                left = fixLeft(left);
            }
            return left;
        }

        // 3. 当View位置改变的时候, 处理要做的事情 (更新状态, 伴随动画, 重绘界面)
        // 此时,View已经发生了位置的改变
        @Override
        public void onViewPositionChanged(@NonNull View changedView, int left, int top, int dx, int dy) {

            // changedView 改变位置的View
            // left 新的左边值
            // dx 水平方向变化量
            super.onViewPositionChanged(changedView, left, top, dx, dy);
            MyLog.d("SwipeLayout  onViewPositionChanged : " + "left: " + left + " dx: " + dx);
            int newLeft = left;
            if (changedView == mLeftContent) {
                // 把当前变化量传递给mMainContent
                newLeft = mMainContent.getLeft() + dx;
            }
            // 进行修正
            newLeft = fixLeft(newLeft);

            if (changedView == mLeftContent) {
                // 当左面板移动之后, 再强制放回去.
                mLeftContent.layout(0, 0, 0 + mWidth, 0 + mHeight);
                mMainContent.layout(newLeft, 0, newLeft + mWidth, 0 + mHeight);
            }
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

            MyLog.d("SwipeLayout   onViewReleased: " + "xvel: " + xvel + " yvel: " + yvel);
            super.onViewReleased(releasedChild, xvel, yvel);

            // 判断执行 关闭/开启
            // 先考虑所有开启的情况,剩下的就都是关闭的情况
            if (xvel == 0 && mMainContent.getLeft() > mRange / 2.0f) {
                open();
            } else if (xvel > 0) {
                open();
            } else {
                close();
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
        if (isSmooth) {
            if (mDragHelper.smoothSlideViewTo(mMainContent, finalLeft, 0)) {
                ViewCompat.postInvalidateOnAnimation(this);
            }
        } else {
            mMainContent.layout(finalLeft, 0, finalLeft + mWidth, 0 + mHeight);
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
            mMainContent.layout(finalLeft, 0, finalLeft + mWidth, 0 + mHeight);
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

    private void dispatchDragEvent(int newLeft) {
        float percent = newLeft * 1.0f / mRange;
        //0.0f -> 1.0f
        MyLog.d("SwipeLayout  percent: " + percent);

        if (mListener != null) {
            mListener.onDraging(percent);
        }

        // 更新状态, 执行回调
        Status preStatus = mStatus;
        mStatus = updateStatus(percent);

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
        // 缩放动画 0.0 -> 1.0 >>> 0.5f -> 1.0f  >>> 0.5f * percent + 0.5f
        //		mLeftContent.setScaleX(0.5f + 0.5f * percent);
        //		mLeftContent.setScaleY(0.5f + 0.5f * percent);
        // 平移动画: -mWidth / 2.0f -> 0.0f


        mLeftContent.setTranslationX(EvaluateUtils.INSTANCE.evaluate(percent, -mWidth / 2.0f, 0) );
        mLeftContent.setAlpha( EvaluateUtils.INSTANCE.evaluate(percent, 0.5f, 1.0f));
        mLeftContent.setScaleX( EvaluateUtils.INSTANCE.evaluate(percent, 0.5f, 1.0f));
        mLeftContent.setScaleY( 0.5f + 0.5f * percent);

        //		> 2. 主面板: 缩放动画
        mMainContent.setScaleX(EvaluateUtils.INSTANCE.evaluate(percent, 1.0f, 0.8f));
        mMainContent.setScaleY(EvaluateUtils.INSTANCE.evaluate(percent, 1.0f, 0.8f));

        //		> 3. 背景动画: 亮度变化 (颜色变化)
        getBackground().setColorFilter(
                (Integer) ColorUtils.INSTANCE.evaluateColor(percent,
                        Color.BLACK, Color.TRANSPARENT), PorterDuff.Mode.SRC_OVER);
    }



    public int getRange() {
        return mRange;
    }

    public void setRange(int range) {
        this.mRange = range;
    }
}
