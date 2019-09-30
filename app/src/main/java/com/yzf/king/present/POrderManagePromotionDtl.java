package com.yzf.king.present;


import com.yzf.king.kit.Version;
import com.yzf.king.kit.utils.ResultCode;
import com.yzf.king.model.servicesmodels.GetMachinApplyInfoDtlResult;
import com.yzf.king.net.Api;
import com.yzf.king.ui.activitys.OrderManagePromotionDtlActivity;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubcriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

/**
 * ClaseName：POrderManagePromotionDtl
 * Description：
 * Author：chocozhao
 * QQ: 1027313530
 * Createtime：2019/7/19 16:45
 * Modified By：
 * Fixtime：2019/7/19 16:45
 * FixDescription：
 **/

public class POrderManagePromotionDtl extends XPresent<OrderManagePromotionDtlActivity> {

    public void getMachinApplyInfoDtl(String merchId,String orderId,String shopId) {
        String version = Version.GETTMACHINAPPLYINFO.version();
        Api.getAPPService().getMachinApplyInfoDtl(merchId,orderId,shopId,version)
                .compose(XApi.<GetMachinApplyInfoDtlResult>getApiTransformer())
                .compose(XApi.<GetMachinApplyInfoDtlResult>getScheduler())
                .compose(getV().<GetMachinApplyInfoDtlResult>bindToLifecycle())
                .subscribe(new ApiSubcriber<GetMachinApplyInfoDtlResult>() {
                    @Override
                    public void onNext(GetMachinApplyInfoDtlResult result) {
                        if (result.getCode() == ResultCode.SUCCESS.code()) {
                            if (result != null) {
                                getV().setAdapter(result.getData());
                            } else {
                                getV().showToast(result.getMessage());
                            }
                        } else {
                            getV().showToast(result.getMessage());
                        }
                    }

                    @Override
                    protected void onFail(NetError error) {
                        getV().showError(error);
                    }
                });
    }
}
