package com.xiao.recycler.decoration

import android.graphics.Rect
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration

/**
 * RecyclerView 装饰器
 * 为线性布局增加左右间距和上下间距
 * 暂时只支持对称设置，不支持不对称设置
 */
class LinearDecoration : ItemDecoration {

    /**
     * 头部间距
     */
    private var topSpace: Int


    /**
     * 底部间距
     */
    private var bottomSpace: Int

    /**
     * 左间距
     */
    private var startSpace: Int

    /**
     * 右间距
     */
    private var endSpace: Int

    /**
     * item之间间距
     */
    private var spaceMiddle = 0

    constructor(
        topSpace: Int,
        bottomSpace: Int,
        startSpace: Int,
        endSpace: Int,
        spaceMiddle: Int,
    ) : super() {
        this.topSpace = topSpace
        this.bottomSpace = bottomSpace
        this.startSpace = startSpace
        this.endSpace = endSpace
        this.spaceMiddle = spaceMiddle
    }
    constructor(
        spaceH: Int,
        spaceV: Int,
        spaceMiddle: Int,
    ) : super() {
        this.topSpace = spaceV
        this.bottomSpace = spaceV
        this.startSpace = spaceH
        this.endSpace = spaceH
        this.spaceMiddle = spaceMiddle
    }



    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val layoutManager = parent.layoutManager as? LinearLayoutManager
        val pos = parent.getChildLayoutPosition(view)
        layoutManager?.let {manager->
            val childCount = manager.childCount
            when(pos){
                0 ->{
                    outRect.set(startSpace,topSpace,endSpace,spaceMiddle)
                }
                in 1 until childCount->{
                    outRect.set(startSpace,spaceMiddle,endSpace,spaceMiddle)
                }
                else-> {
                    outRect.set(startSpace,spaceMiddle,endSpace,bottomSpace)
                }
            }
        }

        Log.e("-----", "outRect : " + outRect.toShortString())
    }
}