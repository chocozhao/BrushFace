package com.yzf.king.model.servicesmodels;

import com.yzf.king.model.BaseModel;

import java.util.List;

/**
 * ClaseName：GetProfitResult
 * Description：
 * Author：JensenWei
 * QQ: 2188307188
 * Createtime：2019/2/27 19:43
 * Modified By：
 * Fixtime：2019/2/27 19:43
 * FixDescription：
 */
public class GetProfitResult extends BaseModel {

    /**
     * code : 200
     * data : {"dataDtl":[{"insertTime":1518314473000,"merchId":"10000016","merchLevel":"1","profitAmt":242.98,"settDate":"20190211","type":"01","updateTime":1518314473000},{"insertTime":1518228073000,"merchId":"10000016","merchLevel":"1","profitAmt":184.01,"settDate":"20190210","type":"01","updateTime":1518228073000},{"insertTime":1518141673000,"merchId":"10000016","merchLevel":"1","profitAmt":144.01,"settDate":"20190209","type":"01","updateTime":1518141673000}],"sumAmt":571}
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
         * dataDtl : [{"insertTime":1518314473000,"merchId":"10000016","merchLevel":"1","profitAmt":242.98,"settDate":"20190211","type":"01","updateTime":1518314473000},{"insertTime":1518228073000,"merchId":"10000016","merchLevel":"1","profitAmt":184.01,"settDate":"20190210","type":"01","updateTime":1518228073000},{"insertTime":1518141673000,"merchId":"10000016","merchLevel":"1","profitAmt":144.01,"settDate":"20190209","type":"01","updateTime":1518141673000}]
         * sumAmt : 571
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

        public static class DataDtlBean {
            /**
             * insertTime : 1518314473000
             * merchId : 10000016
             * merchLevel : 1
             * profitAmt : 242.98
             * settDate : 20190211
             * type : 01
             * updateTime : 1518314473000
             */

            private long insertTime;
            private String merchId;
            private String merchLevel;
            private String profitAmt;
            private String settDate;
            private String type;
            private long updateTime;

            public long getInsertTime() {
                return insertTime;
            }

            public void setInsertTime(long insertTime) {
                this.insertTime = insertTime;
            }

            public String getMerchId() {
                return merchId;
            }

            public void setMerchId(String merchId) {
                this.merchId = merchId;
            }

            public String getMerchLevel() {
                return merchLevel;
            }

            public void setMerchLevel(String merchLevel) {
                this.merchLevel = merchLevel;
            }

            public String getProfitAmt() {
                return profitAmt;
            }

            public void setProfitAmt(String profitAmt) {
                this.profitAmt = profitAmt;
            }

            public String getSettDate() {
                return settDate;
            }

            public void setSettDate(String settDate) {
                this.settDate = settDate;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public long getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(long updateTime) {
                this.updateTime = updateTime;
            }
        }
    }
}
