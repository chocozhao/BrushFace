package com.yzf.king.present;


import android.support.annotation.NonNull;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.yzf.king.kit.AppUser;
import com.yzf.king.kit.Version;
import com.yzf.king.kit.utils.ResultCode;
import com.yzf.king.model.servicesmodels.GetAddApplyInfoResults;
import com.yzf.king.model.servicesmodels.GetInstallResult;
import com.yzf.king.model.servicesmodels.GetMachinApplyInfoDtlResult;
import com.yzf.king.model.servicesmodels.GetTransDevicesResult;
import com.yzf.king.net.Api;
import com.yzf.king.ui.activitys.InstallActivity;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubcriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

/**
 * ClaseName：PInstall
 * Description：
 * Author：chocozhao
 * QQ: 1027313530
 * Createtime：2019/7/15 17:04
 * Modified By：
 * Fixtime：2019/7/15 17:04
 * FixDescription：
 **/

public class PInstall extends XPresent<InstallActivity> {

    /**
     * 获取信息
     * @param merchId
     * @param orderId
     * @param shopId
     */
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
                            }
                            AppUser.getInstance().setTermId("");
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
     * 安装确认
     * @param merchId
     * @param orderId
     * @param ordersInfo
     * @param bindType
     */
    public void installConfirm(String merchId,String orderId,String ordersInfo,String bindType) {
        String version = Version.GETTRANSDEVICES.version();
        Api.getAPPService().installConfirm(merchId,orderId,ordersInfo,bindType,version)
                .compose(XApi.<GetInstallResult>getApiTransformer())
                .compose(XApi.<GetInstallResult>getScheduler())
                .compose(getV().<GetInstallResult>bindToLifecycle())
                .subscribe(new ApiSubcriber<GetInstallResult>() {
                    @Override
                    public void onNext(GetInstallResult result) {
                        if (result.getCode() == ResultCode.SUCCESS.code()) {
                            if (result.getData() != null) {
                                getV().setResult(result);
                            } else {
                                getV().showToast(result.getMessage());
                                getV().finish();
                            }
                        } else {
                            getV().getvDelegate().showErrorDialog(result.getMessage(), new MaterialDialog.SingleButtonCallback() {
                                @Override
                                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                    if (which == DialogAction.POSITIVE) {
                                        getV().finish();
                                    }
                                }
                            });
                        }
                    }

                    @Override
                    protected void onFail(NetError error) {
                        getV().showError(error);
                    }

                });
    }

    /**
     * 店铺设备操作
     * @param merchId
     * @param termId
     * @param orderId
     * @param replaceTermId
     * @param type
     * @param shopId
     */
    public void shopOperate(String merchId,String termId,String orderId,String replaceTermId,String type,String shopId) {
        String version = Version.GETTMACHINAPPLYINFO.version();
        Api.getAPPService().shopOperate(merchId,shopId,termId,orderId,replaceTermId,type,version)
                .compose(XApi.<GetAddApplyInfoResults>getApiTransformer())
                .compose(XApi.<GetAddApplyInfoResults>getScheduler())
                .compose(getV().<GetAddApplyInfoResults>bindToLifecycle())
                .subscribe(new ApiSubcriber<GetAddApplyInfoResults>() {
                    @Override
                    public void onNext(GetAddApplyInfoResults result) {
                        if (result.getCode() == ResultCode.SUCCESS.code()) {
                            if (result != null) {
//                                getV().setAdapter(result.getData());
                                if ("CANCLE".equals(type)) {
                                    getV().finish();
                                } else if ("CHANGE".equals(type)){
                                    getV().toRefreshMachinApplyInfoDtl();
                                } else if ("ADD".equals(type)) {
                                    getV().toRefreshMachinApplyInfoDtl();
                                } else if ("DELETE".equals(type)) {
                                    getV().toRefreshMachinApplyInfoDtl();
                                }
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
