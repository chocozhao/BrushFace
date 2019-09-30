package com.yzf.king.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.yzf.king.R;
import com.yzf.king.model.servicesmodels.GetChannelMerchResult;

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

public class MerchPickAdapter extends SimpleRecAdapter<GetChannelMerchResult.DataBean, MerchPickAdapter.ViewHolder> {

    public static final int TAG_VIEW = 0;


    public MerchPickAdapter(Context context) {
        super(context);
    }

    @Override
    public ViewHolder newViewHolder(View itemView) {
        return new ViewHolder(itemView);
    }

    @Override
    public int getLayoutId() {
        return R.layout.adapter_merchpick;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final GetChannelMerchResult.DataBean item = data.get(position);
        holder.adapterMerchpickNameTv.setText(item.getMerchName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getRecItemClick() != null) {
                    getRecItemClick().onItemClick(position, item, TAG_VIEW, holder);
                }
            }
        });

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.adapter_merchpick_name_tv)
        TextView adapterMerchpickNameTv;
        @BindView(R.id.adapter_merchpick_time_tv)
        TextView adapterMerchpickTimeTv;


        public ViewHolder(View itemView) {
            super(itemView);
            KnifeKit.bind(this, itemView);
        }
    }

}
