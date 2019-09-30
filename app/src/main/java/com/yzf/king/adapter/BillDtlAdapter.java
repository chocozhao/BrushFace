package com.yzf.king.adapter;


import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.yzf.king.R;
import com.zaihuishou.expandablerecycleradapter.viewholder.AbstractAdapterItem;

import butterknife.BindView;

/**
 * ClaseName：BillDtlAdapter
 * Description：
 * Author：chocozhao
 * QQ: 1027313530
 * Createtime：2019/6/12 14:01
 * Modified By：
 * Fixtime：2019/6/12 14:01
 * FixDescription：
 **/

public class BillDtlAdapter extends AbstractAdapterItem {

    @BindView(R.id.adapter_bill_dtl_remark_tv)
    TextView adapterBillDtlRemarkTv;
    @BindView(R.id.adapter_bill_dtl_transdate_tv)
    TextView adapterBillDtlTransdateTv;
    @BindView(R.id.adapter_bill_dtl_transamt_tv)
    TextView adapterBillDtlTransamtTv;

    @Override
    public int getLayoutResId() {
        return R.layout.adapter_bill_dtl;
    }

    @Override
    public void onBindViews(View root) {
        adapterBillDtlRemarkTv = (TextView) root.findViewById(R.id.adapter_bill_dtl_remark_tv);
        adapterBillDtlTransdateTv = (TextView) root.findViewById(R.id.adapter_bill_dtl_transdate_tv);
        adapterBillDtlTransamtTv = (TextView) root.findViewById(R.id.adapter_bill_dtl_transamt_tv);
    }

    @Override
    public void onSetViews() {

    }

    @Override
    public void onUpdateViews(Object model, int position) {
        BillDtlResult billDtlResult = (BillDtlResult) model;
        adapterBillDtlRemarkTv.setText(billDtlResult.remark);
        adapterBillDtlTransdateTv.setText(billDtlResult.transDate);
        if (billDtlResult.transAmt < 0) {
            adapterBillDtlTransamtTv.setText(Html.fromHtml("<font color='#ff0000'>"+String.valueOf(billDtlResult.transAmt)+"</font>"));
        } else {
            adapterBillDtlTransamtTv.setText(Html.fromHtml("<font color='#37d581'>"+String.valueOf(billDtlResult.transAmt)+"</font>"));
        }


    }
}
