package com.xiangmu.ui;
/**
 * 蛋疼的登陆
 */

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.xiangmu.MainActivity;
import com.xiangmu.R;
import com.xiangmu.entity.MyUser;
import com.xiangmu.view.CustomDialog;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_registered, btnLogin;
    private EditText et_name, et_password;
    private CheckBox keep_password;
    private TextView tv_forget;
    private CustomDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
//        每次进来的时候会先判断这个sp里面有没有这个数据，如果没有那就为空
//        String name = ShareUtils.getString(this, "name", "");
//        String password = ShareUtils.getString(this, "password", "");
//        这个是判断用户名和密码不能为空的，如果不为空就是直接执行登录，如果为空那就证明他不是自动登录，那就一步一步往下执行
//        if(!TextUtils.isEmpty(name)&&!TextUtils.isEmpty(password)) {
//           zhidong(name,password);
//        }
    }

    private void initView() {

        btn_registered = findViewById(R.id.btn_registered);
        et_name = findViewById(R.id.et_name);
        et_password = findViewById(R.id.et_passwor);
        btnLogin = findViewById(R.id.btnLogin);
//        keep_password = findViewById(R.id.keep_password);
        tv_forget = findViewById(R.id.tv_forget);

        btn_registered.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
        tv_forget.setOnClickListener(this);

        //dialog实例化
        dialog = new CustomDialog(this, 100, 100, R.layout.dialog_loding, R.style.Theme_dialog, Gravity.CENTER, R.style.pop_anim_style);
        //屏幕外点击无效
        dialog.setCancelable(false);

//        //设置选中状态
//        boolean isCheck = ShareUtils.getBoolean(this, "keeppass", true);
//        keep_password.setChecked(isCheck);
//        if (isCheck) {
//            et_name.setText(ShareUtils.getString(this, "name", ""));
//            et_password.setText(ShareUtils.getString(this, "password", ""));
//        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            //跳忘记密码页面
            case R.id.tv_forget:
                Intent intent1=new Intent(this,ForgetPasswordActivity.class);
                startActivity(intent1);
                break;
            //跳注册
            case R.id.btn_registered:
                Intent intent = new Intent(this, RegisteredAcyivity.class);
                startActivity(intent);
                break;
            case R.id.btnLogin:
                String name = et_name.getText().toString();
                String password = et_password.getText().toString();
                zhidong(name, password);
                break;

        }
    }


    private void zhidong(String name, String password) {
//        dialog.show();
        MyUser myUser = new MyUser();
        myUser.setUsername(name);
        myUser.setPassword(password);


        myUser.login(new SaveListener<MyUser>() {
            @Override
            public void done(MyUser myUser, BmobException e) {
                if (e == null) {
//                    dialog.dismiss();
                    Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                    Intent intent1 = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent1);


//                    //保存点击记住密码的状态
//                    ShareUtils.putBoolean(LoginActivity.this, "keeppass", keep_password.isChecked());
//                    if (keep_password.isChecked()) {
////                        如果是true也就是设置了自动登录那就把用户名和密码存到sp里面
//                        ShareUtils.putString(LoginActivity.this, "name", et_name.getText().toString());
//                        ShareUtils.putString(LoginActivity.this, "password", et_password.getText().toString());
//                    } else {
////                        如果是false那就把sp里面的name和password清空
//                        ShareUtils.deleShare(LoginActivity.this, "name");
//                        ShareUtils.deleShare(LoginActivity.this, "password");
//                    }
                    finish();
                } else {
                    if (e.getErrorCode() == 101) {
                        Toast.makeText(LoginActivity.this, "账号或密码不正确", Toast.LENGTH_SHORT).show();
                    }
                    if (e.getErrorCode() == 9016) {
                        Toast.makeText(LoginActivity.this, "无网络连接，请检查您的手机网络", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
