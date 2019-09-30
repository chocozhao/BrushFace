package com.yzf.king.model.servicesmodels;

import com.yzf.king.model.BaseModel;

public class RegisterResult extends BaseModel {

    /**
     * code : 200
     * data : {"status":"03","merchId":"10000025"}
     * message : 注册成功
     */

    private int code;
    private DataBean data;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class DataBean {
        /**
         * status : 03
         * merchId : 10000025
         */

        private String status;
        private String merchId;

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getMerchId() {
            return merchId;
        }

        public void setMerchId(String merchId) {
            this.merchId = merchId;
        }
    }
}