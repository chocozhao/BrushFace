package com.yzf.king.model.servicesmodels;


import com.yzf.king.model.BaseModel;

/**
 * ClaseName：GetAddShopInfoResults
 * Description：
 * Author：chocozhao
 * QQ: 1027313530
 * Createtime：2019/8/20 17:29
 * Modified By：
 * Fixtime：2019/8/20 17:29
 * FixDescription：
 **/

public class GetAddShopInfoResults extends BaseModel {


    /**
     * code : 400
     * data : {"add1":"nongbenyong@163.com","add2":"18620028795","add3":"","address":"广州市南沙区丰泽东路106号","agentId":"","alipayId":"236052191@qq.com","areaCode":"440100","areaName":"","authFlag":"0","authToken":"","busLicNum":"91440101MA59P62Y8C","busLicType":"0","businessName":"","businessUrl":"http://smilepay.oss-cn-shenzhen.aliyuncs.com/10000055201908141429233065mmexport1565763859558.jpg","cityCode":"440100","cityName":"广州市","idBackUrl":"http://smilepay.oss-cn-shenzhen.aliyuncs.com/10000055201908141430219224mmexport1565763856356.jpg","idFrontUrl":"http://smilepay.oss-cn-shenzhen.aliyuncs.com/10000055201908141430115624mmexport1565763854367.jpg","idnoExpDate":"2016-11-11,2036-11-11","inShopUrl":"http://smilepay.oss-cn-shenzhen.aliyuncs.com/1000005520190814143135736mmexport1565611670635.jpg","insertTime":1565764050000,"legalIdno":"452122198604223017","legalName":"农本勇","legalPhone":"18620028795","lisExpDate":"2017-06-13","logoUrl":"http://smilepay.oss-cn-shenzhen.aliyuncs.com/200000352019081414324528shopLogo.jpg","merchId":"10000055","merchName":"","outShopUr1":"http://smilepay.oss-cn-shenzhen.aliyuncs.com/10000055201908141431418713mmexport1565611670635.jpg","outShopUr2":"http://smilepay.oss-cn-shenzhen.aliyuncs.com/10000055201908141431486754mmexport1565611667295.jpg","outShopUr3":"http://smilepay.oss-cn-shenzhen.aliyuncs.com/10000055201908141431547056mmexport1565611668986.jpg","password":"dd4346edbd089a1e39e731834b77d4f5","phone":"","provencCode":"440000","provencName":"广东省","ranges":"线下零售-数码电器/电脑办公","remark":"012121","settBankName":"交通银行","settBankNo":"11","settCardNo":"6222620710019715893","settName":"农本勇","settPhone":"18620028795","shopId":"20000035","shopName":"广州易呗信息服务有限公司","shopUrl":"http://smilepay.oss-cn-shenzhen.aliyuncs.com/10000055201908141431295455mmexport1565611679406.jpg","status":1,"updateTime":1566293215000,"version":0,"zfbToken":"","zizhiUrl":"http://smilepay.oss-cn-shenzhen.aliyuncs.com/10000055201908141431081138mmexport1565763859558.jpg"}
     * message : 店铺状态已经确定，不能修改
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
         * add1 : nongbenyong@163.com
         * add2 : 18620028795
         * add3 :
         * address : 广州市南沙区丰泽东路106号
         * agentId :
         * alipayId : 236052191@qq.com
         * areaCode : 440100
         * areaName :
         * authFlag : 0
         * authToken :
         * busLicNum : 91440101MA59P62Y8C
         * busLicType : 0
         * businessName :
         * businessUrl : http://smilepay.oss-cn-shenzhen.aliyuncs.com/10000055201908141429233065mmexport1565763859558.jpg
         * cityCode : 440100
         * cityName : 广州市
         * idBackUrl : http://smilepay.oss-cn-shenzhen.aliyuncs.com/10000055201908141430219224mmexport1565763856356.jpg
         * idFrontUrl : http://smilepay.oss-cn-shenzhen.aliyuncs.com/10000055201908141430115624mmexport1565763854367.jpg
         * idnoExpDate : 2016-11-11,2036-11-11
         * inShopUrl : http://smilepay.oss-cn-shenzhen.aliyuncs.com/1000005520190814143135736mmexport1565611670635.jpg
         * insertTime : 1565764050000
         * legalIdno : 452122198604223017
         * legalName : 农本勇
         * legalPhone : 18620028795
         * lisExpDate : 2017-06-13
         * logoUrl : http://smilepay.oss-cn-shenzhen.aliyuncs.com/200000352019081414324528shopLogo.jpg
         * merchId : 10000055
         * merchName :
         * outShopUr1 : http://smilepay.oss-cn-shenzhen.aliyuncs.com/10000055201908141431418713mmexport1565611670635.jpg
         * outShopUr2 : http://smilepay.oss-cn-shenzhen.aliyuncs.com/10000055201908141431486754mmexport1565611667295.jpg
         * outShopUr3 : http://smilepay.oss-cn-shenzhen.aliyuncs.com/10000055201908141431547056mmexport1565611668986.jpg
         * password : dd4346edbd089a1e39e731834b77d4f5
         * phone :
         * provencCode : 440000
         * provencName : 广东省
         * ranges : 线下零售-数码电器/电脑办公
         * remark : 012121
         * settBankName : 交通银行
         * settBankNo : 11
         * settCardNo : 6222620710019715893
         * settName : 农本勇
         * settPhone : 18620028795
         * shopId : 20000035
         * shopName : 广州易呗信息服务有限公司
         * shopUrl : http://smilepay.oss-cn-shenzhen.aliyuncs.com/10000055201908141431295455mmexport1565611679406.jpg
         * status : 1
         * updateTime : 1566293215000
         * version : 0
         * zfbToken :
         * zizhiUrl : http://smilepay.oss-cn-shenzhen.aliyuncs.com/10000055201908141431081138mmexport1565763859558.jpg
         */

