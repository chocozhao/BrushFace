package com.yzf.king.model.servicesmodels;

import com.yzf.king.model.BaseModel;

import java.util.List;

public class GetPlanChannelInfoResult extends BaseModel {
    private int code;
    private List<DataBean> data;
    private String message;

    public static class DataBean {
        private String actionName;
        private String channelName;
        private String feeRate;
        private int maxAmt;
        private int minAmt;
        private int planType;
        private String remark;
        private int status;
        private String supportInfo;
        private String transTime;
        private String bankAbbr;
        private String replaceFeeRate;

        public int getPlanType() {
            return this.planType;
        }

        public void setPlanType(int i) {
            this.planType = i;
        }

        public String getTransTime() {
            return this.transTime;
        }

        public void setTransTime(String str) {
            this.transTime = str;
        }

        public int getMaxAmt() {
            return this.maxAmt;
        }

        public void setMaxAmt(int i) {
            this.maxAmt = i;
        }

        public String getRemark() {
            return this.remark;
        }

        public void setRemark(String str) {
            this.remark = str;
        }

        public String getChannelName() {
            return this.channelName;
        }

        public void setChannelName(String str) {
            this.channelName = str;
        }

        public int getMinAmt() {
            return this.minAmt;
        }

        public void setMinAmt(int i) {
            this.minAmt = i;
        }

        public String getFeeRate() {
            return this.feeRate;
        }

        public void setFeeRate(String str) {
            this.feeRate = str;
        }

        public String getSupportInfo() {
            return this.supportInfo;
        }

        public void setSupportInfo(String str) {
            this.supportInfo = str;
        }

        public int getStatus() {
            return this.status;
        }

        public void setStatus(int i) {
            this.status = i;
        }

        public String getActionName() {
            return this.actionName;
        }

        public void setActionName(String str) {
            this.actionName = str;
        }

        public String getBankAbbr() {
            return bankAbbr;
        }

        public void setBankAbbr(String bankAbbr) {
            this.bankAbbr = bankAbbr;
        }

        public String getReplaceFeeRate() {
            return replaceFeeRate;
        }

        public void setReplaceFeeRate(String replaceFeeRate) {
            this.replaceFeeRate = replaceFeeRate;
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