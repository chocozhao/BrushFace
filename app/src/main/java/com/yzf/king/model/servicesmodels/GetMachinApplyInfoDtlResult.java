package com.yzf.king.model.servicesmodels;


import com.yzf.king.model.BaseModel;

import java.io.Serializable;
import java.util.List;

/**
 * ClaseName：GetMachinApplyInfoDtlResult
 * Description：
 * Author：chocozhao
 * QQ: 1027313530
 * Createtime：2019/7/15 11:11
 * Modified By：
 * Fixtime：2019/7/15 11:11
 * FixDescription：
 **/

public class GetMachinApplyInfoDtlResult extends BaseModel {


    /**
     * code : 200
     * data : {"supplierName":"帅的掉渣","address":"上台花园7栋","supplierId":"10000004","orderId":14,"shopLogo":"https://huikajinguanjia.oss-cn-shenzhen.aliyuncs.com/appLogo.png","shopName":"长春市源中达科技有限公司","supplyDate":"20190714","supplierPhone":"13189081178","confirmDate":"20190711","cityName":"广州市","phone":"13500000001","name":"许小亮","supplierStatus":2,"machinNum":1,"termInfoList":[{"insertTime":1563158236000,"mealId":4,"merchBenefitId":0,"merchId":"10000004","modelName":"支付宝刷脸二代蜻蜓F1","shopBenefitId":0,"status":1,"supTopMerchBenefitId":9,"supTopMerchId":"00000000","termId":"10000078","topMerchBenefitId":0,"topMerchId":"00000000","transSn":34,"updateTime":1563158236000,"upperMerchBenefitId":0,"upperMerchId":"00000000","version":0},{"insertTime":1563158236000,"mealId":4,"merchBenefitId":0,"merchId":"10000004","modelName":"支付宝刷脸二代蜻蜓F1","shopBenefitId":0,"status":1,"supTopMerchBenefitId":9,"supTopMerchId":"00000000","termId":"10000079","topMerchBenefitId":0,"topMerchId":"00000000","transSn":34,"updateTime":1563158236000,"upperMerchBenefitId":0,"upperMerchId":"00000000","version":0}],"shopId":"10000009","provinceName":"广东省","applyDate":"20190711","status":3}
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
         * supplierName : 帅的掉渣
         * address : 上台花园7栋
         * supplierId : 10000004
         * orderId : 14
         * shopLogo : https://huikajinguanjia.oss-cn-shenzhen.aliyuncs.com/appLogo.png
         * shopName : 长春市源中达科技有限公司
         * supplyDate : 20190714
         * supplierPhone : 13189081178
         * confirmDate : 20190711
         * cityName : 广州市
         * phone : 13500000001
         * name : 许小亮
         * supplierStatus : 2
         * machinNum : 1
         * termInfoList : [{"insertTime":1563158236000,"mealId":4,"merchBenefitId":0,"merchId":"10000004","modelName":"支付宝刷脸二代蜻蜓F1","shopBenefitId":0,"status":1,"supTopMerchBenefitId":9,"supTopMerchId":"00000000","termId":"10000078","topMerchBenefitId":0,"topMerchId":"00000000","transSn":34,"updateTime":1563158236000,"upperMerchBenefitId":0,"upperMerchId":"00000000","version":0},{"insertTime":1563158236000,"mealId":4,"merchBenefitId":0,"merchId":"10000004","modelName":"支付宝刷脸二代蜻蜓F1","shopBenefitId":0,"status":1,"supTopMerchBenefitId":9,"supTopMerchId":"00000000","termId":"10000079","topMerchBenefitId":0,"topMerchId":"00000000","transSn":34,"updateTime":1563158236000,"upperMerchBenefitId":0,"upperMerchId":"00000000","version":0}]
         * shopId : 10000009
         * provinceName : 广东省
         * applyDate : 20190711
         * status : 3
         */

        private String supplierName;
        private String address;
        private String supplierId;
        private String orderId;
        private String shopLogo;
        private String shopName;
        private String supplyDate;
        private String supplierPhone;
        private String confirmDate;
        private String cityName;
        private String phone;
        private String name;
        private int supplierStatus;
        private int machinNum;
        private String shopId;
        private String provinceName;
        private String applyDate;
        private int status;
        private String remark;
        private String payAuthRul;
        private String aliPayId;
        private String mailId;
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

        public String getSupplierId() {
            return supplierId;
        }

        public void setSupplierId(String supplierId) {
            this.supplierId = supplierId;
        }

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public String getShopLogo() {
            return shopLogo;
        }

        public void setShopLogo(String shopLogo) {
            this.shopLogo = shopLogo;
        }

        public String getShopName() {
            return shopName;
        }

        public void setShopName(String shopName) {
            this.shopName = shopName;
        }

        public String getSupplyDate() {
            return supplyDate;
        }

        public void setSupplyDate(String supplyDate) {
            this.supplyDate = supplyDate;
        }

        public String getSupplierPhone() {
            return supplierPhone;
        }

        public void setSupplierPhone(String supplierPhone) {
            this.supplierPhone = supplierPhone;
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

        public int getSupplierStatus() {
            return supplierStatus;
        }

        public void setSupplierStatus(int supplierStatus) {
            this.supplierStatus = supplierStatus;
        }

        public int getMachinNum() {
            return machinNum;
        }

        public void setMachinNum(int machinNum) {
            this.machinNum = machinNum;
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

        public String getApplyDate() {
            return applyDate;
        }

        public void setApplyDate(String applyDate) {
            this.applyDate = applyDate;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public List<TermInfoListBean> getTermInfoList() {
            return termInfoList;
        }

        public void setTermInfoList(List<TermInfoListBean> termInfoList) {
            this.termInfoList = termInfoList;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getPayAuthRul() {
            return payAuthRul;
        }

        public void setPayAuthRul(String payAuthRul) {
            this.payAuthRul = payAuthRul;
        }

        public String getAliPayId() {
            return aliPayId;
        }

        public void setAliPayId(String aliPayId) {
            this.aliPayId = aliPayId;
        }

        public String getMailId() {
            return mailId;
        }

        public void setMailId(String mailId) {
            this.mailId = mailId;
        }

        public static class TermInfoListBean {
            /**
             * insertTime : 1563158236000
             * mealId : 4
             * merchBenefitId : 0
             * merchId : 10000004
             * modelName : 支付宝刷脸二代蜻蜓F1
             * shopBenefitId : 0
             * status : 1
             * supTopMerchBenefitId : 9
             * supTopMerchId : 00000000
             * termId : 10000078
             * topMerchBenefitId : 0
             * topMerchId : 00000000
             * transSn : 34
             * updateTime : 1563158236000
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
            private String activCode;
            private String termSn;
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

            public int getVersion() {
                return version;
            }

            public void setVersion(int version) {
                this.version = version;
            }
        }
    }
}
