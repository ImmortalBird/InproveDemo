package com.xiaobing.improvedemo.animation;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;

import com.joker.annotation.MainEnter;
import com.xiaobing.improvedemo.R;
import com.xiaobing.improvedemo.animation.adapter.BookAdapter;
import com.xiaobing.improvedemo.base.activity.BaseActivity;
import com.xiaobing.improvedemo.link.UriAction;
import com.xiaobing.improvedemo.main.adapter.MainAdapter;
import com.xiaobing.improvedemo.main.bean.MainBean;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author 常晓冰
 * @E-mail 471342365@qq.com
 * @date Created on 2019/3/12
 *
 * 用来收集一些酷炫的动画效果
 */
@MainEnter(name = "动画")
public class AnimationMainActivity extends BaseActivity implements Animation.AnimationListener, BookAdapter.OnBookClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void initView() {
        setTitle(R.string.ID_animation_main_01);
        RecyclerView content = findViewById(R.id.rv_content);
        MainAdapter adapter = new MainAdapter(this, getMainBeans());
        content.setAdapter(adapter);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_animation_main;
    }

    private List<MainBean> getMainBeans() {
        ArrayList<MainBean> data = new ArrayList<>();
        data.add(new MainBean.Builder()
                .name(UriAction.ACTION_ANIMATION_OPEN_BOOK)
                .build());
        data.add(new MainBean.Builder()
                .name(UriAction.ACTION_MATRIX)
                .build());
        return data;
    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {

    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }

    @Override
    public void onItemClick(int pos, View view) {

    }
}
