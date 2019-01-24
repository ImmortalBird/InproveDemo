package com.xiaobing.improvedemo.design.bean;

import java.util.ArrayList;

public class GroupBean {

    private String title;

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    private boolean isSelected;
    private boolean isExpand;
    private ArrayList<ChildText> children;

    public boolean isExpand() {
        return isExpand;
    }

    public void setExpand(boolean expand) {
        isExpand = expand;
    }

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
