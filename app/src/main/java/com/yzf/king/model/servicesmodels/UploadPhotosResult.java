package com.yzf.king.model.servicesmodels;

import com.yzf.king.model.BaseModel;

public class UploadPhotosResult extends BaseModel {
    private int code;
    private DataBean data;
    private String message;

    public static class DataBean {
        private BankCardBean bankCardBean;
        private IdBeanBean idBean;
        private String imgId;
        private String imgPath;

        public static class BankCardBean {
            private String bankName;
            private String bankType;
            private String cardNo;

            public String getCardNo() {
                return this.cardNo;
            }

            public void setCardNo(String str) {
                this.cardNo = str;
            }

            public String getBankName() {
                return this.bankName;
            }

            public void setBankName(String str) {
                this.bankName = str;
            }

            public String getBankType() {
                return this.bankType;
            }

            public void setBankType(String str) {
                this.bankType = str;
            }
        }

        public static class IdBeanBean {
            private String agency;
            private String effectDate;
            private String idNo;
            private String invalidDate;
            private String name;

            public String getIdNo() {
                return this.idNo;
            }

            public void setIdNo(String str) {
                this.idNo = str;
            }

            public String getName() {
                return this.name;
            }

            public void setName(String str) {
                this.name = str;
            }

            public String getEffectDate() {
                return this.effectDate;
            }

            public void setEffectDate(String str) {
                this.effectDate = str;
            }

            public String getInvalidDate() {
                return this.invalidDate;
            }

            public void setInvalidDate(String str) {
                this.invalidDate = str;
            }

            public String getAgency() {
                return this.agency;
            }

            public void setAgency(String str) {
                this.agency = str;
            }
        }

        public String getImgId() {
            return this.imgId;
        }

        public void setImgId(String str) {
            this.imgId = str;
        }

        public String getImgPath() {
            return this.imgPath;
        }

        public void setImgPath(String str) {
            this.imgPath = str;
        }

        public IdBeanBean getIdBean() {
            return this.idBean;
        }

        public void setIdBean(IdBeanBean idBeanBean) {
            this.idBean = idBeanBean;
        }

        public BankCardBean getBankCardBean() {
            return this.bankCardBean;
        }

        public void setBankCardBean(BankCardBean bankCardBean) {
            this.bankCardBean = bankCardBean;
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