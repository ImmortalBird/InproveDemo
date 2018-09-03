package com.xiaobing.inprovedemo.design.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.xiaobing.inprovedemo.R;

public class ExpandRecyclerViewActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expand_rv);
    }
}
