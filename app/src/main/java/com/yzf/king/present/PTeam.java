package com.yzf.king.present;

import android.os.Build;
import android.support.annotation.RequiresApi;

import com.yzf.king.kit.Version;
import com.yzf.king.kit.utils.ResultCode;
import com.yzf.king.model.servicesmodels.GetTeamInfoResult;
import com.yzf.king.net.Api;
import com.yzf.king.ui.activitys.TeamManageActivity;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubcriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class PTeam extends XPresent<TeamManageActivity> {

    public void getTeamInfo(String merchId,int page,int pageSize,String beginTime,String endTime) {
        String version = Version.GETTEAMINFO.version();
        Api.getAPPService().getTeamInfo(merchId,page,pageSize,beginTime,endTime,version)
                .compose(XApi.<GetTeamInfoResult>getApiTransformer())
                .compose(XApi.<GetTeamInfoResult>getScheduler())
                .compose(getV().<GetTeamInfoResult>bindToLifecycle())
                .subscribe(new ApiSubcriber<GetTeamInfoResult>() {
                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @Override
                    public void onNext(GetTeamInfoResult result) {
                        if (result.getCode() == ResultCode.SUCCESS.code()) {
//                            getV().setProgress(result.getData());
                            getV().setAdapter(result.getData(),page);
                        } else {
                            getV().showErrorDialog(result.getMessage());
                        }
                    }

                    @Override
                    protected void onFail(NetError error) {
                        getV().showErrorView(error);
                    }

                });
    }


}