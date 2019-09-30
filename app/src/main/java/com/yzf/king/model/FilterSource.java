package com.yzf.king.model;


/**
 * ClaseName：FilterSource
 * Description：
 * Author：chocozhao
 * QQ: 1027313530
 * Createtime：2019/5/21 09:28
 * Modified By：
 * Fixtime：2019/5/21 09:28
 * FixDescription：
 **/

public class FilterSource extends BaseModel {
    String beginTime;
    String endTime;
    String merchName;
    String Phone;
    int position;

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getMerchName() {
        return merchName;
    }

    public void setMerchName(String merchName) {
        this.merchName = merchName;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
