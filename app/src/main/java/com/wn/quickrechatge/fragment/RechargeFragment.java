package com.wn.quickrechatge.fragment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.Poi;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.wn.quickrechatge.R;
import com.wn.quickrechatge.base.BaseFragment;

import org.xutils.common.util.LogUtil;

import java.util.List;

/**
 * 充电
 */
public class RechargeFragment extends BaseFragment {
    private View mainView;
    private MapView mapView;
    public LocationClient locationClient;
    public BDLocationListener myListener = new MyLocationListener();
    private BaiduMap mBaidumap ;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        SDKInitializer.initialize(getActivity().getApplicationContext());

        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mainView = inflater.inflate(R.layout.fragment_recharge,container,false);
        return mainView;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mapView = (MapView) mainView.findViewById(R.id.mapView);
        locationClient = new LocationClient(getActivity().getApplicationContext());
        locationClient.registerLocationListener(myListener);
        startLocation();
        mBaidumap = mapView.getMap();
        initLocation();
    }

    public void startLocation(){
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED){
            locationClient.start();
        }

    }

    private void initLocation(){
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        //可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setCoorType("bd09ll");
        //可选，默认gcj02，设置返回的定位结果坐标系
        int span=0;
        option.setScanSpan(span);
        //可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        option.setIsNeedAddress(true);
        //可选，设置是否需要地址信息，默认不需要
        option.setOpenGps(true);
        //可选，默认false,设置是否使用gps
        option.setLocationNotify(true);
        //可选，默认false，设置是否当GPS有效时按照1S/1次频率输出GPS结果
        option.setIsNeedLocationDescribe(true);
        //可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        option.setIsNeedLocationPoiList(true);
        //可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        option.setIgnoreKillProcess(false);
        //可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        option.SetIgnoreCacheException(false);
        //可选，默认false，设置是否收集CRASH信息，默认收集
        option.setEnableSimulateGps(false);
        //可选，默认false，设置是否需要过滤GPS仿真结果，默认需要
        locationClient.setLocOption(option);
    }

    public class MyLocationListener implements BDLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {
            //获取定位结果
            StringBuffer sb = new StringBuffer(256);

            sb.append(location.getLatitude()+"\n");    //获取纬度信息
            sb.append(location.getLongitude());    //获取纬度信息
            double latitude = location.getLatitude();
            double longitude = location.getLongitude();

            //定义Maker坐标点
            LatLng point = new LatLng(latitude, longitude);

            MapStatus mMapStatus = new MapStatus.Builder()
                    .target(point)
                    .zoom(13)
                    .build();
            //定义MapStatusUpdate对象，以便描述地图状态将要发生的变化
            MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mMapStatus);
            //改变地图状态
            mBaidumap.setMapStatus(mMapStatusUpdate);

            //构建Marker图标
            marker(point,R.drawable.icon_openmap_mark);
            marker(new LatLng(22.5616,113.951462),R.drawable.icon_openmap_focuse_mark);
            marker(new LatLng(22.5139,113.952736),R.drawable.icon_openmap_focuse_mark);
            marker(new LatLng(22.5271,113.955156),R.drawable.icon_openmap_focuse_mark);
            marker(new LatLng(22.5853,113.955866),R.drawable.icon_openmap_focuse_mark);

            if (location.getLocType() == BDLocation.TypeGpsLocation){
                sb.append(location.getAddrStr());    //获取地址信息
            } else if (location.getLocType() == BDLocation.TypeNetWorkLocation){
                // 网络定位结果
                sb.append(location.getAddrStr());    //获取地址信息
            }
            //定位失败
            else{
            }
            sb.append(location.getLocationDescribe());    //位置语义化信息
            LogUtil.d(sb.toString());
        }

        @Override
        public void onConnectHotSpotMessage(String s, int i) {
        }
    }
    private void marker(LatLng point,int pic) {
        BitmapDescriptor bitmap = BitmapDescriptorFactory
                .fromResource(pic);
        //构建MarkerOption，用于在地图上添加Marker
        OverlayOptions option = new MarkerOptions()
                .position(point)
                .icon(bitmap);
        //在地图上添加Marker，并显示
        mapView.getMap().addOverlay(option);
    }
    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }
}
