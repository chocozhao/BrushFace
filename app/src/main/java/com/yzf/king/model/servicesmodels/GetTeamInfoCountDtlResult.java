package com.yzf.king.model.servicesmodels;


import com.yzf.king.model.BaseModel;

import java.io.Serializable;
import java.util.List;

/**
 * ClaseName：GetTeamInfoCountDtlResult
 * Description：
 * Author：chocozhao
 * QQ: 1027313530
 * Createtime：2019/5/17 17:18
 * Modified By：
 * Fixtime：2019/5/17 17:18
 * FixDescription：
 **/

public class GetTeamInfoCountDtlResult extends BaseModel {


    /**
     * code : 200
     * data : {"type":"注册","info":[{"merchId":"10000105","merchLevel":1,"merchName":"18666385288","phone":"18666385288","registerDate":"20190515","status":"03"},{"merchId":"10000096","merchLevel":1,"merchName":"15914429654","phone":"15914429654","registerDate":"20190513","status":"03"},{"idName":"农本勇","merchId":"10000092","merchLevel":2,"merchName":"农本勇","phone":"13144440010","registerDate":"20190425","status":"00","verifyDate":"20190425"},{"merchId":"10000091","merchLevel":1,"merchName":"13144440009","phone":"13144440009","registerDate":"20190425","status":"03"},{"merchId":"10000090","merchLevel":1,"merchName":"13144440007","phone":"13144440007","registerDate":"20190425","status":"03"},{"idName":"农本勇","merchId":"10000089","merchLevel":2,"merchName":"农本勇","phone":"13144440006","registerDate":"20190425","status":"02","verifyDate":"20190516"},{"idName":"农本勇","merchId":"10000088","merchLevel":4,"merchName":"农本勇","phone":"13144440005","registerDate":"20190425","status":"00","upgradeDate":"20190507","verifyDate":"20190425"},{"idName":"农本勇","merchId":"10000086","merchLevel":4,"merchName":"农本勇","phone":"13144440003","registerDate":"20190425","status":"02","upgradeDate":"20190507","verifyDate":"20190425"},{"idName":"苏铭添","merchId":"10000085","merchLevel":2,"merchName":"苏铭添","phone":"13144440002","registerDate":"20190425","status":"02","verifyDate":"20190425"},{"idName":"农本勇","merchId":"10000081","merchLevel":2,"merchName":"农本勇","phone":"13144440080","registerDate":"20190418","status":"02","verifyDate":"20190418"}]}
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
         * type : 注册
         * info : [{"merchId":"10000105","merchLevel":1,"merchName":"18666385288","phone":"18666385288","registerDate":"20190515","status":"03"},{"merchId":"10000096","merchLevel":1,"merchName":"15914429654","phone":"15914429654","registerDate":"20190513","status":"03"},{"idName":"农本勇","merchId":"10000092","merchLevel":2,"merchName":"农本勇","phone":"13144440010","registerDate":"20190425","status":"00","verifyDate":"20190425"},{"merchId":"10000091","merchLevel":1,"merchName":"13144440009","phone":"13144440009","registerDate":"20190425","status":"03"},{"merchId":"10000090","merchLevel":1,"merchName":"13144440007","phone":"13144440007","registerDate":"20190425","status":"03"},{"idName":"农本勇","merchId":"10000089","merchLevel":2,"merchName":"农本勇","phone":"13144440006","registerDate":"20190425","status":"02","verifyDate":"20190516"},{"idName":"农本勇","merchId":"10000088","merchLevel":4,"merchName":"农本勇","phone":"13144440005","registerDate":"20190425","status":"00","upgradeDate":"20190507","verifyDate":"20190425"},{"idName":"农本勇","merchId":"10000086","merchLevel":4,"merchName":"农本勇","phone":"13144440003","registerDate":"20190425","status":"02","upgradeDate":"20190507","verifyDate":"20190425"},{"idName":"苏铭添","merchId":"10000085","merchLevel":2,"merchName":"苏铭添","phone":"13144440002","registerDate":"20190425","status":"02","verifyDate":"20190425"},{"idName":"农本勇","merchId":"10000081","merchLevel":2,"merchName":"农本勇","phone":"13144440080","registerDate":"20190418","status":"02","verifyDate":"20190418"}]
         */

        private String type;
        private List<InfoBean> info;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public List<InfoBean> getInfo() {
            return info;
        }

        public void setInfo(List<InfoBean> info) {
            this.info = info;
        }

        public static class InfoBean implements Serializable {
            /**
             * merchId : 10000105
             * merchLevel : 1
             * merchName : 18666385288
             * phone : 18666385288
             * registerDate : 20190515
             * status : 03
             * idName : 农本勇
             * verifyDate : 20190425
             * upgradeDate : 20190507
             */

            private String merchId;
            private int merchLevel;
            private String merchName;
            private String phone;
            private String registerDate;
            private String status;
            private String idName;
            private String verifyDate;
            private String upgradeDate;

            public String getMerchId() {
                return merchId;
            }

            public void setMerchId(String merchId) {
                this.merchId = merchId;
            }

            public int getMerchLevel() {
                return merchLevel;
            }

            public void setMerchLevel(int merchLevel) {
                this.merchLevel = merchLevel;
            }

            public String getMerchName() {
                return merchName;
            }

            public void setMerchName(String merchName) {
                this.merchName = merchName;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getRegisterDate() {
                return registerDate;
            }

            public void setRegisterDate(String registerDate) {
                this.registerDate = registerDate;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getIdName() {
                return idName;
            }

            public void setIdName(String idName) {
                this.idName = idName;
            }

            public String getVerifyDate() {
                return verifyDate;
            }

            public void setVerifyDate(String verifyDate) {
                this.verifyDate = verifyDate;
            }

            public String getUpgradeDate() {
                return upgradeDate;
            }

            public void setUpgradeDate(String upgradeDate) {
                this.upgradeDate = upgradeDate;
            }
        }
    }
}
