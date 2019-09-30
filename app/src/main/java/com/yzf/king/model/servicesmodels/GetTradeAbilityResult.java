package com.yzf.king.model.servicesmodels;

import com.yzf.king.model.BaseModel;

import java.util.List;

/**
 * ClaseName：GetTradeAbilityResult
 * Description：
 * Author：JensenWei
 * QQ: 2188307188
 * Createtime：2018/5/28 14:33
 * Modified By：
 * Fixtime：2018/5/28 14:33
 * FixDescription：
 */

public class GetTradeAbilityResult extends BaseModel {


    /**
     * code : 200
     * data : [{"beginTime":"09:00","branchCode":"F0000001","endTime":"21:00","extraFee":100,"feeRate":65,"maxAmt":20000,"minAmt":10,"payType":1,"status":1,"transName":"VIP收款3","transType":"12","version":0}]
     * message : 获取成功
     */

    private int code;
    private String message;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * beginTime : 09:00
         * branchCode : F0000001
         * endTime : 21:00
         * extraFee : 100
         * feeRate : 65
         * maxAmt : 20000
         * minAmt : 10
         * payType : 1
         * status : 1
         * transName : VIP收款3
         * transType : 12
         * version : 0
         */

        private String beginTime;
        private String branchCode;
        private String endTime;
        private double extraFee;
        private double feeRate;
        private double maxAmt;
        private double minAmt;
        private String payType;
        private String status;
        private String transName;
        private String transType;
        private int version;
        private boolean choiceFlag;

        public boolean isChoiceFlag() {
            return choiceFlag;
        }

        public void setChoiceFlag(boolean choiceFlag) {
            this.choiceFlag = choiceFlag;
        }

        public String getBeginTime() {
            return beginTime;
        }

        public void setBeginTime(String beginTime) {
            this.beginTime = beginTime;
        }

        public String getBranchCode() {
            return branchCode;
        }

        public void setBranchCode(String branchCode) {
            this.branchCode = branchCode;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public double getExtraFee() {
            return extraFee;
        }

        public void setExtraFee(double extraFee) {
            this.extraFee = extraFee;
        }

        public double getFeeRate() {
            return feeRate;
        }

        public void setFeeRate(double feeRate) {
            this.feeRate = feeRate;
        }

        public double getMaxAmt() {
            return maxAmt;
        }

        public void setMaxAmt(double maxAmt) {
            this.maxAmt = maxAmt;
        }

        public double getMinAmt() {
            return minAmt;
        }

        public void setMinAmt(double minAmt) {
            this.minAmt = minAmt;
        }

        public String getPayType() {
            return payType;
        }

        public void setPayType(String payType) {
            this.payType = payType;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getTransName() {
            return transName;
        }

        public void setTransName(String transName) {
            this.transName = transName;
        }

        public String getTransType() {
            return transType;
        }

        public void setTransType(String transType) {
            this.transType = transType;
        }

        public int getVersion() {
            return version;
        }

        public void setVersion(int version) {
            this.version = version;
        }
    }
}
