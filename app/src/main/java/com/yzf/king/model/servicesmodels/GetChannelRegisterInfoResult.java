package com.yzf.king.model.servicesmodels;

import com.yzf.king.model.BaseModel;

import java.util.List;

public class GetChannelRegisterInfoResult extends BaseModel {
    private int code;
    private DataBean data;
    private String message;

    public static class DataBean {
        private List<InfoBean> info;
        private String needRegister;

        public static class InfoBean {
            private String bindFlag;
            private String bindType;
            private String cardId;
            private String channelCode;
            private String forceFlag;
            private String function;
            private String merchId;
            private String status;

            public String getBindFlag() {
                return this.bindFlag;
            }

            public void setBindFlag(String str) {
                this.bindFlag = str;
            }

            public String getCardId() {
                return this.cardId;
            }

            public void setCardId(String str) {
                this.cardId = str;
            }

            public String getFunction() {
                return this.function;
            }

            public void setFunction(String str) {
                this.function = str;
            }

            public String getBindType() {
                return this.bindType;
            }

            public void setBindType(String str) {
                this.bindType = str;
            }

            public String getForceFlag() {
                return this.forceFlag;
            }

            public void setForceFlag(String str) {
                this.forceFlag = str;
            }

            public String getChannelCode() {
                return this.channelCode;
            }

            public void setChannelCode(String str) {
                this.channelCode = str;
            }

            public String getMerchId() {
                return this.merchId;
            }

            public void setMerchId(String str) {
                this.merchId = str;
            }

            public String getStatus() {
                return this.status;
            }

            public void setStatus(String str) {
                this.status = str;
            }
        }

        public String getNeedRegister() {
            return this.needRegister;
        }

        public void setNeedRegister(String str) {
            this.needRegister = str;
        }

        public List<InfoBean> getInfo() {
            return this.info;
        }

        public void setInfo(List<InfoBean> list) {
            this.info = list;
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