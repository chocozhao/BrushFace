package com.yzf.king.adapter;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yzf.king.R;
import com.yzf.king.kit.AppTools;
import com.yzf.king.kit.AppUser;
import com.yzf.king.model.servicesmodels.GetPlanChannelInfoResult;
import com.yzf.king.widget.StateButton;

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

public class ChannelAdapters extends SimpleRecAdapter<GetPlanChannelInfoResult.DataBean, ChannelAdapters.ViewHolder> {

    public static final int BT_VIEW = 1;
    public static final int TAG_VIEW = 0;
    public static final int SUP_VIEW = 2;


    private OnMyItemClickListener listener;

    public void setOnMyItemClickListener(OnMyItemClickListener listener) {
        this.listener = listener;

    }


    public interface OnMyItemClickListener {
        void myClick(View v, int pos, GetPlanChannelInfoResult.DataBean item, int tag);

        void mLongClick(View v, int pos, GetPlanChannelInfoResult.DataBean item);
    }

    public ChannelAdapters(Context context) {
        super(context);
    }

    @Override
    public ViewHolder newViewHolder(View itemView) {
        return new ViewHolder(itemView);
    }

    @Override
    public int getLayoutId() {
        return R.layout.adapter_channels;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final GetPlanChannelInfoResult.DataBean item = data.get(position);
        holder.channelMinamtAdapter.setText(AppTools.formatF2Y(item.getMinAmt()));
        holder.channelMaxamtAdapter.setText(AppTools.formatF2Y(item.getMaxAmt()));
        String bankAbbr=AppUser.getInstance().getCardInfoBean().getBankAbbr();
        if (item.getPlanType() == 1) {
            if (bankAbbr!=null&&item.getBankAbbr().contains(bankAbbr)) {
                holder.channelFeeAdapter.setText(item.getReplaceFeeRate());
                holder.channelNameAdapter.setText(item.getChannelName());
                holder.channelSupportAdapter.setText("交通银行、浦发银行");
                holder.channelSupportAdapter.setTextColor(context.getResources().getColor(R.color.text_yellow));
                if (listener != null) {
                    holder.channelTipsAdapter.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            listener.myClick(v, position, item,TAG_VIEW);
                        }
                    });
                    holder.channelBtAdapter.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View view) {
                            listener.myClick(view, position, item, BT_VIEW);
                        }
                    });
                }
            }else{
                holder.channelNameAdapter.setText(item.getChannelName());
                holder.channelFeeAdapter.setText(item.getFeeRate());
                holder.channelSupportAdapter.setText("查看明细");
                holder.channelSupportAdapter.setTextColor(context.getResources().getColor(R.color.text_yellow));
                if (listener != null) {
                    holder.channelTipsAdapter.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            listener.myClick(v, position, item,TAG_VIEW);
                        }
                    });
                    holder.channelBtAdapter.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View view) {
                            listener.myClick(view, position, item, BT_VIEW);
                        }
                    });
                    holder.channelSupportAdapter.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View view) {
                            listener.myClick(view, position, item, SUP_VIEW);
                        }
                    });
                }
            }
        }else{
            holder.channelNameAdapter.setText(item.getChannelName());
            holder.channelFeeAdapter.setText(item.getFeeRate());
            holder.channelSupportAdapter.setText("查看明细");
            holder.channelSupportAdapter.setTextColor(context.getResources().getColor(R.color.text_yellow));
            if (listener != null) {
                holder.channelTipsAdapter.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listener.myClick(v, position, item,TAG_VIEW);
                    }
                });
                holder.channelBtAdapter.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        listener.myClick(view, position, item, BT_VIEW);
                    }
                });
                holder.channelSupportAdapter.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        listener.myClick(view, position, item, SUP_VIEW);
                    }
                });
            }
        }
        holder.channelTimeAdapter.setText(item.getTransTime());
        holder.channelBtAdapter.setText(item.getActionName());
        if (item.getPlanType() == 1) {
            holder.channelIvAdapter.setBackgroundResource(R.mipmap.icon_channel_plan);
        } else if (item.getPlanType() == 2) {
            holder.channelIvAdapter.setBackgroundResource(R.mipmap.icon_channel_auth);
        } else {
            holder.channelIvAdapter.setBackgroundResource(R.mipmap.icon_channel_plan);
        }

    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.channel_iv_adapter)
        ImageView channelIvAdapter;
        @BindView(R.id.channel_name_adapter)
        TextView channelNameAdapter;
        @BindView(R.id.channel_tips_adapter)
        ImageView channelTipsAdapter;
        @BindView(R.id.channel_bt_adapter)
        StateButton channelBtAdapter;
        @BindView(R.id.channel_minamt_adapter)
        TextView channelMinamtAdapter;
        @BindView(R.id.channel_maxamt_adapter)
        TextView channelMaxamtAdapter;
        @BindView(R.id.channel_fee_adapter)
        TextView channelFeeAdapter;
        @BindView(R.id.channel_time_adapter)
        TextView channelTimeAdapter;
        @BindView(R.id.channel_support_adapter)
        TextView channelSupportAdapter;

        public ViewHolder(View itemView) {
            super(itemView);
            KnifeKit.bind(this, itemView);
        }
    }

}
