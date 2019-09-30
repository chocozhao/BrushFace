package com.yzf.king.model.servicesmodels;

import com.yzf.king.kit.AppTools;
import com.yzf.king.model.BaseModel;

import java.io.Serializable;
import java.util.List;

/**
 * ClaseName：GetPlanDtlResults
 * Description：
 * Author：JensenWei
 * QQ: 2188307188
 * Createtime：2017/9/5 17:29
 * Modified By：
 * Fixtime：2017/9/5 17:29
 * FixDescription：
 */

public class GetPlanDtlResults extends BaseModel {

    /**
     * respCode : 00
     * respMsg : 查询成功
     * data : [{"repaymentAmount":180000,"depositAmount":65000,"cardId":"6225768771872395","Status":"01","Detail":[{"id":4519,"regId":4259,"orderId":"20170905100124000000004259","cardId":"6225768771872395","merchId":"15999519806","repayDate":"20170903","returnAmt":2000,"returnStatus":"00","returnTime":null,"transAmt":1720,"transStatus":"00","transTime":null,"insertTime":1504576884000,"updateTime":1504576884000},{"id":4521,"regId":4259,"orderId":"20170905100124000000004259","cardId":"6225768771872395","merchId":"15999519806","repayDate":"20170903","returnAmt":5000,"returnStatus":"00","returnTime":null,"transAmt":5630,"transStatus":"00","transTime":null,"insertTime":1504576884000,"updateTime":1504576884000},{"id":4520,"regId":4259,"orderId":"20170905100124000000004259","cardId":"6225768771872395","merchId":"15999519806","repayDate":"20170903","returnAmt":3000,"returnStatus":"00","returnTime":null,"transAmt":2650,"transStatus":"00","transTime":null,"insertTime":1504576884000,"updateTime":1504576884000}],"feeAmount":1568,"DetailList":[{"list":[{"id":"582","reg_id":"4413","dtl_id":"5073","trans_type":"72","order_id":null,"card_id":"6225768771872395","merch_id":"15999519806","repay_date":"20171101","insert_time":"2017-10-31","update_time":"2017-10-31","status":"0","remark":null,"trans_amt":"10500","plan_time":"1557","last_id":"581"},{"id":"581","reg_id":"4413","dtl_id":"5073","trans_type":"71","order_id":null,"card_id":"6225768771872395","merch_id":"15999519806","repay_date":"20171101","insert_time":"2017-10-31","update_time":"2017-10-31","status":"0","remark":null,"trans_amt":"20640","plan_time":"0905","last_id":"0"},{"id":"583","reg_id":"4413","dtl_id":"5073","trans_type":"72","order_id":null,"card_id":"6225768771872395","merch_id":"15999519806","repay_date":"20171101","insert_time":"2017-10-31","update_time":"2017-10-31","status":"0","remark":null,"trans_amt":"10500","plan_time":"2328","last_id":"582"}]},{"list":[{"id":"585","reg_id":"4413","dtl_id":"5074","trans_type":"72","order_id":null,"card_id":"6225768771872395","merch_id":"15999519806","repay_date":"20171102","insert_time":"2017-10-31","update_time":"2017-10-31","status":"0","remark":null,"trans_amt":"13940","plan_time":"1543","last_id":"584"},{"id":"584","reg_id":"4413","dtl_id":"5074","trans_type":"71","order_id":null,"card_id":"6225768771872395","merch_id":"15999519806","repay_date":"20171102","insert_time":"2017-10-31","update_time":"2017-10-31","status":"0","remark":null,"trans_amt":"52880","plan_time":"1003","last_id":"583"},{"id":"586","reg_id":"4413","dtl_id":"5074","trans_type":"72","order_id":null,"card_id":"6225768771872395","merch_id":"15999519806","repay_date":"20171102","insert_time":"2017-10-31","update_time":"2017-10-31","status":"0","remark":null,"trans_amt":"13940","plan_time":"1753","last_id":"585"},{"id":"587","reg_id":"4413","dtl_id":"5074","trans_type":"72","order_id":null,"card_id":"6225768771872395","merch_id":"15999519806","repay_date":"20171102","insert_time":"2017-10-31","update_time":"2017-10-31","status":"0","remark":null,"trans_amt":"25120","plan_time":"2026","last_id":"586"}]},{"list":[{"id":"588","reg_id":"4413","dtl_id":"5075","trans_type":"71","order_id":null,"card_id":"6225768771872395","merch_id":"15999519806","repay_date":"20171104","insert_time":"2017-10-31","update_time":"2017-10-31","status":"0","remark":null,"trans_amt":"41480","plan_time":"0907","last_id":"587"},{"id":"589","reg_id":"4413","dtl_id":"5075","trans_type":"72","order_id":null,"card_id":"6225768771872395","merch_id":"15999519806","repay_date":"20171104","insert_time":"2017-10-31","update_time":"2017-10-31","status":"0","remark":null,"trans_amt":"11560","plan_time":"1559","last_id":"588"},{"id":"590","reg_id":"4413","dtl_id":"5075","trans_type":"72","order_id":null,"card_id":"6225768771872395","merch_id":"15999519806","repay_date":"20171104","insert_time":"2017-10-31","update_time":"2017-10-31","status":"0","remark":null,"trans_amt":"15760","plan_time":"1659","last_id":"589"},{"id":"591","reg_id":"4413","dtl_id":"5075","trans_type":"72","order_id":null,"card_id":"6225768771872395","merch_id":"15999519806","repay_date":"20171104","insert_time":"2017-10-31","update_time":"2017-10-31","status":"0","remark":null,"trans_amt":"13680","plan_time":"2253","last_id":"590"}]}],"merchId":"15999519806","orderId":"20171031100859000000004413"}]
     * page : null
     */

