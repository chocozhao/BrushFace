package com.yzf.king.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yzf.king.R;
import com.yzf.king.model.servicesmodels.GetTeamInfoCountDtlResult;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.base.SimpleRecAdapter;
import cn.droidlover.xdroidmvp.kit.KnifeKit;

/**
 * ClaseName：TeamDayUpgradeDtlAdapter
 * Description：
 * Author：chocozhao
 * QQ: 1027313530
 * Createtime：2019/6/14 17:30
 * Modified By：
 * Fixtime：2019/6/14 17:30
 * FixDescription：
 **/

public class TeamDayUpgradeDtlAdapter extends SimpleRecAdapter<GetTeamInfoCountDtlResult.DataBean.InfoBean, TeamDayUpgradeDtlAdapter.ViewHolder> {
    public static final int TAG_VIEW = 0;

    private TeamDayUpgradeDtlAdapter.OnMyItemClickListener listener;

    public void setOnMyItemClickListener(TeamDayUpgradeDtlAdapter.OnMyItemClickListener listener) {
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

    public TeamDayUpgradeDtlAdapter(Context context) {
        super(context);
    }

    @Override
    public TeamDayUpgradeDtlAdapter.ViewHolder newViewHolder(View view) {
        return new TeamDayUpgradeDtlAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final TeamDayUpgradeDtlAdapter.ViewHolder viewHolder, final int i) {
        final GetTeamInfoCountDtlResult.DataBean.InfoBean dataBean = this.data.get(i);
        switch (dataBean.getMerchLevel()) {
            case 0:
                viewHolder.adapterTeamIv.setImageResource(R.mipmap.level_0);
                break;
            case 1:
                viewHolder.adapterTeamIv.setImageResource(R.mipmap.level_1);
                break;
            case 2:
                viewHolder.adapterTeamIv.setImageResource(R.mipmap.level_2);
                break;
            case 3:
                viewHolder.adapterTeamIv.setImageResource(R.mipmap.level_3);
                break;
            case 4:
                viewHolder.adapterTeamIv.setImageResource(R.mipmap.level_4);
                break;
            case 5:
                viewHolder.adapterTeamIv.setImageResource(R.mipmap.level_5);
                break;
            case 10:
                viewHolder.adapterTeamIv.setImageResource(R.mipmap.level_10);
                break;
            case 11:
                viewHolder.adapterTeamIv.setImageResource(R.mipmap.level_11);
                break;
            case 12:
                viewHolder.adapterTeamIv.setImageResource(R.mipmap.level_12);
                break;
            case 13:
                viewHolder.adapterTeamIv.setImageResource(R.mipmap.level_13);
                break;
            case 14:
                viewHolder.adapterTeamIv.setImageResource(R.mipmap.level_14);
                break;
            default:
                viewHolder.adapterTeamIv.setImageResource(R.mipmap.level_2);
                break;
        }
//        viewHolder.adapterTeamIv.setImageResource(R.mipmap.level_19);

        viewHolder.adapterTeamNameTv.setText(dataBean.getMerchName() + "(" + dataBean.getPhone() + ")");
        viewHolder.adapterTeamPhoneIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.myClick(view, dataBean);
                }
            }
        });
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TeamDayUpgradeDtlAdapter.this.getRecItemClick() != null) {
                    TeamDayUpgradeDtlAdapter.this.getRecItemClick().onItemClick(i, dataBean, TAG_VIEW, viewHolder);
                }
            }
        });
    }
}
