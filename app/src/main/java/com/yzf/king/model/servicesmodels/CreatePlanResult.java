package com.yzf.king.model.servicesmodels;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yzf.king.kit.AppTools;
import com.yzf.king.model.BaseModel;

import java.io.Serializable;
import java.util.List;

/**
 * ClaseName：CreatePlanResult
 * Description：
 * Author：JensenWei
 * QQ: 2188307188
 * Createtime：2018/8/24 11:12
 * Modified By：
 * Fixtime：2018/8/24 11:12
 * FixDescription：
 */
public class CreatePlanResult extends BaseModel {
    private int code;
    private DataBean data;
    private String message;

    public static class DataBean implements Serializable {
        private String avlbAmount;
        private String cardId;
        private String count;
        private String depositAmount;
        private String detailDays;
        private String details;
        private String feeAmount;
        private String firstFeeAmt;
        private String firstTransAmt;
        private String regOrderId;
        private List<RandomPlanCardResult.DataBean.RowsBean> rows;
        private String times;
        private String totalAmount;

        public String getDepositAmount() {
            return AppTools.formatF2Y(this.depositAmount);
        }

        public void setDepositAmount(String str) {
            this.depositAmount = str;
        }

        public String getFeeAmount() {
            return AppTools.formatF2Y(this.feeAmount);
        }

        public void setFeeAmount(String str) {
            this.feeAmount = str;
        }

        public String getFirstFeeAmt() {
            return AppTools.formatF2Y(this.firstFeeAmt);
        }

        public void setFirstFeeAmt(String str) {
            this.firstFeeAmt = str;
        }

        public String getTimes() {
            return this.times;
        }

        public void setTimes(String str) {
            this.times = str;
        }

        public String getRegOrderId() {
            return this.regOrderId;
        }

        public void setRegOrderId(String str) {
            this.regOrderId = str;
        }

        public String getCount() {
            return this.count;
        }

        public void setCount(String str) {
            this.count = str;
        }

        public String getDetails() {
            return this.details;
        }

        public void setDetails(String str) {
            this.details = str;
        }

        public String getDetailDays() {
            return this.detailDays;
        }

        public void setDetailDays(String str) {
            this.detailDays = str;
        }

        public String getFirstTransAmt() {
            return AppTools.formatF2Y(this.firstTransAmt);
        }

        public void setFirstTransAmt(String str) {
            this.firstTransAmt = str;
        }

        public String getAvlbAmount() {
            return AppTools.formatF2Y(this.avlbAmount);
        }

        public void setAvlbAmount(String str) {
            this.avlbAmount = str;
        }

        public String getTotalAmount() {
            return AppTools.formatF2Y(this.totalAmount);
        }

        public void setTotalAmount(String str) {
            this.totalAmount = str;
        }

        public String getCardId() {
            return this.cardId;
        }

        public void setCardId(String str) {
            this.cardId = str;
        }

        public List<RandomPlanCardResult.DataBean.RowsBean> getRows() {
            return (List) new Gson().fromJson(getDetails(), new TypeToken<List<RandomPlanCardResult.DataBean.RowsBean>>() {
            }.getType());
        }

        public void setRows(List<RandomPlanCardResult.DataBean.RowsBean> list) {
            this.rows = list;
        }
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int i) {
        this.code = i;
    }

    public DataBean getData() {
        return this.data;
    }

    public void setData(DataBean dataBean) {
        this.data = dataBean;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String str) {
        this.message = str;
    }
}
