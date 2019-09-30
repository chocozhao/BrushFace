package com.yzf.king.adapter;

/**
 * ClaseName：RepayRecordAdapter
 * Description：
 * Author：chocozhao
 * QQ: 1027313530
 * Createtime：2019/5/8 11:31
 * Modified By：
 * Fixtime：2019/5/8 11:31
 * FixDescription：
 **/

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.yzf.king.R;
import com.yzf.king.model.servicesmodels.GetPlanInfoResults;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.base.SimpleRecAdapter;
import cn.droidlover.xdroidmvp.kit.KnifeKit;

public class RepayRecordAdapter extends SimpleRecAdapter<GetPlanInfoResults.DataBean, RepayRecordAdapter.ViewHolder> {

    public static final int TAG_VIEW = 0;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.record_time_tv)
        TextView recordTimeTv;
        @BindView(R.id.record_status_tv)
        TextView recordStatusTv;
        @BindView(R.id.creditdet_credit_tv)
        TextView creditdetCreditTv;
        @BindView(R.id.creditdet_fee_tv)
        TextView creditdetFeeTv;
        public ViewHolder(View view) {
            super(view);
            KnifeKit.bind(this, view);
        }
    }

    public int getLayoutId() {
        return R.layout.adapter_repay_record;
    }

    public RepayRecordAdapter(Context context) {
        super(context);
    }

    public ViewHolder newViewHolder(View view) {
        return new ViewHolder(view);
    }

    public void onBindViewHolder(final ViewHolder holder, final int position) {

        final GetPlanInfoResults.DataBean item = data.get(position);
        holder.recordStatusTv.setText(format(item.getStatus()));
        if (item.getStatus().equals("04") || item.getStatus().equals("05")) {
            holder.recordStatusTv.setTextColor(context.getResources().getColor(R.color.text_red));
        } else if (item.getStatus().equals("02") || item.getStatus().equals("06")) {
            holder.recordStatusTv.setTextColor(context.getResources().getColor(R.color.text_yellow));
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (getRecItemClick() != null) {
                        getRecItemClick().onItemClick(position, item, TAG_VIEW, holder);
                    }
                }
            });
        } else if (item.getStatus().equals("01")) {
            holder.recordStatusTv.setTextColor(context.getResources().getColor(R.color.text_yellow));
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (getRecItemClick() != null) {
                        getRecItemClick().onItemClick(position, item, TAG_VIEW, holder);
                    }
                }
            });
        } else {
            holder.recordStatusTv.setTextColor(context.getResources().getColor(R.color.text_yellow));
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (getRecItemClick() != null) {
                        getRecItemClick().onItemClick(position, item, TAG_VIEW, holder);
                    }
                }
            });
        }

        holder.creditdetCreditTv.setText(String.valueOf(item.getRepaymentAmount()));
        holder.creditdetFeeTv.setText(String.valueOf(item.getFeeAmount()));
        holder.recordTimeTv.setText(item.getStartDay() + "~" + item.getEndDay());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getRecItemClick() != null) {
                    getRecItemClick().onItemClick(position, item, TAG_VIEW, holder);
                }
            }
        });
    }

    private String format(String i) {
        if (i.equals("00")) {
            return "未确认";
        }
        if (i.equals("01")) {
            return "执行中";
        }
        if (i.equals("02")) {
            return "已完成";
        }
        if (i.equals("03")) {
            return "失效";
        }
        if (i.equals("04")) {
            return "异常处理中";
        }
        if (i.equals("05")) {
            return "异常失败";
        }
        if (i.equals("06")) {
            return "失败退还";
        }
        if (i.equals("07")) {
            return "确认中";
        }
        if (i.equals("08")) {
            return "退款中";
        }
        return i;
    }
}