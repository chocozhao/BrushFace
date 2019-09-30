package com.yzf.king.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yzf.king.R;
import com.yzf.king.kit.AppTools;
import com.yzf.king.model.servicesmodels.GetProcessPlanResults;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.base.SimpleRecAdapter;
import cn.droidlover.xdroidmvp.imageloader.ILFactory;
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

public class ProcessPlanAdapter extends SimpleRecAdapter<GetProcessPlanResults.DataBean, ProcessPlanAdapter.ViewHolder> {

    public static final int TAG_VIEW = 0;
    public static final int TAG_END = 1;
    public static final int TAG_RESTART = 2;



    public ProcessPlanAdapter(Context context) {
        super(context);
    }

    @Override
    public ViewHolder newViewHolder(View itemView) {
        return new ViewHolder(itemView);
    }

    @Override
    public int getLayoutId() {
        return R.layout.adapter_processplan;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final GetProcessPlanResults.DataBean item = data.get(position);
        String bankName = item.getBankName();
        String cardId=item.getCardId();
        if (!AppTools.isEmpty(cardId)) {
            bankName = bankName+"(" + cardId.substring(cardId.length() - 4, cardId.length()) + ")";
        }
        holder.planAdapterBanknameTv.setText(bankName);
        holder.planAdapterstatusTv.setText(format(item.getStatus()));
        holder.planAdapteramtTv.setText(String.valueOf(item.getRepaymentAmount()));
        holder.planAdapterfeeTv.setText(String.valueOf(item.getFeeAmount()));
        holder.planTimeTv.setText(item.getStartDay() + "~" + item.getEndDay());
        if (item.getStatus().equals("04") || item.getStatus().equals("05")) {
            holder.planAdapterstatusTv.setTextColor(context.getResources().getColor(R.color.text_red));
            holder.planAdapterremarkTv.setText(item.getRemark());
            holder.planAdapterremarkLl.setVisibility(View.VISIBLE);
            holder.planAdapterbtLl.setVisibility(View.VISIBLE);
            holder.planAdapterrestartTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (getRecItemClick() != null) {
                        getRecItemClick().onItemClick(position, item, TAG_RESTART, holder);
                    }
                }
            });

            holder.planAdapterendTv.setOnClickListener(new View.OnClickListener() {
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
            holder.planAdapterstatusTv.setTextColor(context.getResources().getColor(R.color.text_6));
            holder.planAdapterremarkLl.setVisibility(View.GONE);
            holder.planAdapterbtLl.setVisibility(View.GONE);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (getRecItemClick() != null) {
                        getRecItemClick().onItemClick(position, item, TAG_VIEW, holder);
                    }
                }
            });
        } else if (item.getStatus().equals("01")) {
            holder.planAdapterstatusTv.setTextColor(context.getResources().getColor(R.color.text_yellow));
            holder.planAdapterremarkLl.setVisibility(View.GONE);
            holder.planAdapterbtLl.setVisibility(View.VISIBLE);
            holder.planAdapterrestartTv.setVisibility(View.GONE);
            holder.planAdapterendTv.setOnClickListener(new View.OnClickListener() {
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
            holder.planAdapterstatusTv.setTextColor(context.getResources().getColor(R.color.text_yellow));
            holder.planAdapterremarkLl.setVisibility(View.GONE);
            holder.planAdapterbtLl.setVisibility(View.GONE);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (getRecItemClick() != null) {
                        getRecItemClick().onItemClick(position, item, TAG_VIEW, holder);
                    }
                }
            });
        }
        if(holder.planAdapterrestartTv.getVisibility()==View.GONE)
        {
            holder.planAdapterLine.setVisibility(View.GONE);
        }else {
            holder.planAdapterLine.setVisibility(View.VISIBLE);
        }

        int id = getImgId("b" + item.getBankCode());
        if (id > 0) {
            ILFactory.getLoader().loadCircleImage(id,holder.planAdapterBankIv);
        }else {
            ILFactory.getLoader().loadCircleImage(getImgId("bank"),holder.planAdapterBankIv);
        }

    }


    private int getImgId(String imgName) {
        int id = -1;
        try {
            id = context.getResources().getIdentifier(imgName, "mipmap", context.getPackageName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
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
            return "异常";
        }
        if (i.equals("05")) {
            return "异常";
        }
        if (i.equals("06")) {
            return "已退款";
        }
        if (i.equals("07")) {
            return "确认中";
        }
        if (i.equals("08")) {
            return "退款中";
        }
        return i;
    }

    private String setTransWeek(String time) {
        if (!AppTools.isEmpty(time)) {
            time = AppTools.getWeek(time, "yyyy-MM-dd HH:mm:ss");
        }
        return time;
    }

    private String setTransDay(String time) {
        if (!AppTools.isEmpty(time)) {
            time = time.substring(5, 10);
        }
        return time;
    }

    private String setTransResult(String result) {
        if (!AppTools.isEmpty(result)) {
            if (result.equals("01")) {
                result = "交易成功";
            } else {
                result = "交易失败";
            }
        }
        return result;
    }

 /*   @OnClick({R.id.processplan_restart_bt, R.id.processplan_end_bt})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.processplan_restart_bt:
                break;
            case R.id.processplan_end_bt:
                break;
        }
    }*/

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.plan_adapter_bank_iv)
        ImageView planAdapterBankIv;
        @BindView(R.id.plan_adapter_bankname_tv)
        TextView planAdapterBanknameTv;
        @BindView(R.id.plan_time_tv)
        TextView planTimeTv;
        @BindView(R.id.plan_adapterstatus_tv)
        TextView planAdapterstatusTv;
        @BindView(R.id.plan_adapteramt_tv)
        TextView planAdapteramtTv;
        @BindView(R.id.plan_adapterfee_tv)
        TextView planAdapterfeeTv;
        @BindView(R.id.plan_item_ll)
        LinearLayout planItemLl;
        @BindView(R.id.plan_adapterremark_tv)
        TextView planAdapterremarkTv;
        @BindView(R.id.plan_adapterremark_ll)
        LinearLayout planAdapterremarkLl;
        @BindView(R.id.plan_adapterend_tv)
        TextView planAdapterendTv;
        @BindView(R.id.plan_adapterrestart_tv)
        TextView planAdapterrestartTv;
        @BindView(R.id.plan_adapterbt_ll)
        LinearLayout planAdapterbtLl;
        @BindView(R.id.plan_adapter_line)
        View planAdapterLine;

        public ViewHolder(View itemView) {
            super(itemView);
            KnifeKit.bind(this, itemView);
        }
    }


}
