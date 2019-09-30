package com.yzf.king.model.servicesmodels;

import com.yzf.king.model.BaseModel;

/**
 * ClaseName：GetLocationResult
 * Description：
 * Author：JensenWei
 * QQ: 2188307188
 * Createtime：2019/3/6 20:57
 * Modified By：
 * Fixtime：2019/3/6 20:57
 * FixDescription：
 */
public class GetLocationResult extends BaseModel {

    /**
     * code : 200
     * data : {"city":"广州","countryCode":"CN","countryName":"中国","ip":"61.140.93.83","latitude":"23.1167","longitude":"113.25","region":"440000"}
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
         * city : 广州
         * countryCode : CN
         * countryName : 中国
         * ip : 61.140.93.83
         * latitude : 23.1167
         * longitude : 113.25
         * region : 440000
         */

        private String city;
        private String countryCode;
        private String countryName;
        private String ip;
        private String latitude;
        private String longitude;
        private String region;

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getCountryCode() {
            return countryCode;
        }

        public void setCountryCode(String countryCode) {
            this.countryCode = countryCode;
        }

        public String getCountryName() {
            return countryName;
        }

        public void setCountryName(String countryName) {
            this.countryName = countryName;
        }

        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getRegion() {
            return region;
        }

        public void setRegion(String region) {
            this.region = region;
        }
    }
}
