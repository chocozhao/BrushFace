package com.yzf.king.model.servicesmodels;

import com.yzf.king.model.BaseModel;

import java.util.List;

public class GetNoticeResult extends BaseModel {
    private int code;
    private List<DataBean> data;
    private String message;

    public static class DataBean {
        private String add1;
        private String add2;
        private String add3;
        private int amt;
        private String beginTime;
        private String content;
        private String endTime;
        private int id;
        private String insertTime;
        private String merchId;
        private String orderId;
        private int readFlag;
        private String remark;
        private int showFlag;
        private int status;
        private String title;
        private int type;
        private String updateTime;
        private String url;

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

        public String getAdd3() {
            return this.add3;
        }

        public void setAdd3(String str) {
            this.add3 = str;
        }

        public int getAmt() {
            return this.amt;
        }

        public void setAmt(int i) {
            this.amt = i;
        }

        public String getBeginTime() {
            return this.beginTime;
        }

        public void setBeginTime(String str) {
            this.beginTime = str;
        }

        public String getContent() {
            return this.content;
        }

        public void setContent(String str) {
            this.content = str;
        }

        public String getEndTime() {
            return this.endTime;
        }

        public void setEndTime(String str) {
            this.endTime = str;
        }

        public int getId() {
            return this.id;
        }

        public void setId(int i) {
            this.id = i;
        }

        public String getInsertTime() {
            return this.insertTime;
        }

        public void setInsertTime(String str) {
            this.insertTime = str;
        }

        public String getMerchId() {
            return this.merchId;
        }

        public void setMerchId(String str) {
            this.merchId = str;
        }

        public String getOrderId() {
            return this.orderId;
        }

        public void setOrderId(String str) {
            this.orderId = str;
        }

        public int getReadFlag() {
            return this.readFlag;
        }

        public void setReadFlag(int i) {
            this.readFlag = i;
        }

        public String getRemark() {
            return this.remark;
        }

        public void setRemark(String str) {
            this.remark = str;
        }

        public int getShowFlag() {
            return this.showFlag;
        }

        public void setShowFlag(int i) {
            this.showFlag = i;
        }

        public int getStatus() {
            return this.status;
        }

        public void setStatus(int i) {
            this.status = i;
        }

        public String getTitle() {
            return this.title;
        }

        public void setTitle(String str) {
            this.title = str;
        }

        public int getType() {
            return this.type;
        }

        public void setType(int i) {
            this.type = i;
        }

        public String getUpdateTime() {
            return this.updateTime;
        }

        public void setUpdateTime(String str) {
            this.updateTime = str;
        }

        public String getUrl() {
            return this.url;
        }

        public void setUrl(String str) {
            this.url = str;
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