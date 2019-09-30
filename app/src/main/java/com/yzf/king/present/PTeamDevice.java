package com.yzf.king.present;


import com.yzf.king.kit.Version;
import com.yzf.king.kit.utils.ResultCode;
import com.yzf.king.model.servicesmodels.GetSubmerchDetailResult;
import com.yzf.king.model.servicesmodels.GetTeamTermInfoResult;
import com.yzf.king.net.Api;
import com.yzf.king.ui.activitys.TeamDeviceActivity;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubcriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

/**
 * ClaseName：PTeamDevice
 * Description：
 * Author：chocozhao
 * QQ: 1027313530
 * Createtime：2019/7/12 14:28
 * Modified By：
 * Fixtime：2019/7/12 14:28
 * FixDescription：
 **/

public class PTeamDevice extends XPresent<TeamDeviceActivity> {

    public void getTeamDevice(String merchId, final int page, int pageSize, String beginTime, String endTime) {
        String version = Version.GETMYTERMINFO.version();
        Api.getAPPService().getTeamTermInfo(merchId, "02", page, pageSize, beginTime, endTime, version)
                .compose(XApi.<GetTeamTermInfoResult>getApiTransformer())
                .compose(XApi.<GetTeamTermInfoResult>getScheduler())
                .compose(getV().<GetTeamTermInfoResult>bindToLifecycle())
                .subscribe(new ApiSubcriber<GetTeamTermInfoResult>() {
                    @Override
                    public void onNext(GetTeamTermInfoResult result) {
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
