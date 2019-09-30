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
 * ClaseName：AvailableAdapter
 * Description：
 * Author：chocozhao
 * QQ: 1027313530
 * Createtime：2019/7/9 19:49
 * Modified By：
 * Fixtime：2019/7/9 19:49
 * FixDescription：
 **/

public class AvailableAdapter extends SimpleRecAdapter<GetMyTermResult.DataBean.DataListBean, AvailableAdapter.ViewHolder> {

    public static final int TAG_VIEW = 0;

    private String shopmanage;
    private String shopname;
    private int number;
    private String unactivate;
    private ArrayList<GetMyTermResult.DataBean.DataListBean> deviceNum = new ArrayList();
    private static CheckCallBack iCheckCallBack;

    public interface CheckCallBack {
        void sent(ArrayList<GetMyTermResult.DataBean.DataListBean> list);
    }

    public static void setCallback(CheckCallBack callBack) {
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

    public AvailableAdapter(Context context) {
        super(context);
    }

    public AvailableAdapter(Context context, String shopname) {
        super(context);
        this.shopname = shopname;
    }

    public AvailableAdapter(Context context, String shopmanage, int number) {
        super(context);
        this.shopmanage = shopmanage;
        this.number = number;
    }

    @Override
    public ViewHolder newViewHolder(View view) {
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int i) {
        final GetMyTermResult.DataBean.DataListBean dataBean = (GetMyTermResult.DataBean.DataListBean) this.data.get(i);
        viewHolder.adapterAvailableDateTv.setText(Kits.Date.getYmdhms(dataBean.getUpdateTime()));
        viewHolder.adaperAvailableTitleTv.setText(dataBean.getModelName());

        if (AppTools.isEmpty(shopname)) {
            if (dataBean.getTermSn() == null) {
                viewHolder.adapterAvailableSerialTv.setVisibility(View.GONE);
            } else {
                viewHolder.adapterAvailableSerialTv.setText("序列号：" + dataBean.getTermSn());
            }
        } else {
            viewHolder.adapterAvailableSerialTv.setText("序列号：" +dataBean.getTermSn());
        }
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
        viewHolder.adapterAvailableSelectCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    //添加到数组
                    if (deviceNum.size() + 1 > number) {
                        viewHolder.adapterAvailableSelectCb.setChecked(false);
                        Toasty.normal(context.getApplicationContext(), "最多只能选" + number + "个设备", Toasty.LENGTH_SHORT).show();
                        return;
                    }
                    deviceNum.add(data.get(i));
                    data.get(i).setCheck(true);
                    iCheckCallBack.sent(deviceNum);
                } else {
                    //从数组中移除
                    deviceNum.remove(data.get(i));
                    data.get(i).setCheck(false);
                    iCheckCallBack.sent(deviceNum);
                }
            }
        });

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (AvailableAdapter.this.getRecItemClick() != null) {
                    AvailableAdapter.this.getRecItemClick().onItemClick(i, dataBean, TAG_VIEW, viewHolder);
                }
            }
        });
    }

}
