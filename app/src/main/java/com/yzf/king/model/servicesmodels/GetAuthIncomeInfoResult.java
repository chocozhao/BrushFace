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
public class GetAuthIncomeInfoResult extends BaseModel {

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

        private boolean bindFlag;
        private boolean incomeFlag;
        private String credit;
        private String userCode;
        private String userKey;

        public boolean isBindFlag() {
            return bindFlag;
        }

        public void setBindFlag(boolean bindFlag) {
            this.bindFlag = bindFlag;
        }

        public boolean isIncomeFlag() {
            return incomeFlag;
        }

        public void setIncomeFlag(boolean incomeFlag) {
            this.incomeFlag = incomeFlag;
        }

        public String getCredit() {
            return credit;
        }

        public void setCredit(String credit) {
            this.credit = credit;
        }

        public String getUserCode() {
            return userCode;
        }

        public void setUserCode(String userCode) {
            this.userCode = userCode;
        }

        public String getUserKey() {
            return userKey;
        }

        public void setUserKey(String userKey) {
            this.userKey = userKey;
        }
    }
}
