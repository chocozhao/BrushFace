package com.yzf.king.model;

import java.io.Serializable;

/**
 * ClaseName：SwipCardModel
 * Description：
 * Author：JensenWei
 * QQ: 2188307188
 * Createtime：2019/3/8 14:26
 * Modified By：
 * Fixtime：2019/3/8 14:26
 * FixDescription：
 */
public class SwipCardModel implements Serializable {

    /**
     * Pinblock : ffffffffffffffff
     * Downgrad : 0
     * ExpireDate : 2706
     * Amount : 201.00
     * SzEntryMode : 072
     * Encrytrack3 :
     * MAC : d856a769568d3233
     * Encrytrack2 : f6ce3c0fc3e9cb6569d3644df94d92505096bf5dffe13ab8
     * Track55 : 9f260839d4ae8deda6e3fb9f2701809f101307020103a00000010a010000000000f97300209f3704f5a4f0839f3602004d950500000000009a031903089c01009f02060000000201005f2a02015682027c009f1a0201569f03060000000000009f3303e0e1c89f34033f00009f3501229f1e08309f260839d4ae8deda6e3fb9f2701809f101307020103a00000010a0100
     * PanSeqNo : 000
     * Tag59 :
     * CardType : 1
     * PAN : 6258362101899672
     */

    private String Pinblock;
    private String Downgrad;
    private String ExpireDate;
    private String Amount;
    private String SzEntryMode;
    private String Encrytrack3;
    private String MAC;
    private String Encrytrack2;
    private String Track55;
    private String PanSeqNo;
    private String Tag59;
    private String CardType;
    private String PAN;

    public String getPinblock() {
        return Pinblock;
    }

    public void setPinblock(String Pinblock) {
        this.Pinblock = Pinblock;
    }

    public String getDowngrad() {
        return Downgrad;
    }

    public void setDowngrad(String Downgrad) {
        this.Downgrad = Downgrad;
    }

    public String getExpireDate() {
        return ExpireDate;
    }

    public void setExpireDate(String ExpireDate) {
        this.ExpireDate = ExpireDate;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String Amount) {
        this.Amount = Amount;
    }

    public String getSzEntryMode() {
        return SzEntryMode;
    }

    public void setSzEntryMode(String SzEntryMode) {
        this.SzEntryMode = SzEntryMode;
    }

    public String getEncrytrack3() {
        return Encrytrack3;
    }

    public void setEncrytrack3(String Encrytrack3) {
        this.Encrytrack3 = Encrytrack3;
    }

    public String getMAC() {
        return MAC;
    }

    public void setMAC(String MAC) {
        this.MAC = MAC;
    }

    public String getEncrytrack2() {
        return Encrytrack2;
    }

    public void setEncrytrack2(String Encrytrack2) {
        this.Encrytrack2 = Encrytrack2;
    }

    public String getTrack55() {
        return Track55;
    }

    public void setTrack55(String Track55) {
        this.Track55 = Track55;
    }

    public String getPanSeqNo() {
        return PanSeqNo;
    }

    public void setPanSeqNo(String PanSeqNo) {
        this.PanSeqNo = PanSeqNo;
    }

    public String getTag59() {
        return Tag59;
    }

    public void setTag59(String Tag59) {
        this.Tag59 = Tag59;
    }

    public String getCardType() {
        return CardType;
    }

    public void setCardType(String CardType) {
        this.CardType = CardType;
    }

    public String getPAN() {
        return PAN;
    }

    public void setPAN(String PAN) {
        this.PAN = PAN;
    }
}
