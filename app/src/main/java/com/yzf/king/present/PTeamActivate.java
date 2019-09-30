package com.yzf.king.present;


import com.yzf.king.kit.Version;
import com.yzf.king.kit.utils.ResultCode;
import com.yzf.king.model.servicesmodels.GetMyTermResult;
import com.yzf.king.model.servicesmodels.GetTeamTermInfoResult;
import com.yzf.king.net.Api;
import com.yzf.king.ui.activitys.TeamActivateActivity;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubcriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

/**
 * ClaseName：PTeamActivate
 * Description：
 * Author：chocozhao
 * QQ: 1027313530
 * Createtime：2019/7/18 15:10
 * Modified By：
 * Fixtime：2019/7/18 15:10
 * FixDescription：
 **/

public class PTeamActivate extends XPresent<TeamActivateActivity> {

    public void getTeamTermInfo(String merchId, final int page, int pageSize, String beginTime, String endTime) {
        String version = Version.GETTEAMTERMINFO.version();
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
