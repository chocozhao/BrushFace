package com.yzf.king.model.servicesmodels;

import com.yzf.king.model.BaseModel;

public class LoginResult extends BaseModel {

    /**
     * code : 200
     * data : {"merchStatus":"00","merchName":"韦俊星","isAgent":"1","phone":"15999519806","token":"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1NTA5MDY2NDIsIm1lcmNoSWQiOiIxMDAwMDAwMCJ9.Mxb6J6jlnnzno37YcErTCjOYrXYyvldTvE1KbHYwkok","merchId":"10000000","merchLevel":1}
     * message : 登录成功
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
         * merchStatus : 00
         * merchName : 韦俊星
         * isAgent : 1
         * phone : 15999519806
         * token : eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1NTA5MDY2NDIsIm1lcmNoSWQiOiIxMDAwMDAwMCJ9.Mxb6J6jlnnzno37YcErTCjOYrXYyvldTvE1KbHYwkok
         * merchId : 10000000
         * merchLevel : 1
         */

        private String merchStatus;
        private String merchName;
        private String isAgent;
        private String phone;
        private String token;
        private String merchId;
        private String merchLevel;
        private String agentFlag;
        private String agentLevel;
        private String usedFlag;
        private String shopFlag;
        private String servicePhone;
        private String merchlevelname;
        private String merchlevelname2;
        private String branchlevelname;
        private String shoplevelname;
        private String agentlevelname;

        public String getMerchStatus() {
            return merchStatus;
        }

        public void setMerchStatus(String merchStatus) {
            this.merchStatus = merchStatus;
        }

        public String getMerchName() {
            return merchName;
        }

        public void setMerchName(String merchName) {
            this.merchName = merchName;
        }

        public String getIsAgent() {
            return isAgent;
        }

        public void setIsAgent(String isAgent) {
            this.isAgent = isAgent;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
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

        public String getAgentFlag() {
            return agentFlag;
        }

        public void setAgentFlag(String agentFlag) {
            this.agentFlag = agentFlag;
        }

        public String getAgentLevel() {
            return agentLevel;
        }

        public void setAgentLevel(String agentLevel) {
            this.agentLevel = agentLevel;
        }

        public String getUsedFlag() {
            return usedFlag;
        }

        public void setUsedFlag(String usedFlag) {
            this.usedFlag = usedFlag;
        }

        public String getShopFlag() {
            return shopFlag;
        }

        public void setShopFlag(String shopFlag) {
            this.shopFlag = shopFlag;
        }

        public String getServicePhone() {
            return servicePhone;
        }

        public void setServicePhone(String servicePhone) {
            this.servicePhone = servicePhone;
        }

        public String getMerchlevelname() {
            return merchlevelname;
        }

        public void setMerchlevelname(String merchlevelname) {
            this.merchlevelname = merchlevelname;
        }

        public String getMerchlevelname2() {
            return merchlevelname2;
        }

        public void setMerchlevelname2(String merchlevelname2) {
            this.merchlevelname2 = merchlevelname2;
        }

        public String getBranchlevelname() {
            return branchlevelname;
        }

        public void setBranchlevelname(String branchlevelname) {
            this.branchlevelname = branchlevelname;
        }

        public String getShoplevelname() {
            return shoplevelname;
        }

        public void setShoplevelname(String shoplevelname) {
            this.shoplevelname = shoplevelname;
        }

        public String getAgentlevelname() {
            return agentlevelname;
        }

        public void setAgentlevelname(String agentlevelname) {
            this.agentlevelname = agentlevelname;
        }
    }
}