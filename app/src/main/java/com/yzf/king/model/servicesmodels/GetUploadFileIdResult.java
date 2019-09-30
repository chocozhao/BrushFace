package com.yzf.king.model.servicesmodels;


import com.yzf.king.model.BaseModel;

/**
 * ClaseName：GetUploadFileIdResult
 * Description：
 * Author：chocozhao
 * QQ: 1027313530
 * Createtime：2019/8/21 16:55
 * Modified By：
 * Fixtime：2019/8/21 16:55
 * FixDescription：
 **/

public class GetUploadFileIdResult extends BaseModel {


    /**
     * code : 200
     * data : {"imgId":"http://smilepay.oss-cn-shenzhen.aliyuncs.com/100000252019082116482188162bbfee41dc3cf98.jpg","idBean":{"name":"易智军","idNo":"440233197609073034","effectDate":"20161111","agency":"广州市公安局天河分局","invalidDate":"20361111"},"imgPath":"http://smilepay.oss-cn-shenzhen.aliyuncs.com/100000252019082116482188162bbfee41dc3cf98.jpg"}
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
         * imgId : http://smilepay.oss-cn-shenzhen.aliyuncs.com/100000252019082116482188162bbfee41dc3cf98.jpg
         * idBean : {"name":"易智军","idNo":"440233197609073034","effectDate":"20161111","agency":"广州市公安局天河分局","invalidDate":"20361111"}
         * imgPath : http://smilepay.oss-cn-shenzhen.aliyuncs.com/100000252019082116482188162bbfee41dc3cf98.jpg
         */

        private String imgId;
        private IdBeanBean idBean;
        private String imgPath;

        public String getImgId() {
            return imgId;
        }

        public void setImgId(String imgId) {
            this.imgId = imgId;
        }

        public IdBeanBean getIdBean() {
            return idBean;
        }

        public void setIdBean(IdBeanBean idBean) {
            this.idBean = idBean;
        }

        public String getImgPath() {
            return imgPath;
        }

        public void setImgPath(String imgPath) {
            this.imgPath = imgPath;
        }

        public static class IdBeanBean {
            /**
             * name : 易智军
             * idNo : 440233197609073034
             * effectDate : 20161111
             * agency : 广州市公安局天河分局
             * invalidDate : 20361111
             */

            private String name;
            private String idNo;
            private String effectDate;
            private String agency;
            private String invalidDate;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
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

            public String getAgency() {
                return agency;
            }

            public void setAgency(String agency) {
                this.agency = agency;
            }

            public String getInvalidDate() {
                return invalidDate;
            }

            public void setInvalidDate(String invalidDate) {
                this.invalidDate = invalidDate;
            }
        }
    }
}
