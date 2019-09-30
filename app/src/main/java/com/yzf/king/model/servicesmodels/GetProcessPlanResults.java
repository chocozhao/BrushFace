package com.yzf.king.model.servicesmodels;

import com.yzf.king.kit.AppTools;
import com.yzf.king.model.BaseModel;

import java.io.Serializable;
import java.util.List;

/**
 * ClaseName：GetProcessPlanResults
 * Description：
 * Author：JensenWei
 * QQ: 2188307188
 * Createtime：2018/6/20 12:05
 * Modified By：
 * Fixtime：2018/6/20 12:05
 * FixDescription：
 */

public class GetProcessPlanResults extends BaseModel {

    /**
     * code : 200
     * data : [{"cardId":"6226230144325303","endDay":"20180618","count":17,"remark":"可用余额不足","status":"01","detailDays":"0606~0618","date":"20180606","depositAmount":99000,"repaymentAmount":1200000,"times":1,"feeAmount":5924,"startDay":"20180606","cardDesc":"民生银行","merchId":"15902020654","orderId":"20180605112342000000180566"},{"cardId":"6226880102702917","endDay":"20180524","count":15,"remark":null,"status":"02","detailDays":"0511~0524","date":"20180511","depositAmount":94000,"repaymentAmount":1000000,"times":1,"feeAmount":4978,"startDay":"20180511","cardDesc":"中信银行","merchId":"15902020654","orderId":"20180510113120000000152185"},{"cardId":"6252470043617304","endDay":"20180303","count":6,"remark":null,"status":"02","detailDays":"0301~0303","date":"20180301","depositAmount":21000,"repaymentAmount":100000,"times":1,"feeAmount":1292,"startDay":"20180301","cardDesc":"工商银行","merchId":"15902020654","orderId":"20180228171115000000072016"},{"cardId":"5201521650077051","endDay":"20180308","count":6,"remark":null,"status":"02","detailDays":"0301~0308","date":"20180301","depositAmount":20000,"repaymentAmount":100000,"times":1,"feeAmount":1292,"startDay":"20180301","cardDesc":"广发银行股份有限公司","merchId":"15902020654","orderId":"20180228165927000000072001"},{"cardId":"6225768755656335","endDay":"20180221","count":6,"remark":null,"status":"02","detailDays":"0214~0221","date":"20180214","depositAmount":21000,"repaymentAmount":100000,"times":1,"feeAmount":1291,"startDay":"20180214","cardDesc":"招商银行","merchId":"15902020654","orderId":"20180205162943000000055203"},{"cardId":"6225768755656335","endDay":"20180210","count":6,"remark":null,"status":"02","detailDays":"0206~0210","date":"20180206","depositAmount":21000,"repaymentAmount":100000,"times":1,"feeAmount":1292,"startDay":"20180206","cardDesc":"招商银行","merchId":"15902020654","orderId":"20180203164816000000053852"},{"cardId":"6225768755656335","endDay":"20180210","count":6,"remark":null,"status":"02","detailDays":"0206~0210","date":"20180206","depositAmount":23000,"repaymentAmount":100000,"times":1,"feeAmount":1293,"startDay":"20180206","cardDesc":"招商银行","merchId":"15902020654","orderId":"20180130201513000000051119"},{"cardId":"6250502008719709","endDay":"20180209","count":4,"remark":null,"status":"02","detailDays":"0130~0209","date":"20180130","depositAmount":33000,"repaymentAmount":100000,"times":1,"feeAmount":1089,"startDay":"20180130","cardDesc":"广州银行股份有限公司","merchId":"15902020654","orderId":"20180130141409000000050842"},{"cardId":"4581230714633684","endDay":"20171202","count":2,"remark":null,"status":"02","detailDays":"1201~1202","date":"20171201","depositAmount":52000,"repaymentAmount":100000,"times":1,"feeAmount":880,"startDay":"20171201","cardDesc":"交通银行","merchId":"15902020654","orderId":"20171130134428000000009286"},{"cardId":"6225768755656335","endDay":"20171202","count":2,"remark":null,"status":"02","detailDays":"1201~1202","date":"20171201","depositAmount":51000,"repaymentAmount":100000,"times":1,"feeAmount":880,"startDay":"20171201","cardDesc":"招商银行","merchId":"15902020654","orderId":"20171130134346000000009285"}]
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
         * cardId : 6226230144325303
         * endDay : 20180618
         * count : 17
         * remark : 可用余额不足
         * status : 01
         * detailDays : 0606~0618
         * date : 20180606
         * depositAmount : 99000
         * repaymentAmount : 1200000
         * times : 1
         * feeAmount : 5924
         * startDay : 20180606
         * cardDesc : 民生银行
         * merchId : 15902020654
         * orderId : 20180605112342000000180566
         */

        private String cardId;
        private String endDay;
        private int count;
        private String remark;
        private String status;
        private String detailDays;
        private String date;
        private String depositAmount;
        private String repaymentAmount;
        private String times;
        private String feeAmount;
        private String startDay;
        private String merchId;
        private String orderId;
        private String regId;
        private String bankName;
        private String bankCode;

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

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getDetailDays() {
            return detailDays;
        }

        public void setDetailDays(String detailDays) {
            this.detailDays = detailDays;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getDepositAmount() {
            return AppTools.formatF2Y(depositAmount);
        }

        public void setDepositAmount(String depositAmount) {
            this.depositAmount = depositAmount;
        }

        public String getRepaymentAmount() {
            return AppTools.formatF2Y(repaymentAmount);
        }

        public void setRepaymentAmount(String repaymentAmount) {
            this.repaymentAmount = repaymentAmount;
        }

        public String getTimes() {
            return times;
        }

        public void setTimes(String times) {
            this.times = times;
        }

        public String getFeeAmount() {
            return AppTools.formatF2Y(feeAmount);
        }

        public void setFeeAmount(String feeAmount) {
            this.feeAmount = feeAmount;
        }

        public String getStartDay() {
            return startDay;
        }

        public void setStartDay(String startDay) {
            this.startDay = startDay;
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

        public String getRegId() {
            return regId;
        }

        public void setRegId(String regId) {
            this.regId = regId;
        }

        public String getBankName() {
            return bankName;
        }

        public void setBankName(String bankName) {
            this.bankName = bankName;
        }

        public String getBankCode() {
            return bankCode;
        }

        public void setBankCode(String bankCode) {
            this.bankCode = bankCode;
        }
    }
}
