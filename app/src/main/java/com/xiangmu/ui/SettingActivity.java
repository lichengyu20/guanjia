package com.xiangmu.ui;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.xiangmu.R;
import com.xiangmu.utils.PackageInfoUtils;
import com.xiangmu.view.ShareUtils;
import com.xys.libzxing.zxing.activity.CaptureActivity;

import cn.bmob.v3.BmobUser;
import cn.jpush.android.api.JPushInterface;

/**
 * Created by 你猜 on 2018/7/2.
 * 设置类
 */

public class SettingActivity extends BaseActivity implements View.OnClickListener {

    //返回,修改密码,意见反馈,版本更新,百度地图,关于软件,推送开关,扫一扫,我的二维码,清理缓存,,,,,,,,,
    private RelativeLayout setting_fanhui, setting_xiugai, setting_yijian, setting_banben, setting_baiduditu, setting_guanyu, setting_kuaiguan, ll_scan, setting_erweima, setting_huancun;
    private ImageView setting_tuisong;//推送,,,,,
    private TextView setting_banbenhao,setting_daxiao;//版本号,缓存大小,,,,,,
    private final int CLEAN_SUC=1001;
    private final int CLEAN_FAIL=1002;
    private String ben,iii="1.0",s;//当前版本号,服务器版本号,缓存大小,,,,,,,,

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ben = PackageInfoUtils.getPackageVersion(this);
//        控件实例化
        initView();

//        计算缓存大小
        try {
            s=Cache.getTotalCacheSize(this);
            //判断缓存大小是否包含0，如果包含就无缓存，如果不包含那就清理
            if(s.indexOf("0")!=-1) {
                setting_daxiao.setText("无需清理");
            }else{
                setting_daxiao.setText(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

//        判断是true还是false并添加相应的图片
        if (ShareUtils.getBoolean(this, "tuisong", true) == true) {
            Picasso.with(SettingActivity.this).load(R.drawable.on).into(setting_tuisong);
        } else {
            Picasso.with(SettingActivity.this).load(R.drawable.off).into(setting_tuisong);
        }

//        判断用户是否登录,如果没登录那就把修改密码注掉
        BmobUser myUser=BmobUser.getCurrentUser();
        if(myUser==null) {
            setting_xiugai.setVisibility(View.GONE);
        }else{
            setting_xiugai.setVisibility(View.VISIBLE);
        }

        initBan();
    }


    private void initView() {
        setting_daxiao=findViewById(R.id.setting_daxiao);

        setting_banbenhao = findViewById(R.id.setting_banbenhao);
        setting_fanhui = findViewById(R.id.setting_fanhui);
        setting_xiugai = findViewById(R.id.setting_xiugai);
        setting_yijian = findViewById(R.id.setting_yijian);
        setting_banben = findViewById(R.id.setting_banben);
        setting_tuisong = findViewById(R.id.setting_tuisong);
        setting_baiduditu = findViewById(R.id.setting_baiduditu);
        setting_guanyu = findViewById(R.id.setting_guanyu);
        setting_kuaiguan = findViewById(R.id.setting_kuaiguan);
        ll_scan = findViewById(R.id.ll_scan);
        setting_erweima = findViewById(R.id.setting_erweima);
        setting_huancun = findViewById(R.id.setting_huancun);

        setting_banbenhao.setOnClickListener(this);
        setting_fanhui.setOnClickListener(this);
        setting_xiugai.setOnClickListener(this);
        setting_yijian.setOnClickListener(this);
        setting_banben.setOnClickListener(this);
        setting_tuisong.setOnClickListener(this);
        setting_baiduditu.setOnClickListener(this);
        setting_guanyu.setOnClickListener(this);
        setting_kuaiguan.setOnClickListener(this);
        ll_scan.setOnClickListener(this);
        setting_erweima.setOnClickListener(this);
        setting_huancun.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            //返回
            case R.id.setting_fanhui:
                finish();
                break;
            //修改密码
            case R.id.setting_xiugai:
                Intent intent = new Intent(this, XiuGaiMiMa.class);
                startActivity(intent);
                break;
            //意见反馈
            case R.id.setting_yijian:
                Intent intent1 = new Intent(this, FanKui.class);
                startActivity(intent1);
                break;
            //版本更新
            case R.id.setting_banben:
                break;
            //推送通知
            case R.id.setting_tuisong:
                initData();
                break;
            //百度地图
            case R.id.setting_baiduditu:
                startActivity(new Intent(this, LocationActivity.class));
//                Toast.makeText(this, "正在努力加载中...", Toast.LENGTH_SHORT).show();
                break;
            //关于软件
            case R.id.setting_guanyu:
                Intent intent2 = new Intent(this, GuanYu.class);
                startActivity(intent2);
                break;
            //推送开关
            case R.id.setting_kuaiguan:
                break;
            //扫一扫
            case R.id.ll_scan:
                Intent intent3 = new Intent(this, CaptureActivity.class);
                startActivityForResult(intent3, 0);
                break;
            //我的二维码
            case R.id.setting_erweima:
                Intent intent4 = new Intent(this, ErWeiMa.class);
                startActivity(intent4);
                break;
            //清理缓存
            case R.id.setting_huancun:
                //判断缓存大小是否包含0，如果包含就无缓存，如果不包含那就清理
                if(s.indexOf("0")!=-1) {
                    Toast.makeText(this, "无需清理", Toast.LENGTH_SHORT).show();
                }else{
                    if(setting_daxiao.getText().toString().equals("无需清理")) {
                        Toast.makeText(this, "无需清理", Toast.LENGTH_SHORT).show();
                    }else{
                        onClickCleanCache();
                    }
                }
                break;
            //检查是否是最新版本
            case R.id.setting_banbenhao:
                initbanben();
                break;

        }
    }

    //    推送开关按钮
    private void initData() {
        if (ShareUtils.getBoolean(this, "tuisong", true) == true) {
            Picasso.with(SettingActivity.this).load(R.drawable.off).into(setting_tuisong);
            ShareUtils.putBoolean(SettingActivity.this, "tuisong", false);
            JPushInterface.stopPush(SettingActivity.this);
        } else {
            Picasso.with(SettingActivity.this).load(R.drawable.on).into(setting_tuisong);
            ShareUtils.putBoolean(SettingActivity.this, "tuisong", true);
            JPushInterface.resumePush(SettingActivity.this);
        }
    }

    //这个是可供用户点击提示版本的
    private void initbanben() {
        //判断版本号是否一致
        if (TextUtils.equals(ben, iii)) {//当前版本号以及服务器返回的版本号
            //一致
            Toast.makeText(this, "已经是最新版本啦", Toast.LENGTH_SHORT).show();
        } else {
            //不一致
            Toast.makeText(this, "是否更新到最新版本", Toast.LENGTH_SHORT).show();
        }
    }


    //初始化的版本
    private void initBan() {
        //判断版本号是否一致
        if (TextUtils.equals(ben, iii)) {//当前版本号以及服务器返回的版本号
            //一致
            setting_banbenhao.setText("已是最新版本");
        } else {
            //不一致
            setting_banbenhao.setText("请更新到最新版本");
        }
    }


    //扫一扫
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Bundle bundle = data.getExtras();
            //扫描结果
            String name = bundle.getString("result");
        }
    }





    //清理缓存
    private void onClickCleanCache() {
        getConfirmDialog(this, "是否清空缓存?", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                clearAppCache();
                setting_daxiao.setText("无需清理");
            }
        }).show();
    }

    public static AlertDialog.Builder getConfirmDialog(Context context, String message, DialogInterface.OnClickListener onClickListener) {
        AlertDialog.Builder builder = getDialog(context);
        builder.setMessage(Html.fromHtml(message));
        builder.setPositiveButton("确定", onClickListener);
        builder.setNegativeButton("取消", null);
        return builder;
    }

    public static AlertDialog.Builder getDialog(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        return builder;
    }

    /**
     * 清除app缓存
     */
    public void clearAppCache() {
        new Thread() {
            @Override
            public void run() {
                //清理缓存
                new Cache().clearAllCache(SettingActivity.this);
            }
        }.start();
    }
    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case CLEAN_FAIL:
                    Toast.makeText(SettingActivity.this, "清除失败", Toast.LENGTH_SHORT).show();
                    break;
                case CLEAN_SUC:
                    Toast.makeText(SettingActivity.this, "清除成功", Toast.LENGTH_SHORT).show();
                    break;
            }
        };
    };




}
