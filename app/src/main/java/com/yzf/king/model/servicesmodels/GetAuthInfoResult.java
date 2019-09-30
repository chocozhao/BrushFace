package com.yzf.king.model.servicesmodels;

import com.yzf.king.model.BaseModel;

/**
 * ClaseName：GetAuthIncomeInfoResult
 * Description：
 * Author：JensenWei
 * QQ: 2188307188
 * Createtime：2019/5/7 13:48
 * Modified By：
 * Fixtime：2019/5/7 13:48
 * FixDescription：
 */
public class GetAuthInfoResult extends BaseModel {

    /**
     * code : 200
     * data : {"bindFlag":false,"incomeFlag":false,"credit":0}
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
         * bindFlag : false
         * incomeFlag : false
         * credit : 0
         */

        private boolean contactFlag;
        private boolean planFlag;
        private String tips;

        public boolean isContactFlag() {
            return contactFlag;
        }

        public void setContactFlag(boolean contactFlag) {
            this.contactFlag = contactFlag;
        }

        public boolean isPlanFlag() {
            return planFlag;
        }

        public void setPlanFlag(boolean planFlag) {
            this.planFlag = planFlag;
        }

        public String getTips() {
            return tips;
        }

        public void setTips(String tips) {
            this.tips = tips;
        }
    }
}
