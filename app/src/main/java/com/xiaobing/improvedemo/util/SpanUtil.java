package com.xiaobing.improvedemo.util;

import android.graphics.Color;
import androidx.annotation.ColorInt;
import androidx.annotation.IntDef;
import android.text.Spannable;
import android.text.style.BackgroundColorSpan;
import android.text.style.CharacterStyle;
import android.text.style.ForegroundColorSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.UnderlineSpan;
import android.widget.TextView;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class SpanUtil {
    public static final int EXCLUD_MODE = Spannable.SPAN_EXCLUSIVE_EXCLUSIVE;
    public static final int INCLUD_MODE = Spannable.SPAN_INCLUSIVE_INCLUSIVE;
    public static final int EXCLUD_INCLUD_MODE = Spannable.SPAN_EXCLUSIVE_INCLUSIVE;
    public static final int INCLUD_EXCLUD_MODE = Spannable.SPAN_INCLUSIVE_EXCLUSIVE;
    static final byte TYPE_FOREGROUND = 1;       // 字体颜色
    static final byte TYPE_BACKGROUND = 2;       // 背景颜色
    static final byte TYPE_BOLD = 3;             // 加粗
    static final byte TYPE_ITALIC = 4;           // 斜体
    static final byte TYPE_DELETE_LINE = 5;      // 删除线
    static final byte TYPE_UNDERLINE = 6;       // 下划线

    public static void setSpan(TextView view, String text, int start, int end, @SpanConfig int spanType) {
        switch (spanType) {
            case TYPE_FOREGROUND:
                break;
            case TYPE_BACKGROUND:
                break;
            case TYPE_BOLD:
                break;
            case TYPE_ITALIC:
                break;
            case TYPE_DELETE_LINE:
                break;
            case TYPE_UNDERLINE:
                break;

        }
    }

    private static CharacterStyle getSpan(@SpanConfig int spanType) {
        switch (spanType) {
            case TYPE_FOREGROUND:
                return getForegroundSpan(Color.RED);
            case TYPE_BACKGROUND:
                return getForegroundSpan(Color.RED);
            case TYPE_BOLD:
                break;
            case TYPE_ITALIC:
                break;
            case TYPE_DELETE_LINE:
                break;
            case TYPE_UNDERLINE:
                break;

        }
        return null;
    }

    /**
     * 获取一个 文字颜色 span
     *
     * @param color 颜色
     * @return span
     */
    private static CharacterStyle getForegroundSpan(@ColorInt int color) {
        return new ForegroundColorSpan(color);
    }

    /**
     * 获取一个 背景颜色 span
     *
     * @param color 颜色
     * @return span
     */
    public static CharacterStyle getBackgroundSpan(@ColorInt int color) {
        return new BackgroundColorSpan(color);
    }

    /**
     * 获取一个删除线span
     * @return span
     */
    public static CharacterStyle getStrikethroughSpan(){
        return new StrikethroughSpan();
    }
    /**
     * 获取一个删除线span
     * @return span
     */
    public static CharacterStyle getUnderlineSpan(){
        return new UnderlineSpan();
    }








    @IntDef({TYPE_FOREGROUND, TYPE_BACKGROUND, TYPE_BOLD, TYPE_ITALIC, TYPE_DELETE_LINE, TYPE_UNDERLINE})
    @Retention(RetentionPolicy.SOURCE)
    @interface SpanConfig {
    }
}
