package com.yzf.king.model.servicesmodels;


import com.yzf.king.model.BaseModel;

/**
 * ClaseName：GetAddApplyInfoResults
 * Description：
 * Author：chocozhao
 * QQ: 1027313530
 * Createtime：2019/8/27 11:51
 * Modified By：
 * Fixtime：2019/8/27 11:51
 * FixDescription：
 **/

public class GetAddApplyInfoResults extends BaseModel {


    /**
     * code : 200
     * data : {"add1":"fgg@qq.com","add2":"13155555888","address":"不吃","applyType":"0","areaCode":"150100","areaName":"赛罕区","authFlag":"0","busLicNum":"910M5CUF01","busLicType":"0","businessName":"易雪珍","businessUrl":"http://smilepay.oss-cn-shenzhen.aliyuncs.com/100000252019082711484894671566877726995670.jpg","cityCode":"150100","cityName":"呼和浩特市","insertTime":1566830360000,"legalName":"易雪珍","lisExpDate":"2019-08-15","logoUrl":"","merchId":"10000025","password":"dd4346edbd089a1e39e731834b77d4f5","provencCode":"150000","provencName":"内蒙古自治区","ranges":"休闲娱乐-娱乐票务","shopId":"20000076","shopName":"金管家(广州)技术服务有限责任公司","status":0,"updateTime":1566877762000,"version":0,"wxApplyStatus":1,"zfbApplyStatus":1}
     * message : 操作成功
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
         * add1 : fgg@qq.com
         * add2 : 13155555888
         * address : 不吃
         * applyType : 0
         * areaCode : 150100
         * areaName : 赛罕区
         * authFlag : 0
         * busLicNum : 910M5CUF01
         * busLicType : 0
         * businessName : 易雪珍
         * businessUrl : http://smilepay.oss-cn-shenzhen.aliyuncs.com/100000252019082711484894671566877726995670.jpg
         * cityCode : 150100
         * cityName : 呼和浩特市
         * insertTime : 1566830360000
         * legalName : 易雪珍
         * lisExpDate : 2019-08-15
         * logoUrl :
         * merchId : 10000025
         * password : dd4346edbd089a1e39e731834b77d4f5
         * provencCode : 150000
         * provencName : 内蒙古自治区
         * ranges : 休闲娱乐-娱乐票务
         * shopId : 20000076
         * shopName : 金管家(广州)技术服务有限责任公司
         * status : 0
         * updateTime : 1566877762000
         * version : 0
         * wxApplyStatus : 1
         * zfbApplyStatus : 1
         */

        private String add1;
        private String add2;
        private String address;
        private String applyType;
        private String areaCode;
        private String areaName;
        private String authFlag;
        private String busLicNum;
        private String busLicType;
        private String businessName;
        private String businessUrl;
        private String cityCode;
        private String cityName;
        private long insertTime;
        private String legalName;
        private String lisExpDate;
        private String logoUrl;
        private String merchId;
        private String password;
        private String provencCode;
        private String provencName;
        private String ranges;
        private String shopId;
        private String shopName;
        private int status;
        private long updateTime;
        private int version;
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

        public String getApplyType() {
            return applyType;
        }

        public void setApplyType(String applyType) {
            this.applyType = applyType;
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

        public String getAuthFlag() {
            return authFlag;
        }

        public void setAuthFlag(String authFlag) {
            this.authFlag = authFlag;
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

        public String getLogoUrl() {
            return logoUrl;
        }

        public void setLogoUrl(String logoUrl) {
            this.logoUrl = logoUrl;
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

        public int getVersion() {
            return version;
        }

        public void setVersion(int version) {
            this.version = version;
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
