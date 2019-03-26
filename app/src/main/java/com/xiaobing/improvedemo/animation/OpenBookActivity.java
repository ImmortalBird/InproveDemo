package com.xiaobing.improvedemo.animation;

import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.xiaobing.improvedemo.R;
import com.xiaobing.improvedemo.animation.adapter.BookAdapter;
import com.xiaobing.improvedemo.animation.anim.ContentScaleAnimation;
import com.xiaobing.improvedemo.animation.anim.Rotate3DAnimation;
import com.xiaobing.improvedemo.base.BaseActivity;
import com.xiaobing.improvedemo.util.LogUtil;

import java.util.Arrays;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author 常晓冰
 * @E-mail 471342365@qq.com
 * @date Created on 2019/3/12
 */
public class OpenBookActivity extends BaseActivity implements BookAdapter.OnBookClickListener, Animation.AnimationListener {

    private RecyclerView recycle;
    private ImageView cover, content;
    private int[] location = new int[2];
    private int statusHeight;
    private ContentScaleAnimation contentScale;
    private Rotate3DAnimation coverTrans;
    private boolean isOpenBook = false;
    private GridLayoutManager layoutManager;
    private boolean canGoBack;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        transparentAndCoverStatusBar(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setTitle(R.string.ID_animation_open_book);

        recycle = findViewById(R.id.recycle);
        layoutManager = new GridLayoutManager(this, 3);
        recycle.setLayoutManager(layoutManager);

        // 获取状态栏高度
        statusHeight = -1;
        //获取status_bar_height资源的ID
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            //根据资源ID获取响应的尺寸值
            statusHeight = 0
//                     + getResources().getDimensionPixelSize(resourceId)
//                    + getResources().getDimensionPixelSize(R.dimen.base40dp)
            ;
        }


