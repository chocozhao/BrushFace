package com.yzf.king.model;

/**
 * ClaseName：HomeSource
 * Description：
 * Author：JensenWei
 * QQ: 2188307188
 * Createtime：2017/3/18 15:27
 * Modified By：
 * Fixtime：2017/3/18 15:27
 * FixDescription：
 */


public class HomeSource {
    private int backRes;
    private int id;
    private int imgRes;
    private String strRes;
    private String targetStr;

    public int getId() {
        return this.id;
    }

    public void setId(int i) {
        this.id = i;
    }

    public int getBackRes() {
        return this.backRes;
    }

    public void setBackRes(int i) {
        this.backRes = i;
    }

    public int getImgRes() {
        return this.imgRes;
    }

    public void setImgRes(int i) {
        this.imgRes = i;
    }

    public String getStrRes() {
        return this.strRes;
    }

    public void setStrRes(String str) {
        this.strRes = str;
    }

    public String getTargetStr() {
        return this.targetStr;
    }

    public void setTargetStr(String str) {
        this.targetStr = str;
    }
}