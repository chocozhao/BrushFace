package com.yzf.king.model.servicesmodels;


import com.yzf.king.model.BaseModel;

import java.util.List;

/**
 * ClaseName：GetShopInfoDtlResults
 * Description：
 * Author：chocozhao
 * QQ: 1027313530
 * Createtime：2019/8/27 20:05
 * Modified By：
 * Fixtime：2019/8/27 20:05
 * FixDescription：
 **/

public class GetShopInfoDtlResults extends BaseModel {


    /**
     * code : 200
     * data : {"address":"番禺广场","shopLogo":"http://smilepay.oss-cn-shenzhen.aliyuncs.com/200000542019082217251683shopLogo.jpg","instrtTime":1566465674000,"shopName":"广东易支付网终科技有限公司","subMerchList":[{"addStatus":"8","channelCode":"ALIPAY","channelName":"支付宝","insertTime":1566476486000,"merchId":"10000055","merchStatus":"00","orderStatus":"01","rate":38,"settBankName":"ALIPAY","settBankNo":"ALIPAY","settCardNo":"13766461661","settName":"易智军","shopId":"20000054","shopName":"广东易支付网终科技有限公司","updateTime":1566476486000,"upgradeStatus":"00","version":0},{"add1":"你","add2":"哈哈","add3":"嘻嘻","addStatus":"8","channelCode":"UNIONPAY","channelName":"银联","insertTime":1566476486000,"merchId":"10000055","merchStatus":"00","orderStatus":"09","rate":38,"settBankName":"中国银行","settBankNo":"11","settCardNo":"13676454545","settName":"易智军","settPhone":"31616455456","shopId":"20000054","shopName":"广东易支付网终科技有限公司","updateTime":1566476486000,"upgradeStatus":"00","version":0},{"addStatus":"8","channelCode":"WXPAY","channelName":"微信","insertTime":1566476486000,"merchId":"10000055","merchStatus":"00","orderStatus":"00","rate":1,"remark":"费率取值有误，请检查后再试。","serviceMerchId":"1547626481","serviceMerchKey":"3246765745745654645ABCBDBCBC0010","serviceMerchName":"广州乐收刷脸科技产业控股有限公司","settBankName":"中国银行","settBankNo":"11","settCardNo":"13676454545","settName":"易智军","settPhone":"31616455456","shopId":"20000054","shopName":"广东易支付网终科技有限公司","updateTime":1566899970000,"upgradeStatus":"00","version":0}],"busLicNum":"S2612014097564(1-1)","cityName":"沈asd","phone":"1354545516","name":"易智军","termStatus":0,"supplierStatus":0,"mailId":"16676645454@gmail.com","shopId":"20000054","provinceName":"辽宁省","alipayId":"13766461661","sumAmt":0,"status":3}
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
         * address : 番禺广场
         * shopLogo : http://smilepay.oss-cn-shenzhen.aliyuncs.com/200000542019082217251683shopLogo.jpg
         * instrtTime : 1566465674000
         * shopName : 广东易支付网终科技有限公司
         * subMerchList : [{"addStatus":"8","channelCode":"ALIPAY","channelName":"支付宝","insertTime":1566476486000,"merchId":"10000055","merchStatus":"00","orderStatus":"01","rate":38,"settBankName":"ALIPAY","settBankNo":"ALIPAY","settCardNo":"13766461661","settName":"易智军","shopId":"20000054","shopName":"广东易支付网终科技有限公司","updateTime":1566476486000,"upgradeStatus":"00","version":0},{"add1":"你","add2":"哈哈","add3":"嘻嘻","addStatus":"8","channelCode":"UNIONPAY","channelName":"银联","insertTime":1566476486000,"merchId":"10000055","merchStatus":"00","orderStatus":"09","rate":38,"settBankName":"中国银行","settBankNo":"11","settCardNo":"13676454545","settName":"易智军","settPhone":"31616455456","shopId":"20000054","shopName":"广东易支付网终科技有限公司","updateTime":1566476486000,"upgradeStatus":"00","version":0},{"addStatus":"8","channelCode":"WXPAY","channelName":"微信","insertTime":1566476486000,"merchId":"10000055","merchStatus":"00","orderStatus":"00","rate":1,"remark":"费率取值有误，请检查后再试。","serviceMerchId":"1547626481","serviceMerchKey":"3246765745745654645ABCBDBCBC0010","serviceMerchName":"广州乐收刷脸科技产业控股有限公司","settBankName":"中国银行","settBankNo":"11","settCardNo":"13676454545","settName":"易智军","settPhone":"31616455456","shopId":"20000054","shopName":"广东易支付网终科技有限公司","updateTime":1566899970000,"upgradeStatus":"00","version":0}]
         * busLicNum : S2612014097564(1-1)
         * cityName : 沈asd
         * phone : 1354545516
         * name : 易智军
         * termStatus : 0
         * supplierStatus : 0
         * mailId : 16676645454@gmail.com
         * shopId : 20000054
         * provinceName : 辽宁省
         * alipayId : 13766461661
         * sumAmt : 0
         * status : 3
         */

