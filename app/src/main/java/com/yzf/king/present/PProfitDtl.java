package com.yzf.king.present;

import com.yzf.king.kit.Version;
import com.yzf.king.kit.utils.ResultCode;
import com.yzf.king.model.servicesmodels.GetProfitDtlResult;
import com.yzf.king.net.Api;
import com.yzf.king.ui.activitys.ProfitDtlActivity;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubcriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class PProfitDtl extends XPresent<ProfitDtlActivity> {

    public void getProfitDtl(String merchId, String type, String orderId, String transType, final int page, int pageSize, String beginTime, String endTime) {
        String version = Version.GETSUBMERCHCOUNT.version();
        Api.getAPPService().getProfitDtl(merchId, type, orderId, transType, page, pageSize, beginTime, endTime, version)
                .compose(XApi.<GetProfitDtlResult>getApiTransformer())
                .compose(XApi.<GetProfitDtlResult>getScheduler())
                .compose(getV().<GetProfitDtlResult>bindToLifecycle())
                .subscribe(new ApiSubcriber<GetProfitDtlResult>() {
                    @Override
                    public void onNext(GetProfitDtlResult result) {
                        if (result.getCode() == ResultCode.SUCCESS.code()) {
                            getV().setAdapter(result.getData(), page);
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