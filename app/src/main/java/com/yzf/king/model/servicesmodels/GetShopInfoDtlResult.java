package com.yzf.king.model.servicesmodels;


import com.yzf.king.model.BaseModel;

import java.io.Serializable;
import java.util.List;

/**
 * ClaseName：GetShopInfoDtlResult
 * Description：
 * Author：chocozhao
 * QQ: 1027313530
 * Createtime：2019/7/17 20:31
 * Modified By：
 * Fixtime：2019/7/17 20:31
 * FixDescription：
 **/

public class GetShopInfoDtlResult extends BaseModel {


    /**
     * code : 200
     * data : {"supplierName":"黑侠","address":"镜子","shopLogo":"http://leshou.oss-cn-shenzhen.aliyuncs.com/200000062019072221244228shopLogo.jpg","instrtTime":1563801883000,"shopName":"厦门粤闽福商贸有限公司","busLicNum":"913502003MA2YFK526D","confirmDate":"20190724","cityName":"北京市","phone":"13144440006","name":"苏亚波","termStatus":1,"supplierStatus":2,"termInfoList":[{"activCode":"AC0112323423","insertTime":1563800898000,"logoUrl":"https://leshou.oss-cn-shenzhen.aliyuncs.com/goods/zhifubaomainphotoxiao.png","mealId":2,"merchBenefitId":4,"merchId":"10000001","modelName":"支付宝刷脸二代蜻蜓F4","password":"dd4346edbd089a1e39e731834b77d4f5","shopBenefitId":0,"shopId":"20000006","status":4,"supTopMerchBenefitId":9,"supTopMerchId":"00000000","termId":"30000000","termSn":"SN0112323423","topMerchBenefitId":0,"topMerchId":"00000000","transSn":112,"updateTime":1563802774000,"upperMerchBenefitId":0,"upperMerchId":"00000000","version":0}],"shopId":"20000006","provinceName":"北京市","sumAmt":0,"status":3}
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

    public static class DataBean implements Serializable {
        /**
         * supplierName : 黑侠
         * address : 镜子
         * shopLogo : http://leshou.oss-cn-shenzhen.aliyuncs.com/200000062019072221244228shopLogo.jpg
         * instrtTime : 1563801883000
         * shopName : 厦门粤闽福商贸有限公司
         * busLicNum : 913502003MA2YFK526D
         * confirmDate : 20190724
         * cityName : 北京市
         * phone : 13144440006
         * name : 苏亚波
         * termStatus : 1
         * supplierStatus : 2
         * termInfoList : [{"activCode":"AC0112323423","insertTime":1563800898000,"logoUrl":"https://leshou.oss-cn-shenzhen.aliyuncs.com/goods/zhifubaomainphotoxiao.png","mealId":2,"merchBenefitId":4,"merchId":"10000001","modelName":"支付宝刷脸二代蜻蜓F4","password":"dd4346edbd089a1e39e731834b77d4f5","shopBenefitId":0,"shopId":"20000006","status":4,"supTopMerchBenefitId":9,"supTopMerchId":"00000000","termId":"30000000","termSn":"SN0112323423","topMerchBenefitId":0,"topMerchId":"00000000","transSn":112,"updateTime":1563802774000,"upperMerchBenefitId":0,"upperMerchId":"00000000","version":0}]
         * shopId : 20000006
         * provinceName : 北京市
         * sumAmt : 0
         * status : 3
         */

        private String supplierName;
        private String address;
        private String shopLogo;
        private long instrtTime;
        private String shopName;
        private String busLicNum;
        private String confirmDate;
        private String cityName;
        private String phone;
        private String name;
        private int termStatus;
        private int supplierStatus;
        private String shopId;
        private String provinceName;
        private int sumAmt;
        private int status;
        private String remark;
        private String applyType;
        private List<TermInfoListBean> termInfoList;

        public String getSupplierName() {
            return supplierName;
        }

        public void setSupplierName(String supplierName) {
            this.supplierName = supplierName;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getShopLogo() {
            return shopLogo;
        }

        public void setShopLogo(String shopLogo) {
            this.shopLogo = shopLogo;
        }

        public long getInstrtTime() {
            return instrtTime;
        }

        public void setInstrtTime(long instrtTime) {
            this.instrtTime = instrtTime;
        }

        public String getShopName() {
            return shopName;
        }

        public void setShopName(String shopName) {
            this.shopName = shopName;
        }

        public String getBusLicNum() {
            return busLicNum;
        }

        public void setBusLicNum(String busLicNum) {
            this.busLicNum = busLicNum;
        }

        public String getConfirmDate() {
            return confirmDate;
        }

        public void setConfirmDate(String confirmDate) {
            this.confirmDate = confirmDate;
        }

        public String getCityName() {
            return cityName;
        }

        public void setCityName(String cityName) {
            this.cityName = cityName;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getTermStatus() {
            return termStatus;
        }

        public void setTermStatus(int termStatus) {
            this.termStatus = termStatus;
        }

        public int getSupplierStatus() {
            return supplierStatus;
        }

        public void setSupplierStatus(int supplierStatus) {
            this.supplierStatus = supplierStatus;
        }

        public String getShopId() {
            return shopId;
        }

        public void setShopId(String shopId) {
            this.shopId = shopId;
        }

        public String getProvinceName() {
            return provinceName;
        }

        public void setProvinceName(String provinceName) {
            this.provinceName = provinceName;
        }

        public int getSumAmt() {
            return sumAmt;
        }

        public void setSumAmt(int sumAmt) {
            this.sumAmt = sumAmt;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getApplyType() {
            return applyType;
        }

        public void setApplyType(String applyType) {
            this.applyType = applyType;
        }

        public List<TermInfoListBean> getTermInfoList() {
            return termInfoList;
        }

        public void setTermInfoList(List<TermInfoListBean> termInfoList) {
            this.termInfoList = termInfoList;
        }

        public static class TermInfoListBean {
            /**
             * activCode : AC0112323423
             * insertTime : 1563800898000
             * logoUrl : https://leshou.oss-cn-shenzhen.aliyuncs.com/goods/zhifubaomainphotoxiao.png
             * mealId : 2
             * merchBenefitId : 4
             * merchId : 10000001
             * modelName : 支付宝刷脸二代蜻蜓F4
             * password : dd4346edbd089a1e39e731834b77d4f5
             * shopBenefitId : 0
             * shopId : 20000006
             * status : 4
             * supTopMerchBenefitId : 9
             * supTopMerchId : 00000000
             * termId : 30000000
             * termSn : SN0112323423
             * topMerchBenefitId : 0
             * topMerchId : 00000000
             * transSn : 112
             * updateTime : 1563802774000
             * upperMerchBenefitId : 0
             * upperMerchId : 00000000
             * version : 0
             */

            private String activCode;
            private long insertTime;
            private String logoUrl;
            private int mealId;
            private int merchBenefitId;
            private String merchId;
            private String modelName;
            private String password;
            private int shopBenefitId;
            private String shopId;
            private int status;
            private int supTopMerchBenefitId;
            private String supTopMerchId;
            private String termId;
            private String termSn;
            private int topMerchBenefitId;
            private String topMerchId;
            private int transSn;
            private long updateTime;
            private int upperMerchBenefitId;
            private String upperMerchId;
            private int version;

            public String getActivCode() {
                return activCode;
            }

            public void setActivCode(String activCode) {
                this.activCode = activCode;
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

            public String getTermSn() {
                return termSn;
            }

            public void setTermSn(String termSn) {
                this.termSn = termSn;
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
