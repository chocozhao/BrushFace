package com.yzf.king.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.yzf.king.R;
import com.yzf.king.model.servicesmodels.GetTransInfoCountResult;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.base.SimpleRecAdapter;
import cn.droidlover.xdroidmvp.kit.KnifeKit;

/**
 * ClaseName：TransStatDayAdapter
 * Description：
 * Author：chocozhao
 * QQ: 1027313530
 * Createtime：2019/5/15 11:33
 * Modified By：
 * Fixtime：2019/5/15 11:33
 * FixDescription：
 **/

public class TransStatDayAdapter extends SimpleRecAdapter<GetTransInfoCountResult.DataBean.InfoBean, TransStatDayAdapter.ViewHolder> {

    public static final int TAG_VIEW = 0;



    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.adapter_transstat_date_tv)
        TextView adapterTransstatDateTv;
        @BindView(R.id.adapter_transstat_count_tv)
        TextView adapterTransstatCountTv;
        @BindView(R.id.adapter_transstat_totalamt_tv)
        TextView adapterTransstatTotalamtTv;
        public ViewHolder(View view) {
            super(view);
            KnifeKit.bind(this, view);
        }
    }

    public int getLayoutId() {
        return R.layout.adapter_transstat;
    }

    public TransStatDayAdapter(Context context) {
        super(context);
    }

    public ViewHolder newViewHolder(View view) {
        return new ViewHolder(view);
    }

    public void onBindViewHolder(final ViewHolder viewHolder, final int i) {
        final GetTransInfoCountResult.DataBean.InfoBean dataBean = (GetTransInfoCountResult.DataBean.InfoBean) this.data.get(i);
        viewHolder.adapterTransstatDateTv.setText(""+dataBean.getDay());
        viewHolder.adapterTransstatCountTv.setText(dataBean.getCount()+"笔");
        viewHolder.adapterTransstatTotalamtTv.setText("￥"+dataBean.getTotalAmt());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (TransStatDayAdapter.this.getRecItemClick() != null) {
                    TransStatDayAdapter.this.getRecItemClick().onItemClick(i, dataBean, TAG_VIEW, viewHolder);
                }
            }
        });
    }
}
