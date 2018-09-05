package com.xiangmu.ui;
/**
 * 微信精选的web页
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.xiangmu.R;

public class WXJXweb extends AppCompatActivity {

    private WebView wed_text1;
    private String url;//网址

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wxweb_layout);
        Intent intent=getIntent();
        url=intent.getStringExtra("urll");
//        控件实例化
        initView();
    }

    private void initView() {
        //webview
        wed_text1=findViewById(R.id.wed_text1);
        //加载网址
        wed_text1.loadUrl(url);
    }
}
