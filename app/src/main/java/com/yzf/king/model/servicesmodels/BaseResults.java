package com.yzf.king.model.servicesmodels;

import com.yzf.king.model.BaseModel;

import java.io.Serializable;

/**
 * ClaseName：BaseResults
 * Description：
 * Author：JensenWei
 * QQ: 2188307188
 * Createtime：2017/2/25 15:20
 * Modified By：
 * Fixtime：2017/2/25 15:20
 * FixDescription：
 */

public class BaseResults extends BaseModel implements Serializable {

    /**
     * respCode : 00
     * respMsg : 验证码发送成功
     * data : null
     */

    private int code;
    private String message;
    private Object data;

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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
