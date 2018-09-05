package com.xiangmu.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * 包信息工具类
 */

public class PackageInfoUtils {
    public static String getPackageVersion(Context context) {
        try {
            PackageInfo packInfo=context.getPackageManager().getPackageInfo(context.getPackageName(),0);
            String version=packInfo.versionName;
            return version;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "没有";
        }
    }
}
