package com.yzf.king.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.yzf.king.R;
import com.yzf.king.kit.AppTools;
import com.yzf.king.model.servicesmodels.GetTeamInfoResult;
import com.yzf.king.model.servicesmodels.GetTeamTermInfoResult;

import java.util.ArrayList;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.base.SimpleRecAdapter;
import cn.droidlover.xdroidmvp.imageloader.ILFactory;
import cn.droidlover.xdroidmvp.kit.Kits;
import cn.droidlover.xdroidmvp.kit.KnifeKit;

/**
 * ClaseName：TeamActivateAdapter
 * Description：
 * Author：chocozhao
 * QQ: 1027313530
 * Createtime：2019/7/18 15:00
 * Modified By：
 * Fixtime：2019/7/18 15:00
 * FixDescription：
 **/

public class TeamActivateAdapter extends SimpleRecAdapter<GetTeamTermInfoResult.DataBean.DataListBean, TeamActivateAdapter.ViewHolder> {

    public static final int TAG_VIEW = 0;


    private String shopmanage;
    private int number;
    private String teamshopname;
    private ArrayList<GetTeamInfoResult.DataBean.DataListBean> homeTests = new ArrayList();
    private static AvailableAdapter.CheckCallBack iCheckCallBack;

    public interface CheckCallBack {
        void sent(ArrayList<GetTeamInfoResult.DataBean.DataListBean> list);
    }

    public static void setCallback(AvailableAdapter.CheckCallBack callBack) {
        iCheckCallBack = callBack;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.adapter_available_date_tv)
        TextView adapterAvailableDateTv;
        @BindView(R.id.adapter_available_status_iv)
        ImageView adapterAvailableStatusIv;
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

        public ViewHolder(View view) {
            super(view);
            KnifeKit.bind(this, view);
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.adapter_available;
    }

    public TeamActivateAdapter(Context context) {
        super(context);
    }
    public TeamActivateAdapter(Context context, String teamshopname) {
        super(context);
        this.teamshopname = teamshopname;
    }

    public TeamActivateAdapter(Context context, String shopmanage, int number) {
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
        final GetTeamTermInfoResult.DataBean.DataListBean dataBean = (GetTeamTermInfoResult.DataBean.DataListBean) this.data.get(i);
        viewHolder.adapterAvailableDateTv.setText(Kits.Date.getYmdhms(dataBean.getUpdateTime()));
        viewHolder.adapterAvailablePackageTv.setText("套餐："+dataBean.getMealId());
        viewHolder.adaperAvailableTitleTv.setText(dataBean.getModelName());
//        viewHolder.adapterAvailableSerialTv.setText("序列号："+dataBean.getTermId());
        ILFactory.getLoader().loadImage(dataBean.getLogoUrl(), viewHolder.adapterAvailableHearIv);
        if (AppTools.isEmpty(shopmanage)) {
            viewHolder.adapterAvailableSelectCb.setVisibility(View.GONE);
        } else {
            viewHolder.adapterAvailableSelectCb.setVisibility(View.VISIBLE);
        }
        if (!AppTools.isEmpty(teamshopname)) {
            viewHolder.adapterAvailableSerialTv.setText("序列号：" + dataBean.getTermSn());
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
//        if (dataBean.isCheck()) {
//            viewHolder.adapterAvailableSelectCb.setChecked(true);
//        } else {
//            viewHolder.adapterAvailableSelectCb.setChecked(false);
//        }

//        viewHolder.adapterAvailableSelectCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if (isChecked) {
//                    //添加到数组
//                    if (homeTests.size() + 1 > number) {
//                        viewHolder.adapterAvailableSelectCb.setChecked(false);
//                        Toasty.normal(context.getApplicationContext(), "最多只能选" + number + "个设备", Toasty.LENGTH_SHORT).show();
//                        return;
//                    }
//                    homeTests.add(data.get(i));
//                    data.get(i).setCheck(true);
//                    iCheckCallBack.sent(homeTests);
//                } else {
//                    //从数组中移除
//                    homeTests.remove(data.get(i));
//                    data.get(i).setCheck(false);
//                    iCheckCallBack.sent(homeTests);
//                }
//            }
//        });

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TeamActivateAdapter.this.getRecItemClick() != null) {
                    TeamActivateAdapter.this.getRecItemClick().onItemClick(i, dataBean, TAG_VIEW, viewHolder);
                }
            }
        });
    }
}
