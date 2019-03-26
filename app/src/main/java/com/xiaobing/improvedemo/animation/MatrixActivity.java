package com.xiaobing.improvedemo.animation;

import android.graphics.Matrix;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.xiaobing.improvedemo.R;
import com.xiaobing.improvedemo.animation.view.SetPolyToPoly;
import com.xiaobing.improvedemo.base.BaseActivity;

import butterknife.BindView;

/**
 * @author 常晓冰
 * @E-mail 471342365@qq.com
 * @date Created on 2019/3/15
 */
public class MatrixActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {
    @BindView(R.id.image)
    SetPolyToPoly  image;
    @BindView(R.id.rb0)
    RadioButton rb0;
    @BindView(R.id.rb1)
    RadioButton rb1;
    @BindView(R.id.rb2)
    RadioButton rb2;
    @BindView(R.id.rb3)
    RadioButton rb3;
    @BindView(R.id.rb4)
    RadioButton rb4;
    @BindView(R.id.rg)
    RadioGroup rg;

    @Override
    protected void initView() {
        rg.setOnCheckedChangeListener(this);
        Matrix matrix = image.getMatrix();

        float[] src = new float[]{0, 0,                         // 左上
                image.getWidth(), 0,                    // 右上
                image.getWidth(), image.getHeight(),    // 右下
                0, image.getHeight()};

        float[] dst = new float[]{0, 0,                             // 左上
                image.getWidth(), 400,                      // 右上
                image.getWidth(), image.getHeight() - 200,  // 右下
                0, image.getHeight()};
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_matrix;
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        int count;
        switch (checkedId) {
            case R.id.rb0:
               count = 0;
                break;
            case R.id.rb1:
                count = 1;
                break;
            case R.id.rb2:
                count = 2;
                break;
            case R.id.rb3:
                count = 3;
                break;
            case R.id.rb4:
                count = 4;
                break;
            default:
                count = 0;
                break;
        }
       image.setTestPoint(count);
    }
}
