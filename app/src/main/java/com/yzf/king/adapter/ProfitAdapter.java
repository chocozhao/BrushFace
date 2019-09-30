package com.yzf.king.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.yzf.king.R;
import com.yzf.king.model.servicesmodels.GetProfitResult;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.base.SimpleRecAdapter;
import cn.droidlover.xdroidmvp.kit.KnifeKit;

public class ProfitAdapter extends SimpleRecAdapter<GetProfitResult.DataBean.DataDtlBean, ProfitAdapter.ViewHolder> {
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

    public int getLayoutId() {
        return R.layout.adapter_profit;
    }

    public ProfitAdapter(Context context) {
        super(context);
    }

    public ViewHolder newViewHolder(View view) {
        return new ViewHolder(view);
    }

    public void onBindViewHolder(final ViewHolder viewHolder, final int i) {
        final GetProfitResult.DataBean.DataDtlBean dataBean = (GetProfitResult.DataBean.DataDtlBean) this.data.get(i);
        viewHolder.adapterProfitDateTv.setText(dataBean.getSettDate());
        viewHolder.adapterProfitAmtTv.setText("当日分润￥" + dataBean.getProfitAmt());
        viewHolder.itemView.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (ProfitAdapter.this.getRecItemClick() != null) {
                    ProfitAdapter.this.getRecItemClick().onItemClick(i, dataBean, TAG_VIEW, viewHolder);
                }
            }
        });
    }
}