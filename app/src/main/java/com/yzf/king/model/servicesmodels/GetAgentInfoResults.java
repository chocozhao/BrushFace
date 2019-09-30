package com.yzf.king.model.servicesmodels;


import com.yzf.king.model.BaseModel;

import java.util.List;

/**
 * ClaseName：GetAgentInfoResults
 * Description：
 * Author：chocozhao
 * QQ: 1027313530
 * Createtime：2019/8/27 15:06
 * Modified By：
 * Fixtime：2019/8/27 15:06
 * FixDescription：
 **/

public class GetAgentInfoResults extends BaseModel {

    /**
     * code : 200
     * data : [{"acctId":"A10000058","agentFlag":1,"agentLevel":2,"branchCode":"00000000","branchFlag":0,"branchLevel":1,"feeId":0,"insertTime":1565664926000,"merchId":" ","merchLevel":2,"merchName":"花花分公司","password":"dd4346edbd089a1e39e731834b77d4f5","phone":"13144440027","registerTime":1565664926000,"relationLevel":1,"routeId":0,"shopFlag":1,"status":"03","updateTime":1565777322000,"version":0},{"acctId":"A10000022","agentFlag":1,"agentLevel":3,"branchCode":"00000000","branchFlag":0,"branchLevel":1,"feeId":0,"insertTime":1563507216000,"merchId":"10000022","merchLevel":2,"merchName":"A迪丽热巴","password":"dd4346edbd089a1e39e731834b77d4f5","phone":"13144440010","registerTime":1563507216000,"relationLevel":1,"routeId":0,"shopFlag":1,"status":"03","updateTime":1565573618000,"version":0},{"acctId":"A10000023","agentFlag":1,"agentLevel":3,"branchCode":"00000000","branchFlag":0,"branchLevel":1,"feeId":0,"insertTime":1563507277000,"merchId":"10000023","merchLevel":2,"merchName":"B杨幂","merchRelation":"10000022","password":"dd4346edbd089a1e39e731834b77d4f5","phone":"13144440011","registerTime":1563507277000,"relationLevel":2,"routeId":0,"shopFlag":0,"status":"03","updateTime":1563760995000,"version":0},{"acctId":"A10000024","agentFlag":1,"agentLevel":3,"bankAbbr":"COMM","branchCode":"10000024","branchFlag":1,"branchLevel":2,"feeId":0,"idName":"农本勇","idNo":"452122198604223017","insertTime":1563507459000,"merchId":"10000024","merchLevel":2,"merchName":"CAngelababy","merchRelation":"1000002210000023","password":"dd4346edbd089a1e39e731834b77d4f5","phone":"13144440012","registerTime":1563507459000,"relationLevel":3,"routeId":0,"settleBankCode":"03010000","settleBankName":"交通银行","settleCardNo":"6222620710019715893","settlePhone":"18620028795","shopFlag":1,"status":"00","updateTime":1565232451000,"version":0},{"acctId":"A10000025","agentFlag":1,"agentLevel":3,"branchCode":"10000024","branchFlag":0,"branchLevel":1,"feeId":0,"insertTime":1563507539000,"merchId":"10000025","merchLevel":1,"merchName":"D蔡徐坤","merchRelation":"100000221000002310000024","password":"dd4346edbd089a1e39e731834b77d4f5","phone":"13144440013","registerTime":1563507539000,"relationLevel":4,"routeId":0,"shopFlag":1,"status":"03","updateTime":1565232451000,"version":0},{"acctId":"A10000027","agentFlag":1,"agentLevel":1,"bankAbbr":"COMM","branchCode":"10000024","branchFlag":0,"branchLevel":1,"feeId":0,"idName":"农本勇","idNo":"452122198604223017","insertTime":1563507748000,"merchId":"10000027","merchLevel":2,"merchName":"F鹿晗","merchRelation":"100000221000002310000024","password":"dd4346edbd089a1e39e731834b77d4f5","phone":"13144440015","registerTime":1563507748000,"relationLevel":4,"routeId":0,"settleBankCode":"03010000","settleBankName":"交通银行","settleCardNo":"6222620710019715893","settlePhone":"18620028795","shopFlag":0,"status":"00","updateTime":1565232451000,"version":0},{"acctId":"A10000028","agentFlag":1,"agentLevel":2,"branchCode":"10000024","branchFlag":0,"branchLevel":1,"feeId":0,"insertTime":1563507921000,"merchId":"10000028","merchLevel":2,"merchName":"D1袁咏仪","merchRelation":"10000022100000231000002410000025","password":"dd4346edbd089a1e39e731834b77d4f5","phone":"13144440016","registerTime":1563507921000,"relationLevel":5,"routeId":0,"shopFlag":1,"status":"03","updateTime":1565232451000,"version":0},{"acctId":"A10000029","agentFlag":1,"agentLevel":2,"branchCode":"10000024","branchFlag":0,"branchLevel":1,"feeId":0,"insertTime":1563507954000,"merchId":"10000029","merchLevel":2,"merchName":"D2张智霖","merchRelation":"10000022100000231000002410000025","password":"dd4346edbd089a1e39e731834b77d4f5","phone":"13144440017","registerTime":1563507954000,"relationLevel":5,"routeId":0,"shopFlag":0,"status":"03","updateTime":1565232451000,"version":0},{"acctId":"A10000046","agentFlag":1,"agentLevel":3,"branchCode":"10000024","branchFlag":0,"branchLevel":1,"feeId":0,"insertTime":1564650809000,"merchId":"10000046","merchLevel":1,"merchName":"邓伦","merchRelation":"100000221000002310000024","password":"dd4346edbd089a1e39e731834b77d4f5","phone":"13144440022","registerTime":1564650809000,"relationLevel":4,"routeId":0,"shopFlag":1,"status":"03","updateTime":1565232451000,"version":0},{"acctId":"A10000047","agentFlag":1,"agentLevel":3,"branchCode":"10000047","branchFlag":1,"branchLevel":1,"feeId":0,"insertTime":1565257795000,"merchId":"10000047","merchLevel":2,"merchName":"辛戈曼","password":"dd4346edbd089a1e39e731834b77d4f5","phone":"17871559499","registerTime":1565257795000,"relationLevel":1,"routeId":0,"shopFlag":1,"status":"03","updateTime":1566309109000,"version":0},{"acctId":"A10000054","agentFlag":1,"agentLevel":2,"branchCode":"00000000","branchFlag":0,"branchLevel":1,"feeId":0,"insertTime":1565596036000,"merchId":"10000054","merchLevel":1,"merchName":"苏亚波","password":"dd4346edbd089a1e39e731834b77d4f5","phone":"13144440023","registerTime":1565596036000,"relationLevel":1,"routeId":0,"shopFlag":1,"status":"03","updateTime":1565596090000,"version":0},{"acctId":"A10000055","agentFlag":1,"agentLevel":2,"branchCode":"00000000","branchFlag":0,"branchLevel":1,"feeId":0,"insertTime":1565596310000,"merchId":"10000055","merchLevel":2,"merchName":"总公司","password":"dd4346edbd089a1e39e731834b77d4f5","phone":"18620028795","registerTime":1565596310000,"relationLevel":1,"routeId":0,"shopFlag":1,"status":"03","updateTime":1566802014000,"version":0},{"acctId":"A10000056","agentFlag":1,"agentLevel":3,"branchCode":"00000000","branchFlag":0,"branchLevel":1,"feeId":0,"insertTime":1565602935000,"merchId":"10000056","merchLevel":1,"merchName":"测试","password":"dd4346edbd089a1e39e731834b77d4f5","phone":"13144440024","registerTime":1565602935000,"relationLevel":1,"routeId":0,"shopFlag":1,"status":"03","updateTime":1565603154000,"version":0}]
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
         * acctId : A10000058
         * agentFlag : 1
         * agentLevel : 2
         * branchCode : 00000000
         * branchFlag : 0
         * branchLevel : 1
         * feeId : 0
         * insertTime : 1565664926000
         * merchId :
         * merchLevel : 2
         * merchName : 花花分公司
         * password : dd4346edbd089a1e39e731834b77d4f5
         * phone : 13144440027
         * registerTime : 1565664926000
         * relationLevel : 1
         * routeId : 0
         * shopFlag : 1
         * status : 03
         * updateTime : 1565777322000
         * version : 0
         * merchRelation : 10000022
         * bankAbbr : COMM
         * idName : 农本勇
         * idNo : 452122198604223017
         * settleBankCode : 03010000
         * settleBankName : 交通银行
         * settleCardNo : 6222620710019715893
         * settlePhone : 18620028795
         */

