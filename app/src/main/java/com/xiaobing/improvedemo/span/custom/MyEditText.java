package com.xiaobing.improvedemo.span.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;

import com.xiaobing.improvedemo.span.TInputConnection;

import androidx.appcompat.widget.AppCompatEditText;

/**
 * @author 常晓冰
 * @E-mail 471342365@qq.com
 * @date Created on 2019/3/20
 */
public class MyEditText extends AppCompatEditText {

    private TInputConnection tInputConnection;

    public MyEditText(Context context) {
        this(context,null);
    }

    public MyEditText(Context context, AttributeSet attrs) {
        this(context, attrs,0);

    }

    public MyEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        requestFocus();
        tInputConnection = new TInputConnection(null, true);
    }

    @Override
    public InputConnection onCreateInputConnection(EditorInfo outAttrs) {
        tInputConnection.setTarget(super.onCreateInputConnection(outAttrs));
        return tInputConnection;
    }

    /**
     * 设置监听器
     * @param listener 监听器
     */
    public void setBackSpaceListener(TInputConnection.OnBackspaceListener listener){
        tInputConnection.setListener(listener);
    }
}
