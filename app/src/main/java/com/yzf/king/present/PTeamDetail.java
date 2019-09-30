package com.yzf.king.present;

import com.yzf.king.kit.Version;
import com.yzf.king.kit.utils.ResultCode;
import com.yzf.king.model.servicesmodels.GetSubmerchDetailResult;
import com.yzf.king.net.Api;
import com.yzf.king.ui.activitys.TeamDetailActivity;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubcriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class PTeamDetail extends XPresent<TeamDetailActivity> {

    public void getSubMerchDetail(String merchId, String merchLevel, final int page, int pageSize) {
        String version = Version.GETSUBMERCHDETAIL.version();
        Api.getAPPService().getSubMerchDetail(merchId, merchLevel, page, pageSize, version)
                .compose(XApi.<GetSubmerchDetailResult>getApiTransformer())
                .compose(XApi.<GetSubmerchDetailResult>getScheduler())
                .compose(getV().<GetSubmerchDetailResult>bindToLifecycle())
                .subscribe(new ApiSubcriber<GetSubmerchDetailResult>() {
                    @Override
                    public void onNext(GetSubmerchDetailResult result) {
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