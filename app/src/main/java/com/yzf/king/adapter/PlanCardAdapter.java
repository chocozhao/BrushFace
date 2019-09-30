package com.yzf.king.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yzf.king.R;
import com.yzf.king.model.servicesmodels.GetPlanInfoResults;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.base.SimpleRecAdapter;
import cn.droidlover.xdroidmvp.kit.KnifeKit;

/**
 * ClaseName：${NAME}
 * Description：
 * Author：JensenWei
 * QQ: 2188307188
 * Createtime：2016/12/10 10:32
 * Modified By：
 * Fixtime：2016/12/10 10:32
 * FixDescription：
 */

public class PlanCardAdapter extends SimpleRecAdapter<GetPlanInfoResults.DataBean, PlanCardAdapter.ViewHolder> {

    public static final int TAG_VIEW = 0;
    public static final int TAG_END = 1;
    public static final int TAG_RESTART = 2;


    public PlanCardAdapter(Context context) {
        super(context);
    }

    @Override
    public ViewHolder newViewHolder(View itemView) {
        return new ViewHolder(itemView);
    }

    @Override
    public int getLayoutId() {
        return R.layout.adapter_plancard;
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final GetPlanInfoResults.DataBean item = data.get(position);
        holder.planStatusTv.setText(format(item.getStatus()));
        if (item.getStatus().equals("04") || item.getStatus().equals("05")) {
            holder.planStatusTv.setTextColor(context.getResources().getColor(R.color.text_red));
            holder.planRemarkTv.setText(item.getRemark());
            holder.planRemarkLl.setVisibility(View.VISIBLE);
            holder.planBtLl.setVisibility(View.VISIBLE);
            holder.planRestartTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (getRecItemClick() != null) {
                        getRecItemClick().onItemClick(position, item, TAG_RESTART, holder);
                    }
                }
            });

            holder.planEndTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (getRecItemClick() != null) {
                        getRecItemClick().onItemClick(position, item, TAG_END, holder);
                    }
                }
            });
            holder.planItemLl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (getRecItemClick() != null) {
                        getRecItemClick().onItemClick(position, item, TAG_VIEW, holder);
                    }
                }
            });
        } else if (item.getStatus().equals("02") || item.getStatus().equals("06")) {
            holder.planStatusTv.setTextColor(context.getResources().getColor(R.color.text_yellow));
            holder.planRemarkLl.setVisibility(View.GONE);
            holder.planBtLl.setVisibility(View.GONE);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (getRecItemClick() != null) {
                        getRecItemClick().onItemClick(position, item, TAG_VIEW, holder);
                    }
                }
            });
        } else if (item.getStatus().equals("01")) {
            holder.planStatusTv.setTextColor(context.getResources().getColor(R.color.text_yellow));
            holder.planRemarkLl.setVisibility(View.GONE);
            holder.planBtLl.setVisibility(View.VISIBLE);
            holder.planRestartTv.setVisibility(View.GONE);
            holder.planEndTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (getRecItemClick() != null) {
                        getRecItemClick().onItemClick(position, item, TAG_END, holder);
                    }
                }
            });

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (getRecItemClick() != null) {
                        getRecItemClick().onItemClick(position, item, TAG_VIEW, holder);
                    }
                }
            });
        } else {
            holder.planStatusTv.setTextColor(context.getResources().getColor(R.color.text_yellow));
            holder.planRemarkLl.setVisibility(View.GONE);
            holder.planBtLl.setVisibility(View.GONE);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (getRecItemClick() != null) {
                        getRecItemClick().onItemClick(position, item, TAG_VIEW, holder);
                    }
                }
            });
        }
        if (holder.planRestartTv.getVisibility() == View.GONE) {
            holder.planLine.setVisibility(View.GONE);
        } else {
            holder.planLine.setVisibility(View.VISIBLE);
        }

        holder.planAmtTv.setText(String.valueOf(item.getRepaymentAmount()));
        holder.planFeeTv.setText(String.valueOf(item.getFeeAmount()));
        holder.planTimeTv.setText(item.getStartDay() + "~" + item.getEndDay());
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.plan_time_tv)
        TextView planTimeTv;
        @BindView(R.id.plan_status_tv)
        TextView planStatusTv;
        @BindView(R.id.plan_amt_tv)
        TextView planAmtTv;
        @BindView(R.id.plan_fee_tv)
        TextView planFeeTv;
        @BindView(R.id.plan_item_ll)
        LinearLayout planItemLl;
        @BindView(R.id.plan_remark_tv)
        TextView planRemarkTv;
        @BindView(R.id.plan_remark_ll)
        LinearLayout planRemarkLl;
        @BindView(R.id.plan_end_tv)
        TextView planEndTv;
        @BindView(R.id.plan_restart_tv)
        TextView planRestartTv;
        @BindView(R.id.plan_bt_ll)
        LinearLayout planBtLl;
        @BindView(R.id.plan_line)
        View planLine;


        public ViewHolder(View itemView) {
            super(itemView);
            KnifeKit.bind(this, itemView);
        }
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
