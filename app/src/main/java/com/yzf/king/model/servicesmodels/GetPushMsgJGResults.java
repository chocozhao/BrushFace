package com.yzf.king.model.servicesmodels;

import com.yzf.king.model.BaseModel;

import java.io.Serializable;
import java.util.List;

public class GetPushMsgJGResults extends BaseModel {
    private int code;
    private List<DataBean> data;
    private String message;

    public static class DataBean implements Serializable {
        private String add1;
        private String add2;
        private String alertTitle;
        private String branchCode;
        private int id;
        private String merchId;
        private String msgContent;
        private String msgExtras;
        private String msgStatus;
        private String msgTime;
        private String msgTitle;
        private String msgType;
        private String orderAmt;
        private String orderDesc;
        private String orderId;
        private String orderStatus;
        private String orderTime;
        private String orderType;
        private String version;

        public String getAdd1() {
            return this.add1;
        }

        public void setAdd1(String str) {
            this.add1 = str;
        }

        public String getAdd2() {
            return this.add2;
        }

        public void setAdd2(String str) {
            this.add2 = str;
        }

        public String getAlertTitle() {
            return this.alertTitle;
        }

        public void setAlertTitle(String str) {
            this.alertTitle = str;
        }

        public String getBranchCode() {
            return this.branchCode;
        }

        public void setBranchCode(String str) {
            this.branchCode = str;
        }

        public int getId() {
            return this.id;
        }

        public void setId(int i) {
            this.id = i;
        }

        public String getMerchId() {
            return this.merchId;
        }

        public void setMerchId(String str) {
            this.merchId = str;
        }

        public String getMsgContent() {
            return this.msgContent;
        }

        public void setMsgContent(String str) {
            this.msgContent = str;
        }

        public String getMsgExtras() {
            return this.msgExtras;
        }

        public void setMsgExtras(String str) {
            this.msgExtras = str;
        }

        public String getMsgStatus() {
            return this.msgStatus;
        }

        public void setMsgStatus(String str) {
            this.msgStatus = str;
        }

        public String getMsgTime() {
            return this.msgTime;
        }

        public void setMsgTime(String str) {
            this.msgTime = str;
        }

        public String getMsgTitle() {
            return this.msgTitle;
        }

        public void setMsgTitle(String str) {
            this.msgTitle = str;
        }

        public String getMsgType() {
            return this.msgType;
        }

        public void setMsgType(String str) {
            this.msgType = str;
        }

        public String getOrderAmt() {
            return this.orderAmt;
        }

        public void setOrderAmt(String str) {
            this.orderAmt = str;
        }

        public String getOrderDesc() {
            return this.orderDesc;
        }

        public void setOrderDesc(String str) {
            this.orderDesc = str;
        }

        public String getOrderId() {
            return this.orderId;
        }

        public void setOrderId(String str) {
            this.orderId = str;
        }

        public String getOrderStatus() {
            return this.orderStatus;
        }

        public void setOrderStatus(String str) {
            this.orderStatus = str;
        }

        public String getOrderTime() {
            return this.orderTime;
        }

        public void setOrderTime(String str) {
            this.orderTime = str;
        }

        public String getOrderType() {
            return this.orderType;
        }

        public void setOrderType(String str) {
            this.orderType = str;
        }

        public String getVersion() {
            return this.version;
        }

        public void setVersion(String str) {
            this.version = str;
        }
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int i) {
        this.code = i;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public List<DataBean> getData() {
        return this.data;
    }

    public void setData(List<DataBean> list) {
        this.data = list;
    }
}