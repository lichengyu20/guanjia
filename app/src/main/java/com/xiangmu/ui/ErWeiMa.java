package com.xiangmu.ui;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.xiangmu.R;
import com.xys.libzxing.zxing.encoding.EncodingUtils;

public class ErWeiMa extends BaseActivity {

    private ImageView er_img;
    private RelativeLayout erweima_fanhui;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_erweima);

        String name="智能管家服务,嗯,啥都没";

        er_img=findViewById(R.id.er_img);
        int width=getResources().getDisplayMetrics().widthPixels;

        //生成中间什么都不带的二维码
        Bitmap bitmap=EncodingUtils.createQRCode(name,width/2,width/2, BitmapFactory.decodeResource(getResources(),0));
        //生成中间带图的二维码
//        Bitmap bitmap=EncodingUtils.createQRCode(name,width/2,width/2, BitmapFactory.decodeResource(getResources(),R.drawable.tubiao));
        er_img.setImageBitmap(bitmap);

        initView();

    }

    private void initView() {
        erweima_fanhui=findViewById(R.id.erweima_fanhui);
        erweima_fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
