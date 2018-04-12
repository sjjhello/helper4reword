package com.example.sjj.help4reword.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;
import com.example.sjj.help4reword.R;

/**
 * Created by sjj on 2018/2/18.
 */

public class MapFragment extends Fragment {

    private View view;
    private MapView mapView = null;
    private AMap aMap;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_map, container, false);
        mapView = (MapView) view.findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        if (aMap == null) {
            aMap = mapView.getMap();
        }

        return view;
    }


    /**
     * 必须重写以下方法
     */
    @Override
    public void onResume(){
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }


    /*private void initLocation(){
        //初始化定位
        mLocationClient = new AMapLocationClient(getActivity());
//初始化AMapLocationClientOption对象
        mLocationOption = new AMapLocationClientOption();
//设置定位模式为高精度模式。
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
//设置定位回调监听
        mLocationClient.setLocationListener(this);
//获取一次定位结果
        mLocationOption.setOnceLocation(true);
//设置是否返回地址信息（默认返回地址信息）
        mLocationOption.setNeedAddress(true);
//给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
//启动定位
        mLocationClient.startLocation();
    }*/


}