    private int code;
    private String message;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean implements Serializable {
        /**
         * repaymentAmount : 180000
         * depositAmount : 65000
         * cardId : 6225768771872395
         * Status : 01
         * Detail : [{"id":4519,"regId":4259,"orderId":"20170905100124000000004259","cardId":"6225768771872395","merchId":"15999519806","repayDate":"20170903","returnAmt":2000,"returnStatus":"00","returnTime":null,"transAmt":1720,"transStatus":"00","transTime":null,"insertTime":1504576884000,"updateTime":1504576884000},{"id":4521,"regId":4259,"orderId":"20170905100124000000004259","cardId":"6225768771872395","merchId":"15999519806","repayDate":"20170903","returnAmt":5000,"returnStatus":"00","returnTime":null,"transAmt":5630,"transStatus":"00","transTime":null,"insertTime":1504576884000,"updateTime":1504576884000},{"id":4520,"regId":4259,"orderId":"20170905100124000000004259","cardId":"6225768771872395","merchId":"15999519806","repayDate":"20170903","returnAmt":3000,"returnStatus":"00","returnTime":null,"transAmt":2650,"transStatus":"00","transTime":null,"insertTime":1504576884000,"updateTime":1504576884000}]
         * feeAmount : 1568
         * DetailList : [{"list":[{"id":"582","reg_id":"4413","dtl_id":"5073","trans_type":"72","order_id":null,"card_id":"6225768771872395","merch_id":"15999519806","repay_date":"20171101","insert_time":"2017-10-31","update_time":"2017-10-31","status":"0","remark":null,"trans_amt":"10500","plan_time":"1557","last_id":"581"},{"id":"581","reg_id":"4413","dtl_id":"5073","trans_type":"71","order_id":null,"card_id":"6225768771872395","merch_id":"15999519806","repay_date":"20171101","insert_time":"2017-10-31","update_time":"2017-10-31","status":"0","remark":null,"trans_amt":"20640","plan_time":"0905","last_id":"0"},{"id":"583","reg_id":"4413","dtl_id":"5073","trans_type":"72","order_id":null,"card_id":"6225768771872395","merch_id":"15999519806","repay_date":"20171101","insert_time":"2017-10-31","update_time":"2017-10-31","status":"0","remark":null,"trans_amt":"10500","plan_time":"2328","last_id":"582"}]},{"list":[{"id":"585","reg_id":"4413","dtl_id":"5074","trans_type":"72","order_id":null,"card_id":"6225768771872395","merch_id":"15999519806","repay_date":"20171102","insert_time":"2017-10-31","update_time":"2017-10-31","status":"0","remark":null,"trans_amt":"13940","plan_time":"1543","last_id":"584"},{"id":"584","reg_id":"4413","dtl_id":"5074","trans_type":"71","order_id":null,"card_id":"6225768771872395","merch_id":"15999519806","repay_date":"20171102","insert_time":"2017-10-31","update_time":"2017-10-31","status":"0","remark":null,"trans_amt":"52880","plan_time":"1003","last_id":"583"},{"id":"586","reg_id":"4413","dtl_id":"5074","trans_type":"72","order_id":null,"card_id":"6225768771872395","merch_id":"15999519806","repay_date":"20171102","insert_time":"2017-10-31","update_time":"2017-10-31","status":"0","remark":null,"trans_amt":"13940","plan_time":"1753","last_id":"585"},{"id":"587","reg_id":"4413","dtl_id":"5074","trans_type":"72","order_id":null,"card_id":"6225768771872395","merch_id":"15999519806","repay_date":"20171102","insert_time":"2017-10-31","update_time":"2017-10-31","status":"0","remark":null,"trans_amt":"25120","plan_time":"2026","last_id":"586"}]},{"list":[{"id":"588","reg_id":"4413","dtl_id":"5075","trans_type":"71","order_id":null,"card_id":"6225768771872395","merch_id":"15999519806","repay_date":"20171104","insert_time":"2017-10-31","update_time":"2017-10-31","status":"0","remark":null,"trans_amt":"41480","plan_time":"0907","last_id":"587"},{"id":"589","reg_id":"4413","dtl_id":"5075","trans_type":"72","order_id":null,"card_id":"6225768771872395","merch_id":"15999519806","repay_date":"20171104","insert_time":"2017-10-31","update_time":"2017-10-31","status":"0","remark":null,"trans_amt":"11560","plan_time":"1559","last_id":"588"},{"id":"590","reg_id":"4413","dtl_id":"5075","trans_type":"72","order_id":null,"card_id":"6225768771872395","merch_id":"15999519806","repay_date":"20171104","insert_time":"2017-10-31","update_time":"2017-10-31","status":"0","remark":null,"trans_amt":"15760","plan_time":"1659","last_id":"589"},{"id":"591","reg_id":"4413","dtl_id":"5075","trans_type":"72","order_id":null,"card_id":"6225768771872395","merch_id":"15999519806","repay_date":"20171104","insert_time":"2017-10-31","update_time":"2017-10-31","status":"0","remark":null,"trans_amt":"13680","plan_time":"2253","last_id":"590"}]}]
         * merchId : 15999519806
         * orderId : 20171031100859000000004413
         */

        private String repaymentAmount;
        private String depositAmount;
        private String cardId;
        private String status;
        private String feeAmount;
        private String merchId;
        private String orderId;
        private String date;
        private String times;
        private String count;
        private String first_fee_amt;
        private String detailDays;
        private String startDay;
        private String endDay;
        private String firstTransAmt;
        private String regId;
        private String remark;
        private List<DetailBean> Detail;
        private List<DetailListBean> DetailList;

        public String getRepaymentAmount() {
            return AppTools.formatF2Y(repaymentAmount);
        }

        public void setRepaymentAmount(String repaymentAmount) {
            this.repaymentAmount = repaymentAmount;
        }

        public String getDepositAmount() {
            return AppTools.formatF2Y(depositAmount);
        }

        public void setDepositAmount(String depositAmount) {
            this.depositAmount = depositAmount;
        }

        public String getCardId() {
            return cardId;
        }

        public void setCardId(String cardId) {
            this.cardId = cardId;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getFeeAmount() {
            return AppTools.formatF2Y(feeAmount);
        }

        public void setFeeAmount(String feeAmount) {
            this.feeAmount = feeAmount;
        }

        public String getMerchId() {
            return merchId;
        }

        public void setMerchId(String merchId) {
            this.merchId = merchId;
        }

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public List<DetailBean> getDetail() {
            return Detail;
        }

        public void setDetail(List<DetailBean> Detail) {
            this.Detail = Detail;
        }

        public List<DetailListBean> getDetailList() {
            return DetailList;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getTimes() {
            return times;
        }

        public void setTimes(String times) {
            this.times = times;
        }

        public String getCount() {
            return count;
        }

        public String getFirst_fee_amt() {
            return AppTools.formatF2Y(first_fee_amt);
        }

        public void setFirst_fee_amt(String first_fee_amt) {
            this.first_fee_amt = first_fee_amt;
        }

        public void setCount(String count) {
            this.count = count;
        }

        public String getDetailDays() {
            return detailDays;
        }

        public String getStartDay() {
            return startDay;
        }

        public void setStartDay(String startDay) {
            this.startDay = startDay;
        }

        public String getEndDay() {
            return endDay;
        }

        public void setEndDay(String endDay) {
            this.endDay = endDay;
        }

        public void setDetailDays(String detailDays) {
            this.detailDays = detailDays;
        }

        public void setDetailList(List<DetailListBean> DetailList) {
            this.DetailList = DetailList;
        }

        public String getFirstTransAmt() {
            return AppTools.formatF2Y(firstTransAmt);
        }

        public void setFirstTransAmt(String firstTransAmt) {
            this.firstTransAmt = firstTransAmt;
        }

        public String getRegId() {
            return regId;
        }

        public void setRegId(String regId) {
            this.regId = regId;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public static class DetailBean implements Serializable {
            /**
             * id : 4519
             * regId : 4259
             * orderId : 20170905100124000000004259
             * cardId : 6225768771872395
             * merchId : 15999519806
             * repayDate : 20170903
             * returnAmt : 2000
             * returnStatus : 00
             * returnTime : null
             * transAmt : 1720
             * transStatus : 00
             * transTime : null
             * insertTime : 1504576884000
             * updateTime : 1504576884000
             */

            private String id;
            private String regId;
            private String orderId;
            private String cardId;
            private String merchId;
            private String repayDate;
            private String returnAmt;
            private String returnStatus;
            private String returnTime;
            private String transAmt;
            private String transStatus;
            private String transTime;
            private String insertTime;
            private String updateTime;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getRegId() {
                return regId;
            }

            public void setRegId(String regId) {
                this.regId = regId;
            }

            public String getOrderId() {
                return orderId;
            }

            public void setOrderId(String orderId) {
                this.orderId = orderId;
            }

            public String getCardId() {
                return cardId;
            }

            public void setCardId(String cardId) {
                this.cardId = cardId;
            }

            public String getMerchId() {
                return merchId;
            }

            public void setMerchId(String merchId) {
                this.merchId = merchId;
            }

            public String getRepayDate() {
                return repayDate;
            }

            public void setRepayDate(String repayDate) {
                this.repayDate = repayDate;
            }

            public String getReturnAmt() {
                return AppTools.formatF2Y(returnAmt);
            }

            public void setReturnAmt(String returnAmt) {
                this.returnAmt = returnAmt;
            }

            public String getReturnStatus() {
                return returnStatus;
            }

            public void setReturnStatus(String returnStatus) {
                this.returnStatus = returnStatus;
            }

            public Object getReturnTime() {
                return returnTime;
            }

            public void setReturnTime(String returnTime) {
                this.returnTime = returnTime;
            }

            public String getTransAmt() {
                return AppTools.formatF2Y(transAmt);
            }

            public void setTransAmt(String transAmt) {
                this.transAmt = transAmt;
            }

            public String getTransStatus() {
                return transStatus;
            }

            public void setTransStatus(String transStatus) {
                this.transStatus = transStatus;
            }

            public Object getTransTime() {
                return transTime;
            }

            public void setTransTime(String transTime) {
                this.transTime = transTime;
            }

            public String getInsertTime() {
                return insertTime;
            }

            public void setInsertTime(String insertTime) {
                this.insertTime = insertTime;
            }

            public String getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(String updateTime) {
                this.updateTime = updateTime;
            }
        }

        public static class DetailListBean implements Serializable {
            private List<ListBean> list;
            private String repayType;
            private String repayTime;
            private String repayAmt;
            private String repayStatus;

            public String getRepayType() {
                return repayType;
            }

            public void setRepayType(String repayType) {
                this.repayType = repayType;
            }

            public String getRepayTime() {
                return repayTime;
            }

            public void setRepayTime(String repayTime) {
                this.repayTime = repayTime;
            }

            public String getRepayAmt() {
                return AppTools.formatF2Y(repayAmt);
            }

            public void setRepayAmt(String repayAmt) {
                this.repayAmt = repayAmt;
            }

            public String getRepayStatus() {
                return repayStatus;
            }

            public void setRepayStatus(String repayStatus) {
                this.repayStatus = repayStatus;
            }

            public List<ListBean> getList() {
                return list;
            }

            public void setList(List<ListBean> list) {
                this.list = list;
            }

            public static class ListBean implements Serializable {
                /**
                 * id : 582
                 * reg_id : 4413
                 * dtl_id : 5073
                 * trans_type : 72
                 * order_id : null
                 * card_id : 6225768771872395
                 * merch_id : 15999519806
                 * repay_date : 20171101
                 * insert_time : 2017-10-31
                 * update_time : 2017-10-31
                 * status : 0
                 * remark : null
                 * trans_amt : 10500
                 * plan_time : 1557
                 * last_id : 581
                 */

                private String id;
                private String regId;
                private String dtlId;
                private String transType;
                private String orderId;
                private String cardId;
                private String merchId;
                private String repayDate;
                private String insertTime;
                private String updateTime;
                private String status;
                private String remark;
                private String transAmt;
                private String planTime;
                private String lastId;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getRegId() {
                    return regId;
                }

                public void setRegId(String regId) {
                    this.regId = regId;
                }

                public String getDtlId() {
                    return dtlId;
                }

                public void setDtlId(String dtlId) {
                    this.dtlId = dtlId;
                }

                public String getTransType() {
                    return transType;
                }

                public void setTransType(String transType) {
                    this.transType = transType;
                }

                public String getOrderId() {
                    return orderId;
                }

                public void setOrderId(String orderId) {
                    this.orderId = orderId;
                }

                public String getCardId() {
                    return cardId;
                }

                public void setCardId(String cardId) {
                    this.cardId = cardId;
                }

                public String getMerchId() {
                    return merchId;
                }

                public void setMerchId(String merchId) {
                    this.merchId = merchId;
                }

                public String getRepayDate() {
                    return repayDate;
                }

                public void setRepayDate(String repayDate) {
                    this.repayDate = repayDate;
                }

                public String getInsertTime() {
                    return insertTime;
                }

                public void setInsertTime(String insertTime) {
                    this.insertTime = insertTime;
                }

                public String getUpdateTime() {
                    return updateTime;
                }

                public void setUpdateTime(String updateTime) {
                    this.updateTime = updateTime;
                }

                public String getStatus() {
                    return status;
                }

                public void setStatus(String status) {
                    this.status = status;
                }

                public String getRemark() {
                    return remark;
                }

                public void setRemark(String remark) {
                    this.remark = remark;
                }

                public String getTransAmt() {
                    return AppTools.formatF2Y(transAmt);
                }

                public void setTransAmt(String transAmt) {
                    this.transAmt = transAmt;
                }

                public String getPlanTime() {
                    return planTime;
                }

                public void setPlanTime(String planTime) {
                    this.planTime = planTime;
                }

                public String getLastId() {
                    return lastId;
                }

                public void setLastId(String lastId) {
                    this.lastId = lastId;
                }
            }
        }
    }
}
