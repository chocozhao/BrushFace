package com.yzf.king.model.servicesmodels;


import com.yzf.king.model.BaseModel;

import java.io.Serializable;
import java.util.List;

/**
 * ClaseName：GetMachinApplyInfoResults
 * Description：
 * Author：chocozhao
 * QQ: 1027313530
 * Createtime：2019/7/13 17:09
 * Modified By：
 * Fixtime：2019/7/13 17:09
 * FixDescription：
 **/

public class GetMachinApplyInfoResults extends BaseModel {


    /**
     * code : 200
     * data : [{"address":"丰泽东路","insertTime":1563035313000,"logoUrl":"https://huikajinguanjia.oss-cn-shenzhen.aliyuncs.com/appLogo.png","machinNum":1,"merchId":"10000022","orderId":7,"shopId":"10000006","shopName":"广州易呗信息服务有限公司","status":0,"supplierStatus":0},{"address":"厦门市思明区","insertTime":1563035320000,"logoUrl":"https://huikajinguanjia.oss-cn-shenzhen.aliyuncs.com/appLogo.png","machinNum":1,"merchId":"10000022","orderId":8,"shopId":"10000007","shopName":"厦门粤闽福商贸有限公司","status":0,"supplierStatus":0},{"address":"市桥街东环路","insertTime":1563035322000,"logoUrl":"https://huikajinguanjia.oss-cn-shenzhen.aliyuncs.com/appLogo.png","machinNum":1,"merchId":"10000022","orderId":9,"shopId":"10000008","shopName":"广东易支付网络科技有限公司","status":0,"supplierStatus":0},{"address":"上台花园7栋","insertTime":1563035325000,"logoUrl":"https://huikajinguanjia.oss-cn-shenzhen.aliyuncs.com/appLogo.png","machinNum":1,"merchId":"10000022","orderId":10,"shopId":"10000009","shopName":"长春市源中达科技有限公司","status":2,"supplierId":"10000004","supplierName":"帅的掉渣","supplierStatus":1},{"address":"南村镇汉溪大道","insertTime":1563035327000,"logoUrl":"https://huikajinguanjia.oss-cn-shenzhen.aliyuncs.com/appLogo.png","machinNum":1,"merchId":"10000022","orderId":11,"shopId":"10000010","shopName":"金管家(广州)技术服务有限责任公司","status":2,"supplierId":"","supplierStatus":0}]
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

    public static class DataBean implements Serializable {
        /**
         * address : 丰泽东路
         * insertTime : 1563035313000
         * logoUrl : https://huikajinguanjia.oss-cn-shenzhen.aliyuncs.com/appLogo.png
         * machinNum : 1
         * merchId : 10000022
         * orderId : 7
         * shopId : 10000006
         * shopName : 广州易呗信息服务有限公司
         * status : 0
         * supplierStatus : 0
         * supplierId : 10000004
         * supplierName : 帅的掉渣
         */

        private String address;
        private long insertTime;
        private String logoUrl;
        private int machinNum;
        private String merchId;
        private String orderId;
        private String shopId;
        private String shopName;
        private int status;
        private int supplierStatus;
        private String supplierId;
        private String supplierName;
        private String cityName;
        private String provencName;
        private String applyType;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public long getInsertTime() {
            return insertTime;
        }

        public void setInsertTime(long insertTime) {
            this.insertTime = insertTime;
        }

        public String getLogoUrl() {
            return logoUrl;
        }

        public void setLogoUrl(String logoUrl) {
            this.logoUrl = logoUrl;
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

        public String getShopName() {
            return shopName;
        }

        public void setShopName(String shopName) {
            this.shopName = shopName;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getSupplierStatus() {
            return supplierStatus;
        }

        public void setSupplierStatus(int supplierStatus) {
            this.supplierStatus = supplierStatus;
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

        public String getCityName() {
            return cityName;
        }

        public void setCityName(String cityName) {
            this.cityName = cityName;
        }

        public String getProvencName() {
            return provencName;
        }

        public void setProvencName(String provencName) {
            this.provencName = provencName;
        }

        public String getApplyType() {
            return applyType;
        }

        public void setApplyType(String applyType) {
            this.applyType = applyType;
        }
    }
}
