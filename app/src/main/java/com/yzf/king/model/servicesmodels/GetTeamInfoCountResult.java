package com.yzf.king.model.servicesmodels;


import com.yzf.king.model.BaseModel;

import java.io.Serializable;
import java.util.List;

/**
 * ClaseName：GetTeamInfoCountResult
 * Description：
 * Author：chocozhao
 * QQ: 1027313530
 * Createtime：2019/5/17 15:10
 * Modified By：
 * Fixtime：2019/5/17 15:10
 * FixDescription：
 **/

public class GetTeamInfoCountResult extends BaseModel {

    /**
     * code : 200
     * data : [{"month":"01","year":"2019","info":[{"month":"03","year":"2019","count":3,"type":"注册","day":"01"},{"month":"02","year":"2019","count":8,"type":"注册","day":"01"}]}]
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
         * info : [{"month":"03","year":"2019","count":3,"type":"注册","day":"01"},{"month":"02","year":"2019","count":8,"type":"注册","day":"01"}]
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
             * month : 03
             * year : 2019
             * count : 3
             * type : 注册
             * day : 01
             */

            private String month;
            private String year;
            private int count;
            private String type;
            private String day;

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

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
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
