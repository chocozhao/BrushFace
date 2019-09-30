package com.yzf.king.model.servicesmodels;

import com.yzf.king.kit.AppTools;
import com.yzf.king.model.BaseModel;

import java.io.Serializable;

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

public class AddRepaymentResults extends BaseModel {


    /**
     * code : 200
     * data : {"depositAmount":"63000","respCode":"0000","count":"2","times":"1","status":"00","first_fee_amt":"391","feeAmount":"703","detailDays":"26-26","respDesc":"新增规划成功","orderId":"20180126112837000000004615","alvbAmount":"73025"}
     * message : 新增规划成功
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
         * depositAmount : 63000
         * respCode : 0000
         * count : 2
         * times : 1
         * status : 00
         * first_fee_amt : 391
         * feeAmount : 703
         * detailDays : 26-26
         * respDesc : 新增规划成功
         * orderId : 20180126112837000000004615
         * alvbAmount : 73025
         */
        private String totalAmount;
        private String cardId;
        private String depositAmount;
        private String respCode;
        private String count;
        private String times;
        private String status;
        private String first_fee_amt;
        private String feeAmount;
        private String detailDays;
        private String respDesc;
        private String orderId;
        private String alvbAmount;
        private String firstTransAmt;

        public String getTotalAmount() {
            return AppTools.formatF2Y(totalAmount);
        }

        public void setTotalAmount(String totalAmount) {
            this.totalAmount = totalAmount;
        }

        public String getCardId() {
            return cardId;
        }

        public void setCardId(String cardId) {
            this.cardId = cardId;
        }

        public String getDepositAmount() {
            return AppTools.formatF2Y(depositAmount);
        }

        public void setDepositAmount(String depositAmount) {
            this.depositAmount = depositAmount;
        }

        public String getRespCode() {
            return respCode;
        }

        public void setRespCode(String respCode) {
            this.respCode = respCode;
        }

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
        }

        public String getTimes() {
            return times;
        }

        public void setTimes(String times) {
            this.times = times;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getFirst_fee_amt() {
            return AppTools.formatF2Y(first_fee_amt);
        }

        public void setFirst_fee_amt(String first_fee_amt) {
            this.first_fee_amt = first_fee_amt;
        }

        public String getFeeAmount() {
            return AppTools.formatF2Y(feeAmount);
        }

        public void setFeeAmount(String feeAmount) {
            this.feeAmount = feeAmount;
        }

        public String getDetailDays() {
            return detailDays;
        }

        public void setDetailDays(String detailDays) {
            this.detailDays = detailDays;
        }

        public String getRespDesc() {
            return respDesc;
        }

        public void setRespDesc(String respDesc) {
            this.respDesc = respDesc;
        }

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public String getAlvbAmount() {
            return AppTools.formatF2Y(alvbAmount);
        }

        public void setAlvbAmount(String alvbAmount) {
            this.alvbAmount = alvbAmount;
        }

        public String getFirstTransAmt() {
            return AppTools.formatF2Y(firstTransAmt);
        }

        public void setFirstTransAmt(String firstTransAmt) {
            this.firstTransAmt = firstTransAmt;
        }
    }
}
