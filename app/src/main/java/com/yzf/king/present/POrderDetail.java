package com.yzf.king.present;


import com.yzf.king.kit.Version;
import com.yzf.king.kit.utils.ResultCode;
import com.yzf.king.model.servicesmodels.GetTransDevicesResult;
import com.yzf.king.model.servicesmodels.GetTransTermInfoResult;
import com.yzf.king.net.Api;
import com.yzf.king.ui.fragments.OrderDetailFragment;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubcriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

/**
 * ClaseName：POrderDetail
 * Description：
 * Author：chocozhao
 * QQ: 1027313530
 * Createtime：2019/7/31 17:26
 * Modified By：
 * Fixtime：2019/7/31 17:26
 * FixDescription：
 **/

public class POrderDetail extends XPresent<OrderDetailFragment> {

    public void getTransTermInfo(String merchId, String status, String orderId, int page, int pageSize, String beginTime, String endTime) {
        String version = Version.GETTRANSTERMINFO.version();
        Api.getAPPService().getTransTermInfo(merchId, status, orderId, page, pageSize, beginTime, endTime, version)
                .compose(XApi.<GetTransTermInfoResult>getApiTransformer())
                .compose(XApi.<GetTransTermInfoResult>getScheduler())
                .compose(getV().<GetTransTermInfoResult>bindToLifecycle())
                .subscribe(new ApiSubcriber<GetTransTermInfoResult>() {
                    @Override
                    public void onNext(GetTransTermInfoResult result) {
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
