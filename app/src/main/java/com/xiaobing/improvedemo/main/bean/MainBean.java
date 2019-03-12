package com.xiaobing.improvedemo.main.bean;


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

    public static class Buidler{
        private MainBean bean = new MainBean();

        public Buidler name(String name){
            bean.name = name;
            return this;
        }

        public Buidler link(String link){
            bean.link = link;
            return this;
        }

        public MainBean build(){
            return bean;
        }
    }


}
