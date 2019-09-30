package com.yzf.king.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yzf.king.R;
import com.yzf.king.model.servicesmodels.GetMyTermResult;
import com.yzf.king.model.servicesmodels.GetTeamTermInfoResult;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.base.SimpleRecAdapter;
import cn.droidlover.xdroidmvp.kit.Kits;
import cn.droidlover.xdroidmvp.kit.KnifeKit;

/**
 * ClaseName：TeamDeviceAdapter
 * Description：
 * Author：chocozhao
 * QQ: 1027313530
 * Createtime：2019/7/17 18:00
 * Modified By：
 * Fixtime：2019/7/17 18:00
 * FixDescription：
 **/

public class TeamDeviceAdapter extends SimpleRecAdapter<GetTeamTermInfoResult.DataBean.DataListBean, TeamDeviceAdapter.ViewHolder> {

    public static final int TAG_VIEW = 0;



    private TeamDetailAdapter.OnMyItemClickListener listener;

    public void setOnMyItemClickListener(TeamDetailAdapter.OnMyItemClickListener listener) {
        this.listener = listener;

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.adapter_device_date_tv)
        TextView adapterDeviceDateTv;
        @BindView(R.id.adapter_device_status_iv)
        ImageView adapterDeviceStatusIv;
        @BindView(R.id.adapter_device_hear_iv)
        ImageView adapterDeviceHearIv;
        @BindView(R.id.adapter_device_title_tv)
        TextView adaperDeviceTitleTv;
        @BindView(R.id.adapter_device_shop_tv)
        TextView adapterDeviceShoplTv;
        @BindView(R.id.adapter_device_package_tv)
        TextView adaperDevicePackageTv;

        public ViewHolder(View view) {
            super(view);
            KnifeKit.bind(this, view);
        }

    }

    @Override
    public int getLayoutId() {
        return R.layout.adapter_my_device;
    }

    public TeamDeviceAdapter(Context context) {
        super(context);
    }

    @Override
    public TeamDeviceAdapter.ViewHolder newViewHolder(View view) {
        return new TeamDeviceAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final TeamDeviceAdapter.ViewHolder viewHolder, final int i) {
        final GetTeamTermInfoResult.DataBean.DataListBean dataBean = (GetTeamTermInfoResult.DataBean.DataListBean) this.data.get(i);
        viewHolder.adapterDeviceDateTv.setText(Kits.Date.getYmdhms(dataBean.getInsertTime()));
        viewHolder.adaperDeviceTitleTv.setText(dataBean.getModelName());
        viewHolder.adapterDeviceShoplTv.setText("序列号："+dataBean.getTermSn());
        viewHolder.adaperDevicePackageTv.setText(dataBean.getMealName());
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
                if (TeamDeviceAdapter.this.getRecItemClick() != null) {
                    TeamDeviceAdapter.this.getRecItemClick().onItemClick(i, dataBean, 0, viewHolder);
                }
            }
        });
    }
}
