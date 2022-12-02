package com.xiaobing.improvedemo.setting;

import android.provider.Settings;

import androidx.recyclerview.widget.RecyclerView;

import com.xiaobing.improvedemo.R;
import com.xiaobing.improvedemo.base.activity.BaseActivity;
import com.xiaobing.improvedemo.setting.bean.SettingBean;

import java.util.ArrayList;

public class SettingActivity extends BaseActivity {

    private RecyclerView container;

    @Override
    protected void initView() {
        setTitle(R.string.ID_setting_main);
        container = findViewById(R.id.root);

        ArrayList<SettingBean> settings = new ArrayList<>();
        settings.add(new SettingBean("ACTION_NETWORK_OPERATOR_SETTINGS", Settings.ACTION_NETWORK_OPERATOR_SETTINGS));
        settings.add(new SettingBean("ACTION_ADD_ACCOUNT", Settings.ACTION_ADD_ACCOUNT));
        settings.add(new SettingBean("ACTION_APPLICATION_SETTINGS", Settings.ACTION_APPLICATION_SETTINGS));

        // TODO: 2019/11/5 更多可跳转的系统设置页面待总结 
        /*
        * android.content.ActivityNotFoundException: No Activity found to handle Intent { act=android.settings.APP_OPS_SETTINGS }
        * settings.add(new SettingBean("ACTION_APP_OPS_SETTINGS", Settings.ACTION_APP_OPS_SETTINGS));
        * settings.add(new SettingBean("ACTION_APP_OPS_SETTINGS", "android.settings.APP_OPS_SETTINGS"));
        */



        SettingAdapter adapter = new SettingAdapter(this);
        container.setAdapter(adapter);
        adapter.setData(settings);

    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_setting;
    }


}
