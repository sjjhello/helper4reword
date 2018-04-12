package com.example.sjj.help4reword.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.sjj.help4reword.Bean.MissionListBean;
import com.example.sjj.help4reword.R;
import com.example.sjj.help4reword.adapters.MissionViewAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sjj on 2018/2/18.
 */

public class ListFragment extends Fragment implements AdapterView.OnItemClickListener{

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
        for(int i=1;i<20;i++){
            list_mission.add(new MissionListBean("50.00","任务"+i,"广州","2018/4/11"));
        }
        missionViewAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


    }
}
