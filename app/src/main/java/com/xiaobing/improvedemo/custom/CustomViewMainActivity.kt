package com.xiaobing.improvedemo.custom

import android.app.ActionBar
import android.graphics.Color
import android.view.ViewGroup
import android.widget.TextView
import com.xiaobing.improvedemo.R
import com.xiaobing.improvedemo.base.BaseActivity
import com.xiaobing.improvedemo.custom.view.FlowLayout

/**
 * @author 常晓冰
 * @E-mail 471342365@qq.com
 * @date Created on 2019/3/26
 *
 * 自定义控件的总入口 activity
 */
class CustomViewMainActivity : BaseActivity() {
    override fun initView() {
        setTitle(R.string.ID_animation_open_book)
        val find = findViewById<FlowLayout>(R.id.fl)
        find.addView(getTextView("12312312312321312312"))
        find.addView(getTextView("123"))
        find.addView(getTextView("123"))
        find.addView(getTextView("123"))
        find.addView(getTextView("123"))
        find.addView(getTextView("123"))
        find.addView(getTextView("123"))
        find.addView(getTextView("123"))
        find.addView(getTextView("123"))
        find.addView(getTextView("123"))
    }

    fun getTextView(content:String) :TextView{
        return TextView(this).apply {
            text = content
            setTextColor(Color.YELLOW)
            textSize = 20f
//            height = 50
//            width = 100
            layoutParams.height =ViewGroup.LayoutParams.WRAP_CONTENT
            layoutParams.width =ViewGroup.LayoutParams.WRAP_CONTENT
        }
    }
    override fun setLayoutId(): Int {
        return R.layout.activity_custom_view_main
    }
}