package com.xiangmu;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.MapView;
import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.tencent.bugly.crashreport.CrashReport;
import com.xiangmu.fragment.ButlerFragment;
import com.xiangmu.fragment.GirlFragment;
import com.xiangmu.fragment.UserFragment;
import com.xiangmu.fragment.WechatFragment;
import com.xiangmu.ui.BaseActivity;
import com.xiangmu.ui.LocationActivity;
import com.xiangmu.ui.SettingActivity;
import com.xys.libzxing.zxing.activity.CaptureActivity;

import java.util.ArrayList;
import java.util.List;

import cn.jpush.android.api.JPushInterface;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private List<String> mTitle;
    private List<Fragment> mFragment;
    private long firstTime = 0;

    //悬浮窗
    private FloatingActionButton fab_setting;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        //判断版本是否支持沉浸式状态栏
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            //透明状态栏
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            //透明导航栏
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//        }
















        fab_setting = findViewById(R.id.fab_setting);
        fab_setting.setOnClickListener(this);
        //默认隐藏悬浮窗
        fab_setting.setVisibility(View.GONE);
        initData();
        initView();
    }


//    @Override
//    public void onWindowFocusChanged(boolean hasFocus) {
//        super.onWindowFocusChanged(hasFocus);
//        if (hasFocus && Build.VERSION.SDK_INT >= 19) {
//            View decorView = getWindow().getDecorView();
//            decorView.setSystemUiVisibility(
//                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
//                            | View.SYSTEM_UI_FLAG_FULLSCREEN
//                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
//        }
//    }


    private void initData() {
        mTitle = new ArrayList<>();
        mTitle.add("微信精选");
        mTitle.add("正在努力");
        mTitle.add("加载中..");
        mTitle.add("个人中心");
        mFragment = new ArrayList<>();
        mFragment.add(new ButlerFragment());
        mFragment.add(new WechatFragment());
        mFragment.add(new GirlFragment());
        mFragment.add(new UserFragment());
    }

    private void initView() {
        mTabLayout = (TabLayout) findViewById(R.id.mTabLayout);
        mViewPager = (ViewPager) findViewById(R.id.mViewPager);
        //预加载
        mViewPager.setOffscreenPageLimit(mFragment.size());


        //viewpager滑动监听
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @SuppressLint("RestrictedApi")
            @Override
            public void onPageSelected(int position) {

                if(position==3) {
                     fab_setting.setVisibility(View.VISIBLE);
                }else{
                    fab_setting.setVisibility(View.GONE);
                }



//                if (position == 0) {
//                    //隐藏悬浮窗
//                    fab_setting.setVisibility(View.GONE);
//                } else if (position == 3) {
//                    //隐藏悬浮窗
//                    fab_setting.setVisibility(View.GONE);
//                } else {
//                    //显示悬浮窗
//                    fab_setting.setVisibility(View.VISIBLE);
//                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        //加载适配器
        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {

            //选中的item
            @Override
            public Fragment getItem(int position) {
                return mFragment.get(position);
            }

            //返回item的个数
            @Override
            public int getCount() {
                return mFragment.size();
            }


            //设置标题
            @Override
            public CharSequence getPageTitle(int position) {
                return mTitle.get(position);
            }
        });

        //绑定viewpager
        mTabLayout.setupWithViewPager(mViewPager);


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab_setting:
//                Toast.makeText(this, "正在努力加载中......", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(this, SettingActivity.class);
                startActivity(i);
                break;
        }
    }

//    点击两次退出程序
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        long secondTime = System.currentTimeMillis();
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ( secondTime - firstTime < 2000) {
                System.exit(0);
            } else {
                Toast.makeText(MainActivity.this, "再按一次退出小管家", Toast.LENGTH_SHORT).show();
                firstTime = System.currentTimeMillis();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
