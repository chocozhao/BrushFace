package com.yzf.king.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yzf.king.R;
import com.yzf.king.model.servicesmodels.GetMyTermResult;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.base.SimpleRecAdapter;
import cn.droidlover.xdroidmvp.imageloader.ILFactory;
import cn.droidlover.xdroidmvp.kit.Kits;
import cn.droidlover.xdroidmvp.kit.KnifeKit;

/**
 * ClaseName：MyDeviceAdapter
 * Description：
 * Author：chocozhao
 * QQ: 1027313530
 * Createtime：2019/7/8 11:55
 * Modified By：
 * Fixtime：2019/7/8 11:55
 * FixDescription：
 **/

public class MyDeviceAdapter extends SimpleRecAdapter<GetMyTermResult.DataBean.DataListBean, MyDeviceAdapter.ViewHolder> {

    public static final int TAG_VIEW = 0;


    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.adapter_device_date_tv)
        TextView adapterDeviceDateTv;
        @BindView(R.id.adapter_device_status_tv)
        TextView adapterDeviceStatusTv;
        @BindView(R.id.adapter_device_hear_iv)
        ImageView adapterDeviceHearIv;
        @BindView(R.id.adapter_device_title_tv)
        TextView adapterDeviceTitleTv;
        @BindView(R.id.adapter_device_shop_tv)
        TextView adapterDeviceShoplTv;
        @BindView(R.id.adapter_device_package_tv)
        TextView adapterDevicePackageTv;
        @BindView(R.id.adapter_device_status_iv)
        ImageView adapterDeviceStatusIv;

        public ViewHolder(View view) {
            super(view);
            KnifeKit.bind(this, view);
        }

    }

    @Override
    public int getLayoutId() {
        return R.layout.adapter_my_device;
    }

    public MyDeviceAdapter(Context context) {
        super(context);
    }

    @Override
    public ViewHolder newViewHolder(View view) {
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int i) {
        final GetMyTermResult.DataBean.DataListBean dataBean = (GetMyTermResult.DataBean.DataListBean) this.data.get(i);
        viewHolder.adapterDeviceDateTv.setText(Kits.Date.getYmdhms(dataBean.getInsertTime()));
        viewHolder.adapterDeviceTitleTv.setText(dataBean.getModelName());
        viewHolder.adapterDeviceShoplTv.setText("序列号："+dataBean.getTermSn());
        viewHolder.adapterDevicePackageTv.setText("套餐："+dataBean.getMealId());
        ILFactory.getLoader().loadImage(dataBean.getLogoUrl(), viewHolder.adapterDeviceHearIv);
        switch (dataBean.getStatus()) {
            case 0:
                viewHolder.adapterDeviceStatusIv.setVisibility(View.GONE);
                break;
            case 1:
                viewHolder.adapterDeviceStatusIv.setVisibility(View.GONE);
                break;
            case 2:
                viewHolder.adapterDeviceStatusIv.setImageResource(R.mipmap.device_normal);
                break;
            case 3:
                viewHolder.adapterDeviceStatusIv.setImageResource(R.mipmap.device_abnormal);
                break;
            case 4:
                viewHolder.adapterDeviceStatusIv.setImageResource(R.mipmap.device_activation);
                break;
            default:
        }
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (MyDeviceAdapter.this.getRecItemClick() != null) {
                    MyDeviceAdapter.this.getRecItemClick().onItemClick(i, dataBean, 0, viewHolder);
                }
            }
        });
    }
}
