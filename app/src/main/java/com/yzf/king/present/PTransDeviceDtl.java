package com.yzf.king.present;


import com.yzf.king.kit.Version;
import com.yzf.king.kit.utils.ResultCode;
import com.yzf.king.model.servicesmodels.GetBenefitDtlResult;
import com.yzf.king.model.servicesmodels.GetOrdersDtl;
import com.yzf.king.net.Api;
import com.yzf.king.ui.activitys.TransDeviceDtlActivity;
import com.yzf.king.ui.fragments.TransDeviceDtlFragment;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubcriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

/**
 * ClaseName：PTransDeviceDtl
 * Description：
 * Author：chocozhao
 * QQ: 1027313530
 * Createtime：2019/8/22 21:01
 * Modified By：
 * Fixtime：2019/8/22 21:01
 * FixDescription：
 **/

public class PTransDeviceDtl extends XPresent<TransDeviceDtlFragment> {


    public void getOrdersDtl(String merchId, String transType, String shopId, String termId,String type,final int page, int pageSize, String beginTime, String endTime) {
        String version = Version.GETBENEFITDTL.version();
        Api.getAPPService().getOrdersDtl(merchId,transType,shopId, termId,type, page, pageSize, beginTime, endTime, version)
                .compose(XApi.<GetOrdersDtl>getApiTransformer())
                .compose(XApi.<GetOrdersDtl>getScheduler())
                .compose(getV().<GetOrdersDtl>bindToLifecycle())
                .subscribe(new ApiSubcriber<GetOrdersDtl>() {
                    @Override
                    public void onNext(GetOrdersDtl result) {
                        if (result.getCode() == ResultCode.SUCCESS.code()) {
                            getV().setAdapter(result.getData(), page);
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
