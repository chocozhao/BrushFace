package com.yzf.king.adapter;


import com.zaihuishou.expandablerecycleradapter.model.ExpandableListItem;

import java.util.List;

/**
 * ClaseName：BillResult
 * Description：
 * Author：chocozhao
 * QQ: 1027313530
 * Createtime：2019/6/12 13:59
 * Modified By：
 * Fixtime：2019/6/12 13:59
 * FixDescription：
 **/

public class BillResult implements ExpandableListItem {

    public boolean mExpanded = false;
    public double totalAmt;
    public String month;
    public String days;
    public List<BillDtlResult> mBillDtlResults;

    @Override
    public List<?> getChildItemList() {
        return mBillDtlResults;
    }

    @Override
    public boolean isExpanded() {
        return mExpanded;
    }

    @Override
    public void setExpanded(boolean isExpanded) {
        mExpanded = isExpanded;
    }

}