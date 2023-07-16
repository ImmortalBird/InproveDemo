package com.xiao.recycler.decoration;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.xiao.recycler.R;

/**
 * RecyclerView 以Grid方式垂直排列，用于在item之间产生间距
 * 更改：添加recycleView中一行条目两端的左右间隔
 * 注：暂时只支持对称设置，不支持不对称设置
 */
public class LinearDecoration1 extends RecyclerView.ItemDecoration {

    private int itemSpace;
    private int spaceMiddle;
    private int spaceBottom;
    private int spaceTop;
    private Drawable mDivider;
    private int posT;

    public LinearDecoration1(Context context, int itemSpace, int spaceMiddle, int spaceBottom, int spaceTop, int posT) {
        this.itemSpace = itemSpace;
        this.spaceMiddle = spaceMiddle / 2;
        this.spaceBottom = spaceBottom;
        this.spaceTop = spaceTop;
        this.posT = posT;
        mDivider = context.getResources().getDrawable(R.drawable.shape_gift_divider);
    }

    public LinearDecoration1(int itemSpace) {
        this.itemSpace = itemSpace;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        int pos = parent.getChildLayoutPosition(view);
        GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
        int spanCount = gridLayoutManager.getSpanCount();//总列数
        GridLayoutManager.SpanSizeLookup spanSizeLookup = gridLayoutManager.getSpanSizeLookup();
        int row = spanSizeLookup.getSpanGroupIndex(pos, spanCount);//第几行
        int index = spanSizeLookup.getSpanIndex(pos, spanCount);//行中的第几个
        int spanSize = spanSizeLookup.getSpanSize(pos);//item占的列数
        Log.e("-----", "row : " + row);
        Log.e("-----", "index : " + index);
        Log.e("-----", "spanSize : " + spanSize);
        if (spanSize == 1 && pos <= posT) {
            switch (index) {
                case 0:
                    outRect.set(itemSpace, spaceTop, spaceMiddle, spaceBottom);
                    break;
                case 1:
                    outRect.set(spaceMiddle, spaceTop, itemSpace, spaceBottom);
                    break;
            }
        } else {
            int half = itemSpace / 2;
            if (spanSize == 1) {
                if (row == 1) {
                    outRect.top = itemSpace;
                    outRect.bottom = half;
                } else if (row == spanCount - 1) {
                    outRect.top = half;
                    outRect.bottom = itemSpace;
                } else {
                    outRect.top = half;
                    outRect.bottom = half;
                }
                switch (index) {
                    case 0:
                    case 1:
                        outRect.left = itemSpace;
                        outRect.right = half;
                        break;
                }
            } else {
                outRect.left = 0;
                outRect.right = 0;
                outRect.bottom = 0;
            }
        }
        Log.e("-----", "outRect : " + outRect.toShortString());
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        drawHorizontal(c, parent);
    }

    private void drawVertical(Canvas c, RecyclerView parent) {
        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final int left = child.getLeft() - itemSpace;
            final int top = child.getTop();
            final int bottom = child.getBottom();
            final int right = child.getLeft();
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }

    private void drawHorizontal(Canvas c, RecyclerView parent) {
        int childCount = parent.getChildCount();
        GridLayoutManager layoutManager = (GridLayoutManager) parent.getLayoutManager();
        int first = layoutManager.findFirstVisibleItemPosition();
        int last = layoutManager.findLastVisibleItemPosition();
        GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
        int spanCount = gridLayoutManager.getSpanCount();//总列数
        GridLayoutManager.SpanSizeLookup spanSizeLookup = gridLayoutManager.getSpanSizeLookup();
        for (int i = 0; i < childCount; i++) {
            int spanSize = spanSizeLookup.getSpanSize(first + i);
            if (spanSize != 1)
                continue;
            if (first + i > posT)
                break;
            if ((first + i) % 2 == 0)
                continue;
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();
//            if (isEn){
            final int left = 0;
            final int top = child.getTop() - spaceTop;
            final int right = parent.getWidth();
//                final int right = child.getRight() + itemSpace +10;
            final int bottom = child.getBottom() + spaceBottom;
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
//            }else {
//                final int left = 0;
//                final int top = child.getTop() - spaceTop;
//                final int right = parent.getWidth();
//                final int bottom = child.getTop()  + spaceBottom;
//                mDivider.setBounds(left, top, right, bottom);
//                mDivider.draw(c);
//            }
        }
    }

    private boolean isLastColum(RecyclerView parent, int spanCount, View view) {
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        int pos = layoutManager.getPosition(view);
        if (layoutManager instanceof GridLayoutManager) {
            if ((pos + 1) % spanCount == 0) {// 如果是最后一列，则不需要绘制右边
                return true;
            }
        }
        return false;
    }

    private boolean isLastRaw(RecyclerView parent, int spanCount,
                              int childCount) {
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            if (childCount % spanCount == 0 || childCount % spanCount < spanCount)
                return true;
        }
        return false;
    }
}