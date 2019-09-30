package com.yzf.king.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.yzf.king.R;
import com.yzf.king.model.servicesmodels.GetTeamInfoCountResult;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.base.SimpleRecAdapter;
import cn.droidlover.xdroidmvp.kit.KnifeKit;

/**
 * ClaseName：TeamDayRegisterAdapter
 * Description：
 * Author：chocozhao
 * QQ: 1027313530
 * Createtime：2019/5/17 15:22
 * Modified By：
 * Fixtime：2019/5/17 15:22
 * FixDescription：
 **/

public class TeamDayRegisterAdapter extends SimpleRecAdapter<GetTeamInfoCountResult.DataBean.InfoBean, TeamDayRegisterAdapter.ViewHolder> {
    public static final int TAG_VIEW = 0;



    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.adapter_teamday_date_tv)
        TextView adapterTeamdayDateTv;
        @BindView(R.id.adapter_teamdayt_type_tv)
        TextView adapterTeamdaytTypeTv;
        @BindView(R.id.adapter_teamday_people_tv)
        TextView adapterTeamdayPeopleTv;

        public ViewHolder(View view) {
            super(view);
            KnifeKit.bind(this, view);
        }
    }

    public int getLayoutId() {
        return R.layout.adapter_team_day;
    }

    public TeamDayRegisterAdapter(Context context) {
        super(context);
    }

    public ViewHolder newViewHolder(View view) {
        return new ViewHolder(view);
    }

    public void onBindViewHolder(final ViewHolder viewHolder, final int i) {
        final GetTeamInfoCountResult.DataBean.InfoBean dataBean = (GetTeamInfoCountResult.DataBean.InfoBean) this.data.get(i);
        viewHolder.adapterTeamdayDateTv.setText("" +dataBean.getDay());
        viewHolder.adapterTeamdaytTypeTv.setText(dataBean.getType());
        viewHolder.adapterTeamdayPeopleTv.setText(dataBean.getCount()+"人");
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (TeamDayRegisterAdapter.this.getRecItemClick() != null) {
                    TeamDayRegisterAdapter.this.getRecItemClick().onItemClick(i, dataBean, TAG_VIEW, viewHolder);
                }
            }
        });
    }
}
