package com.yzf.king.model.servicesmodels;

import com.yzf.king.kit.AppTools;
import com.yzf.king.model.BaseModel;

import java.util.List;

public class GetFeeInfoResult extends BaseModel {

    /**
     * code : 200
     * data : [{"externFee":200,"feeRate":60,"id":2,"insertTime":1550023560000,"maxFee":1000000,"minFee":0,"remark":"提现","status":1,"templateId":0,"transType":"01","updateTime":1550023560000,"version":0},{"externFee":100,"feeRate":65,"id":19,"insertTime":1550304828000,"maxFee":1000000,"minFee":0,"remark":"WX消费","status":1,"templateId":0,"transType":"04","updateTime":1550304828000,"version":0}]
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
         * externFee : 200
         * feeRate : 60
         * id : 2
         * insertTime : 1550023560000
         * maxFee : 1000000
         * minFee : 0
         * remark : 提现
         * status : 1
         * templateId : 0
         * transType : 01
         * updateTime : 1550023560000
         * version : 0
         */

        private String externFee;
        private String feeRate;
        private int id;
        private String insertTime;
        private String maxFee;
        private String minFee;
        private String remark;
        private int status;
        private int templateId;
        private String transType;
        private String updateTime;
        private int version;

        public String getExternFee() {
            return AppTools.formatF2Y(externFee);
        }

        public void setExternFee(String externFee) {
            this.externFee = externFee;
        }

        public String getFeeRate() {
            return feeRate;
        }

        public void setFeeRate(String feeRate) {
            this.feeRate = feeRate;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getInsertTime() {
            return insertTime;
        }

        public void setInsertTime(String insertTime) {
            this.insertTime = insertTime;
        }

        public String getMaxFee() {
            return maxFee;
        }

        public void setMaxFee(String maxFee) {
            this.maxFee = maxFee;
        }

        public String getMinFee() {
            return minFee;
        }

        public void setMinFee(String minFee) {
            this.minFee = minFee;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getTemplateId() {
            return templateId;
        }

        public void setTemplateId(int templateId) {
            this.templateId = templateId;
        }

        public String getTransType() {
            return transType;
        }

        public void setTransType(String transType) {
            this.transType = transType;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public int getVersion() {
            return version;
        }

        public void setVersion(int version) {
            this.version = version;
        }
    }
}