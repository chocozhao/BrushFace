package com.yzf.king.adapter.HolderView;

import android.view.View;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.holder.Holder;
import com.yzf.king.R;

import cn.droidlover.xdroidmvp.imageloader.ILFactory;

public class LocalHolderView extends Holder<String> {
    private ImageView imageView;

    public LocalHolderView(View view) {
        super(view);
    }

    protected void initView(View view) {
        this.imageView = (ImageView) view.findViewById(R.id.item_iv);
    }

    public void updateUI(String url) {
        ILFactory.getLoader().loadImageSizekipMemoryCache( url,imageView);

    }
}