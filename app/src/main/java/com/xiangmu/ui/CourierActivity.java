package com.xiangmu.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.baidu.location.LLSInterface;
import com.xiangmu.R;
import com.xiangmu.utils.StaticClass;

public class CourierActivity extends BaseActivity implements View.OnClickListener {

    private RelativeLayout courier_fanhui;//返回,,,,,,,,
    private EditText et_name, et_number;
    private Button btn_get_courier;
    private RecyclerView courier_recycler;
    private LinearLayout courier_linear;//这个是判断如果请求到了数据就把上面的输入框什么的注掉

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courier);
        initView();
    }

    private void initView() {
        et_name = findViewById(R.id.et_name);
        et_number = findViewById(R.id.et_number);
        courier_recycler = findViewById(R.id.courier_recycler);

        btn_get_courier = findViewById(R.id.btn_get_courier);
        btn_get_courier.setOnClickListener(this);

        courier_fanhui=findViewById(R.id.courier_fanhui);
        courier_fanhui.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            //快递查询
            case R.id.btn_get_courier:
                Toast.makeText(this, "正在努力加载中...", Toast.LENGTH_SHORT).show();
//                kuaidi();
                break;
            //返回
            case R.id.courier_fanhui:
                finish();
                break;
        }
    }

    /**
     * 快递查询
     */
    private void kuaidi() {
        String name = et_name.getText().toString();
        String number = et_number.getText().toString();
        String kuai = null;//快递编号......
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "快递公司不能为空，有问题请反馈", Toast.LENGTH_SHORT).show();
        }
        if (TextUtils.isEmpty(number)) {
            Toast.makeText(this, "快递单号不能为空，有问题请反馈", Toast.LENGTH_SHORT).show();
        }

        /**
         * 无奈的判断快递公司的编号,判断如果不等于负1那就是包含
         */
        if ("韵".indexOf(name) != -1) {
            kuai = "YD";
        }
        if ("中".indexOf(name) != -1) {
            kuai = "zto";
        }
        if ("圆".indexOf(name) != -1) {
            kuai = "yto";
        }
        if ("申".indexOf(name) != -1) {
            kuai = "sto";
        }
        if ("顺".indexOf(name) != -1) {
            kuai = "sf";
        }
        if ("政".indexOf(name) != -1) {
            kuai = "EMS";
        }
        else{
            Toast.makeText(this, name+"暂时不支持", Toast.LENGTH_SHORT).show();
        }

        String url = "http://v.juhe.cn/exp/index?key=" + StaticClass.COURIER_KEY + "&com=" + kuai + "&no=" + number;




    }


}






