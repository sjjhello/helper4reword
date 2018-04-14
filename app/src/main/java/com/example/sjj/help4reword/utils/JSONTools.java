package com.example.sjj.help4reword.utils;

import com.example.sjj.help4reword.bean.MissionListBean;
import com.example.sjj.help4reword.bean.UserBean;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 从服务端请求获得的JSON数据的解析成指定的对象
 * Created by sjj on 2018/4/14.
 */

public class JSONTools {
    public JSONTools(){}

    /**
     *
     * @param key 对应字段
     * @param jsonString 返回的json数据
     * @return
     */
    public static UserBean getUser(String key,String jsonString){
        UserBean userBean = new UserBean();
        try{
            JSONObject jsonObject = new JSONObject(jsonString);
            JSONObject userObject = jsonObject.getJSONObject(key);
            userBean.setCode(userObject.getInt("code"));
            userBean.setToken(userObject.getString("token"));
        }catch (Exception e){
            e.printStackTrace();
        }
        return userBean;
    }


    public static List<MissionListBean> getMissionList(String key,String jsonString){
        List<MissionListBean> list = new ArrayList<MissionListBean>();
        try{
            JSONObject jsonObject = new JSONObject(jsonString);
            JSONArray jsonArray = jsonObject.getJSONArray(key);
            for(int i = 0;i < jsonArray.length(); i++){
                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                MissionListBean missionListBean = new MissionListBean();
                missionListBean.setReword(jsonObject1.getString("reword"));
                missionListBean.setTitle(jsonObject1.getString("title"));
                missionListBean.setContext(jsonObject1.getString("context"));
                missionListBean.setLocation(jsonObject1.getString("location"));
                missionListBean.setTime(jsonObject1.getString("date"));
                list.add(missionListBean);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }



}
