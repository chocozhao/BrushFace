package com.yzf.king.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yzf.king.R;
import com.yzf.king.model.BluetoothDevices;

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

public class BleAdapter extends SimpleRecAdapter<BluetoothDevices, BleAdapter.ViewHolder> {

    public static final int TAG_VIEW = 0;


    public BleAdapter(Context context) {
        super(context);
    }

    @Override
    public ViewHolder newViewHolder(View itemView) {
        return new ViewHolder(itemView);
    }

    @Override
    public int getLayoutId() {
        return R.layout.adapter_bluetooth;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final BluetoothDevices item = data.get(position);
        holder.adapterBluetoothName.setText(item.getName());
        if(item.isCheck())
        {
            holder.adapterBluetoothIv.setImageResource(R.mipmap.rb_check);
        }else {
            holder.adapterBluetoothIv.setImageResource(R.mipmap.rb_uncheck);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (BleAdapter.this.getRecItemClick() != null) {
                    BleAdapter.this.getRecItemClick().onItemClick(position, item, TAG_VIEW, holder);
                }
            }
        });


    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.adapter_bluetooth_name)
        TextView adapterBluetoothName;
        @BindView(R.id.adapter_bluetooth_iv)
        ImageView adapterBluetoothIv;

        public ViewHolder(View itemView) {
            super(itemView);
            KnifeKit.bind(this, itemView);
        }
    }

}
