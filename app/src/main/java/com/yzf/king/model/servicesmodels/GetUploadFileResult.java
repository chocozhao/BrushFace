package com.yzf.king.model.servicesmodels;


import com.yzf.king.model.BaseModel;

/**
 * ClaseName：GetUploadFileResult
 * Description：
 * Author：chocozhao
 * QQ: 1027313530
 * Createtime：2019/8/20 14:02
 * Modified By：
 * Fixtime：2019/8/20 14:02
 * FixDescription：
 **/

public class GetUploadFileResult extends BaseModel {


    /**
     * code : 200
     * data : {"licenseBean":{"licenseNo":"9","address":"广州市番禺区市桥街东环路453号302室","name":"李兴文","shopName":"广州市朋代通讯设备有限公司","range":"crfg2govc中华人民共和国国家工商行政管理总局监制"},"imgId":"http://smilepay.oss-cn-shenzhen.aliyuncs.com/1000002420190820135927809156628076717676.jpg","imgPath":"http://smilepay.oss-cn-shenzhen.aliyuncs.com/1000002420190820135927809156628076717676.jpg"}
     * message : 图片上传成功
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

    public static class DataBean {
        /**
         * licenseBean : {"licenseNo":"9","address":"广州市番禺区市桥街东环路453号302室","name":"李兴文","shopName":"广州市朋代通讯设备有限公司","range":"crfg2govc中华人民共和国国家工商行政管理总局监制"}
         * imgId : http://smilepay.oss-cn-shenzhen.aliyuncs.com/1000002420190820135927809156628076717676.jpg
         * imgPath : http://smilepay.oss-cn-shenzhen.aliyuncs.com/1000002420190820135927809156628076717676.jpg
         */

        private LicenseBeanBean licenseBean;
        private String imgId;
        private String imgPath;

        public LicenseBeanBean getLicenseBean() {
            return licenseBean;
        }

        public void setLicenseBean(LicenseBeanBean licenseBean) {
            this.licenseBean = licenseBean;
        }

        public String getImgId() {
            return imgId;
        }

        public void setImgId(String imgId) {
            this.imgId = imgId;
        }

        public String getImgPath() {
            return imgPath;
        }

        public void setImgPath(String imgPath) {
            this.imgPath = imgPath;
        }

        public static class LicenseBeanBean {
            /**
             * licenseNo : 9
             * address : 广州市番禺区市桥街东环路453号302室
             * name : 李兴文
             * shopName : 广州市朋代通讯设备有限公司
             * range : crfg2govc中华人民共和国国家工商行政管理总局监制
             */

            private String licenseNo;
            private String address;
            private String name;
            private String shopName;
            private String range;
            private String idNo;
            private String effectDate;

            public String getLicenseNo() {
                return licenseNo;
            }

            public void setLicenseNo(String licenseNo) {
                this.licenseNo = licenseNo;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getShopName() {
                return shopName;
            }

            public void setShopName(String shopName) {
                this.shopName = shopName;
            }

            public String getRange() {
                return range;
            }

            public void setRange(String range) {
                this.range = range;
            }

            public String getIdNo() {
                return idNo;
            }

            public void setIdNo(String idNo) {
                this.idNo = idNo;
            }

            public String getEffectDate() {
                return effectDate;
            }

            public void setEffectDate(String effectDate) {
                this.effectDate = effectDate;
            }


        }
    }
}
