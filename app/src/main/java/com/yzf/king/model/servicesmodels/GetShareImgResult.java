package com.yzf.king.model.servicesmodels;

import com.yzf.king.model.BaseModel;

import java.io.Serializable;
import java.util.List;

/**
 * ClaseName：GetShareImgResult
 * Description：
 * Author：JensenWei
 * QQ: 2188307188
 * Createtime：2019/4/11 17:28
 * Modified By：
 * Fixtime：2019/4/11 17:28
 * FixDescription：
 */
public class GetShareImgResult extends BaseModel implements Serializable{

    /**
     * code : 200
     * data : [{"imgName":"1554966792184.jpg","imgPath":"http://huikajinguanjia.oss-cn-shenzhen.aliyuncs.com/100000272019041117254519171554966792184.jpg","imgType":"17","merchId":"10000027","merchType":"0","sortId":"1"},{"imgName":"1554966808377.jpg","imgPath":"http://huikajinguanjia.oss-cn-shenzhen.aliyuncs.com/100000272019041117254631971554966808377.jpg","imgType":"17","merchId":"10000027","merchType":"0","sortId":"2"},{"imgName":"1554966822701.jpg","imgPath":"http://huikajinguanjia.oss-cn-shenzhen.aliyuncs.com/100000272019041117254737911554966822701.jpg","imgType":"17","merchId":"10000027","merchType":"0","sortId":"3"}]
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
         * imgName : 1554966792184.jpg
         * imgPath : http://huikajinguanjia.oss-cn-shenzhen.aliyuncs.com/100000272019041117254519171554966792184.jpg
         * imgType : 17
         * merchId : 10000027
         * merchType : 0
         * sortId : 1
         */

        private String imgName;
        private String imgPath;
        private String imgType;
        private String merchId;
        private String merchType;
        private String sortId;
        private String h5Url;
        private int resourceId;

        public String getImgName() {
            return imgName;
        }

        public void setImgName(String imgName) {
            this.imgName = imgName;
        }

        public String getImgPath() {
            return imgPath;
        }

        public void setImgPath(String imgPath) {
            this.imgPath = imgPath;
        }

        public String getImgType() {
            return imgType;
        }

        public void setImgType(String imgType) {
            this.imgType = imgType;
        }

        public String getMerchId() {
            return merchId;
        }

        public void setMerchId(String merchId) {
            this.merchId = merchId;
        }

        public String getMerchType() {
            return merchType;
        }

        public void setMerchType(String merchType) {
            this.merchType = merchType;
        }

        public String getSortId() {
            return sortId;
        }

        public void setSortId(String sortId) {
            this.sortId = sortId;
        }

        public String getH5Url() {
            return h5Url;
        }

        public void setH5Url(String h5Url) {
            this.h5Url = h5Url;
        }

        public int getResourceId() {
            return resourceId;
        }

        public void setResourceId(int resourceId) {
            this.resourceId = resourceId;
        }
    }
}
