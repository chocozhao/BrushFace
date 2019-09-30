package com.yzf.king.model.servicesmodels;


import com.yzf.king.model.BaseModel;

import java.io.Serializable;
import java.util.List;

/**
 * ClaseName：GetMyTermResult
 * Description：
 * Author：chocozhao
 * QQ: 1027313530
 * Createtime：2019/7/13 18:32
 * Modified By：
 * Fixtime：2019/7/13 18:32
 * FixDescription：
 **/

public class GetMyTermResult extends BaseModel {


    /**
     * code : 200
     * data : {"activeCount":1,"unactiveCount":8,"dataList":[{"alipayId":"1264919919","contractName":"帅的掉渣","goodsName":"支付宝刷脸二代蜻蜓F1","insertTime":1563331639000,"legalName":"许小亮","legalPhone":"13144440008","mealAmt":2,"mealId":3,"mealName":"套餐2","mealNum":10,"merchBenefitId":0,"merchId":"10000007","modelName":"支付宝刷脸二代蜻蜓F1","orderId":"36352198065939957463960754575814","shopAddress":"汉溪长隆","shopBenefitId":0,"shopId":"20000002","shopName":"长春市源中达科技有限公司","status":2,"supTopMerchBenefitId":9,"supTopMerchId":"00000000","termId":"30000019","topMerchBenefitId":0,"topMerchId":"00000000","transSn":56,"updateTime":1563331826000,"upperMerchBenefitId":0,"upperMerchId":"00000000","version":0}],"count":8,"useCount":2}
     * message : 查询成功
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
         * activeCount : 1
         * unactiveCount : 8
         * dataList : [{"alipayId":"1264919919","contractName":"帅的掉渣","goodsName":"支付宝刷脸二代蜻蜓F1","insertTime":1563331639000,"legalName":"许小亮","legalPhone":"13144440008","mealAmt":2,"mealId":3,"mealName":"套餐2","mealNum":10,"merchBenefitId":0,"merchId":"10000007","modelName":"支付宝刷脸二代蜻蜓F1","orderId":"36352198065939957463960754575814","shopAddress":"汉溪长隆","shopBenefitId":0,"shopId":"20000002","shopName":"长春市源中达科技有限公司","status":2,"supTopMerchBenefitId":9,"supTopMerchId":"00000000","termId":"30000019","topMerchBenefitId":0,"topMerchId":"00000000","transSn":56,"updateTime":1563331826000,"upperMerchBenefitId":0,"upperMerchId":"00000000","version":0}]
         * count : 8
         * useCount : 2
         */

        private int activeCount;
        private int unactiveCount;
        private int count;
        private int useCount;
        private List<DataListBean> dataList;

        public int getActiveCount() {
            return activeCount;
        }

        public void setActiveCount(int activeCount) {
            this.activeCount = activeCount;
        }

        public int getUnactiveCount() {
            return unactiveCount;
        }