        BookAdapter adapter = new BookAdapter(this);
        recycle.setAdapter(adapter);
        content = findViewById(R.id.img_content);
        cover = findViewById(R.id.img_first);
        adapter.setListener(this);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_open_book;
    }

    @Override
    public void onItemClick(int pos, View view) {

        // 这段的作用是如果点击条目在屏幕中没有完全显示，就让它完全显示，延迟200毫秒后再执行动画
        // 是为了解决底部item点击动画显示不全的问题
        int lastP = layoutManager.findLastCompletelyVisibleItemPosition();
        int firstP = layoutManager.findFirstCompletelyVisibleItemPosition();
        if (lastP < pos || firstP > pos) {
            recycle.smoothScrollToPosition(pos);
            new Handler().postDelayed(() -> onItemClick(pos, view), 200);
            return;
        }

        /*
         * 获取点击控件的宽高和位置
         */
        // 获取宽高
        int height = view.getHeight();
        int width = view.getWidth();
        // 获取位置
        view.getLocationInWindow(location);
        Log.d("onItemClick", "height = " + height);
        Log.d("onItemClick", "width = " + width);
        Log.d("onItemClick", "location = " + Arrays.toString(location));

        LinearLayout.LayoutParams vParams = (LinearLayout.LayoutParams) view.getLayoutParams();
        Log.d("onItemClick", "vParams.height = " + vParams.height);
        Log.d("onItemClick", "vParams.width = " + vParams.width);
        Log.d("onItemClick", "vParams.leftMargin = " + vParams.leftMargin);
        Log.d("onItemClick", "vParams.topMargin = " + vParams.topMargin);

        /*
         * 将执行动画的控件 的 宽高和位置设置为与点击控件相同
         * 就是覆盖在点击的控件上
         */
        // 获取一个 LayoutParams 对象
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) cover.getLayoutParams();
        // 设置 height、width、leftMargin、topMargin
        params.height = height;
        params.width = width;
        params.leftMargin = location[0];
        params.topMargin = location[1] - statusHeight;
        // 将 params 设置到控件上
        Glide.with(this).load("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1552907482285&di=ccf7054864be6b24b14a51843e0e1baf&imgtype=0&src=http%3A%2F%2Fa4.topitme.com%2Fl%2F201102%2F13%2F12975887687368.jpg").into(cover);
        cover.setLayoutParams(params);
        content.setLayoutParams(params);
        int[] ints = new int[2];
        content.getLocationInWindow(ints);
        Log.d("onItemClick", "params.height = " + params.height);
        Log.d("onItemClick", "params.width = " + params.width);
        Log.d("onItemClick", "statusHeight = " + statusHeight);
        Log.d("onItemClick", "params.leftMargin = " + params.leftMargin);
        Log.d("onItemClick", "params.topMargin = " + params.topMargin);
        Log.d("onItemClick", "content location = " + Arrays.toString(ints));
        if (View.GONE == cover.getVisibility()) {
            cover.setVisibility(View.VISIBLE);
            content.setVisibility(View.VISIBLE);
        }
        /*
         * 初始化动画
         */
        initAnimation(view);
        /*
         * 执行动画
         */

        cover.clearAnimation();
        cover.startAnimation(coverTrans);
        content.clearAnimation();
        content.startAnimation(contentScale);
    }

    private void initAnimation(View view) {
        // 获取 View 的宽高
        int viewWidth = view.getWidth();
        int viewHeight = view.getHeight();

        // 获取屏幕矩阵参数
        DisplayMetrics metrics = new DisplayMetrics();
        getWindow().getWindowManager().getDefaultDisplay().getMetrics(metrics);
        // 获取屏幕宽高
        float heightPixels = metrics.heightPixels;
        float widthPixels = metrics.widthPixels;
        // 计算缩放倍率
        float verScale = heightPixels / viewHeight;
        float horScale = widthPixels / viewWidth;
        float scale = horScale > verScale ? horScale : verScale;

        contentScale = new ContentScaleAnimation(location[0], location[1], widthPixels, heightPixels, scale, false);

        contentScale.setInterpolator(new DecelerateInterpolator());
        int durationMillis = 1000;
        contentScale.setDuration(durationMillis);
        contentScale.setFillAfter(true);
        contentScale.setAnimationListener(this);

        coverTrans = new Rotate3DAnimation(OpenBookActivity.this,
                -180f, 0f, location[0], location[1], scale, true);
        coverTrans.setDuration(durationMillis);
        coverTrans.setInterpolator(new DecelerateInterpolator());
        coverTrans.setFillAfter(true);


    }

    @Override
    protected void onRestart() {
        super.onRestart();
        // 当界面重新进入的时候进行合书的动画
        LogUtil.print("onRestart");
        LogUtil.print("onRestart - isOpenBook = " + isOpenBook);
        if (isOpenBook) {
            contentScale.reverse();
            coverTrans.reverse();
            cover.clearAnimation();
            cover.startAnimation(coverTrans);
            content.clearAnimation();
            content.startAnimation(contentScale);
        }
    }

    @Override
    public void onAnimationStart(Animation animation) {
        canGoBack = false;
    }

    @Override
    public void onAnimationEnd(Animation animation) {
        if (contentScale.hasEnded() && coverTrans.hasEnded()) {
            canGoBack = true;
            // 两个动画都结束的时候再处理后续操作
            if (!isOpenBook) {
                isOpenBook = true;
                BookActivity.startMe(this);
            } else {
                isOpenBook = false;
                cover.clearAnimation();
                content.clearAnimation();
                cover.setVisibility(View.GONE);
                content.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void onBackPressed() {
        if (canGoBack) {
            super.onBackPressed();
        }
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }

    @Override
    protected void onStart() {
        super.onStart();
        LogUtil.print("onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        LogUtil.print("onStop");
    }

    @Override
    protected void onResume() {
        super.onResume();
        LogUtil.print("onResume");

    }

    @Override
    protected void onPause() {
        super.onPause();
        LogUtil.print("onPause");
    }
}
