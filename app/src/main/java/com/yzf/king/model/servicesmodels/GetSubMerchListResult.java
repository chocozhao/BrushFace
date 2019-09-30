package com.yzf.king.model.servicesmodels;

import com.yzf.king.model.BaseModel;

import java.io.Serializable;
import java.util.List;

public class GetSubMerchListResult extends BaseModel {
    private int code;
    private List<DataBean> data;
    private String message;

    public static class DataBean implements Serializable {
        private String merchId;
        private String merchName;
        private String phone;
        private int sumBenefitAmount;
        private int sumBenefitCount;
        private String type;

        public String getMerchId() {
            return this.merchId;
        }

        public void setMerchId(String str) {
            this.merchId = str;
        }

        public String getMerchName() {
            return this.merchName;
        }

        public void setMerchName(String str) {
            this.merchName = str;
        }

        public String getPhone() {
            return this.phone;
        }

        public void setPhone(String str) {
            this.phone = str;
        }

        public int getSumBenefitAmount() {
            return this.sumBenefitAmount;
        }

        public void setSumBenefitAmount(int i) {
            this.sumBenefitAmount = i;
        }

        public int getSumBenefitCount() {
            return this.sumBenefitCount;
        }

        public void setSumBenefitCount(int i) {
            this.sumBenefitCount = i;
        }

        public String getType() {
            return this.type;
        }

        public void setType(String str) {
            this.type = str;
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