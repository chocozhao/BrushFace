package com.yzf.king.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yzf.king.R;
import com.yzf.king.kit.AppTools;
import com.yzf.king.model.servicesmodels.GetTransDevicesResult;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.base.SimpleRecAdapter;
import cn.droidlover.xdroidmvp.imageloader.ILFactory;
import cn.droidlover.xdroidmvp.kit.KnifeKit;

/**
 * ClaseName：OrderAdapter
 * Description：
 * Author：chocozhao
 * QQ: 1027313530
 * Createtime：2019/7/9 13:48
 * Modified By：
 * Fixtime：2019/7/9 13:48
 * FixDescription：
 **/

public class OrderAdapter extends SimpleRecAdapter<GetTransDevicesResult.DataBean, OrderAdapter.ViewHolder> {

    public static final int TAG_VIEW = 0;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.adapter_order_date_tv)
        TextView adapterOrderDateTv;
        @BindView(R.id.adapter_order_status_tv)
        TextView adapterOrderStatusTv;
        @BindView(R.id.adapter_order_hear_iv)
        ImageView adapterOrderHearIv;
        @BindView(R.id.adaper_order_title_tv)
        TextView adaperOrderTitleTv;
        @BindView(R.id.adapter_order_package_tv)
        TextView adapterOrderPackageTv;
        @BindView(R.id.adapter_order_count_tv)
        TextView adapterOrderCountTv;
        @BindView(R.id.adapter_order_amount_tv)
        TextView adapterOrderAmountTv;


        public ViewHolder(View view) {
            super(view);
            KnifeKit.bind(this, view);
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.adapter_order;
    }

    public OrderAdapter(Context context) {
        super(context);
    }

    @Override
    public ViewHolder newViewHolder(View view) {
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int i) {
        final GetTransDevicesResult.DataBean dataBean = (GetTransDevicesResult.DataBean) this.data.get(i);
        viewHolder.adaperOrderTitleTv.setText(dataBean.getGoodsName());
        viewHolder.adapterOrderAmountTv.setText("￥" + AppTools.formatF2Y(dataBean.getOrderAmt()));

        if (dataBean.getLogoUrl() != null) {
            ILFactory.getLoader().loadImage(dataBean.getLogoUrl(), viewHolder.adapterOrderHearIv);
        } else {
            ILFactory.getLoader().loadImage(getImgId("device"), viewHolder.adapterOrderHearIv);
        }
        if (dataBean.getGoodsId() == 3 || dataBean.getGoodsId() == 4) {
            viewHolder.adapterOrderCountTv.setText("总数：X"+dataBean.getTotalNum());
        } else {
            if (dataBean.getGiveNum() == 0) {
                viewHolder.adapterOrderCountTv.setText("数量：X" + dataBean.getOrderNum());
            } else {
                viewHolder.adapterOrderCountTv.setText("数量：X" + dataBean.getOrderNum() + "送" + dataBean.getGiveNum());
            }
            viewHolder.adapterOrderPackageTv.setText(dataBean.getMealName());
        }
        switch (dataBean.getStatus()) {
            case 0:
                viewHolder.adapterOrderStatusTv.setText("待付款");
                break;
            case 1:
                viewHolder.adapterOrderStatusTv.setText("已付款");
                break;
//            case 2:
//                viewHolder.adapterOrderStatusTv.setText("已完成");
//                break;
//            case 3:
//                viewHolder.adapterOrderStatusTv.setText("订单失效");
//                break;
            default:
                break;
        }
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (OrderAdapter.this.getRecItemClick() != null) {
                    OrderAdapter.this.getRecItemClick().onItemClick(i, dataBean, TAG_VIEW, viewHolder);
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
