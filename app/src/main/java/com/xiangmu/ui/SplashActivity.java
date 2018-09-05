package com.xiangmu.ui;
/**
 * 这个..这个..呃，嗯，判断是否第一次进入
 */

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.xiangmu.MainActivity;
import com.xiangmu.R;
import com.xiangmu.view.ShareUtils;
import com.xiangmu.utils.StaticClass;
import com.xiangmu.utils.UtilTools;

public class SplashActivity extends AppCompatActivity {

    TextView tv_splash;

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case StaticClass.HANDLER_SPLASH:
                    if ("0".equals(isFirst())) {
                        Intent intent = new Intent(SplashActivity.this, GuideActivity.class);
                        startActivity(intent);
                    } else {
                            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                    }
                    finish();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initView();
    }

    private void initView() {
        //延时2秒
        handler.sendEmptyMessageDelayed(StaticClass.HANDLER_SPLASH, 1500);
        tv_splash = findViewById(R.id.tv_splash);
        //设置字体
        UtilTools.setFont(this, tv_splash);
    }

    /**
     * 判断是否是第一次进入
     * 这个首先获取sp里面的这个对象的值是0还是1
     * 然后会去判断如果是0那就把这个对象的值赋成1，但返回0
     * 否则返回1
     * 因为0是第一次进入，1是多次进入
     */
    private String isFirst() {
        String isFirst = ShareUtils.getString(this, StaticClass.SHARE_IS_FIRST, "0");
        if ("0".equals(isFirst)) {
            //标记第一次已经进入
            ShareUtils.putString(this, StaticClass.SHARE_IS_FIRST, "1");
            return "0";
        } else {
            return "1";
        }
    }

    //禁止返回键
    @Override
    public void onBackPressed() {
//        super.onBackPressed();
    }
}
