package com.yzf.king.model.servicesmodels;


import com.yzf.king.model.BaseModel;

import java.io.Serializable;
import java.util.List;

/**
 * ClaseName：GetTransDevicesResult
 * Description：
 * Author：chocozhao
 * QQ: 1027313530
 * Createtime：2019/7/16 19:29
 * Modified By：
 * Fixtime：2019/7/16 19:29
 * FixDescription：
 **/

public class GetTransDevicesResult extends BaseModel {


    /**
     * code : 200
     * data : [{"address":"白云区太和镇永兴村","giveNum":7,"goodsId":1,"goodsName":"支付宝刷脸二代蜻蜓F1","id":53,"insertTime":1563275723000,"mealAmt":3,"mealId":4,"mealName":"套餐3","merchId":"10000003","name":"帅的掉渣2","orderAmt":3,"orderId":"05687694061926273233455862546870","orderNum":50,"phone":"13189081178","shipStatus":0,"status":0,"transType":"31","updateTime":1563275723000,"version":0}]
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

    public static class DataBean  implements Serializable {
        /**
         * address : 白云区太和镇永兴村
         * giveNum : 7
         * goodsId : 1
         * goodsName : 支付宝刷脸二代蜻蜓F1
         * id : 53
         * insertTime : 1563275723000
         * mealAmt : 3
         * mealId : 4
         * mealName : 套餐3
         * merchId : 10000003
         * name : 帅的掉渣2
         * orderAmt : 3
         * orderId : 05687694061926273233455862546870
         * orderNum : 50
         * phone : 13189081178
         * shipStatus : 0
         * status : 0
         * transType : 31
         * updateTime : 1563275723000
         * version : 0
         */

        private String add1;
        private String address;
        private int giveNum;
        private int goodsId;
        private String goodsName;
        private String id;
        private long insertTime;
        private int mealAmt;
        private int mealId;
        private String mealName;
        private String merchId;
        private String name;
        private int orderAmt;
        private String orderId;
        private int orderNum;
        private String phone;
        private int shipStatus;
        private int status;
        private String transType;
        private long updateTime;
        private int version;
        private String logoUrl;
        private String remark;
        private String totalNum;
        private String unshipNum;

        public String getAdd1() {
            return add1;
        }

        public void setAdd1(String add1) {
            this.add1 = add1;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public int getGiveNum() {
            return giveNum;
        }

        public void setGiveNum(int giveNum) {
            this.giveNum = giveNum;
        }

        public int getGoodsId() {
            return goodsId;
        }

        public void setGoodsId(int goodsId) {
            this.goodsId = goodsId;
        }

        public String getGoodsName() {
            return goodsName;
        }

        public void setGoodsName(String goodsName) {
            this.goodsName = goodsName;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public long getInsertTime() {
            return insertTime;
        }

        public void setInsertTime(long insertTime) {
            this.insertTime = insertTime;
        }

        public int getMealAmt() {
            return mealAmt;
        }

        public void setMealAmt(int mealAmt) {
            this.mealAmt = mealAmt;
        }

        public int getMealId() {
            return mealId;
        }

        public void setMealId(int mealId) {
            this.mealId = mealId;
        }

        public String getMealName() {
            return mealName;
        }

        public void setMealName(String mealName) {
            this.mealName = mealName;
        }

        public String getMerchId() {
            return merchId;
        }

        public void setMerchId(String merchId) {
            this.merchId = merchId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getOrderAmt() {
            return orderAmt;
        }

        public void setOrderAmt(int orderAmt) {
            this.orderAmt = orderAmt;
        }

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public int getOrderNum() {
            return orderNum;
        }

        public void setOrderNum(int orderNum) {
            this.orderNum = orderNum;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public int getShipStatus() {
            return shipStatus;
        }

        public void setShipStatus(int shipStatus) {
            this.shipStatus = shipStatus;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
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

        public String getLogoUrl() {
            return logoUrl;
        }

        public void setLogoUrl(String logoUrl) {
            this.logoUrl = logoUrl;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getTotalNum() {
            return totalNum;
        }

        public void setTotalNum(String totalNum) {
            this.totalNum = totalNum;
        }

        public String getUnshipNum() {
            return unshipNum;
        }

        public void setUnshipNum(String unshipNum) {
            this.unshipNum = unshipNum;
        }
    }
}
