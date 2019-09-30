package com.yzf.king.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.yzf.king.R;
import com.yzf.king.kit.AppTools;
import com.yzf.king.model.servicesmodels.GetSubmerchDetailResult;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.base.SimpleRecAdapter;
import cn.droidlover.xdroidmvp.kit.KnifeKit;

public class TeamDetailAdapter extends SimpleRecAdapter<GetSubmerchDetailResult.DataBean, TeamDetailAdapter.ViewHolder> {
    public static final int TAG_VIEW = 0;

    private TeamDetailAdapter.OnMyItemClickListener listener;

    public void setOnMyItemClickListener(TeamDetailAdapter.OnMyItemClickListener listener) {
        this.listener = listener;

    }

    public interface OnMyItemClickListener {
        void myClick(View v, GetSubmerchDetailResult.DataBean item);
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

    public TeamDetailAdapter(Context context) {
        super(context);
    }

    @Override
    public ViewHolder newViewHolder(View view) {
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int i) {
        final GetSubmerchDetailResult.DataBean dataBean = (GetSubmerchDetailResult.DataBean) this.data.get(i);
        switch (dataBean.getMerchLevel()) {
            case "0":
                viewHolder.adapterTeamIv.setImageResource(R.mipmap.level_0);
                break;
            case "1":
                viewHolder.adapterTeamIv.setImageResource(R.mipmap.level_1);
                break;
            case "2":
                viewHolder.adapterTeamIv.setImageResource(R.mipmap.level_2);
                break;
            case "3":
                viewHolder.adapterTeamIv.setImageResource(R.mipmap.level_3);
                break;
            case "4":
                viewHolder.adapterTeamIv.setImageResource(R.mipmap.level_4);
                break;
            case "5":
                viewHolder.adapterTeamIv.setImageResource(R.mipmap.level_5);
                break;
            case "10":
                viewHolder.adapterTeamIv.setImageResource(R.mipmap.level_10);
                break;
            case "11":
                viewHolder.adapterTeamIv.setImageResource(R.mipmap.level_11);
                break;
            case "12":
                viewHolder.adapterTeamIv.setImageResource(R.mipmap.level_12);
                break;
            case "13":
                viewHolder.adapterTeamIv.setImageResource(R.mipmap.level_13);
                break;
            case "14":
                viewHolder.adapterTeamIv.setImageResource(R.mipmap.level_14);
                break;
            default:
                viewHolder.adapterTeamIv.setImageResource(R.mipmap.level_2);
                break;
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
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TeamDetailAdapter.this.getRecItemClick() != null) {
                    TeamDetailAdapter.this.getRecItemClick().onItemClick(i, dataBean, 0, viewHolder);
                }
            }
        });
    }
}