package com.yzf.king.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.yzf.king.R;
import com.yzf.king.model.servicesmodels.GetMachinApplyInfoDtlResult;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.base.SimpleRecAdapter;
import cn.droidlover.xdroidmvp.kit.KnifeKit;

/**
 * ClaseName：WaitShopAdapter
 * Description：
 * Author：chocozhao
 * QQ: 1027313530
 * Createtime：2019/7/13 11:54
 * Modified By：
 * Fixtime：2019/7/13 11:54
 * FixDescription：
 **/

public class WaitShopAdapter extends SimpleRecAdapter<GetMachinApplyInfoDtlResult.DataBean.TermInfoListBean, WaitShopAdapter.ViewHolder> {

    public static final int TAG_VIEW = 0;



    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.adapter_dtl_modelname_tv)
        TextView adapterDtlModelnameTv;
        @BindView(R.id.adapter_dtl_sn_tv)
        TextView adapterDtlSnTv;
        @BindView(R.id.adapter_dtl_activate_tv)
        TextView adapterDtlActivateTv;
        @BindView(R.id.adapter_dtl_term_tv)
        TextView adapterDtlTermTv;
        public ViewHolder(View view) {
            super(view);
            KnifeKit.bind(this, view);
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.adapter_wait_shop;
    }

    public WaitShopAdapter(Context context) {
        super(context);
    }

    @Override
    public ViewHolder newViewHolder(View view) {
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int i) {
        final GetMachinApplyInfoDtlResult.DataBean.TermInfoListBean ListBean = (GetMachinApplyInfoDtlResult.DataBean.TermInfoListBean) this.data.get(i);
        viewHolder.adapterDtlModelnameTv.setText(ListBean.getModelName());
        viewHolder.adapterDtlSnTv.setText(ListBean.getTermSn());
        viewHolder.adapterDtlActivateTv.setText(ListBean.getActivCode());
        viewHolder.adapterDtlTermTv.setText(ListBean.getTermId());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (WaitShopAdapter.this.getRecItemClick() != null) {
                    WaitShopAdapter.this.getRecItemClick().onItemClick(i, ListBean, 0, viewHolder);
                }
            }
        });
    }

}
