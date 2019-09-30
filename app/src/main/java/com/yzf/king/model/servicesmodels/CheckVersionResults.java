package com.yzf.king.model.servicesmodels;

import com.yzf.king.model.BaseModel;

/**
 * ClaseName：CheckVersionResults
 * Description：
 * Author：JensenWei
 * QQ: 2188307188
 * Createtime：2018/8/24 11:11
 * Modified By：
 * Fixtime：2018/8/24 11:11
 * FixDescription：
 */

public class CheckVersionResults extends BaseModel {
    private int code;
    private DataBean data;
    private String message;

    public static class DataBean {
        private String add1;
        private String add2;
        private String appDesc;
        private String appName;
        private String appVersion;
        private String branchCode;
        private String createTime;
        private String createUser;
        private String downlandUrl;
        private String iconUrl;
        private int id;
        private String linkUrl;
        private String merchId;
        private String mobileOs;
        private String publicFlag;
        private String updateDesc;
        private String updateFlag;
        private String updateTime;
        private String updateUser;
        private String version;
        private String versionCode;

        public String getAdd1() {
            return this.add1;
        }

        public void setAdd1(String str) {
            this.add1 = str;
        }

        public String getAdd2() {
            return this.add2;
        }

        public void setAdd2(String str) {
            this.add2 = str;
        }

        public String getAppDesc() {
            return this.appDesc;
        }

        public void setAppDesc(String str) {
            this.appDesc = str;
        }

        public String getAppName() {
            return this.appName;
        }

        public void setAppName(String str) {
            this.appName = str;
        }

        public String getAppVersion() {
            return this.appVersion;
        }

        public void setAppVersion(String str) {
            this.appVersion = str;
        }

        public String getBranchCode() {
            return this.branchCode;
        }

        public void setBranchCode(String str) {
            this.branchCode = str;
        }

        public String getCreateTime() {
            return this.createTime;
        }

        public void setCreateTime(String str) {
            this.createTime = str;
        }

        public String getCreateUser() {
            return this.createUser;
        }

        public void setCreateUser(String str) {
            this.createUser = str;
        }

        public String getDownlandUrl() {
            return this.downlandUrl;
        }

        public void setDownlandUrl(String str) {
            this.downlandUrl = str;
        }

        public String getIconUrl() {
            return this.iconUrl;
        }

        public void setIconUrl(String str) {
            this.iconUrl = str;
        }

        public int getId() {
            return this.id;
        }

        public void setId(int i) {
            this.id = i;
        }

        public String getLinkUrl() {
            return this.linkUrl;
        }

        public void setLinkUrl(String str) {
            this.linkUrl = str;
        }

        public String getMerchId() {
            return this.merchId;
        }

        public void setMerchId(String str) {
            this.merchId = str;
        }

        public String getMobileOs() {
            return this.mobileOs;
        }

        public void setMobileOs(String str) {
            this.mobileOs = str;
        }

        public String getPublicFlag() {
            return this.publicFlag;
        }

        public void setPublicFlag(String str) {
            this.publicFlag = str;
        }

        public String getUpdateDesc() {
            return this.updateDesc;
        }

        public void setUpdateDesc(String str) {
            this.updateDesc = str;
        }

        public String getUpdateFlag() {
            return this.updateFlag;
        }

        public void setUpdateFlag(String str) {
            this.updateFlag = str;
        }

        public String getUpdateTime() {
            return this.updateTime;
        }

        public void setUpdateTime(String str) {
            this.updateTime = str;
        }

        public String getUpdateUser() {
            return this.updateUser;
        }

        public void setUpdateUser(String str) {
            this.updateUser = str;
        }

        public String getVersion() {
            return this.version;
        }

        public void setVersion(String str) {
            this.version = str;
        }

        public String getVersionCode() {
            return this.versionCode;
        }

        public void setVersionCode(String str) {
            this.versionCode = str;
        }
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int i) {
        this.code = i;
    }

    public DataBean getData() {
        return this.data;
    }

    public void setData(DataBean dataBean) {
        this.data = dataBean;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String str) {
        this.message = str;
    }
}
