package com.xiangmu.ui;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.xiangmu.R;
import com.xiangmu.entity.MyUser;
import com.xiangmu.touxiang.PhotoUtils;
import com.xiangmu.view.ShareUtils;
import com.xiangmu.utils.StringBitmap;

import java.io.File;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

public class GeRen extends BaseActivity implements View.OnClickListener {

    private RelativeLayout geren_fanhui,geren_touxiang;//返回按钮,更换头像按钮,,,,,,,,,,,,,,,,,,,,
    private TextView geren_xiugai,geren_xiugai1;//修改与保存
    private EditText et_username, et_phone, et_desc;
    private ImageView geren_image;//头像,,,,,,,,,,
    //调用系统相册-选择图片
    private static final int IMAGE = 1;
    //调用系统相机用到的
    private Uri imageUri,cropImageUri;
    private File fileUri = new File(Environment.getExternalStorageDirectory().getPath() + "/photo.jpg");
    private File fileCropUri = new File(Environment.getExternalStorageDirectory().getPath() + "/crop_photo.jpg");
    private static final int CODE_CAMERA_REQUEST = 0xa1;
    private static final int CODE_RESULT_REQUEST = 0xa2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geren);
        initView();
        //添加默认头像
        String s=ShareUtils.getString(this,"photo","");
        if(TextUtils.isEmpty(s)) {
            Picasso.with(this).load(R.drawable.icon).into(geren_image);
        }else{
            Bitmap bitmap=StringBitmap.convertStringToIcon(s);
            geren_image.setImageBitmap(bitmap);
        }
