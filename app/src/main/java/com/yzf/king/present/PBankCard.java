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
import com.yzf.king.model.servicesmodels.BaseResults;
import com.yzf.king.model.servicesmodels.GetCardInfoResult;
import com.yzf.king.net.Api;
import com.yzf.king.ui.activitys.BankCardActivity;
import com.yzf.king.ui.activitys.UploadFaceActivity;
import com.yzf.king.ui.activitys.UploadIdCardZmActivity;

import cn.droidlover.xdroidmvp.event.BusProvider;
import cn.droidlover.xdroidmvp.event.IBus;
import cn.droidlover.xdroidmvp.event.IEvent;
import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubcriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

/**
 * ClaseName：PBankCard
 * Description：银行卡管理逻辑
 * Author：JensenWei
 * QQ: 2188307188
 * Createtime：2017/4/1 11:50
 * Modified By：
 * Fixtime：2017/4/1 11:50
 * FixDescription：
 */

public class PBankCard extends XPresent<BankCardActivity> {
    public void getCardInfo(String merchId) {
        String version = Version.GETCARDINFO.version();
        Api.getAPPService().getCardInfo(merchId, null,"0",version)
                .compose(XApi.<GetCardInfoResult>getApiTransformer())
                .compose(XApi.<GetCardInfoResult>getScheduler())
                .compose(getV().<GetCardInfoResult>bindToLifecycle())
                .subscribe(new ApiSubcriber<GetCardInfoResult>() {
                    @Override
                    public void onNext(GetCardInfoResult result) {
                        if (result.getCode() == ResultCode.SUCCESS.code()) {
                            if (result.getData() != null && result.getData().size() > 0) {
                                Gson gson = new Gson();
                                AppUser.getInstance().setCardList(gson.toJson(result.getData()));
                                getV().setAdapter(result);
                            } else {
                                getV().showEmptyView("还没有绑定过银行卡");
                            }
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

    public void deleteBankCard(String merchId, String cardId, String bindType) {
        String version = Version.CARDOPERATE.version();
        Api.getAPPService().cardOperate(merchId, AppTools.craeateOrderId(), bindType, cardId, null, null, null, null, null, null, null, null, null, null, null, null, version)
                .compose(XApi.<BaseResults>getApiTransformer())
                .compose(XApi.<BaseResults>getScheduler())
                .compose(getV().<BaseResults>bindToLifecycle())
                .subscribe(new ApiSubcriber<BaseResults>() {
                    @Override
                    public void onNext(BaseResults baseResults) {
                        IBus.IEvent iEvent = new IEvent();
                        iEvent.setId("refresh_card");//交易成功后发消息去更新金额
                        BusProvider.getBus().post(iEvent);
                        getV().refresh(baseResults.getMessage());
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

                getV().showDialog("请进行个人实名认证",  getV().getString(R.string.Verified_context), "立即认证", "取消", new MaterialDialog.SingleButtonCallback() {
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
                },true);
                return false;
            case "01": //资料未通过

                getV().showDialog("请进行个人实名认证",  getV().getString(R.string.Verified_context), "立即认证", "取消", new MaterialDialog.SingleButtonCallback() {
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

}