        private String address;
        private String shopLogo;
        private long instrtTime;
        private String shopName;
        private String busLicNum;
        private String cityName;
        private String phone;
        private String name;
        private int termStatus;
        private int supplierStatus;
        private String mailId;
        private String shopId;
        private String provinceName;
        private String alipayId;
        private int sumAmt;
        private int status;
        private List<SubMerchListBean> subMerchList;

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

        public String getMailId() {
            return mailId;
        }

        public void setMailId(String mailId) {
            this.mailId = mailId;
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

        public String getAlipayId() {
            return alipayId;
        }

        public void setAlipayId(String alipayId) {
            this.alipayId = alipayId;
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

        public List<SubMerchListBean> getSubMerchList() {
            return subMerchList;
        }

        public void setSubMerchList(List<SubMerchListBean> subMerchList) {
            this.subMerchList = subMerchList;
        }

        public static class SubMerchListBean {
            /**
             * addStatus : 8
             * channelCode : ALIPAY
             * channelName : 支付宝
             * insertTime : 1566476486000
             * merchId : 10000055
             * merchStatus : 00
             * orderStatus : 01
             * rate : 38
             * settBankName : ALIPAY
             * settBankNo : ALIPAY
             * settCardNo : 13766461661
             * settName : 易智军
             * shopId : 20000054
             * shopName : 广东易支付网终科技有限公司
             * updateTime : 1566476486000
             * upgradeStatus : 00
             * version : 0
             * add1 : 你
             * add2 : 哈哈
             * add3 : 嘻嘻
             * settPhone : 31616455456
             * remark : 费率取值有误，请检查后再试。
             * serviceMerchId : 1547626481
             * serviceMerchKey : 3246765745745654645ABCBDBCBC0010
             * serviceMerchName : 广州乐收刷脸科技产业控股有限公司
             */

            private String addStatus;
            private String channelCode;
            private String channelName;
            private long insertTime;
            private String merchId;
            private String merchStatus;
            private String orderStatus;
            private int rate;
            private String settBankName;
            private String settBankNo;
            private String settCardNo;
            private String settName;
            private String shopId;
            private String shopName;
            private long updateTime;
            private String upgradeStatus;
            private int version;
            private String add1;
            private String add2;
            private String add3;
            private String settPhone;
            private String remark;
            private String serviceMerchId;
            private String serviceMerchKey;
            private String serviceMerchName;

            public String getAddStatus() {
                return addStatus;
            }

            public void setAddStatus(String addStatus) {
                this.addStatus = addStatus;
            }

            public String getChannelCode() {
                return channelCode;
            }

            public void setChannelCode(String channelCode) {
                this.channelCode = channelCode;
            }

            public String getChannelName() {
                return channelName;
            }

            public void setChannelName(String channelName) {
                this.channelName = channelName;
            }

            public long getInsertTime() {
                return insertTime;
            }

            public void setInsertTime(long insertTime) {
                this.insertTime = insertTime;
            }

            public String getMerchId() {
                return merchId;
            }

            public void setMerchId(String merchId) {
                this.merchId = merchId;
            }

            public String getMerchStatus() {
                return merchStatus;
            }

            public void setMerchStatus(String merchStatus) {
                this.merchStatus = merchStatus;
            }

            public String getOrderStatus() {
                return orderStatus;
            }

            public void setOrderStatus(String orderStatus) {
                this.orderStatus = orderStatus;
            }

            public int getRate() {
                return rate;
            }

            public void setRate(int rate) {
                this.rate = rate;
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

            public long getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(long updateTime) {
                this.updateTime = updateTime;
            }

            public String getUpgradeStatus() {
                return upgradeStatus;
            }

            public void setUpgradeStatus(String upgradeStatus) {
                this.upgradeStatus = upgradeStatus;
            }

            public int getVersion() {
                return version;
            }

            public void setVersion(int version) {
                this.version = version;
            }

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

            public String getSettPhone() {
                return settPhone;
            }

            public void setSettPhone(String settPhone) {
                this.settPhone = settPhone;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public String getServiceMerchId() {
                return serviceMerchId;
            }

            public void setServiceMerchId(String serviceMerchId) {
                this.serviceMerchId = serviceMerchId;
            }

            public String getServiceMerchKey() {
                return serviceMerchKey;
            }

            public void setServiceMerchKey(String serviceMerchKey) {
                this.serviceMerchKey = serviceMerchKey;
            }

            public String getServiceMerchName() {
                return serviceMerchName;
            }

            public void setServiceMerchName(String serviceMerchName) {
                this.serviceMerchName = serviceMerchName;
            }
        }
    }
}
