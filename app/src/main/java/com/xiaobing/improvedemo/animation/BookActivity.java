package com.xiaobing.improvedemo.animation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

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
}
