package com.yzf.king.model.servicesmodels;


import com.yzf.king.model.BaseModel;

import java.util.List;

/**
 * ClaseName：GetRecommendShopResults
 * Description：
 * Author：chocozhao
 * QQ: 1027313530
 * Createtime：2019/8/23 11:47
 * Modified By：
 * Fixtime：2019/8/23 11:47
 * FixDescription：
 **/

public class GetRecommendShopResults extends BaseModel {


    /**
     * code : 200
     * data : [{"address":"广兴村兴德街79","cityName":"汕头市","insertTime":1564122609000,"logoUrl":"http://leshou.oss-cn-shenzhen.aliyuncs.com/200000462019072614300864shopLogo.jpg","lootTimeOut":"240","machinNum":1,"merchId":"10000394","orderId":112,"provencName":"广东省","shopId":"20000046","shopName":"汕头市龙湖区百家数码店","status":4,"supplierId":"10002448","supplierLootTime":1566114300000,"supplierName":"汕头分公司","supplierStatus":2},{"address":"1244818171","cityName":"韶关市","insertTime":1564365055000,"logoUrl":"http://leshou.oss-cn-shenzhen.aliyuncs.com/200001382019072909505448shopLogo.jpg","lootTimeOut":"240","machinNum":1,"merchId":"10000848","orderId":178,"provencName":"广东省","shopId":"20000138","shopName":"新丰县鹿角巷奶杂","status":4,"supplierId":"10000001","supplierLootTime":1564384712000,"supplierName":"梁志华","supplierStatus":2},{"address":"汕头市龙湖区南山居委汕充路15号","cityName":"汕头市","insertTime":1564389872000,"logoUrl":"http://leshou.oss-cn-shenzhen.aliyuncs.com/200001672019072916443113shopLogo.jpg","lootTimeOut":"240","machinNum":1,"merchId":"10000922","orderId":194,"provencName":"广东省","shopId":"20000167","shopName":"汕头市龙湖区曰香奶茶店","status":4,"supplierId":"10000273","supplierLootTime":1566458271000,"supplierName":"揭阳分公司","supplierStatus":2},{"address":"陈厝合宁和街211号第二格铺面","cityName":"汕头市","insertTime":1564478254000,"logoUrl":"http://leshou.oss-cn-shenzhen.aliyuncs.com/200002282019073017173355shopLogo.jpg","lootTimeOut":"240","machinNum":1,"merchId":"10000943","orderId":235,"provencName":"广东省","shopId":"20000228","shopName":"汕头市龙湖区良杰奶茶店","status":4,"supplierId":"10002448","supplierLootTime":1565518632000,"supplierName":"沈楚杰","supplierStatus":2},{"address":"广兴村兴德街53-54号","cityName":"汕头市","insertTime":1564545199000,"logoUrl":"http://leshou.oss-cn-shenzhen.aliyuncs.com/20000265201907311153187shopLogo.jpg","lootTimeOut":"240","machinNum":1,"merchId":"10001256","orderId":258,"provencName":"广东省","shopId":"20000265","shopName":"汕头市龙湖区阿梁食杂商","status":4,"supplierId":"10000273","supplierLootTime":1566282948000,"supplierName":"揭阳分公司","supplierStatus":2},{"address":"金南街35号","cityName":"汕头市","insertTime":1564650890000,"logoUrl":"http://leshou.oss-cn-shenzhen.aliyuncs.com/20000336201908011714494shopLogo.jpg","lootTimeOut":"240","machinNum":1,"merchId":"10001510","orderId":316,"provencName":"广东省","shopId":"20000336","shopName":"无","status":4,"supplierId":"10000273","supplierLootTime":1566218884000,"supplierName":"揭阳分公司","supplierStatus":2},{"address":"仁里街108号","cityName":"哈尔滨市","insertTime":1564701599000,"logoUrl":"http://leshou.oss-cn-shenzhen.aliyuncs.com/200003472019080207195963shopLogo.jpg","lootTimeOut":"240","machinNum":1,"merchId":"10001402","orderId":325,"provencName":"黑龙江省","shopId":"20000347","shopName":"哈尔滨市道外区沐恩针织批发","status":4,"supplierId":"10001395","supplierLootTime":1566299302000,"supplierName":"任国辉","supplierStatus":2},{"address":"沙头街莲湖村大街七巷6号101","cityName":"广州市","insertTime":1564724788000,"logoUrl":"http://leshou.oss-cn-shenzhen.aliyuncs.com/200003742019080213462785shopLogo.jpg","lootTimeOut":"240","machinNum":1,"merchId":"10001619","orderId":346,"provencName":"广东省","shopId":"20000374","shopName":"佛山市南海区欧源通讯设备经营部","status":4,"supplierId":"10000001","supplierLootTime":1564908678000,"supplierName":"梁志华","supplierStatus":2},{"address":"汉川西湖大道福星城A区","cityName":"孝感市","insertTime":1564990388000,"logoUrl":"http://leshou.oss-cn-shenzhen.aliyuncs.com/200004962019080515330837shopLogo.jpg","lootTimeOut":"240","machinNum":1,"merchId":"10002176","orderId":459,"provencName":"湖北省","shopId":"20000496","shopName":"汉川市源滋味饭堂","status":4,"supplierId":"10001602","supplierLootTime":1565663782000,"supplierName":"黄正艮","supplierStatus":2},{"address":"河源市河源大道北1号河源市丽日中心城第1层一楼1202号铺","cityName":"河源市","insertTime":1564996159000,"logoUrl":"http://leshou.oss-cn-shenzhen.aliyuncs.com/200005012019080517091975shopLogo.jpg","lootTimeOut":"7200","machinNum":1,"merchId":"10002192","orderId":463,"provencName":"广东省","shopId":"20000501","shopName":"河源市源城区精明眼镜店","status":4,"supplierId":"10000670","supplierLootTime":1565317779000,"supplierStatus":2}]
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

    public static class DataBean {
        /**
         * address : 广兴村兴德街79
         * cityName : 汕头市
         * insertTime : 1564122609000
         * logoUrl : http://leshou.oss-cn-shenzhen.aliyuncs.com/200000462019072614300864shopLogo.jpg
         * lootTimeOut : 240
         * machinNum : 1
         * merchId : 10000394
         * orderId : 112
         * provencName : 广东省
         * shopId : 20000046
         * shopName : 汕头市龙湖区百家数码店
         * status : 4
         * supplierId : 10002448
         * supplierLootTime : 1566114300000
         * supplierName : 汕头分公司
         * supplierStatus : 2
         */

