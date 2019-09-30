package com.yzf.king.model.servicesmodels;

import com.yzf.king.model.BaseModel;

import java.io.Serializable;

/**
 * ClaseName：GetChannelInfoResults
 * Description：
 * Author：JensenWei
 * QQ: 2188307188
 * Createtime：2018/4/10 15:30
 * Modified By：
 * Fixtime：2018/4/10 15:30
 * FixDescription：
 */

public class IncomeResults extends BaseModel{

    /**
     * resp_code : 00
     * resp_msg : 成功
     * data : {"acctId":"70011599951980602","acctType":"01","acctIssuer":"00000000","avlbBal":"0","fznAmt":"0","effDate":null,"expirDate":"20991231","updateTime":null,"insertTime":null,"merchId":null,"acctStatus":"10"}
     */

    private int code;
    private String message;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean implements Serializable {
        /**
         * acctId : 70011599951980602
         * acctType : 01
         * acctIssuer : 00000000
         * avlbBal : 0
         * fznAmt : 0
         * effDate : null
         * expirDate : 20991231
         * updateTime : null
         * insertTime : null
         * merchId : null
         * acctStatus : 10
         */

        private String userCode;
        private String userKey;
        private String incomeFlag;

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

        public String getIncomeFlag() {
            return incomeFlag;
        }

        public void setIncomeFlag(String incomeFlag) {
            this.incomeFlag = incomeFlag;
        }
    }
}
