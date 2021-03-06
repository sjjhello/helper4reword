package com.example.sjj.help4reword;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 

import java.io.Serializable;

/**
 * Entity mapped to table "MISSION_INFO".
 */
public class MissionInfo implements Serializable{

    private Long id;
    /** Not-null value. */
    private String title;
    /** Not-null value. */
    private String reword;
    /** Not-null value. */
    private String context;
    private String location;
    private String deadline;
    private Integer user_id;

    public MissionInfo() {
    }

    public MissionInfo(Long id) {
        this.id = id;
    }

    public MissionInfo(Long id, String title, String reword, String context, String location, String deadline, Integer user_id) {
        this.id = id;
        this.title = title;
        this.reword = reword;
        this.context = context;
        this.location = location;
        this.deadline = deadline;
        this.user_id = user_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /** Not-null value. */
    public String getTitle() {
        return title;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setTitle(String title) {
        this.title = title;
    }

    /** Not-null value. */
    public String getReword() {
        return reword;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setReword(String reword) {
        this.reword = reword;
    }

    /** Not-null value. */
    public String getContext() {
        return context;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setContext(String context) {
        this.context = context;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

}
