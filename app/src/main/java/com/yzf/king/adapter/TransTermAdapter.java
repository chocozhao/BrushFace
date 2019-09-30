package com.yzf.king.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yzf.king.R;
import com.yzf.king.model.servicesmodels.GetTransTermInfoResult;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.base.SimpleRecAdapter;
import cn.droidlover.xdroidmvp.imageloader.ILFactory;
import cn.droidlover.xdroidmvp.kit.Kits;
import cn.droidlover.xdroidmvp.kit.KnifeKit;

/**
 * ClaseName：TransTermAdapter
 * Description：
 * Author：chocozhao
 * QQ: 1027313530
 * Createtime：2019/7/31 18:30
 * Modified By：
 * Fixtime：2019/7/31 18:30
 * FixDescription：
 **/

public class TransTermAdapter  extends SimpleRecAdapter<GetTransTermInfoResult.DataBean, TransTermAdapter.ViewHolder> {

    public static final int TAG_VIEW = 0;

    int goodsId;
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
        @BindView(R.id.adapter_order_add1_tv)
        TextView adapterOrderAdd1Tv;

        public ViewHolder(View view) {
            super(view);
            KnifeKit.bind(this, view);
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.adapter_trans_term;
    }

    public TransTermAdapter(Context context) {
        super(context);
    }

    public TransTermAdapter(Context context,int goodsId) {
        super(context);
        this.goodsId = goodsId;
    }


    @Override
    public TransTermAdapter.ViewHolder newViewHolder(View view) {
        return new TransTermAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final TransTermAdapter.ViewHolder viewHolder, final int i) {
        final GetTransTermInfoResult.DataBean dataBean = (GetTransTermInfoResult.DataBean) this.data.get(i);
        viewHolder.adaperOrderTitleTv.setText(dataBean.getModelName());
        viewHolder.adapterOrderDateTv.setText(Kits.Date.getYmdhms(dataBean.getInsertTime()));

        if (dataBean.getLogoUrl() != null) {
            ILFactory.getLoader().loadImage(dataBean.getLogoUrl(), viewHolder.adapterOrderHearIv);
        } else {
            ILFactory.getLoader().loadImage(getImgId("device"), viewHolder.adapterOrderHearIv);
        }

        if (goodsId == 3 || goodsId == 4) {
            viewHolder.adapterOrderAdd1Tv.setText("总数：X"+dataBean.getAdd1());
            viewHolder.adapterOrderPackageTv.setText("未发货数：X"+dataBean.getAdd2());
        } else {
            viewHolder.adapterOrderPackageTv.setText("套餐："+dataBean.getMealId());
        }
        if ("0".equals(dataBean.getStatus())) {
            viewHolder.adapterOrderStatusTv.setText("未发货");
        } else {
            viewHolder.adapterOrderStatusTv.setText("已发货");
        }
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TransTermAdapter.this.getRecItemClick() != null) {
                    TransTermAdapter.this.getRecItemClick().onItemClick(i, dataBean, TAG_VIEW, viewHolder);
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
