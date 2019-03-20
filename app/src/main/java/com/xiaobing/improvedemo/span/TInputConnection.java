package com.xiaobing.improvedemo.span;

import android.view.KeyEvent;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputConnectionWrapper;

import com.xiaobing.improvedemo.util.LogUtil;

/**
 * @author 常晓冰
 * @E-mail 471342365@qq.com
 * @date Created on 2019/3/20
 * <p>
 * 创建此类是为了实现对键盘 backspace 的监听
 */
public class TInputConnection extends InputConnectionWrapper {


    // 监听是否点击了 backspace
    private OnBackspaceListener listener;

    /**
     * Initializes a wrapper.
     *
     * <p><b>Caveat:</b> Although the system can accept {@code (InputConnection) null} in some
     * places, you cannot emulate such a behavior by non-null {@link InputConnectionWrapper} that
     * has {@code null} in {@code target}.</p>
     *
     * @param target  the {@link InputConnection} to be proxied.
     * @param mutable set {@code true} to protect this object from being reconfigured to target
     *                another {@link InputConnection}.  Note that this is ignored while the target is {@code null}.
     */
    public TInputConnection(InputConnection target, boolean mutable) {
        super(target, mutable);
    }

    public interface OnBackspaceListener {
        boolean onPressBackspace();
    }

    /**
     * 为 backspace 监听器 赋值
     *
     * @param listener 监听器
     */
    public void setListener(OnBackspaceListener listener) {
        this.listener = listener;
    }

    /**
     * 点击 backspace 时触发
     *
     * @param beforeLength 删除操作之前的长度
     * @param afterLength  删除操作之后的长度
     * @return 是否？ todo
     */
    @Override
    public boolean deleteSurroundingText(int beforeLength, int afterLength) {
        LogUtil.print("onPressBackspace beforeLength = " + beforeLength + "  afterLength = " + afterLength);
        return listener != null ?
                listener.onPressBackspace() || super.deleteSurroundingText(beforeLength, afterLength)
                : super.deleteSurroundingText(beforeLength, afterLength);
    }

    @Override
    public boolean sendKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_DEL && event.getAction() == KeyEvent.ACTION_DOWN) {
            if (listener != null && listener.onPressBackspace()) {
                return true;
            }
        }
        return super.sendKeyEvent(event);
    }
}
