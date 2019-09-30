package com.yzf.king.model.servicesmodels;

import com.yzf.king.kit.AppTools;
import com.yzf.king.model.BaseModel;

import java.util.List;

/**
 * ClaseName：RandomPlanCardResult
 * Description：
 * Author：JensenWei
 * QQ: 2188307188
 * Createtime：2018/8/24 11:12
 * Modified By：
 * Fixtime：2018/8/24 11:12
 * FixDescription：
 */
public class RandomPlanCardResult extends BaseModel {
    private int code;
    private DataBean data;
    private String message;

    public static class DataBean {
        private List<RowsBean> rows;
        private String times;

        public static class RowsBean {
            private String returnAmt;
            private String returnDate;
            private String transAmt;

            public String getReturnDate() {
                return this.returnDate;
            }

            public void setReturnDate(String str) {
                this.returnDate = str;
            }

            public String getTransAmt() {
                return AppTools.formatF2Y(this.transAmt);
            }

            public void setTransAmt(String str) {
                this.transAmt = str;
            }

            public String getReturnAmt() {
                return AppTools.formatF2Y(this.returnAmt);
            }

            public void setReturnAmt(String str) {
                this.returnAmt = str;
            }
        }

        public String getTimes() {
            return this.times;
        }

        public void setTimes(String str) {
            this.times = str;
        }

        public List<RowsBean> getRows() {
            return this.rows;
        }

        public void setRows(List<RowsBean> list) {
            this.rows = list;
        }
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int i) {
        this.code = i;
    }

    public DataBean getData() {
        return this.data;
    }

    public void setData(DataBean dataBean) {
        this.data = dataBean;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String str) {
        this.message = str;
    }
}
