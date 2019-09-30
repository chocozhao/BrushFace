package com.yzf.king.model.servicesmodels;

import com.yzf.king.model.BaseModel;

import java.io.Serializable;

/**
 * ClaseName：PosPayConfirmResult
 * Description：
 * Author：JensenWei
 * QQ: 2188307188
 * Createtime：2019/3/8 14:56
 * Modified By：
 * Fixtime：2019/3/8 14:56
 * FixDescription：
 */
public class PosPayConfirmResult extends BaseModel {

    /**
     * code : 200
     * data : {"authCode":"529850","bankName":"广州银行股份有限公司","busCode":"5202","channelMerchId":"898581010010002","channelMerchName":"江山酒家番禺店","channelTermId":"50147005","orderId":"O20190308145418eCzo","orgName":"汇卡支付","respCode":"0000","respDesc":"支付成功","rfNumber":"01234560385421","termTransSn":"000001","userId":"10000000"}
     * message : 支付成功
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

    public static class DataBean implements Serializable {
        /**
         * authCode : 529850
         * bankName : 广州银行股份有限公司
         * busCode : 5202
         * channelMerchId : 898581010010002
         * channelMerchName : 江山酒家番禺店
         * channelTermId : 50147005
         * orderId : O20190308145418eCzo
         * orgName : 汇卡支付
         * respCode : 0000
         * respDesc : 支付成功
         * rfNumber : 01234560385421
         * termTransSn : 000001
         * userId : 10000000
         */

        private String authCode;
        private String bankName;
        private String busCode;
        private String channelMerchId;
        private String channelMerchName;
        private String channelTermId;
        private String orderId;
        private String orgName;
        private String respCode;
        private String respDesc;
        private String rfNumber;
        private String termTransSn;
        private String userId;

        public String getAuthCode() {
            return authCode;
        }

        public void setAuthCode(String authCode) {
            this.authCode = authCode;
        }

        public String getBankName() {
            return bankName;
        }

        public void setBankName(String bankName) {
            this.bankName = bankName;
        }

        public String getBusCode() {
            return busCode;
        }

        public void setBusCode(String busCode) {
            this.busCode = busCode;
        }

        public String getChannelMerchId() {
            return channelMerchId;
        }

        public void setChannelMerchId(String channelMerchId) {
            this.channelMerchId = channelMerchId;
        }

        public String getChannelMerchName() {
            return channelMerchName;
        }

        public void setChannelMerchName(String channelMerchName) {
            this.channelMerchName = channelMerchName;
        }

        public String getChannelTermId() {
            return channelTermId;
        }

        public void setChannelTermId(String channelTermId) {
            this.channelTermId = channelTermId;
        }

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public String getOrgName() {
            return orgName;
        }

        public void setOrgName(String orgName) {
            this.orgName = orgName;
        }

        public String getRespCode() {
            return respCode;
        }

        public void setRespCode(String respCode) {
            this.respCode = respCode;
        }

        public String getRespDesc() {
            return respDesc;
        }

        public void setRespDesc(String respDesc) {
            this.respDesc = respDesc;
        }

        public String getRfNumber() {
            return rfNumber;
        }

        public void setRfNumber(String rfNumber) {
            this.rfNumber = rfNumber;
        }

        public String getTermTransSn() {
            return termTransSn;
        }

        public void setTermTransSn(String termTransSn) {
            this.termTransSn = termTransSn;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }
    }
}
