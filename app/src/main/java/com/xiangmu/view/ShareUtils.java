package com.xiangmu.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;

/**
 * Created by 你猜 on 2018/7/2.
 * 嗯，又蛋疼的封装了一个sp存储
 */

public class ShareUtils {

    public static final String uid="uid";

    /**
     * 蛋疼的封装了sp里面的存
     * 存里面的参数：
     * key是唯一的，取值的时候用key来取
     * value是要保存的数据，取的值就是value
     */
    public static void putString(Context context,String key,String value) {
        SharedPreferences sp=context.getSharedPreferences(uid,Context.MODE_PRIVATE);
        sp.edit().putString(key,value).commit();
    }

    /**
     * 蛋疼的封装了sp里面的取
     * 取里面的参数：
     * key是用来取对应的value的，就是存的value
     * splvalue是默认值，当key取不到值得时候就显示这个
     */
    public static String getString(Context context,String key,String splvalue) {
        SharedPreferences sp=context.getSharedPreferences(uid,Context.MODE_PRIVATE);
        return sp.getString(key,splvalue);
    }






    /**
     * 同上
     */
    public static void putBoolean(Context context,String key,boolean value) {
        SharedPreferences sp=context.getSharedPreferences(uid,Context.MODE_PRIVATE);
        sp.edit().putBoolean(key,value).commit();
    }

    /**
     * 同上
     */
    public static boolean getBoolean(Context context,String key,boolean splvalue) {
        SharedPreferences sp=context.getSharedPreferences(uid,Context.MODE_PRIVATE);
        return sp.getBoolean(key,splvalue);
    }


    /**
     * 很是蛋疼的封装了sp里面的单个删
     * 取里面的参数：
     * key是要删除对应的key和value
     * 这是一个一个删的
     */
    public static void deleShare(Context context,String key) {
        SharedPreferences sp=context.getSharedPreferences(uid,Context.MODE_PRIVATE);
        sp.edit().remove(key).commit();
    }

    /**
     * 很是蛋疼的封装了sp里面的全部删
     */
    public static void deleAll(Context context) {
        SharedPreferences sp=context.getSharedPreferences(uid,Context.MODE_PRIVATE);
        sp.edit().clear().commit();
    }



}
