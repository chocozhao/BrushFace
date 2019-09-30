package com.yzf.king.model.servicesmodels;


import com.yzf.king.model.BaseModel;

/**
 * ClaseName：GetCardOperateResult
 * Description：
 * Author：chocozhao
 * QQ: 1027313530
 * Createtime：2019/7/12 17:38
 * Modified By：
 * Fixtime：2019/7/12 17:38
 * FixDescription：
 **/

public class GetCardOperateResult  extends BaseModel {


    /**
     * code : 200
     * data : {"object":{"bankAbbr":"CMB","bankBin":"621483","bankCode":"03080000","bankName":"招商银行","cardId":"6214832034158753","cardType":"01","chanlInstNo":"80015810","channelOrderNo":"Android201907121753297347","id":76,"idName":"农本勇","idNo":"452122198604223017","insertTime":1562925210000,"orderId":"Android201907121753297347","phoneNo":"18620028795","queryTimes":3,"reqNo":110,"respCode":"0000","respDesc":"实名认证通过","settAmt":0,"settDate":"20190711","settleBankCnaps":"308584007998","status":2,"transAmt":0,"transType":"23","updateTime":1562925210000,"userId":"10000001","version":3},"respCode":"0000","respDesc":"实名认证通过","termTransSn":"110"}
     * message : 认证成功
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
         * object : {"bankAbbr":"CMB","bankBin":"621483","bankCode":"03080000","bankName":"招商银行","cardId":"6214832034158753","cardType":"01","chanlInstNo":"80015810","channelOrderNo":"Android201907121753297347","id":76,"idName":"农本勇","idNo":"452122198604223017","insertTime":1562925210000,"orderId":"Android201907121753297347","phoneNo":"18620028795","queryTimes":3,"reqNo":110,"respCode":"0000","respDesc":"实名认证通过","settAmt":0,"settDate":"20190711","settleBankCnaps":"308584007998","status":2,"transAmt":0,"transType":"23","updateTime":1562925210000,"userId":"10000001","version":3}
         * respCode : 0000
         * respDesc : 实名认证通过
         * termTransSn : 110
         */

        private ObjectBean object;
        private String respCode;
        private String respDesc;
        private String termTransSn;

        public ObjectBean getObject() {
            return object;
        }

        public void setObject(ObjectBean object) {
            this.object = object;
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

        public String getTermTransSn() {
            return termTransSn;
        }

        public void setTermTransSn(String termTransSn) {
            this.termTransSn = termTransSn;
        }

        public static class ObjectBean {
            /**
             * bankAbbr : CMB
             * bankBin : 621483
             * bankCode : 03080000
             * bankName : 招商银行
             * cardId : 6214832034158753
             * cardType : 01
             * chanlInstNo : 80015810
             * channelOrderNo : Android201907121753297347
             * id : 76
             * idName : 农本勇
             * idNo : 452122198604223017
             * insertTime : 1562925210000
             * orderId : Android201907121753297347
             * phoneNo : 18620028795
             * queryTimes : 3
             * reqNo : 110
             * respCode : 0000
             * respDesc : 实名认证通过
             * settAmt : 0
             * settDate : 20190711
             * settleBankCnaps : 308584007998
             * status : 2
             * transAmt : 0
             * transType : 23
             * updateTime : 1562925210000
             * userId : 10000001
             * version : 3
             */

            private String bankAbbr;
            private String bankBin;
            private String bankCode;
            private String bankName;
            private String cardId;
            private String cardType;
            private String chanlInstNo;
            private String channelOrderNo;
            private int id;
            private String idName;
            private String idNo;
            private long insertTime;
            private String orderId;
            private String phoneNo;
            private int queryTimes;
            private int reqNo;
            private String respCode;
            private String respDesc;
            private int settAmt;
            private String settDate;
            private String settleBankCnaps;
            private int status;
            private int transAmt;
            private String transType;
            private long updateTime;
            private String userId;
            private int version;

            public String getBankAbbr() {
                return bankAbbr;
            }

            public void setBankAbbr(String bankAbbr) {
                this.bankAbbr = bankAbbr;
            }

            public String getBankBin() {
                return bankBin;
            }

            public void setBankBin(String bankBin) {
                this.bankBin = bankBin;
            }

            public String getBankCode() {
                return bankCode;
            }

            public void setBankCode(String bankCode) {
                this.bankCode = bankCode;
            }

            public String getBankName() {
                return bankName;
            }

            public void setBankName(String bankName) {
                this.bankName = bankName;
            }

            public String getCardId() {
                return cardId;
            }

            public void setCardId(String cardId) {
                this.cardId = cardId;
            }

            public String getCardType() {
                return cardType;
            }

            public void setCardType(String cardType) {
                this.cardType = cardType;
            }

            public String getChanlInstNo() {
                return chanlInstNo;
            }

            public void setChanlInstNo(String chanlInstNo) {
                this.chanlInstNo = chanlInstNo;
            }

            public String getChannelOrderNo() {
                return channelOrderNo;
            }

            public void setChannelOrderNo(String channelOrderNo) {
                this.channelOrderNo = channelOrderNo;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
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

            public String getOrderId() {
                return orderId;
            }

            public void setOrderId(String orderId) {
                this.orderId = orderId;
            }

            public String getPhoneNo() {
                return phoneNo;
            }

            public void setPhoneNo(String phoneNo) {
                this.phoneNo = phoneNo;
            }

            public int getQueryTimes() {
                return queryTimes;
            }

            public void setQueryTimes(int queryTimes) {
                this.queryTimes = queryTimes;
            }

            public int getReqNo() {
                return reqNo;
            }

            public void setReqNo(int reqNo) {
                this.reqNo = reqNo;
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

            public int getSettAmt() {
                return settAmt;
            }

            public void setSettAmt(int settAmt) {
                this.settAmt = settAmt;
            }

            public String getSettDate() {
                return settDate;
            }

            public void setSettDate(String settDate) {
                this.settDate = settDate;
            }

            public String getSettleBankCnaps() {
                return settleBankCnaps;
            }

            public void setSettleBankCnaps(String settleBankCnaps) {
                this.settleBankCnaps = settleBankCnaps;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public int getTransAmt() {
                return transAmt;
            }

            public void setTransAmt(int transAmt) {
                this.transAmt = transAmt;
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

            public String getUserId() {
                return userId;
            }

            public void setUserId(String userId) {
                this.userId = userId;
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
