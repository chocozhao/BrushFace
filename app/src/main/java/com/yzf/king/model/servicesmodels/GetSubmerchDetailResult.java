package com.yzf.king.model.servicesmodels;

import com.yzf.king.model.BaseModel;

import java.util.List;

public class GetSubmerchDetailResult extends BaseModel {


    /**
     * code : 200
     * data : [{"idName":"赵诗思","merchId":"10000016","merchLevel":0,"merchName":"赵诗思","phone":"17665086368","status":"00"}]
     * message : 查询成功
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
         * idName : 赵诗思
         * merchId : 10000016
         * merchLevel : 0
         * merchName : 赵诗思
         * phone : 17665086368
         * status : 00
         */

        private String idName;
        private String merchId;
        private String merchLevel;
        private String merchName;
        private String phone;
        private String status;

        public String getIdName() {
            return idName;
        }

        public void setIdName(String idName) {
            this.idName = idName;
        }

        public String getMerchId() {
            return merchId;
        }

        public void setMerchId(String merchId) {
            this.merchId = merchId;
        }

        public String getMerchLevel() {
            return merchLevel;
        }

        public void setMerchLevel(String merchLevel) {
            this.merchLevel = merchLevel;
        }

        public String getMerchName() {
            return merchName;
        }

        public void setMerchName(String merchName) {
            this.merchName = merchName;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}