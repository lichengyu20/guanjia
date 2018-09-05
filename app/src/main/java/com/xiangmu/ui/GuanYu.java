package com.xiangmu.ui;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.xiangmu.R;
import com.xiangmu.utils.PackageInfoUtils;
import com.xiangmu.view.L;
import com.xiangmu.view.ShareUtils;
import com.xiangmu.utils.StringBitmap;

public class GuanYu extends AppCompatActivity {

    private TextView guanyu_banben,text1;
    private ImageView guanyu_image;
    private RelativeLayout guanyu_fanhui;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guan_yu);
        initView();
    }

    private void initView() {
        //获取版本号
        String ban=PackageInfoUtils.getPackageVersion(this);
        guanyu_banben=findViewById(R.id.guanyu_banben);
        guanyu_banben.setText("版本号:"+ban);
        //添加头像
        guanyu_image=findViewById(R.id.guanyu_image);
        String img=ShareUtils.getString(this,"photo","");
        if(TextUtils.isEmpty(img)) {
            Picasso.with(this).load(R.drawable.icon).into(guanyu_image);
        }else{
            Bitmap bm= StringBitmap.convertStringToIcon(img);
            guanyu_image.setImageBitmap(bm);
        }
        //返回按钮
        guanyu_fanhui=findViewById(R.id.guanyu_fanhui);
        guanyu_fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //获取当前应用名称也就是app名称
        String applicationName = getApplicationName();
        text1=findViewById(R.id.text1);
        text1.setText("应用名："+applicationName);
    }


    //获取当前应用名称也就是app名称
    public String getApplicationName() {
        PackageManager packageManager = null;
        ApplicationInfo applicationInfo = null;
        try {
            packageManager = getApplicationContext().getPackageManager();
            applicationInfo = packageManager.getApplicationInfo(getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            applicationInfo = null;
        }
        String applicationName =
                (String) packageManager.getApplicationLabel(applicationInfo);
        return applicationName;
    }



}
