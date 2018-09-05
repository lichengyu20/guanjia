package com.xiangmu.application;

import android.app.Application;
import android.os.Bundle;

import com.baidu.mapapi.SDKInitializer;
import com.tencent.bugly.crashreport.CrashReport;
import com.xiangmu.utils.StaticClass;

import cn.bmob.v3.Bmob;

/**
 * Created by 你猜 on 2018/7/2.
 * 蛋疼的初始化工具类
 */

public class BaseApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        //初始化bugly
        CrashReport.initCrashReport(getApplicationContext(), StaticClass.BUGLY_APP_ID, false);
        //初始化bomb
        Bmob.initialize(this, StaticClass.BOMB_APP_ID);
        //初始化百度地图
        SDKInitializer.initialize(getApplicationContext());

        if("".equals("")) {

        }

    }








}
