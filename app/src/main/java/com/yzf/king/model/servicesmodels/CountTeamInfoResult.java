package com.yzf.king.model.servicesmodels;


import com.yzf.king.model.BaseModel;

/**
 * ClaseName：CountTeamInfoResult
 * Description：
 * Author：chocozhao
 * QQ: 1027313530
 * Createtime：2019/5/14 17:12
 * Modified By：
 * Fixtime：2019/5/14 17:12
 * FixDescription：
 **/

public class CountTeamInfoResult extends BaseModel {


    /**
     * code : 200
     * data : {"dayCount":0,"monthUpgrade":0,"totalUpgrade":7,"totalCount":16,"dayUpgrade":0,"monthCount":0}
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

    public static class DataBean {
        /**
         * dayCount : 0
         * monthUpgrade : 0
         * totalUpgrade : 7
         * totalCount : 16
         * dayUpgrade : 0
         * monthCount : 0
         */

        private int dayCount;
        private int monthUpgrade;
        private int totalUpgrade;
        private int totalCount;
        private int dayUpgrade;
        private int monthCount;

        public int getDayCount() {
            return dayCount;
        }

        public void setDayCount(int dayCount) {
            this.dayCount = dayCount;
        }

        public int getMonthUpgrade() {
            return monthUpgrade;
        }

        public void setMonthUpgrade(int monthUpgrade) {
            this.monthUpgrade = monthUpgrade;
        }

        public int getTotalUpgrade() {
            return totalUpgrade;
        }

        public void setTotalUpgrade(int totalUpgrade) {
            this.totalUpgrade = totalUpgrade;
        }

        public int getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(int totalCount) {
            this.totalCount = totalCount;
        }

        public int getDayUpgrade() {
            return dayUpgrade;
        }

        public void setDayUpgrade(int dayUpgrade) {
            this.dayUpgrade = dayUpgrade;
        }

        public int getMonthCount() {
            return monthCount;
        }

        public void setMonthCount(int monthCount) {
            this.monthCount = monthCount;
        }
    }
}
