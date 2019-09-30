package com.yzf.king.model.servicesmodels;


import com.yzf.king.model.BaseModel;

import java.io.Serializable;
import java.util.List;

/**
 * ClaseName：GetSpreadShopInfoRestult
 * Description：
 * Author：chocozhao
 * QQ: 1027313530
 * Createtime：2019/7/17 19:22
 * Modified By：
 * Fixtime：2019/7/17 19:22
 * FixDescription：
 **/

public class GetSpreadShopInfoRestult extends BaseModel {


    /**
     * code : 200
     * data : [{"address":"长安大学","alipayId":"13469161661","authToken":"201907BB5a4eca4a9e194403b1b037e10bb2bX88","busLicNum":"913502003MA2YFK526D","cityName":"厦门市","insertTime":1563331019000,"legalIdno":"4500031995030195514","legalName":"苏亚波","legalPhone":"13144440008","logoUrl":"http://huikajinguanjia.oss-cn-shenzhen.aliyuncs.com/200000002019071710365970shopLogo.jpg","merchId":"10000017","provencName":"福建省","ranges":"汽车用品","shopId":"20000000","shopName":"厦门粤闽福商贸有限公司","status":2,"updateTime":1563331019000,"version":0},{"address":"汉溪长隆","alipayId":"137664646","busLicNum":"91440101MA5CLUFY7B","cityName":"广州市","insertTime":1563331285000,"legalIdno":"4500031995030195514","legalName":"易雪珍","legalPhone":"13144440008","logoUrl":"http://huikajinguanjia.oss-cn-shenzhen.aliyuncs.com/20000001201907171041245shopLogo.jpg","merchId":"10000017","provencName":"广东省","ranges":"旅馆/酒店/度假区","shopId":"20000001","shopName":"金管家(广州)技术服务有限责任公司","status":2,"updateTime":1563331285000,"version":0},{"address":"汉溪长隆","alipayId":"1264919919","busLicNum":"91220103MA14BDAH0T","cityName":"长春市","insertTime":1563331443000,"legalIdno":"4500031995030195514","legalName":"许小亮","legalPhone":"13144440008","logoUrl":"http://huikajinguanjia.oss-cn-shenzhen.aliyuncs.com/200000022019071710440331shopLogo.jpg","merchId":"10000017","provencName":"吉林省","ranges":"旅馆/酒店/度假区","shopId":"20000002","shopName":"长春市源中达科技有限公司","status":2,"updateTime":1563331443000,"version":0},{"address":"汉溪大道","alipayId":"1367994949","busLicNum":"91440101321038501F","cityName":"广州市","insertTime":1563331697000,"legalIdno":"4500031995030195514","legalName":"易智军","legalPhone":"13144440008","logoUrl":"http://huikajinguanjia.oss-cn-shenzhen.aliyuncs.com/200000032019071710481743shopLogo.jpg","merchId":"10000017","provencName":"广东省","ranges":"旅馆/酒店/度假区","shopId":"20000003","shopName":"广东易支付网络科技有限公司","status":2,"updateTime":1563331697000,"version":0},{"address":"汉溪长隆","alipayId":"36491891818","busLicNum":"91440101MA59P62Y8C","cityName":"广州市","insertTime":1563332118000,"legalIdno":"4500031995030195514","legalName":"农本勇","legalPhone":"13144440004","logoUrl":"http://huikajinguanjia.oss-cn-shenzhen.aliyuncs.com/200000042019071710551751shopLogo.jpg","merchId":"10000012","provencName":"广东省","ranges":"旅馆/酒店/度假区","shopId":"20000004","shopName":"广州易呗信息服务有限公司","status":2,"updateTime":1563332118000,"version  ":0},{"address":"林敏","alipayId":"136464694","busLicNum":"92440605MA4010654","cityName":"佛山市","insertTime":1563333773000,"legalIdno":"4500031995030195514","legalName":"李志堂","legalPhone":"13144440004","logoUrl":"http://huikajinguanjia.oss-cn-shenzhen.aliyuncs.com/200000062019071711225253shopLogo.jpg","merchId":"10000012","password":"dd4346edbd089a1e39e731834b77d4f5","provencName":"广东省","ranges":"旅馆/酒店/度假区","shopId":"20000006","shopName":"佛山市南海区欧源通讯设备经营部","status":2,"updateTime":1563333773000,"version":0}]
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
         * address : 长安大学
         * alipayId : 13469161661
         * authToken : 201907BB5a4eca4a9e194403b1b037e10bb2bX88
         * busLicNum : 913502003MA2YFK526D
         * cityName : 厦门市
         * insertTime : 1563331019000
         * legalIdno : 4500031995030195514
         * legalName : 苏亚波
         * legalPhone : 13144440008
         * logoUrl : http://huikajinguanjia.oss-cn-shenzhen.aliyuncs.com/200000002019071710365970shopLogo.jpg
         * merchId : 10000017
         * provencName : 福建省
         * ranges : 汽车用品
         * shopId : 20000000
         * shopName : 厦门粤闽福商贸有限公司
         * status : 2
         * updateTime : 1563331019000
         * version : 0
         * version   : 0
         * password : dd4346edbd089a1e39e731834b77d4f5
         */

        private String address;
        private String alipayId;
        private String authToken;
        private String busLicNum;
        private String cityName;
        private long insertTime;
        private String legalIdno;
        private String legalName;
        private String legalPhone;
        private String logoUrl;
        private String merchId;
        private String provencName;
        private String ranges;
        private String shopId;
        private String shopName;
        private int status;
        private long updateTime;
        private int version;
        private String password;

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

        public String getAuthToken() {
            return authToken;
        }

        public void setAuthToken(String authToken) {
            this.authToken = authToken;
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
        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}
