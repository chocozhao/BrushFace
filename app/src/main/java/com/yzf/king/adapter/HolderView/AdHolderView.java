package com.yzf.king.adapter.HolderView;

import android.view.View;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.holder.Holder;
import com.yzf.king.R;
import com.yzf.king.model.servicesmodels.GetBannerListResult;
import com.yzf.king.model.servicesmodels.GetShareImgResult;

import cn.droidlover.xdroidmvp.imageloader.ILFactory;
import cn.droidlover.xdroidmvp.imageloader.ILoader;

public class AdHolderView extends Holder<GetShareImgResult.DataBean> {
    private ImageView imageView;

    public AdHolderView(View view) {
        super(view);
    }

    protected void initView(View view) {
        this.imageView = (ImageView) view.findViewById(R.id.item_iv);
    }

    public void updateUI(GetShareImgResult.DataBean dataBean) {
        try {
            if (dataBean.getImgPath() != null && dataBean.getImgPath().length() > 20) {
                ILFactory.getLoader().loadImage( dataBean.getImgPath(),imageView);
            } else {
                ILFactory.getLoader().loadImage( dataBean.getResourceId(),imageView);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}