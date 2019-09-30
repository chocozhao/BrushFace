package com.yzf.king.model.servicesmodels;

import java.io.Serializable;

public class QuickPayResult implements Serializable {
    private String busCode;
    private String cct;
    private String merchFee;
    private String orderAmt;
    private String orderId;
    private String payInfo;
    private String paychTime;
    private String respCode;
    private String respDesc;
    private String shareImage;
    private String shareUrl;
    private String merchId;

    public String getOrderId() {
        return this.orderId;
    }

    public void setOrderId(String str) {
        this.orderId = str;
    }

    public String getOrderAmt() {
        return this.orderAmt;
    }

    public void setOrderAmt(String str) {
        this.orderAmt = str;
    }

    public String getPaychTime() {
        return this.paychTime;
    }

    public void setPaychTime(String str) {
        this.paychTime = str;
    }

    public String getRespCode() {
        return this.respCode;
    }

    public void setRespCode(String str) {
        this.respCode = str;
    }

    public String getRespDesc() {
        return this.respDesc;
    }

    public void setRespDesc(String str) {
        this.respDesc = str;
    }

    public String getMerchId() {
        return this.merchId;
    }

    public void setMerchId(String str) {
        this.merchId = str;
    }

    public String getBusCode() {
        return this.busCode;
    }

    public void setBusCode(String str) {
        this.busCode = str;
    }

    public String getPayInfo() {
        return this.payInfo;
    }

    public void setPayInfo(String str) {
        this.payInfo = str;
    }

    public String getCct() {
        return this.cct;
    }

    public void setCct(String str) {
        this.cct = str;
    }

    public String getMerchFee() {
        return this.merchFee;
    }

    public void setMerchFee(String str) {
        this.merchFee = str;
    }

    public String getShareUrl() {
        return this.shareUrl;
    }

    public void setShareUrl(String str) {
        this.shareUrl = str;
    }

    public String getShareImage() {
        return this.shareImage;
    }

    public void setShareImage(String str) {
        this.shareImage = str;
    }
}