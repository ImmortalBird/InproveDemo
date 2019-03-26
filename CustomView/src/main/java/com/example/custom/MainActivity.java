package com.example.custom;

import android.os.Bundle;

import com.example.custom.view.lession_1_2_3.TransformView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new TransformView(this));

    }
}
