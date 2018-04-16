package com.example.sjj.help4reword.fragments;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.MyLocationStyle;
import com.example.sjj.help4reword.R;
import com.example.sjj.help4reword.activies.MainActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by sjj on 2018/2/18.
 */

public class MapFragment extends Fragment implements LocationSource,AMapLocationListener{

    private View view;
    private MapView mapView = null;
    private AMap aMap;

    private OnLocationChangedListener mListener;
    private AMapLocationClient mlocationClient;
    private AMapLocationClientOption mLocationOption;
    private String location;


    public MapFragment(){

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_map, container, false);
        return view;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mapView= (MapView) view.findViewById(R.id.mapView);
        if(mapView != null){
            mapView.onCreate(savedInstanceState);
            aMap = mapView.getMap();
        }
        initLocationService();

    }


    private void initLocationService() {
        if (aMap != null) {
            MyLocationStyle myLocationStyle = new MyLocationStyle();//初始化定位蓝点样式类
            myLocationStyle.interval(5000); //设置连续定位模式下的定位间隔，只在连续定位模式下生效，单次定位模式下不会生效。单位为毫秒。
            myLocationStyle.showMyLocation(true);
            //myLocationStyle.strokeColor(Color.argb(0, 0, 0, 0));
            myLocationStyle.radiusFillColor(Color.argb(0, 0, 0, 0));
            myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_FOLLOW) ;
            aMap.setMyLocationStyle(myLocationStyle);//设置定位蓝点的Style
            aMap.getUiSettings().setMyLocationButtonEnabled(true);//设置默认定位按钮是否显示，非必需设置。
            aMap.setMyLocationEnabled(true);
            aMap.moveCamera(CameraUpdateFactory.zoomTo(16));//地图初始比例

        }
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
        deactivate();
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
        if(null != mlocationClient){
            mlocationClient.onDestroy();
        }
    }


    @Override
    public void onLocationChanged(AMapLocation amaplocation) {
        if (amaplocation != null && mListener != null) {
            if (amaplocation != null && amaplocation.getErrorCode() == 0) {
                mListener.onLocationChanged(amaplocation);

                amaplocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见官方定位类型表
                amaplocation.getLatitude();//获取纬度
                amaplocation.getLongitude();//获取经度
                amaplocation.getAccuracy();//获取精度信息
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = new Date(amaplocation.getTime());
                df.format(date);//定位时间
                amaplocation.getAddress();//地址，如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息。
                amaplocation.getCountry();//国家信息
                amaplocation.getProvince();//省信息
                amaplocation.getCity();//城市信息
                amaplocation.getDistrict();//城区信息
                amaplocation.getStreet();//街道信息
                amaplocation.getStreetNum();//街道门牌号信息
                amaplocation.getCityCode();//城市编码
                amaplocation.getAdCode();//地区编码

                location = amaplocation.getAddress();
                Intent intentLocation = new Intent(this.getActivity(), MainActivity.class);
                intentLocation.putExtra("location",location);
                startActivity(intentLocation);
            }
            else {
                location = "定位失败";
                String errText = "定位失败，" + amaplocation.getErrorCode()+ ": "
                        + amaplocation.getErrorInfo();
                Log.e("error",errText);
            }

        }
    }

    @Override
    public void activate(OnLocationChangedListener onLocationChangedListener) {

        mListener = onLocationChangedListener;
        if (mlocationClient == null) {
            mlocationClient = new AMapLocationClient(getActivity().getApplicationContext());
            mLocationOption = new AMapLocationClientOption();
            mlocationClient.setLocationListener(this);
            //获取一次定位结果
            mLocationOption.setOnceLocation(true);
            //设置是否返回地址信息（默认返回地址信息）
            mLocationOption.setNeedAddress(true);
            mLocationOption.setInterval(2000);
            mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
            mlocationClient.setLocationOption(mLocationOption);
            mlocationClient.startLocation();
        }
    }

    @Override
    public void deactivate() {
        mListener = null;
        if (mlocationClient != null) {
            mlocationClient.stopLocation();
            mlocationClient.onDestroy();
        }
        mlocationClient = null;
        mLocationOption = null;
    }




}
