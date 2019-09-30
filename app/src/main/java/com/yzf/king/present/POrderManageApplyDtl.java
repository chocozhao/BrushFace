package com.yzf.king.present;


import com.yzf.king.kit.Version;
import com.yzf.king.kit.utils.ResultCode;
import com.yzf.king.model.servicesmodels.GetAddApplyInfoResults;
import com.yzf.king.model.servicesmodels.GetMachinApplyInfoDtlResult;
import com.yzf.king.model.servicesmodels.GetReApplyOrderResults;
import com.yzf.king.model.servicesmodels.GetSunMerchInfoListResult;
import com.yzf.king.net.Api;
import com.yzf.king.ui.activitys.OrderManageApplyDtlActivity;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubcriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

/**
 * ClaseName：POrderManageApplyDtl
 * Description：
 * Author：chocozhao
 * QQ: 1027313530
 * Createtime：2019/7/15 09:27
 * Modified By：
 * Fixtime：2019/7/15 09:27
 * FixDescription：
 **/

public class POrderManageApplyDtl extends XPresent<OrderManageApplyDtlActivity> {


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
//                                getV().showErrorView(result.getMessage());
                            }
                        } else {
//                            getV().showErrorView(result.getMessage());
                        }
                    }

                    @Override
                    protected void onFail(NetError error) {
                        getV().showError(error);
                    }

                });
    }


    /**
     * 补交资料SubListBean
     * @param merchId
     * @param shopId
     */

    public void getSunMerchInfoList(String merchId, String shopId) {
        String version = Version.GETSHOPINFO.version();
        Api.getAPPService().getSunMerchInfoList(merchId, shopId, version)
                .compose(XApi.<GetSunMerchInfoListResult>getApiTransformer())
                .compose(XApi.<GetSunMerchInfoListResult>getScheduler())
                .compose(getV().<GetSunMerchInfoListResult>bindToLifecycle())
                .subscribe(new ApiSubcriber<GetSunMerchInfoListResult>() {
                    @Override
                    public void onNext(GetSunMerchInfoListResult result) {
                        if (result.getCode() == ResultCode.SUCCESS.code()) {
                            getV().setSunMerchInfoListAdapter(result.getData());
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
     * 订单失效
     * @param merchId
     * @param orderId
     */
    public void reApplyOrder(String merchId, String orderId) {
        String version = Version.GETSHOPINFO.version();
        Api.getAPPService().reApplyOrder(merchId, orderId, version)
                .compose(XApi.<GetReApplyOrderResults>getApiTransformer())
                .compose(XApi.<GetReApplyOrderResults>getScheduler())
                .compose(getV().<GetReApplyOrderResults>bindToLifecycle())
                .subscribe(new ApiSubcriber<GetReApplyOrderResults>() {
                    @Override
                    public void onNext(GetReApplyOrderResults result) {
                        if (result.getCode() == ResultCode.SUCCESS.code()) {
                            getV().showToast(result.getMessage());
                            getV().finish();
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
