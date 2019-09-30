package com.yzf.king.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.yzf.king.App;
import com.yzf.king.R;
import com.yzf.king.kit.AppUser;
import com.yzf.king.model.servicesmodels.GetTeamInfoResult;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.base.SimpleRecAdapter;
import cn.droidlover.xdroidmvp.kit.KnifeKit;

public class TeamAdapter extends SimpleRecAdapter<GetTeamInfoResult.DataBean.DataListBean, TeamAdapter.ViewHolder> {
    public static final int TAG_VIEW = 0;


    private OnMyItemClickListener listener;

    public void setOnMyItemClickListener(OnMyItemClickListener listener) {
        this.listener = listener;

    }

    public interface OnMyItemClickListener {
        void myClick(View v, GetTeamInfoResult.DataBean.DataListBean item);
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.adapter_team_iv)
        ImageView adapterTeamIv;
        @BindView(R.id.adapter_name_tv)
        TextView adapterNameTv;
        @BindView(R.id.adapter_phon_iv)
        ImageView adapterPhonIv;
        @BindView(R.id.adapter_levelone_tv)
        TextView adapterLeveloneTv;
        @BindView(R.id.adapter_leveltwo_tv)
        TextView adapterLeveltwoTv;
        @BindView(R.id.adapter_levelthree_tv)
        TextView adapterLevelthreeTv;

        public ViewHolder(View view) {
            super(view);
            KnifeKit.bind(this, view);
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.adapter_team;
    }

    public TeamAdapter(Context context) {
        super(context);
    }

    @Override
    public ViewHolder newViewHolder(View view) {
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int i) {
        final GetTeamInfoResult.DataBean.DataListBean dataBean = this.data.get(i);
        viewHolder.adapterNameTv.setText(dataBean.getMerchName());
        switch (dataBean.getShopFlag()) {
            case 0:
                viewHolder.adapterTeamIv.setImageResource(R.mipmap.icon_salesman_shop);
                if (dataBean.getMerchLevel() == 1) {
                    viewHolder.adapterLeveloneTv.setVisibility(View.VISIBLE);
                    viewHolder.adapterLeveltwoTv.setVisibility(View.GONE);
                    viewHolder.adapterLeveloneTv.setText(AppUser.getInstance().getMerchlevelname());
                } else if (dataBean.getMerchLevel() == 2) {
                    viewHolder.adapterLeveltwoTv.setVisibility(View.GONE);
                    viewHolder.adapterLeveloneTv.setVisibility(View.VISIBLE);
                    viewHolder.adapterLeveloneTv.setText(AppUser.getInstance().getMerchlevelname2());
                } else {
                    viewHolder.adapterLeveloneTv.setVisibility(View.GONE);
                    viewHolder.adapterLeveltwoTv.setVisibility(View.GONE);
                }

                if (dataBean.getAgentFlag() == 1) {
                    viewHolder.adapterLeveltwoTv.setVisibility(View.VISIBLE);
                    viewHolder.adapterLeveltwoTv.setText("  "+AppUser.getInstance().getAgentlevelname());
                } else {
                    viewHolder.adapterLeveltwoTv.setVisibility(View.GONE);
                }
                viewHolder.adapterLevelthreeTv.setVisibility(View.GONE);
                break;
            case 1:
                viewHolder.adapterTeamIv.setImageResource(R.mipmap.icon_investors_shop);
                if (dataBean.getMerchLevel() == 1) {
                    viewHolder.adapterLeveloneTv.setVisibility(View.VISIBLE);
                    viewHolder.adapterLeveltwoTv.setVisibility(View.GONE);
                    viewHolder.adapterLeveloneTv.setText(AppUser.getInstance().getMerchlevelname());
                } else if (dataBean.getMerchLevel() == 2) {
                    viewHolder.adapterLeveltwoTv.setVisibility(View.GONE);
                    viewHolder.adapterLeveloneTv.setVisibility(View.VISIBLE);
                    viewHolder.adapterLeveloneTv.setText(AppUser.getInstance().getMerchlevelname2());
                } else {
                    viewHolder.adapterLeveloneTv.setVisibility(View.GONE);
                    viewHolder.adapterLeveltwoTv.setVisibility(View.GONE);
                }

                if (dataBean.getAgentFlag() == 1) {
                    viewHolder.adapterLeveltwoTv.setVisibility(View.VISIBLE);
                    viewHolder.adapterLeveltwoTv.setText("  "+AppUser.getInstance().getAgentlevelname());
                } else {
                    viewHolder.adapterLeveltwoTv.setVisibility(View.GONE);
                }
                viewHolder.adapterLevelthreeTv.setVisibility(View.VISIBLE);
                viewHolder.adapterLevelthreeTv.setText("  店铺");
                break;
            default:
                break;
        }
        viewHolder.adapterPhonIv.setOnClickListener(new OnClickListener() {
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
                if (TeamAdapter.this.getRecItemClick() != null) {
                    TeamAdapter.this.getRecItemClick().onItemClick(i, dataBean, 0, viewHolder);
                }
            }
        });
    }
}