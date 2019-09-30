package com.yzf.king.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yzf.king.R;
import com.yzf.king.kit.AppTools;
import com.yzf.king.model.servicesmodels.GetSunMerchInfoListResult;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.base.SimpleRecAdapter;
import cn.droidlover.xdroidmvp.kit.KnifeKit;

/**
 * ClaseName：SignDtlAdapter
 * Description：
 * Author：chocozhao
 * QQ: 1027313530
 * Createtime：2019/8/22 09:36
 * Modified By：
 * Fixtime：2019/8/22 09:36
 * FixDescription：
 **/

public class SignDtlAdapter extends SimpleRecAdapter<GetSunMerchInfoListResult.DataBean.SubListBean, SignDtlAdapter.ViewHolder> {

    public static final int TAG_VIEW = 0;


    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.adapter_sign_dtl_iv)
        ImageView adapterSignDtlIv;
        @BindView(R.id.adapter_sign_dtl_status)
        TextView adapterSignDtlStatus;
        @BindView(R.id.adapter_sign_dtl_name)
        TextView adapterSignDtlName;
        @BindView(R.id.adapter_sign_dtl_rl)
        RelativeLayout adapterSignDtlRl;
        @BindView(R.id.adapter_sign_dtl_remarks)
        TextView adapterSignDtlRemarks;

        public ViewHolder(View view) {
            super(view);
            KnifeKit.bind(this, view);
        }

    }

    @Override
    public int getLayoutId() {
        return R.layout.adapter_sign_dtl;
    }

    public SignDtlAdapter(Context context) {
        super(context);
    }

    @Override
    public ViewHolder newViewHolder(View view) {
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int i) {
        final GetSunMerchInfoListResult.DataBean.SubListBean dataBean = (GetSunMerchInfoListResult.DataBean.SubListBean) this.data.get(i);
        if ("ALIPAY".equals(dataBean.getChannelCode())) {
            viewHolder.adapterSignDtlName.setText("支付宝签约");
            viewHolder.adapterSignDtlIv.setImageResource(R.mipmap.trans_zfb);
            switch (dataBean.getOrderStatus()) {
                case "00":
                    if (!AppTools.isEmpty(dataBean.getAddStatus())) {
                        switch (dataBean.getAddStatus()) {
                            case "0":
                            case "1":
                            case "2":
                            case "3":
                            case "4":
                            case "5":
                            case "6":
                            case "7":
                                viewHolder.adapterSignDtlStatus.setText("请补交材料");
                                viewHolder.adapterSignDtlStatus.setTextColor(context.getResources().getColor(R.color.btn_yellow_dark));
                                if (!AppTools.isEmpty(dataBean.getRemark())) {
                                    viewHolder.adapterSignDtlRemarks.setText("备注：" + dataBean.getRemark());
                                    viewHolder.adapterSignDtlRemarks.setTextColor(context.getResources().getColor(R.color.btn_yellow_dark));
                                    viewHolder.adapterSignDtlRemarks.setVisibility(View.VISIBLE);
                                } else {
                                    viewHolder.adapterSignDtlRemarks.setVisibility(View.GONE);
                                }
                                break;
                            case "8":
                                //审核中
                                viewHolder.adapterSignDtlStatus.setText("审核中");
                                viewHolder.adapterSignDtlStatus.setTextColor(context.getResources().getColor(R.color.text_tip));
                                break;
                            case "9":
                                //审核通过
                                viewHolder.adapterSignDtlStatus.setText("初审通过");
                                viewHolder.adapterSignDtlStatus.setTextColor(context.getResources().getColor(R.color.text_tip));
                                break;
                            case "10":
                                //审核通过
                                viewHolder.adapterSignDtlStatus.setText("审核拒绝");
                                viewHolder.adapterSignDtlStatus.setTextColor(context.getResources().getColor(R.color.text_tip));
                                break;
                            default:
                                viewHolder.adapterSignDtlStatus.setText("审核中");
                                break;
                        }
                    } else {
                        //审核中
                        viewHolder.adapterSignDtlStatus.setText("审核中");
                    }
                    break;
                case "01":
                    viewHolder.adapterSignDtlStatus.setText("签约中");
                    break;
                case "02":
                    viewHolder.adapterSignDtlStatus.setText("签约拒绝");
                    break;
                case "03":
                    viewHolder.adapterSignDtlStatus.setText("待开通");
                    break;
                case "04":
                    viewHolder.adapterSignDtlStatus.setText("待授权");
                    viewHolder.adapterSignDtlStatus.setTextColor(context.getResources().getColor(R.color.btn_blue));

                    break;
                case "05":
                    viewHolder.adapterSignDtlStatus.setText("完成");
                    break;
                case "09":
                    viewHolder.adapterSignDtlStatus.setText("即将上线");
                    break;
                default:
                    viewHolder.adapterSignDtlStatus.setText("未知状态");
                    break;
            }
        } else if ("WXPAY".equals(dataBean.getChannelCode())) {
            viewHolder.adapterSignDtlName.setText("微信签约");
            viewHolder.adapterSignDtlIv.setImageResource(R.mipmap.trans_wx);
            switch (dataBean.getOrderStatus()) {
                case "00":
                    if (!AppTools.isEmpty(dataBean.getAddStatus())) {
                        switch (dataBean.getAddStatus()) {
                            case "0":
                            case "1":
                            case "2":
                            case "3":
                            case "4":
                            case "5":
                            case "6":
                            case "7":
                                viewHolder.adapterSignDtlStatus.setText("请补交材料");
                                viewHolder.adapterSignDtlStatus.setTextColor(context.getResources().getColor(R.color.btn_yellow_dark));
                                if (!AppTools.isEmpty(dataBean.getRemark())) {
                                    viewHolder.adapterSignDtlRemarks.setText("备注：" + dataBean.getRemark());
                                    viewHolder.adapterSignDtlRemarks.setTextColor(context.getResources().getColor(R.color.btn_yellow_dark));
                                    viewHolder.adapterSignDtlRemarks.setVisibility(View.VISIBLE);
                                } else {
                                    viewHolder.adapterSignDtlRemarks.setVisibility(View.GONE);
                                }
                                break;
                            case "8":
                                //审核中
                                viewHolder.adapterSignDtlStatus.setText("审核中");
                                viewHolder.adapterSignDtlStatus.setTextColor(context.getResources().getColor(R.color.text_tip));
                                break;
                            case "9":
                                //审核通过
                                viewHolder.adapterSignDtlStatus.setText("初审通过");
                                viewHolder.adapterSignDtlStatus.setTextColor(context.getResources().getColor(R.color.text_tip));
                                break;
                            case "10":
                                //审核通过
                                viewHolder.adapterSignDtlStatus.setText("审核拒绝");
                                viewHolder.adapterSignDtlStatus.setTextColor(context.getResources().getColor(R.color.text_tip));
                                break;
                            default:
                                viewHolder.adapterSignDtlStatus.setText("审核中");
                                break;
                        }
                    } else {
                        //审核中
                        viewHolder.adapterSignDtlStatus.setText("审核中");
                    }
                    break;
                case "01":
                    viewHolder.adapterSignDtlStatus.setText("签约中");
                    break;
                case "02":
                    viewHolder.adapterSignDtlStatus.setText("签约拒绝");
                    break;
                case "03":
                    viewHolder.adapterSignDtlStatus.setText("待开通");
                    break;
                case "04":
                    viewHolder.adapterSignDtlStatus.setText("待授权");
                    viewHolder.adapterSignDtlStatus.setTextColor(context.getResources().getColor(R.color.btn_blue));
                    break;
                case "05":
                    viewHolder.adapterSignDtlStatus.setText("完成");
                    break;
                case "09":
                    viewHolder.adapterSignDtlStatus.setText("即将上线");
                    break;
                default:
                    viewHolder.adapterSignDtlStatus.setText("未知状态");
                    break;
            }
        } else if ("UNIONPAY".equals(dataBean.getChannelCode())) {
            viewHolder.adapterSignDtlName.setText("银联签约");
            viewHolder.adapterSignDtlIv.setImageResource(R.mipmap.trans_yl);
            switch (dataBean.getOrderStatus()) {
                case "00":
                    if (!AppTools.isEmpty(dataBean.getAddStatus())) {
                        switch (dataBean.getAddStatus()) {
                            case "0":
                            case "1":
                            case "2":
                            case "3":
                            case "4":
                            case "5":
                            case "6":
                            case "7":
                                viewHolder.adapterSignDtlStatus.setText("请补交材料");
                                viewHolder.adapterSignDtlStatus.setTextColor(context.getResources().getColor(R.color.btn_yellow_dark));
                                if (!AppTools.isEmpty(dataBean.getRemark())) {
                                    viewHolder.adapterSignDtlRemarks.setText("备注：" + dataBean.getRemark());
                                    viewHolder.adapterSignDtlRemarks.setTextColor(context.getResources().getColor(R.color.btn_yellow_dark));
                                    viewHolder.adapterSignDtlRemarks.setVisibility(View.VISIBLE);
                                } else {
                                    viewHolder.adapterSignDtlRemarks.setVisibility(View.GONE);
                                }
                                break;
                            case "8":
                                //审核中
                                viewHolder.adapterSignDtlStatus.setText("审核中");
                                viewHolder.adapterSignDtlStatus.setTextColor(context.getResources().getColor(R.color.text_tip));
                                break;
                            case "9":
                                //审核通过
                                viewHolder.adapterSignDtlStatus.setText("初审通过");
                                viewHolder.adapterSignDtlStatus.setTextColor(context.getResources().getColor(R.color.text_tip));
                                break;
                            case "10":
                                //审核通过
                                viewHolder.adapterSignDtlStatus.setText("审核拒绝");
                                viewHolder.adapterSignDtlStatus.setTextColor(context.getResources().getColor(R.color.text_tip));
                                break;
                            default:
                                viewHolder.adapterSignDtlStatus.setText("即将上线");
                                break;
                        }
                    } else {
                        //审核中
                        viewHolder.adapterSignDtlStatus.setText("即将上线");
                    }
                    break;
                case "01":
                    viewHolder.adapterSignDtlStatus.setText("签约中");
                    break;
                case "02":
                    viewHolder.adapterSignDtlStatus.setText("签约拒绝");
                    break;
                case "03":
                    viewHolder.adapterSignDtlStatus.setText("待开通");
                    break;
                case "04":
                    viewHolder.adapterSignDtlStatus.setText("待授权");
                    viewHolder.adapterSignDtlStatus.setTextColor(context.getResources().getColor(R.color.btn_blue));
                    break;
                case "05":
                    viewHolder.adapterSignDtlStatus.setText("完成");
                    break;
                case "09":
                    viewHolder.adapterSignDtlStatus.setText("即将上线");
                    break;
                default:
                    viewHolder.adapterSignDtlStatus.setText("即将上线");
                    break;
            }
        } else if ("HKPAY".equals(dataBean.getChannelCode())) {
            viewHolder.adapterSignDtlName.setText("间联签约");
            viewHolder.adapterSignDtlIv.setImageResource(R.mipmap.trans_hkpay);
            switch (dataBean.getOrderStatus()) {
                case "00":
                    if (!AppTools.isEmpty(dataBean.getAddStatus())) {
                        switch (dataBean.getAddStatus()) {
                            case "0":
                            case "1":
                            case "2":
                            case "3":
                            case "4":
                            case "5":
                            case "6":
                            case "7":
                                viewHolder.adapterSignDtlStatus.setText("请补交材料");
                                viewHolder.adapterSignDtlStatus.setTextColor(context.getResources().getColor(R.color.btn_yellow_dark));
                                if (!AppTools.isEmpty(dataBean.getRemark())) {
                                    viewHolder.adapterSignDtlRemarks.setText("备注：" + dataBean.getRemark());
                                    viewHolder.adapterSignDtlRemarks.setTextColor(context.getResources().getColor(R.color.btn_yellow_dark));
                                    viewHolder.adapterSignDtlRemarks.setVisibility(View.VISIBLE);
                                } else {
                                    viewHolder.adapterSignDtlRemarks.setVisibility(View.GONE);
                                }
                                break;
                            case "8":
                                //审核中
                                viewHolder.adapterSignDtlStatus.setText("审核中");
                                viewHolder.adapterSignDtlStatus.setTextColor(context.getResources().getColor(R.color.text_tip));
                                break;
                            case "9":
                                //审核通过
                                viewHolder.adapterSignDtlStatus.setText("初审通过");
                                viewHolder.adapterSignDtlStatus.setTextColor(context.getResources().getColor(R.color.text_tip));
                                break;
                            case "10":
                                //审核通过
                                viewHolder.adapterSignDtlStatus.setText("审核拒绝");
                                viewHolder.adapterSignDtlStatus.setTextColor(context.getResources().getColor(R.color.text_tip));
                                break;
                            default:
                                viewHolder.adapterSignDtlStatus.setText("即将上线");
                                break;
                        }
                    } else {
                        //审核中
                        viewHolder.adapterSignDtlStatus.setText("即将上线");
                    }
                    break;
                case "01":
                    viewHolder.adapterSignDtlStatus.setText("签约中");
                    break;
                case "02":
                    viewHolder.adapterSignDtlStatus.setText("签约拒绝");
                    break;
                case "03":
                    viewHolder.adapterSignDtlStatus.setText("待开通");
                    break;
                case "04":
                    viewHolder.adapterSignDtlStatus.setText("待授权");
                    viewHolder.adapterSignDtlStatus.setTextColor(context.getResources().getColor(R.color.btn_blue));
                    break;
                case "05":
                    viewHolder.adapterSignDtlStatus.setText("完成");
                    break;
                case "09":
                    viewHolder.adapterSignDtlStatus.setText("即将上线");
                    break;
                default:
                    viewHolder.adapterSignDtlStatus.setText("即将上线");
                    break;
            }

            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (SignDtlAdapter.this.getRecItemClick() != null) {
                        SignDtlAdapter.this.getRecItemClick().onItemClick(i, dataBean, 0, viewHolder);
                    }
                }
            });
        }

    }
}
