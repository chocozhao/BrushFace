package com.yzf.king.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yzf.king.R;
import com.yzf.king.kit.AppTools;
import com.yzf.king.model.servicesmodels.GetMyShopInfoResult;
import com.yzf.king.model.servicesmodels.GetSpreadShopInfoRestult;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.base.SimpleRecAdapter;
import cn.droidlover.xdroidmvp.imageloader.ILFactory;
import cn.droidlover.xdroidmvp.kit.Kits;
import cn.droidlover.xdroidmvp.kit.KnifeKit;

/**
 * ClaseName：MyShopAdapter
 * Description：
 * Author：chocozhao
 * QQ: 1027313530
 * Createtime：2019/7/17 21:15
 * Modified By：
 * Fixtime：2019/7/17 21:15
 * FixDescription：
 **/

public class MyShopAdapter extends SimpleRecAdapter<GetMyShopInfoResult.DataBean.DataListBean, MyShopAdapter.ViewHolder> {

    public static final int TAG_VIEW = 0;


    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.shop_manage_hear_iv)
        ImageView shopManageHearIv;
        @BindView(R.id.shop_manage_status_iv)
        ImageView shopManageStatusIv;
        @BindView(R.id.shop_promotion_title_tv)
        TextView shopPromotionTitleTv;
        @BindView(R.id.shop_manage_date_tv)
        TextView shopManageDateTv;

        public ViewHolder(View view) {
            super(view);
            KnifeKit.bind(this, view);
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.adapter_promotion;
    }

    public MyShopAdapter(Context context) {
        super(context);
    }

    @Override
    public MyShopAdapter.ViewHolder newViewHolder(View view) {
        return new MyShopAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyShopAdapter.ViewHolder viewHolder, final int i) {
        final GetMyShopInfoResult.DataBean.DataListBean dataBean = (GetMyShopInfoResult.DataBean.DataListBean) this.data.get(i);
        if (dataBean != null) {
            viewHolder.shopManageDateTv.setText(Kits.Date.getYmdhms(dataBean.getInsertTime()));
            viewHolder.shopPromotionTitleTv.setText(dataBean.getShopName());
            if (!AppTools.isEmpty(dataBean.getLogoUrl())) {
                ILFactory.getLoader().loadCircleImage(dataBean.getLogoUrl(), viewHolder.shopManageHearIv);
            } else {
                ILFactory.getLoader().loadCircleImage(getImgId("logo"), viewHolder.shopManageHearIv);
            }
        }
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (MyShopAdapter.this.getRecItemClick() != null) {
                    MyShopAdapter.this.getRecItemClick().onItemClick(i, dataBean, 0, viewHolder);
                }
            }
        });
    }

    private int getImgId(String imgName) {
        int id = -1;
        try {
            id = context.getResources().getIdentifier(imgName, "mipmap", context.getPackageName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }

}
