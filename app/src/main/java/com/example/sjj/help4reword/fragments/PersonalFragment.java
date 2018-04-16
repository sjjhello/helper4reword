package com.example.sjj.help4reword.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.sjj.help4reword.R;
import com.example.sjj.help4reword.activies.MissionDetailActivity;

/**
 * Created by sjj on 2018/2/18.
 */

public class PersonalFragment extends Fragment {
    private View view;
    private LinearLayout setup_mission;
    private LinearLayout accept_mission;
    private LinearLayout myInfo;
    private LinearLayout setting;
    private LinearLayout layout;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_personal,null);
        myInfo = (LinearLayout) view.findViewById(R.id.myInfo);
        accept_mission = (LinearLayout) view.findViewById(R.id.accept_mission);
        setup_mission = (LinearLayout) view.findViewById(R.id.setup_mission);
        setting = (LinearLayout) view.findViewById(R.id.setting);
        layout = (LinearLayout) view.findViewById(R.id.layout);


        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        addListeners();
    }

    private void addListeners() {
        setup_mission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), MissionDetailActivity.class));
            }
        });
    }


}
