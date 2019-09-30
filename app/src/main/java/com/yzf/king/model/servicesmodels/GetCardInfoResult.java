package com.yzf.king.model.servicesmodels;

import com.yzf.king.kit.AppTools;
import com.yzf.king.model.BaseModel;

import java.io.Serializable;
import java.util.List;

public class GetCardInfoResult extends BaseModel {


    /**
     * code : 200
     * data : [{"availAmt":0,"bankAbbr":"SPABANK","bankBin":"356869","bankCode":"03070000","bankName":"平安银行","billDate":"01","billDays":"20","billStatus":0,"cardId":"3568690001380498","cardType":"00","currBillAmt":0,"cvv":"551","days":"9","idNo":"452122198604223017","limitAmt":0,"merchId":"10000027","name":"农本勇","phone":"18620028795","repalyDate":0,"repaymentDate":"20","status":1,"validDate":"1020","version":0},{"availAmt":0,"bankAbbr":"COMM","bankBin":"622252","bankCode":"03010000","bankName":"交通银行","billDate":"02","billDays":"21","billStatus":0,"cardId":"6222520718227537","cardType":"00","currBillAmt":0,"cvv":"180","days":"14","idNo":"452122198604223017","limitAmt":0,"merchId":"10000027","name":"农本勇","phone":"18620028795","repalyDate":0,"repaymentDate":"25","status":1,"validDate":"0821","version":0},{"availAmt":0,"bankAbbr":"HXBANK","bankBin":"622637","bankCode":"03040000","bankName":"华夏银行","billDate":"21","billDays":"10","billStatus":0,"cardId":"6226370024664718","cardType":"00","currBillAmt":0,"cvv":"775","days":"7","idNo":"452122198604223017","limitAmt":0,"merchId":"10000027","name":"农本勇","phone":"18620028795","repalyDate":0,"repaymentDate":"18","status":1,"validDate":"1022","version":0},{"availAmt":0,"bankAbbr":"GDBANK","bankBin":"625050","bankCode":"04135810","bankName":"广州银行股份有限公司","billDate":"01","billDays":"20","billStatus":0,"cardId":"6250502004418405","cardType":"00","currBillAmt":0,"cvv":"553","days":"17","idNo":"452122198604223017","limitAmt":20000,"merchId":"10000027","name":"农本勇","phone":"18620028795","repalyDate":0,"repaymentDate":"28","status":1,"validDate":"1026","version":0},{"availAmt":0,"bankAbbr":"CGB","bankBin":"625809","bankCode":"03060000","bankName":"广发银行股份有限公司","billDate":"01","billDays":"20","billStatus":0,"cardId":"6258091690729822","cardType":"00","currBillAmt":0,"cvv":"303","days":"17","idNo":"452122198604223017","limitAmt":0,"merchId":"10000027","name":"农本勇","phone":"18620028795","repalyDate":0,"repaymentDate":"28","status":1,"validDate":"1022","version":0},{"availAmt":0,"bankAbbr":"CGB","bankBin":"625810","bankCode":"03060000","bankName":"���发银行股份有限公司","billDate":"02","billDays":"21","billStatus":0,"cardId":"6258101679270019","cardType":"00","currBillAmt":0,"cvv":"984","days":"14","idNo":"452122198604223017","limitAmt":0,"merchId":"10000027","name":"农本勇","phone":"18620028795","repalyDate":0,"repaymentDate":"25","status":1,"validDate":"1123","version":0}]
     * message : 获取成功
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
         * availAmt : 0
         * bankAbbr : SPABANK
         * bankBin : 356869
         * bankCode : 03070000
         * bankName : 平安银行
         * billDate : 01
         * billDays : 20
         * billStatus : 0
         * cardId : 3568690001380498
         * cardType : 00
         * currBillAmt : 0
         * cvv : 551
         * days : 9
         * idNo : 452122198604223017
         * limitAmt : 0
         * merchId : 10000027
         * name : 农本勇
         * phone : 18620028795
         * repalyDate : 0
         * repaymentDate : 20
         * status : 1
         * validDate : 1020
         * version : 0
         */

        private int availAmt;
        private String bankAbbr;
        private String bankBin;
        private String bankCode;
        private String bankName;
        private String billDate;
        private String billDays;
        private int billStatus;
        private String cardId;
        private String cardType;
        private String currBillAmt;
        private String cvv;
        private String days;
        private String idNo;
        private int limitAmt;
        private String merchId;
        private String name;
        private String phone;
        private int repalyDate;
        private String repaymentDate;
        private int status;
        private String validDate;
        private int version;

        public int getAvailAmt() {
            return availAmt;
        }

        public void setAvailAmt(int availAmt) {
            this.availAmt = availAmt;
        }

        public String getBankAbbr() {
            return bankAbbr;
        }

        public void setBankAbbr(String bankAbbr) {
            this.bankAbbr = bankAbbr;
        }

        public String getBankBin() {
            return bankBin;
        }

        public void setBankBin(String bankBin) {
            this.bankBin = bankBin;
        }

        public String getBankCode() {
            return bankCode;
        }

        public void setBankCode(String bankCode) {
            this.bankCode = bankCode;
        }

        public String getBankName() {
            return bankName;
        }

        public void setBankName(String bankName) {
            this.bankName = bankName;
        }

        public String getBillDate() {
            return billDate;
        }

        public void setBillDate(String billDate) {
            this.billDate = billDate;
        }

        public String getBillDays() {
            return billDays;
        }

        public void setBillDays(String billDays) {
            this.billDays = billDays;
        }

        public int getBillStatus() {
            return billStatus;
        }

        public void setBillStatus(int billStatus) {
            this.billStatus = billStatus;
        }

        public String getCardId() {
            return cardId;
        }

        public void setCardId(String cardId) {
            this.cardId = cardId;
        }

        public String getCardType() {
            return cardType;
        }

        public void setCardType(String cardType) {
            this.cardType = cardType;
        }

        public String getCurrBillAmt() {
            if (currBillAmt != null) {
                return AppTools.formatF2Y(currBillAmt);
            }
            return currBillAmt;
        }

        public void setCurrBillAmt(String currBillAmt) {
            this.currBillAmt = currBillAmt;
        }

        public String getCvv() {
            return cvv;
        }

        public void setCvv(String cvv) {
            this.cvv = cvv;
        }

        public String getDays() {
            return days;
        }

        public void setDays(String days) {
            this.days = days;
        }

        public String getIdNo() {
            return idNo;
        }

        public void setIdNo(String idNo) {
            this.idNo = idNo;
        }

        public int getLimitAmt() {
            return limitAmt;
        }

        public void setLimitAmt(int limitAmt) {
            this.limitAmt = limitAmt;
        }

        public String getMerchId() {
            return merchId;
        }

        public void setMerchId(String merchId) {
            this.merchId = merchId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public int getRepalyDate() {
            return repalyDate;
        }

        public void setRepalyDate(int repalyDate) {
            this.repalyDate = repalyDate;
        }

        public String getRepaymentDate() {
            return repaymentDate;
        }

        public void setRepaymentDate(String repaymentDate) {
            this.repaymentDate = repaymentDate;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getValidDate() {
            return validDate;
        }

        public void setValidDate(String validDate) {
            this.validDate = validDate;
        }

        public int getVersion() {
            return version;
        }

        public void setVersion(int version) {
            this.version = version;
        }
    }
}