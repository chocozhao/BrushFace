package com.yzf.king.model.servicesmodels;


import com.yzf.king.model.BaseModel;

/**
 * ClaseName：GetReApplyOrderResults
 * Description：
 * Author：chocozhao
 * QQ: 1027313530
 * Createtime：2019/9/25 14:47
 * Modified By：
 * Fixtime：2019/9/25 14:47
 * FixDescription：
 **/

public class GetReApplyOrderResults extends BaseModel {


    /**
     * code : 200
     * data : {"applyType":"2","address":"武昌区万达广场12，13栋1一2层30房","orderId":26,"shopLogo":"http://leshou.oss-cn-shenzhen.aliyuncs.com/200015702019082814241641shopLogo.jpg","shopName":"武汉市武昌区盛鑫旺烟酒礼品超市","aliPayId":"18571598686","cityName":"武汉市","phone":"18571598686","name":"周大站","supplierStatus":0,"machinNum":1,"mailId":"50950577@qq.com","shopId":"20001570","provinceName":"湖北省","applyDate":"20190828","status":7}
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
         * applyType : 2
         * address : 武昌区万达广场12，13栋1一2层30房
         * orderId : 26
         * shopLogo : http://leshou.oss-cn-shenzhen.aliyuncs.com/200015702019082814241641shopLogo.jpg
         * shopName : 武汉市武昌区盛鑫旺烟酒礼品超市
         * aliPayId : 18571598686
         * cityName : 武汉市
         * phone : 18571598686
         * name : 周大站
         * supplierStatus : 0
         * machinNum : 1
         * mailId : 50950577@qq.com
         * shopId : 20001570
         * provinceName : 湖北省
         * applyDate : 20190828
         * status : 7
         */

        private String applyType;
        private String address;
        private int orderId;
        private String shopLogo;
        private String shopName;
        private String aliPayId;
        private String cityName;
        private String phone;
        private String name;
        private int supplierStatus;
        private int machinNum;
        private String mailId;
        private String shopId;
        private String provinceName;
        private String applyDate;
        private int status;

        public String getApplyType() {
            return applyType;
        }

        public void setApplyType(String applyType) {
            this.applyType = applyType;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public int getOrderId() {
            return orderId;
        }

        public void setOrderId(int orderId) {
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

        public String getAliPayId() {
            return aliPayId;
        }

        public void setAliPayId(String aliPayId) {
            this.aliPayId = aliPayId;
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
    }
}
