package com.xiaobing.improvedemo.util

import android.graphics.Paint
import android.text.TextUtils
import android.widget.TextView


/**
 *
 * 根据 view的最大宽度 [maxLength]，[userName] [replyName]来确定最合适的字符显示
 * @param view TextView  textView
 * @param userName String   用户名
 * @param middleText String 中间连接词
 * @param replyName String  被回复的用户名称
 * @param maxLength Int     textView的最大宽度
 *
 */
fun setTextByWidth(view: TextView, userName: String,middleText:String = " 回复 ", replyName: String, maxLength: Int) {
    val minFullDisplayLength = 5
    val paint = view.paint
    var width1 = paint.measureText(userName)
    var maxWidth: Float = maxLength.toFloat()
    var width2 = 0F
    if (!TextUtils.isEmpty(replyName)) {
        val aWidth = paint.measureText(middleText)
        width2 = paint.measureText(replyName)
        maxWidth = maxLength - aWidth
    }
    if (width1 + width2 > maxWidth) {
        if (userName.length <= minFullDisplayLength || replyName.length <= minFullDisplayLength) {
            if (userName.length <= minFullDisplayLength) {
                width2 = maxWidth - width1
            } else {
                width1 = maxWidth - width2
            }
        } else {
            val all = maxWidth / (width1 + width2)
            width1 *= all
            width2 *= all
            val tempW1 = paint.measureText("${userName.substring(0, minFullDisplayLength)}…")
            if (width1 < tempW1) {
                width1 = tempW1
                width2 = maxWidth - tempW1
            } else {
                val tempW2 = paint.measureText("${userName.substring(0, minFullDisplayLength)}…")
                if (width2 < tempW2) {
                    width2 = tempW2
                    width1 = maxWidth - tempW2
                }
            }
        }
        // 超长文本
        view.text = getBreakString(paint, userName, width1)
        if (width2 > 0) {
            view.append(middleText)
            view.append(getBreakString(paint, replyName, width2))
        }
    } else {
        if (width1 > maxWidth) {
            view.text = getBreakString(paint, userName, maxWidth)
        } else
            view.text = userName
        if (width2 > 0) {
            view.append(middleText)
            view.append(replyName)
        }
    }
}

/**
 * 需求:  现在有两个用户名（A和B）和一个中间连接词（默认为回复），需要根据TextView 的最大宽度maxWidth去显示出来
 * 如果有超长的用户名，则尽量将其显示全，在尾部补上...
 * @param paint Paint
 * @param sourceText String
 * @param maxWidth Float
 * @return String
 */
fun getBreakString(paint: Paint, sourceText: String, maxWidth: Float): String {
    if (sourceText.length <= 5)
        return sourceText
    return "${sourceText.substring(0, paint.breakText(sourceText, true, maxWidth, null) - 1)}…"
}