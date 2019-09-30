package com.yzf.king.model.servicesmodels;


import com.yzf.king.model.BaseModel;

/**
 * ClaseName：GetLoopShopResult
 * Description：
 * Author：chocozhao
 * QQ: 1027313530
 * Createtime：2019/7/16 18:51
 * Modified By：
 * Fixtime：2019/7/16 18:51
 * FixDescription：
 **/

public class GetLoopShopResult extends BaseModel {


    /**
     * code : 200
     * data : {"applyDate":"20190716","insertTime":1563237256000,"machinNum":1,"merchId":"10000007","orderId":25,"shopId":"S1000005","status":2,"supplierId":"10000000","supplierName":"王思聪","supplierStatus":1,"topMerchLevel":0,"upMerchId":"10000000","upMerchLevel":1,"updateTime":1563274194000,"version":0}
     * message : 抢单成功
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
         * applyDate : 20190716
         * insertTime : 1563237256000
         * machinNum : 1
         * merchId : 10000007
         * orderId : 25
         * shopId : S1000005
         * status : 2
         * supplierId : 10000000
         * supplierName : 王思聪
         * supplierStatus : 1
         * topMerchLevel : 0
         * upMerchId : 10000000
         * upMerchLevel : 1
         * updateTime : 1563274194000
         * version : 0
         */

        private String applyDate;
        private long insertTime;
        private int machinNum;
        private String merchId;
        private String orderId;
        private String shopId;
        private int status;
        private String supplierId;
        private String supplierName;
        private int supplierStatus;
        private int topMerchLevel;
        private String upMerchId;
        private int upMerchLevel;
        private long updateTime;
        private int version;

        public String getApplyDate() {
            return applyDate;
        }

        public void setApplyDate(String applyDate) {
            this.applyDate = applyDate;
        }

        public long getInsertTime() {
            return insertTime;
        }

        public void setInsertTime(long insertTime) {
            this.insertTime = insertTime;
        }

        public int getMachinNum() {
            return machinNum;
        }

        public void setMachinNum(int machinNum) {
            this.machinNum = machinNum;
        }

        public String getMerchId() {
            return merchId;
        }

        public void setMerchId(String merchId) {
            this.merchId = merchId;
        }

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
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

        public String getSupplierId() {
            return supplierId;
        }

        public void setSupplierId(String supplierId) {
            this.supplierId = supplierId;
        }

        public String getSupplierName() {
            return supplierName;
        }

        public void setSupplierName(String supplierName) {
            this.supplierName = supplierName;
        }

        public int getSupplierStatus() {
            return supplierStatus;
        }

        public void setSupplierStatus(int supplierStatus) {
            this.supplierStatus = supplierStatus;
        }

        public int getTopMerchLevel() {
            return topMerchLevel;
        }

        public void setTopMerchLevel(int topMerchLevel) {
            this.topMerchLevel = topMerchLevel;
        }

        public String getUpMerchId() {
            return upMerchId;
        }

        public void setUpMerchId(String upMerchId) {
            this.upMerchId = upMerchId;
        }

        public int getUpMerchLevel() {
            return upMerchLevel;
        }

        public void setUpMerchLevel(int upMerchLevel) {
            this.upMerchLevel = upMerchLevel;
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
