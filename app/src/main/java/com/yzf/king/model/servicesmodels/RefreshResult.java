package com.yzf.king.model.servicesmodels;

import com.yzf.king.model.BaseModel;

/**
 * ClaseName：RefreshResult
 * Description：
 * Author：JensenWei
 * QQ: 2188307188
 * Createtime：2019/4/9 14:32
 * Modified By：
 * Fixtime：2019/4/9 14:32
 * FixDescription：
 */
public class RefreshResult extends BaseModel {

    /**
     * code : 200
     * data : {"merchStatus":"00","merchName":"农本勇","isAgent":"1","phone":"18620028795","levelName":"认证会员","usedFlag":0,"sumAmt":38.94,"token":"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1NTQ4Nzc4NjAsIm1lcmNoSWQiOiIxMDAwMDAyNyJ9.HnM4l5JWJ7i_YKt4AXnHR2jXrmOE6FTZoYInLyRuzQw","merchId":"10000027","merchLevel":0}
     * message : 刷新成功
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
         * merchName : 农本勇
         * isAgent : 1
         * phone : 18620028795
         * levelName : 认证会员
         * usedFlag : 0
         * sumAmt : 38.94
         * token : eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1NTQ4Nzc4NjAsIm1lcmNoSWQiOiIxMDAwMDAyNyJ9.HnM4l5JWJ7i_YKt4AXnHR2jXrmOE6FTZoYInLyRuzQw
         * merchId : 10000027
         * merchLevel : 0
         */

        private String merchStatus;
        private String merchName;
        private String isAgent;
        private String phone;
        private String levelName;
        private String usedFlag;
        private String sumAmt;
        private String acctBal;
        private String token;
        private String merchId;
        private String merchLevel;
        private String agentFlag;
        private String agentLevel;
        private String myTermNum;
        private String teamTermNum;
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

        public String getLevelName() {
            return levelName;
        }

        public void setLevelName(String levelName) {
            this.levelName = levelName;
        }

        public String getUsedFlag() {
            return usedFlag;
        }

        public void setUsedFlag(String usedFlag) {
            this.usedFlag = usedFlag;
        }

        public String getSumAmt() {
            return sumAmt;
        }

        public void setSumAmt(String sumAmt) {
            this.sumAmt = sumAmt;
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

        public String getAcctBal() {
            return acctBal;
        }

        public void setAcctBal(String acctBal) {
            this.acctBal = acctBal;
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

        public String getMyTermNum() {
            return myTermNum;
        }

        public void setMyTermNum(String myTermNum) {
            this.myTermNum = myTermNum;
        }

        public String getTeamTermNum() {
            return teamTermNum;
        }

        public void setTeamTermNum(String teamTermNum) {
            this.teamTermNum = teamTermNum;
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