        public void setUnactiveCount(int unactiveCount) {
            this.unactiveCount = unactiveCount;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public int getUseCount() {
            return useCount;
        }

        public void setUseCount(int useCount) {
            this.useCount = useCount;
        }

        public List<DataListBean> getDataList() {
            return dataList;
        }

        public void setDataList(List<DataListBean> dataList) {
            this.dataList = dataList;
        }

        public static class DataListBean implements Serializable {
            /**
             * alipayId : 1264919919
             * contractName : 帅的掉渣
             * goodsName : 支付宝刷脸二代蜻蜓F1
             * insertTime : 1563331639000
             * legalName : 许小亮
             * legalPhone : 13144440008
             * mealAmt : 2
             * mealId : 3
             * mealName : 套餐2
             * mealNum : 10
             * merchBenefitId : 0
             * merchId : 10000007
             * modelName : 支付宝刷脸二代蜻蜓F1
             * orderId : 36352198065939957463960754575814
             * shopAddress : 汉溪长隆
             * shopBenefitId : 0
             * shopId : 20000002
             * shopName : 长春市源中达科技有限公司
             * status : 2
             * supTopMerchBenefitId : 9
             * supTopMerchId : 00000000
             * termId : 30000019
             * topMerchBenefitId : 0
             * topMerchId : 00000000
             * transSn : 56
             * updateTime : 1563331826000
             * upperMerchBenefitId : 0
             * upperMerchId : 00000000
             * version : 0
             */

            private String alipayId;
            private String contractName;
            private String goodsName;
            private long insertTime;
            private String legalName;
            private String legalPhone;
            private int mealAmt;
            private int mealId;
            private String mealName;
            private int mealNum;
            private int merchBenefitId;
            private String merchId;
            private String modelName;
            private String orderId;
            private String shopAddress;
            private int shopBenefitId;
            private String shopId;
            private String shopName;
            private int status;
            private int supTopMerchBenefitId;
            private String supTopMerchId;
            private String termId;
            private int topMerchBenefitId;
            private String topMerchId;
            private int transSn;
            private long updateTime;
            private int upperMerchBenefitId;
            private String upperMerchId;
            private int version;
            private boolean check;
            private String activCode;
            private String termSn;
            private String logoUrl;

            public String getAlipayId() {
                return alipayId;
            }

            public void setAlipayId(String alipayId) {
                this.alipayId = alipayId;
            }

            public String getContractName() {
                return contractName;
            }

            public void setContractName(String contractName) {
                this.contractName = contractName;
            }

            public String getGoodsName() {
                return goodsName;
            }

            public void setGoodsName(String goodsName) {
                this.goodsName = goodsName;
            }

            public long getInsertTime() {
                return insertTime;
            }

            public void setInsertTime(long insertTime) {
                this.insertTime = insertTime;
            }

            public String getLegalName() {
                return legalName;
            }

            public void setLegalName(String legalName) {
                this.legalName = legalName;
            }

            public String getLegalPhone() {
                return legalPhone;
            }

            public void setLegalPhone(String legalPhone) {
                this.legalPhone = legalPhone;
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

            public int getMealNum() {
                return mealNum;
            }

            public void setMealNum(int mealNum) {
                this.mealNum = mealNum;
            }

            public int getMerchBenefitId() {
                return merchBenefitId;
            }

            public void setMerchBenefitId(int merchBenefitId) {
                this.merchBenefitId = merchBenefitId;
            }

            public String getMerchId() {
                return merchId;
            }

            public void setMerchId(String merchId) {
                this.merchId = merchId;
            }

            public String getModelName() {
                return modelName;
            }

            public void setModelName(String modelName) {
                this.modelName = modelName;
            }

            public String getOrderId() {
                return orderId;
            }

            public void setOrderId(String orderId) {
                this.orderId = orderId;
            }

            public String getShopAddress() {
                return shopAddress;
            }

            public void setShopAddress(String shopAddress) {
                this.shopAddress = shopAddress;
            }

            public int getShopBenefitId() {
                return shopBenefitId;
            }

            public void setShopBenefitId(int shopBenefitId) {
                this.shopBenefitId = shopBenefitId;
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

            public int getSupTopMerchBenefitId() {
                return supTopMerchBenefitId;
            }

            public void setSupTopMerchBenefitId(int supTopMerchBenefitId) {
                this.supTopMerchBenefitId = supTopMerchBenefitId;
            }

            public String getSupTopMerchId() {
                return supTopMerchId;
            }

            public void setSupTopMerchId(String supTopMerchId) {
                this.supTopMerchId = supTopMerchId;
            }

            public String getTermId() {
                return termId;
            }

            public void setTermId(String termId) {
                this.termId = termId;
            }

            public int getTopMerchBenefitId() {
                return topMerchBenefitId;
            }

            public void setTopMerchBenefitId(int topMerchBenefitId) {
                this.topMerchBenefitId = topMerchBenefitId;
            }

            public String getTopMerchId() {
                return topMerchId;
            }

            public void setTopMerchId(String topMerchId) {
                this.topMerchId = topMerchId;
            }

            public int getTransSn() {
                return transSn;
            }

            public void setTransSn(int transSn) {
                this.transSn = transSn;
            }

            public long getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(long updateTime) {
                this.updateTime = updateTime;
            }

            public int getUpperMerchBenefitId() {
                return upperMerchBenefitId;
            }

            public void setUpperMerchBenefitId(int upperMerchBenefitId) {
                this.upperMerchBenefitId = upperMerchBenefitId;
            }

            public String getUpperMerchId() {
                return upperMerchId;
            }

            public void setUpperMerchId(String upperMerchId) {
                this.upperMerchId = upperMerchId;
            }

            public int getVersion() {
                return version;
            }

            public void setVersion(int version) {
                this.version = version;
            }

            public boolean isCheck() {
                return check;
            }

            public void setCheck(boolean check) {
                this.check = check;
            }

            public String getLogoUrl() {
                return logoUrl;
            }

            public void setLogoUrl(String logoUrl) {
                this.logoUrl = logoUrl;
            }

            public String getActivCode() {
                return activCode;
            }

            public void setActivCode(String activCode) {
                this.activCode = activCode;
            }

            public String getTermSn() {
                return termSn;
            }

            public void setTermSn(String termSn) {
                this.termSn = termSn;
            }
        }
    }
}
