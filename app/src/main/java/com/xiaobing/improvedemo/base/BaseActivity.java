package com.xiaobing.improvedemo.base;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.xiaobing.improvedemo.R;

/**
 *
 * @author Administrator
 * @date 2018/8/29 0029
 */

public class BaseActivity extends Activity {

    private TextView title;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        title = findViewById(R.id.tv_title);
    }

    @Override
    public void setTitle(int resId){
        if (title != null){
            title.setText(getText(resId));

        }
    }
    protected void setTitle(String t){
        if (title != null){
            title.setText(t);

        }
    }
    @TargetApi(Build.VERSION_CODES.KITKAT)
    public static void transparentAndCoverStatusBar(Activity activity) {
        //FLAG_LAYOUT_NO_LIMITS这个千万别用，带虚拟按键的机型会有特别多问题

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


}
