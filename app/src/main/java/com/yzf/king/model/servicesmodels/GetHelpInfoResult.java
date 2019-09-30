package com.yzf.king.model.servicesmodels;


import com.yzf.king.model.BaseModel;

import java.io.Serializable;
import java.util.List;

/**
 * ClaseName：GetHelpInfoResult
 * Description：
 * Author：chocozhao
 * QQ: 1027313530
 * Createtime：2019/5/5 18:54
 * Modified By：
 * Fixtime：2019/5/5 18:54
 * FixDescription：
 **/

public class GetHelpInfoResult extends BaseModel {

    /**
     * code : 200
     * data : [{"actionName":"点我","actionType":"TX","branchCode":"F0000001","description":"我要信用卡套现","insertTime":1557044277000,"sortId":1,"status":1,"title":"小管家教您玩转金管家功能","updateTime":1557044277000,"version":1},{"actionName":"点我","actionType":"PLAN","branchCode":"F0000001","description":"信用卡还款","insertTime":1557044277000,"sortId":2,"status":1,"title":"小管家教您玩转金管家功能","updateTime":1557044277000,"version":1}]
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
         * actionName : 点我
         * actionType : TX
         * branchCode : F0000001
         * description : 我要信用卡套现
         * insertTime : 1557044277000
         * sortId : 1
         * status : 1
         * title : 小管家教您玩转金管家功能
         * updateTime : 1557044277000
         * version : 1
         */

        private String actionName;
        private String actionType;
        private String branchCode;
        private String descriptions;
        private long insertTime;
        private int sortId;
        private int status;
        private String title;
        private long updateTime;
        private int version;

        public String getActionName() {
            return actionName;
        }

        public void setActionName(String actionName) {
            this.actionName = actionName;
        }

        public String getActionType() {
            return actionType;
        }

        public void setActionType(String actionType) {
            this.actionType = actionType;
        }

        public String getBranchCode() {
            return branchCode;
        }

        public void setBranchCode(String branchCode) {
            this.branchCode = branchCode;
        }

        public String getDescriptions() {
            return descriptions;
        }

        public void setDescriptions(String descriptions) {
            this.descriptions = descriptions;
        }

        public long getInsertTime() {
            return insertTime;
        }

        public void setInsertTime(long insertTime) {
            this.insertTime = insertTime;
        }

        public int getSortId() {
            return sortId;
        }

        public void setSortId(int sortId) {
            this.sortId = sortId;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
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
