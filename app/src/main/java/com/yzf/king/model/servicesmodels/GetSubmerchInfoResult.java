package com.yzf.king.model.servicesmodels;

import com.yzf.king.model.BaseModel;

import java.util.List;

public class GetSubmerchInfoResult extends BaseModel {

    /**
     * code : 200
     * data : {"merchData":[{"number":0,"levelName":"未注册","merchLevel":"0"},{"number":0,"levelName":"未认证","merchLevel":"1"},{"number":0,"levelName":"认证会员","merchLevel":"2"},{"number":0,"levelName":"VIP1会员","merchLevel":"3"},{"number":0,"levelName":"VIP2会员","merchLevel":"4"},{"number":0,"levelName":"VIP3会员","merchLevel":"5"}],"totalSubMerchNum":5,"subMerchNum":5,"subSubMerchNum":0}
     * message : 获取成功
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
         * merchData : [{"number":0,"levelName":"未注册","merchLevel":"0"},{"number":0,"levelName":"未认证","merchLevel":"1"},{"number":0,"levelName":"认证会员","merchLevel":"2"},{"number":0,"levelName":"VIP1会员","merchLevel":"3"},{"number":0,"levelName":"VIP2会员","merchLevel":"4"},{"number":0,"levelName":"VIP3会员","merchLevel":"5"}]
         * totalSubMerchNum : 5
         * subMerchNum : 5
         * subSubMerchNum : 0
         */

        private String totalSubMerchNum;
        private String subMerchNum;
        private String subSubMerchNum;
        private List<MerchDataBean> merchData;
        private String merchPhone;
        private String merchName;
        private String merchLevel;
        private String merchLevelName;
        private long insertTime;
        private String totalSubMerchName;
        private String subMerchName;
        private String subSubMerchName;

        public String getTotalSubMerchNum() {
            return totalSubMerchNum;
        }

        public void setTotalSubMerchNum(String totalSubMerchNum) {
            this.totalSubMerchNum = totalSubMerchNum;
        }

        public String getSubMerchNum() {
            return subMerchNum;
        }

        public void setSubMerchNum(String subMerchNum) {
            this.subMerchNum = subMerchNum;
        }

        public String getSubSubMerchNum() {
            return subSubMerchNum;
        }

        public void setSubSubMerchNum(String subSubMerchNum) {
            this.subSubMerchNum = subSubMerchNum;
        }

        public String getMerchPhone() {
            return merchPhone;
        }

        public void setMerchPhone(String merchPhone) {
            this.merchPhone = merchPhone;
        }

        public String getMerchName() {
            return merchName;
        }

        public void setMerchName(String merchName) {
            this.merchName = merchName;
        }

        public String getMerchLevel() {
            return merchLevel;
        }

        public void setMerchLevel(String merchLevel) {
            this.merchLevel = merchLevel;
        }

        public String getMerchLevelName() {
            return merchLevelName;
        }

        public void setMerchLevelName(String merchLevelName) {
            this.merchLevelName = merchLevelName;
        }

        public long getInsertTime() {
            return insertTime;
        }

        public void setInsertTime(long insertTime) {
            this.insertTime = insertTime;
        }

        public List<MerchDataBean> getMerchData() {
            return merchData;
        }

        public void setMerchData(List<MerchDataBean> merchData) {
            this.merchData = merchData;
        }

        public String getTotalSubMerchName() {
            return totalSubMerchName;
        }

        public void setTotalSubMerchName(String totalSubMerchName) {
            this.totalSubMerchName = totalSubMerchName;
        }

        public String getSubMerchName() {
            return subMerchName;
        }

        public void setSubMerchName(String subMerchName) {
            this.subMerchName = subMerchName;
        }

        public String getSubSubMerchName() {
            return subSubMerchName;
        }

        public void setSubSubMerchName(String subSubMerchName) {
            this.subSubMerchName = subSubMerchName;
        }

        public static class MerchDataBean {
            /**
             * number : 0
             * levelName : 未注册
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
}