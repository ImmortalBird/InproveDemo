package com.xiaobing.improvedemo.animation;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.view.KeyEvent;

import com.xiaobing.improvedemo.R;
import com.xiaobing.improvedemo.base.BaseActivity;

/**
 * @author 常晓冰
 * @E-mail 471342365@qq.com
 * @date Created on 2019/3/13
 */
public class BookActivity extends BaseActivity {


    public static void startMe(Context context) {
        context.startActivity(new Intent(context, BookActivity.class));
    }


    @Override
    protected void initView() {
        overridePendingTransition(R.anim.book_in, R.anim.book_out);
        transparentAndCoverStatusBar(this);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_book;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onBackPressed() {
        new MyHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                BookActivity.super.onBackPressed();
            }
        }, 200);


    }

    private static class MyHandler extends Handler {

    }
}
