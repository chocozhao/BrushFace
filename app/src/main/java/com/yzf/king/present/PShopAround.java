package com.yzf.king.present;


import com.yzf.king.kit.Version;
import com.yzf.king.kit.utils.ResultCode;
import com.yzf.king.model.servicesmodels.GetMachinApplyInfoResults;
import com.yzf.king.model.servicesmodels.GetShopAroundResult;
import com.yzf.king.net.Api;
import com.yzf.king.ui.activitys.ShopAroundActivity;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubcriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

/**
 * ClaseName：PShopAround
 * Description：
 * Author：chocozhao
 * QQ: 1027313530
 * Createtime：2019/7/29 11:05
 * Modified By：
 * Fixtime：2019/7/29 11:05
 * FixDescription：
 **/

public class PShopAround extends XPresent<ShopAroundActivity> {

    public void getShopAround(String merchId, String key, String location, String region, String city,String radius, String sortName,String sortRule,int page, int pageSize) {
        String version = Version.GETSHOPAROUND.version();
        Api.getAPPService().getShopAround(merchId, key, location, region, city, radius,sortName,sortRule,page, pageSize, version)
                .compose(XApi.<GetShopAroundResult>getApiTransformer())
                .compose(XApi.<GetShopAroundResult>getScheduler())
                .compose(getV().<GetShopAroundResult>bindToLifecycle())
                .subscribe(new ApiSubcriber<GetShopAroundResult>() {
                    @Override
                    public void onNext(GetShopAroundResult result) {
                        if (result.getCode() == ResultCode.SUCCESS.code()) {
                            if (result.getData() != null) {
//                                Gson gson  =  new Gson();
//                                AppUser.getInstance().setMachinApplyInfo(gson.toJson(result.getData()));
//                                AppUser.getInstance().setOrderId();
                                getV().setAdapter(result, page);
                            } else {
                                getV().showErrorView(result.getMessage());
                            }
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
