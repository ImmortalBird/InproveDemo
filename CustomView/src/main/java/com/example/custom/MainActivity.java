package com.example.custom;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import com.example.custom.custom.vp.ClipPagerAdapter;
import com.example.custom.custom.vp.TestViewPager;
import com.example.custom.custom.vp.ScalePageTransformer;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(new TransformView(this));
//        setContentView(new SaveRestoreView(this));
//        setContentView(new GradientLayout(this));
//        setContentView(new GradientLayout(this));
//        setContentView(new ColorFilterView(this));
        setContentView(R.layout.activity_main_1_2_4);

//        setContentView(R.layout.activity_main_vp);
//
//        final TestViewPager cvp = findViewById(R.id.cvp);
//
//        //需要将整个页面的事件分发给ViewPager，不然的话只有ViewPager中间的view能滑动，其他的都不能滑动，这是肯定的，
//        //因为ViewPager总体布局就是中间那一块大小，其他的子布局都跑到ViewPager外面来了
//        findViewById(R.id.root).setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                return cvp.dispatchTouchEvent(event);
//            }
//        });
//
//        ClipPagerAdapter adapter = new ClipPagerAdapter( this);
//        cvp.setAdapter(adapter);
//        cvp.setPageTransformer(true, new ScalePageTransformer());
//        cvp.setOffscreenPageLimit(adapter.getCount());
//        adapter.notifyDataSetChanged();


    }
}
