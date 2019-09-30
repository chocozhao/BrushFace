package com.yzf.king.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yzf.king.R;
import com.yzf.king.model.servicesmodels.GetMyTermResult;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.base.SimpleRecAdapter;
import cn.droidlover.xdroidmvp.imageloader.ILFactory;
import cn.droidlover.xdroidmvp.kit.Kits;
import cn.droidlover.xdroidmvp.kit.KnifeKit;

/**
 * ClaseName：TransMyDeviceAdapter
 * Description：
 * Author：chocozhao
 * QQ: 1027313530
 * Createtime：2019/8/22 15:43
 * Modified By：
 * Fixtime：2019/8/22 15:43
 * FixDescription：
 **/

public class TransMyDeviceAdapter extends SimpleRecAdapter<GetMyTermResult.DataBean.DataListBean, TransMyDeviceAdapter.ViewHolder> {

    public static final int TAG_VIEW = 0;



    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.adapter_trans_details_date_tv)
        TextView adapterTransDetailsDateTv;
        @BindView(R.id.adapter_trans_details_status_tv)
        TextView adapterTransDetailsStatusTv;
        @BindView(R.id.adapter_trans_details_title_tv)
        TextView adapterTransDetailsTitleTv;
        @BindView(R.id.adapter_trans_details_shop_tv)
        TextView adapterTransDetailsShopTv;
        @BindView(R.id.adapter_trans_details_package_tv)
        TextView adapterTransDetailsPackageTv;
        @BindView(R.id.adapter_trans_details_hear_iv)
        ImageView adapterTransDetailsHearIv;

        public ViewHolder(View view) {
            super(view);
            KnifeKit.bind(this, view);
        }

    }

    @Override
    public int getLayoutId() {
        return R.layout.adapter_trans_details;
    }

    public TransMyDeviceAdapter(Context context) {
        super(context);
    }

    @Override
    public ViewHolder newViewHolder(View view) {
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int i) {
        final GetMyTermResult.DataBean.DataListBean dataBean = (GetMyTermResult.DataBean.DataListBean) this.data.get(i);
        viewHolder.adapterTransDetailsDateTv.setText(Kits.Date.getYmdhms(dataBean.getInsertTime()));
        viewHolder.adapterTransDetailsTitleTv.setText(dataBean.getModelName());
        viewHolder.adapterTransDetailsShopTv.setText(dataBean.getShopName());
        viewHolder.adapterTransDetailsPackageTv.setText(dataBean.getMealName());
        ILFactory.getLoader().loadImage(dataBean.getLogoUrl(), viewHolder.adapterTransDetailsHearIv);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TransMyDeviceAdapter.this.getRecItemClick() != null) {
                    TransMyDeviceAdapter.this.getRecItemClick().onItemClick(i, dataBean, 0, viewHolder);
                }
            }
        });
    }
}