        private String acctId;
        private int agentFlag;
        private int agentLevel;
        private String branchCode;
        private int branchFlag;
        private int branchLevel;
        private int feeId;
        private long insertTime;
        private String merchId;
        private int merchLevel;
        private String merchName;
        private String password;
        private String phone;
        private long registerTime;
        private int relationLevel;
        private int routeId;
        private int shopFlag;
        private String status;
        private long updateTime;
        private int version;
        private String merchRelation;
        private String bankAbbr;
        private String idName;
        private String idNo;
        private String settleBankCode;
        private String settleBankName;
        private String settleCardNo;
        private String settlePhone;

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

        public String getBranchCode() {
            return branchCode;
        }

        public void setBranchCode(String branchCode) {
            this.branchCode = branchCode;
        }

        public int getBranchFlag() {
            return branchFlag;
        }

        public void setBranchFlag(int branchFlag) {
            this.branchFlag = branchFlag;
        }

        public int getBranchLevel() {
            return branchLevel;
        }

        public void setBranchLevel(int branchLevel) {
            this.branchLevel = branchLevel;
        }

        public int getFeeId() {
            return feeId;
        }

        public void setFeeId(int feeId) {
            this.feeId = feeId;
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

        public int getMerchLevel() {
            return merchLevel;
        }

        public void setMerchLevel(int merchLevel) {
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

        public int getShopFlag() {
            return shopFlag;
        }

        public void setShopFlag(int shopFlag) {
            this.shopFlag = shopFlag;
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

        public String getMerchRelation() {
            return merchRelation;
        }

        public void setMerchRelation(String merchRelation) {
            this.merchRelation = merchRelation;
        }

        public String getBankAbbr() {
            return bankAbbr;
        }

        public void setBankAbbr(String bankAbbr) {
            this.bankAbbr = bankAbbr;
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
    }
}
