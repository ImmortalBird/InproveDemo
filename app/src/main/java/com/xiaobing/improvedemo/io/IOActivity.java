package com.xiaobing.improvedemo.io;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.joker.annotation.MainEnter;
import com.xiaobing.improvedemo.R;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * IO流
 */
@MainEnter(name = "IO流")
public class IOActivity extends Activity implements View.OnClickListener {

    private EditText etContent,etWrite;
    private final String fileName = "NewTextFile.txt";
    private static final int REQUEST_PERMISSION = 100;
    private static final int REQUEST_PERMISSION2 = 101;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_io);
        Button btRead = findViewById(R.id.bt_read);
        Button btWrite = findViewById(R.id.bt_write);
        Button btReadSD = findViewById(R.id.bt_read_sd);
        Button btWriteSD = findViewById(R.id.bt_write_sd);
        etContent = findViewById(R.id.et_content);
        etWrite = findViewById(R.id.et_write);

        btRead.setOnClickListener(this);
        btWrite.setOnClickListener(this);
        btReadSD.setOnClickListener(this);
        btWriteSD.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.bt_read:
                etContent.setText( read());
                break;
            case R.id.bt_read_sd:
                etContent.setText(readSD());
                break;
            case R.id.bt_write:
                write(etWrite.getText().toString());
                break;
            case R.id.bt_write_sd:
                writeSD(etWrite.getText().toString());
                break;
        }
    }

    private void write(String s) {
        if (s!= null){
            try {
                if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
//                    File esd = Environment.getExternalStorageDirectory();
//                    File targetFile = new File(esd.getCanonicalPath() + fileName);
                    FileOutputStream fos = openFileOutput(fileName,MODE_APPEND);
                    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
                    bw.write(s);
                    bw.close();
                    Log.e("write" ,"s = "+s);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private void writeSD(String s) {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    REQUEST_PERMISSION);
        }
        if (s!= null){
            try {
                if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
                    File esd = Environment.getExternalStorageDirectory();
                    File targetFile = new File(esd.getCanonicalPath()+"/" + fileName);
                    FileOutputStream fos = new FileOutputStream(targetFile);
                    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
                    bw.write(s);
                    bw.close();
                    Log.e("write" ,"s = "+s);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String read() {
        try {
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
            {

                FileInputStream fis = openFileInput(fileName);
                BufferedReader br = new BufferedReader(new InputStreamReader(fis));
                StringBuilder sb = new StringBuilder();
                String line;
                while((line = br.readLine()) != null){
                    sb.append(line);
                }
                br.close();
                Log.e("write" ,"sb = "+ sb);
                return sb.toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
    private String readSD() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    REQUEST_PERMISSION2);
        }
        try {
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
            {

                FileInputStream fis = new FileInputStream(Environment.getExternalStorageDirectory().getCanonicalPath() +"/"+ fileName);
                BufferedReader br = new BufferedReader(new InputStreamReader(fis));
                StringBuilder sb = new StringBuilder();
                String line;
                while((line = br.readLine()) != null){
                    sb.append(line);
                }
                br.close();
                Log.e("write" ,"sb = "+ sb);
                return sb.toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,@NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch(requestCode){
            case REQUEST_PERMISSION:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    write(etWrite.getText().toString());
                }
                break;
            case REQUEST_PERMISSION2:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    readSD();
                }
                break;
        }

    }
}
