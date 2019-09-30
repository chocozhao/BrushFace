package com.yzf.king.kit.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * ClaseName：TNotice
 * Description：
 * Author：JensenWei
 * QQ: 2188307188
 * Createtime：2018/8/24 14:05
 * Modified By：
 * Fixtime：2018/8/24 14:05
 * FixDescription：
 */
@Entity
public class TNotice {
    @Id(autoincrement = true)
    private Long id;
    private int amt;
    private String content;
    private String merchId;
    private int noticeId;
    private String orderId;
    private int readFlag;
    private String remark;
    private int showFlag;
    private int status;
    private String time;
    private String title;
    private int type;
    private String url;
    @Generated(hash = 407289245)
    public TNotice(Long id, int amt, String content, String merchId, int noticeId,
            String orderId, int readFlag, String remark, int showFlag, int status,
            String time, String title, int type, String url) {
        this.id = id;
        this.amt = amt;
        this.content = content;
        this.merchId = merchId;
        this.noticeId = noticeId;
        this.orderId = orderId;
        this.readFlag = readFlag;
        this.remark = remark;
        this.showFlag = showFlag;
        this.status = status;
        this.time = time;
        this.title = title;
        this.type = type;
        this.url = url;
    }
    @Generated(hash = 593427470)
    public TNotice() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public int getAmt() {
        return this.amt;
    }
    public void setAmt(int amt) {
        this.amt = amt;
    }
    public String getContent() {
        return this.content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getMerchId() {
        return this.merchId;
    }
    public void setMerchId(String merchId) {
        this.merchId = merchId;
    }
    public int getNoticeId() {
        return this.noticeId;
    }
    public void setNoticeId(int noticeId) {
        this.noticeId = noticeId;
    }
    public String getOrderId() {
        return this.orderId;
    }
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    public int getReadFlag() {
        return this.readFlag;
    }
    public void setReadFlag(int readFlag) {
        this.readFlag = readFlag;
    }
    public String getRemark() {
        return this.remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public int getShowFlag() {
        return this.showFlag;
    }
    public void setShowFlag(int showFlag) {
        this.showFlag = showFlag;
    }
    public int getStatus() {
        return this.status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    public String getTime() {
        return this.time;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public int getType() {
        return this.type;
    }
    public void setType(int type) {
        this.type = type;
    }
    public String getUrl() {
        return this.url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
}
