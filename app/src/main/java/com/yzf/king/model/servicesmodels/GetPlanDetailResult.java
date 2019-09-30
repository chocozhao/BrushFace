package com.yzf.king.model.servicesmodels;

import com.yzf.king.model.BaseModel;

import java.util.List;

public class GetPlanDetailResult extends BaseModel {
    private int code;
    private List<DataBean> data;
    private String message;

    public static class DataBean {
        private List<ConsumeBean> consume;
        private String repayDate;
        private List<RepaymentBean> repayment;

        public static class ConsumeBean {
            private String orderId;
            private String planTime;
            private int status;
            private int transAmt;
            private int transType;

            public int getTransType() {
                return this.transType;
            }

            public void setTransType(int i) {
                this.transType = i;
            }

            public String getOrderId() {
                return this.orderId;
            }

            public void setOrderId(String str) {
                this.orderId = str;
            }

            public String getPlanTime() {
                return this.planTime;
            }

            public void setPlanTime(String str) {
                this.planTime = str;
            }

            public int getTransAmt() {
                return this.transAmt;
            }

            public void setTransAmt(int i) {
                this.transAmt = i;
            }

            public int getStatus() {
                return this.status;
            }

            public void setStatus(int i) {
                this.status = i;
            }
        }

        public static class RepaymentBean {
            private String orderId;
            private String planTime;
            private int status;
            private int transAmt;
            private int transType;

            public int getTransType() {
                return this.transType;
            }

            public void setTransType(int i) {
                this.transType = i;
            }

            public String getOrderId() {
                return this.orderId;
            }

            public void setOrderId(String str) {
                this.orderId = str;
            }

            public String getPlanTime() {
                return this.planTime;
            }

            public void setPlanTime(String str) {
                this.planTime = str;
            }

            public int getTransAmt() {
                return this.transAmt;
            }

            public void setTransAmt(int i) {
                this.transAmt = i;
            }

            public int getStatus() {
                return this.status;
            }

            public void setStatus(int i) {
                this.status = i;
            }
        }

        public String getRepayDate() {
            return this.repayDate;
        }

        public void setRepayDate(String str) {
            this.repayDate = str;
        }

        public List<ConsumeBean> getConsume() {
            return this.consume;
        }

        public void setConsume(List<ConsumeBean> list) {
            this.consume = list;
        }

        public List<RepaymentBean> getRepayment() {
            return this.repayment;
        }

        public void setRepayment(List<RepaymentBean> list) {
            this.repayment = list;
        }
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int i) {
        this.code = i;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public List<DataBean> getData() {
        return this.data;
    }

    public void setData(List<DataBean> list) {
        this.data = list;
    }
}