package com.xiaobing.improvedemo.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
}
