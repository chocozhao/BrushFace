package com.yzf.king.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yzf.king.R;
import com.yzf.king.kit.AppTools;
import com.yzf.king.model.servicesmodels.GetBenefitDtlResult;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.base.SimpleRecAdapter;
import cn.droidlover.xdroidmvp.kit.Kits;
import cn.droidlover.xdroidmvp.kit.KnifeKit;

public class TransDetailAdapter extends SimpleRecAdapter<GetBenefitDtlResult.DataBean.DataDtlBean, TransDetailAdapter.ViewHolder> {
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

    @Override
    public int getLayoutId() {
        return R.layout.adapter_trans_detail;
    }

    public TransDetailAdapter(Context context) {
        super(context);
    }

    @Override
    public ViewHolder newViewHolder(View view) {
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int i) {
        final GetBenefitDtlResult.DataBean.DataDtlBean dataBean = (GetBenefitDtlResult.DataBean.DataDtlBean) this.data.get(i);
//        viewHolder.adapterTeamIv.setImageDrawable(this.context.getResources().getDrawable(homeSource.getImgRes()));
        viewHolder.adapterTransdtlRemarkTv.setText(AppTools.formatL2Y(dataBean.getBenefitAmt()) + "元");
        viewHolder.adapterTransdtlTimeTv.setText(Kits.Date.getYmdhms(dataBean.getInsertTime()));
        viewHolder.adapterTransdtlTypeTv.setText(dataBean.getMerchName() + "为您产生了一笔收益");
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TransDetailAdapter.this.getRecItemClick() != null) {
                    TransDetailAdapter.this.getRecItemClick().onItemClick(i, dataBean, 0, viewHolder);
                }
            }
        });
//        if ("01".equals(dataBean.getProcStatus())) {
//            viewHolder.adapterTransdtlRemarkTv.setTextColor(context.getResources().getColor(R.color.text_6));
//        } else {
//            viewHolder.adapterTransdtlRemarkTv.setTextColor(context.getResources().getColor(R.color.text_yellow));
//        }

        switch (dataBean.getBenefitType()) {
            case "22":
                //推广补贴
                viewHolder.adapterTransdtlIv.setImageResource(R.mipmap.promition_subsidy);
                break;
                //团购奖励
            case "44":
                viewHolder.adapterTransdtlIv.setImageResource(R.mipmap.icon_group_buy);
                break;
                //大礼包奖励
            case "45":
                viewHolder.adapterTransdtlIv.setImageResource(R.mipmap.icon_gift_package);
                break;
                //收款补贴
            case "01":
            case "02":
            case "42":
                switch (dataBean.getTransType()){
                    case "01":
                        //支付宝扫码
                        viewHolder.adapterTransdtlIv.setImageResource(R.mipmap.trans_zfb);
                        viewHolder.adapterTransdtlTypeTv.setText(dataBean.getMerchName() + "为您产生了一笔扫码收益");
                        break;
                    case "02":
                        //支付宝刷脸
                        viewHolder.adapterTransdtlIv.setImageResource(R.mipmap.trans_zfb);
                        viewHolder.adapterTransdtlTypeTv.setText(dataBean.getMerchName() + "为您产生了一笔刷脸收益");
                        break;
                    case "03":
                        //微信扫码
                        viewHolder.adapterTransdtlIv.setImageResource(R.mipmap.trans_wx);
                        viewHolder.adapterTransdtlTypeTv.setText(dataBean.getMerchName() + "为您产生了一笔扫码收益");
                        break;
                    case "04":
                        //微信刷脸
                        viewHolder.adapterTransdtlIv.setImageResource(R.mipmap.trans_wx);
                        viewHolder.adapterTransdtlTypeTv.setText(dataBean.getMerchName() + "为您产生了一笔刷脸收益");
                        break;
                    default:
                        break;
                }
                break;
            case "30":
            case "31":
            case "32":
            case "33":
            case "41":
                //设备补贴
                viewHolder.adapterTransdtlIv.setImageResource(R.mipmap.home_adapter_mydevice);
                break;
            default:
                viewHolder.adapterTransdtlIv.setImageResource(R.mipmap.logo);
                break;
        }

    }
}