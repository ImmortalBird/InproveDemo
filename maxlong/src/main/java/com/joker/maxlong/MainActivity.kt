package com.joker.maxlong

import android.app.Activity
import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.widget.EditText
import android.widget.TextView
import androidx.core.view.marginLeft

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val text = findViewById<TextView>(R.id.text)
        val et1 = findViewById<EditText>(R.id.et1)
        val et2 = findViewById<EditText>(R.id.et2)

        val max = resources.displayMetrics.widthPixels - text.marginLeft - resources.getDimensionPixelOffset(R.dimen.eee)
        setTextByWidth(text, "312321", "321321", max)
        var text1 = ""
        var text2 = ""
        et1.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                text1 = s?.toString() ?: ""
                setTextByWidth(text, text1, text2, max)
            }

        })
        et2.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                text2 = s?.toString() ?: ""
                setTextByWidth(text, text1, text2, max)
            }

        })
    }
}

/**
 *
 * 根据 view的最大宽度 [maxLength]，[userName] [replyName]来确定最合适的字符显示
 * @param view TextView  textView
 * @param userName String   用户名
 * @param replyName String  被回复的用户名称
 * @param maxLength Int     textView的最大宽度
 *
 */
fun setTextByWidth(view: TextView, userName: String, replyName: String, maxLength: Int) {
    val minFullDisplayLength = 5
    val paint = view.paint
    var width1 = paint.measureText(userName)
    var maxWidth: Float = maxLength.toFloat()
    var width2 = 0F
    if (!TextUtils.isEmpty(replyName)) {
        val aWidth = paint.measureText(" 回复 ")
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
            view.append(" 回复 ")
            view.append(getBreakString(paint, replyName, width2))
        }
    } else {
        if (width1 > maxWidth) {
            view.text = getBreakString(paint, userName, maxWidth)
        } else
            view.text = userName
        if (width2 > 0) {
            view.append(" 回复 ")
            view.append(replyName)
        }
    }
}

fun getBreakString(paint: Paint, sourceText: String, maxWidth: Float): String {
    if (sourceText.length <= 5)
        return sourceText
    return "${sourceText.substring(0, paint.breakText(sourceText, true, maxWidth, null) - 1)}…"
}
