package com.yzf.king.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.yzf.king.R;
import com.yzf.king.model.servicesmodels.GetPlanDtlResults;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.base.SimpleRecAdapter;
import cn.droidlover.xdroidmvp.kit.KnifeKit;

/**
 *
 * ClaseName：${NAME}
 * Description：
 * Author：JensenWei
 * QQ: 2188307188
 * Createtime：2016/12/10 10:32
 * Modified By：
 * Fixtime：2016/12/10 10:32
 * FixDescription：
 * @version
 *
 */

public class PlanCardDetailAdapter extends SimpleRecAdapter<GetPlanDtlResults.DataBean.DetailBean, PlanCardDetailAdapter.ViewHolder> {

    public static final int TAG_VIEW = 0;

    public PlanCardDetailAdapter(Context context) {
        super(context);
    }

    @Override
    public ViewHolder newViewHolder(View itemView) {
        return new ViewHolder(itemView);
    }

    @Override
    public int getLayoutId() {
        return R.layout.adapter_plandetail;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final GetPlanDtlResults.DataBean.DetailBean item = data.get(position);
        holder.plancardDateAdapter.setText(item.getRepayDate());
        holder.plancardAmtAdapter.setText("还款  " + item.getReturnAmt());
        holder.plancardStatusAdapter.setText(format(item.getReturnStatus()));
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.plancard_amt_adapter)
        TextView plancardAmtAdapter;
        @BindView(R.id.plancard_status_adapter)
        TextView plancardStatusAdapter;
        @BindView(R.id.plancard_date_adapter)
        TextView plancardDateAdapter;
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

}
