package com.yzf.king.model.servicesmodels;

import com.yzf.king.kit.AppTools;
import com.yzf.king.model.BaseModel;

import java.io.Serializable;

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
public class GetCreditInfoResult extends BaseModel {

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

    public static class DataBean implements Serializable {
        /**
         * bindFlag : false
         * incomeFlag : false
         * credit : 0
         */

        private String credit;
        private String amount;
        private String fee;

        public String getCredit() {
            return AppTools.formatF2Y(credit);
        }

        public void setCredit(String credit) {
            this.credit = credit;
        }

        public String getAmount() {
            return AppTools.formatF2Y(amount);
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getFee() {
            return AppTools.formatF2Y(fee);
        }

        public void setFee(String fee) {
            this.fee = fee;
        }
    }
}
