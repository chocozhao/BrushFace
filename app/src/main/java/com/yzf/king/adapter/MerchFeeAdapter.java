package com.yzf.king.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yzf.king.R;
import com.yzf.king.model.servicesmodels.GetFeeInfoResult;

import java.text.DecimalFormat;

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

public class MerchFeeAdapter extends SimpleRecAdapter<GetFeeInfoResult.DataBean, MerchFeeAdapter.ViewHolder> {

    public static final int TAG_VIEW = 0;
    @BindView(R.id.fee_adapter_img_iv)
    ImageView feeAdapterImgIv;
    @BindView(R.id.fee_adapter_name_tv)
    TextView feeAdapterNameTv;
    @BindView(R.id.fee_adapter_rate_tv)
    TextView feeAdapterRateTv;

    public MerchFeeAdapter(Context context) {
        super(context);
    }

    @Override
    public ViewHolder newViewHolder(View itemView) {
        return new ViewHolder(itemView);
    }

    @Override
    public int getLayoutId() {
        return R.layout.adapter_merchfee;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final GetFeeInfoResult.DataBean item = data.get(position);
        if (item.getTransType().equals("70")) {
            holder.feeAdapterNameTv.setText(item.getRemark());
            holder.feeAdapterImgIv.setBackgroundResource(R.mipmap.icon_plan_fee);
            holder.feeAdapterRateTv.setText(formatRate(item.getFeeRate(), item.getExternFee()));
        } else if (item.getTransType().equals("78")) {
            holder.feeAdapterNameTv.setText(item.getRemark());
            holder.feeAdapterImgIv.setBackgroundResource(R.mipmap.icon_vip_fee);
            holder.feeAdapterRateTv.setText(formatRate(item.getFeeRate(), item.getExternFee()));
        } else if (item.getTransType().equals("90")) {
            holder.feeAdapterNameTv.setText(item.getRemark());
            holder.feeAdapterImgIv.setBackgroundResource(R.mipmap.icon_auth_fee);
            holder.feeAdapterRateTv.setText(formatRate(item.getFeeRate(), item.getExternFee()));
        } else if (item.getTransType().equals("80")) {
            holder.feeAdapterNameTv.setText(item.getRemark());
            holder.feeAdapterImgIv.setBackgroundResource(R.mipmap.icon_vip_fee);
            holder.feeAdapterRateTv.setText(formatRate(item.getFeeRate(), item.getExternFee()));
        } else if (item.getTransType().equals("61")) {
            holder.feeAdapterNameTv.setText(item.getRemark());
            holder.feeAdapterImgIv.setBackgroundResource(R.mipmap.icon_plan_fee);
            holder.feeAdapterRateTv.setText(formatRate(item.getFeeRate(), item.getExternFee()));
        } else {
            holder.feeAdapterNameTv.setText(item.getRemark());
            holder.feeAdapterImgIv.setBackgroundResource(R.mipmap.icon_vip_fee);
            holder.feeAdapterRateTv.setText(formatRate(item.getFeeRate(), item.getExternFee()));
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getRecItemClick() != null) {
                    getRecItemClick().onItemClick(position, item, TAG_VIEW, holder);
                }
            }
        });
    }

    String formatRate(String rate, String extern) {
        double feeRate = Double.parseDouble(rate);
        double externFee = Double.parseDouble(extern);
        if (feeRate == 0) {
            if (externFee <= 0) {
                return "费率  免费";
            }
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("费率  ");
            stringBuilder.append(externFee);
            stringBuilder.append("元/笔");
            return stringBuilder.toString();
        } else if (externFee <= 0) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("费率  ");
            stringBuilder.append(F2Percentformat(feeRate));
            return stringBuilder.toString();
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("费率  ");
            stringBuilder.append(F2Percentformat(feeRate));
            stringBuilder.append("+");
            stringBuilder.append(externFee);
            return stringBuilder.toString();
        }
    }

    public static String F2Percentformat(double i) {
        return new DecimalFormat("0.00%").format((i / 10000));
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.fee_adapter_img_iv)
        ImageView feeAdapterImgIv;
        @BindView(R.id.fee_adapter_name_tv)
        TextView feeAdapterNameTv;
        @BindView(R.id.fee_adapter_rate_tv)
        TextView feeAdapterRateTv;

        public ViewHolder(View itemView) {
            super(itemView);
            KnifeKit.bind(this, itemView);
        }
    }

}
