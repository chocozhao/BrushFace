package com.yzf.king.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yzf.king.R;
import com.yzf.king.kit.AppTools;
import com.yzf.king.model.servicesmodels.GetGoodsInfoResults;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.base.SimpleRecAdapter;
import cn.droidlover.xdroidmvp.imageloader.ILFactory;
import cn.droidlover.xdroidmvp.kit.KnifeKit;

/**
 * ClaseName：HomeProductAdapter
 * Description：
 * Author：chocozhao
 * QQ: 1027313530
 * Createtime：2019/7/6 14:41
 * Modified By：
 * Fixtime：2019/7/6 14:41
 * FixDescription：
 **/

public class HomeProductAdapter extends SimpleRecAdapter<GetGoodsInfoResults.DataBean, HomeProductAdapter.ViewHolder> {
    public static final int TAG_VIEW = 0;


    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.home_goods_iv)
        ImageView homeGoodsIv;
        @BindView(R.id.home_goods_title_tv)
        TextView homeGoodsTitleTv;
        @BindView(R.id.home_goods_context_tv)
        TextView homeGoodsContextTv;
        @BindView(R.id.home_goods_amount_tv)
        TextView homeGoodsAmountTv;
        @BindView(R.id.home_goods_buydesc_tv)
        TextView homeGoodsBuydescTv;

        public ViewHolder(View view) {
            super(view);
            KnifeKit.bind(this, view);
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.adapter_home_product;
    }

    public HomeProductAdapter(Context context) {
        super(context);
    }

    @Override
    public ViewHolder newViewHolder(View view) {
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int i) {
        final GetGoodsInfoResults.DataBean dataBean = (GetGoodsInfoResults.DataBean) this.data.get(i);
        viewHolder.homeGoodsTitleTv.setText(dataBean.getGoodsName());
        viewHolder.homeGoodsContextTv.setText(dataBean.getGoodsDesc());
        if (dataBean.getAmt() == null) {
            viewHolder.homeGoodsAmountTv.setVisibility(View.GONE);
        } else {
            viewHolder.homeGoodsAmountTv.setText(dataBean.getAmtDesc());
//            viewHolder.homeGoodsAmountTv.setText("采购￥" + AppTools.formatF2Y(dataBean.getAmt()));
        }
        viewHolder.homeGoodsBuydescTv.setText(dataBean.getBuyDesc());
        if (dataBean.getLogoImgUrl() != null) {
            ILFactory.getLoader().loadImage(dataBean.getLogoImgUrl(), viewHolder.homeGoodsIv);
        } else {
            ILFactory.getLoader().loadImage(getImgId("logo"), viewHolder.homeGoodsIv);
        }
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (HomeProductAdapter.this.getRecItemClick() != null) {
                    HomeProductAdapter.this.getRecItemClick().onItemClick(i, dataBean, 0, viewHolder);
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