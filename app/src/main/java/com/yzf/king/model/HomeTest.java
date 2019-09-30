package com.yzf.king.model;


/**
 * ClaseName：HomeTest
 * Description：
 * Author：chocozhao
 * QQ: 1027313530
 * Createtime：2019/7/7 14:11
 * Modified By：
 * Fixtime：2019/7/7 14:11
 * FixDescription：
 **/

public class HomeTest {
    private String title;
    private String context;
    private String amount;
    private int image;
    public boolean isSelect;

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

}
