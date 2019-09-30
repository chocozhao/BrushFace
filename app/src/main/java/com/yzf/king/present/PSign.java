package com.yzf.king.present;


import com.yzf.king.kit.Version;
import com.yzf.king.kit.utils.ResultCode;
import com.yzf.king.model.servicesmodels.GetMyShopInfoResult;
import com.yzf.king.net.Api;
import com.yzf.king.ui.activitys.SignActivity;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubcriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

/**
 * ClaseName：PSign
 * Description：
 * Author：chocozhao
 * QQ: 1027313530
 * Createtime：2019/8/12 17:14
 * Modified By：
 * Fixtime：2019/8/12 17:14
 * FixDescription：
 **/

public class PSign extends XPresent<SignActivity> {

    public void getShopInfo(String merchId, String type, int page, int pageSize, String beginTime, String endTime) {
        String version = Version.GETSHOPINFO.version();
        Api.getAPPService().getShopInfo(merchId, type, page, pageSize, beginTime, endTime, version)
                .compose(XApi.<GetMyShopInfoResult>getApiTransformer())
                .compose(XApi.<GetMyShopInfoResult>getScheduler())
                .compose(getV().<GetMyShopInfoResult>bindToLifecycle())
                .subscribe(new ApiSubcriber<GetMyShopInfoResult>() {
                    @Override
                    public void onNext(GetMyShopInfoResult result) {
                        if (result.getCode() == ResultCode.SUCCESS.code()) {
                            getV().setAdapter(result, page);
                        } else {
                            getV().showErrorView(result.getMessage());
                        }
                    }

                    @Override
                    protected void onFail(NetError error) {
                        getV().showError(error);
                    }

                });
    }
}
