package com.yzf.king.model.servicesmodels;


import com.yzf.king.model.BaseModel;

import java.util.List;

/**
 * ClaseName：GetOrdersDtl
 * Description：
 * Author：chocozhao
 * QQ: 1027313530
 * Createtime：2019/8/22 21:36
 * Modified By：
 * Fixtime：2019/8/22 21:36
 * FixDescription：
 **/

public class GetOrdersDtl extends BaseModel {


    /**
     * code : 200
     * data : {"dataDtl":[{"acctId":"A10000055","acctType":"01","channelCode":"wxface","channelOrderId":"4200000383201908166394326838","insertTime":1565946457000,"merchFee":0,"merchFeeAmt":0,"merchId":"10000025","merchSettAmt":0,"orderId":"Android201908161707296775","respCode":"0000","respDesc":"交易成功","settDate":"20190816","shopId":"20000037","status":1,"termId":"30000048","transAmt":10,"transSn":606,"transType":"04","updateTime":1565946459000,"version":3}],"sumAmt":10}
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
         * dataDtl : [{"acctId":"A10000055","acctType":"01","channelCode":"wxface","channelOrderId":"4200000383201908166394326838","insertTime":1565946457000,"merchFee":0,"merchFeeAmt":0,"merchId":"10000025","merchSettAmt":0,"orderId":"Android201908161707296775","respCode":"0000","respDesc":"交易成功","settDate":"20190816","shopId":"20000037","status":1,"termId":"30000048","transAmt":10,"transSn":606,"transType":"04","updateTime":1565946459000,"version":3}]
         * sumAmt : 10
         */

        private int sumAmt;
        private List<DataDtlBean> dataDtl;

        public int getSumAmt() {
            return sumAmt;
        }

        public void setSumAmt(int sumAmt) {
            this.sumAmt = sumAmt;
        }

        public List<DataDtlBean> getDataDtl() {
            return dataDtl;
        }

        public void setDataDtl(List<DataDtlBean> dataDtl) {
            this.dataDtl = dataDtl;
        }

        public static class DataDtlBean {
            /**
             * acctId : A10000055
             * acctType : 01
             * channelCode : wxface
             * channelOrderId : 4200000383201908166394326838
             * insertTime : 1565946457000
             * merchFee : 0
             * merchFeeAmt : 0
             * merchId : 10000025
             * merchSettAmt : 0
             * orderId : Android201908161707296775
             * respCode : 0000
             * respDesc : 交易成功
             * settDate : 20190816
             * shopId : 20000037
             * status : 1
             * termId : 30000048
             * transAmt : 10
             * transSn : 606
             * transType : 04
             * updateTime : 1565946459000
             * version : 3
             */

            private String acctId;
            private String acctType;
            private String channelCode;
            private String channelOrderId;
            private long insertTime;
            private int merchFee;
            private int merchFeeAmt;
            private String merchId;
            private int merchSettAmt;
            private String orderId;
            private String respCode;
            private String respDesc;
            private String settDate;
            private String shopId;
            private int status;
            private String termId;
            private int transAmt;
            private int transSn;
            private String transType;
            private long updateTime;
            private int version;

            public String getAcctId() {
                return acctId;
            }

            public void setAcctId(String acctId) {
                this.acctId = acctId;
            }

            public String getAcctType() {
                return acctType;
            }

            public void setAcctType(String acctType) {
                this.acctType = acctType;
            }

            public String getChannelCode() {
                return channelCode;
            }

            public void setChannelCode(String channelCode) {
                this.channelCode = channelCode;
            }

            public String getChannelOrderId() {
                return channelOrderId;
            }

            public void setChannelOrderId(String channelOrderId) {
                this.channelOrderId = channelOrderId;
            }

            public long getInsertTime() {
                return insertTime;
            }

            public void setInsertTime(long insertTime) {
                this.insertTime = insertTime;
            }

            public int getMerchFee() {
                return merchFee;
            }

            public void setMerchFee(int merchFee) {
                this.merchFee = merchFee;
            }

            public int getMerchFeeAmt() {
                return merchFeeAmt;
            }

            public void setMerchFeeAmt(int merchFeeAmt) {
                this.merchFeeAmt = merchFeeAmt;
            }

            public String getMerchId() {
                return merchId;
            }

            public void setMerchId(String merchId) {
                this.merchId = merchId;
            }

            public int getMerchSettAmt() {
                return merchSettAmt;
            }

            public void setMerchSettAmt(int merchSettAmt) {
                this.merchSettAmt = merchSettAmt;
            }

            public String getOrderId() {
                return orderId;
            }

            public void setOrderId(String orderId) {
                this.orderId = orderId;
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

            public String getSettDate() {
                return settDate;
            }

            public void setSettDate(String settDate) {
                this.settDate = settDate;
            }

            public String getShopId() {
                return shopId;
            }

            public void setShopId(String shopId) {
                this.shopId = shopId;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getTermId() {
                return termId;
            }

            public void setTermId(String termId) {
                this.termId = termId;
            }

            public int getTransAmt() {
                return transAmt;
            }

            public void setTransAmt(int transAmt) {
                this.transAmt = transAmt;
            }

            public int getTransSn() {
                return transSn;
            }

            public void setTransSn(int transSn) {
                this.transSn = transSn;
            }

            public String getTransType() {
                return transType;
            }

            public void setTransType(String transType) {
                this.transType = transType;
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
        }
    }
}
