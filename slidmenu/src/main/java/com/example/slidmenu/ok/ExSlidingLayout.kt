package com.example.slidmenu.ok

import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.ViewConfiguration
import android.view.ViewGroup
import androidx.annotation.IdRes
import com.example.slidmenu.R

/**
 * @author Created by cz
 * @date 2019-06-17 09:47
 * @email chenzhen@okay.cn
 * 可以上下抽动的滑动式抽屉
 * @attr R.attr.ex_sliding_handle 配置可拖动把手的 id
 */
class ExSlidingLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : ViewGroup(context, attrs, defStyleAttr) {
    companion object {
        private const val TAG="ExSlidingLayout"
        /**
         * 最大支持控件个数,顶部 layout 中间 handle 底部 view
         */
        private const val MAX_VIEW_COUNT=3

        /**
         * 拖动时不更新
         */
        private const val UPDATE_NONE=0x01
        /**
         * 拖动时更新顶部布局
         */
        private const val UPDATE_START_LAYOUT=0x02
        /**
         * 拖动时更新底部布局
         */
        private const val UPDATE_END_LAYOUT=0x04
    }
    /**
     * 拖动view的id
     */
    private var handleId:Int=View.NO_ID
    /**
     * 默认的排版权重
     */
    private var slidingWeight:Float=0f
    /**
     * 上次拖动位置
     */
    private var lastX=0f
    private var lastY=0f

    /**
     * 拖动阀值
     */
    private var scaledTouchSlop=0
    /**
     * 是否开始拖动
     */
    private var isBeingDragged=false
    /**
     * 用于临时操作的rect 对象
     */
    private val tempRect= Rect()
    /**
     * 拖动时更新布局标记
     */
    private var updateFlag=UPDATE_NONE

    init {
        setWillNotDraw(false)
        scaledTouchSlop = ViewConfiguration.get(context).scaledTouchSlop
        context.obtainStyledAttributes(attrs, R.styleable.ExSlidingLayout).apply {
            setSlidingHandle(getResourceId(R.styleable.ExSlidingLayout_ex_sliding_handle, View.NO_ID))
            setSlidingWeight(getFloat(R.styleable.ExSlidingLayout_ex_sliding_weight,1f))
        }
    }

    /**
     * 设置拖动把手的 id
     */
    private fun setSlidingHandle(@IdRes id: Int) {
        if(id==View.NO_ID){
            throw NullPointerException("Before you drag the layout,you should set sliding_handle to the handle bar!")
        }
        this.handleId=id
    }

    /**
     * 设置底部布局初始排版权重
     */
    private fun setSlidingWeight(float: Float) {
        this.slidingWeight=float
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        //检测把子控件是否存在
        findViewById<View>(handleId) ?: throw IllegalArgumentException("Handle id is invalid! Please check your id:${resources.getResourceEntryName(handleId)}")
    }


