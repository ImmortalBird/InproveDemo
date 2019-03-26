package com.xiaobing.improvedemo.custom;

import com.xiaobing.improvedemo.R;
import com.xiaobing.improvedemo.base.BaseActivity;

/**
 * @author 常晓冰
 * @E-mail 471342365@qq.com
 * @date Created on 2019/3/26
 *
 * 自定义控件的总入口 activity
 */
public class CustomViewMainActivity extends BaseActivity {


    @Override
    protected void initView() {

        setTitle(R.string.ID_animation_open_book);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_custom_view_main;
    }
}
