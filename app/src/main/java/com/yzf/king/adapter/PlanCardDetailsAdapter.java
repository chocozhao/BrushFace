package com.yzf.king.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yzf.king.R;
import com.yzf.king.kit.AppTools;
import com.yzf.king.model.servicesmodels.GetPlanDtlResults;

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

public class PlanCardDetailsAdapter extends SimpleRecAdapter<GetPlanDtlResults.DataBean.DetailListBean, PlanCardDetailsAdapter.ViewHolder> {

    public static final int TAG_VIEW = 0;


    public PlanCardDetailsAdapter(Context context) {
        super(context);
    }

    @Override
    public ViewHolder newViewHolder(View itemView) {
        return new ViewHolder(itemView);
    }

    @Override
    public int getLayoutId() {
        return R.layout.adapter_plandetails;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final GetPlanDtlResults.DataBean.DetailListBean item = data.get(position);
        if ("71".equals(item.getRepayType()) || "73".equals(item.getRepayType()) || "91".equals(item.getRepayType())) {
            if ("73".equals(item.getRepayType())) {
                holder.plandetailsTypeTv.setText("规划完成");
            }else {
                holder.plandetailsTypeTv.setText("还款");
            }
            holder.plandetailsIv.setBackgroundResource(R.mipmap.trans_repayment);
        } else {
            holder.plandetailsTypeTv.setText("消费");
            holder.plandetailsIv.setBackgroundResource(R.mipmap.trans_consume);
        }
        holder.plandetailsTimesTv.setText(item.getRepayTime());
        holder.plandetailsAmtTv.setText(item.getRepayAmt());
        holder.plandetailsStatusTv.setText(format(item.getRepayStatus()));
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.plandetails_iv)
        ImageView plandetailsIv;
        @BindView(R.id.plandetails_type_tv)
        TextView plandetailsTypeTv;
        @BindView(R.id.plandetails_amt_tv)
        TextView plandetailsAmtTv;
        @BindView(R.id.plandetails_status_tv)
        TextView plandetailsStatusTv;
        @BindView(R.id.plandetails_times_tv)
        TextView plandetailsTimesTv;

        public ViewHolder(View itemView) {
            super(itemView);
            KnifeKit.bind(this, itemView);
        }
    }

    private String format(String i) {
        if (i.equals("00")) {
            return "未还款";
        }
        if (i.equals("01")) {
            return "已还款";
        }
        if (i.equals("02")) {
            return "还款失败";
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
            return "已退还保留金";
        }
        if (i.equals("07")) {
            return "确认中";
        }
        if (i.equals("08")) {
            return "退款中";
        }
        return i;
    }

    private String formatType(String type, String status) {
        if (AppTools.isEmpty(status)) {
            if (!AppTools.isEmpty(type) && type.equals("71") || !AppTools.isEmpty(type) && type.equals("73")|| !AppTools.isEmpty(type) && type.equals("91")) {
                if (type.equals("73")) {
                    return "规划完成";
                }
                return "还款";
            }
            if (!AppTools.isEmpty(type) && type.equals("72") || !AppTools.isEmpty(type) && type.equals("70")|| !AppTools.isEmpty(type) && type.equals("92")) {
                return "消费";
            }
        } else {
            switch (status) {
                case "0":
                case "1":
                    if (!AppTools.isEmpty(type) && type.equals("71") || !AppTools.isEmpty(type) && type.equals("73")|| !AppTools.isEmpty(type) && type.equals("91")) {
                        if (type.equals("73")) {
                            return "规划完成(未执行)";
                        }
                        return "还款(未执行)";
                    }
                    if (!AppTools.isEmpty(type) && type.equals("72") || !AppTools.isEmpty(type) && type.equals("70")|| !AppTools.isEmpty(type) && type.equals("92")) {
                        return "消费(未执行)";
                    }
                    break;
                case "2":
                    if (!AppTools.isEmpty(type) && type.equals("71") || !AppTools.isEmpty(type) && type.equals("73")|| !AppTools.isEmpty(type) && type.equals("91")) {
                        if (type.equals("73")) {
                            return "规划完成(已执行)";
                        }
                        return "还款(已执行)";
                    }
                    if (!AppTools.isEmpty(type) && type.equals("72") || !AppTools.isEmpty(type) && type.equals("70")|| !AppTools.isEmpty(type) && type.equals("92")) {
                        return "消费(已执行)";
                    }
                    break;
                case "3":
                    if (!AppTools.isEmpty(type) && type.equals("71") || !AppTools.isEmpty(type) && type.equals("73")|| !AppTools.isEmpty(type) && type.equals("91")) {
                        if (type.equals("73")) {
                            return "规划完成(执行失败)";
                        }
                        return "还款失败";
                    }
                    if (!AppTools.isEmpty(type) && type.equals("72") || !AppTools.isEmpty(type) && type.equals("70")|| !AppTools.isEmpty(type) && type.equals("92")) {
                        return "消费失败";
                    }
                    break;
            }
        }
        return type;
    }

}
