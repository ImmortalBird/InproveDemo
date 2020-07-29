package com.xiaobing.test;

import android.app.admin.SystemUpdateInfo;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tv = findViewById(R.id.tv);

        String text = "Build.ID = " + Build.ID + "\n";
        tv.setText(text);

//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                tv.append("Build.getSerial1 = " + Build.getSerial() + "\n");
//            }else{
//                tv.append("Build.getSerial2 = " + Build.SERIAL + "\n");
//            }
//            return;
//        }

        tv.append("getLanguage = " + Locale.getDefault().getLanguage() + "\n");
        tv.append("androidId = " + Settings.System.getString(getContentResolver(), Settings.Secure.ANDROID_ID) + "\n");
        tv.append("Build.BOARD = " + Build.BOARD + "\n");
        tv.append("Build.BOOTLOADER = " + Build.BOOTLOADER + "\n");
        tv.append("Build.BRAND = " + Build.BRAND + "\n");
        tv.append("Build.DEVICE = " + Build.DEVICE + "\n");
        tv.append("Build.DISPLAY = " + Build.DISPLAY + "\n");
        tv.append("Build.FINGERPRINT = " + Build.FINGERPRINT + "\n");
        tv.append("Build.HARDWARE = " + Build.HARDWARE + "\n");
        tv.append("Build.HOST = " + Build.HOST + "\n");
        tv.append("Build.ID = " + Build.ID + "\n");
        tv.append("Build.MANUFACTURER = " + Build.MANUFACTURER + "\n");
        tv.append("Build.MODEL = " + Build.MODEL + "\n");
        tv.append("Build.PRODUCT = " + Build.PRODUCT + "\n");
        tv.append("Build.TAGS = " + Build.TAGS+ "\n");
        tv.append("Build.TYPE = " + Build.TYPE + "\n");
        tv.append("Build.TIME = " + Build.TIME + "\n");
        tv.append("Build.SUPPORTED_32_BIT_ABIS = " + Build.SUPPORTED_32_BIT_ABIS + "\n");
        tv.append("Build.SUPPORTED_64_BIT_ABIS = " + Build.SUPPORTED_64_BIT_ABIS + "\n");
        tv.append("Build.SUPPORTED_ABIS = " + Build.SUPPORTED_ABIS + "\n");
    }
}
