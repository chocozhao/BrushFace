package com.yzf.king.model.servicesmodels;


import com.yzf.king.model.BaseModel;

import java.io.Serializable;
import java.util.List;

/**
 * ClaseName：GetTransTermInfoResult
 * Description：
 * Author：chocozhao
 * QQ: 1027313530
 * Createtime：2019/7/31 18:25
 * Modified By：
 * Fixtime：2019/7/31 18:25
 * FixDescription：
 **/

public class GetTransTermInfoResult extends BaseModel {


    /**
     * code : 200
     * data : [{"insertTime":1563759310000,"logoUrl":"http://smilepay.oss-cn-shenzhen.aliyuncs.com/goodsTitle.png","mealId":2,"merchBenefitId":4,"merchId":"10000024","modelName":"支付宝刷脸二代蜻蜓F4","password":"dd4346edbd089a1e39e731834b77d4f5","shopBenefitId":0,"status":0,"supTopMerchBenefitId":9,"supTopMerchId":"00000000","termId":"30000004","topMerchBenefitId":0,"topMerchId":"00000000","transSn":106,"updateTime":1563759310000,"upperMerchBenefitId":0,"upperMerchId":"00000000","version":0},{"insertTime":1563759310000,"logoUrl":"http://smilepay.oss-cn-shenzhen.aliyuncs.com/goodsTitle.png","mealId":2,"merchBenefitId":4,"merchId":"10000024","modelName":"支付宝刷脸二代蜻蜓F4","password":"dd4346edbd089a1e39e731834b77d4f5","shopBenefitId":0,"status":0,"supTopMerchBenefitId":9,"supTopMerchId":"00000000","termId":"30000005","topMerchBenefitId":0,"topMerchId":"00000000","transSn":106,"updateTime":1563759310000,"upperMerchBenefitId":0,"upperMerchId":"00000000","version":0},{"insertTime":1563759310000,"logoUrl":"http://smilepay.oss-cn-shenzhen.aliyuncs.com/goodsTitle.png","mealId":2,"merchBenefitId":4,"merchId":"10000024","modelName":"支付宝刷脸二代蜻蜓F4","password":"dd4346edbd089a1e39e731834b77d4f5","shopBenefitId":0,"status":0,"supTopMerchBenefitId":9,"supTopMerchId":"00000000","termId":"30000006","topMerchBenefitId":0,"topMerchId":"00000000","transSn":106,"updateTime":1563759310000,"upperMerchBenefitId":0,"upperMerchId":"00000000","version":0},{"insertTime":1563759310000,"logoUrl":"http://smilepay.oss-cn-shenzhen.aliyuncs.com/goodsTitle.png","mealId":2,"merchBenefitId":4,"merchId":"10000024","modelName":"支付宝刷脸二代蜻蜓F4","password":"dd4346edbd089a1e39e731834b77d4f5","shopBenefitId":0,"status":0,"supTopMerchBenefitId":9,"supTopMerchId":"00000000","termId":"30000007","topMerchBenefitId":0,"topMerchId":"00000000","transSn":106,"updateTime":1563759310000,"upperMerchBenefitId":0,"upperMerchId":"00000000","version":0},{"insertTime":1563759310000,"logoUrl":"http://smilepay.oss-cn-shenzhen.aliyuncs.com/goodsTitle.png","mealId":2,"merchBenefitId":4,"merchId":"10000024","modelName":"支付宝刷脸二代蜻蜓F4","password":"dd4346edbd089a1e39e731834b77d4f5","shopBenefitId":0,"status":0,"supTopMerchBenefitId":9,"supTopMerchId":"00000000","termId":"30000008","topMerchBenefitId":0,"topMerchId":"00000000","transSn":106,"updateTime":1563759310000,"upperMerchBenefitId":0,"upperMerchId":"00000000","version":0},{"insertTime":1563759310000,"logoUrl":"http://smilepay.oss-cn-shenzhen.aliyuncs.com/goodsTitle.png","mealId":2,"merchBenefitId":4,"merchId":"10000024","modelName":"支付宝刷脸二代蜻蜓F4","password":"dd4346edbd089a1e39e731834b77d4f5","shopBenefitId":0,"status":0,"supTopMerchBenefitId":9,"supTopMerchId":"00000000","termId":"30000009","topMerchBenefitId":0,"topMerchId":"00000000","transSn":106,"updateTime":1563759310000,"upperMerchBenefitId":0,"upperMerchId":"00000000","version":0},{"insertTime":1563759310000,"logoUrl":"http://smilepay.oss-cn-shenzhen.aliyuncs.com/goodsTitle.png","mealId":2,"merchBenefitId":4,"merchId":"10000024","modelName":"支付宝刷脸二代蜻蜓F4","password":"dd4346edbd089a1e39e731834b77d4f5","shopBenefitId":0,"status":0,"supTopMerchBenefitId":9,"supTopMerchId":"00000000","termId":"30000010","topMerchBenefitId":0,"topMerchId":"00000000","transSn":106,"updateTime":1563759310000,"upperMerchBenefitId":0,"upperMerchId":"00000000","version":0}]
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
         * insertTime : 1563759310000
         * logoUrl : http://smilepay.oss-cn-shenzhen.aliyuncs.com/goodsTitle.png
         * mealId : 2
         * merchBenefitId : 4
         * merchId : 10000024
         * modelName : 支付宝刷脸二代蜻蜓F4
         * password : dd4346edbd089a1e39e731834b77d4f5
         * shopBenefitId : 0
         * status : 0
         * supTopMerchBenefitId : 9
         * supTopMerchId : 00000000
         * termId : 30000004
         * topMerchBenefitId : 0
         * topMerchId : 00000000
         * transSn : 106
         * updateTime : 1563759310000
         * upperMerchBenefitId : 0
         * upperMerchId : 00000000
         * version : 0
         */
        private String add1;
        private String add2;
        private long insertTime;
        private String logoUrl;
        private int mealId;
        private int merchBenefitId;
        private String merchId;
        private String modelName;
        private String password;
        private int shopBenefitId;
        private String status;
        private int supTopMerchBenefitId;
        private String supTopMerchId;
        private String termId;
        private int topMerchBenefitId;
        private String topMerchId;
        private int transSn;
        private long updateTime;
        private int upperMerchBenefitId;
        private String upperMerchId;
        private String termSn;
        private String activCode;
        private int version;

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

        public int getMealId() {
            return mealId;
        }

        public void setMealId(int mealId) {
            this.mealId = mealId;
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

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public int getShopBenefitId() {
            return shopBenefitId;
        }

        public void setShopBenefitId(int shopBenefitId) {
            this.shopBenefitId = shopBenefitId;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
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

        public String getTermSn() {
            return termSn;
        }

        public void setTermSn(String termSn) {
            this.termSn = termSn;
        }

        public String getActivCode() {
            return activCode;
        }

        public void setActivCode(String activCode) {
            this.activCode = activCode;
        }

        public int getVersion() {
            return version;
        }

        public void setVersion(int version) {
            this.version = version;
        }
    }
}
