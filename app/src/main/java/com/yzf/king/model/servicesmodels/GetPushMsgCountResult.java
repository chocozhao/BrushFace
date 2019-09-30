package com.yzf.king.model.servicesmodels;


import com.yzf.king.model.BaseModel;

import java.io.Serializable;
import java.util.List;

/**
 * ClaseName：GetPushMsgCountResult
 * Description：
 * Author：
 * QQ:
 * Createtime：2019/4/24 14:00
 * Modified By：
 * Fixtime：2019/4/24 14:00
 * FixDescription：
 **/

public class GetPushMsgCountResult extends BaseModel {


    /**
     * code : 200
     * data : [{"number":6,"typeName":"系统消息","hasNews":true,"type":"00"},{"number":0,"typeName":"交易消息","hasNews":false,"type":"01"},{"number":0,"typeName":"规划消息","hasNews":false,"type":"02"},{"number":0,"typeName":"用户消息","hasNews":false,"type":"04"}]
     * message : 获取成功
     */

    private int code;
    private String message;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean  {
        /**
         * number : 6
         * typeName : 系统消息
         * hasNews : true
         * type : 00
         */

        private int number;
        private String typeName;
        private boolean hasNews;
        private String type;

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public String getTypeName() {
            return typeName;
        }

        public void setTypeName(String typeName) {
            this.typeName = typeName;
        }

        public boolean isHasNews() {
            return hasNews;
        }

        public void setHasNews(boolean hasNews) {
            this.hasNews = hasNews;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
