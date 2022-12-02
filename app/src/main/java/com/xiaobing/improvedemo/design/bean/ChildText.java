package com.xiaobing.improvedemo.design.bean;

public class ChildText {

    private String text;

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    private boolean isSelected;


    public ChildText(String text) {
        this.text = text;
    }

    public String getText() {
        return text == null ? "" : text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "ChildText{" +
                "text='" + text + '\'' +
                ", isSelected=" + isSelected +
                '}';
    }
}
