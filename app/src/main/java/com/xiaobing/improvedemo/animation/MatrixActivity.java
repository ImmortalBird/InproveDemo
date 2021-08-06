package com.xiaobing.improvedemo.animation;

import android.graphics.Matrix;
import android.widget.RadioGroup;

import com.xiaobing.improvedemo.R;
import com.xiaobing.improvedemo.base.BaseActivity;
import com.xiaobing.improvedemo.databinding.ActivityMatrixBinding;


/**
 * @author 常晓冰
 * @E-mail 471342365@qq.com
 * @date Created on 2019/3/15
 */
public class MatrixActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {

    private ActivityMatrixBinding binding;

    @Override
    protected void initView() {
        binding = ActivityMatrixBinding.inflate(getLayoutInflater());
        binding.rg.setOnCheckedChangeListener(this);
        Matrix matrix = binding.image.getMatrix();

        float[] src = new float[]{0, 0,                         // 左上
                binding.image.getWidth(), 0,                    // 右上
                binding.image.getWidth(), binding.image.getHeight(),    // 右下
                0, binding.image.getHeight()};

        float[] dst = new float[]{0, 0,                             // 左上
                binding.image.getWidth(), 400,                      // 右上
                binding.image.getWidth(), binding.image.getHeight() - 200,  // 右下
                0, binding.image.getHeight()};
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
        binding.image.setTestPoint(count);
    }
}
