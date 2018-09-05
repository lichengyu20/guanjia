package com.xiangmu.shiti;

public class ShiTi1 {

    private String id;
    private String title;
    private String source;
    private String url;

    public ShiTi1(String id, String title, String source, String url) {
        this.id = id;
        this.title = title;
        this.source = source;
        this.url = url;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
