package com.yzf.king.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yzf.king.R;
import com.yzf.king.model.servicesmodels.GetTransInfoCountDtlResult;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.base.SimpleRecAdapter;
import cn.droidlover.xdroidmvp.kit.Kits;
import cn.droidlover.xdroidmvp.kit.KnifeKit;

/**
 * ClaseName：TransStatDtlAdapter
 * Description：
 * Author：chocozhao
 * QQ: 1027313530
 * Createtime：2019/5/15 19:47
 * Modified By：
 * Fixtime：2019/5/15 19:47
 * FixDescription：
 **/

public class TransStatDtlAdapter extends SimpleRecAdapter<GetTransInfoCountDtlResult.DataBean, TransStatDtlAdapter.ViewHolder> {
    public static final int TAG_VIEW = 0;



    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.adapter_transdtl_iv)
        ImageView adapterTransdtlIv;
        @BindView(R.id.adapter_transdtl_type_tv)
        TextView adapterTransdtlTypeTv;
        @BindView(R.id.adapter_transdtl_amt_tv)
        TextView adapterTransdtlAmtTv;
        @BindView(R.id.adapter_transdtl_time_tv)
        TextView adapterTransdtlTimeTv;
        @BindView(R.id.adapter_transdtl_remark_tv)
        TextView adapterTransdtlRemarkTv;

        public ViewHolder(View view) {
            super(view);
            KnifeKit.bind(this, view);
        }
    }

    public int getLayoutId() {
        return R.layout.adapter_trans_detail;
    }

    public TransStatDtlAdapter(Context context) {
        super(context);
    }

    public ViewHolder newViewHolder(View view) {
        return new ViewHolder(view);
    }

    public void onBindViewHolder(final ViewHolder viewHolder, final int i) {
        final GetTransInfoCountDtlResult.DataBean dataBean = this.data.get(i);
        viewHolder.adapterTransdtlTypeTv.setText(dataBean.getServicesName());
        viewHolder.adapterTransdtlAmtTv.setText("￥"+dataBean.getTransAmt());
        viewHolder.adapterTransdtlTimeTv.setText(Kits.Date.getYmdhms(dataBean.getInsertTime()));
        viewHolder.adapterTransdtlRemarkTv.setText(dataBean.getRespDesc());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (TransStatDtlAdapter.this.getRecItemClick() != null) {
                    TransStatDtlAdapter.this.getRecItemClick().onItemClick(i, dataBean, TAG_VIEW, viewHolder);
                }
            }
        });

        if ("01".equals(dataBean.getProcStatus())) {
            viewHolder.adapterTransdtlRemarkTv.setTextColor(context.getResources().getColor(R.color.text_6));
        } else {
            viewHolder.adapterTransdtlRemarkTv.setTextColor(context.getResources().getColor(R.color.text_yellow));
        }
        switch (dataBean.getTransType()) {
            case "01":
            case "02":
                viewHolder.adapterTransdtlIv.setImageResource(R.mipmap.trans_icon);//提现
                break;
            case "04":
                viewHolder.adapterTransdtlIv.setImageResource(R.mipmap.trans_zfb);//支付宝
                break;
            case "05":
                viewHolder.adapterTransdtlIv.setImageResource(R.mipmap.trans_wx);//微信
                break;
            case "43":
            case "46":
                viewHolder.adapterTransdtlIv.setImageResource(R.mipmap.trans_upgrade);//升级
                break;
            case "61":
            case "63":
            case "70":
            case "72":
            case "90":
                viewHolder.adapterTransdtlIv.setImageResource(R.mipmap.trans_consume);//消费
                break;
            case "71":
            case "73":
            case "91":
            case "97":
                viewHolder.adapterTransdtlIv.setImageResource(R.mipmap.trans_repayment);//还款
                break;
            case "78":
            case "80":
                viewHolder.adapterTransdtlIv.setImageResource(R.mipmap.trans_tx);//套现
                break;
            default:
                viewHolder.adapterTransdtlIv.setImageResource(R.mipmap.trans_icon);
                break;
        }
    }
}
