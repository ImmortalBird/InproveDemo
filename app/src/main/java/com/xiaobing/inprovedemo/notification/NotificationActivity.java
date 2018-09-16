package com.xiaobing.inprovedemo.notification;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.xiaobing.inprovedemo.R;
import com.xiaobing.inprovedemo.base.BaseActivity;

/**
 * Created by Administrator on 2018/8/29 0029.
 */

public class NotificationActivity extends BaseActivity implements View.OnClickListener {

    private View expand;
    private View hang;
    private NotificationManager notificationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_notification);
        super.onCreate(savedInstanceState);

        View normal = findViewById(R.id.tv_normal);
        expand = findViewById(R.id.tv_expand);
        hang = findViewById(R.id.tv_hang);
        normal.setOnClickListener(this);
        expand.setOnClickListener(this);
        hang.setOnClickListener(this);
        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_normal:
                Notification.Builder builder = new Notification.Builder(this);
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://blog.csdn.net/itachi85/"));
                PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
                Notification notification = builder.setContentIntent(pendingIntent)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher))
                        .setAutoCancel(true)
                        .setContentTitle(getString(R.string.ID_design_02_02))
                        .build();
                notificationManager.notify(1,notification);

                break;
            case R.id.tv_expand:
                break;
            case R.id.tv_hang:
                break;
        }
    }
}
