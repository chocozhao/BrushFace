package com.yzf.king.model.servicesmodels;

import com.yzf.king.model.BaseModel;

import java.io.Serializable;
import java.util.List;

public class GetRedPacketResult extends BaseModel {
    private int code;
    private List<DataBean> data;
    private String message;

    public static class DataBean implements Serializable {
        private int amount;
        private int count;
        private String expireDate;
        private int id;
        private String insertTime;
        private String name;
        private String orderId;
        private String pushCode;
        private int pushSource;
        private int pushType;
        private String redPackDesc;
        private int redPackId;
        private String redPackOrderId;
        private int redPackType;
        private String remark;
        private String sourceCode;
        private int status;
        private int type;
        private String updateTime;
        private String validDate;

        public int getAmount() {
            return this.amount;
        }

        public void setAmount(int i) {
            this.amount = i;
        }

        public int getCount() {
            return this.count;
        }

        public void setCount(int i) {
            this.count = i;
        }

        public String getExpireDate() {
            return this.expireDate;
        }

        public void setExpireDate(String str) {
            this.expireDate = str;
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

        public String getName() {
            return this.name;
        }

        public void setName(String str) {
            this.name = str;
        }

        public String getOrderId() {
            return this.orderId;
        }

        public void setOrderId(String str) {
            this.orderId = str;
        }

        public String getPushCode() {
            return this.pushCode;
        }

        public void setPushCode(String str) {
            this.pushCode = str;
        }

        public int getPushSource() {
            return this.pushSource;
        }

        public void setPushSource(int i) {
            this.pushSource = i;
        }

        public int getPushType() {
            return this.pushType;
        }

        public void setPushType(int i) {
            this.pushType = i;
        }

        public String getRedPackDesc() {
            return this.redPackDesc;
        }

        public void setRedPackDesc(String str) {
            this.redPackDesc = str;
        }

        public int getRedPackId() {
            return this.redPackId;
        }

        public void setRedPackId(int i) {
            this.redPackId = i;
        }

        public String getRedPackOrderId() {
            return this.redPackOrderId;
        }

        public void setRedPackOrderId(String str) {
            this.redPackOrderId = str;
        }

        public int getRedPackType() {
            return this.redPackType;
        }

        public void setRedPackType(int i) {
            this.redPackType = i;
        }

        public String getRemark() {
            return this.remark;
        }

        public void setRemark(String str) {
            this.remark = str;
        }

        public String getSourceCode() {
            return this.sourceCode;
        }

        public void setSourceCode(String str) {
            this.sourceCode = str;
        }

        public int getStatus() {
            return this.status;
        }

        public void setStatus(int i) {
            this.status = i;
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

        public String getValidDate() {
            return this.validDate;
        }

        public void setValidDate(String str) {
            this.validDate = str;
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