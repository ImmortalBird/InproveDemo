package com.xiaobing.improvedemo.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.View;

import com.joker.annotation.MainEnter;
import com.xiaobing.improvedemo.R;
import com.xiaobing.improvedemo.base.activity.BaseActivity;

/**
 * Created by Administrator on 2018/8/29 0029.
 */
@MainEnter(name="通知")
public class NotificationActivity extends BaseActivity implements View.OnClickListener {

    private NotificationManager notificationManager;


    @Override
    protected void initView() {

        View normal = findViewById(R.id.tv_normal);
        View expand = findViewById(R.id.tv_expand);
        View hang = findViewById(R.id.tv_hang);
        normal.setOnClickListener(this);
        expand.setOnClickListener(this);
        hang.setOnClickListener(this);
        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_notification;
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
                        .setContentTitle(getString(R.string.ID_notification_01_02))
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
