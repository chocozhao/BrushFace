package com.yzf.king.kit.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * ClaseName：TPusgMsg
 * Description：
 * Author：JensenWei
 * QQ: 2188307188
 * Createtime：2018/5/19 14:08
 * Modified By：
 * Fixtime：2018/5/19 14:08
 * FixDescription：
 */

@Entity
public class TPusgMsg {
    @Id(autoincrement = true)
    private Long id;
    private String merchId;
    private String msgTitle;
    private String msgContent;
    private String msgType;
    private String msgTime;
    private String msgStatus;
    private String orderId;
    private String orderAmt;
    private String orderType;
    private String orderTime;
    private String orderStatus;
    private String orderDesc;

    @Generated(hash = 1463878656)
    public TPusgMsg(Long id, String merchId, String msgTitle, String msgContent,
                    String msgType, String msgTime, String msgStatus, String orderId,
                    String orderAmt, String orderType, String orderTime, String orderStatus,
                    String orderDesc) {
        this.id = id;
        this.merchId = merchId;
        this.msgTitle = msgTitle;
        this.msgContent = msgContent;
        this.msgType = msgType;
        this.msgTime = msgTime;
        this.msgStatus = msgStatus;
        this.orderId = orderId;
        this.orderAmt = orderAmt;
        this.orderType = orderType;
        this.orderTime = orderTime;
        this.orderStatus = orderStatus;
        this.orderDesc = orderDesc;
    }

    @Generated(hash = 280602799)
    public TPusgMsg() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMsgTitle() {
        return this.msgTitle;
    }

    public void setMsgTitle(String msgTitle) {
        this.msgTitle = msgTitle;
    }

    public String getMsgContent() {
        return this.msgContent;
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }

    public String getMsgType() {
        return this.msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getMsgTime() {
        return this.msgTime;
    }

    public void setMsgTime(String msgTime) {
        this.msgTime = msgTime;
    }

    public String getMsgStatus() {
        return this.msgStatus;
    }

    public void setMsgStatus(String msgStatus) {
        this.msgStatus = msgStatus;
    }

    public String getOrderId() {
        return this.orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderAmt() {
        return this.orderAmt;
    }

    public void setOrderAmt(String orderAmt) {
        this.orderAmt = orderAmt;
    }

    public String getOrderType() {
        return this.orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getOrderTime() {
        return this.orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public String getOrderStatus() {
        return this.orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getMerchId() {
        return this.merchId;
    }

    public void setMerchId(String merchId) {
        this.merchId = merchId;
    }

    public String getOrderDesc() {
        return this.orderDesc;
    }

    public void setOrderDesc(String orderDesc) {
        this.orderDesc = orderDesc;
    }

}
