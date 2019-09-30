package com.yzf.king.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.yzf.king.R;
import com.yzf.king.model.servicesmodels.GetCardInfoResult;
import com.yzf.king.model.servicesmodels.GetMachinApplyInfoDtlResult;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.base.SimpleRecAdapter;
import cn.droidlover.xdroidmvp.kit.KnifeKit;

/**
 * ClaseName：InstallAdapter
 * Description：
 * Author：chocozhao
 * QQ: 1027313530
 * Createtime：2019/8/28 09:55
 * Modified By：
 * Fixtime：2019/8/28 09:55
 * FixDescription：
 **/

public class InstallAdapter extends SimpleRecAdapter<GetMachinApplyInfoDtlResult.DataBean.TermInfoListBean, InstallAdapter.ViewHolder> {

    public static final int TAG_VIEW = 0;
    public static final int TAG_DEL = 1;
    public static final int TAG_REPLACE = 2;
    public static final int TAG_ADD = 3;

    private OnMyItemClickListener listener;

    public void setOnMyItemClickListener(OnMyItemClickListener listener) {
        this.listener = listener;

    }

    public interface OnMyItemClickListener {
        void myClick(View v, int pos, GetMachinApplyInfoDtlResult.DataBean.TermInfoListBean item);

        void mLongClick(View v, int pos, GetMachinApplyInfoDtlResult.DataBean.TermInfoListBean item);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.adapter_install_delete_tv)
        TextView adapterInstallDeleteTv;
        @BindView(R.id.adapter_install_replace_tv)
        TextView adapterInstallReplaceTv;
        @BindView(R.id.adapter_install_modelname_tv)
        TextView adapterInstallModelnameTv;
        @BindView(R.id.adapter_install_sn_tv)
        TextView adapterInstallSnTv;
        @BindView(R.id.adapter_install_activate_tv)
        TextView adapterInstallActivateTv;
        @BindView(R.id.adapter_install_term_tv)
        TextView adapterInstallTermTv;
        public ViewHolder(View view) {
            super(view);
            KnifeKit.bind(this, view);
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.adapter_install;
    }

    public InstallAdapter(Context context) {
        super(context);
    }

    @Override
    public ViewHolder newViewHolder(View view) {
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int i) {
        final GetMachinApplyInfoDtlResult.DataBean.TermInfoListBean ListBean = (GetMachinApplyInfoDtlResult.DataBean.TermInfoListBean) this.data.get(i);
        viewHolder.adapterInstallModelnameTv.setText(ListBean.getModelName());
        viewHolder.adapterInstallSnTv.setText(ListBean.getTermSn());
        viewHolder.adapterInstallActivateTv.setText(ListBean.getActivCode());
        viewHolder.adapterInstallTermTv.setText(ListBean.getTermId());

        viewHolder.adapterInstallDeleteTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (InstallAdapter.this.getRecItemClick() != null) {
                    InstallAdapter.this.getRecItemClick().onItemClick(i, ListBean, TAG_DEL, viewHolder);
                }
            }
        });
        viewHolder.adapterInstallReplaceTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (InstallAdapter.this.getRecItemClick() != null) {
                    InstallAdapter.this.getRecItemClick().onItemClick(i, ListBean, TAG_REPLACE, viewHolder);
                }
            }
        });
    }
}
