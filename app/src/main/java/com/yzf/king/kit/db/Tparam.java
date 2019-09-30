package com.yzf.king.kit.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Unique;

/**
 * ClaseName：Tparam
 * Description：param表
 * Author：JensenWei
 * QQ: 2188307188
 * Createtime：2017/6/26 9:38
 * Modified By：
 * Fixtime：2017/6/26 9:38
 * FixDescription：
 */

@Entity
public class Tparam {
    @Unique
    private String key;
    private String value;

    @Generated(hash = 196291744)
    public Tparam(String key, String value) {
        this.key = key;
        this.value = value;
    }

    @Generated(hash = 978573506)
    public Tparam() {
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
