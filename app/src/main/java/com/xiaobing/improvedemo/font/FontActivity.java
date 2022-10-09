package com.xiaobing.improvedemo.font;

import android.view.View;
import android.widget.TextView;

import androidx.core.content.res.ResourcesCompat;

import com.xiaobing.improvedemo.R;
import com.xiaobing.improvedemo.base.BaseActivity;

public class FontActivity extends BaseActivity{
    @Override
    protected void initView() {
        setTitle(getString(R.string.ID_rx_01_01));
        TextView tv01 = findViewById(R.id.tv_01);
        TextView tv02 = findViewById(R.id.tv_02);
        TextView tv03 = findViewById(R.id.tv_03);
        TextView tv04 = findViewById(R.id.tv_04);
        TextView tv05 = findViewById(R.id.tv_05);
//        tv01.setTypeface( ResourcesCompat.getFont(this, R.font.mao));
        tv02.setTypeface( ResourcesCompat.getFont(this, R.font.mao_ze_dong));
        tv03.setTypeface( ResourcesCompat.getFont(this, R.font.ye_gen_you_xing));
//        tv04.setTypeface( ResourcesCompat.getFont(this, R.font.jin_mei_mao_xing_shu));
//        tv05.setTypeface( ResourcesCompat.getFont(this, R.font.t1));
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_font_main;
    }
}
