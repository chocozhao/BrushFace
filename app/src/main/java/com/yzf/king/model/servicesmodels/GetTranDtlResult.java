package com.yzf.king.model.servicesmodels;

import com.yzf.king.kit.AppTools;
import com.yzf.king.model.BaseModel;

import java.io.Serializable;
import java.util.List;

/**
 * ClaseName：GetTranDtlResult
 * Description：
 * Author：JensenWei
 * QQ: 2188307188
 * Createtime：2019/2/23 15:55
 * Modified By：
 * Fixtime：2019/2/23 15:55
 * FixDescription：
 */
public class GetTranDtlResult extends BaseModel {


    /**
     * code : 200
     * data : [{"acctId":"A10000024","acctType":"01","cardId":"6222620710019715893","channelCode":"84005810","channelOrderId":"wdO20190721212746zPpD","extenFee":200,"insertTime":1563715666000,"merchFee":260,"merchFeeAmt":60,"merchId":"10000024","merchSettAmt":1060,"orderId":"iOS201907212127452426","respCode":"0000","respDesc":"交易成功","settDate":"20190721","status":1,"transAmt":1000,"transSn":291,"transType":"21","updateTime":1563715687000,"version":3}]
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

    public static class DataBean implements Serializable {
        /**
         * acctId : A10000024
         * acctType : 01
         * cardId : 6222620710019715893
         * channelCode : 84005810
         * channelOrderId : wdO20190721212746zPpD
         * extenFee : 200
         * insertTime : 1563715666000
         * merchFee : 260
         * merchFeeAmt : 60
         * merchId : 10000024
         * merchSettAmt : 1060
         * orderId : iOS201907212127452426
         * respCode : 0000
         * respDesc : 交易成功
         * settDate : 20190721
         * status : 1
         * transAmt : 1000
         * transSn : 291
         * transType : 21
         * updateTime : 1563715687000
         * version : 3
         */

        private String acctId;
        private String acctType;
        private String cardId;
        private String channelCode;
        private String channelOrderId;
        private int extenFee;
        private long insertTime;
        private String merchFee;
        private int merchFeeAmt;
        private String merchId;
        private String merchSettAmt;
        private String orderId;
        private String respCode;
        private String respDesc;
        private String settDate;
        private int status;
        private String transAmt;
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

        public String getCardId() {
            return cardId;
        }

        public void setCardId(String cardId) {
            this.cardId = cardId;
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

        public int getExtenFee() {
            return extenFee;
        }

        public void setExtenFee(int extenFee) {
            this.extenFee = extenFee;
        }

        public long getInsertTime() {
            return insertTime;
        }

        public void setInsertTime(long insertTime) {
            this.insertTime = insertTime;
        }

        public String getMerchFee() {
            return AppTools.formatF2Y(merchFee);
        }

        public void setMerchFee(String merchFee) {
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

        public String getMerchSettAmt() {
            return AppTools.formatF2Y(merchSettAmt);
        }

        public void setMerchSettAmt(String merchSettAmt) {
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

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getTransAmt() {
            return AppTools.formatF2Y(transAmt);
        }

        public void setTransAmt(String transAmt) {
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
