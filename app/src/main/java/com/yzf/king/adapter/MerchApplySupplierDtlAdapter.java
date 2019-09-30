package com.yzf.king.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yzf.king.R;
import com.yzf.king.model.servicesmodels.GetAgentInfoResults;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.base.SimpleRecAdapter;
import cn.droidlover.xdroidmvp.kit.KnifeKit;

/**
 * ClaseName：MerchApplySupplierDtlAdapter
 * Description：
 * Author：chocozhao
 * QQ: 1027313530
 * Createtime：2019/8/27 15:39
 * Modified By：
 * Fixtime：2019/8/27 15:39
 * FixDescription：
 **/

public class MerchApplySupplierDtlAdapter extends SimpleRecAdapter<GetAgentInfoResults.DataBean, MerchApplySupplierDtlAdapter.ViewHolder> {

    public static final int TAG_VIEW = 0;



    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.adapter_merch_apply_supplier_dtl_iv)
        ImageView adapterMerchApplySupplierDtlIv;
        @BindView(R.id.adapter_merch_apply_supplier_dtl_name_tv)
        TextView adapterMerchApplySupplierDtlNameTv;
        @BindView(R.id.adapter_merch_apply_supplier_dtl_phone_tv)
        TextView adapterMerchApplySupplierDtlPhoneTv;
        public ViewHolder(View view) {
            super(view);
            KnifeKit.bind(this, view);
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.adapter_merch_apply_supplier_dtl;
    }

    public MerchApplySupplierDtlAdapter(Context context) {
        super(context);
    }

    @Override
    public ViewHolder newViewHolder(View view) {
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int i) {
        final GetAgentInfoResults.DataBean dataBean = this.data.get(i);
        viewHolder.adapterMerchApplySupplierDtlNameTv.setText(dataBean.getMerchName());
        viewHolder.adapterMerchApplySupplierDtlPhoneTv.setText(dataBean.getPhone());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (MerchApplySupplierDtlAdapter.this.getRecItemClick() != null) {
                    MerchApplySupplierDtlAdapter.this.getRecItemClick().onItemClick(i, dataBean, 0, viewHolder);
                }
            }
        });
    }

}
