package com.xiangmu.shiti;

public class ShiTi2 {

    private int img;
    private String title;
    private String tit;
    private String shijian;


    public ShiTi2(int img, String title, String tit, String shijian) {
        this.img = img;
        this.title = title;
        this.tit = tit;
        this.shijian = shijian;
    }


    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTit() {
        return tit;
    }

    public void setTit(String tit) {
        this.tit = tit;
    }

    public String getShijian() {
        return shijian;
    }

    public void setShijian(String shijian) {
        this.shijian = shijian;
    }
}
