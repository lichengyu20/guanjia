package com.xiangmu.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.xiangmu.MainActivity;
import com.xiangmu.R;
import com.xiangmu.entity.MyUser;
import com.xiangmu.ui.CourierActivity;
import com.xiangmu.ui.GeRen;
import com.xiangmu.ui.LoginActivity;
import com.xiangmu.ui.PhoneCha;
import com.xiangmu.ui.SettingActivity;
import com.xiangmu.view.ShareUtils;
import com.xiangmu.utils.StringBitmap;

import cn.bmob.v3.BmobUser;

/**
 * Created by 你猜 on 2018/7/2.
 * 个人中心类
 */

public class UserFragment extends Fragment implements View.OnClickListener {


    /**
     * 吊吊的运算法
     * boolean b=true;
     * true是1，false是2
     * String name=b?"1":"2";
     */

    //退出登录按钮,登录按钮,,,,,,,
    private Button btn_exit_user,btn_exit_user1;
    private TextView edit_user,edit_user1;//登录,未登录,,,,
    private ImageView profile_image,user_touxiang1;
    private RelativeLayout user_shezhi,user_kuaidi,user_phone;//设置,快递查询,号码归属地查询,,,,,,
    private View view;
    private String photo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_user, container, false);

        initDatw();

        return view;
    }

    private void initDatw() {
//        控件实例化
        initView();
//        判断并添加默认头像
        photo=ShareUtils.getString(getActivity(),"photo","");
        if(TextUtils.isEmpty(photo)) {
            Picasso.with(getActivity()).load(R.drawable.icon).into(profile_image);
        }else{
            Bitmap bm= StringBitmap.convertStringToIcon(photo);
            profile_image.setImageBitmap(bm);
        }
//        bmob判断用户是否登录
        BmobUser bmobUser = BmobUser.getCurrentUser();
        if(bmobUser == null){
            //用户没登录
            edit_user.setVisibility(View.GONE);
            edit_user1.setVisibility(View.VISIBLE);
            btn_exit_user.setVisibility(View.GONE);
            btn_exit_user1.setVisibility(View.VISIBLE);
        }else{
            // 用户登录
            edit_user.setVisibility(View.VISIBLE);
            edit_user1.setVisibility(View.GONE);
            btn_exit_user.setVisibility(View.VISIBLE);
            btn_exit_user1.setVisibility(View.GONE);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        initDatw();
    }

    private void initView() {

        edit_user = view.findViewById(R.id.edit_user);
        edit_user.setOnClickListener(this);

        btn_exit_user = view.findViewById(R.id.btn_exit_user);
        btn_exit_user.setOnClickListener(this);

        profile_image = view.findViewById(R.id.profile_image);
        profile_image.setOnClickListener(this);

        user_shezhi=view.findViewById(R.id.user_shezhi);
        user_shezhi.setOnClickListener(this);

        edit_user1=view.findViewById(R.id.edit_user1);
        edit_user1.setOnClickListener(this);

        btn_exit_user1=view.findViewById(R.id.btn_exit_user1);
        btn_exit_user1.setOnClickListener(this);

        user_kuaidi=view.findViewById(R.id.user_kuaidi);
        user_kuaidi.setOnClickListener(this);

        user_phone=view.findViewById(R.id.user_phone);
        user_phone.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            //退出登录
            case R.id.btn_exit_user:
                MyUser.logOut();
                MyUser.getCurrentUser();
                ShareUtils.deleAll(getActivity());
                Intent intent = new Intent(getActivity(), MainActivity.class);
//                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                getActivity().finish();
                break;
            //编辑资料
            case R.id.edit_user:
                getActivity().startActivity(new Intent(getActivity(),GeRen.class));
                break;
            //设置
            case R.id.user_shezhi:
                startActivity(new Intent(getActivity(), SettingActivity.class));
                break;
            //未登录
            case R.id.edit_user1:
                startActivity(new Intent(getActivity(),LoginActivity.class));
//                Toast.makeText(getActivity(), "请登录", Toast.LENGTH_SHORT).show();
                break;
            //登录按钮
            case R.id.btn_exit_user1:
                startActivity(new Intent(getActivity(),LoginActivity.class));
//                Toast.makeText(getActivity(), "稍等片刻...", Toast.LENGTH_SHORT).show();
                break;
            //快递查询
            case R.id.user_kuaidi:
                startActivity(new Intent(getActivity(),CourierActivity.class));
                break;
            //号码归属地查询
            case R.id.user_phone:
                startActivity(new Intent(getActivity(), PhoneCha.class));
                break;
            //查看大图
            case R.id.profile_image:
                showPopueWindow();
                break;
        }
    }

    //头像大图的popupwindow
    private void showPopueWindow() {
        View popView = View.inflate(getActivity(), R.layout.popupwindow_touxiang_need, null);
        user_touxiang1 = popView.findViewById(R.id.user_touxiang1);//相册
        //获取屏幕宽高
        int weight = getResources().getDisplayMetrics().widthPixels* 1 / 2;
        int height = getResources().getDisplayMetrics().heightPixels* 1 / 2;

        final PopupWindow popupWindow = new PopupWindow(popView, weight, height);
        popupWindow.setFocusable(true);
        //点击外部popueWindow消失
        popupWindow.setOutsideTouchable(true);

        //头像大图

        user_touxiang1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });

        initData();

        //popupWindow消失屏幕变为不透明
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
                lp.alpha = 1.0f;
                getActivity().getWindow().setAttributes(lp);
            }
        });
        //popupWindow出现屏幕变为半透明
        WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
        lp.alpha = 0.5f;
        getActivity().getWindow().setAttributes(lp);
        popupWindow.showAtLocation(popView, Gravity.BOTTOM, 0, 50);

    }



    private void initData() {
        if(TextUtils.isEmpty(photo)) {
            Picasso.with(getActivity()).load(R.drawable.icon).into(user_touxiang1);
        }
        else{
            Bitmap bm= StringBitmap.convertStringToIcon(photo);
            user_touxiang1.setImageBitmap(bm);
        }

    }







}
