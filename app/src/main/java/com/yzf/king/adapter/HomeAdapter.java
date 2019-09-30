package com.yzf.king.adapter;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yzf.king.R;
import com.yzf.king.model.HomeSource;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.base.SimpleRecAdapter;
import cn.droidlover.xdroidmvp.kit.KnifeKit;

public class HomeAdapter extends SimpleRecAdapter<HomeSource, HomeAdapter.ViewHolder> {
    public static final int TAG_VIEW = 0;

    public static class ViewHolder extends android.support.v7.widget.RecyclerView.ViewHolder {
        @BindView(R.id.home_item_iv)
        ImageView homeItemIv;
        @BindView(R.id.home_item_tv)
        TextView homeItemTv;
        @BindView(R.id.home_bg_ll)
        LinearLayout homeBgLl;

        public ViewHolder(View view) {
            super(view);
            KnifeKit.bind(this, view);
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.adapter_home;
    }

    public HomeAdapter(Context context) {
        super(context);
    }

    @Override
    public ViewHolder newViewHolder(View view) {
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int i) {
        final HomeSource homeSource = (HomeSource) this.data.get(i);
        viewHolder.homeBgLl.setBackground(this.context.getResources().getDrawable(homeSource.getBackRes()));
        viewHolder.homeItemIv.setImageDrawable(this.context.getResources().getDrawable(homeSource.getImgRes()));
        viewHolder.homeItemTv.setText(homeSource.getStrRes());
        viewHolder.itemView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (HomeAdapter.this.getRecItemClick() != null) {
                    HomeAdapter.this.getRecItemClick().onItemClick(i, homeSource, 0, viewHolder);
                }
            }
        });
    }
}