package com.example.sjj.help4reword.bean;

/**
 * Created by sjj on 2018/4/10.
 */

public class MissionListBean {
    private String title;
    private String context;
    private String reword;
    private String time;
    private String location;


    public MissionListBean(){

    }
    public MissionListBean(String reword, String title, String location, String time){
        this.reword = reword;
        this.title = title;
        this.location = location;
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public void setReword(String reword) {
        this.reword = reword;
    }

    public String getReword() {
        return reword;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
