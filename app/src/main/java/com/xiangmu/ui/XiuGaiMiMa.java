package com.xiangmu.ui;

/**
 * 修改密码类
 */

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.xiangmu.R;
import com.xiangmu.entity.MyUser;
import com.xiangmu.view.ShareUtils;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

public class XiuGaiMiMa extends AppCompatActivity implements View.OnClickListener {

    private EditText et_now, et_new, et_new_password;
    private Button btn_update_password;
    private RelativeLayout xiugai_fanhui;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiugai_mima);
        initView();
    }

    private void initView() {
        et_now = findViewById(R.id.et_now);
        et_new = findViewById(R.id.et_new);
        et_new_password = findViewById(R.id.et_new_password);
        btn_update_password = findViewById(R.id.btn_update_password);
        btn_update_password.setOnClickListener(this);
        xiugai_fanhui = findViewById(R.id.xiugai_fanhui);
        xiugai_fanhui.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_update_password:






                String now = et_now.getText().toString();
                final String news = et_new.getText().toString();
                String new_password = et_new_password.getText().toString();
                if (!TextUtils.isEmpty(now)) {
                    if (!TextUtils.isEmpty(news) && !TextUtils.isEmpty(new_password)) {
                        if (news.equals(new_password)) {
                            MyUser.updateCurrentUserPassword(now, news, new UpdateListener() {
                                @Override
                                public void done(BmobException e) {
                                    if (e == null) {
                                        ShareUtils.putString(XiuGaiMiMa.this,"",news);
                                        Toast.makeText(XiuGaiMiMa.this, "修改成功", Toast.LENGTH_SHORT).show();
                                        finish();
                                    } else {
                                        Toast.makeText(XiuGaiMiMa.this, "修改失败" + e.getErrorCode(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        } else {
                            Toast.makeText(this, "新密码不一至", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(this, "请输入新密码", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "请输入旧密码", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.xiugai_fanhui:
                finish();
                break;
        }
    }
}
