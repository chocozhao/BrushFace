package com.yzf.king.present;

import android.support.annotation.NonNull;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.yzf.king.R;
import com.yzf.king.kit.AppTools;
import com.yzf.king.kit.AppUser;
import com.yzf.king.kit.Version;
import com.yzf.king.kit.utils.ResultCode;
import com.yzf.king.model.servicesmodels.CheckVersionResults;
import com.yzf.king.net.Api;
import com.yzf.king.ui.activitys.MerchInfoActivity;
import com.yzf.king.ui.activitys.SettingActivity;
import com.yzf.king.ui.activitys.UploadFaceActivity;
import com.yzf.king.ui.activitys.UploadIdCardZmActivity;

import java.util.concurrent.TimeUnit;

import cn.droidlover.xdroidmvp.kit.Kits;
import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubcriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class PSetting extends XPresent<SettingActivity> {

    public void checkUpdate(final boolean show) {
        String version = Version.CHECKAPPVERSION.version();
        Api.getAPPService().checkAppVersion("Android", AppUser.getInstance().getMerchId(), null, version)
                .compose(XApi.<CheckVersionResults>getApiTransformer())
                .compose(XApi.<CheckVersionResults>getScheduler())
                .compose(getV().<CheckVersionResults>bindToLifecycle())
                .subscribe(new ApiSubcriber<CheckVersionResults>() {
                    @Override
                    public void onNext(CheckVersionResults results) {
                        if (results.getCode() == ResultCode.SUCCESS.code()) {
                            int versionCode = 0;
                            if (!AppTools.isEmpty(results.getData().getVersionCode())) {
                                try {
                                    versionCode = Integer.parseInt(results.getData().getVersionCode());
                                } catch (NumberFormatException e) {
                                    e.printStackTrace();
                                }
                            }
//                            getV().update(results);
                            if (Kits.Package.getVersionCode(getV().getApplicationContext()) < versionCode) {
                                getV().update(results);
                            } else {
                                if (show) {
                                    getV().showToast("暂无更新");
                                }
                            }
                        } else {
                            getV().showToast(results.getMessage());
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
                },true);
                return false;
            case "02": //{"message":"商户未上传照片","status":100,"state":"3","storeId":null}

                getV().showDialog("请上传实名认证照片",  getV().getString(R.string.Verified_context), "立即上传", "取消", new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        if (which == DialogAction.POSITIVE) {
                            getV().JumpActivity(UploadFaceActivity.class);
                        }
                    }
                },false);
                return true;
            case "01": //资料未通过
                getV().showDialog("请进行个人实名认证", getV().getString(R.string.Verified_context), "立即认证", "取消", new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        if (which == DialogAction.POSITIVE) {
                            getV().JumpActivity(UploadIdCardZmActivity.class);
                        }
                    }
                },true);
                return false;
            default: //资料未通过
                getV().showDialog("请进行个人实名认证", getV().getString(R.string.Verified_context), "立即认证", "取消", new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        if (which == DialogAction.POSITIVE) {
                            getV().JumpActivity(UploadIdCardZmActivity.class);
                        }
                    }
                },true);
                return false;
        }
    }

    /**
     * 根据state判断用户状态
     *
     * @param state 用户状态
     * @return 已实名认证后返回true
     */
    public void toVerify(String state) {
        if (AppTools.isEmpty(state)) {
            state = AppUser.getInstance().getStatus();
        }
        if (AppTools.isEmpty(state)) {
            state = "00";
        }
        if ("00".equals(state)) {//通过实名认证
            getV().JumpActivity(MerchInfoActivity.class);
            return;
        }
        if ("10".equals(state)) {//
            getV().showErrorDialog("收款账户已冻结");
            return;
        }
        switch (state) {
            case "03":
                getV().JumpActivity(UploadIdCardZmActivity.class);
                break;
            case "02": //{"message":"商户未上传照片","status":100,"state":"3","storeId":null}
                getV().JumpActivity(UploadFaceActivity.class);
                break;

            case "01": //资料未通过
                getV().JumpActivity(UploadIdCardZmActivity.class);
                break;
            default:
                break;
        }
    }


}