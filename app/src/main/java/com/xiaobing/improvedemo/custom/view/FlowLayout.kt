package com.xiaobing.improvedemo.custom.view

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import com.xiaobing.improvedemo.util.DisplayUtil
import com.xiaobing.improvedemo.util.LogUtil

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
    var lineCount = 0

    /**
     * 每次触发换行的索引
     */
    val mBreakLineIndexList = mutableListOf<Int>()


    val mHorizontalSpace = DisplayUtil.dip2px(16F)
    val mVerticalSpace = DisplayUtil.dip2px(16F)

    fun resetList() {
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
        var parentNeedHeight = mVerticalSpace

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
                parentNeedHeight += lineHeight + mVerticalSpace
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
        curT += (linesHeight[0] + mVerticalSpace)
        curL = paddingStart
        for (line in 0 until mBreakLineIndexList.size - 1) {
            for (i in mBreakLineIndexList[line] until mBreakLineIndexList[line + 1]) {
                val child = getChildAt(i)
                curL += (child.measuredWidth + mHorizontalSpace)
            }
            curT += (linesHeight[0] + mVerticalSpace)
            curL = paddingStart
        }
    }
}