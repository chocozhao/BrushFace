package com.yzf.king.present;


import com.yzf.king.kit.Version;
import com.yzf.king.kit.utils.ResultCode;
import com.yzf.king.model.servicesmodels.GetTransInfoCountResult;
import com.yzf.king.net.Api;
import com.yzf.king.ui.activitys.TransStatMonthActivity;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubcriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

/**
 * ClaseName：PTransStatMonths
 * Description：
 * Author：chocozhao
 * QQ: 1027313530
 * Createtime：2019/5/16 19:43
 * Modified By：
 * Fixtime：2019/5/16 19:43
 * FixDescription：
 **/

public class PTransStatMonths extends XPresent<TransStatMonthActivity> {

    public void getTransStatMonths(String merchId, String type,String merchName, String phone,String transType,String beginTime, String endTime) {
        String version = Version.GETSUBMERCHCOUNT.version();
        Api.getAPPService().getTransInfoCount(merchId,type, merchName,phone,transType, beginTime, endTime,version)
                .compose(XApi.<GetTransInfoCountResult>getApiTransformer())
                .compose(XApi.<GetTransInfoCountResult>getScheduler())
                .compose(getV().<GetTransInfoCountResult>bindToLifecycle())
                .subscribe(new ApiSubcriber<GetTransInfoCountResult>() {
                    @Override
                    public void onNext(GetTransInfoCountResult result) {
                        if (result.getCode() == ResultCode.SUCCESS.code()) {
                            if (result.getData() != null) {
                                getV().setAdapter(result.getData().get(0).getInfo());
                            }else {
                                getV().showErrorView(result.getMessage());
                            }
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
