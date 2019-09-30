package com.yzf.king.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.yzf.king.R;
import com.yzf.king.kit.AppTools;
import com.yzf.king.model.servicesmodels.GetMyTermResult;

import java.util.ArrayList;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.base.SimpleRecAdapter;
import cn.droidlover.xdroidmvp.imageloader.ILFactory;
import cn.droidlover.xdroidmvp.kit.Kits;
import cn.droidlover.xdroidmvp.kit.KnifeKit;
import es.dmoral.toasty.Toasty;

/**
 * ClaseName：UnactivateAdapter
 * Description：
 * Author：chocozhao
 * QQ: 1027313530
 * Createtime：2019/7/23 16:06
 * Modified By：
 * Fixtime：2019/7/23 16:06
 * FixDescription：
 **/

public class UnactivateAdapter extends SimpleRecAdapter<GetMyTermResult.DataBean.DataListBean, UnactivateAdapter.ViewHolder> {

    public static final int TAG_VIEW = 0;

    private String shopmanage;
    private String shopname;
    private int number;
    private String unactivate;
    private ArrayList<GetMyTermResult.DataBean.DataListBean> homeTests = new ArrayList();
    private static AvailableAdapter.CheckCallBack iCheckCallBack;

    public interface CheckCallBack {
        void sent(ArrayList<GetMyTermResult.DataBean.DataListBean> list);
    }

    public static void setCallback(AvailableAdapter.CheckCallBack callBack) {
        iCheckCallBack = callBack;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.adapter_available_date_tv)
        TextView adapterAvailableDateTv;
        @BindView(R.id.adapter_available_status_tv)
        TextView adapterAvailableStatusTv;
        @BindView(R.id.adapter_available_hear_iv)
        ImageView adapterAvailableHearIv;
        @BindView(R.id.adaper_available_title_tv)
        TextView adaperAvailableTitleTv;
        @BindView(R.id.adapter_available_serial_tv)
        TextView adapterAvailableSerialTv;
        @BindView(R.id.adapter_available_select_cb)
        CheckBox adapterAvailableSelectCb;
        @BindView(R.id.adapter_available_package_tv)
        TextView adapterAvailablePackageTv;
        @BindView(R.id.adapter_available_status_iv)
        ImageView adapterAvailableStatusIv;

        public ViewHolder(View view) {
            super(view);
            KnifeKit.bind(this, view);
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.adapter_available;
    }

    public UnactivateAdapter(Context context) {
        super(context);
    }

    public UnactivateAdapter(Context context, String shopname) {
        super(context);
        this.shopname = shopname;
    }

    public UnactivateAdapter(Context context, String shopmanage, int number) {
        super(context);
        this.shopmanage = shopmanage;
        this.number = number;
    }

    @Override
    public UnactivateAdapter.ViewHolder newViewHolder(View view) {
        return new UnactivateAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final UnactivateAdapter.ViewHolder viewHolder, final int i) {
        final GetMyTermResult.DataBean.DataListBean dataBean = (GetMyTermResult.DataBean.DataListBean) this.data.get(i);
        viewHolder.adapterAvailableDateTv.setText(Kits.Date.getYmdhms(dataBean.getUpdateTime()));
        viewHolder.adaperAvailableTitleTv.setText(dataBean.getModelName());
        viewHolder.adapterAvailablePackageTv.setText("套餐："+dataBean.getMealId());
        if (!AppTools.isEmpty(dataBean.getLogoUrl())) {
            ILFactory.getLoader().loadImage(dataBean.getLogoUrl(), viewHolder.adapterAvailableHearIv);
        }else {
            ILFactory.getLoader().loadImage(R.mipmap.logo, viewHolder.adapterAvailableHearIv);
        }

        if (AppTools.isEmpty(shopmanage)) {
            viewHolder.adapterAvailableSelectCb.setVisibility(View.GONE);
        } else {
            viewHolder.adapterAvailableSelectCb.setVisibility(View.VISIBLE);
        }
        if (dataBean.isCheck()) {
            viewHolder.adapterAvailableSelectCb.setChecked(true);
        } else {
            viewHolder.adapterAvailableSelectCb.setChecked(false);
        }
        switch (dataBean.getStatus()) {
            case 0:
                viewHolder.adapterAvailableStatusIv.setVisibility(View.GONE);
                break;
            case 1:
                viewHolder.adapterAvailableStatusIv.setVisibility(View.GONE);
                break;
            case 2:
                viewHolder.adapterAvailableStatusIv.setImageResource(R.mipmap.device_normal);
                break;
            case 3:
                viewHolder.adapterAvailableStatusIv.setImageResource(R.mipmap.device_abnormal);
                break;
            case 4:
                viewHolder.adapterAvailableStatusIv.setImageResource(R.mipmap.device_activation);
                break;
            default:
        }


        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (UnactivateAdapter.this.getRecItemClick() != null) {
                    UnactivateAdapter.this.getRecItemClick().onItemClick(i, dataBean, TAG_VIEW, viewHolder);
                }
            }
        });
    }
}
