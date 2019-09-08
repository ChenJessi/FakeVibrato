package com.chen.fakevibrato.widget.decoration;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ImageSpan;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.chen.fakevibrato.R;
import com.chen.fakevibrato.bean.TitleBean;
import com.chen.fakevibrato.utils.DisplayUtils;
import com.qmuiteam.qmui.util.QMUIDrawableHelper;

import java.util.List;

/**
 * 有分类title的
 * ItemDecoration
 */

public class TitleItemDecoration extends RecyclerView.ItemDecoration {
    private static final String TAG = "TitleItemDecoration";
    private Context context;
    private List<TitleBean> mData;
    private Paint mPaint;
    private Rect mBounds;

    private int mTitleHeight;
    private static int TITLE_BG_COLOR = Color.parseColor("#1C1F28");
    private static int TITLE_BG_COLOR_NEW = Color.parseColor("#24272C");
    private static int TITLE_TEXT_COLOR = Color.parseColor("#ABABAB");
    private static int mTitleTextSize;

    private Rect mSrcRect, mDestRect;
    private Paint mBitPaint;


    public TitleItemDecoration(Context context, List<TitleBean> data) {
        super();
        this.context = context;
        mData = data;
        mPaint = new Paint();
        mBounds = new Rect();
        mTitleHeight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 30, context.getResources().getDisplayMetrics());
        mTitleTextSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12, context.getResources().getDisplayMetrics());
        mPaint.setTextSize(mTitleTextSize);
        mPaint.setAntiAlias(true);

        mBitPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBitPaint.setFilterBitmap(true);
        mBitPaint.setDither(true);
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        final int left = parent.getPaddingLeft();
        final int right = parent.getWidth() - parent.getPaddingRight();
        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();
            int position = params.getViewLayoutPosition();
            if (position > -1) {
                if (position == 0) {//等于0的时候绘制title
                    drawTitle(c, left, right, child, params, position);
                } else {
                    if (null != mData.get(position).getInitial() && !mData.get(position)
                            .getInitial().equals(mData.get(position - 1).getInitial())) {
                        //字母不为空，并且不等于前一个，也要title
                        drawTitle(c, left, right, child, params, position);
                    }
                }
            }
        }
    }

    /**
     * 绘制Title区域背景和文字的方法
     *最先调用，绘制最下层的title
     * @param c
     * @param left
     * @param right
     * @param child
     * @param params
     * @param position
     */
    private void drawTitle(Canvas c, int left, int right, View child, RecyclerView.LayoutParams params, int position) {

        mPaint.setColor(TextUtils.equals(mData.get(position).getInitial(), "new") ? TITLE_BG_COLOR_NEW : TITLE_BG_COLOR);
        c.drawRect(left, child.getTop() - params.topMargin - mTitleHeight, right, child.getTop() - params.topMargin, mPaint);

        mPaint.setColor(TITLE_TEXT_COLOR);
        String str = TextUtils.equals(mData.get(position).getInitial(), "new") ? "新好友推荐" : "全部推荐";

        mPaint.getTextBounds(str, 0, mData.get(position).getInitial().length(), mBounds);
//        Paint.FontMetrics fontMetrics = mPaint.getFontMetrics();
//        float height1 = fontMetrics.descent - fontMetrics.ascent;
//        float height2 = fontMetrics.bottom - fontMetrics.top;
        c.drawText(str,
                child.getPaddingLeft() + DisplayUtils.dp2px(context, 15),
                child.getTop() - params.topMargin - (mTitleHeight / 2 - mBounds.height() / 2  ) , mPaint);

        if (!TextUtils.equals(mData.get(position).getInitial(), "new")){
            Bitmap bitmap = QMUIDrawableHelper.drawableToBitmap(ContextCompat.getDrawable(context, R.mipmap.prompt));
            int mBitWidth = bitmap.getWidth();
            int mBitHeight = bitmap.getHeight();
            mSrcRect = new Rect(0, 0, mBitWidth, mBitHeight);
            float measuredWidth = mPaint.measureText(str);

            int bitleft = (int) (child.getPaddingLeft() + DisplayUtils.dp2px(context, 15) + measuredWidth) + DisplayUtils.dp2px(context, 5);

            mDestRect = new Rect(bitleft,  child.getTop() - params.topMargin - (mTitleHeight / 2 + mBitHeight / 2),bitleft + mBitWidth, child.getTop() - params.topMargin - (mTitleHeight / 2  + mBitHeight / 2) + mBitHeight);
            c.drawBitmap(bitmap, mSrcRect, mDestRect  , mBitPaint);
        }
    }

    /**
     * 最后调用，绘制最上层的title
     * @param c
     * @param parent
     * @param state
     */
    @Override
    public void onDrawOver(Canvas c, final RecyclerView parent, RecyclerView.State state) {
//        int position = ((LinearLayoutManager) (parent.getLayoutManager())).findFirstVisibleItemPosition();
//        if (position == -1) return;//在搜索到没有的索引的时候position可能等于-1，所以在这里判断一下
//        String tag = mData.get(position).getInitial();
//        View child = parent.findViewHolderForLayoutPosition(position).itemView;
//        //Canvas是否位移过的标志
//        boolean flag = false;
//        if ((position + 1) < mData.size()) {
//            //当前第一个可见的Item的字母索引，不等于其后一个item的字母索引，说明悬浮的View要切换了
//            if (null != tag && !tag.equals(mData.get(position + 1).getInitial())) {
//                //当第一个可见的item在屏幕中剩下的高度小于title的高度时，开始悬浮Title的动画
//                if (child.getHeight() + child.getTop() < mTitleHeight) {
//                    c.save();
//                    flag = true;
//                    /**
//                     * 下边的索引把上边的索引顶上去的效果
//                     */
//                    c.translate(0, child.getHeight() + child.getTop() - mTitleHeight);
//
//                    /**
//                     * 头部折叠起来的视效（下边的索引慢慢遮住上边的索引）
//                     */
//                    /*c.clipRect(parent.getPaddingLeft(),
//                            parent.getPaddingTop(),
//                            parent.getRight() - parent.getPaddingRight(),
//                            parent.getPaddingTop() + child.getHeight() + child.getTop());*/
//                }
//            }
//        }
//        mPaint.setColor(TITLE_BG_COLOR);
//        c.drawRect(parent.getPaddingLeft(),
//                parent.getPaddingTop(),
//                parent.getRight() - parent.getPaddingRight(),
//                parent.getPaddingTop() + mTitleHeight, mPaint);
//        mPaint.setColor(TITLE_TEXT_COLOR);
//        mPaint.getTextBounds(tag, 0, tag.length(), mBounds);
//        c.drawText(tag, child.getPaddingLeft(),
//                parent.getPaddingTop() + mTitleHeight - (mTitleHeight / 2 - mBounds.height() / 2),
//                mPaint);
//        if (flag)
//            c.restore();//恢复画布到之前保存的状态

    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int position = ((RecyclerView.LayoutParams) view.getLayoutParams()).getViewLayoutPosition();
        if (position > -1) {
            //等于0的时候绘制title
            if (position == 0) {
                outRect.set(0, mTitleHeight, 0, 0);
            } else {
                if (null != mData.get(position).getInitial() &&
                        !mData.get(position).getInitial().equals(mData.get(position - 1).getInitial())) {
                    //字母不为空，并且不等于前一个，绘制title
                    outRect.set(0, mTitleHeight, 0, 0);
                } else {
                    outRect.set(0, 0, 0, 0);
                }
            }
        }
    }

}