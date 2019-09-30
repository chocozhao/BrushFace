package com.yzf.king.model.servicesmodels;


import com.yzf.king.model.BaseModel;

import java.util.List;

/**
 * ClaseName：GetBenefitDtlResult
 * Description：
 * Author：chocozhao
 * QQ: 1027313530
 * Createtime：2019/7/23 15:46
 * Modified By：
 * Fixtime：2019/7/23 15:46
 * FixDescription：
 **/

public class GetBenefitDtlResult extends BaseModel {


    /**
     * code : 200
     * data : {"dataDtl":[{"benefitAmt":6,"benefitExternFee":0,"benefitFee":6,"benefitMercLevel":2,"benefitMerchId":"10000024","benefitType":"02","benefitTypeList":["02"],"insertTime":1563949821000,"merchName":"E吴亦凡","settDate":"20190722","transAmt":20000,"transMerchId":"10000026","transName":"支付宝扫码支付","transShopId":"20000001","transSn":306,"transTermId":"30000000","transType":"01","updateTime":1563949821000,"version":0},{"benefitAmt":6,"benefitExternFee":0,"benefitFee":6,"benefitMercLevel":2,"benefitMerchId":"10000024","benefitType":"01","benefitTypeList":["01"],"insertTime":1563949820000,"merchName":"E吴亦凡","settDate":"20190722","transAmt":20000,"transMerchId":"10000026","transName":"支付宝扫码支付","transShopId":"20000001","transSn":306,"transTermId":"30000000","transType":"01","updateTime":1563949820000,"version":0},{"benefitAmt":30,"benefitExternFee":30,"benefitFee":0,"benefitMercLevel":2,"benefitMerchId":"10000024","benefitType":"31","benefitTypeList":["31"],"insertTime":1563949819000,"merchName":"E吴亦凡","settDate":"20190722","transAmt":20000,"transMerchId":"10000026","transName":"支付宝扫码支付","transShopId":"20000001","transSn":306,"transTermId":"30000000","transType":"01","updateTime":1563949819000,"version":0}],"sumAmt":42}
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
         * dataDtl : [{"benefitAmt":6,"benefitExternFee":0,"benefitFee":6,"benefitMercLevel":2,"benefitMerchId":"10000024","benefitType":"02","benefitTypeList":["02"],"insertTime":1563949821000,"merchName":"E吴亦凡","settDate":"20190722","transAmt":20000,"transMerchId":"10000026","transName":"支付宝扫码支付","transShopId":"20000001","transSn":306,"transTermId":"30000000","transType":"01","updateTime":1563949821000,"version":0},{"benefitAmt":6,"benefitExternFee":0,"benefitFee":6,"benefitMercLevel":2,"benefitMerchId":"10000024","benefitType":"01","benefitTypeList":["01"],"insertTime":1563949820000,"merchName":"E吴亦凡","settDate":"20190722","transAmt":20000,"transMerchId":"10000026","transName":"支付宝扫码支付","transShopId":"20000001","transSn":306,"transTermId":"30000000","transType":"01","updateTime":1563949820000,"version":0},{"benefitAmt":30,"benefitExternFee":30,"benefitFee":0,"benefitMercLevel":2,"benefitMerchId":"10000024","benefitType":"31","benefitTypeList":["31"],"insertTime":1563949819000,"merchName":"E吴亦凡","settDate":"20190722","transAmt":20000,"transMerchId":"10000026","transName":"支付宝扫码支付","transShopId":"20000001","transSn":306,"transTermId":"30000000","transType":"01","updateTime":1563949819000,"version":0}]
         * sumAmt : 42
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
             * benefitAmt : 6
             * benefitExternFee : 0
             * benefitFee : 6
             * benefitMercLevel : 2
             * benefitMerchId : 10000024
             * benefitType : 02
             * benefitTypeList : ["02"]
             * insertTime : 1563949821000
             * merchName : E吴亦凡
             * settDate : 20190722
             * transAmt : 20000
             * transMerchId : 10000026
             * transName : 支付宝扫码支付
             * transShopId : 20000001
             * transSn : 306
             * transTermId : 30000000
             * transType : 01
             * updateTime : 1563949821000
             * version : 0
             */

            private int benefitAmt;
            private int benefitExternFee;
            private int benefitFee;
            private int benefitMercLevel;
            private String benefitMerchId;
            private String benefitType;
            private long insertTime;
            private String merchName;
            private String settDate;
            private int transAmt;
            private String transMerchId;
            private String transName;
            private String transShopId;
            private int transSn;
            private String transTermId;
            private String transType;
            private long updateTime;
            private int version;
            private List<String> benefitTypeList;

            public int getBenefitAmt() {
                return benefitAmt;
            }

            public void setBenefitAmt(int benefitAmt) {
                this.benefitAmt = benefitAmt;
            }

            public int getBenefitExternFee() {
                return benefitExternFee;
            }

            public void setBenefitExternFee(int benefitExternFee) {
                this.benefitExternFee = benefitExternFee;
            }

            public int getBenefitFee() {
                return benefitFee;
            }

            public void setBenefitFee(int benefitFee) {
                this.benefitFee = benefitFee;
            }

            public int getBenefitMercLevel() {
                return benefitMercLevel;
            }

            public void setBenefitMercLevel(int benefitMercLevel) {
                this.benefitMercLevel = benefitMercLevel;
            }

            public String getBenefitMerchId() {
                return benefitMerchId;
            }

            public void setBenefitMerchId(String benefitMerchId) {
                this.benefitMerchId = benefitMerchId;
            }

            public String getBenefitType() {
                return benefitType;
            }

            public void setBenefitType(String benefitType) {
                this.benefitType = benefitType;
            }

            public long getInsertTime() {
                return insertTime;
            }

            public void setInsertTime(long insertTime) {
                this.insertTime = insertTime;
            }

            public String getMerchName() {
                return merchName;
            }

            public void setMerchName(String merchName) {
                this.merchName = merchName;
            }

            public String getSettDate() {
                return settDate;
            }

            public void setSettDate(String settDate) {
                this.settDate = settDate;
            }

            public int getTransAmt() {
                return transAmt;
            }

            public void setTransAmt(int transAmt) {
                this.transAmt = transAmt;
            }

            public String getTransMerchId() {
                return transMerchId;
            }

            public void setTransMerchId(String transMerchId) {
                this.transMerchId = transMerchId;
            }

            public String getTransName() {
                return transName;
            }

            public void setTransName(String transName) {
                this.transName = transName;
            }

            public String getTransShopId() {
                return transShopId;
            }

            public void setTransShopId(String transShopId) {
                this.transShopId = transShopId;
            }

            public int getTransSn() {
                return transSn;
            }

            public void setTransSn(int transSn) {
                this.transSn = transSn;
            }

            public String getTransTermId() {
                return transTermId;
            }

            public void setTransTermId(String transTermId) {
                this.transTermId = transTermId;
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

            public List<String> getBenefitTypeList() {
                return benefitTypeList;
            }

            public void setBenefitTypeList(List<String> benefitTypeList) {
                this.benefitTypeList = benefitTypeList;
            }
        }
    }
}
