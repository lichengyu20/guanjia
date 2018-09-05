package com.xiangmu.ui;
/**
 * 忘记密码
 */

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.xiangmu.R;
import com.xiangmu.entity.MyUser;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

public class ForgetPasswordActivity extends BaseActivity implements View.OnClickListener {

    private EditText et_email;
    private Button btn_forget_password;
    private RelativeLayout forget_fanhui;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget);
        initView();
    }

    private void initView() {
        et_email = findViewById(R.id.et_email);
        btn_forget_password = findViewById(R.id.btn_forget_password);
        btn_forget_password.setOnClickListener(this);
        forget_fanhui = findViewById(R.id.forget_fanhui);
        forget_fanhui.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_forget_password:
                final String email = et_email.getText().toString();
                if (!TextUtils.isEmpty(email)) {
                    MyUser.resetPasswordByEmail(email, new UpdateListener() {
                        @Override
                        public void done(BmobException e) {
                            if (e == null) {
                                Toast.makeText(ForgetPasswordActivity.this, "邮箱已发送至：" + email, Toast.LENGTH_SHORT).show();
                                finish();
                            } else {
                                Toast.makeText(ForgetPasswordActivity.this, "邮箱发送失败", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                } else {
                    Toast.makeText(this, "邮箱不能为空", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.forget_fanhui:
                finish();
                break;
        }
    }
}
