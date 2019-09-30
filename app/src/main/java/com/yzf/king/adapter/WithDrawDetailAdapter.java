package com.yzf.king.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.yzf.king.R;
import com.yzf.king.kit.AppTools;
import com.yzf.king.model.servicesmodels.GetTranDtlResult;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.base.SimpleRecAdapter;
import cn.droidlover.xdroidmvp.kit.Kits;
import cn.droidlover.xdroidmvp.kit.KnifeKit;

public class WithDrawDetailAdapter extends SimpleRecAdapter<GetTranDtlResult.DataBean, WithDrawDetailAdapter.ViewHolder> {
    public static final int TAG_VIEW = 0;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.adapter_withdraw_view)
        View adapterWithdrawView;
        @BindView(R.id.adapter_withdraw_total_tv)
        TextView adapterWithdrawTotalTv;
        @BindView(R.id.adapter_withdraw_amt_tv)
        TextView adapterWithdrawAmtTv;
        @BindView(R.id.adapter_withdraw_fee_tv)
        TextView adapterWithdrawFeeTv;
        @BindView(R.id.adapter_withdraw_time_tv)
        TextView adapterWithdrawTimeTv;
        @BindView(R.id.adapter_withdraw_remark_tv)
        TextView adapterWithdrawRemarkTv;

        public ViewHolder(View view) {
            super(view);
            KnifeKit.bind(this, view);
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.adapter_withdraw_detail;
    }

    public WithDrawDetailAdapter(Context context) {
        super(context);
    }

    @Override
    public ViewHolder newViewHolder(View view) {
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int i) {
        final GetTranDtlResult.DataBean dataBean = (GetTranDtlResult.DataBean) this.data.get(i);
        viewHolder.adapterWithdrawTotalTv.setText(dataBean.getTransAmt()+"");
        viewHolder.adapterWithdrawFeeTv.setText(dataBean.getMerchFee()+"元");
        viewHolder.adapterWithdrawAmtTv.setText(AppTools.formatAmt(dataBean.getMerchSettAmt())+"元");
        viewHolder.adapterWithdrawTimeTv.setText(Kits.Date.getYmdhms(dataBean.getInsertTime()));
        viewHolder.adapterWithdrawRemarkTv.setText(dataBean.getRespDesc());
        if (dataBean.getStatus() == 1) {
            viewHolder.adapterWithdrawRemarkTv.setTextColor(context.getResources().getColor(R.color.bg_green));
            viewHolder.adapterWithdrawView.setBackgroundColor(context.getResources().getColor(R.color.bg_green));
        } else {
            viewHolder.adapterWithdrawRemarkTv.setTextColor(context.getResources().getColor(R.color.text_yellow_dark));
            viewHolder.adapterWithdrawView.setBackgroundColor(context.getResources().getColor(R.color.text_yellow_dark
            ));
        }

    }
}