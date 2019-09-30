package com.yzf.king.present;


import com.yzf.king.kit.Version;
import com.yzf.king.kit.utils.ResultCode;
import com.yzf.king.model.servicesmodels.GetMyTermResult;
import com.yzf.king.model.servicesmodels.GetTermInfoResult;
import com.yzf.king.model.servicesmodels.GetTransDevicesResult;
import com.yzf.king.net.Api;
import com.yzf.king.ui.activitys.AvailableActivity;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubcriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

/**
 * ClaseName：PAvailable
 * Description：
 * Author：chocozhao
 * QQ: 1027313530
 * Createtime：2019/7/17 09:51
 * Modified By：
 * Fixtime：2019/7/17 09:51
 * FixDescription：
 **/

public class PAvailable extends XPresent<AvailableActivity> {

    public void getMyTermInfo(String merchId,final int page, int pageSize,String beginTime,String endTime) {
        String version = Version.GETMYTERMINFO.version();
        Api.getAPPService().getMyTermInfo(merchId, "01",page,pageSize,beginTime,endTime,version)
                .compose(XApi.<GetMyTermResult>getApiTransformer())
                .compose(XApi.<GetMyTermResult>getScheduler())
                .compose(getV().<GetMyTermResult>bindToLifecycle())
                .subscribe(new ApiSubcriber<GetMyTermResult>() {
                    @Override
                    public void onNext(GetMyTermResult result) {
                        if (result.getCode() == ResultCode.SUCCESS.code()) {
                            if (result.getData() != null) {
                                getV().setAdapter(result,page);
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
