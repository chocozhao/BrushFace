package com.yzf.king.model.servicesmodels;


import com.yzf.king.model.BaseModel;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * ClaseName：CountTransInfoResult
 * Description：
 * Author：chocozhao
 * QQ: 1027313530
 * Createtime：2019/5/14 17:11
 * Modified By：
 * Fixtime：2019/5/14 17:11
 * FixDescription：
 **/

public class CountTransInfoResult extends BaseModel {


    /**
     * code : 200
     * data : {"totalSum":2770.11,"dayCount":0,"monthSum":0.15,"totalCount":12,"daySum":0,"monthCount":3}
     * message : 获取成功
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
         * totalSum : 2770.11
         * dayCount : 0
         * monthSum : 0.15
         * totalCount : 12
         * daySum : 0
         * monthCount : 3
         */

        private BigDecimal totalSum;
        private int dayCount;
        private double monthSum;
        private int totalCount;
        private int daySum;
        private int monthCount;

        public BigDecimal getTotalSum() {
            return totalSum;
        }

        public void setTotalSum(BigDecimal totalSum) {
            this.totalSum = totalSum;
        }

        public int getDayCount() {
            return dayCount;
        }

        public void setDayCount(int dayCount) {
            this.dayCount = dayCount;
        }

        public double getMonthSum() {
            return monthSum;
        }

        public void setMonthSum(double monthSum) {
            this.monthSum = monthSum;
        }

        public int getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(int totalCount) {
            this.totalCount = totalCount;
        }

        public int getDaySum() {
            return daySum;
        }

        public void setDaySum(int daySum) {
            this.daySum = daySum;
        }

        public int getMonthCount() {
            return monthCount;
        }

        public void setMonthCount(int monthCount) {
            this.monthCount = monthCount;
        }
    }
}
