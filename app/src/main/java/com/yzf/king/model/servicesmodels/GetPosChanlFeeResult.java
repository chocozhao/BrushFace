package com.yzf.king.model.servicesmodels;

import com.yzf.king.model.BaseModel;

import java.io.Serializable;
import java.util.List;

/**
 * ClaseName：GetKameiIncomeResult
 * Description：
 * Author：JensenWei
 * QQ: 2188307188
 * Createtime：2018/12/6 14:19
 * Modified By：
 * Fixtime：2018/12/6 14:19
 * FixDescription：
 */
public class GetPosChanlFeeResult extends BaseModel {

    /**
     * code : 200
     * data : [{"add1":"","add2":"","busCode":"5201","insertTime":1544085428000,"status":1,"tradeCode":"3110","tradeFee":"0.55+3","tradeName":"标准商户","tradeRange":"10-3000","transType":"52","updateTime":1544085428000},{"add1":"","add2":"","busCode":"5301","insertTime":1544085428000,"status":1,"tradeCode":"3111","tradeFee":"0.65+3","tradeName":"商旅服务","tradeRange":"10-20000","transType":"53","updateTime":1544085428000},{"add1":"","add2":"","busCode":"5401","insertTime":1544085428000,"status":1,"tradeCode":"3112","tradeFee":"0.75+3","tradeName":"餐饮娱乐","tradeRange":"10-20000","transType":"54","updateTime":1544085428000},{"add1":"","add2":"","busCode":"5501","insertTime":1544085428000,"status":1,"tradeCode":"3113","tradeFee":"0.56+1","tradeName":"日用百货","tradeRange":"10-5000","transType":"55","updateTime":1544085428000},{"add1":"","add2":"","busCode":"5601","insertTime":1544085428000,"status":1,"tradeCode":"3114","tradeFee":"0.73+3","tradeName":"高端珠宝","tradeRange":"10-50000","transType":"56","updateTime":1544085428000},{"add1":"","add2":"","busCode":"5701","insertTime":1544085428000,"status":1,"tradeCode":"3300","tradeFee":"0.69+3","tradeName":"超市家电","tradeRange":"10-20000","transType":"57","updateTime":1544085428000},{"add1":"","add2":"","busCode":"5801","insertTime":1544085428000,"status":1,"tradeCode":"3301","tradeFee":"0.49+3","tradeName":"便民服务","tradeRange":"10-1000","transType":"58","updateTime":1544085428000},{"add1":"","add2":"","busCode":"5901","insertTime":1544085428000,"status":1,"tradeCode":"3302","tradeFee":"0.73+3","tradeName":"房产汽车","tradeRange":"10-50000","transType":"59","updateTime":1544085428000}]
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
         * add1 :
         * add2 :
         * busCode : 5201
         * insertTime : 1544085428000
         * status : 1
         * tradeCode : 3110
         * tradeFee : 0.55+3
         * tradeName : 标准商户
         * tradeRange : 10-3000
         * transType : 52
         * updateTime : 1544085428000
         */

        private String add1;
        private String add2;
        private String busCode;
        private long insertTime;
        private int status;
        private String tradeCode;
        private String tradeFee;
        private String tradeName;
        private String tradeRange;
        private String transType;
        private long updateTime;
        private String city;

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

        public String getBusCode() {
            return busCode;
        }

        public void setBusCode(String busCode) {
            this.busCode = busCode;
        }

        public long getInsertTime() {
            return insertTime;
        }

        public void setInsertTime(long insertTime) {
            this.insertTime = insertTime;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getTradeCode() {
            return tradeCode;
        }

        public void setTradeCode(String tradeCode) {
            this.tradeCode = tradeCode;
        }

        public String getTradeFee() {
            return tradeFee;
        }

        public void setTradeFee(String tradeFee) {
            this.tradeFee = tradeFee;
        }

        public String getTradeName() {
            return tradeName;
        }

        public void setTradeName(String tradeName) {
            this.tradeName = tradeName;
        }

        public String getTradeRange() {
            return tradeRange;
        }

        public void setTradeRange(String tradeRange) {
            this.tradeRange = tradeRange;
        }

        public String getTransType() {
            return transType;
        }

        public void setTransType(String transType) {
            this.transType = transType;
        }

        public long getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(long updateTime) {
            this.updateTime = updateTime;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }
    }
}
