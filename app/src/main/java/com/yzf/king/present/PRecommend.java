package com.yzf.king.present;


import com.yzf.king.kit.Version;
import com.yzf.king.kit.utils.ResultCode;
import com.yzf.king.model.servicesmodels.GetOrdersDtl;
import com.yzf.king.model.servicesmodels.GetRecommendShopResults;
import com.yzf.king.net.Api;
import com.yzf.king.ui.activitys.RecommendActivity;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubcriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

/**
 * ClaseName：PRecommend
 * Description：
 * Author：chocozhao
 * QQ: 1027313530
 * Createtime：2019/8/23 11:31
 * Modified By：
 * Fixtime：2019/8/23 11:31
 * FixDescription：
 **/

public class PRecommend extends XPresent<RecommendActivity> {

    public void getRecommendShop(String merchId, String city, final int page, int pageSize, String beginTime, String endTime) {
        String version = Version.GETBENEFITDTL.version();
        Api.getAPPService().getRecommendShop(merchId, city, page, pageSize, beginTime, endTime, version)
                .compose(XApi.<GetRecommendShopResults>getApiTransformer())
                .compose(XApi.<GetRecommendShopResults>getScheduler())
                .compose(getV().<GetRecommendShopResults>bindToLifecycle())
                .subscribe(new ApiSubcriber<GetRecommendShopResults>() {
                    @Override
                    public void onNext(GetRecommendShopResults result) {
                        if (result.getCode() == ResultCode.SUCCESS.code()) {
                            getV().setAdapter(result, page);
                        } else {
                            getV().showErrorView(result.getMessage());
                        }
                    }

                    @Override
                    protected void onFail(NetError error) {
                        getV().showErrorView(error);
                    }

                });
    }
}
