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
import com.yzf.king.model.servicesmodels.GetAcctInfoResult;
import com.yzf.king.model.servicesmodels.GetGoodsInfoResults;
import com.yzf.king.model.servicesmodels.GetMerchInfoResult;
import com.yzf.king.model.servicesmodels.GetMposIncomeResult;
import com.yzf.king.model.servicesmodels.GetNewsResult;
import com.yzf.king.model.servicesmodels.GetNoticeResult;
import com.yzf.king.model.servicesmodels.GetShareImgResult;
import com.yzf.king.model.servicesmodels.RefreshResult;
import com.yzf.king.net.Api;
import com.yzf.king.ui.activitys.UploadIdCardZmActivity;
import com.yzf.king.ui.fragments.HomeFragment;

import cn.droidlover.xdroidmvp.event.BusProvider;
import cn.droidlover.xdroidmvp.event.IBus;
import cn.droidlover.xdroidmvp.event.IEvent;
import cn.droidlover.xdroidmvp.log.Logger;
import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubcriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class PHome extends XPresent<HomeFragment> {

    public void getAcctInfo(String merchId) {
        if (AppTools.isEmpty(merchId)) {
            getV().showToast("商户号为空");
            return;
        }
        String version = Version.GETACCTINFO.version();
        Api.getAPPService().getAcctInfo(merchId, version)
                .compose(XApi.<GetAcctInfoResult>getApiTransformer())
                .compose(XApi.<GetAcctInfoResult>getScheduler())
                .compose(getV().<GetAcctInfoResult>bindToLifecycle())
                .subscribe(new ApiSubcriber<GetAcctInfoResult>() {
                    @Override
                    public void onNext(GetAcctInfoResult result) {
                        if (result.getCode() == ResultCode.SUCCESS.code()) {
                            Gson gson = new Gson();
                            AppUser.getInstance().setAcctInfo(gson.toJson(result.getData()));
                            getV().setInfo();
                        } else {
                            getV().showErrorDialog(result.getMessage());
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

    /**
     * 获取卡美进件结果
     */
    public void getKayouIncomeResult(final String merchId) {
        String version = Version.GETKAYOUINCOMERESULT.version();
        Api.getAPPService().getMposIncomeResult(merchId, version)
                .compose(XApi.<GetMposIncomeResult>getApiTransformer())
                .compose(XApi.<GetMposIncomeResult>getScheduler())
                .compose(getV().<GetMposIncomeResult>bindToLifecycle())
                .subscribe(new ApiSubcriber<GetMposIncomeResult>() {
                    @Override
                    public void onNext(GetMposIncomeResult result) {
                        getV().dismissLoading();
                        if (result.getCode() == ResultCode.SUCCESS.code()) {
                            if (result.getData().getResult_code().equals("00"))//审核通过
                            {
                                if (result.getData().getMposApplyRecord() != null) {
//                                    getV().JumpActivity(PosPayActivity.class, false);
//                                    if (result.getData().getMposApplyRecord().getPayStatus() == 1)//已收货
//                                    {
//                                        getV().JumpActivity(PosPayActivity.class, false);
//                                    } else {
//                                        Gson gson = new Gson();
//                                        AppUser.getInstance().setMposApplyInfo(gson.toJson(result.getData().getMposApplyRecord()));
//                                        getV().toWebView();
//
////                                        getV().JumpActivity(BuyPosActivity.class, false);
//                                    }
                                } else {
                                    getV().showToast("数据同步失败，请联系管理员");
                                }

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
     * 刷新用户状态
     *
     * @param merchId
     */
    public void refresh(final String merchId) {
        String version = Version.REFRESH.version();
        Api.getAPPService().refresh(merchId,null,null,null,null, version)
                .compose(XApi.<RefreshResult>getApiTransformer())
                .compose(XApi.<RefreshResult>getScheduler())
                .subscribe(new ApiSubcriber<RefreshResult>() {
                    @Override
                    public void onNext(RefreshResult result) {
                        getV().refreshed();
                        if (result.getCode() == ResultCode.SUCCESS.code()) {
                            AppUser.getInstance().setMerchId(merchId);
                            AppUser.getInstance().setToken(result.getData().getToken());
                            AppUser.getInstance().setStatus(result.getData().getMerchStatus());
                            AppUser.getInstance().setMerchlevelname(result.getData().getMerchlevelname());
                            AppUser.getInstance().setMerchlevelname2(result.getData().getMerchlevelname2());
                            AppUser.getInstance().setBranchlevelname(result.getData().getBranchlevelname());
                            AppUser.getInstance().setShoplevelname(result.getData().getShoplevelname());
                            AppUser.getInstance().setAgentlevelname(result.getData().getAgentlevelname());
                            AppUser.getInstance().setLeveName(result.getData().getLevelName());
                            Logger.i("refresh setIsAgent=" + result.getData().getIsAgent());
                            AppUser.getInstance().setIsAgent(result.getData().getIsAgent());
                            Gson gson = new Gson();
                            GetMerchInfoResult.DataBean bean = AppUser.getInstance().getMerchInfoBean();
                            AppUser.getInstance().setMerchInfo(gson.toJson(bean));
//                            GetAcctInfoResult.DataBeanX acctInfoBean = AppUser.getInstance().getAcctInfoBean();
//                            acctInfoBean.setSumAmt(result.getData().getSumAmt());
//                            AppUser.getInstance().setAcctInfo(gson.toJson(acctInfoBean));
                            getV().setInfo(result.getData());
                            IBus.IEvent iEvent = new IEvent();
                            iEvent.setId("refresh_person_info");
                            BusProvider.getBus().post(iEvent);
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

    public void getDynamicNews(String merchId) {
        if (AppTools.isEmpty(merchId)) {
            getV().showToast("商户号为空");
            return;
        }
        String version = Version.GETSHAREIMG.version();
        Api.getAPPService().getDynamicNews(merchId, version)
                .compose(XApi.<GetNewsResult>getApiTransformer())
                .compose(XApi.<GetNewsResult>getScheduler())
                .subscribe(new ApiSubcriber<GetNewsResult>() {
                    @Override
                    public void onNext(GetNewsResult result) {
                        if (result.getCode() == ResultCode.SUCCESS.code()) {
                            if (result.getData() != null) {
                                getV().setMarqueeView(result.getData());
                            }
                        }
                    }

                    @Override
                    protected void onFail(NetError error) {
                        getV().showError(error);
                    }

                });
    }

    /**
     * 获取通知
     */
    public void getNotice(String merchId) {
        String version = Version.GETNOTICE.version();
        Api.getAPPService().getNotice(merchId, "0", "1", version)
                .compose(XApi.<GetNoticeResult>getApiTransformer())
                .compose(XApi.<GetNoticeResult>getScheduler())
                .compose(getV().<GetNoticeResult>bindToLifecycle())
                .subscribe(new ApiSubcriber<GetNoticeResult>() {
                    @Override
                    public void onNext(GetNoticeResult result) {
                        if (result.getCode() == ResultCode.SUCCESS.code()) {
                            if (result.getData() != null) {
                                getV().showNotice(result);
                            } else {
//                                getV().showToast(result.getMessage());
                            }
                        }
                    }

                    @Override
                    protected void onFail(NetError error) {
                        getV().showError(error);
                    }

                });
    }

    public void updateNotice(GetNoticeResult.DataBean dataBean) {
        if (dataBean.getReadFlag() == 0) {
            String version = Version.UPDATENOTICE.version();
            Api.getAPPService().updateNotice(AppUser.getInstance().getMerchId(), String.valueOf(dataBean.getId()), version)
                    .compose(XApi.<BaseResults>getApiTransformer())
                    .compose(XApi.<BaseResults>getScheduler())
                    .compose(getV().<BaseResults>bindToLifecycle())
                    .subscribe(new ApiSubcriber<BaseResults>() {
                        @Override
                        public void onNext(BaseResults result) {
                            if (result.getCode() == ResultCode.SUCCESS.code()) {
                                Logger.i("修改状态成功");
                            }
                        }

                        @Override
                        protected void onFail(NetError error) {
                            getV().showError(error);
                        }

                    });
        }
    }

    public void getGoodsInfo(String merchId) {
        String version = Version.GETMERCHINFO.version();
        Api.getAPPService().getGoodsInfo(merchId,null, version)
                .compose(XApi.<GetGoodsInfoResults>getApiTransformer())
                .compose(XApi.<GetGoodsInfoResults>getScheduler())
                .compose(getV().<GetGoodsInfoResults>bindToLifecycle())
                .subscribe(new ApiSubcriber<GetGoodsInfoResults>() {
                    @Override
                    public void onNext(GetGoodsInfoResults result) {
                        if (result.getCode() == ResultCode.SUCCESS.code()) {
                            getV().setInfo(result.getData());
                        } else {
                            getV().showErrorDialog(result.getMessage());
                        }
                    }

                    @Override
                    protected void onFail(NetError error) {
                        getV().showError(error);
                    }

                });
    }

}