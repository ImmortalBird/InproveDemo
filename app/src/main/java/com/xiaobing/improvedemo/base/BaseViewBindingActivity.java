package com.xiaobing.improvedemo.base;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;

import com.xiaobing.improvedemo.R;
import com.xiaobing.improvedemo.util.LogUtil;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;

/**
 *
 * @author Administrator
 * @date 2018/8/29 0029
 */

public abstract class BaseViewBindingActivity<Binding extends ViewBinding> extends Activity {

    private TextView title;
    protected Binding binding;

    @Override
            @SuppressWarnings("unchecked")
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            Class<Binding> bindingClass = (Class<Binding>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
            Method inflate = bindingClass.getMethod("inflate", LayoutInflater.class);
            binding = (Binding) inflate.invoke(null, getLayoutInflater());
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        setContentView(binding.getRoot());
        title = findViewById(R.id.tv_title);
        initView();
    }

    /**
     * 初始化
     */
    protected abstract void initView();


    @Override
    public void setTitle(int resId){
        setTitle(getString(resId));
    }
    protected void setTitle(String t){
        if (title != null){
            title.setText(t);

        }
    }
    @TargetApi(Build.VERSION_CODES.KITKAT)
    public static void transparentAndCoverStatusBar(Activity activity) {
        //FLAG_LAYOUT_NO_LIMITS 这个千万别用，带虚拟按键的机型会有特别多问题

//        //FLAG_TRANSLUCENT_STATUS要求API大于19
//        activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
//        //FLAG_LAYOUT_NO_LIMITS对API没有要求
//        activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
            window.setNavigationBarColor(Resources.getSystem().getColor(android.R.color.background_dark));
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = activity.getWindow();
            window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        LogUtil.print(getClass().getName(),"onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        LogUtil.print(getClass().getName(),"onStop");
    }

    @Override
    protected void onResume() {
        super.onResume();
        LogUtil.print(getClass().getName(),"onResume");

    }

    @Override
    protected void onPause() {
        super.onPause();
        LogUtil.print(getClass().getName(),"onPause");
    }
}
