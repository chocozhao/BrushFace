package com.yzf.king.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.yzf.king.R;
import com.yzf.king.model.HomeSource;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.base.SimpleRecAdapter;
import cn.droidlover.xdroidmvp.kit.KnifeKit;

public class PersonAdapter extends SimpleRecAdapter<HomeSource, PersonAdapter.ViewHolder> {
    public static final int TAG_VIEW = 0;


    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.person_item_iv)
        ImageView personItemIv;
        @BindView(R.id.person_item_tv)
        TextView personItemTv;

        public ViewHolder(View view) {
            super(view);
            KnifeKit.bind(this, view);
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.adapter_person;
    }

    public PersonAdapter(Context context) {
        super(context);
    }

    @Override
    public ViewHolder newViewHolder(View view) {
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int i) {
        final HomeSource homeSource = (HomeSource) this.data.get(i);
        viewHolder.personItemIv.setImageDrawable(this.context.getResources().getDrawable(homeSource.getImgRes()));
        viewHolder.personItemTv.setText(homeSource.getStrRes());
        viewHolder.itemView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (PersonAdapter.this.getRecItemClick() != null) {
                    PersonAdapter.this.getRecItemClick().onItemClick(i, homeSource, 0, viewHolder);
                }
            }
        });
    }
}