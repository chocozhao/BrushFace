package com.yzf.king.model.servicesmodels;

import com.yzf.king.model.BaseModel;

import java.util.List;

public class GetUrlResult extends BaseModel {

    /**
     * code : 200
     * data : [{"branchCode":"F0000001","insertTime":1552632024000,"remark":"信用卡申请","status":1,"type":"creditUrl","updateTime":1552632024000,"url":"http://newtesth5.yiyoupay.net/king/#/upGrade?merchId=10000030&token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1NTI3MjEwMzksIm1lcmNoSWQiOiIxMDAwMDAzMCJ9.SbXxXg_gBLUl-bYIoNC47-ZFl56moCWk3EJV1mzHoOc"},{"branchCode":"F0000001","insertTime":1552632024000,"remark":"新手指南","status":1,"type":"guideUrl","updateTime":1552632024000,"url":"http://h5.yiyoupay.net/link/html/directions.html?topBranchNo=20000029"},{"branchCode":"F0000001","insertTime":1552632024000,"remark":"贷款","status":1,"type":"loanUrl","updateTime":1552632024000,"url":"http://h5.yiyoupay.net/apply/html/loanApplication.html"},{"branchCode":"F0000001","insertTime":1552632024000,"remark":"资讯","status":1,"type":"newsUrl","updateTime":1552632024000,"url":"https://finance.sina.cn/creditcard?vt=4&cid=76653"},{"branchCode":"F0000001","insertTime":1552632024000,"remark":"规划链接","status":1,"type":"planUrl","updateTime":1552632024000,"url":"king/#/home?merchId="},{"branchCode":"F0000001","insertTime":1552632024000,"remark":"分享链接","status":1,"type":"shareUrl","updateTime":1552632024000,"url":"king/#/register?fatherId="},{"branchCode":"F0000001","insertTime":1552632024000,"remark":"升级链接","status":1,"type":"upgradeUrl","updateTime":1552632024000,"url":"king/#/upGrade?merchId="}]
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
         * branchCode : F0000001
         * insertTime : 1552632024000
         * remark : 信用卡申请
         * status : 1
         * type : creditUrl
         * updateTime : 1552632024000
         * url : http://newtesth5.yiyoupay.net/king/#/upGrade?merchId=10000030&token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1NTI3MjEwMzksIm1lcmNoSWQiOiIxMDAwMDAzMCJ9.SbXxXg_gBLUl-bYIoNC47-ZFl56moCWk3EJV1mzHoOc
         */

        private String branchCode;
        private long insertTime;
        private String remark;
        private int status;
        private String type;
        private long updateTime;
        private String url;

        public String getBranchCode() {
            return branchCode;
        }

        public void setBranchCode(String branchCode) {
            this.branchCode = branchCode;
        }

        public long getInsertTime() {
            return insertTime;
        }

        public void setInsertTime(long insertTime) {
            this.insertTime = insertTime;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public long getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(long updateTime) {
            this.updateTime = updateTime;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}