package com.xiaobing.inprovedemo.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.xiaobing.inprovedemo.R;

/**
 * Created by Administrator on 2018/8/29 0029.
 */

public class BaseActivity extends Activity {

    private TextView title;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        title = findViewById(R.id.tv_title);
    }

    public void setTitle(int resId){
        if (title != null)
            title.setText(getText(resId));
    }
    protected void setTitle(String t){
        if (title != null)
            title.setText(t);
    }
}
