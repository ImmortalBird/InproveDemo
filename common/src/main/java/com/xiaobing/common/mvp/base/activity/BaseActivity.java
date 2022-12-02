package com.xiaobing.common.mvp.base.activity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.xiaobing.common.mvp.base.presenter.BasePresenter;
import com.xiaobing.common.mvp.base.view.BaseView;


/**
 * 基于 MVP 架构的BaseActivity
 * @param <P>           presenter
 * @param <CONTRACT>    接口约束
 */
public abstract class BaseActivity<P extends BasePresenter, CONTRACT>
        extends AppCompatActivity
        implements BaseView<P, CONTRACT> {
    protected P presenter;
    protected CONTRACT contract;

    protected abstract void initView();


    @SuppressWarnings("unchecked")
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        immersive();
        presenter = getPresenter();
        contract = getContract();
        presenter.bindView(this);
        initView();
    }

    /**
     * 沉浸式
     */
    private void immersive() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            return;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            //设置状态栏颜色透明
            window.setStatusBarColor(Color.TRANSPARENT);
            int visibility = window.getDecorView().getSystemUiVisibility();
            //布局内容全屏展示
            visibility |= View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
            //隐藏虚拟导航栏
//            visibility |= View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
            //防止内容区域大小发生变化
            visibility |= View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            // 设置状态栏图标为深色，防止白色背景看不清楚 需要API>=23 否则无效
            visibility |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            window.getDecorView().setSystemUiVisibility(visibility);
        } else {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    /**
     * 给 Activity 设置 layout
     *
     * @return layout id
     */
    public abstract int getLayoutId();

    @Override
    public abstract CONTRACT getContract();

    /**
     * P 层产生的错误会通过此方法传递到 V 层
     *
     * @param error 里面包含详细的错误信息
     */
    public void onError(Exception error) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 解除绑定
        presenter.unBindView();
    }
}
