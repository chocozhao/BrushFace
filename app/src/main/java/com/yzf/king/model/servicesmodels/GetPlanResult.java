package com.yzf.king.model.servicesmodels;

import com.yzf.king.model.BaseModel;

import java.io.Serializable;
import java.util.List;

public class GetPlanResult extends BaseModel {
    private int code;
    private List<DataBean> data;
    private String message;

    public static class DataBean implements Serializable {
        private String bankLogoUrl;
        private String bankName;
        private String cardId;
        private int count;
        private String date;
        private int depositAmount;
        private String detailDays;
        private String endDay;
        private int feeAmount;
        private int firstTransAmt;
        private String merchId;
        private String orderId;
        private int regId;
        private String remark;
        private int repaymentAmount;
        private String startDay;
        private int status;
        private int times;

        public int getDepositAmount() {
            return this.depositAmount;
        }

        public void setDepositAmount(int i) {
            this.depositAmount = i;
        }

        public String getDate() {
            return this.date;
        }

        public void setDate(String str) {
            this.date = str;
        }

        public String getBankLogoUrl() {
            return this.bankLogoUrl;
        }

        public void setBankLogoUrl(String str) {
            this.bankLogoUrl = str;
        }

        public int getRepaymentAmount() {
            return this.repaymentAmount;
        }

        public void setRepaymentAmount(int i) {
            this.repaymentAmount = i;
        }

        public String getOrderId() {
            return this.orderId;
        }

        public void setOrderId(String str) {
            this.orderId = str;
        }

        public String getStartDay() {
            return this.startDay;
        }

        public void setStartDay(String str) {
            this.startDay = str;
        }

        public int getCount() {
            return this.count;
        }

        public void setCount(int i) {
            this.count = i;
        }

        public String getRemark() {
            return this.remark;
        }

        public void setRemark(String str) {
            this.remark = str;
        }

        public String getBankName() {
            return this.bankName;
        }

        public void setBankName(String str) {
            this.bankName = str;
        }

        public int getFirstTransAmt() {
            return this.firstTransAmt;
        }

        public void setFirstTransAmt(int i) {
            this.firstTransAmt = i;
        }

        public String getMerchId() {
            return this.merchId;
        }

        public void setMerchId(String str) {
            this.merchId = str;
        }

        public int getFeeAmount() {
            return this.feeAmount;
        }

        public void setFeeAmount(int i) {
            this.feeAmount = i;
        }

        public int getTimes() {
            return this.times;
        }

        public void setTimes(int i) {
            this.times = i;
        }

        public String getCardId() {
            return this.cardId;
        }

        public void setCardId(String str) {
            this.cardId = str;
        }

        public String getEndDay() {
            return this.endDay;
        }

        public void setEndDay(String str) {
            this.endDay = str;
        }

        public int getRegId() {
            return this.regId;
        }

        public void setRegId(int i) {
            this.regId = i;
        }

        public String getDetailDays() {
            return this.detailDays;
        }

        public void setDetailDays(String str) {
            this.detailDays = str;
        }

        public int getStatus() {
            return this.status;
        }

        public void setStatus(int i) {
            this.status = i;
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