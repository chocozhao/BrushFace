package com.yzf.king.model.servicesmodels;


import com.yzf.king.model.BaseModel;

import java.io.Serializable;
import java.util.List;

/**
 * ClaseName：GetTransInfoCountResult
 * Description：
 * Author：chocozhao
 * QQ: 1027313530
 * Createtime：2019/5/15 16:51
 * Modified By：
 * Fixtime：2019/5/15 16:51
 * FixDescription：
 **/

public class GetTransInfoCountResult extends BaseModel {

    /**
     * code : 200
     * data : [{"month":"01","year":"2019","info":[{"totalAmt":1040.3,"month":"05","year":"2019","count":8,"day":"01"},{"totalAmt":98.5,"month":"04","year":"2019","count":7,"day":"01"},{"totalAmt":15502.13,"month":"03","year":"2019","count":61,"day":"01"},{"totalAmt":46753.68,"month":"02","year":"2019","count":72,"day":"01"}]}]
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
         * month : 01
         * year : 2019
         * info : [{"totalAmt":1040.3,"month":"05","year":"2019","count":8,"day":"01"},{"totalAmt":98.5,"month":"04","year":"2019","count":7,"day":"01"},{"totalAmt":15502.13,"month":"03","year":"2019","count":61,"day":"01"},{"totalAmt":46753.68,"month":"02","year":"2019","count":72,"day":"01"}]
         */

        private String month;
        private String year;
        private List<InfoBean> info;

        public String getMonth() {
            return month;
        }

        public void setMonth(String month) {
            this.month = month;
        }

        public String getYear() {
            return year;
        }

        public void setYear(String year) {
            this.year = year;
        }

        public List<InfoBean> getInfo() {
            return info;
        }

        public void setInfo(List<InfoBean> info) {
            this.info = info;
        }

        public static class InfoBean implements Serializable {
            /**
             * totalAmt : 1040.3
             * month : 05
             * year : 2019
             * count : 8
             * day : 01
             */

            private double totalAmt;
            private String month;
            private String year;
            private int count;
            private String day;

            public double getTotalAmt() {
                return totalAmt;
            }

            public void setTotalAmt(double totalAmt) {
                this.totalAmt = totalAmt;
            }

            public String getMonth() {
                return month;
            }

            public void setMonth(String month) {
                this.month = month;
            }

            public String getYear() {
                return year;
            }

            public void setYear(String year) {
                this.year = year;
            }

            public int getCount() {
                return count;
            }

            public void setCount(int count) {
                this.count = count;
            }

            public String getDay() {
                return day;
            }

            public void setDay(String day) {
                this.day = day;
            }
        }
    }
}
