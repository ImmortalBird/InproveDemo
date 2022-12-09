package com.xiaobing.improvedemo.font;

import android.widget.TextView;

import androidx.core.content.res.ResourcesCompat;

import com.joker.annotation.MainEnter;
import com.xiaobing.improvedemo.R;
import com.xiaobing.improvedemo.base.activity.BaseActivity;

/**
 * 自定义字体
 */
@MainEnter(name = "自定义字体")
public class FontActivity extends BaseActivity{
    @Override
    protected void initView() {
        setTitle(getString(R.string.ID_rx_01_01));
        TextView tv02 = findViewById(R.id.tv_02);
        TextView tv03 = findViewById(R.id.tv_03);
        tv02.setTypeface( ResourcesCompat.getFont(this, R.font.mao_ze_dong));
        tv03.setTypeface( ResourcesCompat.getFont(this, R.font.ye_gen_you_xing));
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_font_main;
    }
}
