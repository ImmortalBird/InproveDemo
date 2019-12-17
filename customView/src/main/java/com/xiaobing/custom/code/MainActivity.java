package com.xiaobing.custom.code;

import android.os.Bundle;

import com.xiaobing.custom.code.lession_1_2_1.GradientLayout;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new GradientLayout(this));
    }
}
