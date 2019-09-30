package com.yzf.king.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yzf.king.R;
import com.yzf.king.kit.AppTools;
import com.yzf.king.model.servicesmodels.GetMachinApplyInfoResults;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.base.SimpleRecAdapter;
import cn.droidlover.xdroidmvp.imageloader.ILFactory;
import cn.droidlover.xdroidmvp.kit.KnifeKit;

/**
 * ClaseName：OrderManageAdapter
 * Description：
 * Author：chocozhao
 * QQ: 1027313530
 * Createtime：2019/7/13 10:56
 * Modified By：
 * Fixtime：2019/7/13 10:56
 * FixDescription：
 **/

public class OrderManageAdapter extends SimpleRecAdapter<GetMachinApplyInfoResults.DataBean, OrderManageAdapter.ViewHolder> {

    public static final int TAG_VIEW = 0;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.order_promotion_hear_iv)
        ImageView orderPromotionHearIv;
        @BindView(R.id.order_promotion_title_tv)
        TextView orderPromotionTitleTv;
        @BindView(R.id.order_promotion_address_tv)
        TextView orderPromotionAddressTv;
        @BindView(R.id.order_promotion_status_tv)
        TextView orderPromotionStatusTv;

        public ViewHolder(View view) {
            super(view);
            KnifeKit.bind(this, view);
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.adapter_order_manage_promotion;
    }

    public OrderManageAdapter(Context context) {
        super(context);
    }

    @Override
    public ViewHolder newViewHolder(View view) {
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int i) {
        final GetMachinApplyInfoResults.DataBean dataBean = (GetMachinApplyInfoResults.DataBean) this.data.get(i);
        if (!AppTools.isEmpty(dataBean.getProvencName()) || !AppTools.isEmpty(dataBean.getCityName()) || !AppTools.isEmpty(dataBean.getAddress())) {
            viewHolder.orderPromotionAddressTv.setText("店铺地址:" + dataBean.getProvencName() + dataBean.getCityName() + dataBean.getAddress());
        } else {
            viewHolder.orderPromotionAddressTv.setText("店铺地址:");
        }
        viewHolder.orderPromotionTitleTv.setText(dataBean.getShopName());
        switch (dataBean.getStatus()) {
            case 0:
                viewHolder.orderPromotionStatusTv.setText("审核中");
                break;
            case 1:
                viewHolder.orderPromotionStatusTv.setText("待授权");
                break;
            case 2:
                viewHolder.orderPromotionStatusTv.setText("待铺货");
                break;
            case 3:
                viewHolder.orderPromotionStatusTv.setText("装机中");
                break;
            case 4:
                viewHolder.orderPromotionStatusTv.setText("已完成");
                break;
            case 5:
                viewHolder.orderPromotionStatusTv.setText("审核失败");
                viewHolder.orderPromotionStatusTv.setTextColor(getColor(R.color.btn_yellow_dark));
                break;
            case 6:
                viewHolder.orderPromotionStatusTv.setText("装机失败");
                viewHolder.orderPromotionStatusTv.setTextColor(getColor(R.color.btn_yellow_dark));
                break;
            case 7:
                viewHolder.orderPromotionStatusTv.setText("订单失效");
                viewHolder.orderPromotionStatusTv.setTextColor(getColor(R.color.btn_yellow_dark));
                break;
            default:
                break;
        }

//        int logo = getImgId(dataBean.getLogoUrl());
        if (!AppTools.isEmpty(dataBean.getLogoUrl())) {
            ILFactory.getLoader().loadCircleImage(dataBean.getLogoUrl(), viewHolder.orderPromotionHearIv);
        } else {
            ILFactory.getLoader().loadCircleImage(getImgId("logo"), viewHolder.orderPromotionHearIv);
        }
//
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (OrderManageAdapter.this.getRecItemClick() != null) {
                    OrderManageAdapter.this.getRecItemClick().onItemClick(i, dataBean, 0, viewHolder);
                }
            }
        });
    }

    private int getImgId(String imgName) {
        int id = -1;
        try {
            id = context.getResources().getIdentifier(imgName, "mipmap", context.getPackageName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }

}
