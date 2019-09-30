package com.yzf.king.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.yzf.king.R;
import com.yzf.king.kit.AppTools;
import com.yzf.king.model.servicesmodels.GetOrdersDaily;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.base.SimpleRecAdapter;
import cn.droidlover.xdroidmvp.kit.KnifeKit;

/**
 * ClaseName：TransDeviceAdapter
 * Description：
 * Author：chocozhao
 * QQ: 1027313530
 * Createtime：2019/8/22 21:16
 * Modified By：
 * Fixtime：2019/8/22 21:16
 * FixDescription：
 **/

public class TransDeviceAdapter extends SimpleRecAdapter<GetOrdersDaily.DataBean.DataDtlBean, TransDeviceAdapter.ViewHolder> {
    public static final int TAG_VIEW = 0;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.adapter_profit_date_tv)
        TextView adapterProfitDateTv;
        @BindView(R.id.adapter_profit_amt_tv)
        TextView adapterProfitAmtTv;

        public ViewHolder(View view) {
            super(view);
            KnifeKit.bind(this, view);
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.adapter_profit;
    }

    public TransDeviceAdapter(Context context) {
        super(context);
    }

    @Override
    public TransDeviceAdapter.ViewHolder newViewHolder(View view) {
        return new TransDeviceAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final TransDeviceAdapter.ViewHolder viewHolder, final int i) {
        final GetOrdersDaily.DataBean.DataDtlBean dataBean = (GetOrdersDaily.DataBean.DataDtlBean) this.data.get(i);
        viewHolder.adapterProfitDateTv.setText(dataBean.getDay());
        viewHolder.adapterProfitAmtTv.setText("当日累计交易￥" + AppTools.formatF2Y(dataBean.getTotalAmt()));
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TransDeviceAdapter.this.getRecItemClick() != null) {
                    TransDeviceAdapter.this.getRecItemClick().onItemClick(i, dataBean, TAG_VIEW, viewHolder);
                }
            }
        });
    }
}
