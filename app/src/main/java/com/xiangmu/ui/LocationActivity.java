package com.xiangmu.ui;
/**
 * 地图类
 */

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.Poi;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.xiangmu.R;

import java.util.List;

public class LocationActivity extends AppCompatActivity implements View.OnClickListener{

    private MapView mMapView = null;
    private TextView ditu_text1;
    private BaiduMap baiduMap;
    public LocationClient mLocationClient = null;
    private MyLocationListener myListener = new MyLocationListener();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        initView();
    }


    private void initView() {
        mMapView = findViewById(R.id.mMapView);
        baiduMap=mMapView.getMap();
        ditu_text1=findViewById(R.id.ditu_text1);
        ditu_text1.setOnClickListener(this);
        mLocationClient = new LocationClient(getApplicationContext());
        //声明LocationClient类
        mLocationClient.registerLocationListener(myListener);
        //注册监听函数

        initLocation();
        mLocationClient.start();
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ditu_text1:
                showPopueWindow();
                break;
        }
    }



    private void initLocation() {
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        option.setCoorType("bd09ll");
        //这个是你要定位多少次
        option.setScanSpan(0);
        option.setOpenGps(true);
        option.setLocationNotify(true);
        option.setIgnoreKillProcess(false);
        option.SetIgnoreCacheException(false);
        option.setWifiCacheTimeOut(5*60*1000);
        option.setEnableSimulateGps(false);
        option.setIsNeedAddress(true);
        option.setIsNeedLocationPoiList(true);



        mLocationClient.setLocOption(option);





    }



    private void showPopueWindow() {
        View popView = View.inflate(this, R.layout.popupwindow_ditu_need, null);
        Button putong = popView.findViewById(R.id.putong);
        Button weixing = popView.findViewById(R.id.weixing);
        Button kongbai = popView.findViewById(R.id.kongbai);
        //获取屏幕宽高
        int weight = getResources().getDisplayMetrics().widthPixels;
        int height = getResources().getDisplayMetrics().heightPixels * 1 / 3;

        final PopupWindow popupWindow = new PopupWindow(popView, weight, height);
        popupWindow.setFocusable(true);
        //点击外部popueWindow消失
        popupWindow.setOutsideTouchable(true);

        //调用系统相册
        putong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
                tuceng(1);
            }
        });

        weixing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
                tuceng(2);
            }
        });

        kongbai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
                tuceng(3);
            }
        });


        //popupWindow消失屏幕变为不透明
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 1.0f;
                getWindow().setAttributes(lp);
            }
        });
        //popupWindow出现屏幕变为半透明
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.5f;
        getWindow().setAttributes(lp);
        popupWindow.showAtLocation(popView, Gravity.BOTTOM, 0, 50);

    }



    private void tuceng(int i) {
        if(i==1) {
            baiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
        }else if(i==2) {
            baiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);
        }else if(i==3) {
            baiduMap.setMapType(BaiduMap.MAP_TYPE_NONE);
        }
    }





    public class MyLocationListener extends BDAbstractLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location){
            double latitude = location.getLatitude();    //获取纬度信息
            double longitude = location.getLongitude();    //获取经度信息
            float radius = location.getRadius();    //获取定位精度，默认值为0.0f
            String coorType = location.getCoorType();//获取经纬度坐标类型，以LocationClientOption中设置过的坐标类型为准
            int errorCode = location.getLocType();//获取定位类型、定位错误返回码，具体信息可参照类参考中BDLocation类中的说明
            String addr = location.getAddrStr();    //获取详细地址信息
            String country = location.getCountry();    //获取国家
            String province = location.getProvince();    //获取省份
            String city = location.getCity();    //获取城市
            String district = location.getDistrict();    //获取区县
            String street = location.getStreet();    //获取街道信息
            String locationDescribe = location.getLocationDescribe();    //获取位置描述信息
            List<Poi> poiList = location.getPoiList();//获取周边POI信息

            //移动到当前位置
            MapStatusUpdate mapStatusUpdate= MapStatusUpdateFactory.zoomTo(18);
            baiduMap.setMapStatus(mapStatusUpdate);

            //开始移动
            MapStatusUpdate mapStatusUpdate1=MapStatusUpdateFactory.newLatLng(new LatLng(latitude,longitude));
            baiduMap.setMapStatus(mapStatusUpdate1);



            //1.定义marker的坐标点LatLng
            LatLng point = new LatLng(latitude,longitude);//北京区域
            //2.定义该坐标点的位图
            BitmapDescriptor bitmap = BitmapDescriptorFactory.fromResource(R.drawable.baidudingwei);
            //3.构建MarkOption,用于集合坐标点和图标成完成的Marker，以在地图上添加Marker
            OverlayOptions options = new MarkerOptions()
                    .position(point)  //设置marker的位置
                    .icon(bitmap) ;    //设置marker图标

            //4.在地图上添加Marker
            baiduMap.addOverlay(options);

        }
    }






    @Override
    protected void onStart() {
        super.onStart();
//        initView();
        initLocation();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mMapView.onPause();
    }








}