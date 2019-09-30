package com.yzf.king.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.yzf.king.R;
import com.yzf.king.kit.AppTools;
import com.yzf.king.model.servicesmodels.GetBenefitDailyResult;
import com.yzf.king.model.servicesmodels.GetTranDtlResult;
import com.yzf.king.model.servicesmodels.GetTransResult;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.base.SimpleRecAdapter;
import cn.droidlover.xdroidmvp.kit.KnifeKit;

public class TransAdapter extends SimpleRecAdapter<GetBenefitDailyResult.DataBean.DataDtlBean, TransAdapter.ViewHolder> {
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

    public TransAdapter(Context context) {
        super(context);
    }

    @Override
    public ViewHolder newViewHolder(View view) {
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int i) {
        final GetBenefitDailyResult.DataBean.DataDtlBean dataBean = (GetBenefitDailyResult.DataBean.DataDtlBean) this.data.get(i);
        viewHolder.adapterProfitDateTv.setText(dataBean.getDay());
        viewHolder.adapterProfitAmtTv.setText("当日交易￥" + AppTools.formatL2Y(dataBean.getTotalAmt()));
        viewHolder.itemView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TransAdapter.this.getRecItemClick() != null) {
                    TransAdapter.this.getRecItemClick().onItemClick(i, dataBean, TAG_VIEW, viewHolder);
                }
            }
        });
    }
}