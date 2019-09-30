package com.yzf.king.model.servicesmodels;


import com.yzf.king.model.BaseModel;

import java.util.List;

/**
 * ClaseName：GetGoodsInfoResults
 * Description：
 * Author：chocozhao
 * QQ: 1027313530
 * Createtime：2019/7/10 15:59
 * Modified By：
 * Fixtime：2019/7/10 15:59
 * FixDescription：
 **/

public class GetGoodsInfoResults extends BaseModel {


    /**
     * code : 200
     * data : [{"amt":198888,"amtDesc":"惊喜价 198,888 ￥","buyDesc":"商家免费领取","detailsImgUrl5":"","goodsDesc":"5G时代支付模式趋势，100台起售","goodsId":1,"goodsName":"支付宝刷脸二代蜻蜓F4","mealId":3,"status":1,"version":0},{"add1":"","add2":"","add3":"","amt":19999,"amtDesc":"惊喜价 19,999 ￥","buyDesc":"商家免费领取","detailsImgUrl1":"","detailsImgUrl2":"","detailsImgUrl3":"","detailsImgUrl4":"","detailsImgUrl5":"","goodsDesc":"5G时代支付模式趋势，10台起售","goodsId":2,"goodsName":"支付宝刷脸二代蜻蜓F4","insertTime":1562755313000,"logoImgUrl":"","mealId":2,"remark":"","status":1,"titleImgUrl1":"","titleImgUrl2":"","titleImgUrl3":"","titleImgUrl4":"","updateTime":1562755315000,"version":0},{"add1":"","add2":"","add3":"","amt":2000,"amtDesc":"惊喜价 2,000 ￥","buyDesc":"商家免费领取","detailsImgUrl1":"","detailsImgUrl2":"","detailsImgUrl3":"","detailsImgUrl4":"","detailsImgUrl5":"","goodsDesc":"5G时代支付模式趋势，1台起售","goodsId":3,"goodsName":"支付宝刷脸二代蜻蜓F4","insertTime":1562755313000,"logoImgUrl":"","mealId":1,"remark":"","status":1,"titleImgUrl1":"","titleImgUrl2":"","titleImgUrl3":"","titleImgUrl4":"","updateTime":1562755315000,"version":0}]
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

    public static class DataBean {
        /**
         * amt : 198888
         * amtDesc : 惊喜价 198,888 ￥
         * buyDesc : 商家免费领取
         * detailsImgUrl5 :
         * goodsDesc : 5G时代支付模式趋势，100台起售
         * goodsId : 1
         * goodsName : 支付宝刷脸二代蜻蜓F4
         * mealId : 3
         * status : 1
         * version : 0
         * add1 :
         * add2 :
         * add3 :
         * detailsImgUrl1 :
         * detailsImgUrl2 :
         * detailsImgUrl3 :
         * detailsImgUrl4 :
         * insertTime : 1562755313000
         * logoImgUrl :
         * remark :
         * titleImgUrl1 :
         * titleImgUrl2 :
         * titleImgUrl3 :
         * titleImgUrl4 :
         * updateTime : 1562755315000
         */

        private String amt;
        private String amtDesc;
        private String buyDesc;
        private String detailsImgUrl5;
        private String goodsDesc;
        private int goodsId;
        private String goodsName;
        private int mealId;
        private int status;
        private int version;
        private String add1;
        private String add2;
        private String add3;
        private String detailsImgUrl1;
        private String detailsImgUrl2;
        private String detailsImgUrl3;
        private String detailsImgUrl4;
        private long insertTime;
        private String logoImgUrl;
        private String remark;
        private String titleImgUrl1;
        private String titleImgUrl2;
        private String titleImgUrl3;
        private String titleImgUrl4;
        private long updateTime;
        private String actionUrl;

        public String getAmt() {
            return amt;
        }

        public void setAmt(String amt) {
            this.amt = amt;
        }

        public String getAmtDesc() {
            return amtDesc;
        }

        public void setAmtDesc(String amtDesc) {
            this.amtDesc = amtDesc;
        }

        public String getBuyDesc() {
            return buyDesc;
        }

        public void setBuyDesc(String buyDesc) {
            this.buyDesc = buyDesc;
        }

        public String getDetailsImgUrl5() {
            return detailsImgUrl5;
        }

        public void setDetailsImgUrl5(String detailsImgUrl5) {
            this.detailsImgUrl5 = detailsImgUrl5;
        }

        public String getGoodsDesc() {
            return goodsDesc;
        }

        public void setGoodsDesc(String goodsDesc) {
            this.goodsDesc = goodsDesc;
        }

        public int getGoodsId() {
            return goodsId;
        }

        public void setGoodsId(int goodsId) {
            this.goodsId = goodsId;
        }

        public String getGoodsName() {
            return goodsName;
        }

        public void setGoodsName(String goodsName) {
            this.goodsName = goodsName;
        }

        public int getMealId() {
            return mealId;
        }

        public void setMealId(int mealId) {
            this.mealId = mealId;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
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

        public String getDetailsImgUrl1() {
            return detailsImgUrl1;
        }

        public void setDetailsImgUrl1(String detailsImgUrl1) {
            this.detailsImgUrl1 = detailsImgUrl1;
        }

        public String getDetailsImgUrl2() {
            return detailsImgUrl2;
        }

        public void setDetailsImgUrl2(String detailsImgUrl2) {
            this.detailsImgUrl2 = detailsImgUrl2;
        }

        public String getDetailsImgUrl3() {
            return detailsImgUrl3;
        }

        public void setDetailsImgUrl3(String detailsImgUrl3) {
            this.detailsImgUrl3 = detailsImgUrl3;
        }

        public String getDetailsImgUrl4() {
            return detailsImgUrl4;
        }

        public void setDetailsImgUrl4(String detailsImgUrl4) {
            this.detailsImgUrl4 = detailsImgUrl4;
        }

        public long getInsertTime() {
            return insertTime;
        }

        public void setInsertTime(long insertTime) {
            this.insertTime = insertTime;
        }

        public String getLogoImgUrl() {
            return logoImgUrl;
        }

        public void setLogoImgUrl(String logoImgUrl) {
            this.logoImgUrl = logoImgUrl;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getTitleImgUrl1() {
            return titleImgUrl1;
        }

        public void setTitleImgUrl1(String titleImgUrl1) {
            this.titleImgUrl1 = titleImgUrl1;
        }

        public String getTitleImgUrl2() {
            return titleImgUrl2;
        }

        public void setTitleImgUrl2(String titleImgUrl2) {
            this.titleImgUrl2 = titleImgUrl2;
        }

        public String getTitleImgUrl3() {
            return titleImgUrl3;
        }

        public void setTitleImgUrl3(String titleImgUrl3) {
            this.titleImgUrl3 = titleImgUrl3;
        }

        public String getTitleImgUrl4() {
            return titleImgUrl4;
        }

        public void setTitleImgUrl4(String titleImgUrl4) {
            this.titleImgUrl4 = titleImgUrl4;
        }

        public long getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(long updateTime) {
            this.updateTime = updateTime;
        }

        public String getActionUrl() {
            return actionUrl;
        }

        public void setActionUrl(String actionUrl) {
            this.actionUrl = actionUrl;
        }
    }
}
