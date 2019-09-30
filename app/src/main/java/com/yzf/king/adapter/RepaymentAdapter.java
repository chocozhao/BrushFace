package com.yzf.king.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.yzf.king.R;
import com.yzf.king.model.servicesmodels.GetRepaymentResults;

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

public class RepaymentAdapter extends SimpleRecAdapter<GetRepaymentResults.DataBean.RowsBean, RepaymentAdapter.ViewHolder> {

    public static final int TAG_VIEW = 0;

    public RepaymentAdapter(Context context) {
        super(context);
    }

    @Override
    public ViewHolder newViewHolder(View itemView) {
        return new ViewHolder(itemView);
    }

    @Override
    public int getLayoutId() {
        return R.layout.adapter_repayment;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final GetRepaymentResults.DataBean.RowsBean item = data.get(position);
        holder.date.setText("还款日期："+item.getReturnDate());
        holder.amt.setText("还款金额："+item.getReturnAmt());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getRecItemClick() != null) {
                    getRecItemClick().onItemClick(position, item, TAG_VIEW, holder);
                }
            }
        });
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.repayment_date_adapter)
        TextView date;
        @BindView(R.id.repayment_amt_adapter)
        TextView amt;

        public ViewHolder(View itemView) {
            super(itemView);
            KnifeKit.bind(this, itemView);
        }
    }

}
