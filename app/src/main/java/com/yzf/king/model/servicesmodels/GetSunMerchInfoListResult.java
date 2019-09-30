package com.yzf.king.model.servicesmodels;


import com.yzf.king.model.BaseModel;

import java.io.Serializable;
import java.util.List;

/**
 * ClaseName：GetSunMerchInfoListResult
 * Description：
 * Author：chocozhao
 * QQ: 1027313530
 * Createtime：2019/8/12 20:27
 * Modified By：
 * Fixtime：2019/8/12 20:27
 * FixDescription：
 **/

public class GetSunMerchInfoListResult extends BaseModel {


    /**
     * code : 200
     * data : {"insertTime":1565603786000,"subList":[{"channelCode":"ALIPAY","orderStatus":"00","settBankName":"ALIPAY(1367***4661)","settName":"李志堂","shopId":"20000012","shopName":"佛山市南海区桂源手机店"},{"channelCode":"WXPAY","orderStatus":"03","settBankName":"中国农业银行广东省佛山市南海区狮山支行(6228***5514)","settName":"李志堂","shopId":"20000012","shopName":"佛山市南海区桂源手机店"},{"channelCode":"UNIONPAY","orderStatus":"09","settBankName":"中国农业银行广东省佛山市南海区狮山支行(6228***5514)","settName":"李志堂","shopId":"20000012","shopName":"佛山市南海区桂源手机店"}],"shopName":"佛山市南海区桂源手机店","shopId":"20000012","logoUrl":"http://smilepay.oss-cn-shenzhen.aliyuncs.com/200000122019081218022486shopLogo.jpg"}
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

    public static class DataBean implements Serializable {
        /**
         * insertTime : 1565603786000
         * subList : [{"channelCode":"ALIPAY","orderStatus":"00","settBankName":"ALIPAY(1367***4661)","settName":"李志堂","shopId":"20000012","shopName":"佛山市南海区桂源手机店"},{"channelCode":"WXPAY","orderStatus":"03","settBankName":"中国农业银行广东省佛山市南海区狮山支行(6228***5514)","settName":"李志堂","shopId":"20000012","shopName":"佛山市南海区桂源手机店"},{"channelCode":"UNIONPAY","orderStatus":"09","settBankName":"中国农业银行广东省佛山市南海区狮山支行(6228***5514)","settName":"李志堂","shopId":"20000012","shopName":"佛山市南海区桂源手机店"}]
         * shopName : 佛山市南海区桂源手机店
         * shopId : 20000012
         * logoUrl : http://smilepay.oss-cn-shenzhen.aliyuncs.com/200000122019081218022486shopLogo.jpg
         */

        private long insertTime;
        private String shopName;
        private String shopId;
        private String logoUrl;
        private List<SubListBean> subList;

        public long getInsertTime() {
            return insertTime;
        }

        public void setInsertTime(long insertTime) {
            this.insertTime = insertTime;
        }

        public String getShopName() {
            return shopName;
        }

        public void setShopName(String shopName) {
            this.shopName = shopName;
        }

        public String getShopId() {
            return shopId;
        }

        public void setShopId(String shopId) {
            this.shopId = shopId;
        }

        public String getLogoUrl() {
            return logoUrl;
        }

        public void setLogoUrl(String logoUrl) {
            this.logoUrl = logoUrl;
        }

        public List<SubListBean> getSubList() {
            return subList;
        }

        public void setSubList(List<SubListBean> subList) {
            this.subList = subList;
        }

        public static class SubListBean implements Serializable {
            /**
             * channelCode : ALIPAY
             * orderStatus : 00
             * settBankName : ALIPAY(1367***4661)
             * settName : 李志堂
             * shopId : 20000012
             * shopName : 佛山市南海区桂源手机店
             */

            private String channelCode;
            private String orderStatus;
            private String settBankName;
            private String settName;
            private String shopId;
            private String shopName;
            private String authUrl;
            private String addStatus;
            private String remark;

            public String getChannelCode() {
                return channelCode;
            }

            public void setChannelCode(String channelCode) {
                this.channelCode = channelCode;
            }

            public String getOrderStatus() {
                return orderStatus;
            }

            public void setOrderStatus(String orderStatus) {
                this.orderStatus = orderStatus;
            }

            public String getSettBankName() {
                return settBankName;
            }

            public void setSettBankName(String settBankName) {
                this.settBankName = settBankName;
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

            public String getAuthUrl() {
                return authUrl;
            }

            public void setAuthUrl(String authUrl) {
                this.authUrl = authUrl;
            }

            public String getAddStatus() {
                return addStatus;
            }

            public void setAddStatus(String addStatus) {
                this.addStatus = addStatus;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }
        }


    }
}
