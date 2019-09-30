package com.yzf.king.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yzf.king.R;
import com.yzf.king.kit.AppTools;
import com.yzf.king.model.servicesmodels.GetPushMsgJGResults;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.base.SimpleRecAdapter;
import cn.droidlover.xdroidmvp.kit.Kits;
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

public class MsgAdapter extends SimpleRecAdapter<GetPushMsgJGResults.DataBean, MsgAdapter.ViewHolder> {

    public static final int TAG_VIEW = 0;


    public MsgAdapter(Context context) {
        super(context);
    }

    @Override
    public ViewHolder newViewHolder(View itemView) {
        return new ViewHolder(itemView);
    }

    @Override
    public int getLayoutId() {
        return R.layout.adapter_msg;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final GetPushMsgJGResults.DataBean item = data.get(position);
        holder.msgAdapterTitleTv.setText(item.getMsgTitle());
        holder.msgAdapterTimeTv.setText(Kits.Date.getYmdhms(Long.valueOf(item.getMsgTime())));
        holder.msgAdapterContentTv.setText(item.getMsgContent());
        if (item.getMsgType().equals("00")) {
            if (item.getMsgStatus().equals("0")) {
                holder.msgAdapterImgIv.setImageDrawable(context.getResources().getDrawable(R.mipmap.msg_sys_read));
            } else {
                holder.msgAdapterImgIv.setImageDrawable(context.getResources().getDrawable(R.mipmap.msg_sys));
            }
        } else if (item.getMsgType().equals("01")) {
            if (item.getMsgStatus().equals("0")) {
                holder.msgAdapterImgIv.setImageDrawable(context.getResources().getDrawable(R.mipmap.msg_trans_read));
            } else {
                holder.msgAdapterImgIv.setImageDrawable(context.getResources().getDrawable(R.mipmap.msg_trans));
            }
        } else if (item.getMsgType().equals("02")) {
            if (item.getMsgStatus().equals("0")) {
                holder.msgAdapterImgIv.setImageDrawable(context.getResources().getDrawable(R.mipmap.msg_cardmanage_read));
            } else {
                holder.msgAdapterImgIv.setImageDrawable(context.getResources().getDrawable(R.mipmap.msg_cardmanage));
            }
        } else if (item.getMsgType().equals("03")) {
            if (item.getMsgStatus().equals("0")) {
                holder.msgAdapterImgIv.setImageDrawable(context.getResources().getDrawable(R.mipmap.msg_fission_read));
            } else {
                holder.msgAdapterImgIv.setImageDrawable(context.getResources().getDrawable(R.mipmap.msg_fission));
            }
        }
        else {
            if (item.getMsgStatus().equals("0")) {
                holder.msgAdapterImgIv.setImageDrawable(context.getResources().getDrawable(R.mipmap.msg_user_read));
            } else {
                holder.msgAdapterImgIv.setImageDrawable(context.getResources().getDrawable(R.mipmap.msg_user));
            }
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


    private String setTransResult(String result) {
        if (!AppTools.isEmpty(result)) {
            if (result.equals("01")) {
                result = "交易成功";
            } else {
                result = "交易失败";
            }
        }
        return result;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.msg_adapter_img_iv)
        ImageView msgAdapterImgIv;
        @BindView(R.id.msg_adapter_title_tv)
        TextView msgAdapterTitleTv;
        @BindView(R.id.msg_adapter_time_tv)
        TextView msgAdapterTimeTv;
        @BindView(R.id.msg_adapter_content_tv)
        TextView msgAdapterContentTv;

        public ViewHolder(View itemView) {
            super(itemView);
            KnifeKit.bind(this, itemView);
        }
    }


}
