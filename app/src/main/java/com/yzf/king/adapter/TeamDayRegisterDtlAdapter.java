package com.yzf.king.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.yzf.king.R;
import com.yzf.king.model.servicesmodels.GetTeamInfoCountDtlResult;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.base.SimpleRecAdapter;
import cn.droidlover.xdroidmvp.kit.KnifeKit;

/**
 * ClaseName：TeamDayRegisterDtlAdapter
 * Description：
 * Author：chocozhao
 * QQ: 1027313530
 * Createtime：2019/5/17 17:21
 * Modified By：
 * Fixtime：2019/5/17 17:21
 * FixDescription：
 **/

public class TeamDayRegisterDtlAdapter extends SimpleRecAdapter<GetTeamInfoCountDtlResult.DataBean.InfoBean, TeamDayRegisterDtlAdapter.ViewHolder> {
    public static final int TAG_VIEW = 0;

    private TeamDayRegisterDtlAdapter.OnMyItemClickListener listener;

    public void setOnMyItemClickListener(TeamDayRegisterDtlAdapter.OnMyItemClickListener listener) {
        this.listener = listener;

    }

    public interface OnMyItemClickListener {
        void myClick(View v, GetTeamInfoCountDtlResult.DataBean.InfoBean item);
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.adapter_team_iv)
        ImageView adapterTeamIv;
        @BindView(R.id.adapter_team_name_tv)
        TextView adapterTeamNameTv;
        @BindView(R.id.adapter_team_phone_iv)
        ImageView adapterTeamPhoneIv;

        public ViewHolder(View view) {
            super(view);
            KnifeKit.bind(this, view);
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.adapter_team_detail;
    }

    public TeamDayRegisterDtlAdapter(Context context) {
        super(context);
    }

    @Override
    public TeamDayRegisterDtlAdapter.ViewHolder newViewHolder(View view) {
        return new TeamDayRegisterDtlAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final TeamDayRegisterDtlAdapter.ViewHolder viewHolder, final int i) {
        final GetTeamInfoCountDtlResult.DataBean.InfoBean dataBean = this.data.get(i);

        if (dataBean.getMerchLevel() >= 2) {
            viewHolder.adapterTeamIv.setImageResource(R.mipmap.level_2);
        } else {
            viewHolder.adapterTeamIv.setImageResource(R.mipmap.level_1);
        }
        viewHolder.adapterTeamNameTv.setText(dataBean.getMerchName() + "(" + dataBean.getPhone() + ")");
        viewHolder.adapterTeamPhoneIv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.myClick(view, dataBean);
                }
            }
        });
        viewHolder.itemView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TeamDayRegisterDtlAdapter.this.getRecItemClick() != null) {
                    TeamDayRegisterDtlAdapter.this.getRecItemClick().onItemClick(i, dataBean, TAG_VIEW, viewHolder);
                }
            }
        });
    }
}