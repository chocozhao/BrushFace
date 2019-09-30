package com.yzf.king.present;


import com.google.gson.Gson;
import com.yzf.king.kit.AppUser;
import com.yzf.king.kit.Version;
import com.yzf.king.kit.utils.ResultCode;
import com.yzf.king.model.servicesmodels.GetChannelMerchResult;
import com.yzf.king.model.servicesmodels.GetLocationResult;
import com.yzf.king.net.Api;
import com.yzf.king.ui.activitys.MposMerchPickActivity;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubcriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

/**
 * ClaseName：PMposMerchPick
 * Description：
 * Author：chocozhao
 * QQ: 1027313530
 * Createtime：2019/6/4 18:21
 * Modified By：
 * Fixtime：2019/6/4 18:21
 * FixDescription：
 **/

public class PMposMerchPick extends XPresent<MposMerchPickActivity> {

    public void getLocationInfo(String merchid) {
        String version = Version.GETLOCATIONINFO.version();
        Api.getAPPService().getLocationInfo(merchid, version)
                .compose(XApi.<GetLocationResult>getApiTransformer())
                .compose(XApi.<GetLocationResult>getScheduler())
                .compose(getV().<GetLocationResult>bindToLifecycle())
                .subscribe(new ApiSubcriber<GetLocationResult>() {
                    @Override
                    public void onNext(GetLocationResult result) {
                        if (result.getCode() == ResultCode.SUCCESS.code()) {
                            getV().initDatas(result.getData().getCity());
                        } else {
                            getV().showErrorDialog(result.getMessage());
                        }
                    }

                    @Override
                    protected void onFail(NetError error) {
                        getV().showError(error);
                    }

                });
    }


}
