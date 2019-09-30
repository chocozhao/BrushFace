package com.yzf.king.adapter;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.yzf.king.R;
import com.yzf.king.model.servicesmodels.GetChannelInfoResults;

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

public class ChannelAdapter extends SimpleRecAdapter<GetChannelInfoResults.DataBean, ChannelAdapter.ViewHolder> {

    public static final int TAG_VIEW = 0;


    private OnMyItemClickListener listener;

    public void setOnMyItemClickListener(OnMyItemClickListener listener) {
        this.listener = listener;

    }


    public interface OnMyItemClickListener {
        void myClick(View v, int pos, GetChannelInfoResults.DataBean item);

        void mLongClick(View v, int pos, GetChannelInfoResults.DataBean item);
    }

    public ChannelAdapter(Context context) {
        super(context);
    }

    @Override
    public ViewHolder newViewHolder(View itemView) {
        return new ViewHolder(itemView);
    }

    @Override
    public int getLayoutId() {
        return R.layout.adapter_channel;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final GetChannelInfoResults.DataBean item = data.get(position);
        holder.channelNameAdapter.setText(item.getBranchName());
        holder.channelDescAdapter.setText("(" + item.getBranchDesc() + ")");
        if (item.getStatus() != null && item.getStatus().equals("2")) {
            holder.channelStatusAdapter.setText("已开通");
            holder.channelStatusAdapter.setTextColor(context.getResources().getColor(R.color.text_red));
        } else {
            holder.channelStatusAdapter.setText("未开通");
        }
        if (listener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.myClick(v, position, item);
                }
            });
        }


    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.channel_name_adapter)
        TextView channelNameAdapter;
        @BindView(R.id.channel_desc_adapter)
        TextView channelDescAdapter;
        @BindView(R.id.channel_status_adapter)
        TextView channelStatusAdapter;

        public ViewHolder(View itemView) {
            super(itemView);
            KnifeKit.bind(this, itemView);
        }
    }

}
