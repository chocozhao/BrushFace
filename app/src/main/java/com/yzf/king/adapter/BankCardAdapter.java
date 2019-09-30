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

public class BankCardAdapter extends SimpleRecAdapter<GetCardInfoResult.DataBean, BankCardAdapter.ViewHolder> {

    public static final int TAG_VIEW = 0;

    private BankCardAdapter.OnMyItemClickListener listener;

    public void setOnMyItemClickListener(BankCardAdapter.OnMyItemClickListener listener) {
        this.listener = listener;

    }

    public interface OnMyItemClickListener {
        void myClick(View v, int pos, GetCardInfoResult.DataBean item);

        void mLongClick(View v, int pos, GetCardInfoResult.DataBean item);
    }

    public BankCardAdapter(Context context) {
        super(context);
    }

    @Override
    public ViewHolder newViewHolder(View itemView) {
        return new ViewHolder(itemView);
    }

    @Override
    public int getLayoutId() {
        return R.layout.adapter_bankcard;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final GetCardInfoResult.DataBean item = data.get(position);
        holder.bankcardAdapterAcctnoTv.setText(setAcctno(item.getCardId()));
        holder.bankcardAdapterBanknameTv.setText(item.getBankName());
        holder.bankcardAdapterBanktypeTv.setText(setCardType(item.getCardType()));
        holder.bankcardAdapterPaydayTv.setText("账单日：" + item.getBillDate() + "日");
        holder.bankcardAdapterRepaydayTv.setText("还款日：" + item.getRepaymentDate() + "日");
        holder.bankcardAdapterLimitTv.setText("额度：" + item.getLimitAmt() + "元");
        if (item.getBankName().contains("工商") || item.getBankName().contains("中国银行")
                || item.getBankName().contains("招商") || item.getBankName().contains("中信")) {
            holder.bank_bg.setBackground(ContextCompat.getDrawable(context, R.drawable.bankcard_red));
        } else if (item.getBankName().contains("农业") || item.getBankName().contains("邮政")) {
            holder.bank_bg.setBackground(ContextCompat.getDrawable(context, R.drawable.bankcard_green));
        } else if (item.getBankName().contains("交通") || item.getBankName().contains("浦") || item.getBankName().contains("民生")
                || item.getBankName().contains("兴业") || item.getBankName().contains("建设")) {
            holder.bank_bg.setBackground(ContextCompat.getDrawable(context, R.drawable.bankcard_blue));
        } else if (item.getBankName().contains("平安") || item.getBankName().contains("光大") || item.getBankName().contains("农村")) {
            holder.bank_bg.setBackground(ContextCompat.getDrawable(context, R.drawable.bankcard_yellow));
        } else {
            holder.bank_bg.setBackground(ContextCompat.getDrawable(context, R.drawable.bankcard_red));
        }
        int id = getImgId("b" + item.getBankCode());
        if (id > 0) {
            ILFactory.getLoader().loadCircleImage(id,holder.bankIv);
        }else {
            ILFactory.getLoader().loadCircleImage(getImgId("bank"),holder.bankIv);
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
            acctNo = acctNo.substring(0, 4) + "     ****     ****     " + acctNo.substring(acctNo.length() - 4, acctNo.length());

        }
        return acctNo;
    }

    private String setCardType(String cartType) {
        String type = "储蓄卡";
        if (!AppTools.isEmpty(cartType)) {
            switch (cartType) {
                case "00":
                    type = "信用卡";
                    break;
                case "01":
                    type = "储蓄卡";
                    break;
                case "02":
                    type = "准贷记卡";
                    break;
            }
        }
        return type;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.bankcard_adapter_bankname_tv)
        TextView bankcardAdapterBanknameTv;
        @BindView(R.id.bankcard_adapter_banktype_tv)
        TextView bankcardAdapterBanktypeTv;
        @BindView(R.id.bankcard_adapter_acctno_tv)
        TextView bankcardAdapterAcctnoTv;
        @BindView(R.id.bankcard_adapter_payday_tv)
        TextView bankcardAdapterPaydayTv;
        @BindView(R.id.bankcard_adapter_repayday_tv)
        TextView bankcardAdapterRepaydayTv;
        @BindView(R.id.bankcard_adapter_limit_tv)
        TextView bankcardAdapterLimitTv;
        @BindView(R.id.bank_bg)
        LinearLayout bank_bg;
        @BindView(R.id.bank_iv)
        ImageView bankIv;


        public ViewHolder(View itemView) {
            super(itemView);
            KnifeKit.bind(this, itemView);
        }
    }

}
