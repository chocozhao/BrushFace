package com.yzf.king.model.servicesmodels;

import com.yzf.king.model.BaseModel;

import java.io.Serializable;
import java.util.List;

/**
 * ClaseName：GetChannelMerchResult
 * Description：
 * Author：JensenWei
 * QQ: 2188307188
 * Createtime：2019/3/8 17:05
 * Modified By：
 * Fixtime：2019/3/8 17:05
 * FixDescription：
 */
public class GetChannelMerchResult extends BaseModel {

    /**
     * code : 200
     * data : [{"channelCode":"70495810","cityCode":"5810","cityName":"广州市","endtime":"240000","merchName":"测试商户","merchNo":"498058100001001","merchType":"52","provinceCode":"5800","provinceName":"广东省","starttime":"000000","status":"1"}]
     * message : 获取成功
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
         * channelCode : 70495810
         * cityCode : 5810
         * cityName : 广州市
         * endtime : 240000
         * merchName : 测试商户
         * merchNo : 498058100001001
         * merchType : 52
         * provinceCode : 5800
         * provinceName : 广东省
         * starttime : 000000
         * status : 1
         */

        private String channelCode;
        private String cityCode;
        private String cityName;
        private String endtime;
        private String merchName;
        private String merchNo;
        private String merchType;
        private String provinceCode;
        private String provinceName;
        private String starttime;
        private String status;

        public String getChannelCode() {
            return channelCode;
        }

        public void setChannelCode(String channelCode) {
            this.channelCode = channelCode;
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

        public String getEndtime() {
            return endtime;
        }

        public void setEndtime(String endtime) {
            this.endtime = endtime;
        }

        public String getMerchName() {
            return merchName;
        }

        public void setMerchName(String merchName) {
            this.merchName = merchName;
        }

        public String getMerchNo() {
            return merchNo;
        }

        public void setMerchNo(String merchNo) {
            this.merchNo = merchNo;
        }

        public String getMerchType() {
            return merchType;
        }

        public void setMerchType(String merchType) {
            this.merchType = merchType;
        }

        public String getProvinceCode() {
            return provinceCode;
        }

        public void setProvinceCode(String provinceCode) {
            this.provinceCode = provinceCode;
        }

        public String getProvinceName() {
            return provinceName;
        }

        public void setProvinceName(String provinceName) {
            this.provinceName = provinceName;
        }

        public String getStarttime() {
            return starttime;
        }

        public void setStarttime(String starttime) {
            this.starttime = starttime;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
