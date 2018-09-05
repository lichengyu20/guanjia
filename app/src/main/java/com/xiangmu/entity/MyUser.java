package com.xiangmu.entity;

import cn.bmob.v3.BmobUser;

public class MyUser extends BmobUser {
    private int phone;
    private int sex;
    private String desc;

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public int isSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
