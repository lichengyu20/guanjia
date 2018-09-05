package com.xiangmu.ui;
/**
 * 蛋疼的注册
 */

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.xiangmu.R;
import com.xiangmu.entity.MyUser;
import com.xiangmu.view.L;

import java.util.Timer;
import java.util.TimerTask;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class RegisteredAcyivity extends BaseActivity implements View.OnClickListener {

    private EditText et_user, et_phone, et_pass, et_password, et_email, et_yanzheng;//账号,手机号,密码,密码,邮箱,验证码,,,,,,,,,,,,,,,,,,,,,
    private RelativeLayout btnRegistered;//注册按钮
    private String user, phone, password, pass, email, yanzheng;//账号,手机号,密码,密码,邮箱,验证码,,,,
    private TextView et_yanzhengma;//验证码按钮,,,,,,,,

    //验证码用到的
    private int timess;
    private TimerTask timerTask;
    private Timer timer;
    private ProgressDialog progressDialog;
    //===========================================================================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registered);
        //短信验证的
//        SMSSDK.getInstance().initSdk(this);
//        SMSSDK.getInstance().setIntervalTime(60 * 1000);
        initView();
    }

    private void initView() {
        //实例化控件
        et_user = findViewById(R.id.et_user);
        et_phone = findViewById(R.id.et_phone);
        et_pass = findViewById(R.id.et_pass);
        et_password = findViewById(R.id.et_password);
        et_email = findViewById(R.id.et_email);
        et_yanzheng = findViewById(R.id.et_yanzheng);


        //按钮实例化并添加点击事件
        et_yanzhengma = findViewById(R.id.et_yanzhengma);
        et_yanzhengma.setOnClickListener(this);
        btnRegistered = findViewById(R.id.btnRegistered);
        btnRegistered.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.et_yanzhengma:
                Toast.makeText(this, "正在努力开发中......", Toast.LENGTH_SHORT).show();

//                String phonee=et_phone.getText().toString();
//                if (TextUtils.isEmpty(phonee)) {
//                    Toast.makeText(this, "请输入手机号", Toast.LENGTH_SHORT).show();
//                }
                //点击验证码按钮获取验证码
//                et_yanzhengma.setClickable(false);
//                startTimer();
//                Toast.makeText(this, et_phone.getText().toString(), Toast.LENGTH_SHORT).show();
//                SMSSDK.getInstance().getSmsCodeAsyn(phonee, 1 + "", new SmscodeListener() {
//                    @Override
//                    public void getCodeSuccess(final String uuid) {
//
//                    }
//
//                    @Override
//                    public void getCodeFail(int errCode, final String errmsg) {
//                        Toast.makeText(RegisteredAcyivity.this, errCode+"            00", Toast.LENGTH_SHORT).show();
//                        //失败后停止计时
//                        stopTimer();
//
//                    }
//                });
                break;
            case R.id.btnRegistered:

                user = et_user.getText().toString().trim();
                phone = et_phone.getText().toString();
                password = et_pass.getText().toString().trim();
                pass = et_password.getText().toString().trim();
                email = et_email.getText().toString().trim();
                yanzheng = et_yanzheng.getText().toString().trim();

                if (!TextUtils.isEmpty(user)) {
                    if (!TextUtils.isEmpty(phone)) {
                        if (!TextUtils.isEmpty(password) && !TextUtils.isEmpty(pass)) {
                            if (password.equals(pass)) {
                                if (!TextUtils.isEmpty(email)) {

                                    MyUser myUser = new MyUser();
                                    myUser.setUsername(user);
                                    myUser.setPassword(password);
                                    myUser.setEmail(email);
                                    myUser.setPhone(Integer.parseInt(phone+""));

                                    myUser.signUp(new SaveListener<MyUser>() {
                                        @Override
                                        public void done(MyUser myUser, BmobException e) {
                                            if (e == null) {

//                                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisteredAcyivity.this);
//                                                builder.setTitle("提示");
//                                                builder.setMessage("成了，滚吧");
//                                                builder.setPositiveButton("好的", new DialogInterface.OnClickListener() {
//                                                    @Override
//                                                    public void onClick(DialogInterface dialog, int which) {
//                                                        finish();
//                                                    }
//                                                });
//                                                builder.setNegativeButton("拒绝", new DialogInterface.OnClickListener() {
//                                                    @Override
//                                                    public void onClick(DialogInterface dialog, int which) {
//                                                        //...To-do
//                                                    }
//                                                });
//                                                // 显示
//                                                builder.show();
                                                Toast.makeText(RegisteredAcyivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                                                finish();

                                            } else {
                                                int ee = e.getErrorCode();
                                                L.e(ee + "");
                                                if (ee == 202) {
                                                    Toast.makeText(RegisteredAcyivity.this, "用户名已存在", Toast.LENGTH_SHORT).show();
                                                }
                                                if (ee == 203) {
                                                    Toast.makeText(RegisteredAcyivity.this, "邮箱已注册", Toast.LENGTH_SHORT).show();
                                                }
                                                if (ee == 9016) {
                                                    Toast.makeText(RegisteredAcyivity.this, "无网络连接，请检查您的手机网络", Toast.LENGTH_SHORT).show();
                                                }
                                                if (ee == 9019) {
                                                    Toast.makeText(RegisteredAcyivity.this, "输入格式有误", Toast.LENGTH_SHORT).show();
                                                }
                                                if (ee == 301) {
                                                    Toast.makeText(RegisteredAcyivity.this, "邮箱格式有误", Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        }
                                    });









//                                    //验证短信验证码的
//                                    progressDialog.setTitle("稍等...");
//                                    progressDialog.show();
//                                    SMSSDK.getInstance().checkSmsCodeAsyn(phone, yanzheng, new SmscheckListener() {
//                                        @Override
//                                        public void checkCodeSuccess(final String code) {
//                                            if (progressDialog != null && progressDialog.isShowing()) {
//                                                progressDialog.dismiss();
//                                            }
//                                            //验证成功的操作
//                                        }
//
//                                        @Override
//                                        public void checkCodeFail(int errCode, final String errmsg) {
//                                            if (progressDialog != null && progressDialog.isShowing()) {
//                                                progressDialog.dismiss();
//                                            }
//                                            //验证失败的操作
//                                        }
//                                    });
                                } else {
                                    Toast.makeText(this, "邮箱不能为空", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(this, "密码不一致", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(this, "密码不能为空", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(this, "手机号不能为空", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "昵称不能为空", Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }


//    private void startTimer() {
//        timess = (int) (SMSSDK.getInstance().getIntervalTime() / 1000);
//        et_yanzhengma.setText(timess + "s");
//        if (timerTask == null) {
//            timerTask = new TimerTask() {
//                @Override
//                public void run() {
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            timess--;
//                            if (timess <= 0) {
//                                stopTimer();
//                                return;
//                            }
//                            et_yanzhengma.setText(timess + "s");
//                        }
//                    });
//                }
//            };
//        }
//        if (timer == null) {
//            timer = new Timer();
//        }
//        timer.schedule(timerTask, 1000, 1000);
//    }
//
//    private void stopTimer() {
//        if (timer != null) {
//            timer.cancel();
//            timer = null;
//        }
//        if (timerTask != null) {
//            timerTask.cancel();
//            timerTask = null;
//        }
//        et_yanzhengma.setText("重新获取");
//        et_yanzhengma.setClickable(true);
//    }


}
