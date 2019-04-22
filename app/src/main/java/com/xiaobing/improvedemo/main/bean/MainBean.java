package com.xiaobing.improvedemo.main.bean;


import com.xiaobing.improvedemo.util.ParseLinkUtil;

/**
 * @author 常晓冰
 */
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

    public MainBean name(String name){
        this.name = name;
        return this;
    }

    public static class Builder {
        private MainBean bean = new MainBean();

        public Builder name(String name){
            bean.name = name;
            return link(ParseLinkUtil.getLink(name));
        }

        public Builder link(String link){
            bean.link = link;
            return this;
        }

        public MainBean build(){
            return bean;
        }
    }


}
