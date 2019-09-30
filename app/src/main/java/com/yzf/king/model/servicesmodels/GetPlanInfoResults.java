package com.yzf.king.model.servicesmodels;

import com.yzf.king.kit.AppTools;
import com.yzf.king.model.BaseModel;

import java.io.Serializable;
import java.util.List;

/**
 * ClaseName：GetPlanInfoResults
 * Description：
 * Author：JensenWei
 * QQ: 2188307188
 * Createtime：2019/4/1 15:49
 * Modified By：
 * Fixtime：2019/4/1 15:49
 * FixDescription：
 */
public class GetPlanInfoResults extends BaseModel {

    /**
     * code : 200
     * data : [{"depositAmount":39000,"date":"20190316","bankCode":"03060000","repaymentAmount":100000,"orderId":"O20190316175122Ual8","startDay":"20190316","count":3,"bankName":"广发银行股份有限公司","firstTransAmt":39356,"merchId":"10000000","feeAmount":958,"times":1,"cardId":"6258101659596581","endDay":"20190318","regId":100361601,"detailDays":"16~18","status":"06"}]
     * message : 查询成功
     */

    private int code;
    private String message;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable {
        /**
         * depositAmount : 39000
         * date : 20190316
         * bankCode : 03060000
         * repaymentAmount : 100000
         * orderId : O20190316175122Ual8
         * startDay : 20190316
         * count : 3
         * bankName : 广发银行股份有限公司
         * firstTransAmt : 39356
         * merchId : 10000000
         * feeAmount : 958
         * times : 1
         * cardId : 6258101659596581
         * endDay : 20190318
         * regId : 100361601
         * detailDays : 16~18
         * status : 06
         */

        private String depositAmount;
        private String date;
        private String bankCode;
        private String repaymentAmount;
        private String orderId;
        private String startDay;
        private String count;
        private String bankName;
        private String firstTransAmt;
        private String merchId;
        private String feeAmount;
        private String times;
        private String cardId;
        private String endDay;
        private String regId;
        private String detailDays;
        private String status;
        private String remark;

        public String getDepositAmount() {
            return AppTools.formatF2Y(depositAmount);
        }

        public void setDepositAmount(String depositAmount) {
            this.depositAmount = depositAmount;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getBankCode() {
            return bankCode;
        }

        public void setBankCode(String bankCode) {
            this.bankCode = bankCode;
        }

        public String getRepaymentAmount() {
            return AppTools.formatF2Y(repaymentAmount);
        }

        public void setRepaymentAmount(String repaymentAmount) {
            this.repaymentAmount = repaymentAmount;
        }

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public String getStartDay() {
            return startDay;
        }

        public void setStartDay(String startDay) {
            this.startDay = startDay;
        }

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
        }

        public String getBankName() {
            return bankName;
        }

        public void setBankName(String bankName) {
            this.bankName = bankName;
        }

        public String getFirstTransAmt() {
            return AppTools.formatF2Y(firstTransAmt);
        }

        public void setFirstTransAmt(String firstTransAmt) {
            this.firstTransAmt = firstTransAmt;
        }

        public String getMerchId() {
            return merchId;
        }

        public void setMerchId(String merchId) {
            this.merchId = merchId;
        }

        public String getFeeAmount() {
            return AppTools.formatF2Y(feeAmount);
        }

        public void setFeeAmount(String feeAmount) {
            this.feeAmount = feeAmount;
        }

        public String getTimes() {
            return times;
        }

        public void setTimes(String times) {
            this.times = times;
        }

        public String getCardId() {
            return cardId;
        }

        public void setCardId(String cardId) {
            this.cardId = cardId;
        }

        public String getEndDay() {
            return endDay;
        }

        public void setEndDay(String endDay) {
            this.endDay = endDay;
        }

        public String getRegId() {
            return regId;
        }

        public void setRegId(String regId) {
            this.regId = regId;
        }

        public String getDetailDays() {
            return detailDays;
        }

        public void setDetailDays(String detailDays) {
            this.detailDays = detailDays;
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
    }
}
