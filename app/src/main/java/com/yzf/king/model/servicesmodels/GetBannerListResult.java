package com.yzf.king.model.servicesmodels;

import com.yzf.king.model.BaseModel;

import java.util.List;

/**
 * ClaseName：GetBannerListResult
 * Description：
 * Author：JensenWei
 * QQ: 2188307188
 * Createtime：2017/5/4 16:19
 * Modified By：
 * Fixtime：2017/5/4 16:19
 * FixDescription：
 */

public class GetBannerListResult extends BaseModel {
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
         * orderId : bc_test201704111640422877
         * amt : 10000
         * serviceName : 绑卡
         * serviceId : 23
         * transTime : 2017-04-11 16:42:03.0
         * incomeAmt : 3
         * yMerchId : 990120745840
         */

        private String url;
        private String targetUrl;
        private int resourceId;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getTargetUrl() {
            return targetUrl;
        }

        public void setTargetUrl(String targetUrl) {
            this.targetUrl = targetUrl;
        }

        public int getResourceId() {
            return resourceId;
        }

        public void setResourceId(int resourceId) {
            this.resourceId = resourceId;
        }
    }
}
