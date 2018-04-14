package com.example.sjj.help4reword.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.MyLocationStyle;
import com.example.sjj.help4reword.R;

/**
 * Created by sjj on 2018/2/18.
 */

public class MapFragment extends Fragment implements LocationSource,AMapLocationListener{

    private View view;
    private MapView mapView = null;
    private AMap aMap;
    private OnLocationChangedListener mListener;
    private MyLocationStyle myLocationStyle;
    private AMapLocationClient locationClient;
    private AMapLocationClientOption clientOption;

    public static MapFragment newInstance(){
        MapFragment mapFragment = new MapFragment();
        return  mapFragment;
    }

    public MapFragment(){

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        initLocation();
        view = inflater.inflate(R.layout.fragment_map, container, false);
        initView(savedInstanceState,view);
        mapView.onCreate(savedInstanceState);
        return view;
    }

    private void initLocation(){
        //初始化定位
        locationClient = new AMapLocationClient(this.getActivity().getApplicationContext());
//初始化AMapLocationClientOption对象
        clientOption = new AMapLocationClientOption();
//设置定位模式为高精度模式。
        clientOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
//设置定位回调监听
        locationClient.setLocationListener(this);
//获取一次定位结果
        clientOption.setOnceLocation(true);
//设置是否返回地址信息（默认返回地址信息）
        clientOption.setNeedAddress(true);
//给定位客户端对象设置定位参数
        locationClient.setLocationOption(clientOption);
//启动定位
        locationClient.startLocation();

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

    private void initView(Bundle savedInstanceState,View view){
        mapView= (MapView) view.findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        if (aMap==null)
        {
            aMap=mapView.getMap();
        }
        myLocationStyle = new MyLocationStyle();//初始化定位蓝点样式类
        aMap.setMyLocationStyle(myLocationStyle);
        aMap.getUiSettings().setMyLocationButtonEnabled(true);
        aMap.setLocationSource(this);
        aMap.setMyLocationEnabled(true);
        aMap.setMapType(AMap.MAP_TYPE_NORMAL);
    }




    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (mListener != null && aMapLocation != null) {
            if (aMapLocation != null && aMapLocation.getErrorCode() == 0) {
                aMapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见官方定位类型表
                aMapLocation.getLatitude();//获取纬度
                aMapLocation.getLongitude();//获取经度
                aMapLocation.getAccuracy();//获取精度信息
                aMapLocation.getCountry();//国家信息
                aMapLocation.getProvince();//省信息
                aMapLocation.getCity();//城市信息
                aMapLocation.getDistrict();//城区信息
                aMapLocation.getStreet();//街道信息
                aMapLocation.getStreetNum();//街道门牌号信息
                aMapLocation.getCityCode();//城市编码
                aMapLocation.getAdCode();//地区编码
                mListener.onLocationChanged(aMapLocation);
            } else {
                String errText = "定位失败," + aMapLocation.getErrorCode()+ ": " + aMapLocation.getErrorInfo();
                Log.e("AmapErr",errText);
                Toast.makeText(this.getActivity().getApplicationContext(), "定位失败", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void activate(OnLocationChangedListener onLocationChangedListener) {
        mListener = onLocationChangedListener;
        if(locationClient==null){
            locationClient=new AMapLocationClient(getActivity());
            clientOption=new AMapLocationClientOption();
            locationClient.setLocationListener(this);
            clientOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);//高精度定位
            clientOption.setOnceLocationLatest(true);//设置单次精确定位
            locationClient.setLocationOption(clientOption);
            locationClient.startLocation();
        }
    }

    @Override
    public void deactivate() {
        mListener=null;
        if(locationClient!=null){
            locationClient.stopLocation();
            locationClient.onDestroy();
        }
        locationClient=null;
    }
}
