package com.yzf.king.adapter;


import android.content.Context;

import com.yzf.king.R;
import com.yzf.king.kit.utils.CommonAdapter;
import com.yzf.king.kit.utils.ViewHolder;
import com.yzf.king.model.servicesmodels.MposMerchPickBean;

import java.util.List;

/**
 * Created by zhangxutong .
 * Date: 16/08/28
 */

public class MposMerchPickAdapter extends CommonAdapter<MposMerchPickBean> {
    public MposMerchPickAdapter(Context context, int layoutId, List<MposMerchPickBean> datas) {
        super(context, layoutId, datas);
    }

    @Override
    public void convert(ViewHolder holder, final MposMerchPickBean cityBean) {
        holder.setText(R.id.tvCity, cityBean.getCity());
    }
}