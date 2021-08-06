package com.example.slidmenu

import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import android.view.MotionEvent
import android.view.ViewGroup
import android.widget.HorizontalScrollView
import android.widget.LinearLayout
import kotlin.math.abs

class SlidMenu : HorizontalScrollView {
    var mScreenWidth: Int = 0

    /**
     * dp
     */
    var mMenuRightPadding: Int = 50

    var mMenuWidth = 0
    var mHalfMenuWidth = 0


    var once = false


    constructor(context: Context?) : this(context, null) {}
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0) {}
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {}

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        if (!once) {
            mScreenWidth = resources.displayMetrics.widthPixels
            val wrapper = getChildAt(0) as LinearLayout
            val menu = wrapper.getChildAt(1) as ViewGroup

            val content = wrapper.getChildAt(0) as ViewGroup

            mMenuRightPadding = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                    mMenuRightPadding.toFloat(), content.resources.displayMetrics).toInt()

            mMenuWidth = mScreenWidth - mMenuRightPadding

            mHalfMenuWidth = mMenuWidth.shr(1)
            content.layoutParams.width = mScreenWidth
            menu.layoutParams.width = mMenuWidth
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        super.onLayout(changed, l, t, r, b)
        if (changed) {
            this.scrollTo(0, 0)
            once = true
        }
    }

    override fun onTouchEvent(ev: MotionEvent): Boolean {
        when (ev.action) {
            MotionEvent.ACTION_UP -> {
                if (abs(scrollX) > mHalfMenuWidth) {
                    this.smoothScrollTo(mMenuWidth, 0)
                } else
                    this.smoothScrollTo(0, 0)

                return true
            }
        }
        return super.onTouchEvent(ev)
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        return false
        return super.onInterceptTouchEvent(ev)
    }
}
