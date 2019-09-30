package com.yzf.king.model.servicesmodels;

import com.yzf.king.model.BaseModel;

import java.io.Serializable;
import java.util.List;

/**
 * ClaseName：GetTeamInfoResult
 * Description：
 * Author：JensenWei
 * QQ: 2188307188
 * Createtime：2019/4/17 11:16
 * Modified By：
 * Fixtime：2019/4/17 11:16
 * FixDescription：
 */
public class GetTeamInfoResult extends BaseModel {


    /**
     * code : 200
     * data : {"dataList":[{"acctId":"A10000019","agentFlag":0,"agentLevel":1,"feeId":0,"insertTime":1563365523000,"merchId":"10000019","merchLevel":1,"merchName":"李世职","merchRelation":"1000000710000010100000111000001210000017","password":"be5cd76d621e9fdeb312b3fb317f0a3d","phone":"13926825308","registerTime":1563365523000,"relationLevel":6,"routeId":0,"shopFlag":0,"status":"03","updateTime":1563365523000,"version":0}],"count":1}
     * message : 查询成功
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
         * dataList : [{"acctId":"A10000019","agentFlag":0,"agentLevel":1,"feeId":0,"insertTime":1563365523000,"merchId":"10000019","merchLevel":1,"merchName":"李世职","merchRelation":"1000000710000010100000111000001210000017","password":"be5cd76d621e9fdeb312b3fb317f0a3d","phone":"13926825308","registerTime":1563365523000,"relationLevel":6,"routeId":0,"shopFlag":0,"status":"03","updateTime":1563365523000,"version":0}]
         * count : 1
         */

        private String count;
        private List<DataListBean> dataList;

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
        }

        public List<DataListBean> getDataList() {
            return dataList;
        }

        public void setDataList(List<DataListBean> dataList) {
            this.dataList = dataList;
        }

        public static class DataListBean implements Serializable{
            /**
             * acctId : A10000019
             * agentFlag : 0
             * agentLevel : 1
             * feeId : 0
             * insertTime : 1563365523000
             * merchId : 10000019
             * merchLevel : 1
             * merchName : 李世职
             * merchRelation : 1000000710000010100000111000001210000017
             * password : be5cd76d621e9fdeb312b3fb317f0a3d
             * phone : 13926825308
             * registerTime : 1563365523000
             * relationLevel : 6
             * routeId : 0
             * shopFlag : 0
             * status : 03
             * updateTime : 1563365523000
             * version : 0
             */

            private String acctId;
            private int agentFlag;
            private int agentLevel;
            private int feeId;
            private long insertTime;
            private String merchId;
            private int merchLevel;
            private String merchName;
            private String merchRelation;
            private String password;
            private String phone;
            private long registerTime;
            private int relationLevel;
            private int routeId;
            private int shopFlag;
            private String status;
            private long updateTime;
            private int version;

            public String getAcctId() {
                return acctId;
            }

            public void setAcctId(String acctId) {
                this.acctId = acctId;
            }

            public int getAgentFlag() {
                return agentFlag;
            }

            public void setAgentFlag(int agentFlag) {
                this.agentFlag = agentFlag;
            }

            public int getAgentLevel() {
                return agentLevel;
            }

            public void setAgentLevel(int agentLevel) {
                this.agentLevel = agentLevel;
            }

            public int getFeeId() {
                return feeId;
            }

            public void setFeeId(int feeId) {
                this.feeId = feeId;
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

            public int getMerchLevel() {
                return merchLevel;
            }

            public void setMerchLevel(int merchLevel) {
                this.merchLevel = merchLevel;
            }

            public String getMerchName() {
                return merchName;
            }

            public void setMerchName(String merchName) {
                this.merchName = merchName;
            }

            public String getMerchRelation() {
                return merchRelation;
            }

            public void setMerchRelation(String merchRelation) {
                this.merchRelation = merchRelation;
            }

            public String getPassword() {
                return password;
            }

            public void setPassword(String password) {
                this.password = password;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public long getRegisterTime() {
                return registerTime;
            }

            public void setRegisterTime(long registerTime) {
                this.registerTime = registerTime;
            }

            public int getRelationLevel() {
                return relationLevel;
            }

            public void setRelationLevel(int relationLevel) {
                this.relationLevel = relationLevel;
            }

            public int getRouteId() {
                return routeId;
            }

            public void setRouteId(int routeId) {
                this.routeId = routeId;
            }

            public int getShopFlag() {
                return shopFlag;
            }

            public void setShopFlag(int shopFlag) {
                this.shopFlag = shopFlag;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
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
