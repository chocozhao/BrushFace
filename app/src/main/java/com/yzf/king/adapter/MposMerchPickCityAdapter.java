package com.yzf.king.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.yzf.king.R;
import com.yzf.king.model.servicesmodels.CityBean;

import java.util.List;

/**
 * ClaseName：MposMerchPickCityAdapter
 * Description：
 * Author：chocozhao
 * QQ: 1027313530
 * Createtime：2019/6/4 16:36
 * Modified By：
 * Fixtime：2019/6/4 16:36
 * FixDescription：
 **/

public class MposMerchPickCityAdapter extends RecyclerView.Adapter<MposMerchPickCityAdapter.ViewHolder>{
    protected Context mContext;
    protected List<CityBean> mDatas;
    protected LayoutInflater mInflater;

    public MposMerchPickCityAdapter(Context mContext, List<CityBean> mDatas) {
        this.mContext = mContext;
        this.mDatas = mDatas;
        mInflater = LayoutInflater.from(mContext);
    }

    public List<CityBean> getDatas() {
        return mDatas;
    }

    public MposMerchPickCityAdapter setDatas(List<CityBean> datas) {
        mDatas = datas;
        return this;
    }

    @Override
    public MposMerchPickCityAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mInflater.inflate(R.layout.mpos_merch_pick_item_city, parent, false));
    }

    @Override
    public void onBindViewHolder(final MposMerchPickCityAdapter.ViewHolder holder, final int position) {
        final CityBean cityBean = mDatas.get(position);
        holder.tvCity.setText(cityBean.getCity());
        holder.content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "pos:" + position, Toast.LENGTH_SHORT).show();
            }
        });
//        holder.avatar.setImageResource(R.drawable.friend);
    }

    @Override
    public int getItemCount() {
        return mDatas != null ? mDatas.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvCity;
        ImageView avatar;
        View content;

        public ViewHolder(View itemView) {
            super(itemView);
            tvCity = (TextView) itemView.findViewById(R.id.tvCity);
            avatar = (ImageView) itemView.findViewById(R.id.ivAvatar);
            content = itemView.findViewById(R.id.content);
        }
    }

}
