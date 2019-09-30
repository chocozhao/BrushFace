package com.yzf.king.model.servicesmodels;


import com.yzf.king.model.BaseModel;

/**
 * ClaseName：GetShopApplyResults
 * Description：
 * Author：chocozhao
 * QQ: 1027313530
 * Createtime：2019/8/26 20:18
 * Modified By：
 * Fixtime：2019/8/26 20:18
 * FixDescription：
 **/

public class GetShopApplyResults extends BaseModel {

    /**
     * code : 200
     * data : {"add1":"jdnfndndn","add2":"655959555","address":"那些难兄难弟","areaCode":"120000","areaName":"河东区","busLicNum":"91350203MA2YFK526","busLicType":"0","businessName":"苏亚波","businessUrl":"http://smilepay.oss-cn-shenzhen.aliyuncs.com/100000252019082620155478711566821753100907.jpg","cityCode":"120000","cityName":"省直辖县级行政单位","insertTime":1566821786937,"legalName":"苏亚波","lisExpDate":"2019-08-23","merchId":"10000025","password":"dd4346edbd089a1e39e731834b77d4f5","provencCode":"120000","provencName":"天津市","ranges":"交通出行-铁路客运","shopId":"20000063","shopName":"厦门粤闽福商贸有限公司","status":0,"updateTime":1566821786937,"wxApplyStatus":1,"zfbApplyStatus":1}
     * message : 店铺申请成功
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
         * add1 : jdnfndndn
         * add2 : 655959555
         * address : 那些难兄难弟
         * areaCode : 120000
         * areaName : 河东区
         * busLicNum : 91350203MA2YFK526
         * busLicType : 0
         * businessName : 苏亚波
         * businessUrl : http://smilepay.oss-cn-shenzhen.aliyuncs.com/100000252019082620155478711566821753100907.jpg
         * cityCode : 120000
         * cityName : 省直辖县级行政单位
         * insertTime : 1566821786937
         * legalName : 苏亚波
         * lisExpDate : 2019-08-23
         * merchId : 10000025
         * password : dd4346edbd089a1e39e731834b77d4f5
         * provencCode : 120000
         * provencName : 天津市
         * ranges : 交通出行-铁路客运
         * shopId : 20000063
         * shopName : 厦门粤闽福商贸有限公司
         * status : 0
         * updateTime : 1566821786937
         * wxApplyStatus : 1
         * zfbApplyStatus : 1
         */

        private String add1;
        private String add2;
        private String address;
        private String areaCode;
        private String areaName;
        private String busLicNum;
        private String busLicType;
        private String businessName;
        private String businessUrl;
        private String cityCode;
        private String cityName;
        private long insertTime;
        private String legalName;
        private String lisExpDate;
        private String merchId;
        private String password;
        private String provencCode;
        private String provencName;
        private String ranges;
        private String shopId;
        private String shopName;
        private int status;
        private long updateTime;
        private int wxApplyStatus;
        private int zfbApplyStatus;

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

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getAreaCode() {
            return areaCode;
        }

        public void setAreaCode(String areaCode) {
            this.areaCode = areaCode;
        }

        public String getAreaName() {
            return areaName;
        }

        public void setAreaName(String areaName) {
            this.areaName = areaName;
        }

        public String getBusLicNum() {
            return busLicNum;
        }

        public void setBusLicNum(String busLicNum) {
            this.busLicNum = busLicNum;
        }

        public String getBusLicType() {
            return busLicType;
        }

        public void setBusLicType(String busLicType) {
            this.busLicType = busLicType;
        }

        public String getBusinessName() {
            return businessName;
        }

        public void setBusinessName(String businessName) {
            this.businessName = businessName;
        }

        public String getBusinessUrl() {
            return businessUrl;
        }

        public void setBusinessUrl(String businessUrl) {
            this.businessUrl = businessUrl;
        }

        public String getCityCode() {
            return cityCode;
        }

        public void setCityCode(String cityCode) {
            this.cityCode = cityCode;
        }

        public String getCityName() {
            return cityName;
        }

        public void setCityName(String cityName) {
            this.cityName = cityName;
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

        public String getLisExpDate() {
            return lisExpDate;
        }

        public void setLisExpDate(String lisExpDate) {
            this.lisExpDate = lisExpDate;
        }

        public String getMerchId() {
            return merchId;
        }

        public void setMerchId(String merchId) {
            this.merchId = merchId;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getProvencCode() {
            return provencCode;
        }

        public void setProvencCode(String provencCode) {
            this.provencCode = provencCode;
        }

        public String getProvencName() {
            return provencName;
        }

        public void setProvencName(String provencName) {
            this.provencName = provencName;
        }

        public String getRanges() {
            return ranges;
        }

        public void setRanges(String ranges) {
            this.ranges = ranges;
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

        public long getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(long updateTime) {
            this.updateTime = updateTime;
        }

        public int getWxApplyStatus() {
            return wxApplyStatus;
        }

        public void setWxApplyStatus(int wxApplyStatus) {
            this.wxApplyStatus = wxApplyStatus;
        }

        public int getZfbApplyStatus() {
            return zfbApplyStatus;
        }

        public void setZfbApplyStatus(int zfbApplyStatus) {
            this.zfbApplyStatus = zfbApplyStatus;
        }
    }
}