    override fun addView(child: View?, index: Int, params: LayoutParams?) {
        super.addView(child, index, params)
        if(childCount> MAX_VIEW_COUNT){
            throw IllegalArgumentException("SlidingLayout can't add additional views!")
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val measuredHeight = measuredHeight

        val layoutHeight = measuredHeight - paddingTop - paddingBottom

        //测量子控件
        val startLayout=getChildAt(0)
        //起始控件所占高度
        val startLayoutHeight = slidingWeight * layoutHeight
        //测量起始控件
        measureChild(startLayout, widthMeasureSpec,  MeasureSpec.makeMeasureSpec(startLayoutHeight.toInt(),MeasureSpec.EXACTLY))

        //先测量中间把手,因为整个高度除把手之后的距离按比例划分
        val handleLayout=findViewById<View>(handleId)
        if(startLayout==handleLayout){
            throw IllegalArgumentException("The start layout can't be the handle layout!")
        }
        measureChild(handleLayout, widthMeasureSpec,  heightMeasureSpec)

        //测量底部布局
        val endLayout=getChildAt(childCount-1)
        val endLayoutHeight = (1f-slidingWeight) * layoutHeight
        //测量起始控件
        measureChild(endLayout, widthMeasureSpec,  MeasureSpec.makeMeasureSpec(endLayoutHeight.toInt(),MeasureSpec.EXACTLY))
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        //排版中间把手
        val handleLayout=findViewById<View>(handleId)
        //排版起始控件
        val startLayout=getChildAt(0)
        val weightLayoutHeight=(measuredHeight * slidingWeight).toInt()
        startLayout.layout(paddingLeft,paddingTop,measuredWidth-paddingRight,paddingTop+weightLayoutHeight)

        handleLayout.layout(paddingLeft,weightLayoutHeight-handleLayout.measuredHeight,measuredWidth-paddingRight,weightLayoutHeight)
        //排版底部布局
        val endLayout=getChildAt(childCount-1)
        endLayout.layout(paddingLeft,weightLayoutHeight,measuredWidth-paddingRight,weightLayoutHeight+endLayout.measuredHeight)
    }

    /**
     * 手势操作在 handleBar时拦截手势
     */
    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        val action = ev.actionMasked
        when(action){
            MotionEvent.ACTION_DOWN->{
                lastX=ev.x
                lastY=ev.y
            }
            MotionEvent.ACTION_MOVE->{
                checkHandleBar(ev)
                if(isBeingDragged){
                    return true
                }
            }
            MotionEvent.ACTION_UP,MotionEvent.ACTION_CANCEL->{
                isBeingDragged = false
            }
        }
        return super.onInterceptTouchEvent(ev)
    }

    /**
     * 移动布局
     */
    override fun onTouchEvent(event: MotionEvent): Boolean {
        val action = event.actionMasked
        when(action){
            MotionEvent.ACTION_DOWN->{
                lastY=event.y
            }
            MotionEvent.ACTION_MOVE->{
                //检测 handle 是否被拖动
                checkHandleBar(event)
                //如果被拖动,滚动视图
                if(isBeingDragged){
                    var offsetY=(event.y-lastY).toInt()
                    val startLayout = getChildAt(0)
                    val handleLayout = findViewById<View>(handleId)
                    val endLayout = getChildAt(childCount-1)
                    //做越界检测,取 startLayout的minimumHeight 与 endLayout 的minimumHeight作范围约束
                    //往上越界
                    if(0 > offsetY){
                        if(handleLayout.top<=startLayout.minimumHeight){
                            offsetY=0
                        } else if(startLayout.minimumHeight>handleLayout.top+offsetY){
                            offsetY=startLayout.minimumHeight-handleLayout.top
                        }
                    }
                    //往上越界
                    if(0 < offsetY){
                        val endOffset = measuredHeight - endLayout.top
                        if(endOffset<=endLayout.minimumHeight){
                            offsetY=0
                        } else if(endLayout.minimumHeight>endOffset-offsetY){
                            offsetY=endOffset-endLayout.minimumHeight
                        }
                    }
                    lastY=event.y
                    //移动把手,与最后一个布局体
                    handleLayout.offsetTopAndBottom(offsetY)
                    endLayout.offsetTopAndBottom(offsetY)
                    val layoutHeight = measuredHeight - paddingTop - paddingBottom

                    val slidingWeight = endLayout.top * 1f / layoutHeight
                    this.slidingWeight = slidingWeight
                    startLayout.layoutParams.height = (layoutHeight * slidingWeight).toInt()
                    endLayout.layoutParams.height = (layoutHeight * (1f - slidingWeight)).toInt()
                    requestLayout()
                }
            }
            MotionEvent.ACTION_UP,MotionEvent.ACTION_CANCEL->{
                isBeingDragged = false
            }
        }
        return true
    }

    /**
     * 检测 handleBar
     */
    private fun checkHandleBar(ev: MotionEvent) {
        if (!isBeingDragged) {
            val offsetX = Math.abs(ev.x - lastX)
            val offsetY = Math.abs(ev.y - lastY)
            val handleLayout = findViewById<View>(handleId)
            tempRect.set(handleLayout.left, handleLayout.top, handleLayout.right, handleLayout.bottom)
            if (tempRect.contains(ev.x.toInt(), ev.y.toInt())) {
                if (offsetX > scaledTouchSlop || offsetY > scaledTouchSlop) {
                    //开始拖动
                    isBeingDragged = true
                }
            }
        }
    }

}