package com.yzf.king.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.yzf.king.R;
import com.yzf.king.model.servicesmodels.AddressResult;

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

public class AddressAdapter extends SimpleRecAdapter<AddressResult.DataBean, AddressAdapter.ViewHolder> {

    public static final int TAG_VIEW = 0;
    public static final int DEL_VIEW = 1;
    public static final int MOD_VIEW = 2;


    public AddressAdapter(Context context) {
        super(context);
    }

    @Override
    public ViewHolder newViewHolder(View itemView) {
        return new ViewHolder(itemView);
    }

    @Override
    public int getLayoutId() {
        return R.layout.adapter_address;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final AddressResult.DataBean item = data.get(position);
        holder.addressAdapterNameTv.setText(item.getName());
        holder.addressAdapterPhoneTv.setText(item.getPhone());
        holder.addressAdapterAddressTv.setText(item.getAddress() + item.getAddressDtl());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getRecItemClick() != null) {
                    getRecItemClick().onItemClick(position, item, TAG_VIEW, holder);
                }
            }
        });
        holder.addressAdapterDelTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getRecItemClick() != null) {
                    getRecItemClick().onItemClick(position, item, DEL_VIEW, holder);
                }
            }
        });
        holder.addressAdapterModTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getRecItemClick() != null) {
                    getRecItemClick().onItemClick(position, item, MOD_VIEW, holder);
                }
            }
        });

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.address_adapter_name_tv)
        TextView addressAdapterNameTv;
        @BindView(R.id.address_adapter_phone_tv)
        TextView addressAdapterPhoneTv;
        @BindView(R.id.address_adapter_address_tv)
        TextView addressAdapterAddressTv;
        @BindView(R.id.address_adapter_del_tv)
        TextView addressAdapterDelTv;
        @BindView(R.id.address_adapter_mod_tv)
        TextView addressAdapterModTv;

        public ViewHolder(View itemView) {
            super(itemView);
            KnifeKit.bind(this, itemView);
        }
    }

}
