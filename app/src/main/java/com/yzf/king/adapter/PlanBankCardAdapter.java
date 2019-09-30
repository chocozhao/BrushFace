package com.yzf.king.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yzf.king.R;
import com.yzf.king.kit.AppTools;
import com.yzf.king.model.servicesmodels.GetCardInfoResult;

import java.util.Calendar;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.base.SimpleRecAdapter;
import cn.droidlover.xdroidmvp.imageloader.ILFactory;
import cn.droidlover.xdroidmvp.kit.Kits;
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

public class PlanBankCardAdapter extends SimpleRecAdapter<GetCardInfoResult.DataBean, PlanBankCardAdapter.ViewHolder> {

    public static final int TAG_VIEW = 0;

    public static final int ADD_VIEW = 1;


    private OnMyItemClickListener listener;

    public void setOnMyItemClickListener(OnMyItemClickListener listener) {
        this.listener = listener;

    }

    public interface OnMyItemClickListener {
        void myClick(View v, int pos, GetCardInfoResult.DataBean item);

        void mLongClick(View v, int pos, GetCardInfoResult.DataBean item);
    }

    public PlanBankCardAdapter(Context context) {
        super(context);
    }

    @Override
    public ViewHolder newViewHolder(View itemView) {
        return new ViewHolder(itemView);
    }

    @Override
    public int getLayoutId() {
        return R.layout.adapter_plan_bankcard;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final GetCardInfoResult.DataBean item = data.get(position);
        holder.plancardAdapterCardNoTv.setText(setAcctno(item.getCardId()));
        holder.plancardAdapterBanknameTv.setText(item.getBankName());
        holder.plancardAdapterNameTv.setText(AppTools.formatName(item.getName()));

        String nowDay = Kits.Date.getYymd();
        String nowMonth = nowDay.substring(0, 6);
        String billDate = nowMonth + item.getBillDate();
        String repaymentDate = nowMonth + item.getRepaymentDate();
        if (nowDay.compareTo(billDate) < 0) {
            holder.plancardAdapterDaysTv.setText(item.getBillDays());
            holder.plancardAdapterBilldateTv.setText(billDate);
            holder.plancardAdapterBilldtlTv.setText("天后出账");
        } else {
            holder.plancardAdapterDaysTv.setText(item.getDays());
            holder.plancardAdapterBilldateTv.setText(repaymentDate);
            holder.plancardAdapterBilldtlTv.setText("天后到期");
        }
        if (item.getCurrBillAmt() == null) {
            holder.plancardAdapterPayamtTv.setText("0.00元");
        } else {
            holder.plancardAdapterPayamtTv.setText(item.getCurrBillAmt() + "元");
        }

        if (item.getBankName().contains("工商") || item.getBankName().contains("中国银行")
                || item.getBankName().contains("招商") || item.getBankName().contains("中信")) {
            holder.bankBg.setBackground(ContextCompat.getDrawable(context, R.drawable.bankcard_red));
        } else if (item.getBankName().contains("农业") || item.getBankName().contains("邮政")) {
            holder.bankBg.setBackground(ContextCompat.getDrawable(context, R.drawable.bankcard_green));
        } else if (item.getBankName().contains("交通") || item.getBankName().contains("浦") || item.getBankName().contains("民生")
                || item.getBankName().contains("兴业") || item.getBankName().contains("建设")) {
            holder.bankBg.setBackground(ContextCompat.getDrawable(context, R.drawable.bankcard_blue));
        } else if (item.getBankName().contains("平安") || item.getBankName().contains("光大") || item.getBankName().contains("农村")) {
            holder.bankBg.setBackground(ContextCompat.getDrawable(context, R.drawable.bankcard_yellow));
        } else {
            holder.bankBg.setBackground(ContextCompat.getDrawable(context, R.drawable.bankcard_red));
        }
        int id = getImgId("b" + item.getBankCode());
        if (id > 0) {
            ILFactory.getLoader().loadCircleImage(id, holder.bankIv);
        } else {
            ILFactory.getLoader().loadCircleImage(getImgId("bank"), holder.bankIv);
        }
        if (listener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.myClick(v, position, item);
                }
            });
            // set LongClick
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    listener.mLongClick(v, position, item);
                    return true;
                }
            });
            holder.plancardAdapterAddTv.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    PlanBankCardAdapter.this.getRecItemClick().onItemClick(position, item, ADD_VIEW, holder);

                }
            });
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

    private String setAcctno(String acctNo) {
        if (!AppTools.isEmpty(acctNo)) {
            acctNo = "尾号(" + acctNo.substring(acctNo.length() - 4, acctNo.length()) + ")";
        }
        return acctNo;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.bank_iv)
        ImageView bankIv;
        @BindView(R.id.plancard_adapter_bankname_tv)
        TextView plancardAdapterBanknameTv;
        @BindView(R.id.plancard_adapter_cardno_tv)
        TextView plancardAdapterCardNoTv;
        @BindView(R.id.plancard_adapter_name_tv)
        TextView plancardAdapterNameTv;
        @BindView(R.id.plancard_adapter_days_tv)
        TextView plancardAdapterDaysTv;
        @BindView(R.id.plancard_adapter_payamt_tv)
        TextView plancardAdapterPayamtTv;
        @BindView(R.id.plancard_adapter_add_tv)
        TextView plancardAdapterAddTv;
        @BindView(R.id.plancard_adapter_billdate_tv)
        TextView plancardAdapterBilldateTv;
        @BindView(R.id.plancard_adapter_billdtl_tv)
        TextView plancardAdapterBilldtlTv;
        @BindView(R.id.bank_bg)
        LinearLayout bankBg;


        public ViewHolder(View itemView) {
            super(itemView);
            KnifeKit.bind(this, itemView);
        }
    }

}
