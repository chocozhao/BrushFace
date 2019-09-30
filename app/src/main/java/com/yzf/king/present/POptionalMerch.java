package com.yzf.king.present;


import com.google.gson.Gson;
import com.yzf.king.kit.AppUser;
import com.yzf.king.kit.Version;
import com.yzf.king.kit.utils.ResultCode;
import com.yzf.king.model.servicesmodels.GetMerchInfoResult;
import com.yzf.king.model.servicesmodels.GetPosChanlFeeResult;
import com.yzf.king.net.Api;
import com.yzf.king.ui.activitys.OptionalMerchActivity;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubcriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

/**
 * ClaseName：POptionalMerch
 * Description：
 * Author：chocozhao
 * QQ: 1027313530
 * Createtime：2019/5/31 10:15
 * Modified By：
 * Fixtime：2019/5/31 10:15
 * FixDescription：
 **/

public class POptionalMerch extends XPresent<OptionalMerchActivity> {
    /**
     * 获取商户信息
     *
     * @param merchid
     */
    public void getOptionalMerch(String merchid) {
        String version = Version.GETMERCHINFO.version();
        Api.getAPPService().getMerchInfo(merchid, version)
                .compose(XApi.<GetMerchInfoResult>getApiTransformer())
                .compose(XApi.<GetMerchInfoResult>getScheduler())
                .compose(getV().<GetMerchInfoResult>bindToLifecycle())
                .subscribe(new ApiSubcriber<GetMerchInfoResult>() {
                    @Override
                    public void onNext(GetMerchInfoResult result) {
                        if (result.getCode() == ResultCode.SUCCESS.code()) {
                            Gson gson = new Gson();
                            AppUser.getInstance().setMerchInfo(gson.toJson(result.getData()));
//                            getV().setSettleInfo(result.getData());
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
     * mpospay
     */
    public void getPosChanlFee(String merchId) {
        String version = Version.GETPOSCHANLFEE.version();
        Api.getAPPService().getPosChanlFee(merchId, version)
                .compose(XApi.<GetPosChanlFeeResult>getApiTransformer())
                .compose(XApi.<GetPosChanlFeeResult>getScheduler())
                .compose(getV().<GetPosChanlFeeResult>bindToLifecycle())
                .subscribe(new ApiSubcriber<GetPosChanlFeeResult>() {
                    @Override
                    public void onNext(GetPosChanlFeeResult result) {
                        if (result.getCode() == ResultCode.SUCCESS.code()) {
                            if (result.getData() != null && result.getData().size() > 0) {
                                getV().setAdapter(result.getData());
                            } else {
                                getV().showEmptyView("暂无数据");
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

}
