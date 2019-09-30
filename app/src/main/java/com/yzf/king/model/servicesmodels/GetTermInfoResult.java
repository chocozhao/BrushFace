package com.yzf.king.model.servicesmodels;


import com.yzf.king.model.BaseModel;

import java.io.Serializable;
import java.util.List;

/**
 * ClaseName：GetTermInfoResult
 * Description：
 * Author：chocozhao
 * QQ: 1027313530
 * Createtime：2019/7/17 10:56
 * Modified By：
 * Fixtime：2019/7/17 10:56
 * FixDescription：
 **/

public class GetTermInfoResult extends BaseModel {

    /**
     * code : 200
     * data : {"respDesc":"查询成功","dataList":[{"insertTime":1563331639000,"mealId":3,"merchBenefitId":0,"merchId":"10000007","modelName":"支付宝刷脸二代蜻蜓F1","shopBenefitId":0,"status":1,"supTopMerchBenefitId":9,"supTopMerchId":"00000000","termId":"30000020","topMerchBenefitId":0,"topMerchId":"00000000","transSn":56,"updateTime":1563331639000,"upperMerchBenefitId":0,"upperMerchId":"00000000","version":0},{"insertTime":1563331639000,"mealId":3,"merchBenefitId":0,"merchId":"10000007","modelName":"支付宝刷脸二代蜻蜓F1","shopBenefitId":0,"status":1,"supTopMerchBenefitId":9,"supTopMerchId":"00000000","termId":"30000021","topMerchBenefitId":0,"topMerchId":"00000000","transSn":56,"updateTime":1563331639000,"upperMerchBenefitId":0,"upperMerchId":"00000000","version":0}],"status":"01"}
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
         * respDesc : 查询成功
         * dataList : [{"insertTime":1563331639000,"mealId":3,"merchBenefitId":0,"merchId":"10000007","modelName":"支付宝刷脸二代蜻蜓F1","shopBenefitId":0,"status":1,"supTopMerchBenefitId":9,"supTopMerchId":"00000000","termId":"30000020","topMerchBenefitId":0,"topMerchId":"00000000","transSn":56,"updateTime":1563331639000,"upperMerchBenefitId":0,"upperMerchId":"00000000","version":0},{"insertTime":1563331639000,"mealId":3,"merchBenefitId":0,"merchId":"10000007","modelName":"支付宝刷脸二代蜻蜓F1","shopBenefitId":0,"status":1,"supTopMerchBenefitId":9,"supTopMerchId":"00000000","termId":"30000021","topMerchBenefitId":0,"topMerchId":"00000000","transSn":56,"updateTime":1563331639000,"upperMerchBenefitId":0,"upperMerchId":"00000000","version":0}]
         * status : 01
         */

        private String respDesc;
        private String status;
        private List<DataListBean> dataList;

        public String getRespDesc() {
            return respDesc;
        }

        public void setRespDesc(String respDesc) {
            this.respDesc = respDesc;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public List<DataListBean> getDataList() {
            return dataList;
        }

        public void setDataList(List<DataListBean> dataList) {
            this.dataList = dataList;
        }

        public static class DataListBean implements Serializable {
            /**
             * insertTime : 1563331639000
             * mealId : 3
             * merchBenefitId : 0
             * merchId : 10000007
             * modelName : 支付宝刷脸二代蜻蜓F1
             * shopBenefitId : 0
             * status : 1
             * supTopMerchBenefitId : 9
             * supTopMerchId : 00000000
             * termId : 30000020
             * topMerchBenefitId : 0
             * topMerchId : 00000000
             * transSn : 56
             * updateTime : 1563331639000
             * upperMerchBenefitId : 0
             * upperMerchId : 00000000
             * version : 0
             */

            private long insertTime;
            private int mealId;
            private int merchBenefitId;
            private String merchId;
            private String modelName;
            private int shopBenefitId;
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

            public long getInsertTime() {
                return insertTime;
            }

            public void setInsertTime(long insertTime) {
                this.insertTime = insertTime;
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

            public int getShopBenefitId() {
                return shopBenefitId;
            }

            public void setShopBenefitId(int shopBenefitId) {
                this.shopBenefitId = shopBenefitId;
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
        }
    }
}
