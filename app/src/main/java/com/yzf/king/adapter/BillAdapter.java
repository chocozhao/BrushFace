package com.yzf.king.adapter;


import android.animation.ObjectAnimator;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yzf.king.R;
import com.zaihuishou.expandablerecycleradapter.viewholder.AbstractExpandableAdapterItem;

/**
 * ClaseName：BillAdapter
 * Description：
 * Author：chocozhao
 * QQ: 1027313530
 * Createtime：2019/6/6 14:44
 * Modified By：
 * Fixtime：2019/6/6 14:44
 * FixDescription：
 **/

public class BillAdapter extends AbstractExpandableAdapterItem {

    TextView adapterBillMonthTv;
    TextView adapterBillTransdateTv;
    TextView adapterBillTotalamtTv;
    ImageView adapterBillIv;

    BillResult mBillResult;

    @Override
    public int getLayoutResId() {
        return R.layout.adapter_bill;
    }

    @Override
    public void onBindViews(final View root) {
        /**
         * control item expand and unexpand
         */
        root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doExpandOrUnexpand();
            }
        });
        adapterBillMonthTv = (TextView) root.findViewById(R.id.adapter_bill_month_tv);
        adapterBillTransdateTv = (TextView) root.findViewById(R.id.adapter_bill_transdate_tv);
        adapterBillTotalamtTv = (TextView) root.findViewById(R.id.adapter_bill_totalamt_tv);
        adapterBillIv = (ImageView) root.findViewById(R.id.adapter_bill_iv);
    }

    @Override
    public void onExpansionToggled(boolean expanded) {
        float start, target;
        if (expanded) {
            start = 0f;
            target = 180f;
        } else {
            start = 180f;
            target = 0f;
        }
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(adapterBillIv, View.ROTATION, start, target);
        objectAnimator.setDuration(300);
        objectAnimator.start();
    }

    @Override
    public void onSetViews() {
        adapterBillIv.setImageResource(0);
        adapterBillIv.setImageResource(R.mipmap.arrow_b);
    }

    @Override
    public void onUpdateViews(Object model, int position) {
        super.onUpdateViews(model, position);
        onSetViews();
        onExpansionToggled(getExpandableListItem().isExpanded());
        mBillResult = (BillResult) model;
        adapterBillMonthTv.setText(mBillResult.month);
        adapterBillTransdateTv.setText(mBillResult.days);
        adapterBillTotalamtTv.setText(String.valueOf(mBillResult.totalAmt));
    }
}
