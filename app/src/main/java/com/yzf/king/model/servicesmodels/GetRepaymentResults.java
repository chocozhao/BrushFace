package com.yzf.king.model.servicesmodels;

import com.yzf.king.kit.AppTools;
import com.yzf.king.model.BaseModel;

import java.util.List;

/**
 * ClaseName：GetRepaymentResults
 * Description：
 * Author：JensenWei
 * QQ: 2188307188
 * Createtime：2017/9/2 18:25
 * Modified By：
 * Fixtime：2017/9/2 18:25
 * FixDescription：
 */

public class GetRepaymentResults extends BaseModel {

    /**
     * code : 200
     * data : {"respCode":"0000","times":"1","respDesc":"规划生成成功","rows":[{"returnAmt":"70000","transAmt":"70000","returnDate":"20180125"},{"returnAmt":"30000","transAmt":"30000","returnDate":"20180125"}]}
     * message : 规划生成成功
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
         * respCode : 0000
         * times : 1
         * respDesc : 规划生成成功
         * rows : [{"returnAmt":"70000","transAmt":"70000","returnDate":"20180125"},{"returnAmt":"30000","transAmt":"30000","returnDate":"20180125"}]
         */

        private String respCode;
        private String times;
        private String respDesc;
        private List<RowsBean> rows;

        public String getRespCode() {
            return respCode;
        }

        public void setRespCode(String respCode) {
            this.respCode = respCode;
        }

        public String getTimes() {
            return times;
        }

        public void setTimes(String times) {
            this.times = times;
        }

        public String getRespDesc() {
            return respDesc;
        }

        public void setRespDesc(String respDesc) {
            this.respDesc = respDesc;
        }

        public List<RowsBean> getRows() {
            return rows;
        }

        public void setRows(List<RowsBean> rows) {
            this.rows = rows;
        }

        public static class RowsBean {
            /**
             * returnAmt : 70000
             * transAmt : 70000
             * returnDate : 20180125
             */

            private String returnAmt;
            private String transAmt;
            private String returnDate;

            public String getReturnAmt() {
               return AppTools.formatF2Y(returnAmt);
            }

            public void setReturnAmt(String returnAmt) {
                this.returnAmt = returnAmt;
            }

            public String getTransAmt() {
                return transAmt;
            }

            public void setTransAmt(String transAmt) {
                this.transAmt = transAmt;
            }

            public String getReturnDate() {
                return returnDate;
            }

            public void setReturnDate(String returnDate) {
                this.returnDate = returnDate;
            }
        }
    }
}
