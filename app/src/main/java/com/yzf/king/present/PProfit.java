package com.yzf.king.present;

import com.yzf.king.kit.Version;
import com.yzf.king.kit.utils.ResultCode;
import com.yzf.king.model.servicesmodels.GetProfitResult;
import com.yzf.king.net.Api;
import com.yzf.king.ui.fragments.ProfitFragment;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubcriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class PProfit extends XPresent<ProfitFragment> {

    public void getProfitDaily(String merchId, String type, final int page, int pageSize, String beginTime, String endTime,String transType) {
        String version = Version.GETSUBMERCHCOUNT.version();
        Api.getAPPService().getProfitDaily(merchId, type, page, pageSize, beginTime, endTime, version,transType)
                .compose(XApi.<GetProfitResult>getApiTransformer())
                .compose(XApi.<GetProfitResult>getScheduler())
                .compose(getV().<GetProfitResult>bindToLifecycle())
                .subscribe(new ApiSubcriber<GetProfitResult>() {
                    @Override
                    public void onNext(GetProfitResult result) {
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