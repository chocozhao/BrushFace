package com.yzf.king.kit.utils;

import java.io.Serializable;
import java.util.Map;

/**
 * ClaseName：SerializableMap
 * Description：
 * Author：JensenWei
 * QQ: 2188307188
 * Createtime：2019/3/8 10:59
 * Modified By：
 * Fixtime：2019/3/8 10:59
 * FixDescription：
 */
public class SerializableMap implements Serializable {

    private Map<String, Object> map;

    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }
}
