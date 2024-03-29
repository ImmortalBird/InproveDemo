package com.xiaobing.custom;

import android.os.Bundle;
import android.util.Log;


import androidx.appcompat.app.AppCompatActivity;

import com.xiaobing.custom.code.lession_1_2_2.ColorFilterView;
import com.xiaobing.custom.code.lession_1_2_3.SaveRestoreView;
import com.xiaobing.custom.view.lession_1_2_3.TransformView;
import com.xiaobing.custom.view.lession_1_2_6.PathMeasureView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(new com.xiaobing.custom.code.lession_1_2_3.split.SplitView(this));
//        setContentView(new TransformView(this));
//        setContentView(new SaveRestoreView(this));
//        setContentView(new GradientLayout(this));
//        setContentView(new GradientLayout(this));
//        setContentView(new ColorFilterView(this));
        setContentView(R.layout.activity_main_1_2_3);
//        setContentView(R.layout.activity_main_1_2_4);

//        setContentView(new PathMeasureView(this));
//        setContentView(new PathView(this));
//        setContentView(new ClearView(this));
//        setContentView(new DragBubbleView(this));
//        setContentView(R.layout.activity_main_vp);
//        setContentView(R.layout.activity_main_1_2_5);
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

    @Override
    protected void onStart() {
        super.onStart();
        Log.e("MainActivity", "onStart");
//        finish();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("MainActivity", "onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("MainActivity", "onResume");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("MainActivity", "onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e("MainActivity", "onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("MainActivity", "onDestroy");
    }
}
