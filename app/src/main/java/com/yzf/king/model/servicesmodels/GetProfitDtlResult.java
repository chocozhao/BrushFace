package com.yzf.king.model.servicesmodels;

import com.yzf.king.model.BaseModel;

import java.io.Serializable;
import java.util.List;

/**
 * ClaseName：GetProfitDtlResult
 * Description：
 * Author：JensenWei
 * QQ: 2188307188
 * Createtime：2019/2/27 19:46
 * Modified By：
 * Fixtime：2019/2/27 19:46
 * FixDescription：
 */
public class GetProfitDtlResult extends BaseModel {

    /**
     * code : 200
     * data : {"dataDtl":[{"insertTime":1551261845000,"merchId":"10000042","merchName":"罗俊伟","profitAmt":3.21,"serviceName":"(新)卡规划还款"},{"insertTime":1551261496000,"merchId":"10000027","merchName":"农本勇","profitAmt":5.49,"serviceName":"(新)卡规划还款"},{"insertTime":1551259194000,"merchId":"10000028","merchName":"曹城","profitAmt":14,"serviceName":"升级新)卡规划还款"},{"insertTime":1551261496000,"merchId":"10000027","merchName":"农本勇","profitAmt":5.49,"serviceName":"(新)卡规划还款"},{"insertTime":1551259194000,"merchId":"10000028","merchName":"曹城","profitAmt":14,"serviceName":"升级代理商"},{"insertTime":1551238405000,"merchId":"10000042","merchName":"罗俊伟","profitAmt":2.63,"serviceName":"(新)卡规划结束(还款)"},{"insertTime":1551187603000,"merchId":"10000041","merchName":"陈衍欣","profitAmt":14,"serviceName":"升级新)卡规划还款"},{"insertTime":1551261496000,"merchId":"10000027","merchName":"农本勇","profitAmt":5.49,"serviceName":"(新)卡规划还款"},{"insertTime":1551259194000,"merchId":"10000028","merchName":"曹城","profitAmt":14,"serviceName":"升级344273代理商"},{"insertTime":1551238405000,"merchId":"10000042","merchName":"罗俊伟","profitAmt":2.63,"serviceName":"(新)卡规划结束(还款)"},{"insertTime":1551187603000,"merchId":"10000041","merchName":"陈衍欣","profitAmt":14,"serviceName":"升级代理商"},{"insertTime":1551186687000,"merchId":"10000024","merchName":"彭晖","profitAmt":14,"serviceName":"升级代理商"},{"insertTime":1551186046000,"merchId":"10000024","merchName":"彭晖","profitAmt":14,"serviceName":"升级代理商"},{"insertTime":1551184426000,"merchId":"10000042","merchName":"罗俊伟","profitAmt":3.67,"serviceName":"(新)卡规划还款"},{"insertTime":1551181199000,"merchId":"10000042","merchName":"罗俊伟","profitAmt":14,"serviceName":"升级代理商"},{"insertTime":1551166674000,"merchId":"10000027","merchName":"农本勇","profitAmt":3.99,"serviceName":"(新)卡规划还款"}],"sumAmt":88.99}
     * message : 查询成功
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

    public static class DataBean implements Serializable{
        /**
         * dataDtl : [{"insertTime":1551261845000,"merchId":"10000042","merchName":"罗俊伟","profitAmt":3.21,"serviceName":"(新)卡规划还款"},{"insertTime":1551261496000,"merchId":"10000027","merchName":"农本勇","profitAmt":5.49,"serviceName":"(新)卡规划还款"},{"insertTime":1551259194000,"merchId":"10000028","merchName":"曹城","profitAmt":14,"serviceName":"升级新)卡规划还款"},{"insertTime":1551261496000,"merchId":"10000027","merchName":"农本勇","profitAmt":5.49,"serviceName":"(新)卡规划还款"},{"insertTime":1551259194000,"merchId":"10000028","merchName":"曹城","profitAmt":14,"serviceName":"升级代理商"},{"insertTime":1551238405000,"merchId":"10000042","merchName":"罗俊伟","profitAmt":2.63,"serviceName":"(新)卡规划结束(还款)"},{"insertTime":1551187603000,"merchId":"10000041","merchName":"陈衍欣","profitAmt":14,"serviceName":"升级新)卡规划还款"},{"insertTime":1551261496000,"merchId":"10000027","merchName":"农本勇","profitAmt":5.49,"serviceName":"(新)卡规划还款"},{"insertTime":1551259194000,"merchId":"10000028","merchName":"曹城","profitAmt":14,"serviceName":"升级344273代理商"},{"insertTime":1551238405000,"merchId":"10000042","merchName":"罗俊伟","profitAmt":2.63,"serviceName":"(新)卡规划结束(还款)"},{"insertTime":1551187603000,"merchId":"10000041","merchName":"陈衍欣","profitAmt":14,"serviceName":"升级代理商"},{"insertTime":1551186687000,"merchId":"10000024","merchName":"彭晖","profitAmt":14,"serviceName":"升级代理商"},{"insertTime":1551186046000,"merchId":"10000024","merchName":"彭晖","profitAmt":14,"serviceName":"升级代理商"},{"insertTime":1551184426000,"merchId":"10000042","merchName":"罗俊伟","profitAmt":3.67,"serviceName":"(新)卡规划还款"},{"insertTime":1551181199000,"merchId":"10000042","merchName":"罗俊伟","profitAmt":14,"serviceName":"升级代理商"},{"insertTime":1551166674000,"merchId":"10000027","merchName":"农本勇","profitAmt":3.99,"serviceName":"(新)卡规划还款"}]
         * sumAmt : 88.99
         */

        private String sumAmt;
        private List<DataDtlBean> dataDtl;

        public String getSumAmt() {
            return sumAmt;
        }

        public void setSumAmt(String sumAmt) {
            this.sumAmt = sumAmt;
        }

        public List<DataDtlBean> getDataDtl() {
            return dataDtl;
        }

        public void setDataDtl(List<DataDtlBean> dataDtl) {
            this.dataDtl = dataDtl;
        }

        public static class DataDtlBean implements Serializable {
            /**
             * insertTime : 1551261845000
             * merchId : 10000042
             * merchName : 罗俊伟
             * profitAmt : 3.21
             * serviceName : (新)卡规划还款
             */

            private long insertTime;
            private String merchId;
            private String merchName;
            private String profitAmt;
            private String serviceName;
            private String businessId;
            private String businessName;

            public long getInsertTime() {
                return insertTime;
            }

            public void setInsertTime(long insertTime) {
                this.insertTime = insertTime;
            }

            public String getMerchId() {
                return merchId;
            }

            public void setMerchId(String merchId) {
                this.merchId = merchId;
            }

            public String getMerchName() {
                return merchName;
            }

            public void setMerchName(String merchName) {
                this.merchName = merchName;
            }

            public String getProfitAmt() {
                return profitAmt;
            }

            public void setProfitAmt(String profitAmt) {
                this.profitAmt = profitAmt;
            }

            public String getServiceName() {
                return serviceName;
            }

            public void setServiceName(String serviceName) {
                this.serviceName = serviceName;
            }

            public String getBusinessId() {
                return businessId;
            }

            public void setBusinessId(String businessId) {
                this.businessId = businessId;
            }

            public String getBusinessName() {
                return businessName;
            }

            public void setBusinessName(String businessName) {
                this.businessName = businessName;
            }
        }
    }
}
