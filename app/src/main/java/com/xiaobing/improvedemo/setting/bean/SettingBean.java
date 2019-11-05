package com.xiaobing.improvedemo.setting.bean;

public class SettingBean {
    private String name;
    private String action;

    public SettingBean(String name, String action) {
        this.name = name;
        this.action = action;
    }

    public String getName() {
        return name;
    }

    public String getAction() {
        return action;
    }


}
