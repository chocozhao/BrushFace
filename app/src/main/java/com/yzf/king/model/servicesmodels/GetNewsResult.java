package com.yzf.king.model.servicesmodels;

import com.yzf.king.model.BaseModel;

import java.util.List;

/**
 * ClaseName：GetNewsResult
 * Description：
 * Author：JensenWei
 * QQ: 2188307188
 * Createtime：2019/4/16 19:25
 * Modified By：
 * Fixtime：2019/4/16 19:25
 * FixDescription：
 */
public class GetNewsResult extends BaseModel {

    /**
     * code : 200
     * data : ["陈**辉 (137****8893) 领取分润297元","邹**志 (133****2532) 领取分润147元","秦**嘉 (188****2035) 领取分润12元"]
     * message : 获取成功
     */

    private int code;
    private String message;
    private List<String> data;

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

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }
}
