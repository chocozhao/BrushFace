package com.yzf.king.model;


import java.io.Serializable;

public class CardItem implements Serializable {

    private String title;
    private String conetent;
    private String url;
    private String name;
    private int imageResource;
    private Object data;

    public CardItem(String title, String conetent, String url, int imageResource, Object data,String name) {
        this.title = title;
        this.conetent = conetent;
        this.url = url;
        this.imageResource = imageResource;
        this.data = data;
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getConetent() {
        return conetent;
    }

    public void setConetent(String conetent) {
        this.conetent = conetent;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImageResource() {
        return imageResource;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
