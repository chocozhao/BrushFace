package com.yzf.king.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yzf.king.R;
import com.yzf.king.model.servicesmodels.GetShopInfoDtlResults;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.base.SimpleRecAdapter;
import cn.droidlover.xdroidmvp.kit.KnifeKit;

/**
 * ClaseName：ShopManageDtlAdapter
 * Description：
 * Author：chocozhao
 * QQ: 1027313530
 * Createtime：2019/8/27 20:14
 * Modified By：
 * Fixtime：2019/8/27 20:14
 * FixDescription：
 **/

public class ShopManageDtlAdapter extends SimpleRecAdapter<GetShopInfoDtlResults.DataBean.SubMerchListBean, ShopManageDtlAdapter.ViewHolder> {

    public static final int TAG_VIEW = 0;



    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.adapter_shop_sign_dtl_iv)
        ImageView adapterShopSignDtlIv;
        @BindView(R.id.adapter_shop_sign_dtl_name)
        TextView adapterShopSignDtlName;
        @BindView(R.id.adapter_shop_sign_dtl_status)
        TextView adapterShopSignDtlStatus;
        @BindView(R.id.adapter_shop_sign_dtl_remarks)
        TextView adapterShopSignDtlRemarks;

        public ViewHolder(View view) {
            super(view);
            KnifeKit.bind(this, view);
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.adapter_shop_manage_dtl;
    }

    public ShopManageDtlAdapter(Context context) {
        super(context);
    }

    @Override
    public ViewHolder newViewHolder(View view) {
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int i) {
        final GetShopInfoDtlResults.DataBean.SubMerchListBean listBean = (GetShopInfoDtlResults.DataBean.SubMerchListBean) this.data.get(i);
        if ("ALIPAY".equals(listBean.getChannelCode())) {
            viewHolder.adapterShopSignDtlName.setText("支付宝签约");
            viewHolder.adapterShopSignDtlIv.setImageResource(R.mipmap.trans_zfb);
            viewHolder.adapterShopSignDtlRemarks.setText(listBean.getRemark());
            viewHolder.adapterShopSignDtlRemarks.setTextColor(context.getResources().getColor(R.color.btn_yellow_dark));
            switch (listBean.getOrderStatus()) {
                case "00":
                    viewHolder.adapterShopSignDtlStatus.setText("未签约");
                    break;
                case "01":
                    viewHolder.adapterShopSignDtlStatus.setText("签约中");
                    break;
                case "02":
                    viewHolder.adapterShopSignDtlStatus.setText("签约拒绝");
                    break;
                case "03":
                    viewHolder.adapterShopSignDtlStatus.setText("待开通");
                    break;
                case "04":
                    viewHolder.adapterShopSignDtlStatus.setText("待授权");
                    viewHolder.adapterShopSignDtlStatus.setTextColor(context.getResources().getColor(R.color.btn_blue));
                    break;
                case "05":
                    viewHolder.adapterShopSignDtlStatus.setText("完成");
                    break;
                case "09":
                    viewHolder.adapterShopSignDtlStatus.setText("即将上线");
                    break;
                default:
                    viewHolder.adapterShopSignDtlStatus.setText("未知状态");
                    break;
            }
        } else if ("WXPAY".equals(listBean.getChannelCode())) {
            viewHolder.adapterShopSignDtlName.setText("微信签约");
            viewHolder.adapterShopSignDtlIv.setImageResource(R.mipmap.trans_wx);
            viewHolder.adapterShopSignDtlRemarks.setText(listBean.getRemark());
            viewHolder.adapterShopSignDtlRemarks.setTextColor(context.getResources().getColor(R.color.btn_yellow_dark));
            switch (listBean.getOrderStatus()) {
                case "00":
                    viewHolder.adapterShopSignDtlStatus.setText("未签约");
                    break;
                case "01":
                    viewHolder.adapterShopSignDtlStatus.setText("签约中");
                    break;
                case "02":
                    viewHolder.adapterShopSignDtlStatus.setText("签约拒绝");
                    break;
                case "03":
                    viewHolder.adapterShopSignDtlStatus.setText("待开通");
                    break;
                case "04":
                    viewHolder.adapterShopSignDtlStatus.setText("待授权");
                    viewHolder.adapterShopSignDtlStatus.setTextColor(context.getResources().getColor(R.color.btn_blue));
                    break;
                case "05":
                    viewHolder.adapterShopSignDtlStatus.setText("完成");
                    break;
                case "09":
                    viewHolder.adapterShopSignDtlStatus.setText("即将上线");
                    break;
                default:
                    viewHolder.adapterShopSignDtlStatus.setText("未知状态");
                    break;
            }
        } else if ("UNIONPAY".equals(listBean.getChannelCode())) {
            viewHolder.adapterShopSignDtlName.setText("银联签约");
            viewHolder.adapterShopSignDtlIv.setImageResource(R.mipmap.trans_yl);
            viewHolder.adapterShopSignDtlRemarks.setText(listBean.getRemark());
            viewHolder.adapterShopSignDtlRemarks.setTextColor(context.getResources().getColor(R.color.btn_yellow_dark));
            switch (listBean.getOrderStatus()) {
                case "00":
                    viewHolder.adapterShopSignDtlStatus.setText("未签约");
                    break;
                case "01":
                    viewHolder.adapterShopSignDtlStatus.setText("签约中");
                    break;
                case "02":
                    viewHolder.adapterShopSignDtlStatus.setText("签约拒绝");
                    break;
                case "03":
                    viewHolder.adapterShopSignDtlStatus.setText("待开通");
                    break;
                case "04":
                    viewHolder.adapterShopSignDtlStatus.setText("待授权");
                    viewHolder.adapterShopSignDtlStatus.setTextColor(context.getResources().getColor(R.color.btn_blue));
                    break;
                case "05":
                    viewHolder.adapterShopSignDtlStatus.setText("完成");
                    break;
                case "09":
                    viewHolder.adapterShopSignDtlStatus.setText("即将上线");
                    break;
                default:
                    viewHolder.adapterShopSignDtlStatus.setText("未知状态");
                    break;
            }
        } else if ("HKPAY".equals(listBean.getChannelCode())) {
            viewHolder.adapterShopSignDtlName.setText("间联签约");
            viewHolder.adapterShopSignDtlIv.setImageResource(R.mipmap.trans_hkpay);
            viewHolder.adapterShopSignDtlRemarks.setText(listBean.getRemark());
            viewHolder.adapterShopSignDtlRemarks.setTextColor(context.getResources().getColor(R.color.btn_yellow_dark));
            switch (listBean.getOrderStatus()) {
                case "00":
                    viewHolder.adapterShopSignDtlStatus.setText("未签约");
                    break;
                case "01":
                    viewHolder.adapterShopSignDtlStatus.setText("签约中");
                    break;
                case "02":
                    viewHolder.adapterShopSignDtlStatus.setText("签约拒绝");
                    break;
                case "03":
                    viewHolder.adapterShopSignDtlStatus.setText("待开通");
                    break;
                case "04":
                    viewHolder.adapterShopSignDtlStatus.setText("待授权");
                    viewHolder.adapterShopSignDtlStatus.setTextColor(context.getResources().getColor(R.color.btn_blue));
                    break;
                case "05":
                    viewHolder.adapterShopSignDtlStatus.setText("完成");
                    break;
                case "09":
                    viewHolder.adapterShopSignDtlStatus.setText("即将上线");
                    break;
                default:
                    viewHolder.adapterShopSignDtlStatus.setText("未知状态");
                    break;
            }
        } else {

        }
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ShopManageDtlAdapter.this.getRecItemClick() != null) {
                    ShopManageDtlAdapter.this.getRecItemClick().onItemClick(i, listBean, 0, viewHolder);
                }
            }
        });
    }
}

