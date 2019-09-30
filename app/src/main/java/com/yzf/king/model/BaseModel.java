package com.yzf.king.model;

import cn.droidlover.xdroidmvp.net.IModel;

/**
 * ClaseName：BaseModel
 * Description：
 * Author：JensenWei
 * QQ: 2188307188
 * Createtime：2017/5/10 18:02
 * Modified By：
 * Fixtime：2017/5/10 18:02
 * FixDescription：
 */
public class BaseModel implements IModel {
    protected boolean error;


    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    @Override
    public boolean isNull() {
        return false;
    }

    @Override
    public boolean isAuthError() {
        return false;
    }

    @Override
    public boolean isBizError() {
        return error;
    }

    @Override
    public String getErrorMsg() {
        return null;
    }
}
