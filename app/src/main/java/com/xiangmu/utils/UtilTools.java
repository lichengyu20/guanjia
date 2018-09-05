package com.xiangmu.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.TextView;

/**
 * Created by 你猜 on 2018/7/2.
 * 就是个工具类
 */

public class UtilTools {

    //设置字体
    public static void setFont(Context context, TextView text) {
        Typeface fontType=Typeface.createFromAsset(context.getAssets(),"fonts/HKWWT.TTF");
        text.setTypeface(fontType);
    }

}
