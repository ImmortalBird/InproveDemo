package com.xiaobing.improvedemo.main.bean;

public class MainBean implements Cloneable{
    private String name;
    private String link;

    public String getName() {
        return name == null ? "" : name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link == null ? "" : link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
