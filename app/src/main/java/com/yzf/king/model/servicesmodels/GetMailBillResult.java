package com.yzf.king.model.servicesmodels;


import android.os.Parcel;
import android.os.Parcelable;

import com.yzf.king.model.BaseModel;
import com.zaihuishou.expandablerecycleradapter.model.ExpandableListItem;

import java.util.List;

/**
 * ClaseName：GetMailBillResult
 * Description：
 * Author：chocozhao
 * QQ: 1027313530
 * Createtime：2019/6/11 16:43
 * Modified By：
 * Fixtime：2019/6/11 16:43
 * FixDescription：
 **/

public class GetMailBillResult extends BaseModel {


    /**
     * code : 200
     * data : [{"totalAmt":1.01,"month":"201904","days":"20190326-20190426","list":[{"add1":"201905","cardId":"6225768771847090","id":"145","insertTime":1560238557000,"merchId":"10000017","postDate":"*","remark":"消费？广州市天河区电脑商店（个体）杨景仁","transAmt":0.01,"transDate":"20190418","updateTime":1560238557000,"version":0},{"add1":"201905","cardId":"6225768771847090","id":"145","insertTime":1560238557000,"merchId":"10000017","postDate":"*","remark":"消费？广州市天河区电脑商店（个体）杨景仁","transAmt":1,"transDate":"20190416","updateTime":1560238557000,"version":0}]}]
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


    public static class DataBean {
        /**
         * totalAmt : 1.01
         * month : 201904
         * days : 20190326-20190426
         * list : [{"add1":"201905","cardId":"6225768771847090","id":"145","insertTime":1560238557000,"merchId":"10000017","postDate":"*","remark":"消费？广州市天河区电脑商店（个体）杨景仁","transAmt":0.01,"transDate":"20190418","updateTime":1560238557000,"version":0},{"add1":"201905","cardId":"6225768771847090","id":"145","insertTime":1560238557000,"merchId":"10000017","postDate":"*","remark":"消费？广州市天河区电脑商店（个体）杨景仁","transAmt":1,"transDate":"20190416","updateTime":1560238557000,"version":0}]
         */

        private double totalAmt;
        private String month;
        private String days;
        private List<ListBean> list;
        public boolean mExpanded = false;

        public double getTotalAmt() {
            return totalAmt;
        }

        public void setTotalAmt(double totalAmt) {
            this.totalAmt = totalAmt;
        }

        public String getMonth() {
            return month;
        }

        public void setMonth(String month) {
            this.month = month;
        }

        public String getDays() {
            return days;
        }

        public void setDays(String days) {
            this.days = days;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean  {
            /**
             * add1 : 201905
             * cardId : 6225768771847090
             * id : 145
             * insertTime : 1560238557000
             * merchId : 10000017
             * postDate : *
             * remark : 消费？广州市天河区电脑商店（个体）杨景仁
             * transAmt : 0.01
             * transDate : 20190418
             * updateTime : 1560238557000
             * version : 0
             */

            private String add1;
            private String cardId;
            private String id;
            private long insertTime;
            private String merchId;
            private String postDate;
            private String remark;
            private double transAmt;
            private String transDate;
            private long updateTime;
            private int version;

            public String getAdd1() {
                return add1;
            }

            public void setAdd1(String add1) {
                this.add1 = add1;
            }

            public String getCardId() {
                return cardId;
            }

            public void setCardId(String cardId) {
                this.cardId = cardId;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public long getInsertTime() {
                return insertTime;
            }

            public void setInsertTime(long insertTime) {
                this.insertTime = insertTime;
            }

            public String getMerchId() {
                return merchId;
            }

            public void setMerchId(String merchId) {
                this.merchId = merchId;
            }

            public String getPostDate() {
                return postDate;
            }

            public void setPostDate(String postDate) {
                this.postDate = postDate;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public double getTransAmt() {
                return transAmt;
            }

            public void setTransAmt(double transAmt) {
                this.transAmt = transAmt;
            }

            public String getTransDate() {
                return transDate;
            }

            public void setTransDate(String transDate) {
                this.transDate = transDate;
            }

            public long getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(long updateTime) {
                this.updateTime = updateTime;
            }

            public int getVersion() {
                return version;
            }

            public void setVersion(int version) {
                this.version = version;
            }

        }
    }
}
