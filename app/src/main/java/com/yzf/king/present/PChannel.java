package com.yzf.king.present;

import com.yzf.king.kit.AppConfig;
import com.yzf.king.kit.AppTools;
import com.yzf.king.kit.AppUser;
import com.yzf.king.kit.Version;
import com.yzf.king.kit.utils.ResultCode;
import com.yzf.king.model.servicesmodels.BaseResults;
import com.yzf.king.model.servicesmodels.GetAuthIncomeInfoResult;
import com.yzf.king.model.servicesmodels.GetChannelInfoResults;
import com.yzf.king.model.servicesmodels.GetPlanChannelInfoResult;
import com.yzf.king.model.servicesmodels.IncomeBindCardResult;
import com.yzf.king.model.servicesmodels.IncomeResults;
import com.yzf.king.net.Api;
import com.yzf.king.ui.activitys.ChannelActivity;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubcriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

/**
 * ClaseName：PZmCredit
 * Description：上传图片逻辑
 * Author：JensenWei
 * QQ: 2188307188
 * Createtime：2017/3/21 17:00
 * Modified By：
 * Fixtime：2017/3/21 17:00
 * FixDescription：
 */

public class PChannel extends XPresent<ChannelActivity> {


    public void getPlanChannelInfo(String merchId) {
        if (AppTools.isEmpty(merchId)) {
            getV().showToast("商户号为空");
            return;
        }
        String version = Version.GETPLANCHANNELINFO.version();
        Api.getAPPService().getPlanChannelInfo(merchId, version)
                .compose(XApi.<GetPlanChannelInfoResult>getApiTransformer())
                .compose(XApi.<GetPlanChannelInfoResult>getScheduler())
                .compose(getV().<GetPlanChannelInfoResult>bindToLifecycle())
                .subscribe(new ApiSubcriber<GetPlanChannelInfoResult>() {
                    @Override
                    public void onNext(GetPlanChannelInfoResult result) {
                        if (result.getCode() == ResultCode.SUCCESS.code()) {
                            getV().setAdapter(result);
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
     * 获取渠道信息
     */
    public void getChannelInfo(String merchId, final String cardNo, final String bankAbbr, final String replaceBankAbbr, final String transType) {
        String version = Version.GETCHANNELINFO.version();
        Api.getAPPService().getChannelInfo(merchId, cardNo, "0", transType, version)
                .compose(XApi.<GetChannelInfoResults>getApiTransformer())
                .compose(XApi.<GetChannelInfoResults>getScheduler())
                .compose(getV().<GetChannelInfoResults>bindToLifecycle())
                .subscribe(new ApiSubcriber<GetChannelInfoResults>() {
                    @Override
                    public void onNext(GetChannelInfoResults getChannelInfoResults) {
                        if (getChannelInfoResults.getCode() == ResultCode.SUCCESS.code()) {
                            if (getChannelInfoResults.getData().size() > 0) {
                                boolean flag = false;
                                for (GetChannelInfoResults.DataBean dataBean : getChannelInfoResults.getData()) {
                                    if (dataBean.getNeedRegister() != null && dataBean.getNeedRegister().equals("1")) {
                                        flag = true;
                                        break;
                                    }
                                }
                                if (flag) {
                                    StringBuilder stringBuilder = new StringBuilder();
                                    stringBuilder.append(AppConfig.H5_URL + "channelBind/autoChannel.html?merchId=");
                                    stringBuilder.append(AppUser.getInstance().getMerchId());
                                    stringBuilder.append("&cardNo=");
                                    stringBuilder.append(cardNo);
                                    stringBuilder.append("&term_type=2");
                                    String isSpecial = "0";
                                    if (replaceBankAbbr != null && bankAbbr != null && replaceBankAbbr.contains(bankAbbr)) {
                                        isSpecial = "1";
                                    }
                                    stringBuilder.append("&isSpecial=" + isSpecial);
                                    stringBuilder.append("&token=" + AppUser.getInstance().getToken());
                                    stringBuilder.append("&transType=" + transType);
                                    getV().toWebView(stringBuilder.toString());

                                } else {
                                    getV().toPlanCardAct();
                                }
                            } else {
//                                getV().showToast("暂无渠道信息");
                                getV().toPlanCardAct();
                            }
                        } else {
                            getV().showToast(getChannelInfoResults.getMessage());
                        }
                    }

                    @Override
                    protected void onFail(NetError error) {
                        getV().showError(error);
                    }

                });
    }

    public void getAuthIncomeInfo(final String merchId, final String cardId, final String channelId) {
        String version = Version.GETAUTHINCOMEINFO.version();
        Api.getAPPService().getAuthIncomeInfo(merchId, cardId, channelId, version)
                .compose(XApi.<GetAuthIncomeInfoResult>getApiTransformer())
                .compose(XApi.<GetAuthIncomeInfoResult>getScheduler())
                .compose(getV().<GetAuthIncomeInfoResult>bindToLifecycle())
                .subscribe(new ApiSubcriber<GetAuthIncomeInfoResult>() {
                    @Override
                    public void onNext(GetAuthIncomeInfoResult result) {
                        if (result.getCode() == ResultCode.SUCCESS.code()) {
                            if (!result.getData().isIncomeFlag()) {//未进件
                                merchIncome(merchId, cardId, "SdkUserStoreBind", channelId, channelId);
                            } else {
                                if (!result.getData().isBindFlag()) {//未绑卡
                                    incomeBindCard(merchId, cardId, channelId, result.getData().getUserCode(), result.getData().getUserKey(), "1", AppConfig.H5_URL + "channelBind/goToApp.html", "1");
                                } else {//已进件已绑卡
                                    getV().toCreditRepaymentAct();
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

    public void merchIncome(final String merchId, final String cardId, String action, String branchId, final String channelId) {
        String version = Version.MERCHINCOME.version();
        Api.getAPPService().merchIncome(merchId, cardId, action, branchId, channelId, "1", version)
                .compose(XApi.<IncomeResults>getApiTransformer())
                .compose(XApi.<IncomeResults>getScheduler())
                .compose(getV().<IncomeResults>bindToLifecycle())
                .subscribe(new ApiSubcriber<IncomeResults>() {
                    @Override
                    public void onNext(IncomeResults incomeResults) {
                        if (incomeResults.getCode() == ResultCode.SUCCESS.code()) {//进件成功后去绑卡
                            incomeBindCard(merchId, cardId, channelId, incomeResults.getData().getUserCode(), incomeResults.getData().getUserKey(), "1", AppConfig.H5_URL + "channelBind/goToApp.html", "1");
                        } else {
                            getV().showToast(incomeResults.getMessage());
                        }

                    }

                    @Override
                    protected void onFail(NetError error) {
                        getV().showError(error);
                    }

                });
    }

    public void incomeBindCard(String merchId, String cardId, String channelId, String userCode, String userKey, String bindType, String bgUrl, String type) {
        String orderId = AppTools.craeateOrderId();
        String version = Version.INCOMEBINDCARD.version();
        Api.getAPPService().incomeBindCard(merchId, orderId, cardId, userCode, userKey, channelId, bindType, bgUrl, type, version)
                .compose(XApi.<IncomeBindCardResult>getApiTransformer())
                .compose(XApi.<IncomeBindCardResult>getScheduler())
                .compose(getV().<IncomeBindCardResult>bindToLifecycle())
                .subscribe(new ApiSubcriber<IncomeBindCardResult>() {
                    @Override
                    public void onNext(IncomeBindCardResult result) {
                        if (result.getCode() == ResultCode.SUCCESS.code()) {
                            getV().toWebView(result.getData().getUrl());
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
