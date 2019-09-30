package com.yzf.king.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.yzf.king.R;
import com.yzf.king.kit.AppTools;
import com.yzf.king.model.servicesmodels.GetProfitDtlResult;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.base.SimpleRecAdapter;
import cn.droidlover.xdroidmvp.kit.Kits;
import cn.droidlover.xdroidmvp.kit.KnifeKit;

public class ProfitDtlAdapter extends SimpleRecAdapter<GetProfitDtlResult.DataBean.DataDtlBean, ProfitDtlAdapter.ViewHolder> {
    public static final int TAG_VIEW = 0;


    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.adapter_profitdtl_name_tv)
        TextView adapterProfitdtlNameTv;
        @BindView(R.id.adapter_profitdtl_id_tv)
        TextView adapterProfitdtlIdTv;
        @BindView(R.id.adapter_profitdtl_amt_tv)
        TextView adapterProfitdtlAmtTv;
        @BindView(R.id.adapter_profitdtl_date_tv)
        TextView adapterProfitdtlDateTv;
        @BindView(R.id.adapter_profitdtl_type_tv)
        TextView adapterProfitdtlTypeTv;

        public ViewHolder(View view) {
            super(view);
            KnifeKit.bind(this, view);
        }
    }

    public int getLayoutId() {
        return R.layout.adapter_profit_dtl;
    }

    public ProfitDtlAdapter(Context context) {
        super(context);
    }

    public ViewHolder newViewHolder(View view) {
        return new ViewHolder(view);
    }

    public void onBindViewHolder(final ViewHolder viewHolder, final int i) {
        final GetProfitDtlResult.DataBean.DataDtlBean dataBean = (GetProfitDtlResult.DataBean.DataDtlBean) this.data.get(i);
        viewHolder.adapterProfitdtlNameTv.setText( AppTools.formatName(dataBean.getMerchName()));
        viewHolder.adapterProfitdtlIdTv.setText( "("+AppTools.formatPhone(dataBean.getMerchId())+")");
        viewHolder.adapterProfitdtlAmtTv.setText(dataBean.getProfitAmt());
        viewHolder.adapterProfitdtlTypeTv.setText(dataBean.getServiceName());
        viewHolder.adapterProfitdtlDateTv.setText(Kits.Date.getYmdhms(dataBean.getInsertTime()));
        viewHolder.itemView.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (ProfitDtlAdapter.this.getRecItemClick() != null) {
                    ProfitDtlAdapter.this.getRecItemClick().onItemClick(i, dataBean, TAG_VIEW, viewHolder);
                }
            }
        });
    }
}