        private String add1;
        private String add2;
        private String add3;
        private String address;
        private String agentId;
        private String alipayId;
        private String areaCode;
        private String areaName;
        private String authFlag;
        private String authToken;
        private String busLicNum;
        private String busLicType;
        private String businessName;
        private String businessUrl;
        private String cityCode;
        private String cityName;
        private String idBackUrl;
        private String idFrontUrl;
        private String idnoExpDate;
        private String inShopUrl;
        private long insertTime;
        private String legalIdno;
        private String legalName;
        private String legalPhone;
        private String lisExpDate;
        private String logoUrl;
        private String merchId;
        private String merchName;
        private String outShopUr1;
        private String outShopUr2;
        private String outShopUr3;
        private String password;
        private String phone;
        private String provencCode;
        private String provencName;
        private String ranges;
        private String remark;
        private String settBankName;
        private String settBankNo;
        private String settCardNo;
        private String settName;
        private String settPhone;
        private String shopId;
        private String shopName;
        private String shopUrl;
        private int status;
        private long updateTime;
        private int version;
        private String zfbToken;
        private String zizhiUrl;

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

        public String getAdd3() {
            return add3;
        }

        public void setAdd3(String add3) {
            this.add3 = add3;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getAgentId() {
            return agentId;
        }

        public void setAgentId(String agentId) {
            this.agentId = agentId;
        }

        public String getAlipayId() {
            return alipayId;
        }

        public void setAlipayId(String alipayId) {
            this.alipayId = alipayId;
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

        public String getIdBackUrl() {
            return idBackUrl;
        }

        public void setIdBackUrl(String idBackUrl) {
            this.idBackUrl = idBackUrl;
        }

        public String getIdFrontUrl() {
            return idFrontUrl;
        }

        public void setIdFrontUrl(String idFrontUrl) {
            this.idFrontUrl = idFrontUrl;
        }

        public String getIdnoExpDate() {
            return idnoExpDate;
        }

        public void setIdnoExpDate(String idnoExpDate) {
            this.idnoExpDate = idnoExpDate;
        }

        public String getInShopUrl() {
            return inShopUrl;
        }

        public void setInShopUrl(String inShopUrl) {
            this.inShopUrl = inShopUrl;
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

        public String getMerchName() {
            return merchName;
        }

        public void setMerchName(String merchName) {
            this.merchName = merchName;
        }

        public String getOutShopUr1() {
            return outShopUr1;
        }

        public void setOutShopUr1(String outShopUr1) {
            this.outShopUr1 = outShopUr1;
        }

        public String getOutShopUr2() {
            return outShopUr2;
        }

        public void setOutShopUr2(String outShopUr2) {
            this.outShopUr2 = outShopUr2;
        }

        public String getOutShopUr3() {
            return outShopUr3;
        }

        public void setOutShopUr3(String outShopUr3) {
            this.outShopUr3 = outShopUr3;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
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

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getSettBankName() {
            return settBankName;
        }

        public void setSettBankName(String settBankName) {
            this.settBankName = settBankName;
        }

        public String getSettBankNo() {
            return settBankNo;
        }

        public void setSettBankNo(String settBankNo) {
            this.settBankNo = settBankNo;
        }

        public String getSettCardNo() {
            return settCardNo;
        }

        public void setSettCardNo(String settCardNo) {
            this.settCardNo = settCardNo;
        }

        public String getSettName() {
            return settName;
        }

        public void setSettName(String settName) {
            this.settName = settName;
        }

        public String getSettPhone() {
            return settPhone;
        }

        public void setSettPhone(String settPhone) {
            this.settPhone = settPhone;
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

        public String getShopUrl() {
            return shopUrl;
        }

        public void setShopUrl(String shopUrl) {
            this.shopUrl = shopUrl;
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

        public String getZfbToken() {
            return zfbToken;
        }

        public void setZfbToken(String zfbToken) {
            this.zfbToken = zfbToken;
        }

        public String getZizhiUrl() {
            return zizhiUrl;
        }

        public void setZizhiUrl(String zizhiUrl) {
            this.zizhiUrl = zizhiUrl;
        }
    }
}
