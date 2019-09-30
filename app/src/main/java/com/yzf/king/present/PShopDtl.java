package com.yzf.king.present;


import com.yzf.king.kit.AppUser;
import com.yzf.king.kit.Version;
import com.yzf.king.kit.utils.ResultCode;
import com.yzf.king.model.servicesmodels.GetLoopShopResult;
import com.yzf.king.model.servicesmodels.GetTermInfoResult;
import com.yzf.king.net.Api;
import com.yzf.king.ui.activitys.ShopDtlActivity;
import com.yzf.king.ui.activitys.ShopFinishActivity;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubcriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

/**
 * ClaseName：PShopDtl
 * Description：
 * Author：chocozhao
 * QQ: 1027313530
 * Createtime：2019/7/16 19:04
 * Modified By：
 * Fixtime：2019/7/16 19:04
 * FixDescription：
 **/

public class PShopDtl extends XPresent<ShopFinishActivity> {
    public void shopConfirm(String merchId,String orderId,String termIdList,String endDate,String bindType) {
        String version = Version.SHOPCONFIRM.version();
        Api.getAPPService().shopConfirm(merchId, orderId,termIdList,endDate,bindType,version)
                .compose(XApi.<GetLoopShopResult>getApiTransformer())
                .compose(XApi.<GetLoopShopResult>getScheduler())
                .compose(getV().<GetLoopShopResult>bindToLifecycle())
                .subscribe(new ApiSubcriber<GetLoopShopResult>() {
                    @Override
                    public void onNext(GetLoopShopResult result) {
                        if (result.getCode() == ResultCode.SUCCESS.code()) {
                            if (result != null) {
                                getV().showResult(result.getMessage());
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
