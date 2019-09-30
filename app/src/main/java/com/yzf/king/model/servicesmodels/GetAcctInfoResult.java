package com.yzf.king.model.servicesmodels;

import com.yzf.king.kit.AppTools;
import com.yzf.king.model.BaseModel;

import java.util.List;

public class GetAcctInfoResult extends BaseModel {


    /**
     * code : 200
     * data : {"settBankName":"招商银行","data":[{"totalAmt":18000,"externFee":100,"transFee":600,"frozenAmt":1060,"acctType":"01","avlbAmt":16940}],"settCardNo":"6214832034158753","sumAmt":18000,"settPhone":"18620028795"}
     * message : 查询成功
     */

    private int code;
    private DataBeanX data;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBeanX getData() {
        return data;
    }

    public void setData(DataBeanX data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class DataBeanX {
        /**
         * settBankName : 招商银行
         * data : [{"totalAmt":18000,"externFee":100,"transFee":600,"frozenAmt":1060,"acctType":"01","avlbAmt":16940}]
         * settCardNo : 6214832034158753
         * sumAmt : 18000
         * settPhone : 18620028795
         */

        private String settBankName;
        private String settCardNo;
        private int sumAmt;
        private String settPhone;
        private String yesterdayAmt;
        private String todayAmt;
        private List<DataBean> data;

        public String getSettBankName() {
            return settBankName;
        }

        public void setSettBankName(String settBankName) {
            this.settBankName = settBankName;
        }

        public String getSettCardNo() {
            return settCardNo;
        }

        public void setSettCardNo(String settCardNo) {
            this.settCardNo = settCardNo;
        }

        public int getSumAmt() {
            return sumAmt;
        }

        public void setSumAmt(int sumAmt) {
            this.sumAmt = sumAmt;
        }

        public String getSettPhone() {
            return settPhone;
        }

        public void setSettPhone(String settPhone) {
            this.settPhone = settPhone;
        }

        public String getYesterdayAmt() {
            return yesterdayAmt;
        }

        public void setYesterdayAmt(String yesterdayAmt) {
            this.yesterdayAmt = yesterdayAmt;
        }

        public String getTodayAmt() {
            return todayAmt;
        }

        public void setTodayAmt(String todayAmt) {
            this.todayAmt = todayAmt;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * totalAmt : 18000
             * externFee : 100
             * transFee : 600
             * frozenAmt : 1060
             * acctType : 01
             * avlbAmt : 16940
             */

            private int totalAmt;
            private String externFee;
            private String transFee;
            private int frozenAmt;
            private String acctType;
            private String avlbAmt;

            public int getTotalAmt() {
                return totalAmt;
            }

            public void setTotalAmt(int totalAmt) {
                this.totalAmt = totalAmt;
            }

            public String getExternFee() {
                return AppTools.formatL2Y(externFee);
            }

            public void setExternFee(String externFee) {
                this.externFee = externFee;
            }

            public String getTransFee() {
                return transFee;
            }

            public void setTransFee(String transFee) {
                this.transFee = transFee;
            }

            public int getFrozenAmt() {
                return frozenAmt;
            }

            public void setFrozenAmt(int frozenAmt) {
                this.frozenAmt = frozenAmt;
            }

            public String getAcctType() {
                return acctType;
            }

            public void setAcctType(String acctType) {
                this.acctType = acctType;
            }

            public String getAvlbAmt() {
                return AppTools.formatL2Y(avlbAmt);
            }

            public void setAvlbAmt(String avlbAmt) {
                this.avlbAmt = avlbAmt;
            }
        }
    }
}