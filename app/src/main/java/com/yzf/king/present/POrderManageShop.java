package com.yzf.king.present;


import android.support.annotation.NonNull;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.yzf.king.kit.AppUser;
import com.yzf.king.kit.Version;
import com.yzf.king.kit.utils.ResultCode;
import com.yzf.king.model.servicesmodels.GetLoopShopResult;
import com.yzf.king.model.servicesmodels.GetMachinApplyInfoDtlResult;
import com.yzf.king.net.Api;
import com.yzf.king.ui.activitys.OrderManageShopActivity;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubcriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

/**
 * ClaseName：POrderManageShop
 * Description：
 * Author：chocozhao
 * QQ: 1027313530
 * Createtime：2019/7/15 14:22
 * Modified By：
 * Fixtime：2019/7/15 14:22
 * FixDescription：
 **/

public class POrderManageShop extends XPresent<OrderManageShopActivity> {

    public void getMachinApplyInfoDtl(String merchId, String orderId, String shopId) {
        String version = Version.GETTMACHINAPPLYINFO.version();
        Api.getAPPService().getMachinApplyInfoDtl(merchId, orderId, shopId, version)
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

    /**
     * 铺货提交确认
     *
     * @param merchId
     * @param orderId
     * @param termIdList
     * @param endDate
     * @param bindType
     */
    public void shopConfirm(String merchId, String orderId, String termIdList, String endDate, String bindType) {
        String version = Version.SHOPCONFIRM.version();
        Api.getAPPService().shopConfirm(merchId, orderId, termIdList, endDate, bindType, version)
                .compose(XApi.<GetLoopShopResult>getApiTransformer())
                .compose(XApi.<GetLoopShopResult>getScheduler())
                .compose(getV().<GetLoopShopResult>bindToLifecycle())
                .subscribe(new ApiSubcriber<GetLoopShopResult>() {
                    @Override
                    public void onNext(GetLoopShopResult result) {
                        if (result.getCode() == ResultCode.SUCCESS.code()) {
                            if (result != null) {
                                getV().confirmResult(result.getMessage());
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
