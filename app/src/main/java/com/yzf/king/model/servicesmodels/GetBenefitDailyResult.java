package com.yzf.king.model.servicesmodels;


import com.yzf.king.model.BaseModel;

import java.io.Serializable;
import java.util.List;

/**
 * ClaseName：GetBenefitDailyResult
 * Description：
 * Author：chocozhao
 * QQ: 1027313530
 * Createtime：2019/7/23 10:47
 * Modified By：
 * Fixtime：2019/7/23 10:47
 * FixDescription：
 **/

public class GetBenefitDailyResult extends BaseModel {


    /**
     * code : 200
     * data : {"dataDtl":[{"totalAmt":18000,"count":1,"day":"20190722"}],"sumAmt":18000}
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

    public static class DataBean{
        /**
         * dataDtl : [{"totalAmt":18000,"count":1,"day":"20190722"}]
         * sumAmt : 18000
         */

        private String sumAmt;
        private List<DataDtlBean> dataDtl;

        public String getSumAmt() {
            return sumAmt;
        }

        public void setSumAmt(String sumAmt) {
            this.sumAmt = sumAmt;
        }

        public List<DataDtlBean> getDataDtl() {
            return dataDtl;
        }

        public void setDataDtl(List<DataDtlBean> dataDtl) {
            this.dataDtl = dataDtl;
        }

        public static class DataDtlBean implements Serializable{
            /**
             * totalAmt : 18000
             * count : 1
             * day : 20190722
             */

            private String totalAmt;
            private int count;
            private String day;

            public String getTotalAmt() {
                return totalAmt;
            }

            public void setTotalAmt(String totalAmt) {
                this.totalAmt = totalAmt;
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
