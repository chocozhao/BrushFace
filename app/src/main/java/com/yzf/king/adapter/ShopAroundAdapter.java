package com.yzf.king.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yzf.king.R;
import com.yzf.king.model.servicesmodels.GetShopAroundResult;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.base.SimpleRecAdapter;
import cn.droidlover.xdroidmvp.kit.KnifeKit;

/**
 * ClaseName：ShopAroundAdapter
 * Description：
 * Author：chocozhao
 * QQ: 1027313530
 * Createtime：2019/7/29 11:49
 * Modified By：
 * Fixtime：2019/7/29 11:49
 * FixDescription：
 **/

public class ShopAroundAdapter extends SimpleRecAdapter<GetShopAroundResult.DataBean, ShopAroundAdapter.ViewHolder> {

    public static final int TAG_VIEW = 0;


    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.shop_around_iv)
        ImageView shopAroundIv;
        @BindView(R.id.shop_around_title_tv)
        TextView shopAroundTitleTv;
        @BindView(R.id.shop_around_address_tv)
        TextView shopAroundAddressTv;
        @BindView(R.id.shop_around_amount_tv)
        TextView shopAroundAmountTv;
        @BindView(R.id.shop_around_distance_tv)
        TextView shopAroundDistanceTv;
        @BindView(R.id.shop_around_rating_tv)
        TextView shopAroundRatingTv;

        public ViewHolder(View view) {
            super(view);
            KnifeKit.bind(this, view);
        }

    }

    @Override
    public int getLayoutId() {
        return R.layout.adapter_shop_around;
    }

    public ShopAroundAdapter(Context context) {
        super(context);
    }

    @Override
    public ViewHolder newViewHolder(View view) {
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int i) {
        final GetShopAroundResult.DataBean dataBean = (GetShopAroundResult.DataBean) this.data.get(i);
        viewHolder.shopAroundTitleTv.setText(dataBean.getName());
        viewHolder.shopAroundAddressTv.setText(dataBean.getDetail_info().getTag());
        if (dataBean.getDetail_info().getPrice() == null) {
            viewHolder.shopAroundAmountTv.setText("人均：￥");
        } else {
            viewHolder.shopAroundAmountTv.setText("人均：￥" + dataBean.getDetail_info().getPrice());
        }
        if (dataBean.getDetail_info().getDistance() == 0) {
            viewHolder.shopAroundDistanceTv.setVisibility(View.GONE);
        } else {
            viewHolder.shopAroundDistanceTv.setVisibility(View.VISIBLE);
            viewHolder.shopAroundDistanceTv.setText(dataBean.getDetail_info().getDistance() + "m");
        }
        if (dataBean.getDetail_info().getOverall_rating() == null) {
            viewHolder.shopAroundRatingTv.setText("暂无评分");
        } else {
            viewHolder.shopAroundRatingTv.setText("评分："+dataBean.getDetail_info().getOverall_rating());
        }
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ShopAroundAdapter.this.getRecItemClick() != null) {
                    ShopAroundAdapter.this.getRecItemClick().onItemClick(i, dataBean, 0, viewHolder);
                }
            }
        });
    }

}
