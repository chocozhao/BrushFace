package com.yzf.king.model.servicesmodels;

import com.yzf.king.model.BaseModel;

import java.io.Serializable;
import java.util.List;

/**
 * ClaseName：AddressResult
 * Description：
 * Author：JensenWei
 * QQ: 2188307188
 * Createtime：2018/12/7 11:24
 * Modified By：
 * Fixtime：2018/12/7 11:24
 * FixDescription：
 */
public class AddressResult extends BaseModel {

    /**
     * code : 200
     * data : [{"add1":"","add2":"","add3":"","address":"广东省广州市番禺区","addressDtl":"招商城市主场4栋\n","addressType":"0","id":2,"insertTime":1543917603000,"merchId":"15902020654","name":"冯志祥","phone":"13189081178","type":"","updateTime":1543990579000,"zipCode":""}]
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

    public static class DataBean implements Serializable {
        /**
         * add1 :
         * add2 :
         * add3 :
         * address : 广东省广州市番禺区
         * addressDtl : 招商城市主场4栋
         * <p>
         * addressType : 0
         * id : 2
         * insertTime : 1543917603000
         * merchId : 15902020654
         * name : 冯志祥
         * phone : 13189081178
         * type :
         * updateTime : 1543990579000
         * zipCode :
         */

        private String add1;
        private String add2;
        private String add3;
        private String address;
        private String addressDtl;
        private String addressType;
        private int id;
        private long insertTime;
        private String merchId;
        private String name;
        private String phone;
        private String type;
        private long updateTime;
        private String zipCode;

        public String getAdd1() {
            return add1;
        }

        public void setAdd1(String add1) {
            this.add1 = add1;
        }

        public String getAdd2() {
            return add2;
        }

        public void setAdd2(String add2) {
            this.add2 = add2;
        }

        public String getAdd3() {
            return add3;
        }

        public void setAdd3(String add3) {
            this.add3 = add3;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getAddressDtl() {
            return addressDtl;
        }

        public void setAddressDtl(String addressDtl) {
            this.addressDtl = addressDtl;
        }

        public String getAddressType() {
            return addressType;
        }

        public void setAddressType(String addressType) {
            this.addressType = addressType;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public long getInsertTime() {
            return insertTime;
        }

        public void setInsertTime(long insertTime) {
            this.insertTime = insertTime;
        }

        public String getMerchId() {
            return merchId;
        }

        public void setMerchId(String merchId) {
            this.merchId = merchId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public long getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(long updateTime) {
            this.updateTime = updateTime;
        }

        public String getZipCode() {
            return zipCode;
        }

        public void setZipCode(String zipCode) {
            this.zipCode = zipCode;
        }
    }
}
