package com.yzf.king.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yzf.king.R;
import com.yzf.king.kit.AppTools;
import com.yzf.king.model.servicesmodels.GetOrdersDtl;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.base.SimpleRecAdapter;
import cn.droidlover.xdroidmvp.kit.Kits;
import cn.droidlover.xdroidmvp.kit.KnifeKit;

/**
 * ClaseName：TransDeviceDtlAdapter
 * Description：
 * Author：chocozhao
 * QQ: 1027313530
 * Createtime：2019/8/22 21:39
 * Modified By：
 * Fixtime：2019/8/22 21:39
 * FixDescription：
 **/

public class TransDeviceDtlAdapter extends SimpleRecAdapter<GetOrdersDtl.DataBean.DataDtlBean, TransDeviceDtlAdapter.ViewHolder> {
    public static final int TAG_VIEW = 0;


    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.adapter_trans_device_dtl_iv)
        ImageView adapterTransDeviceDtlIv;
        @BindView(R.id.adapter_trans_device_dtl_type_tv)
        TextView adapterTransDeviceDtlTypeTv;
        @BindView(R.id.adapter_trans_device_dtl_amt_tv)
        TextView adapterTransDeviceDtlAmtTv;
        @BindView(R.id.adapter_trans_device_dtl_time_tv)
        TextView adapterTransDeviceDtlTimeTv;
        @BindView(R.id.adapter_trans_device_dtl_remark_tv)
        TextView adapterTransDeviceDtlRemarkTv;

        public ViewHolder(View view) {
            super(view);
            KnifeKit.bind(this, view);
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.adapter_trans_device_dtl;
    }

    public TransDeviceDtlAdapter(Context context) {
        super(context);
    }

    @Override
    public ViewHolder newViewHolder(View view) {
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int i) {
        final GetOrdersDtl.DataBean.DataDtlBean dataBean = (GetOrdersDtl.DataBean.DataDtlBean) this.data.get(i);
        viewHolder.adapterTransDeviceDtlTimeTv.setText(Kits.Date.getYmdhms(dataBean.getInsertTime()));
        if ("04".equals(dataBean.getTransType()) ) {
            viewHolder.adapterTransDeviceDtlTypeTv.setText("微信刷脸成功收款一笔"+AppTools.formatF2Y(dataBean.getTransAmt())+"元");
            viewHolder.adapterTransDeviceDtlIv.setImageResource(R.mipmap.trans_wx);
        } else if ("03".equals(dataBean.getTransType())) {
            viewHolder.adapterTransDeviceDtlTypeTv.setText("微信扫码成功收款一笔"+AppTools.formatF2Y(dataBean.getTransAmt())+"元");
            viewHolder.adapterTransDeviceDtlIv.setImageResource(R.mipmap.trans_wx);
        } else if ("02".equals(dataBean.getTransType())) {
            viewHolder.adapterTransDeviceDtlTypeTv.setText("支付宝刷脸成功收款一笔" + AppTools.formatF2Y(dataBean.getTransAmt()) + "元");
            viewHolder.adapterTransDeviceDtlIv.setImageResource(R.mipmap.trans_zfb);
        } else if ("01".equals(dataBean.getTransType())) {
            viewHolder.adapterTransDeviceDtlTypeTv.setText("支付宝扫码成功收款一笔" + AppTools.formatF2Y(dataBean.getTransAmt()) + "元");
            viewHolder.adapterTransDeviceDtlIv.setImageResource(R.mipmap.trans_zfb);
        } else {
            viewHolder.adapterTransDeviceDtlTypeTv.setText("");
            viewHolder.adapterTransDeviceDtlIv.setImageResource(R.mipmap.logo);
        }

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TransDeviceDtlAdapter.this.getRecItemClick() != null) {
                    TransDeviceDtlAdapter.this.getRecItemClick().onItemClick(i, dataBean, TAG_VIEW, viewHolder);
                }
            }
        });
    }
}
