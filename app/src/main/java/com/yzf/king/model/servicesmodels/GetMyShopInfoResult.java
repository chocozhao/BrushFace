package com.yzf.king.model.servicesmodels;


import com.yzf.king.model.BaseModel;

import java.io.Serializable;
import java.util.List;

/**
 * ClaseName：GetMyShopInfoResult
 * Description：
 * Author：chocozhao
 * QQ: 1027313530
 * Createtime：2019/7/17 21:07
 * Modified By：
 * Fixtime：2019/7/17 21:07
 * FixDescription：
 **/

public class GetMyShopInfoResult extends BaseModel {


    /**
     * code : 200
     * data : {"dataList":[{"address":"市桥15号","alipayId":"13646494994","busLicNum":"9140105P570HBG","cityName":"广州市","insertTime":1563520358000,"legalIdno":"4500031995030195514","legalName":"李兴文","legalPhone":"13144440014","logoUrl":"http://smilepay.oss-cn-shenzhen.aliyuncs.com/200000072019071915123736shopLogo.jpg","merchId":"10000026","password":"dd4346edbd089a1e39e731834b77d4f5","provencName":"广东省","ranges":"旅馆/酒店/度假区","shopId":"20000007","shopName":"广州市朋代通讯设备有限公司","status":2,"updateTime":1563520358000,"version":0},{"address":"市桥60号","alipayId":"136764949","busLicNum":"91440101MA59KKKT7N","cityName":"广州市","insertTime":1563522310000,"legalIdno":"4500031995030195514","legalName":"曹城","legalPhone":"13144440014","logoUrl":"http://smilepay.oss-cn-shenzhen.aliyuncs.com/200000092019071915451024shopLogo.jpg","merchId":"10000026","password":"dd4346edbd089a1e39e731834b77d4f5","provencName":"广东省","ranges":"旅馆/酒店/度假区","shopId":"20000009","shopName":"广东易铎信息科技服务有限公司","status":2,"updateTime":1563522310000,"version":0}],"count":2}
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
         * dataList : [{"address":"市桥15号","alipayId":"13646494994","busLicNum":"9140105P570HBG","cityName":"广州市","insertTime":1563520358000,"legalIdno":"4500031995030195514","legalName":"李兴文","legalPhone":"13144440014","logoUrl":"http://smilepay.oss-cn-shenzhen.aliyuncs.com/200000072019071915123736shopLogo.jpg","merchId":"10000026","password":"dd4346edbd089a1e39e731834b77d4f5","provencName":"广东省","ranges":"旅馆/酒店/度假区","shopId":"20000007","shopName":"广州市朋代通讯设备有限公司","status":2,"updateTime":1563520358000,"version":0},{"address":"市桥60号","alipayId":"136764949","busLicNum":"91440101MA59KKKT7N","cityName":"广州市","insertTime":1563522310000,"legalIdno":"4500031995030195514","legalName":"曹城","legalPhone":"13144440014","logoUrl":"http://smilepay.oss-cn-shenzhen.aliyuncs.com/200000092019071915451024shopLogo.jpg","merchId":"10000026","password":"dd4346edbd089a1e39e731834b77d4f5","provencName":"广东省","ranges":"旅馆/酒店/度假区","shopId":"20000009","shopName":"广东易铎信息科技服务有限公司","status":2,"updateTime":1563522310000,"version":0}]
         * count : 2
         */

        private int count;
        private List<DataListBean> dataList;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public List<DataListBean> getDataList() {
            return dataList;
        }

        public void setDataList(List<DataListBean> dataList) {
            this.dataList = dataList;
        }

        public static class DataListBean implements Serializable {
            /**
             * address : 市桥15号
             * alipayId : 13646494994
             * busLicNum : 9140105P570HBG
             * cityName : 广州市
             * insertTime : 1563520358000
             * legalIdno : 4500031995030195514
             * legalName : 李兴文
             * legalPhone : 13144440014
             * logoUrl : http://smilepay.oss-cn-shenzhen.aliyuncs.com/200000072019071915123736shopLogo.jpg
             * merchId : 10000026
             * password : dd4346edbd089a1e39e731834b77d4f5
             * provencName : 广东省
             * ranges : 旅馆/酒店/度假区
             * shopId : 20000007
             * shopName : 广州市朋代通讯设备有限公司
             * status : 2
             * updateTime : 1563520358000
             * version : 0
             */

            private String address;
            private String alipayId;
            private String busLicNum;
            private String cityName;
            private long insertTime;
            private String legalIdno;
            private String legalName;
            private String legalPhone;
            private String logoUrl;
            private String merchId;
            private String password;
            private String provencName;
            private String ranges;
            private String shopId;
            private String shopName;
            private int status;
            private long updateTime;
            private int version;
            private String MailId;

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getAlipayId() {
                return alipayId;
            }

            public void setAlipayId(String alipayId) {
                this.alipayId = alipayId;
            }

            public String getBusLicNum() {
                return busLicNum;
            }

            public void setBusLicNum(String busLicNum) {
                this.busLicNum = busLicNum;
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

            public String getLegalIdno() {
                return legalIdno;
            }

            public void setLegalIdno(String legalIdno) {
                this.legalIdno = legalIdno;
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

            public String getMailId() {
                return MailId;
            }

            public void setMailId(String mailId) {
                MailId = mailId;
            }
        }
    }
}