//        控件实例化
    }

    private void initView() {
        et_username = findViewById(R.id.et_username);
        et_phone = findViewById(R.id.et_phone);
        et_desc = findViewById(R.id.et_desc);

        geren_image=findViewById(R.id.geren_image);

        geren_fanhui=findViewById(R.id.geren_fanhui);
        geren_xiugai=findViewById(R.id.geren_xiugai);
        geren_xiugai1=findViewById(R.id.geren_xiugai1);
        geren_touxiang=findViewById(R.id.geren_touxiang);

        geren_fanhui.setOnClickListener(this);
        geren_xiugai.setOnClickListener(this);
        geren_xiugai1.setOnClickListener(this);
        geren_touxiang.setOnClickListener(this);


        //设置edittext不可点击
        setEnabled(false);

        //设置text默认值
        MyUser userInfo = BmobUser.getCurrentUser(MyUser.class);
        et_username.setText(userInfo.getUsername() + "");
        et_phone.setText(userInfo.getPhone() + "");
        et_desc.setText(userInfo.getDesc());

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            返回按钮
            case R.id.geren_fanhui:
                finish();
                break;
//                修改个人资料
            case R.id.geren_xiugai:
                geren_xiugai.setVisibility(View.GONE);
                geren_xiugai1.setVisibility(View.VISIBLE);
                setEnabled(true);
                break;
            case R.id.geren_xiugai1:

                final String name = et_username.getText().toString() + "";
                String phone = et_phone.getText().toString() + "";
                String desc = et_desc.getText().toString() + "";

                MyUser myUser1 = new MyUser();
                myUser1.setUsername(name);
                myUser1.setPhone(Integer.parseInt(phone));
                myUser1.setDesc(desc);
                BmobUser bmobUser = BmobUser.getCurrentUser();
                myUser1.update(bmobUser.getObjectId(), new UpdateListener() {
                    @Override
                    public void done(BmobException e) {
                        if (e == null) {
                            geren_xiugai.setVisibility(View.VISIBLE);
                            geren_xiugai1.setVisibility(View.GONE);
                            setEnabled(false);
                            ShareUtils.putString( GeRen.this,"name", name);
                            Toast.makeText(GeRen.this, "修改成功", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(GeRen.this, "修改失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                break;

            case R.id.geren_touxiang:
                showPopueWindow();
                break;
        }
    }



    //设置edittext不可点击
    private void setEnabled(boolean b) {
        //b==true是可以修改，如果b==false是不可修改
        et_username.setEnabled(b);
        et_phone.setEnabled(b);
        et_desc.setEnabled(b);
        if(b==true) {
            geren_touxiang.setVisibility(View.VISIBLE);
        }else{
            geren_touxiang.setVisibility(View.GONE);
        }
    }





    //调用系统相册的popupwindow
    private void showPopueWindow() {
        View popView = View.inflate(this, R.layout.popupwindow_camera_need, null);
        Button btn_pop_album = popView.findViewById(R.id.btn_pop_album);//相册
        Button btn_pop_camera = popView.findViewById(R.id.btn_pop_camera);//相机
        Button bt_cancle = popView.findViewById(R.id.btn_pop_cancel);
        //获取屏幕宽高
        int weight = getResources().getDisplayMetrics().widthPixels;
        int height = getResources().getDisplayMetrics().heightPixels * 1 / 3;

        final PopupWindow popupWindow = new PopupWindow(popView, weight, height);
        popupWindow.setFocusable(true);
        //点击外部popueWindow消失
        popupWindow.setOutsideTouchable(true);

        //调用系统相册
        btn_pop_album.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
                inXiangCe();
            }
        });
        //调用系统相机
        btn_pop_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
                xiangji();
            }
        });
        bt_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();

            }
        });
        //popupWindow消失屏幕变为不透明
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 1.0f;
                getWindow().setAttributes(lp);
            }
        });
        //popupWindow出现屏幕变为半透明
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.5f;
        getWindow().setAttributes(lp);
        popupWindow.showAtLocation(popView, Gravity.BOTTOM, 0, 50);

    }


    //调用系统相机
    public void xiangji() {
        imageUri = Uri.fromFile(fileUri);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
            //通过FileProvider创建一个content类型的Uri
            imageUri = FileProvider.getUriForFile(GeRen.this, "com.xiangmu.fileprovider", fileUri);
        PhotoUtils.takePicture(GeRen.this, imageUri, CODE_CAMERA_REQUEST);
    }

    //调用系统相册
    private void inXiangCe() {
        //调用相册
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, IMAGE);
    }


    //获取图片路径的
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //应该是图片的宽与高
        int output_X = 480, output_Y = 480;
        //获取图片路径
        if (requestCode == IMAGE && resultCode == Activity.RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
            String[] filePathColumns = {MediaStore.Images.Media.DATA};
            Cursor c = this.getContentResolver().query(selectedImage, filePathColumns, null, null, null);
            c.moveToFirst();
            int columnIndex = c.getColumnIndex(filePathColumns[0]);
            String imagePath = c.getString(columnIndex);
            Bitmap bm = BitmapFactory.decodeFile(imagePath);

            //把bitmap转成string为了方便和下面的手机拍张保持一致，很无奈啊
            String imsg=StringBitmap.convertIconToString(bm);
            ShareUtils.putString(this,"photo",imsg);
            showimage(bm);
            c.close();
        }

        //系统相机拍照返回值
        switch (requestCode) {
            case CODE_CAMERA_REQUEST:
                cropImageUri = Uri.fromFile(fileCropUri);
                PhotoUtils.cropImageUri(this, imageUri, cropImageUri, 1, 1, output_X, output_Y, CODE_RESULT_REQUEST);
                Bitmap bitmap = PhotoUtils.getBitmapFromUri(cropImageUri, this);
               //把bitmap转成string已方便存进sp里面
                String img=StringBitmap.convertIconToString(bitmap);
                ShareUtils.putString(this,"photo",img);

                showimage(bitmap);
                break;
        }
    }


    //这个是直接加载bitmap,也就是相机用的
    private void showimage(Bitmap bitmap) {
        geren_image.setImageBitmap(bitmap);
    }

}
