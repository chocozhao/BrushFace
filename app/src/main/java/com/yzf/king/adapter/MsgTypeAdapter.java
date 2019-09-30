package com.yzf.king.adapter;

/**
 * ClaseName：MsgTypeAdapter
 * Description：
 * Author：
 * QQ:
 * Createtime：2019/4/24 15:00
 * Modified By：
 * Fixtime：2019/4/24 15:00
 * FixDescription：
 **/

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yzf.king.R;
import com.yzf.king.model.servicesmodels.GetPushMsgCountResult;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.base.SimpleRecAdapter;
import cn.droidlover.xdroidmvp.kit.KnifeKit;

public class MsgTypeAdapter extends SimpleRecAdapter<GetPushMsgCountResult.DataBean, MsgTypeAdapter.ViewHolder> {
    public static final int TAG_VIEW = 0;


    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.msgtype_textView)
        TextView msgtypeTv;
        @BindView(R.id.msgtype_point_textView)
        TextView msgtypePointTv;
        @BindView(R.id.msgtype_iv)
        ImageView msgtypeIv;
        @BindView(R.id.msgtype_sys)
        LinearLayout msgtypeSys;

        public ViewHolder(View view) {
            super(view);
            KnifeKit.bind(this, view);
        }
    }


    public int getLayoutId() {
        return R.layout.adapter_msg_type;
    }

    public MsgTypeAdapter(Context context) {
        super(context);
    }

    public ViewHolder newViewHolder(View view) {
        return new ViewHolder(view);
    }

    public void onBindViewHolder(final ViewHolder viewHolder, final int i) {
        final GetPushMsgCountResult.DataBean dataBean = this.data.get(i);
        viewHolder.msgtypeTv.setText(dataBean.getTypeName());
        if (dataBean.isHasNews()) {
            viewHolder.msgtypePointTv.setVisibility(View.VISIBLE);
            viewHolder.msgtypePointTv.setText("" + dataBean.getNumber());
        } else {
            viewHolder.msgtypePointTv.setVisibility(View.GONE);
        }
        switch (dataBean.getType()) {
            case "00":
                viewHolder.msgtypeIv.setImageResource(R.mipmap.msgtype_sys);
                break;
            case "01":
                viewHolder.msgtypeIv.setImageResource(R.mipmap.msgtype_trade);
                break;
            case "02":
                viewHolder.msgtypeIv.setImageResource(R.mipmap.msgtype_plan);
                break;
            case "03":
                viewHolder.msgtypeIv.setImageResource(R.mipmap.msgtype_profit);
                break;
            case "04":
                viewHolder.msgtypeIv.setImageResource(R.mipmap.msgtype_other);
                break;
            default:
                break;

        }
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getRecItemClick() != null) {
                    getRecItemClick().onItemClick(i, dataBean, TAG_VIEW, viewHolder);
                }
            }
        });
    }
}