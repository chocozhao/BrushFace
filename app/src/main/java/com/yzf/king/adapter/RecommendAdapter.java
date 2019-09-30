package com.yzf.king.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yzf.king.R;
import com.yzf.king.kit.AppTools;
import com.yzf.king.model.servicesmodels.GetRecommendShopResults;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.base.SimpleRecAdapter;
import cn.droidlover.xdroidmvp.imageloader.ILFactory;
import cn.droidlover.xdroidmvp.kit.KnifeKit;

/**
 * ClaseName：RecommendAdapter
 * Description：
 * Author：chocozhao
 * QQ: 1027313530
 * Createtime：2019/7/9 15:12
 * Modified By：
 * Fixtime：2019/7/9 15:12
 * FixDescription：
 **/

public class RecommendAdapter extends SimpleRecAdapter<GetRecommendShopResults.DataBean, RecommendAdapter.ViewHolder> {
    public static final int TAG_VIEW = 0;


    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.recommend_adapter_hear_iv)
        ImageView recommendAdapterHearIv;
        @BindView(R.id.recommend_adapter_status_tv)
        TextView recommendAdapterStatusTv;
        @BindView(R.id.recommend_adapter_title_tv)
        TextView recommendAdapterTitleTv;
        @BindView(R.id.recommend_adapter_address_tv)
        TextView recommendAdapterAddressTv;


        public ViewHolder(View view) {
            super(view);
            KnifeKit.bind(this, view);
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.adapter_recommend;
    }

    public RecommendAdapter(Context context) {
        super(context);
    }

    @Override
    public ViewHolder newViewHolder(View view) {
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int i) {
        final GetRecommendShopResults.DataBean dataBean = (GetRecommendShopResults.DataBean) this.data.get(i);
        viewHolder.recommendAdapterTitleTv.setText(dataBean.getShopName());
        viewHolder.recommendAdapterAddressTv.setText(dataBean.getProvencName() + dataBean.getCityName() + dataBean.getAddress());
        if (!AppTools.isEmpty(dataBean.getLogoUrl())) {
            ILFactory.getLoader().loadCircleImage(dataBean.getLogoUrl(), viewHolder.recommendAdapterHearIv);
        } else {
            ILFactory.getLoader().loadCircleImage(R.mipmap.logo, viewHolder.recommendAdapterHearIv);
        }
        switch (dataBean.getStatus()) {
            case 0:
                viewHolder.recommendAdapterStatusTv.setText("审核中");
                break;
            case 1:
                viewHolder.recommendAdapterStatusTv.setText("待授权");
                break;
            case 2:
                viewHolder.recommendAdapterStatusTv.setText("待铺货");
                break;
            case 3:
                viewHolder.recommendAdapterStatusTv.setText("装机中");
                break;
            case 4:
                viewHolder.recommendAdapterStatusTv.setText("已完成");
                break;
            case 5:
                viewHolder.recommendAdapterStatusTv.setText("审核失败");
                break;
            case 6:
                viewHolder.recommendAdapterStatusTv.setText("装机失败");
                break;
            default:
        }
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (RecommendAdapter.this.getRecItemClick() != null) {
                    RecommendAdapter.this.getRecItemClick().onItemClick(i, dataBean, 0, viewHolder);
                }
            }
        });
    }

}
