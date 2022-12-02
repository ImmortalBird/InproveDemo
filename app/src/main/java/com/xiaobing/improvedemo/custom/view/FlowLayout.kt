package com.xiaobing.improvedemo.custom.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import com.xiaobing.improvedemo.util.DisplayUtil
import com.xiaobing.improvedemo.util.LogUtil
import java.io.File
import java.lang.reflect.Field
import java.util.ArrayList
import java.util.zip.ZipFile

class FlowLayout : ViewGroup {
    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : this(
        context,
        attrs,
        defStyleAttr, 0
    )

    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes) {
        LogUtil.print("s")
    }

    /**
     * 每行的高度
     */
    val linesHeight = mutableListOf<Int>()

    /**
     * 行数
     */
    var lineCount = linesHeight.size
        private set

    /**
     * 每次触发换行的索引
     */
    val mBreakLineIndexList = mutableListOf<Int>()


    /**
     * 水平方向和竖直方向的间距
     */
    val mHorizontalSpace = DisplayUtil.dip2px(16F)
    val mVerticalSpace = DisplayUtil.dip2px(16F)

    /**
     * Joker: 2022/12/2
     * 重置 行高集合 和 换行index集合
     */
    private fun resetList() {
        linesHeight.clear()
        mBreakLineIndexList.clear()
    }


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        resetList()
        // 当前行已经使用的宽度
        var lineWidthUsed = 0

        // 当前行的高度
        var lineHeight = 0

        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)

        var parentNeedWidth = 0
        var parentNeedHeight = 0

        for (i in 0 until childCount) {
            val child = getChildAt(i)
            val childParams = child.layoutParams
            val childWidthMeasureSpec =
                getChildMeasureSpec(widthMeasureSpec, paddingStart + paddingEnd, childParams.width)
            val childHeightMeasureSpec = getChildMeasureSpec(
                heightMeasureSpec,
                paddingTop + paddingBottom,
                childParams.height
            )
            child.measure(childWidthMeasureSpec, childHeightMeasureSpec)

            val childWidth = child.measuredWidth
            val childHeight = child.measuredHeight

            // 判断是否要换行
            if (lineWidthUsed + childWidth > widthSize) {
                linesHeight.add(lineHeight)
                mBreakLineIndexList.add(i)
                parentNeedWidth = lineWidthUsed.coerceAtLeast(parentNeedWidth)
                parentNeedHeight += lineHeight + mVerticalSpace
                lineWidthUsed = 0
                lineHeight = 0
            }
            lineWidthUsed += (childWidth + mHorizontalSpace)
            lineHeight = lineHeight.coerceAtLeast(childHeight)

            if (i == childCount - 1) {
                linesHeight.add(lineHeight)
                mBreakLineIndexList.add(i)
                parentNeedWidth = lineWidthUsed.coerceAtLeast(parentNeedWidth)
                parentNeedHeight += lineHeight
            }
        }

        val realWidth = if (widthMode == MeasureSpec.EXACTLY) widthSize else parentNeedWidth
        val realHeight = if (heightMode == MeasureSpec.EXACTLY) heightSize else parentNeedHeight
        setMeasuredDimension(realWidth, realHeight)
    }


    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        var curL = paddingStart
        var curT = paddingTop
        for (i in 0 until mBreakLineIndexList[0]) {
            val child = getChildAt(i)
            child.layout(curL, curT, curL + child.measuredWidth, child.measuredHeight + curT)
            curL += (child.measuredWidth + mHorizontalSpace)
        }
        for (line in 0 until mBreakLineIndexList.size - 1) {
            curT += (linesHeight[0] + mVerticalSpace)
            curL = paddingStart
            for (i in mBreakLineIndexList[line] until mBreakLineIndexList[line + 1]) {
                val child = getChildAt(i)
                child.layout(curL, curT, curL + child.measuredWidth, child.measuredHeight + curT)
                curL += (child.measuredWidth + mHorizontalSpace)
            }
        }

        if (mBreakLineIndexList[mBreakLineIndexList.size - 1] < childCount) {
            for (i in mBreakLineIndexList[mBreakLineIndexList.size - 1] until childCount) {
                val child = getChildAt(i)
                child.layout(curL, curT, curL + child.measuredWidth, child.measuredHeight + curT)
            }
        }
    }
}