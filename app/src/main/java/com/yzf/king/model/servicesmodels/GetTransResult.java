package com.yzf.king.model.servicesmodels;

import com.yzf.king.model.BaseModel;

import java.util.List;

/**
 * ClaseName：GetTransResult
 * Description：
 * Author：JensenWei
 * QQ: 2188307188
 * Createtime：2019/2/28 14:55
 * Modified By：
 * Fixtime：2019/2/28 14:55
 * FixDescription：
 */
public class GetTransResult extends BaseModel {

    /**
     * code : 200
     * data : {"dataDtl":[{"merchId":"10000016","settDate":"20190221","transAmt":110},{"merchId":"10000016","settDate":"20190222","transAmt":1862.17},{"merchId":"10000016","settDate":"20190225","transAmt":10}],"sumAmt":1982.17}
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

    public static class DataBean {
        /**
         * dataDtl : [{"merchId":"10000016","settDate":"20190221","transAmt":110},{"merchId":"10000016","settDate":"20190222","transAmt":1862.17},{"merchId":"10000016","settDate":"20190225","transAmt":10}]
         * sumAmt : 1982.17
         */

        private double sumAmt;
        private List<DataDtlBean> dataDtl;

        public double getSumAmt() {
            return sumAmt;
        }

        public void setSumAmt(double sumAmt) {
            this.sumAmt = sumAmt;
        }

        public List<DataDtlBean> getDataDtl() {
            return dataDtl;
        }

        public void setDataDtl(List<DataDtlBean> dataDtl) {
            this.dataDtl = dataDtl;
        }

        public static class DataDtlBean {
            /**
             * merchId : 10000016
             * settDate : 20190221
             * transAmt : 110
             */

            private String merchId;
            private String settDate;
            private String transAmt;

            public String getMerchId() {
                return merchId;
            }

            public void setMerchId(String merchId) {
                this.merchId = merchId;
            }

            public String getSettDate() {
                return settDate;
            }

            public void setSettDate(String settDate) {
                this.settDate = settDate;
            }

            public String getTransAmt() {
                return transAmt;
            }

            public void setTransAmt(String transAmt) {
                this.transAmt = transAmt;
            }
        }
    }
}
