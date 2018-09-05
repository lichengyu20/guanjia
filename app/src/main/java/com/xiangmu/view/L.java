package com.xiangmu.view;

import android.util.Log;

/**
 * Created by 你猜 on 2018/7/2.
 * 很是蛋疼的封装了一个log类，不知道有啥用,看着好牛掰，实际没啥卵用
 */

public class L {
    public static final boolean DEBUG=true;
    public static final String TAG="ABCDEFGHIJKL";

    public static void d(String text) {
        if(DEBUG) {
            Log.d(TAG,text);
        }
    }

    public static void i(String text) {
        if(DEBUG) {
            Log.i(TAG,text);
        }
    }

    public static void w(String text) {
        if(DEBUG) {
            Log.w(TAG,text);
        }
    }

    public static void e(String text) {
        if(DEBUG) {
            Log.e(TAG,text);
        }
    }




}
