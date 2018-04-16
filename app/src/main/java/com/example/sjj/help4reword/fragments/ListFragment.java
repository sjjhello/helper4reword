package com.example.sjj.help4reword.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.sjj.help4reword.R;
import com.example.sjj.help4reword.adapters.MissionViewAdapter;
import com.example.sjj.help4reword.bean.MissionListBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sjj on 2018/2/18.
 */

public class ListFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private ListView lv_mission;
    private View view;

    public ListFragment(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_list,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        lv_mission = (ListView) getView().findViewById(R.id.lv_mission);
        List<MissionListBean> list_mission = new ArrayList<>();
        MissionViewAdapter missionViewAdapter = new MissionViewAdapter(list_mission,getActivity());
        lv_mission.setAdapter(missionViewAdapter);
        //添加数据
        list_mission.add(new MissionListBean("50.00","图书帮忙借取","广州","2018/4/11"));
        list_mission.add(new MissionListBean("5.00","提供X老师的联系方式","广州","2018/4/11"));
        list_mission.add(new MissionListBean("40.00","请求！帮忙取份论文","广州","2018/4/11"));
        list_mission.add(new MissionListBean("2.00","h5楼下代拿外卖","广州","2018/4/11"));
        list_mission.add(new MissionListBean("36.00","益禾堂代购","广州","2018/4/11"));
        missionViewAdapter.notifyDataSetChanged();
    }

    @Override
    public void onRefresh() {

    }
}
