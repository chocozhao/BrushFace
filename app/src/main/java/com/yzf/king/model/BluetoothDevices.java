package com.yzf.king.model;

/**
 * ClaseName：BluetoothDevices
 * Description：
 * Author：JensenWei
 * QQ: 2188307188
 * Createtime：2019/3/7 10:33
 * Modified By：
 * Fixtime：2019/3/7 10:33
 * FixDescription：
 */
public class BluetoothDevices {
    private String name;
    private String address;
    private Object Data;
    private boolean check;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Object getData() {
        return Data;
    }

    public void setData(Object data) {
        Data = data;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }
}
