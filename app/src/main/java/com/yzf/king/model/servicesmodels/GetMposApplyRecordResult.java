package com.yzf.king.model.servicesmodels;

import com.yzf.king.model.BaseModel;

/**
 * ClaseName：GetMposApplyRecordResult
 * Description：
 * Author：JensenWei
 * QQ: 2188307188
 * Createtime：2018/12/14 11:59
 * Modified By：
 * Fixtime：2018/12/14 11:59
 * FixDescription：
 */
public class GetMposApplyRecordResult extends BaseModel {

    /**
     * code : 200
     * data : {"add1":"","add2":"","add3":"","addressDtl":"12312","addressName":"","addressPhone":"15999519806","applyStatus":0,"idName":"1231","idNo":"1231","insertTime":1544667050000,"merchId":"15999519806","merchName":"111","orderId":"O20181213171536SqeK","payStatus":0,"receiptDate":"","receiptStatus":0,"settleCardBank":"1231","settleCardNo":"12312","settleCardPhone":"15999519806","shipCompany":"","shipDate":"","shipOrderId":"","shipStatus":0,"transType":"44","updateTime":1544667050000,"verifyDesc":"","verifyStatus":0}
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
         * add1 :
         * add2 :
         * add3 :
         * addressDtl : 12312
         * addressName :
         * addressPhone : 15999519806
         * applyStatus : 0
         * idName : 1231
         * idNo : 1231
         * insertTime : 1544667050000
         * merchId : 15999519806
         * merchName : 111
         * orderId : O20181213171536SqeK
         * payStatus : 0
         * receiptDate :
         * receiptStatus : 0
         * settleCardBank : 1231
         * settleCardNo : 12312
         * settleCardPhone : 15999519806
         * shipCompany :
         * shipDate :
         * shipOrderId :
         * shipStatus : 0
         * transType : 44
         * updateTime : 1544667050000
         * verifyDesc :
         * verifyStatus : 0
         */

        private String add1;
        private String add2;
        private String add3;
        private String addressDtl;
        private String addressName;
        private String addressPhone;
        private int applyStatus;
        private String idName;
        private String idNo;
        private long insertTime;
        private String merchId;
        private String merchName;
        private String orderId;
        private int payStatus;
        private String receiptDate;
        private int receiptStatus;
        private String settleCardBank;
        private String settleCardNo;
        private String settleCardPhone;
        private String shipCompany;
        private String shipDate;
        private String shipOrderId;
        private int shipStatus;
        private String transType;
        private long updateTime;
        private String verifyDesc;
        private int verifyStatus;

        public String getAdd1() {
            return add1;
        }

        public void setAdd1(String add1) {
            this.add1 = add1;
        }

        public String getAdd2() {
            return add2;
        }

        public void setAdd2(String add2) {
            this.add2 = add2;
        }

        public String getAdd3() {
            return add3;
        }

        public void setAdd3(String add3) {
            this.add3 = add3;
        }

        public String getAddressDtl() {
            return addressDtl;
        }

        public void setAddressDtl(String addressDtl) {
            this.addressDtl = addressDtl;
        }

        public String getAddressName() {
            return addressName;
        }

        public void setAddressName(String addressName) {
            this.addressName = addressName;
        }

        public String getAddressPhone() {
            return addressPhone;
        }

        public void setAddressPhone(String addressPhone) {
            this.addressPhone = addressPhone;
        }

        public int getApplyStatus() {
            return applyStatus;
        }

        public void setApplyStatus(int applyStatus) {
            this.applyStatus = applyStatus;
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

        public String getMerchName() {
            return merchName;
        }

        public void setMerchName(String merchName) {
            this.merchName = merchName;
        }

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public int getPayStatus() {
            return payStatus;
        }

        public void setPayStatus(int payStatus) {
            this.payStatus = payStatus;
        }

        public String getReceiptDate() {
            return receiptDate;
        }

        public void setReceiptDate(String receiptDate) {
            this.receiptDate = receiptDate;
        }

        public int getReceiptStatus() {
            return receiptStatus;
        }

        public void setReceiptStatus(int receiptStatus) {
            this.receiptStatus = receiptStatus;
        }

        public String getSettleCardBank() {
            return settleCardBank;
        }

        public void setSettleCardBank(String settleCardBank) {
            this.settleCardBank = settleCardBank;
        }

        public String getSettleCardNo() {
            return settleCardNo;
        }

        public void setSettleCardNo(String settleCardNo) {
            this.settleCardNo = settleCardNo;
        }

        public String getSettleCardPhone() {
            return settleCardPhone;
        }

        public void setSettleCardPhone(String settleCardPhone) {
            this.settleCardPhone = settleCardPhone;
        }

        public String getShipCompany() {
            return shipCompany;
        }

        public void setShipCompany(String shipCompany) {
            this.shipCompany = shipCompany;
        }

        public String getShipDate() {
            return shipDate;
        }

        public void setShipDate(String shipDate) {
            this.shipDate = shipDate;
        }

        public String getShipOrderId() {
            return shipOrderId;
        }

        public void setShipOrderId(String shipOrderId) {
            this.shipOrderId = shipOrderId;
        }

        public int getShipStatus() {
            return shipStatus;
        }

        public void setShipStatus(int shipStatus) {
            this.shipStatus = shipStatus;
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

        public String getVerifyDesc() {
            return verifyDesc;
        }

        public void setVerifyDesc(String verifyDesc) {
            this.verifyDesc = verifyDesc;
        }

        public int getVerifyStatus() {
            return verifyStatus;
        }

        public void setVerifyStatus(int verifyStatus) {
            this.verifyStatus = verifyStatus;
        }
    }
}
