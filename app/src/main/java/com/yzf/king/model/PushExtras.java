package com.yzf.king.model;

/**
 * ClaseName：PushExtras
 * Description：
 * Author：JensenWei
 * QQ: 2188307188
 * Createtime：2018/5/19 15:39
 * Modified By：
 * Fixtime：2018/5/19 15:39
 * FixDescription：
 */

public class PushExtras {
    /**
     * extras : {"msgContent":"消息内容","msgType":"00","time":"20180519160750"}
     */

    private ExtrasBean extras;

    public ExtrasBean getExtras() {
        return extras;
    }

    public void setExtras(ExtrasBean extras) {
        this.extras = extras;
    }

    public static class ExtrasBean {
        /**
         * msgContent : 消息内容
         * msgType : 00
         * time : 20180519160750
         */

        private String msgType;
        private String time;
        private String msgContent;
        private String msgTitle;
        private String orderId;
        private String orderAmt;
        private String orderTime;
        private String orderStatus;
        private String orderType;
        private String orderDesc;

        public String getMsgType() {
            return msgType;
        }

        public void setMsgType(String msgType) {
            this.msgType = msgType;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getMsgContent() {
            return msgContent;
        }

        public void setMsgContent(String msgContent) {
            this.msgContent = msgContent;
        }

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public String getOrderAmt() {
            return orderAmt;
        }

        public void setOrderAmt(String orderAmt) {
            this.orderAmt = orderAmt;
        }

        public String getOrderTime() {
            return orderTime;
        }

        public void setOrderTime(String orderTime) {
            this.orderTime = orderTime;
        }

        public String getOrderStatus() {
            return orderStatus;
        }

        public void setOrderStatus(String orderStatus) {
            this.orderStatus = orderStatus;
        }

        public String getOrderType() {
            return orderType;
        }

        public void setOrderType(String orderType) {
            this.orderType = orderType;
        }

        public String getOrderDesc() {
            return orderDesc;
        }

        public void setOrderDesc(String orderDesc) {
            this.orderDesc = orderDesc;
        }

        public String getMsgTitle() {
            return msgTitle;
        }

        public void setMsgTitle(String msgTitle) {
            this.msgTitle = msgTitle;
        }
    }
}
