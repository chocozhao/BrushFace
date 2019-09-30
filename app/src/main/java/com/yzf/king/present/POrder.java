package com.yzf.king.present;


import com.yzf.king.kit.Version;
import com.yzf.king.kit.utils.ResultCode;
import com.yzf.king.model.servicesmodels.GetTeamTermInfoResult;
import com.yzf.king.model.servicesmodels.GetTransDevicesResult;
import com.yzf.king.net.Api;
import com.yzf.king.ui.fragments.OrderFragment;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubcriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

/**
 * ClaseName：POrder
 * Description：
 * Author：chocozhao
 * QQ: 1027313530
 * Createtime：2019/7/16 15:38
 * Modified By：
 * Fixtime：2019/7/16 15:38
 * FixDescription：
 **/

public class POrder extends XPresent<OrderFragment> {

    public void getTransDevices(String merchId, String status, int page, int pageSize, String beginTime, String endTime) {
        String version = Version.GETTRANSDEVICES.version();
        Api.getAPPService().getTransDevices(merchId, status, page, pageSize, beginTime, endTime, version)
                .compose(XApi.<GetTransDevicesResult>getApiTransformer())
                .compose(XApi.<GetTransDevicesResult>getScheduler())
                .compose(getV().<GetTransDevicesResult>bindToLifecycle())
                .subscribe(new ApiSubcriber<GetTransDevicesResult>() {
                    @Override
                    public void onNext(GetTransDevicesResult result) {
                        if (result.getCode() == ResultCode.SUCCESS.code()) {
                            getV().setAdapter(result.getData(), page);
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
