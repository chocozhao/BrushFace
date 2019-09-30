package com.yzf.king.model.servicesmodels;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yzf.king.kit.AppTools;
import com.yzf.king.model.BaseModel;

import java.io.Serializable;
import java.util.List;

/**
 * ClaseName：GetCreateResult
 * Description：
 * Author：chocozhao
 * QQ: 1027313530
 * Createtime：2019/5/8 15:31
 * Modified By：
 * Fixtime：2019/5/8 15:31
 * FixDescription：
 **/

public class GetCreateResult extends BaseModel {


    /**
     * code : 200
     * data : {"cardManagerStatus":"00","count":"28","depositAmount":"0","detail":"[{\"returnDate\":\"20190508\",\"plan\":[{\"transAmt\":\"71000\",\"returnAmt\":\"71000\"},{\"transAmt\":\"58000\",\"returnAmt\":\"58000\"},{\"transAmt\":\"52500\",\"returnAmt\":\"52500\"},{\"transAmt\":\"58500\",\"returnAmt\":\"58500\"}]},{\"returnDate\":\"20190509\",\"plan\":[{\"transAmt\":\"71800\",\"returnAmt\":\"71800\"},{\"transAmt\":\"55200\",\"returnAmt\":\"55200\"},{\"transAmt\":\"59200\",\"returnAmt\":\"59200\"},{\"transAmt\":\"53800\",\"returnAmt\":\"53800\"}]},{\"returnDate\":\"20190510\",\"plan\":[{\"transAmt\":\"70200\",\"returnAmt\":\"70200\"},{\"transAmt\":\"51500\",\"returnAmt\":\"51500\"},{\"transAmt\":\"52700\",\"returnAmt\":\"52700\"},{\"transAmt\":\"65600\",\"returnAmt\":\"65600\"}]},{\"returnDate\":\"20190511\",\"plan\":[{\"transAmt\":\"36500\",\"returnAmt\":\"36500\"},{\"transAmt\":\"63500\",\"returnAmt\":\"63500\"}]}]","detailDays":"08-11","endDate":"20190511","feeAmount":"12000","orderId":"20190508000100361659","respCode":"0000","respDesc":"规划生成成功","startDate":"20190508","times":"1"}
     * message : 新增成功
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

    public static class DataBean implements Serializable {
        /**
         * cardManagerStatus : 00
         * count : 28
         * depositAmount : 0
         * detail : [{"returnDate":"20190508","plan":[{"transAmt":"71000","returnAmt":"71000"},{"transAmt":"58000","returnAmt":"58000"},{"transAmt":"52500","returnAmt":"52500"},{"transAmt":"58500","returnAmt":"58500"}]},{"returnDate":"20190509","plan":[{"transAmt":"71800","returnAmt":"71800"},{"transAmt":"55200","returnAmt":"55200"},{"transAmt":"59200","returnAmt":"59200"},{"transAmt":"53800","returnAmt":"53800"}]},{"returnDate":"20190510","plan":[{"transAmt":"70200","returnAmt":"70200"},{"transAmt":"51500","returnAmt":"51500"},{"transAmt":"52700","returnAmt":"52700"},{"transAmt":"65600","returnAmt":"65600"}]},{"returnDate":"20190511","plan":[{"transAmt":"36500","returnAmt":"36500"},{"transAmt":"63500","returnAmt":"63500"}]}]
         * detailDays : 08-11
         * endDate : 20190511
         * feeAmount : 12000
         * orderId : 20190508000100361659
         * respCode : 0000
         * respDesc : 规划生成成功
         * startDate : 20190508
         * times : 1
         */

        private String cardManagerStatus;
        private String count;
        private String depositAmount;
        private String detail;
        private String detailDays;
        private String endDate;
        private String feeAmount;
        private String orderId;
        private String respCode;
        private String respDesc;
        private String startDate;
        private String times;
        private List<DeatailBean> deatailBeanList;

        /**
         * returnDate : 20190508
         * plan : [{"transAmt":"71000","returnAmt":"71000"},{"transAmt":"58000","returnAmt":"58000"},{"transAmt":"52500","returnAmt":"52500"},{"transAmt":"58500","returnAmt":"58500"}]
         */


        public String getCardManagerStatus() {
            return cardManagerStatus;
        }

        public void setCardManagerStatus(String cardManagerStatus) {
            this.cardManagerStatus = cardManagerStatus;
        }

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
        }

        public String getDepositAmount() {
            return depositAmount;
        }

        public void setDepositAmount(String depositAmount) {
            this.depositAmount = depositAmount;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        public String getDetailDays() {
            return detailDays;
        }

        public void setDetailDays(String detailDays) {
            this.detailDays = detailDays;
        }

        public String getEndDate() {
            return endDate;
        }

        public void setEndDate(String endDate) {
            this.endDate = endDate;
        }

        public String getFeeAmount() {
            return feeAmount;
        }

        public void setFeeAmount(String feeAmount) {
            this.feeAmount = feeAmount;
        }

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public String getRespCode() {
            return respCode;
        }

        public void setRespCode(String respCode) {
            this.respCode = respCode;
        }

        public String getRespDesc() {
            return respDesc;
        }

        public void setRespDesc(String respDesc) {
            this.respDesc = respDesc;
        }

        public String getStartDate() {
            return startDate;
        }

        public void setStartDate(String startDate) {
            this.startDate = startDate;
        }

        public String getTimes() {
            return times;
        }

        public void setTimes(String times) {
            this.times = times;
        }

        public List<DeatailBean> getDeatailBeanList() {
            Gson gson = new Gson();
            deatailBeanList = gson.fromJson(getDetail(),
                    new TypeToken<List<DeatailBean>>() {
                    }.getType());
            return deatailBeanList;
        }

        public void setDeatailBeanList(List<DeatailBean> deatailBeanList) {
            this.deatailBeanList = deatailBeanList;
        }


        public static class DeatailBean {
            private String returnDate;
            private List<PlanBean> plan;

            public String getReturnDate() {
                return returnDate;
            }

            public void setReturnDate(String returnDate) {
                this.returnDate = returnDate;
            }

            public List<PlanBean> getPlan() {
                return plan;
            }

            public void setPlan(List<PlanBean> plan) {
                this.plan = plan;
            }
        }

        public static class PlanBean {
            /**
             * transAmt : 71000
             * returnAmt : 71000
             */

            private String transAmt;
            private String returnAmt;

            public String getTransAmt() {
                return AppTools.formatF2Y(transAmt);
            }

            public void setTransAmt(String transAmt) {
                this.transAmt = transAmt;
            }

            public String getReturnAmt() {
                return AppTools.formatF2Y(returnAmt);
            }

            public void setReturnAmt(String returnAmt) {
                this.returnAmt = returnAmt;
            }
        }
    }
}
