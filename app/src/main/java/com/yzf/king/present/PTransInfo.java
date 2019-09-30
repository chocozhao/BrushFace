package com.yzf.king.present;

/**
 * ClaseName：PTransInfo
 * Description：
 * Author：chocozhao
 * QQ: 1027313530
 * Createtime：2019/5/13 17:43
 * Modified By：
 * Fixtime：2019/5/13 17:43
 * FixDescription：
 **/

import com.yzf.king.kit.Version;
import com.yzf.king.kit.utils.ResultCode;
import com.yzf.king.model.servicesmodels.CountTransInfoResult;
import com.yzf.king.net.Api;
import com.yzf.king.ui.activitys.TransInfoActivity;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubcriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class PTransInfo extends XPresent<TransInfoActivity> {

    public void getTransStatInfo(String merchId,String transType) {
        String version = Version.COUNTTRANSINFO.version();
        Api.getAPPService().countTransInfo(merchId,transType,version)
                .compose(XApi.<CountTransInfoResult>getApiTransformer())
                .compose(XApi.<CountTransInfoResult>getScheduler())
                .compose(getV().<CountTransInfoResult>bindToLifecycle())
                .subscribe(new ApiSubcriber<CountTransInfoResult>() {
                    @Override
                    public void onNext(CountTransInfoResult result) {
                        if (result.getCode() == ResultCode.SUCCESS.code()) {
                            if (result.getData() != null) {
//                                Gson gson = new Gson();
//                                AppUser.getInstance().setAcctInfo(gson.toJson(result.getData()));
                                getV().setData(result.getData());
                            } else {
                                getV().showEmptyView(result.getMessage());
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