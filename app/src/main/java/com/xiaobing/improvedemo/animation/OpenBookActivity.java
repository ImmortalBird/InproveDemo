package com.xiaobing.improvedemo.animation;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.xiaobing.improvedemo.R;
import com.xiaobing.improvedemo.base.BaseActivity;

/**
 * @author 常晓冰
 * @E-mail 471342365@qq.com
 * @date Created on 2019/3/12
 */
public class OpenBookActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_open_book);
        super.onCreate(savedInstanceState);
        setTitle(R.string.ID_animation_open_book);
    }
}
