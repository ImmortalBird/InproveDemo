package com.xiaobing.improvedemo.animation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;

import androidx.annotation.Nullable;

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
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_book);
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.book_in,R.anim.book_out);
        transparentAndCoverStatusBar(this);

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
        },200);


    }

    private static class MyHandler extends Handler{

    }
}
