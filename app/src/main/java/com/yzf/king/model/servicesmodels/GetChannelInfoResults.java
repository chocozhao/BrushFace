package com.yzf.king.model.servicesmodels;

import com.yzf.king.model.BaseModel;

import java.util.List;

/**
 * ClaseName：GetChannelInfoResults
 * Description：
 * Author：JensenWei
 * QQ: 2188307188
 * Createtime：2018/4/10 15:30
 * Modified By：
 * Fixtime：2018/4/10 15:30
 * FixDescription：
 */

public class GetChannelInfoResults extends BaseModel{

    /**
     * code : 200
     * data : [{"incomeFlag":"1","channelId":"80015810","status":"0","userCode":null,"branchDesc":"线上商户","bindCardFlag":"0","cardId":"6225768771872395","userKey":null,"branchId":"80015810","bindCardType":0,"action":"SdkUserStoreBind","branchName":"渠道3","imageFlag":"0"},{"incomeFlag":"1","channelId":"80035810","status":"0","userCode":null,"branchDesc":"线上商户","bindCardFlag":"1","cardId":"6225768771872395","userKey":null,"branchId":"70435812","bindCardType":1,"action":"SdkUserStoreBind","branchName":"渠道2","imageFlag":"0"},{"incomeFlag":"1","channelId":"80055810","status":"0","userCode":null,"branchDesc":"落地商户","bindCardFlag":"1","cardId":"6225768771872395","userKey":null,"branchId":"70500000","bindCardType":2,"action":"SdkUserStoreBind7050","branchName":"渠道1","imageFlag":"1"},{"incomeFlag":"1","channelId":"80065810","status":"0","userCode":null,"branchDesc":"落地商户","bindCardFlag":"1","cardId":"6225768771872395","userKey":null,"branchId":"70600000","bindCardType":3,"action":"SdkUserStoreBind7060","branchName":"渠道4","imageFlag":"0"}]
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
         * incomeFlag : 1
         * channelId : 80015810
         * status : 0
         * userCode : null
         * branchDesc : 线上商户
         * bindCardFlag : 0
         * cardId : 6225768771872395
         * userKey : null
         * branchId : 80015810
         * bindCardType : 0
         * action : SdkUserStoreBind
         * branchName : 渠道3
         * imageFlag : 0
         */

        private String incomeFlag;
        private String channelId;
        private String status;
        private String userCode;
        private String branchDesc;
        private String bindCardFlag;
        private String cardId;
        private String userKey;
        private String branchId;
        private String bindCardType;
        private String action;
        private String branchName;
        private String imageFlag;
        private String needRegister;
        private String merchId;
        private String token;

        public String getIncomeFlag() {
            return incomeFlag;
        }

        public void setIncomeFlag(String incomeFlag) {
            this.incomeFlag = incomeFlag;
        }

        public String getChannelId() {
            return channelId;
        }

        public void setChannelId(String channelId) {
            this.channelId = channelId;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getUserCode() {
            return userCode;
        }

        public void setUserCode(String userCode) {
            this.userCode = userCode;
        }

        public String getBranchDesc() {
            return branchDesc;
        }

        public void setBranchDesc(String branchDesc) {
            this.branchDesc = branchDesc;
        }

        public String getBindCardFlag() {
            return bindCardFlag;
        }

        public void setBindCardFlag(String bindCardFlag) {
            this.bindCardFlag = bindCardFlag;
        }

        public String getCardId() {
            return cardId;
        }

        public void setCardId(String cardId) {
            this.cardId = cardId;
        }

        public String getUserKey() {
            return userKey;
        }

        public void setUserKey(String userKey) {
            this.userKey = userKey;
        }

        public String getBranchId() {
            return branchId;
        }

        public void setBranchId(String branchId) {
            this.branchId = branchId;
        }

        public String getBindCardType() {
            return bindCardType;
        }

        public void setBindCardType(String bindCardType) {
            this.bindCardType = bindCardType;
        }

        public String getAction() {
            return action;
        }

        public void setAction(String action) {
            this.action = action;
        }

        public String getBranchName() {
            return branchName;
        }

        public void setBranchName(String branchName) {
            this.branchName = branchName;
        }

        public String getImageFlag() {
            return imageFlag;
        }

        public void setImageFlag(String imageFlag) {
            this.imageFlag = imageFlag;
        }

        public String getNeedRegister() {
            return needRegister;
        }

        public void setNeedRegister(String needRegister) {
            this.needRegister = needRegister;
        }

        public String getMerchId() {
            return merchId;
        }

        public void setMerchId(String merchId) {
            this.merchId = merchId;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}
