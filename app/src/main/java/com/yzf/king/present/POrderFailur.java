package com.yzf.king.present;


import com.yzf.king.kit.Version;
import com.yzf.king.kit.utils.ResultCode;
import com.yzf.king.model.servicesmodels.GetInstallResult;
import com.yzf.king.model.servicesmodels.GetTransDevicesResult;
import com.yzf.king.net.Api;
import com.yzf.king.ui.activitys.OrderFailureActivity;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubcriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

/**
 * ClaseName：POrderFailur
 * Description：
 * Author：chocozhao
 * QQ: 1027313530
 * Createtime：2019/7/20 15:01
 * Modified By：
 * Fixtime：2019/7/20 15:01
 * FixDescription：
 **/

public class POrderFailur extends XPresent<OrderFailureActivity> {

    public void installConfirm(String merchId,String orderId,String ordersInfo,String bindType) {
        String version = Version.GETTRANSDEVICES.version();
        Api.getAPPService().installConfirm(merchId,orderId,ordersInfo,bindType,version)
                .compose(XApi.<GetInstallResult>getApiTransformer())
                .compose(XApi.<GetInstallResult>getScheduler())
                .compose(getV().<GetInstallResult>bindToLifecycle())
                .subscribe(new ApiSubcriber<GetInstallResult>() {
                    @Override
                    public void onNext(GetInstallResult result) {
                        if (result.getCode() == ResultCode.SUCCESS.code()) {
                            if (result.getData() != null) {
                                getV().setResult(result);
                            } else {
                                getV().showToast(result.getMessage());
                                getV().backActivity();
                            }
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
