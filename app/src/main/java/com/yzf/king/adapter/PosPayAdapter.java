package com.yzf.king.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yzf.king.R;
import com.yzf.king.model.servicesmodels.GetPosChanlFeeResult;

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

public class PosPayAdapter extends SimpleRecAdapter<GetPosChanlFeeResult.DataBean, PosPayAdapter.ViewHolder> {

    public static final int TAG_VIEW = 0;

    public PosPayAdapter(Context context) {
        super(context);
    }

    @Override
    public ViewHolder newViewHolder(View itemView) {
        return new ViewHolder(itemView);
    }

    @Override
    public int getLayoutId() {
        return R.layout.adapter_pos_pay;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final GetPosChanlFeeResult.DataBean item = data.get(position);
        holder.payposAdapterTypeTv.setText(item.getTradeName());
        holder.payposAdapterFeeTv.setText("费率：" + item.getTradeFee());
        holder.payposAdapterAmtTv.setText("单笔金额：" + item.getTradeRange() + "元");
        holder.payposAdapterDescTv.setText(item.getAdd1());
        int id = getImgId("k" + item.getTradeCode());
        if (id > 0) {
            ILFactory.getLoader().loadCircleImage(id,holder.payposAdapterImgIv);
        }else {
            ILFactory.getLoader().loadCircleImage(getImgId("k5201"),holder.payposAdapterImgIv);
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

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.buypos_address_iv)
        ImageView payposAdapterImgIv;
        @BindView(R.id.buypos_name_tv)
        TextView payposAdapterTypeTv;
        @BindView(R.id.buypos_phone_tv)
        TextView payposAdapterFeeTv;
        @BindView(R.id.buypos_address_tv)
        TextView payposAdapterAmtTv;
        @BindView(R.id.paypos_adapter_desc_tv)
        TextView payposAdapterDescTv;

        public ViewHolder(View itemView) {
            super(itemView);
            KnifeKit.bind(this, itemView);
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

}
