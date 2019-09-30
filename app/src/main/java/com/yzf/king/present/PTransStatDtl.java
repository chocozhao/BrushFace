package com.yzf.king.present;


import com.yzf.king.kit.Version;
import com.yzf.king.kit.utils.ResultCode;
import com.yzf.king.model.servicesmodels.GetTransInfoCountDtlResult;
import com.yzf.king.net.Api;
import com.yzf.king.ui.activitys.TransStatDtlActivity;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubcriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

/**
 * ClaseName：PTransStatDtl
 * Description：
 * Author：chocozhao
 * QQ: 1027313530
 * Createtime：2019/5/15 19:04
 * Modified By：
 * Fixtime：2019/5/15 19:04
 * FixDescription：
 **/

public class PTransStatDtl extends XPresent<TransStatDtlActivity> {

    public void getTransStatDtl(String merchId, String merchName,String phone,String transType,  String beginTime, String endTime,final int page, int pageSize) {
        String version = Version.GETTRANSINFOCOUNTDTL.version();
        Api.getAPPService().getTransInfoCountDtl(merchId, merchName, phone,transType, beginTime, endTime, page, pageSize, version)
                .compose(XApi.<GetTransInfoCountDtlResult>getApiTransformer())
                .compose(XApi.<GetTransInfoCountDtlResult>getScheduler())
                .compose(getV().<GetTransInfoCountDtlResult>bindToLifecycle())
                .subscribe(new ApiSubcriber<GetTransInfoCountDtlResult>() {
                    @Override
                    public void onNext(GetTransInfoCountDtlResult result) {
                        if (result.getCode() == ResultCode.SUCCESS.code()) {
                            if (result.getData() != null) {
                                getV().setAdapter(result, page);
                            }else {
                                getV().showErrorView(result.getMessage());
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
