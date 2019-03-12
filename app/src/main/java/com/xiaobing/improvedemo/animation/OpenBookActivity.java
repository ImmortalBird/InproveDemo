package com.xiaobing.improvedemo.animation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.xiaobing.improvedemo.R;
import com.xiaobing.improvedemo.animation.adapter.BookAdapter;
import com.xiaobing.improvedemo.base.BaseActivity;
import com.xiaobing.improvedemo.base.BaseAdapter;

/**
 * @author 常晓冰
 * @E-mail 471342365@qq.com
 * @date Created on 2019/3/12
 */
public class OpenBookActivity extends BaseActivity {

    private RecyclerView recycle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_open_book);
        super.onCreate(savedInstanceState);
        setTitle(R.string.ID_animation_open_book);

        recycle = findViewById(R.id.recycle);
        recycle.setLayoutManager(new GridLayoutManager(this,3));

        recycle.setAdapter(new BookAdapter(this));
    }
}
