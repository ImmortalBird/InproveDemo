package com.xiaobing.improvedemo.span.factory;

import android.text.style.BackgroundColorSpan;
import android.text.style.CharacterStyle;
import android.text.style.ForegroundColorSpan;

public class SpanFactory {
    /**
     * 获取一个 文字颜色 span
     * @param color 颜色
     * @return span
     */
    public static CharacterStyle getForegroundSpan(int color) {
        return new ForegroundColorSpan(color);
    }


    /**
     * 获取一个 背景颜色 span
     * @param color 颜色
     * @return span
     */
    public static CharacterStyle getBackgroundSpan(int color) {
        return new BackgroundColorSpan(color);
    }



}
