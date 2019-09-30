package com.yzf.king.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.yzf.king.R;
import com.yzf.king.model.servicesmodels.GetMachinApplyInfoResults;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.base.SimpleRecAdapter;
import cn.droidlover.xdroidmvp.imageloader.ILFactory;
import cn.droidlover.xdroidmvp.kit.Kits;
import cn.droidlover.xdroidmvp.kit.KnifeKit;

/**
 * ClaseName：ShopAdapter
 * Description：
 * Author：chocozhao
 * QQ: 1027313530
 * Createtime：2019/7/7 15:12
 * Modified By：
 * Fixtime：2019/7/7 15:12
 * FixDescription：
 **/

public class ShopAdapter extends SimpleRecAdapter<GetMachinApplyInfoResults.DataBean, ShopAdapter.ViewHolder> {

    public static final int TAG_VIEW = 0;



    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.adapter_shop_title_iv)
        ImageView adapterShopTitleIv;
        @BindView(R.id.adapter_shop_hear_iv)
        ImageView adapterShopHearIv;
        @BindView(R.id.adapter_shop_title_tv)
        TextView adapterShopTitleTv;
        @BindView(R.id.adapter_shop_run_tv)
        TextView adapterShopRunTv;
        @BindView(R.id.adapter_shop_apply_type_tv)
        TextView adapterShopApplyTypeTv;
        @BindView(R.id.adapter_shop_date_tv)
        TextView adapterShopDateTv;
        @BindView(R.id.adapter_shop_address_tv)
        TextView adapterShopAddressTv;
        @BindView(R.id.shop_define_bt)
        Button shopDefineBt;
        public ViewHolder(View view) {
            super(view);
            KnifeKit.bind(this, view);
        }

    }

    @Override
    public int getLayoutId() {
        return R.layout.adapter_shop;
    }

    public ShopAdapter(Context context) {
        super(context);
    }

    @Override
    public ViewHolder newViewHolder(View view) {
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int i) {
        final GetMachinApplyInfoResults.DataBean dataBean = (GetMachinApplyInfoResults.DataBean) this.data.get(i);
        viewHolder.adapterShopTitleTv.setText(dataBean.getShopName());
        viewHolder.adapterShopAddressTv.setText(dataBean.getProvencName() + dataBean.getCityName() + dataBean.getAddress());
        viewHolder.adapterShopDateTv.setText(Kits.Date.getYmdhms(dataBean.getInsertTime()));
        if ("0".equals(dataBean.getApplyType())) {
            viewHolder.adapterShopApplyTypeTv.setText("该商家已通过支付宝官方审核");
        } else if ("1".equals(dataBean.getApplyType())) {
            viewHolder.adapterShopApplyTypeTv.setText("该商家已通过微信官方审核");
        } else if ("2".equals(dataBean.getApplyType())) {
            viewHolder.adapterShopApplyTypeTv.setText("该商家已通过支付宝官方审核");
        }else {
            viewHolder.adapterShopApplyTypeTv.setText("该商家已通过官方审核");
        }
        if (dataBean.getLogoUrl() != null) {
            ILFactory.getLoader().loadCircleImage(dataBean.getLogoUrl(), viewHolder.adapterShopHearIv);
        } else {
            ILFactory.getLoader().loadCircleImage(R.mipmap.logo, viewHolder.adapterShopHearIv);
        }
        viewHolder.shopDefineBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ShopAdapter.this.getRecItemClick() != null) {
                    ShopAdapter.this.getRecItemClick().onItemClick(i, dataBean, 0, viewHolder);
                }
            }
        });
    }


}
