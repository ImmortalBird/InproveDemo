package com.xiaobing.inprovedemo.design.bean;

public class ChildText {

    private String text;


    public ChildText(String text) {
        this.text = text;
    }

    public String getText() {
        return text == null ? "" : text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
