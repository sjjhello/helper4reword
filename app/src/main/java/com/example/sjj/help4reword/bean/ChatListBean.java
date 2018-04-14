package com.example.sjj.help4reword.bean;

/**
 * Created by sjj on 2018/4/10.
 */

public class ChatListBean {
    private int image;
    private String name;
    private String says;

    public ChatListBean(){

    }

    public  ChatListBean(int image,String name,String says){
        this.image = image;
        this.name = name;
        this.says = says;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSays() {
        return says;
    }

    public void setSays(String says) {
        this.says = says;
    }
}
