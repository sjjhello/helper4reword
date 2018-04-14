package com.example.sjj.help4reword.bean;

/**
 * http请求返回最外层对象
 * Created by sjj on 2018/4/14.
 */

public class DataBean<T> {
    private int icode;
    private String msg;
    private T data;

    public int getIcode() {
        return icode;
    }

    public void setIcode(int icode) {
        this.icode = icode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