        private String address;
        private String cityName;
        private long insertTime;
        private String logoUrl;
        private String lootTimeOut;
        private int machinNum;
        private String merchId;
        private int orderId;
        private String provencName;
        private String shopId;
        private String shopName;
        private int status;
        private String supplierId;
        private long supplierLootTime;
        private String supplierName;
        private int supplierStatus;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
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

        public String getLogoUrl() {
            return logoUrl;
        }

        public void setLogoUrl(String logoUrl) {
            this.logoUrl = logoUrl;
        }

        public String getLootTimeOut() {
            return lootTimeOut;
        }

        public void setLootTimeOut(String lootTimeOut) {
            this.lootTimeOut = lootTimeOut;
        }

        public int getMachinNum() {
            return machinNum;
        }

        public void setMachinNum(int machinNum) {
            this.machinNum = machinNum;
        }

        public String getMerchId() {
            return merchId;
        }

        public void setMerchId(String merchId) {
            this.merchId = merchId;
        }

        public int getOrderId() {
            return orderId;
        }

        public void setOrderId(int orderId) {
            this.orderId = orderId;
        }

        public String getProvencName() {
            return provencName;
        }

        public void setProvencName(String provencName) {
            this.provencName = provencName;
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

        public String getSupplierId() {
            return supplierId;
        }

        public void setSupplierId(String supplierId) {
            this.supplierId = supplierId;
        }

        public long getSupplierLootTime() {
            return supplierLootTime;
        }

        public void setSupplierLootTime(long supplierLootTime) {
            this.supplierLootTime = supplierLootTime;
        }

        public String getSupplierName() {
            return supplierName;
        }

        public void setSupplierName(String supplierName) {
            this.supplierName = supplierName;
        }

        public int getSupplierStatus() {
            return supplierStatus;
        }

        public void setSupplierStatus(int supplierStatus) {
            this.supplierStatus = supplierStatus;
        }
    }
}
