package com.yzf.king.present;

import android.support.annotation.NonNull;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.google.gson.Gson;
import com.yzf.king.R;
import com.yzf.king.kit.AppTools;
import com.yzf.king.kit.AppUser;
import com.yzf.king.kit.Version;
import com.yzf.king.kit.utils.ResultCode;
import com.yzf.king.model.servicesmodels.GetAcctInfoResult;
import com.yzf.king.net.Api;
import com.yzf.king.ui.activitys.AcctInfoActivity;
import com.yzf.king.ui.activitys.UploadIdCardZmActivity;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubcriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class PAcctInfo extends XPresent<AcctInfoActivity> {

    public void getAcctInfo(String merchId) {
        String version = Version.GETACCTINFO.version();
        Api.getAPPService().getAcctInfo(merchId, version)
                .compose(XApi.<GetAcctInfoResult>getApiTransformer())
                .compose(XApi.<GetAcctInfoResult>getScheduler())
                .compose(getV().<GetAcctInfoResult>bindToLifecycle())
                .subscribe(new ApiSubcriber<GetAcctInfoResult>() {
                    @Override
                    public void onNext(GetAcctInfoResult result) {
                        if (result.getCode() == ResultCode.SUCCESS.code()) {
                            if (result.getData() != null) {
                                Gson gson = new Gson();
                                AppUser.getInstance().setAcctInfo(gson.toJson(result.getData()));
                                getV().setData(result.getData());
                            } else {
                                getV().showEmptyView(result.getMessage());
                            }
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


    /**
     * 根据state判断用户状态
     *
     * @param state 用户状态
     * @return 已实名认证后返回true
     */
    public boolean verifyStatus(String state) {
        if (AppTools.isEmpty(state)) {
            state = AppUser.getInstance().getStatus();
        }
        if (AppTools.isEmpty(state)) {
            state = "00";
        }
        if ("00".equals(state)) {//通过实名认证
            /*if ("1".equals(AppUser.getInstance().getUsedFlag())) {
                getV().showNoticeDialog("您还未申请MPOS，暂时不可用该功能，是否现在申请？", new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        if (which == DialogAction.POSITIVE) {
                            getV().toWebView();
                        }
                    }
                });
                return false;
            }*/
            return true;
        }
        if ("10".equals(state)) {//
            getV().showErrorDialog("收款账户已冻结");
            return false;
        }
        switch (state) {
            case "30": //
                return true;
            case "03":
                getV().showDialog("请进行个人实名认证", getV().getString(R.string.Verified_context), "立即认证", "取消", new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        if (which == DialogAction.POSITIVE) {
                            getV().JumpActivity(UploadIdCardZmActivity.class);
                        }
                    }
                }, true);
                return false;
            case "02": //{"message":"商户未上传照片","status":100,"state":"3","storeId":null}

//                getV().showDialog("请上传实名认证照片",  getV().getString(R.string.Verified_context), "立即上传", "取消", new MaterialDialog.SingleButtonCallback() {
//                    @Override
//                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
//                        if (which == DialogAction.POSITIVE) {
//                            getV().JumpActivity(UploadFaceActivity.class);
//                        }
//                    }
//                },true);
//                return false;
                return true;
            case "01": //资料未通过

                getV().showDialog("请进行个人实名认证", getV().getString(R.string.Verified_context), "立即认证", "取消", new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        if (which == DialogAction.POSITIVE) {
                            getV().JumpActivity(UploadIdCardZmActivity.class);
                        }
                    }
                }, true);
                return false;
            default: //资料未通过

                getV().showDialog("请进行个人实名认证", getV().getString(R.string.Verified_context), "立即认证", "取消", new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        if (which == DialogAction.POSITIVE) {
                            getV().JumpActivity(UploadIdCardZmActivity.class);
                        }
                    }
                }, true);
                return false;

        }
    }


}