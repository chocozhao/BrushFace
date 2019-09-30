package com.yzf.king.model.servicesmodels;

import com.yzf.king.model.BaseModel;

/**
 * ClaseName：GetPosKeyResult
 * Description：
 * Author：JensenWei
 * QQ: 2188307188
 * Createtime：2019/3/7 14:34
 * Modified By：
 * Fixtime：2019/3/7 14:34
 * FixDescription：
 */
public class GetPosKeyResult extends BaseModel {


    /**
     * code : 200
     * data : {"macKey":"CE974DD05A8BD0B47065576FA793AFDF","macKeyCheck":"956F5C41","merchId":"10000000","pinKey":"CE974DD05A8BD0B47065576FA793AFDF","pinKeyCheck":"956F5C41","status":1,"termKey":"37A2D7C09A348114B2C80E6A0210E140","termKeyCheck":"99C269E3","traKey":"CE974DD05A8BD0B47065576FA793AFDF","traKeyCheck":"956F5C41"}
     * message : 获取成功
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
         * macKey : CE974DD05A8BD0B47065576FA793AFDF
         * macKeyCheck : 956F5C41
         * merchId : 10000000
         * pinKey : CE974DD05A8BD0B47065576FA793AFDF
         * pinKeyCheck : 956F5C41
         * status : 1
         * termKey : 37A2D7C09A348114B2C80E6A0210E140
         * termKeyCheck : 99C269E3
         * traKey : CE974DD05A8BD0B47065576FA793AFDF
         * traKeyCheck : 956F5C41
         */

        private String macKey;
        private String macKeyCheck;
        private String merchId;
        private String pinKey;
        private String pinKeyCheck;
        private String status;
        private String termKey;
        private String termKeyCheck;
        private String traKey;
        private String traKeyCheck;

        public String getMacKey() {
            return macKey;
        }

        public void setMacKey(String macKey) {
            this.macKey = macKey;
        }

        public String getMacKeyCheck() {
            return macKeyCheck;
        }

        public void setMacKeyCheck(String macKeyCheck) {
            this.macKeyCheck = macKeyCheck;
        }

        public String getMerchId() {
            return merchId;
        }

        public void setMerchId(String merchId) {
            this.merchId = merchId;
        }

        public String getPinKey() {
            return pinKey;
        }

        public void setPinKey(String pinKey) {
            this.pinKey = pinKey;
        }

        public String getPinKeyCheck() {
            return pinKeyCheck;
        }

        public void setPinKeyCheck(String pinKeyCheck) {
            this.pinKeyCheck = pinKeyCheck;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getTermKey() {
            return termKey;
        }

        public void setTermKey(String termKey) {
            this.termKey = termKey;
        }

        public String getTermKeyCheck() {
            return termKeyCheck;
        }

        public void setTermKeyCheck(String termKeyCheck) {
            this.termKeyCheck = termKeyCheck;
        }

        public String getTraKey() {
            return traKey;
        }

        public void setTraKey(String traKey) {
            this.traKey = traKey;
        }

        public String getTraKeyCheck() {
            return traKeyCheck;
        }

        public void setTraKeyCheck(String traKeyCheck) {
            this.traKeyCheck = traKeyCheck;
        }
    }
}
