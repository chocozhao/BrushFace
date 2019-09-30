package com.yzf.king.model.servicesmodels;

import com.yzf.king.model.BaseModel;

import java.util.List;

public class GetPhotosResult extends BaseModel {
    private int code;
    private List<DataBean> data;
    private String message;

    public static class DataBean {
        private String fileType;
        private String imgId;
        private String imgName;
        private String imgPath;
        private String merchId;
        private String sortId;
        private String url;

        public String getImgName() {
            return this.imgName;
        }

        public void setImgName(String str) {
            this.imgName = str;
        }

        public String getImgId() {
            return this.imgId;
        }

        public void setImgId(String str) {
            this.imgId = str;
        }

        public String getSortId() {
            return this.sortId;
        }

        public void setSortId(String str) {
            this.sortId = str;
        }

        public String getImgPath() {
            return this.imgPath;
        }

        public void setImgPath(String str) {
            this.imgPath = str;
        }

        public String getFileType() {
            return this.fileType;
        }

        public void setFileType(String str) {
            this.fileType = str;
        }

        public String getUrl() {
            return this.url;
        }

        public void setUrl(String str) {
            this.url = str;
        }

        public String getMerchId() {
            return this.merchId;
        }

        public void setMerchId(String str) {
            this.merchId = str;
        }
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int i) {
        this.code = i;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public List<DataBean> getData() {
        return this.data;
    }

    public void setData(List<DataBean> list) {
        this.data = list;
    }
}