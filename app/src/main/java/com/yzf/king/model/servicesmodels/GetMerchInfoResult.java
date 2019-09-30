package com.yzf.king.model.servicesmodels;

import com.yzf.king.model.BaseModel;

public class GetMerchInfoResult extends BaseModel {

    /**
     * code : 200
     * data : {"acctId":"A10000001","agentFlag":0,"agentLevel":1,"bankAbbr":"CMB","feeId":0,"idName":"农本勇","idNo":"452122198604223017","insertTime":1562644515000,"merchId":"10000001","merchLevel":1,"merchName":"明天会更好","password":"dd4346edbd089a1e39e731834b77d4f5","phone":"13144443548","registerTime":1562644515000,"relationLevel":1,"routeId":0,"settleBankCode":"03080000","settleBankName":"招商银行","settleCardNo":"6214832034158753","settlePhone":"18620028795","status":"00","updateTime":1562925923000,"version":0}
     * message : 查询成功
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
         * acctId : A10000001
         * agentFlag : 0
         * agentLevel : 1
         * bankAbbr : CMB
         * feeId : 0
         * idName : 农本勇
         * idNo : 452122198604223017
         * insertTime : 1562644515000
         * merchId : 10000001
         * merchLevel : 1
         * merchName : 明天会更好
         * password : dd4346edbd089a1e39e731834b77d4f5
         * phone : 13144443548
         * registerTime : 1562644515000
         * relationLevel : 1
         * routeId : 0
         * settleBankCode : 03080000
         * settleBankName : 招商银行
         * settleCardNo : 6214832034158753
         * settlePhone : 18620028795
         * status : 00
         * updateTime : 1562925923000
         * version : 0
         */

        private String acctId;
        private int agentFlag;
        private int agentLevel;
        private String bankAbbr;
        private int feeId;
        private String idName;
        private String idNo;
        private long insertTime;
        private String merchId;
        private String merchLevel;
        private String merchName;
        private String password;
        private String phone;
        private long registerTime;
        private int relationLevel;
        private int routeId;
        private String settleBankCode;
        private String settleBankName;
        private String settleCardNo;
        private String settlePhone;
        private String status;
        private long updateTime;
        private int version;
        private int shopFlag;
        private int branchFlag;

        public String getAcctId() {
            return acctId;
        }

        public void setAcctId(String acctId) {
            this.acctId = acctId;
        }

        public int getAgentFlag() {
            return agentFlag;
        }

        public void setAgentFlag(int agentFlag) {
            this.agentFlag = agentFlag;
        }

        public int getAgentLevel() {
            return agentLevel;
        }

        public void setAgentLevel(int agentLevel) {
            this.agentLevel = agentLevel;
        }

        public String getBankAbbr() {
            return bankAbbr;
        }

        public void setBankAbbr(String bankAbbr) {
            this.bankAbbr = bankAbbr;
        }

        public int getFeeId() {
            return feeId;
        }

        public void setFeeId(int feeId) {
            this.feeId = feeId;
        }

        public String getIdName() {
            return idName;
        }

        public void setIdName(String idName) {
            this.idName = idName;
        }

        public String getIdNo() {
            return idNo;
        }

        public void setIdNo(String idNo) {
            this.idNo = idNo;
        }

        public long getInsertTime() {
            return insertTime;
        }

        public void setInsertTime(long insertTime) {
            this.insertTime = insertTime;
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

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public long getRegisterTime() {
            return registerTime;
        }

        public void setRegisterTime(long registerTime) {
            this.registerTime = registerTime;
        }

        public int getRelationLevel() {
            return relationLevel;
        }

        public void setRelationLevel(int relationLevel) {
            this.relationLevel = relationLevel;
        }

        public int getRouteId() {
            return routeId;
        }

        public void setRouteId(int routeId) {
            this.routeId = routeId;
        }

        public String getSettleBankCode() {
            return settleBankCode;
        }

        public void setSettleBankCode(String settleBankCode) {
            this.settleBankCode = settleBankCode;
        }

        public String getSettleBankName() {
            return settleBankName;
        }

        public void setSettleBankName(String settleBankName) {
            this.settleBankName = settleBankName;
        }

        public String getSettleCardNo() {
            return settleCardNo;
        }

        public void setSettleCardNo(String settleCardNo) {
            this.settleCardNo = settleCardNo;
        }

        public String getSettlePhone() {
            return settlePhone;
        }

        public void setSettlePhone(String settlePhone) {
            this.settlePhone = settlePhone;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public long getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(long updateTime) {
            this.updateTime = updateTime;
        }

        public int getVersion() {
            return version;
        }

        public void setVersion(int version) {
            this.version = version;
        }

        public int getShopFlag() {
            return shopFlag;
        }

        public void setShopFlag(int shopFlag) {
            this.shopFlag = shopFlag;
        }

        public int getBranchFlag() {
            return branchFlag;
        }

        public void setBranchFlag(int branchFlag) {
            this.branchFlag = branchFlag;
        }
    }
}