package com.yzf.king.present;


import com.yzf.king.kit.Version;
import com.yzf.king.kit.utils.ResultCode;
import com.yzf.king.model.servicesmodels.GetMyShopInfoResult;
import com.yzf.king.model.servicesmodels.GetSunMerchInfoListResult;
import com.yzf.king.net.Api;
import com.yzf.king.ui.activitys.SignDtlActivity;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubcriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

/**
 * ClaseName：PSignDtl
 * Description：
 * Author：chocozhao
 * QQ: 1027313530
 * Createtime：2019/8/12 19:47
 * Modified By：
 * Fixtime：2019/8/12 19:47
 * FixDescription：
 **/

public class PSignDtl extends XPresent<SignDtlActivity> {

    public void getSunMerchInfoList(String merchId, String shopId) {
        String version = Version.GETSHOPINFO.version();
        Api.getAPPService().getSunMerchInfoList(merchId, shopId, version)
                .compose(XApi.<GetSunMerchInfoListResult>getApiTransformer())
                .compose(XApi.<GetSunMerchInfoListResult>getScheduler())
                .compose(getV().<GetSunMerchInfoListResult>bindToLifecycle())
                .subscribe(new ApiSubcriber<GetSunMerchInfoListResult>() {
                    @Override
                    public void onNext(GetSunMerchInfoListResult result) {
                        if (result.getCode() == ResultCode.SUCCESS.code()) {
                            getV().setAdapter(result.getData());
                        } else {
                            getV().showToast(result.getMessage());
                        }
                    }

                    @Override
                    protected void onFail(NetError error) {
                        getV().showError(error);
                    }

                });
    }

}
