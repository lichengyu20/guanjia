package com.xiangmu.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.xiangmu.R;


public class FanKui extends BaseActivity implements View.OnClickListener {

    private EditText mEditText,fan_qq,fan_phone;
    private RelativeLayout fankui_fanhui;
    private TextView fankui_shu;
    private Button fankui_tijiao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fan_kui);
        mEditText = findViewById(R.id.ET);
        mEditText.addTextChangedListener(mTextWatcher);
        fan_qq=findViewById(R.id.fan_qq);
        fan_phone=findViewById(R.id.fan_phone);
        fankui_fanhui=findViewById(R.id.fankui_fanhui);
        fankui_fanhui.setOnClickListener(this);
        fankui_shu=findViewById(R.id.fankui_shu);
        fankui_tijiao=findViewById(R.id.fankui_tijiao);
        fankui_tijiao.setOnClickListener(this);
    }



    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fankui_fanhui:
                finish();
                break;
            case R.id.fankui_tijiao:
                String text=mEditText.getText().toString();
                String qq=fan_qq.getText().toString();
                String phone=fan_phone.getText().toString();
                if(!TextUtils.isEmpty(qq)) {
                    //内容以后在提交吧
                    Toast.makeText(this, "提交成功", Toast.LENGTH_SHORT).show();
                    finish();
                }else{
                    Toast.makeText(this, "请填写QQ，谢谢合作", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }







    TextWatcher mTextWatcher = new TextWatcher() {
        private CharSequence temp;
        private int editStart ;
        private int editEnd ;
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            // TODO Auto-generated method stub
            temp = s;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {
            // TODO Auto-generated method stub
//			mTextView.setText(s);//将输入的内容实时显示
        }

        @Override
        public void afterTextChanged(Editable s) {
            // TODO Auto-generated method stub
            editStart = mEditText.getSelectionStart();
            editEnd = mEditText.getSelectionEnd();
            fankui_shu.setText(temp.length()+"");
            if (temp.length() > 300) {
                Toast.makeText(FanKui.this,
                        "你输入的字数已经超过了限制！", Toast.LENGTH_SHORT)
                        .show();
                s.delete(editStart-1, editEnd);
                int tempSelection = editStart;
                mEditText.setText(s);
                mEditText.setSelection(tempSelection);
            }
        }
    };

}
