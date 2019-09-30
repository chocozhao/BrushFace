package com.yzf.king.model.servicesmodels;


import com.yzf.king.model.BaseModel;

/**
 * ClaseName：GetInstallResult
 * Description：
 * Author：chocozhao
 * QQ: 1027313530
 * Createtime：2019/7/22 12:01
 * Modified By：
 * Fixtime：2019/7/22 12:01
 * FixDescription：
 **/

public class GetInstallResult extends BaseModel {


    /**
     * code : 400
     * data : null
     * message : 终端编号为[null]的设备没有完成交易测试，请先完成交易测试再确认
     */

    private int code;
    private Object data;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
