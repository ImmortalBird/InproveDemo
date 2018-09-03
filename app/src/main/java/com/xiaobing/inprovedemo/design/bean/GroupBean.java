package com.xiaobing.inprovedemo.design.bean;

import java.util.ArrayList;

public class GroupBean {

    private String title;
    private ArrayList<ChildText> children;

    public GroupBean(String title) {
        this.title = title;
    }

    public GroupBean(String title, ArrayList<ChildText> children) {
        this.title = title;
        this.children = children;
    }

    public String getTitle() {
        return title == null ? "" : title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<ChildText> getChildren() {
        if (children == null) {
            return new ArrayList<>();
        }
        return children;
    }

    public void setChildren(ArrayList<ChildText> children) {
        this.children = children;
    }
}
