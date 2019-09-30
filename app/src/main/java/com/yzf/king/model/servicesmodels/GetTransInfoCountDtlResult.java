package com.yzf.king.model.servicesmodels;


import com.yzf.king.model.BaseModel;

import java.io.Serializable;
import java.util.List;

/**
 * ClaseName：GetTransInfoCountDtlResult
 * Description：
 * Author：chocozhao
 * QQ: 1027313530
 * Createtime：2019/5/15 19:33
 * Modified By：
 * Fixtime：2019/5/15 19:33
 * FixDescription：
 **/

public class GetTransInfoCountDtlResult extends BaseModel {


    /**
     * code : 200
     * data : [{"branchCode":"F0000001","chnlCode":"70980002","insertTime":1557371667000,"merchFee":0,"merchId":"10000027","merchSettAmt":120,"orderId":"902019050911142704926121","procStatus":"01","respCode":"0000","respDesc":"交易成功","servicesName":"额度还款服务费","settDate":"20190509","transAmt":120,"transSn":"11893793","transType":"90"},{"branchCode":"F0000001","chnlCode":"70940000","insertTime":1557306303000,"merchFee":1.65,"merchId":"10000027","merchSettAmt":98.35,"orderId":"iOS201905081705032213","procStatus":"01","respCode":"0000","respDesc":"交易成功","servicesName":"极速收款","settDate":"20190508","transAmt":100,"transSn":"11893783","transType":"78"},{"branchCode":"F0000001","chnlCode":"70980009","insertTime":1557282043000,"merchFee":0,"merchId":"10000027","merchSettAmt":45.15,"orderId":"2019050810204304926103","procStatus":"01","respCode":"0000","respDesc":"交易成功","servicesName":"还款服务费退款","settDate":"20190508","transAmt":45.15,"transSn":"11893780","transType":"97"},{"branchCode":"F0000001","chnlCode":"88085810","insertTime":1557230035000,"merchFee":0.05,"merchId":"10000092","merchSettAmt":0,"orderId":"15572300354055990","procStatus":"01","respCode":"0000","respDesc":"交易支付成功","servicesName":"商户入驻","settDate":"20190507","transAmt":0.05,"transSn":"11893772","transType":"46"},{"branchCode":"F0000001","chnlCode":"88085810","insertTime":1557227466000,"merchFee":0.05,"merchId":"10000088","merchSettAmt":0,"orderId":"15572274664250116","procStatus":"01","respCode":"0000","respDesc":"交易支付成功","servicesName":"商户入驻","settDate":"20190507","transAmt":0.05,"transSn":"11893768","transType":"46"},{"branchCode":"F0000001","chnlCode":"70980002","insertTime":1557227415000,"merchFee":0,"merchId":"10000027","merchSettAmt":715,"orderId":"2019050719101504926089","procStatus":"01","respCode":"0000","respDesc":"交易成功","servicesName":"额度还款","settDate":"20190507","transAmt":715,"transSn":"11893767","transType":"91"},{"branchCode":"F0000001","chnlCode":"88085810","insertTime":1557226314000,"merchFee":0.05,"merchId":"10000086","merchSettAmt":0,"orderId":"15572263138445120","procStatus":"01","respCode":"0000","respDesc":"交易支付成功","servicesName":"商户入驻","settDate":"20190507","transAmt":0.05,"transSn":"11893765","transType":"46"},{"branchCode":"F0000001","chnlCode":"70980002","insertTime":1557144700000,"merchFee":0,"merchId":"10000027","merchSettAmt":60,"orderId":"902019050620113904926036","procStatus":"01","respCode":"0000","respDesc":"交易成功","servicesName":"额度还款服务费","settDate":"20190506","transAmt":60,"transSn":"11893694","transType":"90"},{"branchCode":"F0000001","chnlCode":"84005810","insertTime":1555567060000,"merchFee":2.23,"merchId":"10000027","merchSettAmt":39.23,"orderId":"Android201904181357406818","procStatus":"01","respCode":"0000","respDesc":"交易成功","servicesName":"商户提现","settDate":"20190418","transAmt":37,"transSn":"11893549","transType":"02"},{"branchCode":"F0000001","chnlCode":"70940000","insertTime":1555555775000,"merchFee":1.07,"merchId":"10000027","merchSettAmt":8.93,"orderId":"iOS201904181049355760","procStatus":"01","respCode":"0000","respDesc":"交易成功","servicesName":"极速收款","settDate":"20190418","transAmt":10,"transSn":"11893547","transType":"78"}]
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

    public static class DataBean  implements Serializable {
        /**
         * branchCode : F0000001
         * chnlCode : 70980002
         * insertTime : 1557371667000
         * merchFee : 0
         * merchId : 10000027
         * merchSettAmt : 120
         * orderId : 902019050911142704926121
         * procStatus : 01
         * respCode : 0000
         * respDesc : 交易成功
         * servicesName : 额度还款服务费
         * settDate : 20190509
         * transAmt : 120
         * transSn : 11893793
         * transType : 90
         */

        private String branchCode;
        private String chnlCode;
        private long insertTime;
        private String merchFee;
        private String merchId;
        private String merchSettAmt;
        private String orderId;
        private String procStatus;
        private String respCode;
        private String respDesc;
        private String servicesName;
        private String settDate;
        private String transAmt;
        private String transSn;
        private String transType;

        public String getBranchCode() {
            return branchCode;
        }

        public void setBranchCode(String branchCode) {
            this.branchCode = branchCode;
        }

        public String getChnlCode() {
            return chnlCode;
        }

        public void setChnlCode(String chnlCode) {
            this.chnlCode = chnlCode;
        }

        public long getInsertTime() {
            return insertTime;
        }

        public void setInsertTime(long insertTime) {
            this.insertTime = insertTime;
        }

        public String getMerchFee() {
            return merchFee;
        }

        public void setMerchFee(String merchFee) {
            this.merchFee = merchFee;
        }

        public String getMerchId() {
            return merchId;
        }

        public void setMerchId(String merchId) {
            this.merchId = merchId;
        }

        public String getMerchSettAmt() {
            return merchSettAmt;
        }

        public void setMerchSettAmt(String merchSettAmt) {
            this.merchSettAmt = merchSettAmt;
        }

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public String getProcStatus() {
            return procStatus;
        }

        public void setProcStatus(String procStatus) {
            this.procStatus = procStatus;
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

        public String getServicesName() {
            return servicesName;
        }

        public void setServicesName(String servicesName) {
            this.servicesName = servicesName;
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

        public String getTransSn() {
            return transSn;
        }

        public void setTransSn(String transSn) {
            this.transSn = transSn;
        }

        public String getTransType() {
            return transType;
        }

        public void setTransType(String transType) {
            this.transType = transType;
        }
    }
}
