package com.yzf.king.model.servicesmodels;

import com.yzf.king.model.BaseModel;

import java.util.List;

public class GetSubmerchCountResult extends BaseModel {


    /**
     * code : 200
     * data : [{"number":7,"levelName":"认证会员","merchLevel":"0"},{"number":1,"levelName":"VIP会员","merchLevel":"1"}]
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
         * number : 7
         * levelName : 认证会员
         * merchLevel : 0
         */

        private String number;
        private String levelName;
        private String merchLevel;

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getLevelName() {
            return levelName;
        }

        public void setLevelName(String levelName) {
            this.levelName = levelName;
        }

        public String getMerchLevel() {
            return merchLevel;
        }

        public void setMerchLevel(String merchLevel) {
            this.merchLevel = merchLevel;
        }
    }
}